package com.example.dayjourney.data

import androidx.lifecycle.LiveData

class DiaryEntryRepository(private val diaryEntryDao: DiaryEntryDao) {
    val readAllEntries: LiveData<List<DiaryEntry>> = diaryEntryDao.readAllDiaryEntries()

    suspend fun addEntry(diaryEntry: DiaryEntry){
        diaryEntryDao.addDiaryEntry(diaryEntry)
    }

    suspend fun updateEntry(diaryEntry: DiaryEntry){
        diaryEntryDao.updateEntry(diaryEntry)
    }

    suspend fun deleteEntry(diaryEntry: DiaryEntry){
        diaryEntryDao.deleteEntry(diaryEntry)
    }

    suspend fun deleteAllENtries(){
        diaryEntryDao.deleteAllEntries()
    }


}

