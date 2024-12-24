package es.rlujancreations.minesweeper

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import minesweeper.composeapp.generated.resources.Res
import minesweeper.composeapp.generated.resources.ic_mineseeper
import org.jetbrains.compose.resources.painterResource

/**
 * Created by Raúl L.C. on 24/12/24.
 */

const val WINDOW_WIDTH = 600
const val WINDOW_HEIGHT = 600

fun main() = application {
    val windowSize = rememberWindowState(
        width = WINDOW_WIDTH.dp,
        height = WINDOW_HEIGHT.dp,
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Classic Minesweeper",
        resizable = false,
        icon = painterResource(Res.drawable.ic_mineseeper),
        state = windowSize,
    ) {
        initKoin()
        App()
    }
}
