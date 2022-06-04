package com.example.dayjourney.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class DiaryEntryViewModel(application: Application): AndroidViewModel(application) {

    val readAllEntries: LiveData<List<DiaryEntry>>
    private val repository: DiaryEntryRepository

    init {
        val diaryEntryDao = DiaryEntryDatabase.getDatabase(application).diaryEntryDao()
        repository = DiaryEntryRepository(diaryEntryDao)
        readAllEntries = repository.readAllEntries
    }

    fun addEntry(diaryEntry: DiaryEntry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEntry(diaryEntry)
        }
    }
}