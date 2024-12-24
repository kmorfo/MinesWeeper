package es.rlujancreations.minesweeper.ui.core

import es.rlujancreations.minesweeper.WINDOW_HEIGHT
import es.rlujancreations.minesweeper.WINDOW_WIDTH

actual fun getScreenDimensions(): ScreenDimensions {
    return object : ScreenDimensions {
        override val screenWidthDp: Int
            get() = WINDOW_WIDTH
        override val screenHeightDp: Int
            get() = WINDOW_HEIGHT
    }
}
