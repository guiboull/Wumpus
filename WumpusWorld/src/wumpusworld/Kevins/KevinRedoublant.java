/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusworld.Kevins;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import wumpusworld.Cell;

/**
 *
 * @author Doryann
 */
public class KevinRedoublant extends Kevin {

    int x;
    int y;
    int previousX;
    int previousY;
    int age;
    int[][] board = new int[100][100];

    public KevinRedoublant() {
        //init position kevin
        x = 1;
        y = 1;
        previousX = 1;
        previousY = 1;
        //init age kevin
        age = 15;
        status = 0;
        //init board kevin
        board[x][y] = 0;
        board[0][0] = -1;
        board[1][0] = -1;
        board[0][1] = -1;

    }

    @Override
    public int[] go() {
        thinkKevinTHINK();
        int[] move = new int[2];
        move[0] = 0;
        move[1] = 0;
        Random rand = new Random();
        if (board[x][y] == 5) {
            status = 1;
            System.out.println("Pas GG");
        } else if (board[x][y] == 9) {
            status = 2;
            System.out.println("GG");
        } else if (board[x][y] == -1) {
            move[0] = previousX - x;
            move[1] = previousY - y;
            x = previousX;
            y = previousY;
        } else {
            int randomMove = rand.nextInt(4);
            System.out.println(randomMove);
            board[x][y] = 1;
            previousX = x;
            previousY = y;
            switch (randomMove) {
                case 0:
                    x += 1;
                    move[0] = 1;
                    move[1] = 0;
                    break;
                case 1:
                    x -= 1;
                    move[0] = -1;
                    move[1] = 0;
                    break;
                case 2:
                    y += 1;
                    move[0] = 0;
                    move[1] = 1;
                    break;
                case 3:
                    y -= 1;
                    move[0] = 0;
                    move[1] = -1;
                    break;
            }
        }
        return move;
    }

    private void thinkKevinTHINK() {
        if (currentCell.isWall()) {
            board[x][y] = -1;
        } else if (!currentCell.isSafe()) {
            board[x][y] = 5;
        } else if (currentCell.getGold()) {
            board[x][y] = 9;
        } else {
            board[x][y] = 1;
        }
        System.out.println("etat case " + board[x][y]);
        System.out.print("");
    }
}
