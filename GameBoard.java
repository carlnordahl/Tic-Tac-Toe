package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class GameBoard {
    private TicTacToeGame ticTacToeGame;
    JFrame board;
    //    boardInfo stores a tile number (1-9) and information about the tile: claimed by X, O or empty.
    HashMap<Integer, String> boardInfo;
    List<JButton> tiles;

    /**
     * Constructor for the GameBoard class.
     *
     * @param ticTacToeGame an instance of the TicTacToeGame class
     */
    public GameBoard(TicTacToeGame ticTacToeGame) {
        this.ticTacToeGame = ticTacToeGame;
    }

    /**
     * Create the JFrame in which the game is played.
     * Create JButtons to serve as tiles and make them alert the TicTacToeGame class when pressed.
     * Construct 'boardInfo' map and 'tiles' list.
     */
    public void initBoard() {
//        Initialize frame
        board = new JFrame("Tic Tac Toe");
        board.setLayout(new GridLayout(3, 3));

//        Initialize tiles
        JButton topLeftTile = new JButton("");
        JButton topCenterTile = new JButton("");
        JButton topRightTile = new JButton("");
        JButton midLeftTile = new JButton("");
        JButton midCenterTile = new JButton("");
        JButton midRightTile = new JButton("");
        JButton btmLeftTile = new JButton("");
        JButton btmCenterTile = new JButton("");
        JButton btmRightTile = new JButton("");

//        Add all tiles to a list
        tiles = List.of(
                topLeftTile, topCenterTile, topRightTile,
                midLeftTile, midCenterTile, midRightTile,
                btmLeftTile, btmCenterTile, btmRightTile);

//        Initialize board info. All tiles are empty ('E') in the beginning.
        boardInfo = new HashMap<>();
        boardInfo.put(0, "E");
        boardInfo.put(1, "E");
        boardInfo.put(2, "E");
        boardInfo.put(3, "E");
        boardInfo.put(4, "E");
        boardInfo.put(5, "E");
        boardInfo.put(6, "E");
        boardInfo.put(7, "E");
        boardInfo.put(8, "E");

        for (var tile : tiles) {
            tile.addActionListener(e -> {
//                alert TicTacToeGame class that a tile has been pressed
                ticTacToeGame.tilePressed(tiles.indexOf(tile));
            });
            board.add(tile);
        }
        board.setSize(600, 600);
        board.setLocationRelativeTo(null);
        board.setVisible(true);
    }

    /**
     * Mark a tile with ether 'X' for player or 'O' for computer.
     * Update boardInfo to assign the tile to the player or the computer.
     *
     * @param index the index of the selected tile in the 'tiles' list.
     * @param team  'X' for player, 'O' for computer.
     */
//    when a tile is selected, boardInfo is updated and the tile is displayed with an 'X' for player or an 'O' for computer
    public void selectTile(int index, String team) {
        tiles.get(index).setText(team);
        boardInfo.replace(index, (team));
    }

    /**
     * display option to either play again or return to menu when game is over.
     *
     * @param winner either the player, the computer or nobody (a tie)
     * @return an int representing the option picked by the user. 0 for yes, 1 for no.
     */
    public int gameOverMessage(String winner) {
        int option = JOptionPane.showOptionDialog(board, "Do you want to play again?", winner + " won!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        return option;
    }

    /**
     * close gameBoard.
     */
    public void closeBoard() {
        board.dispose();
    }
}
