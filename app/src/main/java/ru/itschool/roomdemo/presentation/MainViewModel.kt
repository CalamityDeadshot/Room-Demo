package ru.itschool.roomdemo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import ru.itschool.roomdemo.data.local.Database

class MainViewModel(
    application: Application
): AndroidViewModel(application) {

    val database = Room.databaseBuilder(
        application,
        Database::class.java,
        "my_database"
    ).build()


}