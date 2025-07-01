package com.example.jettip.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val IconButtonSizeModifier = Modifier.size(40.dp)
@Composable
fun RoundIconBtn(
    imageVector: ImageVector,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = Color.Black.copy(0.8f),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    elevation: Dp = 4.dp
) {
    Card(
        shape = CircleShape,
        modifier = modifier
//            .clickable { onClickAction.invoke() }
            .padding(1.dp)
            .then(IconButtonSizeModifier)
            .background(backgroundColor),
        elevation = CardDefaults.cardElevation(elevation)) {
        Icon(modifier = Modifier.padding(start = 8.dp, top = 8.dp).clickable { onClickAction.invoke() },
            imageVector = imageVector,
            contentDescription = "Plus Or minus icon",
            tint = tint
        )
    }
}