package es.rlujancreations.minesweeper.ui.game


import android.graphics.Color
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import es.rlujancreations.minesweeper.R

/**
 * Created by Raúl L.C. on 3/1/24.
 */
@Preview(showBackground = true)
@Composable
fun Cell(modifier: Modifier = Modifier) {

}

sealed class CellIcon(
    @DrawableRes val icon: Int? = null
) {
    object Empty : CellIcon()
    object Mine : CellIcon(icon = R.drawable.mine, )
    object One : CellIcon(icon = R.drawable.digit_one)
    object Two : CellIcon(icon = R.drawable.digit_two)
    object Three : CellIcon(icon = R.drawable.digit_three)
    object Four : CellIcon(icon = R.drawable.digit_four)
    object Marked : CellIcon(icon = R.drawable.flag)

}