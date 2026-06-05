package com.example.duckapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duckapp.model.DuckPhoto
import com.example.duckapp.network.DuckApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import androidx.compose.runtime.*
import kotlinx.coroutines.supervisorScope

sealed interface DuckUIState {
    data class Success(val json: String) : DuckUIState
    object Loading : DuckUIState
    object Error : DuckUIState
}

class DuckViewModel : ViewModel() {
    var uiState by mutableStateOf<DuckUIState>(DuckUIState.Loading)
        private set

    init {
        fetchDucks()
    }

    fun fetchDucks() {
        viewModelScope.launch {
            uiState = DuckUIState.Loading

            try {
                val ducks = supervisorScope {
                    List(10) {
                        async {
                            DuckApi.retrofitService.getRandomDuck()
                        }
                    }.awaitAll()
                }

                val json = Json.encodeToString(ducks)
                uiState = DuckUIState.Success(json)

            } catch (e: Exception) {
                uiState = DuckUIState.Error
            }
        }
    }
}
