package my.clean.data.repository

import io.reactivex.Flowable
import my.clean.data.db.ArticlesDao
import my.clean.data.db.NewsDatabase
import my.clean.data.entities.NewsDataEntityMapper
import my.clean.data.entities.NewsEntityDataMapper
import my.clean.domian.entities.NewsSourcesEntity

class NewsCacheImpl(
    database: NewsDatabase,
    private val entityToDataMapper: NewsEntityDataMapper,
    private val dataToEntityMapper: NewsDataEntityMapper
) : NewsDataStore {

    private val dao: ArticlesDao = database.getArticlesDao()

    override fun getNews(): Flowable<NewsSourcesEntity> =
        dao.getAllArticles().map { dataToEntityMapper.mapToEntity(it) }

    fun saveArticles(it: NewsSourcesEntity) = dao.run {
        clear()
        saveAllArticles(
            it.articles.map { entityToDataMapper.mapArticleToEntity(it) }
        )

    }
}