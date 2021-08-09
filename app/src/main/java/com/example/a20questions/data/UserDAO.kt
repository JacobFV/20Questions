package com.example.a20questions.data

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    //@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    //fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE username LIKE :uname " +
            "LIMIT 1")
    fun findByName(uname: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(vararg users: User)

    @Delete
    fun delete(user: User)
}