/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusworld;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author frus71323
 */
public class GameWindow {
    
    private int windowsSize = 800;
    private JButton[][] buttonGrid;

    public GameWindow(Board mBoard) {
        buttonGrid = new JButton[mBoard.x][mBoard.y];
        JFrame window = new JFrame();
        window.setTitle("Le monde de Wumpus");
        window.setSize(windowsSize, windowsSize);
        window.setLayout(new GridLayout(mBoard.x, mBoard.y));
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                String cellLabel = "";
                if (mBoard.getBoard()[row][col].getWall() == true) {
                    cellLabel += "WALL \n";
                }
                if (mBoard.getBoard()[row][col].getHole() == true) {
                    cellLabel += "HOLE \n";
                }
                if (mBoard.getBoard()[row][col].getMonster() == true) {
                    cellLabel += "MONSTER \n";
                }
                if (mBoard.getBoard()[row][col].getPlayer() == true) {
                    cellLabel += "PLAYER \n";
                }
                if (mBoard.getBoard()[row][col].getGold() == true) {
                    cellLabel += "GOLD \n";
                }
                if (mBoard.getBoard()[row][col].getSmell() == true) {
                    cellLabel += "SMELL \n";
                }
                if (mBoard.getBoard()[row][col].getWind() == true) {
                    cellLabel += "WIND \n";
                }
                buttonGrid[row][col] = new JButton(cellLabel);
                buttonGrid[row][col].setEnabled(false);
                window.add(buttonGrid[row][col]);
            }
        }

        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

}
