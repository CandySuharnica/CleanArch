package dev.rodkin.syharnicacleanarch.composeUI.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.syharnicacleanarch.composeUI.theme.Icons


@Composable
fun BasketItem(
    item: BasketItem,
    onClickAdd: () -> Unit,
    onClickRemove: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(90.dp)
                    .drawBehind {
                        drawLine(
                            color = Color.Black,
                            Offset(size.width, 0f),
                            Offset(size.width, size.height),
                            10f
                        )
                    },
                model = item.imgUrl[0],
                loading = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                },
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(start = 14.dp)
            ) {
                Column() {
                    Text(
                        text = item.label,
                        fontWeight = FontWeight.Black,
                        fontSize = 22.sp,
                        maxLines = 1
                    )
                    Text(
                        text = item.priceSale.toString().plus(" BYN"),
                        fontSize = 16.sp
                    )
                    Text(
                        text = item.weight.toString().plus(" г"),
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onClickRemove
                    ) {
                        Image(
                            /* modifier = Modifier.pointerInput(Unit) {
                                 detectTapGestures(onLongPress = {
                                     viewModel.addItemIntoBasket(item.id, OnBasketMode.REMOVE)
                                 })
                             },*/
                            painter = painterResource(id = Icons.Minus.image),
                            contentDescription = stringResource(id = Icons.Minus.description),
                        )
                    }
                    Text(
                        modifier = Modifier.widthIn(min = 25.dp),
                        textAlign = TextAlign.Center,
                        text = "${item.count}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    IconButton(onClick = onClickAdd) {
                        Image(
                            painter = painterResource(id = Icons.BigPlus.image),
                            contentDescription = stringResource(id = Icons.BigPlus.description)
                        )
                    }
                }
            }

        }
        Divider(thickness = 2.dp, color = Color.Black)
    }
}