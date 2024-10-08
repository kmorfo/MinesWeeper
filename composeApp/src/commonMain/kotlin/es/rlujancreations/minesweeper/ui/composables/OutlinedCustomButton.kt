package es.rlujancreations.minesweeper.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Created by Raúl L.C. on 11/1/24.
 */
@Composable
fun OutlinedCustomButton(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        onClick = { onClick() },
    ) {
        Text(
            text,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}
