package com.example.app_mvp_angc.view_models

import com.example.app_mvp_angc.network.dto.AssetDto

sealed class ListadoUiState {
    data object Loading : ListadoUiState()
    data class Success(val assets: List<AssetDto>) : ListadoUiState()
    data class Error(val message: String) : ListadoUiState()
}


sealed class PerfilUiState {
    data object Loading : PerfilUiState()
    data class Success(val asset: AssetDto) : PerfilUiState()
    data class Error(val message: String) : PerfilUiState()
}