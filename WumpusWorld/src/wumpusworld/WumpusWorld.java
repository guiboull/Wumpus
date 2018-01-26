package wumpusworld;

import static java.lang.Thread.sleep;
import java.util.Random;
import javax.swing.JFrame;

public class WumpusWorld {

    private static final int numberOfCells = 9;

    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Board boardGame = new Board(numberOfCells, numberOfCells);
        boardGame.setBoard(3);
        boardGame.showBoard();
        //boardGame.showBoard2();
        //boardGame.showBoard3();
        GameWindow mWindow = new GameWindow(boardGame);
        //boardGame.cheminPasTropOpti(numberOfCells, 1, Direction.N, "", 20);
        //System.out.println(boardGame.getCheminPasTropOpti());

        int i = 1;
        sleep(3000);
        while ( i < 2){
            int[] move = boardGame.kevin.go();
            boardGame.moveKevin(move[0], move[1]);
            mWindow.refreshBoard(boardGame);
            sleep(1000);
        }
        boardGame.getProxi(1, 9);

        ShortestPath d = new ShortestPath(numberOfCells, numberOfCells, boardGame.colGold, boardGame.rowGold, boardGame.proxiBoard);
        d.djikstra();
        d.showDjikstra();
    }

}
