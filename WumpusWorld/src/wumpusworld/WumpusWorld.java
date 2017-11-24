/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusworld;

import javax.swing.JFrame;

/**
 *
 * @author frus71323
 */
public class WumpusWorld {

    /**
     * @param args the command line arguments
     */
    private static final int numberOfCells = 9;

    public static void main(String[] args) {
        // TODO code application logic here
        Board boardGame = new Board(numberOfCells, numberOfCells);
        boardGame.setBoard(2);
        boardGame.showBoard();
        boardGame.showBoard2();
        boardGame.showBoard3();
        GameWindow mWindow= new GameWindow(boardGame);
        boardGame.cheminOpti(numberOfCells, 1, Direction.N, "", 20);
        System.out.println(boardGame.getCheminOpti());
    }

}
