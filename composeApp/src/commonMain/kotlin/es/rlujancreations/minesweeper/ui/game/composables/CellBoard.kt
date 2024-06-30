package es.rlujancreations.minesweeper.ui.game.composables

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
import androidx.compose.ui.unit.dp
import es.rlujancreations.minesweeper.domain.model.Cell
import es.rlujancreations.minesweeper.domain.model.CellStatus
import es.rlujancreations.minesweeper.ui.theme.BoardBackground
import minesweeper.composeapp.generated.resources.Res
import minesweeper.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
                    painter = painterResource(CellIcon.Marked.icon!!),
                    contentDescription = stringResource( CellIcon.Marked.description!!),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
    if (cell.status == CellStatus.Discovered) {
        if (cellIcon == CellIcon.Empty) {
            Box(modifier = modifier.background(BoardBackground)) { }
        } else {
            Row(
                modifier = modifier
                    .background(BoardBackground)
                    .border(1.dp, Color.Black),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = modifier
                        .background(if (cellIcon==CellIcon.Mine) Color.Red else BoardBackground)
                        .padding(3.dp)
                        .border(1.dp, Color.Gray)
                ) {
                    if (cellIcon.icon != null && cellIcon.description != null)
                        Image(
                            painter = painterResource(cellIcon.icon),
                            contentDescription = stringResource(cellIcon.description),
                            modifier = Modifier.align(Alignment.Center)
                        )
                }
            }
        }
    }
}

sealed class CellIcon(
    val icon: DrawableResource? = null,
    val description: StringResource? = null
) {
    object Empty : CellIcon()
    object Mine : CellIcon(icon = Res.drawable.ic_mine, description = Res.string.mine)
    object One : CellIcon(icon = Res.drawable.digit_one, description = Res.string.onemine)
    object Two : CellIcon(icon = Res.drawable.digit_two, description = Res.string.twomines)
    object Three : CellIcon(icon = Res.drawable.digit_three, description = Res.string.threemines)
    object Four : CellIcon(icon = Res.drawable.digit_four, description = Res.string.fourmines)
    object Marked : CellIcon(icon = Res.drawable.flag, description = Res.string.flag)

}