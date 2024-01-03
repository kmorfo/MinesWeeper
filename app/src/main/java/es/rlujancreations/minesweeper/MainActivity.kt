package es.rlujancreations.minesweeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import es.rlujancreations.minesweeper.ui.core.ContentWrapper
import es.rlujancreations.minesweeper.ui.theme.MinesWeeperTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navigationController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MinesWeeperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navigationController = rememberNavController()
                    ContentWrapper(navigationController = navigationController)
                }
            }
        }
    }
}
