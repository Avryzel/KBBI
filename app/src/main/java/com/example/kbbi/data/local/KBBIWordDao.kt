package com.example.kbbi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KBBIWordDao {
    @Query("SELECT * FROM kbbi_words ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWord(): KBBIWord

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: List<KBBIWord>)
}