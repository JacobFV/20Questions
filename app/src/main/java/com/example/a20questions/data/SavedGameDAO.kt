package com.example.a20questions.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SavedGameDao {
    @Query("SELECT * FROM savedgames")
    fun getAll(): List<SavedGame>

    @Query("SELECT * FROM savedgames WHERE gid IN (:gameIds)")
    fun loadAllByIds(gameIds: IntArray): List<SavedGame>

    @Insert
    fun insertSavedGames(vararg savedGames: SavedGame)

    @Delete
    fun delete(savedGame: SavedGame)
}