package es.rlujancreations.minesweeper

import androidx.compose.ui.window.ComposeUIViewController

@Suppress("ktlint")
fun MainViewController() =
    ComposeUIViewController(
        configure = { initKoin() },
    ) {
        App()
    }
