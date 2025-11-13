package com.example.app_mvp_angc.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** GENERADO CON CLAUDE
 * DTO para representar un asset de criptomoneda de la API de CoinCap
 */
@Serializable
data class AssetDto(
    @SerialName("id")
    val id: String,

    @SerialName("rank")
    val rank: String,

    @SerialName("symbol")
    val symbol: String,

    @SerialName("name")
    val name: String,

    @SerialName("supply")
    val supply: String,

    @SerialName("maxSupply")
    val maxSupply: String? = null,

    @SerialName("marketCapUsd")
    val marketCapUsd: String,

    @SerialName("volumeUsd24Hr")
    val volumeUsd24Hr: String,

    @SerialName("priceUsd")
    val priceUsd: String,

    @SerialName("changePercent24Hr")
    val changePercent24Hr: String,

    @SerialName("vwap24Hr")
    val vwap24Hr: String,

    @SerialName("tokens")
    val tokens: Map<String, List<String>> = emptyMap()
)


/** GENERADO CON CLAUDE
 * Respuesta de la API que contiene la lista de assets
 */
@Serializable
data class AssetsResponse(
    @SerialName("data")
    val data: List<AssetDto>,

    @SerialName("timestamp")
    val timestamp: Long
)

@Serializable
data class AssetResponse(
    @SerialName("data")
    val data: AssetDto
)


