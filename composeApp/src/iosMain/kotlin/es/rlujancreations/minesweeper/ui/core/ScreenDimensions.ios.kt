package es.rlujancreations.minesweeper.ui.core


import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

class IOSScreenDimensions : ScreenDimensions {
    @OptIn(ExperimentalForeignApi::class)
    override val screenWidthDp: Int
        get() = (UIScreen.mainScreen.bounds.useContents { size.width } ).toInt()

    @OptIn(ExperimentalForeignApi::class)
    override val screenHeightDp: Int
        get() = (UIScreen.mainScreen.bounds.useContents { size.height } -65 ).toInt()
}

actual fun getScreenDimensions(): ScreenDimensions {
    return IOSScreenDimensions()
}
