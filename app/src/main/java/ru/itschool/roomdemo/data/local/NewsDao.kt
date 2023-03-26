package ru.itschool.roomdemo.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM Author")
    fun getAuthors(): Flow<List<Author>>
}