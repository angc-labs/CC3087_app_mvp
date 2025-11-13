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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_mvp_angc.RoutingNames
import com.example.app_mvp_angc.components.AssetCard
import com.example.app_mvp_angc.network.ApiService
import com.example.app_mvp_angc.network.AppClient
import com.example.app_mvp_angc.ui.theme.App_mvpTheme
import com.example.app_mvp_angc.view_models.ListadoUiState
import io.ktor.client.HttpClient
import com.example.app_mvp_angc.view_models.ListadoViewModel


// pantalla de busqueda y detalles de busqueda
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListadoScreen (
    viewModel: ListadoViewModel,
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
                    Text(
                        text = "Assets",
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 60.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    /*TODO: GUARDAR DATOS EN BASE DE DATOS DE ROOM*/
                }
            ) {
                Text(
                    text = "Ver Offline"
                )
            }

            when (uiState) {
                is ListadoUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is ListadoUiState.Success -> {
                    val assets = (uiState as ListadoUiState.Success).assets

                    Text(
                        text = "Viendo la información más reciente",
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(assets) { asset ->
                            AssetCard(
                                modifier = Modifier.fillMaxWidth(),
                                assetName = asset.name,
                                symbol = asset.symbol,
                                price = asset.priceUsd.toDoubleOrNull(),
                                percentageChange = asset.changePercent24Hr.toDoubleOrNull(),
                                onClick = {
                                    navController.navigate(RoutingNames.PerfilsScreen(asset.id))
                                }
                            )
                        }
                    }
                }
                is ListadoUiState.Error -> {
                    val errorMessage = (uiState as ListadoUiState.Error).message

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Error al cargar datos",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = errorMessage,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Button(onClick = { viewModel.refresh() }) {
                                Text("Reintentar")
                            }
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
fun ListadoScreenPreview() {
    App_mvpTheme(darkTheme = false) {
        ListadoScreen(
            viewModel = ListadoViewModel(
                ApiService(
                    AppClient.httpClient
                )
            ),
            navController = rememberNavController()
        )
    }
}

// preview dark theme
@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun ListadoScreenDarkPreview() {
    App_mvpTheme(darkTheme = true) {
        ListadoScreen(
            viewModel = ListadoViewModel(
                ApiService(
                    AppClient.httpClient
                )
            ),
            navController = rememberNavController()
        )
    }
}