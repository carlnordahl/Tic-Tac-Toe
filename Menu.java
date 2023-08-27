package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Menu {
    private TicTacToeGame ticTacToeGame;

    /**
     * Constructor for the menu
     *
     * @param ticTacToeGame an instance of the TicTacToeGame class.
     */
    public Menu(TicTacToeGame ticTacToeGame) {
        this.ticTacToeGame = ticTacToeGame;
    }

    /**
     * Create the JFrame for the menu, create a start and quit button to start or quit the game.
     */
    public void initMenu() {
        JFrame menu = new JFrame("Tic Tac Toe");
        menu.setLayout(new GridLayout(2, 1));
        JButton startGameButton = new JButton("Start");
        JButton quitGameButton = new JButton("Quit");

        startGameButton.addActionListener(e -> {
            ticTacToeGame.run();
            menu.setVisible(false);
        });
        quitGameButton.addActionListener(e -> {
            System.exit(0);
        });

        menu.add(startGameButton);
        menu.add(quitGameButton);
        menu.setSize(600, 600);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}
