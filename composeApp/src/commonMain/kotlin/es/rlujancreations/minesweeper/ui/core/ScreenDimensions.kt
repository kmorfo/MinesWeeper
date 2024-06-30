package es.rlujancreations.minesweeper.ui.core

interface ScreenDimensions {
    val screenWidthDp: Int
    val screenHeightDp: Int
}

expect fun getScreenDimensions(): ScreenDimensions