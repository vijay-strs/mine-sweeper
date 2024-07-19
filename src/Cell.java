public class Cell {

    private boolean mine;
    private boolean revealed;
    private int adjacentMinesCount;

    public Cell() {
        this.mine = false;
        this.revealed = false;
        this.adjacentMinesCount = 0;
    }

    public boolean isMine() {
        return mine;
    }

    public void placeMine() {
        this.mine = true;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        this.revealed = true;
    }

    public int getAdjacentMinesCount() {
        return adjacentMinesCount;
    }

    public void setAdjacentMinesCount(int adjacentMinesCount) {
        this.adjacentMinesCount = adjacentMinesCount;
    }

    @Override
    public String toString() {
        if (!revealed) {
            return "_";
        } else if (mine) {
            return "*";
        } else {
            return String.valueOf(adjacentMinesCount);
        }
    }
}
