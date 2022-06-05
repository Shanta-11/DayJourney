package com.example.dayjourney.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DiaryEntryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDiaryEntry(diaryEntry: DiaryEntry)

    @Update
    suspend fun updateEntry(diaryEntry: DiaryEntry)

    @Delete
    suspend fun deleteEntry(diaryEntry: DiaryEntry)

    @Query("DELETE FROM entry_table")
    suspend fun deleteAllEntries()

    @Query("SELECT * FROM entry_table ORDER by id DESC")
    fun readAllDiaryEntries(): LiveData<List<DiaryEntry>>
}