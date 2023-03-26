package ru.itschool.roomdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        Article::class, Author::class
    ]
)
abstract class Database: RoomDatabase() {
    abstract val newsDao: NewsDao
}