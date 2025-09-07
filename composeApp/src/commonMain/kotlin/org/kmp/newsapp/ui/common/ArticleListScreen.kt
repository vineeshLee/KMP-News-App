package org.kmp.newsapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.serialization.json.Json
import org.kmp.newsapp.data.model.Article
import org.kmp.newsapp.navigation.Route
import org.kmp.newsapp.theme.cardMinSize
import org.kmp.newsapp.theme.mediumPadding
import org.kmp.newsapp.util.getRandomId


@Composable
fun ArticleListScreen(
    articleList: List<Article>,
    navController: NavController
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(cardMinSize),
        verticalItemSpacing = mediumPadding,
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
    ) {
        items(articleList, key = {
            it.publishedAt + getRandomId()
        }) { item ->
            ArticleItem(article = item, onClick = {

                val articleStr = Json.encodeToString(item)
                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("article", articleStr)
                }
                navController.navigate(Route.NewsDetail)
            })
        }
    }

}