package es.rlujancreations.minesweeper.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.ui.composables.OutlinedCustomButton
import es.rlujancreations.minesweeper.ui.theme.DarkBlue
import es.rlujancreations.minesweeper.ui.theme.LightBlue
import es.rlujancreations.minesweeper.ui.theme.RecordsBoard
import minesweeper.composeapp.generated.resources.Res
import minesweeper.composeapp.generated.resources.best_times
import minesweeper.composeapp.generated.resources.btn_play_easy
import minesweeper.composeapp.generated.resources.btn_play_hard
import minesweeper.composeapp.generated.resources.btn_play_medium
import minesweeper.composeapp.generated.resources.help
import minesweeper.composeapp.generated.resources.ic_help
import minesweeper.composeapp.generated.resources.ic_mineseeper
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

/**
 * Created by Raúl L.C. on 3/1/24.
 */

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    navigateToGame: (Level) -> Unit,
    navigateToHelp: () -> Unit,
) {
    val records by homeViewModel.recordsState.collectAsState()

    LaunchedEffect(true) {
        homeViewModel.loadRecords()
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = listOf(DarkBlue, LightBlue))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text("MinesWeeper", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 36.sp)
        Image(
            painter = painterResource(Res.drawable.ic_mineseeper),
            contentDescription = "App Logo",
            contentScale = ContentScale.Fit,
            modifier =
                Modifier
                    .padding(top = 16.dp)
                    .size(220.dp),
        )
        Spacer(modifier = Modifier.weight(2f))
        OutlinedCard(
            colors = CardDefaults.cardColors(containerColor = RecordsBoard),
            border = BorderStroke(1.dp, Color.Black),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            RecordText(
                text = stringResource(Res.string.best_times),
                color = Color.Black,
                modifier = Modifier.padding(8.dp),
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                RecordText(text = records.easy, color = Color(0xFF3F51B5))
                RecordText(text = records.medium, color = Color(0xFF0F3452))
                RecordText(text = records.hard, color = Color(0xFFF44336))
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))

        OutlinedCustomButton(
            text = stringResource(Res.string.btn_play_easy),
            color = Color(0xFF3F51B5),
            onClick = { navigateToGame(Level.Easy) },
        )
        OutlinedCustomButton(
            text = stringResource(Res.string.btn_play_medium),
            color = Color(0xFF0F3452),
            onClick = { navigateToGame(Level.Medium) },
        )
        OutlinedCustomButton(
            text = stringResource(Res.string.btn_play_hard),
            color = Color(0xFFF44336),
            onClick = { navigateToGame(Level.Hard) },
        )

        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(containerColor = DarkBlue, onClick = { navigateToHelp() }) {
            Icon(
                modifier = Modifier.size(35.dp),
                painter = painterResource(Res.drawable.ic_help),
                contentDescription = stringResource(Res.string.help),
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Composable
private fun RecordText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )
}
