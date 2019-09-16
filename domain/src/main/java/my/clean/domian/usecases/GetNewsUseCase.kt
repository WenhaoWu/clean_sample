package my.clean.domian.usecases

import io.reactivex.Flowable
import my.clean.domian.common.BaseFlowableUseCase
import my.clean.domian.common.FlowableRxTransformer
import my.clean.domian.entities.NewsSourcesEntity
import my.clean.domian.repositories.NewsRepository

/**
 * It will first get articles from the local database and also update it with the latest
 * articles from remote
 */
class GetNewsUseCase(
    private val transformer: FlowableRxTransformer<NewsSourcesEntity>,
    private val repositories: NewsRepository
) : BaseFlowableUseCase<NewsSourcesEntity>(transformer) {

    override fun createFlowable(data: Map<String, Any>?): Flowable<NewsSourcesEntity> {
        return repositories.getNews()
    }

    fun getNews(): Flowable<NewsSourcesEntity> {
        val data = HashMap<String, String>()
        return single(data)
    }
}