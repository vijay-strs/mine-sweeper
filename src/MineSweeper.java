import java.util.Scanner;

public class MineSweeper {
    private Grid grid;
    private Scanner scanner;

    public MineSweeper() {
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to Minesweeper!");

        int gridSize = getGridSize();
        int mineCount = getMineCount(gridSize);

        grid = new Grid(gridSize, mineCount);
        grid.placeMines();

        boolean gameOver = false, isFirst = true;
        while (!gameOver) {
            grid.displayGrid(isFirst);
            String userInput = getUserInput();
            gameOver = grid.revealCell(userInput);
            if (grid.isGameWon()) {
                System.out.println("Congratulations, you have won the game!");
                break;
            }
            if(!gameOver)
                System.out.printf("This square contains %d adjacent mines. ", grid.getAdjacentMineCount(userInput));
            isFirst = false;
        }

        if (gameOver) {
            System.out.println("Oh no, you detonated a mine! Game over.");
        }
    }

    private int getGridSize() {
        while (true) {
            try {
                System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
                int gridSize = Integer.parseInt(scanner.nextLine());
                if (gridSize >= 2 && gridSize <= 10) {
                    return gridSize;
                } else {
                    System.out.println("Grid size must be between 2 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input.");
            }
        }
    }

    private int getMineCount(int gridSize) {
        int maxMines = (int) Math.floor(gridSize * gridSize * 0.35);
        while (true) {
            try {
                System.out.printf("Enter the number of mines to place on the grid (maximum is %d(35%% of the total squares)): ", maxMines);
                int mineCount = Integer.parseInt(scanner.nextLine());
                if (mineCount >= 1 && mineCount <= maxMines) {
                    return mineCount;
                } else {
                    System.out.println("Number of mines must be between 1 and " + maxMines + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input.");
            }
        }
    }

    private String getUserInput() {
        System.out.println();
        while (true) {
            System.out.print("Select a square to reveal (e.g. A1): ");
            String userInput = scanner.nextLine().toUpperCase();
            if (userInput.matches("^[A-J][1-9][0]?$")) {
                return userInput;
            } else {
                System.out.println("Incorrect input.");
            }
        }
    }
}