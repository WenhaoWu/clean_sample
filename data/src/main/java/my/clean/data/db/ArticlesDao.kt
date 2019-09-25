package my.clean.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import my.clean.data.entities.NewsPublisherData

@Dao
interface ArticlesDao {
    @Query("Select * from news_articles")
    fun getAllArticles(): Flowable<List<NewsPublisherData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllArticles(articles: List<NewsPublisherData>)

    @Query("Delete FROM news_articles")
    fun clear()
}