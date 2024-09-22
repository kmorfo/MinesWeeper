package es.rlujancreations.minesweeper.data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Raúl L.C. on 15/1/24.
 */
class BoardTest {
    private lateinit var board: Board

    @Before
    fun setUp() {
        board = Board()
    }

    @Test
    fun `check if the number of mines matches with level`() {
        val level = Level.Hard
        board.initialize(level)

        assertEquals(level.mines, board.getMines())
    }

    @Test
    fun `check cell with valid coordinates returns cell value`() {
        val level = Level.Easy
        board.initialize(level)

        val cellValue = board.getCell(0, 0)

        assertEquals(true, cellValue >= -1 && cellValue <= 4)
    }

    @Test
    fun `check cell with invalid coordinates returns default value`() {
        val level = Level.Medium
        board.initialize(level)

        val cellValue = board.getCell(-1, 0)

        assertEquals(0, cellValue)
    }
}
