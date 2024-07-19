import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class MinesweeperTests {
    private Grid grid;

    @BeforeEach
    public void setUp() {
        grid = new Grid(4, 3);
    }

    @Test
    public void testPlaceMines() {
        grid.placeMines();
        int mineCount = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid.getCell(i, j).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(3, mineCount);
    }

    @Test
    public void testRevealCell() {
        grid.placeMines();
        boolean gameOver = grid.revealCell("A1");
        assertFalse(gameOver);
    }

    @Test
    public void testIsGameWon() {
        grid.placeMines();
        assertFalse(grid.isGameWon());
    }

    @Test
    public void testRevealAdjacentCells() {
        grid.placeMines();
        grid.revealCell("A1");
        assertTrue(grid.getCell(0, 0).isRevealed());
    }
}