package es.rlujancreations.minesweeper.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Raúl L.C. on 7/1/24.
 */
@Composable
fun TextButtonDialog(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    TextButton(onClick = { onClick() }, modifier = modifier) {
        Text(
            text = text,
            modifier = modifier.padding(bottom = 8.dp, start = 24.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
    }

}