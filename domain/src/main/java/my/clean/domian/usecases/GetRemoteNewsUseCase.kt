package my.clean.domian.usecases

import io.reactivex.Flowable
import my.clean.domian.common.BaseFlowableUseCase
import my.clean.domian.common.FlowableRxTransformer
import my.clean.domian.entities.NewsSourcesEntity
import my.clean.domian.repositories.NewsRepository

//It will get you the only the latest by fetching it from remote
class GetRemoteNewsUseCase(
    private val transformer: FlowableRxTransformer<NewsSourcesEntity>,
    private val repositories: NewsRepository
) : BaseFlowableUseCase<NewsSourcesEntity>(transformer) {

    companion object {
        private const val PARAM_FILE_NEWS_ENTITY = "param:NewsStatus"
    }

    override fun createFlowable(data: Map<String, Any>?): Flowable<NewsSourcesEntity> {
        return repositories.getNews()
    }

    fun getNews(): Flowable<NewsSourcesEntity> {
        val data = HashMap<String, String>()
        return single(data)
    }


}