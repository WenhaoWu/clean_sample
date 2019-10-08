package my.clean.domian.usecases

import io.reactivex.Flowable
import my.clean.domian.common.BaseFlowableUseCase
import my.clean.domian.common.FlowableRxTransformer
import my.clean.domian.entities.MovieEntity
import my.clean.domian.repositories.MoviesRepository

class GetLocalMoviesUseCase(
    private val transformer: FlowableRxTransformer<List<MovieEntity>>,
    private val repository: MoviesRepository
) : BaseFlowableUseCase<List<MovieEntity>>(transformer) {

    companion object {
        private const val PARAM_FILE_MOVIES_ENTITY = "param:MoviesStatus"
    }

    override fun createFlowable(data: Map<String, Any>?): Flowable<List<MovieEntity>> {
        return repository.getLocalMovies()
    }

    fun getMovies(): Flowable<List<MovieEntity>> {
        val data = HashMap<String, String>()
        return single(data)
    }

}