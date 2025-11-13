package com.example.app_mvp_angc.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_mvp_angc.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val apiService: ApiService,
    private val assetId: String
) : ViewModel() {

    private val _uiState = MutableStateFlow<PerfilUiState>(PerfilUiState.Loading)
    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    init {
        loadAsset()
    }

    /**
     * Carga los detalles de un asset específico
     */
    fun loadAsset() {
        viewModelScope.launch {
            _uiState.value = PerfilUiState.Loading
            try {
                val response = apiService.getAssetById(assetId)
                _uiState.value = PerfilUiState.Success(response.data)
            } catch (e: Exception) {
                _uiState.value = PerfilUiState.Error(
                    e.message ?: "Error desconocido al cargar el asset"
                )
            }
        }
    }

    fun refresh() {
        loadAsset()
    }
}