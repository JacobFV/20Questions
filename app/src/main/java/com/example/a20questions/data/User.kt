package com.example.a20questions.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int, // uid = User ID
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String
)