package com.example.app_mvp_angc

import kotlinx.serialization.Serializable
import java.util.Currency

sealed class RoutingNames() {
    @Serializable
    data object ListadoScreen : RoutingNames()

    @Serializable
    data class PerfilsScreen  (
        val currencyId: String
    ) : RoutingNames()
}
