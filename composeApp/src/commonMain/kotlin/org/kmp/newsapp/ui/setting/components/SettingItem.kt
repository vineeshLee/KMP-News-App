package org.kmp.newsapp.ui.setting.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.newsapp.theme.NewsAppTheme
import org.kmp.newsapp.theme.mediumPadding
import org.kmp.newsapp.theme.xLargePadding

@Composable
fun SettingItem(
    onclick: () -> Unit,
    painter: Painter,
    itemName: String,
    itemColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onclick.invoke()}
            .padding(mediumPadding),
        horizontalArrangement = spacedBy(
            mediumPadding,
            Start
        ),
        verticalAlignment = CenterVertically
    ) {
        Image(
            modifier = Modifier.size(xLargePadding),
            painter = painter,
            contentDescription = null,
            colorFilter = ColorFilter.tint(itemColor)
        )
        Text(
            text = itemName,
            color = itemColor,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal

        )
    }
}

@Preview
@Composable
fun SettingItemPreview() {
    /*NewsAppTheme(false) {
       *//* SettingItem(
            onclick = {},
            painter = Painter()
        )*//*
    }*/
}