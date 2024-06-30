package es.rlujancreations.minesweeper.ui.help


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.minesweeper.ui.theme.BoardBackground
import es.rlujancreations.minesweeper.ui.theme.DarkBlue
import es.rlujancreations.minesweeper.ui.theme.HeaderBackground
import minesweeper.composeapp.generated.resources.Res
import minesweeper.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * Created by Raúl L.C. on 11/1/24.
 */
@Composable
fun HelpScreen(modifier: Modifier = Modifier, navigateToHome: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                brush = Brush.verticalGradient(colors = listOf(HeaderBackground, BoardBackground))
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(containerColor = DarkBlue),
            shape = CircleShape,
            onClick = { navigateToHome() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = stringResource( Res.string.back_home),
                tint = Color.White
            )
        }
        Image(
            painter = painterResource(Res.drawable.ic_mineseeper),
            contentDescription = "App Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource( Res.string.help_text),
            textAlign = TextAlign.Justify,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource( Res.string.help_new),
            textAlign = TextAlign.Justify,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
        )
        Image(
            painter = painterResource(Res.drawable.ic_logorc),
            contentDescription = "RLujanCreations Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(320.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}