package com.example.app_mvp_angc.network

import android.util.Log
import com.example.app_mvp_angc.network.dto.AssetResponse
import com.example.app_mvp_angc.network.dto.AssetsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders

class ApiService(
    private val client: HttpClient
) {
    private val baseUrl = "https://rest.coincap.io/v3"

    suspend fun getAssets(): AssetsResponse {
        return client.get("$baseUrl/assets"){
            header(HttpHeaders.Accept, "application/json")
            header(HttpHeaders.ContentType, "application/json")
            header(HttpHeaders.Authorization, "Bearer 6f8c2f757cc81e9950a05aeed8292abff853114ebc731977f3f5a580b1e9371a")
        }.body()
    }

    suspend fun getAssetById(id: String): AssetResponse {
        Log.d("ApiService", "$baseUrl/assets/$id")
        return client.get("$baseUrl/assets/$id").body()
    }
}