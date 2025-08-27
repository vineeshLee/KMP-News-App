package org.kmp.newsapp.ui.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun BookMarkScreen() {
    Box() {
        Text(
            text = "BookMark",
            fontSize = 32.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}