package com.example.app_mvp_angc.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_mvp_angc.network.ApiService
import com.example.app_mvp_angc.network.dto.AssetDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListadoViewModel(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow<ListadoUiState>(ListadoUiState.Loading)
    val uiState: StateFlow<ListadoUiState> = _uiState.asStateFlow()

    init {
        loadAssets()
    }

    /**
     * Carga la lista de assets desde la API
     */
    fun loadAssets() {
        viewModelScope.launch {
            _uiState.value = ListadoUiState.Loading
            try {
                val response = apiService.getAssets()
                _uiState.value = ListadoUiState.Success(response.data)
            } catch (e: Exception) {
                _uiState.value = ListadoUiState.Error(
                    e.message ?: "Error desconocido al cargar los assets"
                )
            }
        }
    }

    fun refresh() {
        loadAssets()
    }
}