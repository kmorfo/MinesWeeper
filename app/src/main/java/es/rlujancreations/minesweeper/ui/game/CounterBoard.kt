package es.rlujancreations.minesweeper.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.minesweeper.ui.theme.BoardBackground
import es.rlujancreations.minesweeper.ui.theme.CounterBackground
import es.rlujancreations.minesweeper.ui.theme.CounterFontColor
import es.rlujancreations.minesweeper.ui.theme.CounterShadowFont
import es.rlujancreations.minesweeper.ui.theme.Orbitron

/**
 * Created by Raúl L.C. on 6/1/24.
 */
@Composable
fun CounterBoard(number: Int = 10, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(BoardBackground)
            .height(40.dp)
            .width(72.dp)
            .border(1.dp, Color.Gray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier
                .background(CounterBackground)
                .height(38.dp)
                .width(70.dp)
                .border(1.dp, CounterShadowFont)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 6.dp).align(Alignment.Center),
                text = "888",
                fontFamily = Orbitron,
                color = CounterShadowFont,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp, textAlign = TextAlign.Right
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 6.dp).align(Alignment.Center),
                text = "$number",
                fontFamily = Orbitron,
                color = CounterFontColor,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp, textAlign = TextAlign.Right
            )
        }


    }
}