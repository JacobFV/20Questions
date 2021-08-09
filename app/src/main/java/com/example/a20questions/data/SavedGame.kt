package com.example.a20questions.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// game data: only saved after the game is completed
// partial games are not saved
@Entity
data class SavedGame(
    @PrimaryKey val gid: Int, // gid = game ID
    @ColumnInfo(name = "time_completed_seconds") val time_completed_seconds: Int, // raw seconds since Jan 1, 1970 (used for sorting)
    @ColumnInfo(name = "time_completed_formatted") val time_completed_formatted: String, // Gregorian style "Oct 5, 2021 5:30PM"
    @ColumnInfo(name = "questions_and_answers") val questions_and_answers: String, // comma-separated questions and answers
    @ColumnInfo(name = "did_win") val did_win: Boolean,
    @ColumnInfo(name = "username") val username: String
)
