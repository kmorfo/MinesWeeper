package es.rlujancreations.minesweeper.domain.model

/**
 * Created by Raúl L.C. on 3/1/24.
 */
data class Cell(val x: Int, val y: Int, var mines: Int, var status: CellStatus)
sealed class CellStatus {
    object Untouched : CellStatus()
    object Marked : CellStatus()
    object Discovered: CellStatus()

}