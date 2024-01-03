package es.rlujancreations.minesweeper.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import es.rlujancreations.minesweeper.R
import es.rlujancreations.minesweeper.ui.theme.DarkBlue
import es.rlujancreations.minesweeper.ui.theme.LightBlue


/**
 * Created by Raúl L.C. on 3/1/24.
 */

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (Int) -> Unit
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
            contentDescription = "speaker.name",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(220.dp)
        )
        Spacer(modifier = Modifier.weight(2f))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F3452)),
            onClick = { navigateToGame(0) },
        ) {
            Text("Jugar", fontWeight = FontWeight.Bold, color = Color.White)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}