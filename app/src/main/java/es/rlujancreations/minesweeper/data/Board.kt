package es.rlujancreations.minesweeper.data

import javax.inject.Inject
import kotlin.random.Random

/**
 * Created by Raúl L.C. on 3/1/24.
 */
class Board @Inject constructor(
    private var level: Level,
    var remainingMines: Int = level.mines
) {
    private var matrix: Array<IntArray> = Array(level.rows) { IntArray(level.columns) { 0 } }

    fun initialize(newLevel:Level) {
        level=newLevel
        remainingMines = level.mines
        fillBoard()
        printBoard()
    }

    fun fillBoard() {
        //Add mines to board
        for (i in 0..<level.mines) {
            var rX: Int = Random.nextInt(level.rows)
            var rY: Int = Random.nextInt(level.columns)

            //Check if the matrix has already mine
            while (matrix[rX][rY] == -1) {
                rX = Random.nextInt(level.rows)
                rY = Random.nextInt(level.columns)
            }
            //Add mine to the current cell
            matrix[rX][rY] = -1

            //add number of mines in the nearby cells
            for (tempX in rX - 1..rX + 1)
                for (tempY in rY - 1..rY + 1) {
                    if (tempX < 0 || tempX >= level.rows || tempY < 0 || tempY >= level.columns) continue
                    if (matrix[tempX][tempY] != -1) matrix[tempX][tempY]++
                }
        }
    }

    private fun printBoard() {
        for (row in matrix) {
            for (element in row) print("$element ")
            println()
        }
    }

    fun getCell(x: Int, y: Int): Int {
        return matrix[x][y]
    }
}

sealed class Level(val rows: Int, val columns: Int, val mines: Int) {
    object Easy : Level(rows = 8, columns = 8, mines = 10)
    object Medium : Level(rows = 12, columns = 12, mines = 30)
    object Hard : Level(rows = 16, columns = 16, mines = 60)
}
