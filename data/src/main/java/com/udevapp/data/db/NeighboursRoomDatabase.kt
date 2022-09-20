package com.udevapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.udevapp.data.db.dao.DefaultPlaceDao
import com.udevapp.data.db.entity.DefaultPlace

@Database(entities = [DefaultPlace::class], version = 2)
abstract class NeighboursRoomDatabase : RoomDatabase() {

    abstract fun defaultPlaceDao(): DefaultPlaceDao

    companion object {
        @Volatile
        private var INSTANCE: NeighboursRoomDatabase? = null

        fun getDatabase(context: Context): NeighboursRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NeighboursRoomDatabase::class.java,
                    "neighbours_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}