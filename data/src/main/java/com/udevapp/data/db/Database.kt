package com.udevapp.data.db

import android.content.Context
import androidx.room.Room

class Database(
    private val context: Context,
    val neighbours: NeighboursRoomDatabase = Room.databaseBuilder(
        context,
        NeighboursRoomDatabase::class.java, "neighbours_database"
    ).build()
) {
}