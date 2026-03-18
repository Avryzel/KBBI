package com.example.kbbi.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kbbi.data.KBBIDataApplication
import com.example.kbbi.data.repository.KBBIWordRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class KBBIViewModel(private val kbbiWordRepository: KBBIWordRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(KBBIUiState())
    val uiState: StateFlow<KBBIUiState> = _uiState.asStateFlow()

    fun getNextWord() {
        viewModelScope.launch {
            _uiState.update {
                val newWord = kbbiWordRepository.getRandomWord()
                val shuffledOptions =
                    listOfNotNull(newWord?.formalWord, newWord?.informalWord).shuffled()

                it.copy(
                    isCorrect = null,
                    currentWord = newWord,
                    userGuess = "",
                    options = shuffledOptions,
                    isLoading = false
                )
            }
        }
    }

    fun updateUserGuess(newGuess: String) {
        _uiState.update { currentState ->
            currentState.copy(userGuess = newGuess)
        }
    }

    fun checkUserGuess() {
        if (_uiState.value.isCorrect != null) {
            return
        }

        viewModelScope.launch {
            _uiState.update { currentState ->
                val isCorrect = currentState.userGuess.trim().equals(
                    currentState.currentWord?.formalWord,
                    ignoreCase = true
                )

                currentState.copy(
                    isCorrect = isCorrect,
                    userGuess = "",
                    streak = if (isCorrect) currentState.streak + 1 else 0
                )
            }

            delay(2000L)

            getNextWord()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KBBIDataApplication
                KBBIViewModel(application.container.kbbiWordRepository)
            }
        }
    }

    init {
        viewModelScope.launch {
            kbbiWordRepository.initializeDatabase()
            getNextWord()
        }
    }
}