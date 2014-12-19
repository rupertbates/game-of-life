package com.example;

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
                board[i][j] = random.nextBoolean();
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
        for (int i1 = i - 1; i1 <= i + 1; i1++) {
            for (int j1 = j - 1; j1 <= j + 1; j1++) {
                if (i1 >= 0 && i1 < boardSize && j1 >= 0 && j1 < boardSize && board[i1][j1])
                    live++;
            }
        }
        return live;
    }

}
