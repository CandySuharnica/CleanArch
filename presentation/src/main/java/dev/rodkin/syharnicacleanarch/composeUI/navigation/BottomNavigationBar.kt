package dev.rodkin.syharnicacleanarch.composeUI.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.rodkin.syharnicacleanarch.composeUI.theme.Icons


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavGraph.Catalog,
        NavGraph.Basket,
        NavGraph.Profile
    )
    //val basketItems = viewModel.getBasket.collectAsState(initial = listOf()).value
   // val totalCount = basketItems.sumOf { it.count }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                selected = currentRoute == item.route,
                icon = {
                    if (item.icon == Icons.Basket)
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(id = item.icon.image),
                                contentDescription = stringResource(id = item.icon.description),
                            )
                            /*Text(
                                modifier = Modifier.padding(top = 2.dp),
                                text =
                                if (totalCount > 0) "$totalCount"
                                else "",
                                color = Colors.RedButton.color,
                                fontWeight = FontWeight.SemiBold
                            )*/
                        }
                    else
                        Icon(
                            painter = painterResource(id = item.icon!!.image),
                            contentDescription = stringResource(id = item.icon.description),
                        )
                },
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
    Divider(
        color = Color.Black,
        thickness = 2.dp
    )
}

