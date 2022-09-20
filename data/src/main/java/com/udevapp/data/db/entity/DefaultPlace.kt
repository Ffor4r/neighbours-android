package com.udevapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(
        value = ["user_id"],
        unique = true
    )]
)
data class DefaultPlace(

    @PrimaryKey(autoGenerate = true) val id: Int? = null,

    @ColumnInfo(name = "user_id") val userId: String?,

    @ColumnInfo(name = "default_index") val defaultIndex: Int?,

    @ColumnInfo(name = "place_id") val placeId: String?
)