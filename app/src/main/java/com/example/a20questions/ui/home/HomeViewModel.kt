package com.example.a20questions.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.a20questions.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val savedGameDao: SavedGameDao = AppDatabase.getDatabase(application).savedGameDao()
    val savedGamesList: LiveData<List<SavedGame>>

    init {
        savedGamesList = savedGameDao.allSavedGame
        Log.d("viewmodel", savedGamesList.toString())
        runBlocking { launch { addSavedGames() } }
    }

    private suspend fun addSavedGames() {
        withContext(Dispatchers.IO) {
            savedGameDao.insertSavedGames(savedGame1, savedGame2, savedGame3, savedGame4)
        }
    }
}