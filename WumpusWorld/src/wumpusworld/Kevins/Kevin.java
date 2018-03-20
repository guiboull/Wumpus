package wumpusworld.Kevins;

import wumpusworld.Cell;

public class Kevin {

    public int x;
    public int y;
    public int previousX;
    public int previousY;
    public  boolean isAlive;
    public int[][] board = new int[100][100];

    public int age;
    public int status; // 0 = en vie, 1 = mort, 2 = victoire
    public Cell currentCell;

    public int[] go() {
        //must be override
        int[] move = new int[2];

        move[0] = 0;
        move[1] = 0;

        return move;

    }

}
