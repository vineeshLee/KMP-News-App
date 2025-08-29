package org.kmp.newsapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.navigation.NewsRouteScreen
import org.kmp.newsapp.theme.mediumPadding
import org.kmp.newsapp.theme.xLargePadding
import org.kmp.newsapp.util.Type
import org.kmp.newsapp.util.articles
import org.kmp.newsapp.util.getRandomId
import org.kmp.newsapp.util.getType


@Composable
fun ArticleListScreen(
    articleList: List<Article>,
    navController: NavController
) {
    val isDeskTop = remember {
        getType() == Type.DESKTOP
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isDeskTop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
    ) {
        items(articles, key = {
            it.publishedAt + getRandomId()
        }) {
            ArticleItem(it, onClick = {
                navController.navigate(NewsRouteScreen.NewsDetails.route)
            })
        }
    }

}