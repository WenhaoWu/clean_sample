package my.clean.persentation

import android.app.Application
import my.clean.persentation.di.*
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(
            this,
            listOf(
                mMoviesNetworkModules,
                mMoviesViewModels,
                mMoviesRepositoryModule,
                mMoviesUseCaseModules,
                mMoviesLocalModules
            )

        )
    }
}