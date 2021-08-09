package com.example.a20questions.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SavedGameDao {

    @Query("SELECT * FROM savedgame ORDER BY time_completed_seconds ASC")
    fun getAll(): List<SavedGame>

    @Query("SELECT * FROM savedgame WHERE username = (:username) ORDER BY time_completed_seconds ASC")
    fun getAllForUser(username: String): List<SavedGame>

    //@Query("SELECT * FROM savedgame WHERE gid IN (:gameIds) ORDER BY time_completed_seconds ASC")
    //fun loadAllByIds(gameIds: IntArray): List<SavedGame>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSavedGames(vararg savedGames: SavedGame)

    @Delete
    fun delete(savedGame: SavedGame)

    @Query("DELETE FROM savedgame")
    fun deleteAll()
}