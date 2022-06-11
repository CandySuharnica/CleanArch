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
import dev.rodkin.syharnicacleanarch.composeUI.common.SearchBar
import dev.rodkin.syharnicacleanarch.composeUI.items.CatalogItem
import dev.rodkin.syharnicacleanarch.presenters.CatalogViewModel


@Composable
fun CatalogScreen(viewModel: CatalogViewModel, navController: NavController) {
    /*val sortFlow = viewModel.sortFlow.collectAsState().value
    val initialList = listOf("Все", "Любимое")
    val sortTypes = viewModel.getTypes.collectAsState(initial = listOf()).value
    val finalListTypes = initialList + sortTypes
    val searchFlow = viewModel.searchFlow.collectAsState().value*/
    val catalogItems = viewModel.catalogList
        .collectAsState(initial = listOf()).value
    /*val listOfLikes =
        viewModel.listOfLikes.collectAsState(initial = listOf()).value.firstNotNullOfOrNull { it }?.likes
            ?: emptyList()*/
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(text = "searchFlow") {
            //viewModel.searchFlow.value = it
        }
        /*SortBar(
            content = finalListTypes,
            sortMode = sortFlow,
        ) {
            viewModel.sortFlow.value = it
        }*/
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
                            count = 0/*viewModel.getItemCountInBasket(it.id)
                                .collectAsState(initial = 0).value*/,
                            liked = false/*listOfLikes.contains(it.id)*/,
                            onClickAddItem = {
                                /*viewModel.addItemIntoBasket(
                                    it.id,
                                    OnBasketMode.ADD
                                )*/
                            },
                            onClickItem = {
                                //navController.navigate("${NavGraph.DetailScreen.route}/itemId=${it.id}")
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