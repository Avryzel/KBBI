package com.example.kbbi.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kbbi.data.KBBIDataApplication
import com.example.kbbi.data.repository.KBBIWordRepository
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
                it.copy(currentWord = kbbiWordRepository.getRandomWord())
            }
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
        getNextWord()
    }
}