package es.rlujancreations.minesweeper.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
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
fun CounterBoard(number: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(BoardBackground)
            .height(25.dp)
            .width(52.dp)
            .border(1.dp, Color.Gray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier
                .background(CounterBackground)
                .height(24.dp)
                .width(50.dp)
                .border(1.dp, CounterShadowFont)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 2.dp),
                text = "888",
                fontFamily = Orbitron,
                color = CounterShadowFont,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp, textAlign = TextAlign.Left
            )
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 2.dp),
                text = number,
                fontFamily = Orbitron,
                color = CounterFontColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp, textAlign = TextAlign.Left
            )
        }


    }
}