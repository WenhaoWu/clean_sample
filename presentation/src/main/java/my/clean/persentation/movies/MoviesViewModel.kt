package my.clean.persentation.movies

import androidx.lifecycle.MutableLiveData
import my.clean.domian.common.Mapper
import my.clean.domian.entities.MovieEntity
import my.clean.domian.usecases.GetMoviesUseCase
import my.clean.persentation.common.BaseViewModel
import my.clean.persentation.entities.Movie
import my.clean.presentation.entities.Data
import my.clean.presentation.entities.Error
import my.clean.presentation.entities.Status

class MoviesViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val mapper: Mapper<MovieEntity, Movie>
) : BaseViewModel() {

    companion object {
        private val TAG = "ViewModel"
    }

    var mMovies = MutableLiveData<Data<List<Movie>>>()

    fun fetchMovies() {
        val disposable = getMoviesUseCase.getMovies()
            .flatMap { mapper.flowable(it) }
            .subscribe({ response ->
                mMovies.value = Data(responseType = Status.SUCCESSFUL, data = response)
            }, { error ->
                mMovies.value = Data(responseType = Status.ERROR, error = Error(error.message))
            }, {

            })

        addDisposable(disposable)
    }

    fun getMoviesLiveData() = mMovies
}