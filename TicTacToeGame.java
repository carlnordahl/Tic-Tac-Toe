package tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TicTacToeGame {
    private GameBoard gameBoard;
    private int turnNumber;
    private boolean isPlayerTurn = true;

    /**
     * open menu.
     */
    public void menu() {
        new Menu(this).initMenu();
    }

    /**
     * start game.
     */
    public void run() {
        turnNumber = 0;
        isPlayerTurn = true;
        this.gameBoard = new GameBoard(this);
        gameBoard.initBoard();
    }

    /**
     * the player claims a tile, if player wins, the game ends otherwise the computer claims a tile.
     * If computer wins the game ends.
     * If the board is full and nobody has won, the game ends.
     *
     * @param index the index of the tile that has been pressed (index in 'tiles' list).
     */
    public void tilePressed(int index) {
        if (isPlayerTurn) {
            turnNumber++;
//            check that tile isn't already claimed
            if (gameBoard.boardInfo.get(index).equals("E")) {
                gameBoard.selectTile(index, "X");
                isPlayerTurn = false;
            }
            if (winCondition(gameBoard.boardInfo)) {
                gameOver("You");
            }
            if (turnNumber == 5)  {
                gameOver("Nobody");
            }
            if (!isPlayerTurn) {
                computerPick(gameBoard.boardInfo);
                isPlayerTurn = true;
                if (winCondition(gameBoard.boardInfo)) {
                    gameOver("Computer");
                }
            }
        }
    }

    /**
     * The computer picks a randomly selected unclaimed tile.
     *
     * @param boardInfo a map containing the indexes of all tiles (in the 'tiles' list) and a letter:
     *                  'E' for empty (not claimed)
     *                  'X' for player-claimed
     *                  'O' for computer-claimed
     */
    public void computerPick(HashMap boardInfo) {
//        make a list of the indexes of all empty tiles
        List<Integer> emptyTiles = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (boardInfo.get(i) == "E") {
                emptyTiles.add(i);
            }
        }
//        the computer selects a random tile out of the empty ones.
        gameBoard.selectTile(emptyTiles.get(new Random().nextInt(emptyTiles.size())), "O");
    }

    /**
     * Display message that says if the player, computer or nobody (a tie) won.
     * Ask if the player wants to play again.
     *
     * @param winner the player, the computer or nobody.
     */
    public void gameOver(String winner) {
        isPlayerTurn = true;
        if (gameBoard.gameOverMessage(winner) == 0) {
            gameBoard.closeBoard();
            run();
        } else {
            gameBoard.closeBoard();
            menu();
        }
    }

    /**
     * Check if a win condition is met.
     *
     * @param boardInfo a map containing the indexes of all tiles (in the 'tiles' list) and a letter:
     *                  'E' for empty (not claimed)
     *                  'X' for player-claimed
     *                  'O' for computer-claimed
     * @return true if a win condition is met, otherwise false
     */
    public boolean winCondition(HashMap boardInfo) {
//        horizontal wins
        if (boardInfo.get(0) != "E" && boardInfo.get(0) == boardInfo.get(1) && boardInfo.get(0) == boardInfo.get(2)) {
            return true;
        }
        if (boardInfo.get(3) != "E" && boardInfo.get(3) == boardInfo.get(4) && boardInfo.get(3) == boardInfo.get(5)) {
            return true;
        }
        if (boardInfo.get(6) != "E" && boardInfo.get(6) == boardInfo.get(7) && boardInfo.get(6) == boardInfo.get(8)) {
            return true;
        }
//        vertical wins
        if (boardInfo.get(0) != "E" && boardInfo.get(0) == boardInfo.get(3) && boardInfo.get(0) == boardInfo.get(6)) {
            return true;
        }
        if (boardInfo.get(1) != "E" && boardInfo.get(1) == boardInfo.get(4) && boardInfo.get(1) == boardInfo.get(7)) {
            return true;
        }
        if (boardInfo.get(2) != "E" && boardInfo.get(2) == boardInfo.get(5) && boardInfo.get(2) == boardInfo.get(8)) {
            return true;
        }
//        diagonal wins
        if (boardInfo.get(0) != "E" && boardInfo.get(0) == boardInfo.get(4) && boardInfo.get(0) == boardInfo.get(8)) {
            return true;
        }
        if (boardInfo.get(2) != "E" && boardInfo.get(2) == boardInfo.get(4) && boardInfo.get(2) == boardInfo.get(6)) {
            return true;
        }
        return false;
    }
}
