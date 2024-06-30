package es.rlujancreations.minesweeper.ui.game

import minesweeper.composeapp.generated.resources.Res
import minesweeper.composeapp.generated.resources.face_dizzy
import minesweeper.composeapp.generated.resources.face_partying
import minesweeper.composeapp.generated.resources.face_sleep
import minesweeper.composeapp.generated.resources.face_sleeping
import minesweeper.composeapp.generated.resources.face_smiling
import minesweeper.composeapp.generated.resources.ic_happy_mine
import minesweeper.composeapp.generated.resources.ic_sad_mine
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

/**
 * Created by Raúl L.C. on 30/6/24.
 */
sealed class GameStatus(
    val icon: DrawableResource,
    val image: DrawableResource,
    val description: StringResource
) {
    object Paused : GameStatus(
        icon = Res.drawable.face_sleep,
        image = Res.drawable.face_sleep,
        description = Res.string.face_sleeping
    )

    object Running : GameStatus(
        icon = Res.drawable.face_smiling,
        image = Res.drawable.face_smiling,
        description = Res.string.face_smiling
    )

    object Winned : GameStatus(
        icon = Res.drawable.face_partying,
        image = Res.drawable.ic_happy_mine,
        description = Res.string.face_partying
    )

    object Losed : GameStatus(
        icon = Res.drawable.face_dizzy,
        image = Res.drawable.ic_sad_mine,
        description = Res.string.face_dizzy
    )
}