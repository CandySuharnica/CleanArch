package dev.rodkin.syharnicacleanarch.composeUI.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rodkin.syharnicacleanarch.composeUI.theme.Icons

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RedButton(
    modifier: Modifier = Modifier,
    onClickIconAdd: (() -> Unit)? = null,
    onClickIconRemove: (() -> Unit)? = null,
    onClickButton: (() -> Unit)? = null,
    text: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    count: Int? = null,
    textAlign: Alignment = Alignment.CenterStart,
    icon: Icons? = null,
    price: Double? = null,
    weight: Int? = null
) {
    if (onClickButton != null)
        Card(
            modifier = modifier,
            backgroundColor = backgroundColor,
            border = BorderStroke(2.dp, color = Color.Black),
            shape = RoundedCornerShape(8.dp),
            onClick = onClickButton
        ) {
            RedButtonInsideImpl(
                onClickIconAdd = onClickIconAdd,
                onClickIconRemove = onClickIconRemove,
                text = text,
                count = count,
                textAlign = textAlign,
                icon = icon,
                price = price,
                weight = weight
            )
        }
    else
        Card(
            modifier = modifier,
            backgroundColor = backgroundColor,
            border = BorderStroke(2.dp, color = Color.Black),
            shape = RoundedCornerShape(8.dp),
        ) {
            RedButtonInsideImpl(
                onClickIconAdd = onClickIconAdd,
                onClickIconRemove = onClickIconRemove,
                text = text,
                count = count,
                textAlign = textAlign,
                icon = icon,
                price = price,
                weight = weight
            )
        }
}

@Composable
fun RedButtonInsideImpl(
    onClickIconAdd: (() -> Unit)?,
    onClickIconRemove: (() -> Unit)?,
    text: String?,
    count: Int?,
    textAlign: Alignment,
    icon: Icons?,
    price: Double?,
    weight: Int?
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (text != null)
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .align(textAlign),
                text = text,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        Row(
            modifier = Modifier
                .padding(end = 25.dp)
                .align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
            ) {
                if (price != null)
                    Text(
                        text = "$price BYN",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                if (weight != null)
                    Text(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(bottom = 2.dp),
                        text = "$weight г"
                    )
            }
            if (icon != null)
                Icon(
                    painter = painterResource(id = icon.image),
                    contentDescription = stringResource(id = icon.description)
                )
            if (onClickIconRemove != null)
                IconButton(onClick = onClickIconRemove) {
                    Image(
                        painter = painterResource(id = Icons.Minus.image),
                        contentDescription = stringResource(id = Icons.Minus.description)
                    )
                }
            if (count != null)
                Text(
                    modifier = Modifier.widthIn(min = 25.dp),
                    textAlign = TextAlign.Center,
                    text = "$count",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            if (onClickIconAdd != null)
                IconButton(onClick = onClickIconAdd) {
                    Image(
                        painter = painterResource(id = Icons.BigPlus.image),
                        contentDescription = stringResource(id = Icons.BigPlus.description)
                    )
                }
        }
    }
}