package dev.rodkin.syharnicacleanarch.composeUI.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SortBar(
    content: List<String>,
    sortMode: String,
    onClickSort: (String) -> Unit
) {
    Column {
        LazyRow(content = {
            item {
                Card(
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Row(modifier = Modifier.height(38.dp)) {
                        content.forEach {
                            TextButton(
                                modifier = Modifier
                                    .background(
                                        if (sortMode == it) MaterialTheme.colorScheme.primary
                                        else Color.White
                                    ),
                                onClick = { onClickSort(it) }) {
                                Text(
                                    text = it,
                                    color = if (sortMode == it) Color.Black else Color.Gray
                                )
                            }

                            Divider(
                                modifier = Modifier
                                    .height(38.dp)
                                    .width(2.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        })
        Divider(
            color = Color.Black,
            thickness = 2.dp
        )
    }

}