package com.example.dayjourney.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "entry_table")
data class DiaryEntry(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "text")val text: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name= "mood") val mood: Int
) : Parcelable

data class MoodImg(
    val id: Int,
    @DrawableRes val imageResourceId : Int

)