package com.example.a20questions.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.a20questions.data.AppDatabase
import com.example.a20questions.data.SavedGame
import com.example.a20questions.data.SavedGameDao

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val savedGameDao: SavedGameDao = AppDatabase.getDatabase(application).savedGameDao()
    val savedGamesList: LiveData<List<SavedGame>>

    init {
        savedGamesList = savedGameDao.allSavedGame
    }
}