package wumpusworld;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class WumpusWorld {

    public static boolean moveKevinAuto = false;
    private static final int numberOfCells = 10;

    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Board boardGame = new Board(numberOfCells, numberOfCells);
        boardGame.setBoard(5);
        boardGame.showBoard();
        //boardGame.showBoard2();
        //boardGame.showBoard3();
        GameWindow mWindow = new GameWindow(boardGame);
        //boardGame.cheminPasTropOpti(numberOfCells, 1, Direction.N, "", 20);
        //System.out.println(boardGame.getCheminPasTropOpti());

        //ShortestPath d = new ShortestPath(numberOfCells, numberOfCells, boardGame.colGold, boardGame.rowGold, boardGame.proxiBoard);
        //d.djikstra();
        // d.showDjikstra();
        sleep(3000);
        
        while (boardGame.kevin.status == 0) {
            while (moveKevinAuto) {
                int[] move = boardGame.kevin.go();
                boardGame.moveKevin(move[0], move[1]);
                mWindow.refreshBoard();
                sleep(1000);
            }
            sleep(1);
        }
    }
}
