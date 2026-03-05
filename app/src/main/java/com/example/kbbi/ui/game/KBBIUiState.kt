package com.example.kbbi.ui.game

import com.example.kbbi.data.local.KBBIWord

data class KBBIUiState(
    val currentWord: KBBIWord? = null,
    val userGuess: String = "",
    val streak: Int = 0,
    val isCorrect: Boolean? = null,
    val options: List<String> = emptyList()
)