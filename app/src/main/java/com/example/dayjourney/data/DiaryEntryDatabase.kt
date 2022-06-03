package com.example.dayjourney.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DiaryEntry::class], version = 1, exportSchema = false)

abstract class DiaryEntryDatabase: RoomDatabase() {

    abstract fun diaryEntryDao(): DiaryEntryDao

    companion object{
        @Volatile
        private var INSTANCE: DiaryEntryDatabase? = null

        fun getDatabase(context: Context): DiaryEntryDatabase{
            val tempinstance = INSTANCE
            if(tempinstance != null){
                return tempinstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiaryEntryDatabase::class.java,
                    "diary_entry_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}