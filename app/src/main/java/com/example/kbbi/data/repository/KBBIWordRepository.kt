package com.example.kbbi.data.repository

import com.example.kbbi.data.local.KBBIWord

interface KBBIWordRepository {
    suspend fun getRandomWord(): KBBIWord?
    suspend fun insertWords(words: List<KBBIWord>)
    suspend fun updateWord(word: KBBIWord)
    suspend fun initializeDatabase()
}