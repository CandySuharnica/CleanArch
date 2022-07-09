package dev.rodkin.syharnicacleanarch.composeUI.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.rodkin.domain.useCases.OnBasketMode
import dev.rodkin.syharnicacleanarch.composeUI.common.SearchBar
import dev.rodkin.syharnicacleanarch.composeUI.common.SortBar
import dev.rodkin.syharnicacleanarch.composeUI.items.CatalogItem
import dev.rodkin.syharnicacleanarch.composeUI.navigation.NavGraph
import dev.rodkin.syharnicacleanarch.viewModels.BasketViewModel
import dev.rodkin.syharnicacleanarch.viewModels.CatalogViewModel


@Composable
fun CatalogScreen(
    catalogViewModel: CatalogViewModel,
    basketViewModel: BasketViewModel,
    navController: NavController
) {

    val sortTypes = catalogViewModel.catalogTypes.collectAsState().value
    val catalogItems = catalogViewModel.catalogList.collectAsState().value
    val basketItems = basketViewModel.basketList.collectAsState().value
    val flowSearch = catalogViewModel.flowSearch.collectAsState()
    val flowType = catalogViewModel.flowType.collectAsState()
    val flowSearchMode = catalogViewModel.flowSearchMode.collectAsState()
    /*val listOfLikes =
        viewModel.listOfLikes.collectAsState(initial = listOf()).value.firstNotNullOfOrNull { it }?.likes
            ?: emptyList()*/
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(text = flowSearch.value) {
            catalogViewModel.flowSearch.value = it
            catalogViewModel.sortCatalogList()
        }
        SortBar(
            content = sortTypes,
            sortMode = flowType.value ?: "",
        ) {
            catalogViewModel.flowType.value = it
            catalogViewModel.sortCatalogList()
        }
        Box() {
            LazyVerticalGrid(
                modifier = Modifier.padding(bottom = 59.dp),
                columns = GridCells.Fixed(2)
            ) {
                items(
                    count = catalogItems.size,
                    itemContent = { index ->
                        CatalogItem(
                            item = catalogItems[index],
                            count = basketItems
                                .find { it.id == catalogItems[index].id }
                                .let { item ->
                                    item?.count ?: 0
                                },
                            liked = false/*listOfLikes.contains(it.id)*/,
                            onClickAddItem = { catalogItem ->
                                basketViewModel.updateBasket(item = catalogItem, OnBasketMode.ADD)
                            },
                            onClickItem = {
                                navController.navigate(
                                    route = "${NavGraph.DetailScreen.route}/itemId=${catalogItems[index].id}"
                                )
                            },
                            onClickLike = {
                                //viewModel.like(it.id)
                            }
                        )
                    }
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .align(Alignment.CenterStart),
                color = Color.White
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .align(Alignment.CenterEnd),
                color = Color.White
            )
        }
    }
}