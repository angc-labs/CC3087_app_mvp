package com.example.app_mvp_angc.view_models

import androidx.lifecycle.ViewModel
import com.example.app_mvp_angc.network.ApiService

class ViewModelFactory(
    private val apiService: ApiService
) : androidx.lifecycle.ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ListadoViewModel::class.java) -> {
                ListadoViewModel(apiService) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

class PerfilViewModelFactory(
    private val apiService: ApiService,
    private val assetId: String
) : androidx.lifecycle.ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PerfilViewModel::class.java) -> {
                PerfilViewModel(apiService, assetId) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}