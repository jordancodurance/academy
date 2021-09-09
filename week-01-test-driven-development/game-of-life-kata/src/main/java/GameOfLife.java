import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;

public class GameOfLife {

    private final boolean[][] board;

    public GameOfLife(boolean[][] board) {
        this.board = board;
    }

    public boolean[][] getCurrentGeneration() {
        return board;
    }

    public void nextGeneration() {
        boolean[][] previousGeneration = getPreviousGeneration();

        for (int row = 0; row < previousGeneration.length; row++) {
            for (int column = 0; column < previousGeneration[row].length; column++) {
                updateCellStatus(row, column, previousGeneration);
            }
        }
    }

    private boolean[][] getPreviousGeneration() {
        return stream(board)
                .map(row -> copyOf(row, row.length))
                .toArray(boolean[][]::new);
    }

    private void updateCellStatus(int row, int column, boolean[][] previousGeneration) {
        List<Boolean> neighbourStatuses = getSurroundingNeighbourStatuses(row, column, previousGeneration);
        long aliveNeighbourCount = neighbourStatuses.stream().filter(status -> status).count();

        if (aliveNeighbourCount < 2) {
            board[row][column] = false;
        } else {
            board[row][column] = aliveNeighbourCount == 3;
        }
    }

    public List<Boolean> getSurroundingNeighbourStatuses(int cellRow, int cellColumn, boolean[][] board) {
        List<Boolean> neighbours = new ArrayList<>();
        int previousRow = cellRow - 1;
        int previousColumn = cellColumn - 1;
        for (int row = previousRow; row < previousRow + 3; row++) {
            for (int column = previousColumn; column < previousColumn + 3; column++) {
                if (row != cellRow || column != cellColumn) {
                    boolean neighbour = getNeighbourCell(row, column, board);
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    private boolean getNeighbourCell(int row, int column, boolean[][] board) {
        try {
            return board[row][column];
        } catch (IndexOutOfBoundsException exception) {
            return false;
        }
    }
}
