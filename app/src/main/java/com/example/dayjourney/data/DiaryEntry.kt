package com.example.dayjourney.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "entry_table")
data class DiaryEntry(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "text")val text: String,
    @ColumnInfo(name = "date") val date: String
)