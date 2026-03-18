package com.example.kbbi.data.repository

import android.content.res.AssetManager
import com.example.kbbi.data.local.KBBIWord
import com.example.kbbi.data.local.KBBIWordDao
import com.example.kbbi.data.model.KBBIResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class OfflineKBBIWordRepository(
    private val wordDao: KBBIWordDao,
    private val assets: AssetManager,
    private val json: Json = Json { ignoreUnknownKeys = true }
) : KBBIWordRepository {
    override suspend fun getRandomWord(): KBBIWord? = wordDao.getRandomWord()

    override suspend fun insertWords(words: List<KBBIWord>) = wordDao.insertWords(words)

    override suspend fun updateWord(word: KBBIWord) = wordDao.updateWord(word)

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun initializeDatabase() {
        val count = wordDao.getWordCount()

        if (count > 0) {
            return
        }

        val partFiles = listOf(
            "kbbi_v_part1.json",
            "kbbi_v_part2.json",
            "kbbi_v_part3.json",
            "kbbi_v_part4.json",
        )

        partFiles.forEach { fileName ->
            assets.open(fileName).use { inputStream ->
                val response = json.decodeFromStream<KBBIResponse>(inputStream)

                val filteredWord = response.data.entri.filter {
                    it.bentuk_tidak_baku.isNotEmpty()
                }.map {
                    KBBIWord(
                        formalWord = it.nama,
                        informalWord = it.bentuk_tidak_baku.first()
                    )
                }

                insertWords(filteredWord)
            }
        }
    }
}