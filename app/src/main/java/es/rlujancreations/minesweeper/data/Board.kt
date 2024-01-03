package es.rlujancreations.minesweeper.data

/**
 * Created by Raúl L.C. on 3/1/24.
 */
data class Board(
    private val matrix: Array<IntArray>,
    private val columns: Int,
    private val rows: Int,
    private val mines: Int,
    private val remainingMines: Int
) {

    fun getCell(x: Int, y: Int):Int {
        return matrix[x][y]
    }


}
