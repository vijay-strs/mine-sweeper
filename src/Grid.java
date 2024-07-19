import java.util.Random;

public class Grid {

    private final int size;
    private final int mineCount;
    private final Cell[][] cells;
    private final Random random = new Random();

    public Grid(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!cells[row][col].isMine()) {
                cells[row][col].placeMine();
                minesPlaced++;
            }
        }
        updateAdjacentMinesCount();
    }

    private void updateAdjacentMinesCount() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!cells[row][col].isMine()) {
                    cells[row][col].setAdjacentMinesCount(countAdjacentMines(row, col));
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < size && j >= 0 && j < size && cells[i][j].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void displayGrid(boolean isFirst) {

        System.out.println();
        System.out.println("Here is your" + (isFirst ? " " : " updated ") + "minefield:");
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int getRowFromUserInput(String userInput){
        return userInput.charAt(0) - 'A';
    }

    private int getColFromUserInput(String userInput){
        return Integer.parseInt(userInput.substring(1)) - 1;
    }

    public int getAdjacentMineCount(String userInput){
        return cells[getRowFromUserInput(userInput)][getColFromUserInput(userInput)].getAdjacentMinesCount();
    }

    public Cell getCell(int row, int col){
        return cells[row][col];
    }

    public boolean revealCell(String userInput) {

        int row = getRowFromUserInput(userInput);
        int col = getColFromUserInput(userInput);

        if (cells[row][col].isMine()) {
            return true;
        }

        reveal(row, col);
        return false;
    }

    private void reveal(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size || cells[row][col].isRevealed()) {
            return;
        }

        cells[row][col].reveal();

        if (cells[row][col].getAdjacentMinesCount() == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    reveal(i, j);
                }
            }
        }
    }

    public boolean isGameWon() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!cells[i][j].isMine() && !cells[i][j].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }
}
