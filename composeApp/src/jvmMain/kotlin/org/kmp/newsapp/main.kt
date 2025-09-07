package org.kmp.newsapp

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import coil3.size.Dimension
import org.kmp.newsapp.di.initKoin
import java.awt.Window

fun main() = application {
    initKoin ()
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-News-App",
        state = WindowState(
            position = WindowPosition(Alignment.Center)
        )
    ) {
        //window.minimumSize= java.awt.Dimension(1280,768)
        App()
    }
}