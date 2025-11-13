package com.example.app_mvp_angc.views

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_mvp_angc.network.AppClient
import com.example.app_mvp_angc.ui.theme.App_mvpTheme
import com.example.app_mvp_angc.view_models.PerfilUiState
import com.example.app_mvp_angc.view_models.PerfilViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PerfilScreen (
    viewModel: PerfilViewModel,
    currencyId: String?,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp)
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        // back button
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Back"
                        )
                    }
                    Text(
                        text = currencyId?.capitalize() ?: "Perfil",
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 60.dp)
        ) {
            when (uiState) {
                is PerfilUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is PerfilUiState.Success -> {
                    val asset = (uiState as PerfilUiState.Success).asset

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Información principal
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = asset.symbol,
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        "Market Cap $${String.format("%.0f", asset.marketCapUsd.toDoubleOrNull())}"
                                    )
                                    Text(
                                        "Supply $${String.format("%.0f", asset.supply.toDoubleOrNull() ?: 0.0)}"
                                    )

                                    asset.maxSupply?.let {
                                        Text("Max Supply: ${String.format("%.0f", it.toDoubleOrNull() ?: 0.0)}")
                                    }

                                    Text(
                                        "Viendo data más reciente"
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = { viewModel.refresh() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Actualizar")
                        }
                    }
                }

                is PerfilUiState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Error al cargar datos",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = (uiState as PerfilUiState.Error).message,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Button(onClick = { viewModel.refresh() }) {
                            Text("Reintentar")
                        }
                    }
                }
                else -> {}
            }
        }

    }
}

// preview ligth theme
@Composable
@Preview(showBackground = true)
fun PerfilScreenPreview() {
    App_mvpTheme(darkTheme = false) {
        PerfilScreen(
            viewModel = PerfilViewModel(
                AppClient.apiService,
                assetId = "bitcoin"
            ),
            currencyId = "bitcoin",
            navController = rememberNavController()
        )
    }
}

// preview dark theme
@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun PerfilScreenDarkPreview() {
    App_mvpTheme(darkTheme = true) {
        PerfilScreen(
            viewModel = PerfilViewModel(
                AppClient.apiService,
                assetId = "bitcoin"
            ),
            currencyId = "bitcoin",
            navController = rememberNavController()
        )
    }
}