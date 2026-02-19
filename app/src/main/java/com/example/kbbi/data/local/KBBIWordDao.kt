package com.example.kbbi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface KBBIWordDao {
    @Query("SELECT * FROM kbbi_words ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWord(): KBBIWord?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<KBBIWord>)

    @Update
    suspend fun updateWord(word: KBBIWord)
}