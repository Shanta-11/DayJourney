package com.example.dayjourney.data

import androidx.lifecycle.LiveData

class DiaryEntryRepository(private val diaryEntryDao: DiaryEntryDao) {
    val readAllEntries: LiveData<List<DiaryEntry>> = diaryEntryDao.readAllDiaryEntries()

    suspend fun addEntry(diaryEntry: DiaryEntry){
        diaryEntryDao.addDiaryEntry(diaryEntry)
    }


}

