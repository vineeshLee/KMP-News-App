package org.kmp.newsapp.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.kmp.newsapp.theme.mediumPadding
import org.kmp.newsapp.theme.smallPadding
import org.kmp.newsapp.theme.xxSmallPadding
import org.kmp.newsapp.ui.common.ArticleListScreen
import org.kmp.newsapp.util.articles

@Composable
fun SearchScreen() {
    var searchQuery by rememberSaveable() {
        mutableStateOf("")
    }
    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding)) {
        SearchBar(
            text = searchQuery,
            onValueChange = { changeValue ->
                searchQuery = changeValue
            },
            onSearch = {}
        )

        Spacer(modifier = Modifier.height(xxSmallPadding))

        ArticleListScreen(articles)
    }

}