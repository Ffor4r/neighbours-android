package com.udevapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udevapp.data.db.entity.DefaultPlace

@Dao
interface DefaultPlaceDao {

    @Query("SELECT * FROM defaultplace WHERE user_id = :userId")
    fun findByUserId(userId: String): DefaultPlace

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(defaultPlace: DefaultPlace)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg defaultPlaces: DefaultPlace)

}