package wumpusworld;

import javax.swing.JFrame;

public class WumpusWorld {


    private static final int numberOfCells = 9;

    public static void main(String[] args) {
        // TODO code application logic here
        Board boardGame = new Board(numberOfCells, numberOfCells);
        boardGame.setBoard(5);
        boardGame.showBoard();
        //boardGame.showBoard2();
        //boardGame.showBoard3();
        GameWindow mWindow= new GameWindow(boardGame);
        //boardGame.cheminPasTropOpti(numberOfCells, 1, Direction.N, "", 20);
        //System.out.println(boardGame.getCheminPasTropOpti());
        boardGame.getProxi(1,9);
        
        ShortestPath d = new ShortestPath(numberOfCells, numberOfCells, boardGame.colGold, boardGame.rowGold, boardGame.proxiBoard);
        d.djikstra();
        d.showDjikstra();
    }  
    
}
