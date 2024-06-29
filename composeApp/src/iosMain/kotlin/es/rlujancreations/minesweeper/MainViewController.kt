package es.rlujancreations.minesweeper

import App
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(
//    configure = { initKoin() }
) {
    App()
}