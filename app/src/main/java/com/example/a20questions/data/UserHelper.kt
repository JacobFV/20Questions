package com.example.a20questions.data

import android.app.Application
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.math.max
import kotlin.random.Random

fun isValidUser(username: String, password: String, application: Application): Boolean {
    var retVar = false
    runBlocking { launch {
        retVar = _isValidUser(username, password, application)
    } }
    return retVar
}

private suspend fun _isValidUser(username: String, password: String, application: Application): Boolean {
    var retVal = false
    withContext(Dispatchers.IO) {
        val allUsers = AppDatabase.getDatabase(application).userDao().getAll()
        retVal = allUsers.any {
            (it.username == username) && (it.password == password)
        }
    }
    return retVal
}

fun createUser(username: String, password: String, application: Application) {
    runBlocking { launch { _createUser(username, password, application) } }
}

private suspend fun _createUser(username: String, password: String, application: Application) {
    withContext(Dispatchers.IO) {
        val allUsers = AppDatabase
            .getDatabase(application)
            .userDao()
            .insertUsers(
                User(Random.nextInt(), username, password)
            )
    }
}