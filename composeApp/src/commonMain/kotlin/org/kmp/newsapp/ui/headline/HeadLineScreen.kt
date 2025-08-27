package org.kmp.newsapp.ui.headline

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.newsapp.theme.NewsAppTheme
import org.kmp.newsapp.ui.common.ArticleListScreen
import org.kmp.newsapp.util.articles

@Composable
fun HeadLineScreen() {
    ArticleListScreen(articles)
}