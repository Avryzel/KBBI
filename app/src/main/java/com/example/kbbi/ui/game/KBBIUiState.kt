package com.example.kbbi.ui.game

import com.example.kbbi.data.local.KBBIWord

data class KBBIUiState(
    val currentWord: KBBIWord? = null,
    val userGuess: String = "",
    val score: Int = 0,
    val isCorrect: Boolean = false
)