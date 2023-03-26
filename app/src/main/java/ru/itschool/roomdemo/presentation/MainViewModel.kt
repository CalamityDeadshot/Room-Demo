package ru.itschool.roomdemo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.itschool.roomdemo.data.local.Database

class MainViewModel(
    application: Application
): AndroidViewModel(application) {

    private val database = Room.databaseBuilder(
        application,
        Database::class.java,
        "my_database"
    ).build()

    private val dao = database.newsDao

    val authors = dao.getAuthors()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val articles = dao.getArticles("Ch")
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}