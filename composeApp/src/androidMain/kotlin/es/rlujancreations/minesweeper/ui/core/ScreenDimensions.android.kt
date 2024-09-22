package es.rlujancreations.minesweeper.ui.core

import android.content.Context
import android.util.DisplayMetrics
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AndroidScreenDimensions(context: Context) : ScreenDimensions {
    private val displayMetrics: DisplayMetrics = context.resources.displayMetrics

    override val screenWidthDp: Int
        get() = (displayMetrics.widthPixels / displayMetrics.density).toInt()

    override val screenHeightDp: Int
        get() = (displayMetrics.heightPixels / displayMetrics.density).toInt()
}

object ScreenDimensionsProvider : KoinComponent {
    private val context: Context by inject()
    val screenDimensions = AndroidScreenDimensions(context)
}

actual fun getScreenDimensions(): ScreenDimensions {
    return ScreenDimensionsProvider.screenDimensions
}
