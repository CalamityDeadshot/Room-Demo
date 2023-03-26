@file:OptIn(ExperimentalFoundationApi::class)

package ru.itschool.roomdemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ru.itschool.roomdemo.presentation.theme.RoomDemoTheme

class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDemoTheme {
                val authors by viewModel.authors.collectAsState()
                val articles by viewModel.articles.collectAsState()
                val searchQuery by viewModel.searchQuery.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = searchQuery,
                            onValueChange = viewModel::onSearchQueryChanged
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                        ) {
                            items(
                                items = articles,
                                key = { it.article.id }
                            ) {
                                Column(
                                    modifier = Modifier.animateItemPlacement()
                                ) {
                                    Text(
                                        text = it.article.title,
                                        style = MaterialTheme.typography.h4
                                    )
                                    Text(
                                        text = "Author: ${it.author.name}",
                                        style = MaterialTheme.typography.subtitle1
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}