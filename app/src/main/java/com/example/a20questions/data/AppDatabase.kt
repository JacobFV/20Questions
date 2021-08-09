package com.example.a20questions.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(User::class, SavedGame::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun savedGameDao(): SavedGameDao

    // thanks: https://medium.com/@tonia.tkachuk/android-app-example-using-room-database-63f7091e69af
    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "atwentyqdb"

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, DB_NAME).build()
                    }
                }
            }

            return INSTANCE!!
        }
    }
    // end thanks
}