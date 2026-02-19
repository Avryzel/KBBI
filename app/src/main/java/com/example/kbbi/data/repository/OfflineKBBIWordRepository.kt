package com.example.kbbi.data.repository

import com.example.kbbi.data.local.KBBIWord
import com.example.kbbi.data.local.KBBIWordDao

class OfflineKBBIWordRepository(private val wordDao: KBBIWordDao) : KBBIWordRepository {
    override suspend fun getRandomWord(): KBBIWord? = wordDao.getRandomWord()

    override suspend fun insertWords(words: List<KBBIWord>) = wordDao.insertWords(words)

    override suspend fun updateWord(word: KBBIWord) = wordDao.updateWord(word)
}