package es.rlujancreations.minesweeper.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import es.rlujancreations.minesweeper.R
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.ui.composables.OutlinedCustomButton
import es.rlujancreations.minesweeper.ui.theme.DarkBlue
import es.rlujancreations.minesweeper.ui.theme.LightBlue


/**
 * Created by Raúl L.C. on 3/1/24.
 */

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (Level) -> Unit,
    navigateToHelp: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = listOf(DarkBlue, LightBlue))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text("MinesWeeper", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 36.sp)
        Image(
            painter = painterResource(R.drawable.ic_mineseeper),
            contentDescription = "App Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(220.dp)
        )
        Spacer(modifier = Modifier.weight(2f))

        OutlinedCustomButton(
            text = R.string.btn_play_easy,
            color = Color(0xFF3F51B5),
            onClick = { navigateToGame(Level.Easy) })
        OutlinedCustomButton(
            text = R.string.btn_play_medium,
            color = Color(0xFF0F3452),
            onClick = { navigateToGame(Level.Medium) })
        OutlinedCustomButton(
            text = R.string.btn_play_hard,
            color = Color(0xFFF44336),
            onClick = { navigateToGame(Level.Hard) })

        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(containerColor = DarkBlue,onClick = { navigateToHelp() }) {
            Icon(
                modifier = Modifier.size(35.dp),
                painter = painterResource(id = R.drawable.ic_help),
                contentDescription = stringResource(id = R.string.help),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}