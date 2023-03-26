package ru.itschool.roomdemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn {
                        items(
                            items = authors,
                            key = { it.id }
                        ) {
                            Text(text = it.name)
                        }
                    }
                }
            }
        }
    }
}