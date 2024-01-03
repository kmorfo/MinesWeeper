package es.rlujancreations.minesweeper.data

import kotlin.random.Random

/**
 * Created by Raúl L.C. on 3/1/24.
 */
class Board(
    private val columns: Int,
    private val rows: Int,
    private val mines: Int,
) {
    private var remainingMines: Int = mines
    private var matrix: Array<IntArray> = Array(rows) { IntArray(columns) { 0 } }

    fun getCell(x: Int, y: Int): Int {
        return matrix[x][y]
    }

    fun fillBoard() {
        var rX: Int
        var rY: Int

        //Add mines to board
        for (i in 0..<mines) {
            rX = Random.nextInt(rows)
            rY = Random.nextInt(columns)

            //Check if the matrix has already mine
            while (true) {
                if (matrix[rX][rY] == -1) {
                    rX = Random.nextInt(rows)
                    rY = Random.nextInt(columns)
                } else {
                    matrix[rX][rY] = -1
                    break
                }
            }
            //add number of mines in the nearby cells
            for (tempX in rX - 1..rX + 1) for (tempY in rY - 1..rY + 1) {
                if (tempX < 0 || tempX >= rows || tempY < 0 || tempY >= columns) continue
                if (matrix[tempX][tempY] !== -1) {
                    matrix[tempX][tempY]++
                }
            }
        }

    }


}
