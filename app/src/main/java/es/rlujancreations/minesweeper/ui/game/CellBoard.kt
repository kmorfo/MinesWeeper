package es.rlujancreations.minesweeper.ui.game


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.rlujancreations.minesweeper.R
import es.rlujancreations.minesweeper.data.Cell
import es.rlujancreations.minesweeper.data.CellStatus
import es.rlujancreations.minesweeper.ui.theme.BoardBackground

/**
 * Created by Raúl L.C. on 3/1/24.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CellBoard(
    cell: Cell,
    modifier: Modifier = Modifier,
    onClick: (Cell) -> Unit,
    onLongClick: (Cell) -> Unit
) {
    val cellIcon: CellIcon = when (cell.mines) {
        -1 -> CellIcon.Mine
        0 -> CellIcon.Empty
        1 -> CellIcon.One
        2 -> CellIcon.Two
        3 -> CellIcon.Three
        4 -> CellIcon.Four
        else -> CellIcon.Empty
    }

    if (cell.status == CellStatus.Untouched) {
        Row(
            modifier = modifier
                .background(BoardBackground)
                .border(1.dp, Color.Black)
                .combinedClickable(
                    onClick = { onClick(cell) },
                    onLongClick = { onLongClick(cell) }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = modifier
                    .background(BoardBackground)
                    .padding(3.dp)
                    .border(1.dp, Color.Gray)
            ) {}
        }
    }
    if (cell.status == CellStatus.Marked) {
        Row(
            modifier = modifier
                .background(BoardBackground)
                .border(1.dp, Color.Black)
                .combinedClickable(
                    onClick = { },
                    onLongClick = { onLongClick(cell) }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = modifier
                    .background(BoardBackground)
                    .padding(3.dp)
                    .border(1.dp, Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = cellIcon.icon!!),
                    contentDescription = stringResource(id = cellIcon.description!!),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
    if (cell.status == CellStatus.Discovered) {
        if (cellIcon == CellIcon.Empty) {
            Box(
                modifier = modifier
                    .background(BoardBackground)
                    .border(1.dp, Color.Gray)//Maybe I quit this border later
            ) { }
        } else {
            Row(
                modifier = modifier
                    .background(BoardBackground)
                    .border(1.dp, Color.Black),
//                    .combinedClickable(
//                        onClick = { onClick(cell) },
//                        onLongClick = { onLongClick(cell) }
//                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = modifier
                        .background(BoardBackground)
                        .padding(3.dp)
                        .border(1.dp, Color.Gray)
                ) {
                    if (cellIcon.icon != null && cellIcon.description != null)
                        Image(
                            painter = painterResource(id = cellIcon.icon),
                            contentDescription = stringResource(id = cellIcon.description),
                            modifier = Modifier.align(Alignment.Center)
                        )
                }
            }
        }
    }
}

sealed class CellIcon(
    @DrawableRes val icon: Int? = null,
    @StringRes val description: Int? = null
) {
    object Empty : CellIcon()
    object Mine : CellIcon(icon = R.drawable.mine, description = R.string.mine)
    object One : CellIcon(icon = R.drawable.digit_one, description = R.string.onemine)
    object Two : CellIcon(icon = R.drawable.digit_two, description = R.string.twomines)
    object Three : CellIcon(icon = R.drawable.digit_three, description = R.string.threemines)
    object Four : CellIcon(icon = R.drawable.digit_four, description = R.string.fourmines)
    object Marked : CellIcon(icon = R.drawable.flag, description = R.string.flag)

}