package com.example.kbbi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KBBIWord::class], version = 1)
abstract class KBBIDatabase : RoomDatabase() {
    abstract fun wordDao(): KBBIWordDao

    companion object {
        @Volatile
        private var Instance: KBBIDatabase? = null

        fun getDatabase(context: Context): KBBIDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    KBBIDatabase::class.java,
                    "kbbi_db"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}