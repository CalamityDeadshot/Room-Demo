@file:OptIn(ExperimentalCoroutinesApi::class)

package ru.itschool.roomdemo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
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

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val authors = dao.getAuthors()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val articles = searchQuery.flatMapLatest { query ->
        dao.getArticles(query)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onSearchQueryChanged(query: String) {
        _searchQuery.update { query }
    }
}