package dev.rodkin.syharnicacleanarch.composeUI.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.rodkin.syharnicacleanarch.composeUI.screens.*
import dev.rodkin.syharnicacleanarch.composeUI.theme.Icons
import dev.rodkin.syharnicacleanarch.viewModels.*

@Composable
fun NavigationGraph() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val bottomBarViewModel = hiltViewModel<BottomBarViewModel>()
            BottomNavigationBar(navController,bottomBarViewModel)
        }
    ) { padding ->
        NavHost(navController, startDestination = NavGraph.Catalog.route) {

            composable(NavGraph.Catalog.route) {
                val catalogViewModel = hiltViewModel<CatalogViewModel>()
                CatalogScreen(
                    catalogViewModel = catalogViewModel,
                    bottomPaddingValues = padding,
                    navController = navController
                )
            }
            composable(NavGraph.Basket.route) {
                val basketViewModel = hiltViewModel<BasketViewModel>()
                BasketScreen(basketViewModel = basketViewModel, navController)
            }
            /*composable(NavGraph.AdminScreen.route) {
                AdminScreen(viewModel)
            }
            composable(NavGraph.MakingAnOrderScreen.route) {
                if (user?.name != null) {
                    MakingAnOrderScreen(viewModel)
                } else LogInAndSignUpScreen(viewModel)
            }*/
            composable(NavGraph.Profile.route) {
                val logInAndSignUpViewModel = hiltViewModel<LogInAndSignUpViewModel>()
                LogInAndSignUpScreen(viewModel = logInAndSignUpViewModel)
            }
            composable(
                "${NavGraph.DetailScreen.route}/itemId={itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.LongType })
            ) { backStackEntry ->
                val detailViewModel = hiltViewModel<DetailCatalogViewModel>()
                val catalogViewModel = hiltViewModel<CatalogViewModel>()
                DetailScreen(
                    detailViewModel = detailViewModel,
                    navController,
                    backStackEntry.arguments?.getLong("itemId") ?: -1
                )
            }
        }
    }
}

sealed class NavGraph(
    val icon: Icons?,
    val route: String
) {

    object Catalog : NavGraph(
        Icons.Catalog,
        "catalog"
    )

    object Basket : NavGraph(
        Icons.Basket,
        "basket"
    )

    object Profile : NavGraph(
        Icons.Profile,
        "profile"
    )

    object DetailScreen : NavGraph(
        null,
        "detail"
    )

    object LogInAndSignUpScreen : NavGraph(
        null,
        "loginAndSignUp"
    )

    object AdminScreen : NavGraph(
        null,
        "AdminScreen"
    )

    object MakingAnOrderScreen : NavGraph(
        null,
        "MakingAnOrderScreen"
    )

}