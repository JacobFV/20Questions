package com.example.a20questions.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class, SavedGame::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun savedGameDao(): SavedGameDao
}