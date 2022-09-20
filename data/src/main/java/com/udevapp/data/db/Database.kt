package com.udevapp.data.db

import android.content.Context
import androidx.room.Room

class Database(
    private val context: Context,
    val neighbours: NeighboursRoomDatabase = NeighboursRoomDatabase.getDatabase(context)
) {
}