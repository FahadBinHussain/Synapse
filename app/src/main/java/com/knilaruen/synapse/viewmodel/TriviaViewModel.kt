package com.knilaruen.synapse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knilaruen.synapse.data.TriviaFact
import com.knilaruen.synapse.data.TriviaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TriviaViewModel : ViewModel() {
    
    private val _currentFact = MutableStateFlow<TriviaFact?>(null)
    val currentFact: StateFlow<TriviaFact?> = _currentFact.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadRandomFact()
    }
    
    fun loadRandomFact() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _currentFact.value = TriviaRepository.getRandomFact()
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun getNextFact() {
        loadRandomFact()
    }
}
