package es.rlujancreations.minesweeper.data

/**
 * Created by Raúl L.C. on 3/1/24.
 */
data class Cell(val x: Int, val y: Int, var cellStatus: CellStatus)
sealed class CellStatus {
    object Unmarked : CellStatus()
    object Marked : CellStatus()
    object Mine : CellStatus()
}