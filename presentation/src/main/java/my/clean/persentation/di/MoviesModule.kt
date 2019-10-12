package my.clean.persentation.di

import androidx.room.Room
import my.clean.data.api.RemoteMoviesApi
import my.clean.data.db.MoviesDatabase
import my.clean.data.entities.MovieDataEntityMapper
import my.clean.data.entities.MovieEntityDataMapper
import my.clean.data.repository.MoviesCacheImpl
import my.clean.data.repository.MoviesRemoteImpl
import my.clean.data.repository.MoviesRepositoryImpl
import my.clean.domian.repositories.MoviesRepository
import my.clean.domian.usecases.GetMoviesUseCase
import my.clean.persentation.common.AsyncFlowableTransformer
import my.clean.persentation.mapper.MoviesEntityMapper
import my.clean.persentation.movies.MoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_MOVIES_USE_CASE = "getMoviesUseCase"
private const val REMOTE = "remote"
private const val LOCAL = "local"
private const val DATABASE = "database"
private const val DATABASE_NAME = "movies"

val mMoviesRepositoryModule = module {
    single(name = REMOTE) { MoviesRemoteImpl(api = get(API)) }
    single(name = LOCAL) {
        MoviesCacheImpl(
            database = get(DATABASE),
            entityToDataMapper = MovieEntityDataMapper(),
            dataToEntityMapper = MovieDataEntityMapper()
        )
    }
    single {
        MoviesRepositoryImpl(
            remote = get(REMOTE),
            cache = get(LOCAL)
        ) as MoviesRepository
    }
}

val mMoviesUseCaseModules = module {
    factory(name = GET_MOVIES_USE_CASE) {
        GetMoviesUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
}

val mMoviesNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RemoteMoviesApi::class.java) }
}

val mMoviesLocalModules = module {
    single(name = DATABASE) {
        Room.databaseBuilder(
            androidApplication(),
            MoviesDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}

val mMoviesViewModels = module {
    viewModel {
        MoviesViewModel(
            getMoviesUseCase = get(GET_MOVIES_USE_CASE),
            mapper = MoviesEntityMapper()
        )
    }
}