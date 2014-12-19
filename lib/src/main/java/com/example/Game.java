package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Live cells:
 * <p/>
 * a live cell with zero or one live neighbours will die.
 * a live cell with two or three live neighbours will remain alive
 * a live cell with four or more live neighbours will die.
 * Dead cells:
 * <p/>
 * a dead cell with exactly three live neighbours becomes alive
 * in all other cases a dead cell will stay dead.
 */

public class Game {
    private final int boardSize;
    private boolean[][] board;


    public Game(int boardSize) {
        this.boardSize = boardSize;
        createBoard();
    }

    public boolean[][] createBoard() {
        Random random = new Random();
        boolean[][] board = new boolean[boardSize][boardSize];
        for (int i = 0; i < board.length; i++) {
            boolean[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                board[i][j] = (random.nextFloat() < 0.2);
            }
        }
        this.board = board;
        return board;
    }

    public boolean[][] calculateNextGeneration() {
        boolean[][] nextBoard = new boolean[boardSize][boardSize];
        for (int i = 0; i < board.length; i++) {
            boolean[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                nextBoard[i][j] = getCellState(board, i, j);
            }
        }
        board = nextBoard;
        return nextBoard;
    }

    private boolean getCellState(boolean[][] board, int i, int j) {
        int liveNeighbours = getNumberOfLiveNeighbours(board, i, j);

        if (cellIsAlive(board, i, j))
            return liveNeighbours == 2 || liveNeighbours == 3;
        else
            return liveNeighbours == 3;

    }

    private boolean cellIsAlive(boolean[][] board, int i, int j) {
        return board[i][j];
    }

    private int getNumberOfLiveNeighbours(boolean[][] board, int i, int j) {
        int live = 0;

        List<CellAddress> neighbours = getNeighbours(i,j);
        for (CellAddress neighbour : neighbours) {
            if(cellIsAlive(board, neighbour.x, neighbour.y))
                live ++;
        }

        return live;
    }

    protected List<CellAddress> getNeighbours(int x, int y) {
        List<CellAddress> result = new ArrayList<>();
        for (int neighbourx = x - 1; neighbourx <= x + 1; neighbourx++) {
            for (int neighboury = y - 1; neighboury <= y + 1; neighboury++) {
                if(neighbourx != x || neighboury != y) //Don't add the original cell
                    result.add(getAddress(neighbourx, neighboury));
            }
        }
        return result;
    }

    private CellAddress getAddress(int x, int y) {
        return new CellAddress(getValidCoordinate(x), getValidCoordinate(y));
    }

    private int getValidCoordinate(int i){
        if(i == -1)
            return boardSize - 1;
        if(i == boardSize)
            return 0;
        return i;
    }

    static class CellAddress {
        public final int x;
        public final int y;

        CellAddress(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
