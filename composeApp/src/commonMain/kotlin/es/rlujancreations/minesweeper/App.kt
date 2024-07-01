package es.rlujancreations.minesweeper

import androidx.compose.runtime.Composable
import es.rlujancreations.minesweeper.ui.core.Navigation
import es.rlujancreations.minesweeper.ui.theme.MinesWeeperTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        MinesWeeperTheme { Navigation() }
    }
}