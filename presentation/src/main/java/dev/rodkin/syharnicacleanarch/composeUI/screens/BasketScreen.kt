package dev.rodkin.syharnicacleanarch.composeUI.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.rodkin.domain.useCases.OnBasketMode
import dev.rodkin.syharnicacleanarch.R
import dev.rodkin.syharnicacleanarch.composeUI.common.RedButton
import dev.rodkin.syharnicacleanarch.composeUI.items.BasketItem
import dev.rodkin.syharnicacleanarch.composeUI.theme.Icons
import dev.rodkin.syharnicacleanarch.viewModels.BasketViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BasketScreen(viewModel: BasketViewModel, navController: NavController) {
    val basketItems = viewModel.basketList
        .collectAsState(initial = listOf()).value
    val totalPrice = basketItems.sumOf { it.priceSale * it.count }
    val totalWeight = basketItems.sumOf { it.weight * it.count }
    Box(modifier = Modifier.fillMaxSize()) {
        Column() {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = {

                    }) {
                    Image(
                        painter = painterResource(id = Icons.ArrowBack.image),
                        contentDescription = stringResource(id = Icons.ArrowBack.description)
                    )
                }

                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.basket),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterEnd),
                    text = stringResource(id = R.string.choose),
                    fontSize = 18.sp,
                )

            }
            Divider(
                thickness = 2.dp,
                color = Color.Black
            )
            Box {
                LazyColumn(
                    modifier = Modifier
                        .padding(12.dp)
                ) {
                    items(
                        count = basketItems.size,
                        itemContent = { index ->
                            BasketItem(
                                item = basketItems[index],
                                onClickAdd = { basketItems ->
                                    viewModel.updateBasket(basketItems, OnBasketMode.ADD)
                                },
                                onClickRemove = { basketItems ->
                                    viewModel.updateBasket(basketItems, OnBasketMode.REMOVE)
                                }
                            )
                        }
                    )
                }
                Divider(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    thickness = 14.dp,
                    color = Color.White
                )
            }
        }
        RedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 70.dp)
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.place_an_order),
            price = totalPrice,
            weight = totalWeight,
            onClickButton = {
                /*if (basketItems.isEmpty())
                    CoroutineScope(Dispatchers.Default).launch {
                        viewModel.errorHandler.emit("basket is empty")
                    }
                else navController.navigate(NavGraph.MakingAnOrderScreen.route)*/
            }
        )
    }

}
