package org.kmp.newsapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.newsapp.theme.NewsAppTheme
import org.kmp.newsapp.theme.detailiImageSize
import org.kmp.newsapp.util.Theme

@Composable
fun TransparentBlackLinearGradientBox() {
    Box(
        modifier = Modifier.height(detailiImageSize).fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,             // End color (fully transparent)
                        Color.Black.copy(alpha = 0.9f) // Start color (more opaque black)
                    )
                )
            )
    ) {

    }
}

@Preview
@Composable
fun transparentBlackLinearGradientBox() {
    NewsAppTheme(Theme.SYSTEM_DEFAULT.name, true) {
        TransparentBlackLinearGradientBox()
    }
}