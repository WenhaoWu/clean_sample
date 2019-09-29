package my.clean.persentation.di

import androidx.room.Room
import my.clean.data.api.RemoteNewsApi
import my.clean.data.db.NewsDatabase
import my.clean.data.entities.NewsDataEntityMapper
import my.clean.data.entities.NewsEntityDataMapper
import my.clean.data.repository.NewsCacheImpl
import my.clean.data.repository.NewsRemoteImpl
import my.clean.data.repository.NewsRepositoryImpl
import my.clean.domian.repositories.NewsRepository
import my.clean.domian.usecases.GetNewsUseCase
import my.clean.persentation.common.AsyncFlowableTransformer
import my.clean.persentation.mapper.NewsEntityMapper
import my.clean.persentation.news.NewsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

private const val BASE_URL = "https://newsapi.org/v2/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_NEWS_USE_CASE = "getNewsUseCase"
private const val REMOTE = "remote"
private const val LOCAL = "local"
private const val DATABASE = "database"
private const val DATABASE_NAME = "news_articles"

val mRepositoryModules = module {
    single(name = REMOTE) { NewsRemoteImpl(api = get(API)) }
    single(name = LOCAL) {
        NewsCacheImpl(
            database = get(DATABASE),
            entityToDataMapper = NewsEntityDataMapper(),
            dataToEntityMapper = NewsDataEntityMapper()
        )
    }
    single {
        NewsRepositoryImpl(
            remote = get(REMOTE),
            cache = get(LOCAL)
        ) as NewsRepository
    }
}

val mUseCaseModules = module {
    factory(name = GET_NEWS_USE_CASE) {
        GetNewsUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RemoteNewsApi::class.java) }
}

val mLocalModules = module {
    single(name = DATABASE) {
        Room.databaseBuilder(
            androidApplication(),
            NewsDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}

val mViewModels = module {
    viewModel {
        NewsViewModel(getNewsUseCase = get(GET_NEWS_USE_CASE), mapper = NewsEntityMapper())
    }
}