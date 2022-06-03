package com.example.dayjourney.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiaryEntryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDiaryEntry(diaryEntry: DiaryEntry)

    @Query("SELECT * FROM entry_table ORDER by id DESC")
    fun readAllDiaryEntries(): LiveData<List<DiaryEntry>>
}