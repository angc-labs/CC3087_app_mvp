package com.example.app_mvp_angc


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.app_mvp_angc.network.ApiService
import com.example.app_mvp_angc.network.AppClient
import com.example.app_mvp_angc.view_models.ListadoViewModel
import com.example.app_mvp_angc.view_models.PerfilViewModel
import com.example.app_mvp_angc.views.ListadoScreen
import com.example.app_mvp_angc.views.PerfilScreen
import io.ktor.client.HttpClient

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = RoutingNames.ListadoScreen,
        modifier = modifier
    ) {
        composable<RoutingNames.ListadoScreen> {
            ListadoScreen(
                viewModel = ListadoViewModel(
                    ApiService(
                        AppClient.httpClient
                    )
                ),
                navController = navController
            )
        }

        composable<RoutingNames.PerfilsScreen> { backStackEntry ->
            var result_metadata = backStackEntry.toRoute<RoutingNames.PerfilsScreen>()
            PerfilScreen(
                viewModel = PerfilViewModel(
                    AppClient.apiService,
                    result_metadata.currencyId
                ),
                currencyId = result_metadata.currencyId,
                navController = navController
            )
        }
    }
}