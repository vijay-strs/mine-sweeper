# MineSweeper



## Instructions to Run the Application

### Environment:
Java 11
IntelliJ IDEA
JUnit 5 for testing

### Running the Application:
Clone the repository.
Open the project in IntelliJ IDEA.
Run the main class GameMain.
Follow the command line prompts to play the game

### Running Tests:
Open the project in IntelliJ IDEA.
Run the test classes to execute the unit tests.



## Design and Assumptions

### Grid Size and Mines Placement:

Minimum grid size is 2x2.
Maximum grid size is 10x10.
Number of mines must be between 1 and 35% of the total number of cells in the grid.


### Game Mechanics:

The game will be played through a command line interface.
The user will be prompted to input the grid size and number of mines.
Mines will be randomly placed on the grid.
The user will select cells to uncover by providing coordinates.
If a mine is uncovered, the game ends.
If a cell with no adjacent mines is uncovered, adjacent cells will be automatically uncovered.
The game is won when all non-mine cells are uncovered.

## Design

The Minesweeper game is designed with a focus on separation of concerns and adherence to SOLID principles. The primary classes involved are Minesweeper, Grid, and Cell

GameMain:

Responsibility: Entry point for the application. Initializes and starts the game.
Methods:
main(String[] args): Runs the game.


Minesweeper:

Responsibility: Manages the overall game logic, including user interaction and game flow.
Methods:
startGame(): Starts the game by prompting the user for input and managing the game loop.
getGridSize(): Prompts the user for the grid size.
getMineCount(int gridSize): Prompts the user for the number of mines.
getUserInput(): Prompts the user for a cell to reveal.


Grid:

Responsibility: Manages the grid, including placing mines, displaying the grid, and revealing cells.
Methods:
Grid(int size, int mineCount): Constructor to initialize the grid.
placeMines(): Randomly places mines on the grid.
displayGrid(): Displays the current state of the grid.
revealCell(String userInput): Reveals the cell specified by the user input.
isGameWon(): Checks if the game is won.
reveal(int row, int col): Reveals a cell and its adjacent cells if applicable.
countAdjacentMines(int row, int col): Counts the number of adjacent mines for a cell.

Cell:

Responsibility: Represents a single cell on the grid, keeping track of its state (mine, revealed, adjacent mines count).
Methods:
Cell(): Constructor to initialize a cell.
isMine(): Returns whether the cell contains a mine.
placeMine(): Places a mine in the cell.
isRevealed(): Returns whether the cell has been revealed.
reveal(): Marks the cell as revealed.
getAdjacentMinesCount(): Returns the number of adjacent mines.
setAdjacentMinesCount(int count): Sets the number of adjacent mines.
toString(): Returns a string representation of the cell's state.

### Interaction Flow

Game Start:

GameMain.main() calls Minesweeper.startGame().
Minesweeper.startGame() prompts the user for grid size and number of mines, then initializes a Grid object.
Grid.placeMines() places mines randomly on the grid.
Game Loop:

Minesweeper.startGame() enters a loop, repeatedly displaying the grid and prompting the user for input.
Grid.revealCell(userInput) reveals the selected cell and checks if it contains a mine.
If it does, the game ends.
If not, the cell's state is updated and adjacent cells are revealed if applicable.
Grid.isGameWon() checks if all non-mine cells have been revealed, indicating a win.
End Game:

If a mine is revealed, the game ends with a loss.
If all non-mine cells are revealed, the game ends with a win.

