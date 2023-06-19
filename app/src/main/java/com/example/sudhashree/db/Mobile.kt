package com.example.sudhashree.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (tableName = "MOBdata",
    indices = [Index(value = ["mobileModel"], unique = true)])
data class Mobile(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mobileModel: String,
    val mobilePrice: String,
    val quantityWhite: String,
    val quantityBlack: String)
