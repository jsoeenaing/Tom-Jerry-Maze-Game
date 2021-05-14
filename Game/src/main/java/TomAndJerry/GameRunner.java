package TomAndJerry;

import javax.swing.*;
import java.awt.*;

public class  GameRunner extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            // Calls GameRunner() which creates a new board
            GameRunner gb = new GameRunner();
            gb.setVisible(true);
            // Setting size of the screen
            gb.setSize(600, 700);
            // Adding "Tom and Jerry" on the frame
            gb.setTitle("Tom And Jerry");
            gb.setDefaultCloseOperation(EXIT_ON_CLOSE);
            gb.setLocationRelativeTo(null);
        });

    }

    // Creates a new game board
    public GameRunner() {
        add(new GameBoard());
    }
}