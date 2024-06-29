package es.rlujancreations.minesweeper

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import es.rlujancreations.minesweeper.ui.theme.MinesWeeperTheme
import es.rlujancreations.minesweeper.ui.theme.Orbitron
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MinesWeeperTheme {
        Text("Hola serdo", fontFamily = Orbitron())
    }
}