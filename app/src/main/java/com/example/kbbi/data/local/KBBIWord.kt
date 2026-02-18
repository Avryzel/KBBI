package com.example.kbbi.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kbbi_words")
data class KBBIWord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "formal_word") val formalWord: String,
    @ColumnInfo(name = "informal_word") val informalWord: String
)