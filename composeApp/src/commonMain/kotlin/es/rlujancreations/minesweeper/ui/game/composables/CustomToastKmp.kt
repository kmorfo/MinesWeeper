package es.rlujancreations.minesweeper.ui.game.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Raúl L.C. on 30/6/24.
 */
@Composable
fun CustomToastKmp(message: String, duration: Long = 2000L) {
    var visible by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(message) {
        scope.launch {
            delay(duration)
            visible = false
        }
    }

    if (visible) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
//                backgroundColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}