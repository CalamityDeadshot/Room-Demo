package ru.itschool.roomdemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM Author")
    fun getAuthors(): Flow<List<Author>>

    @Insert
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM Article WHERE title LIKE '%' || :query || '%'")
    fun getArticles(query: String): Flow<List<ArticleWithAuthor>>
}