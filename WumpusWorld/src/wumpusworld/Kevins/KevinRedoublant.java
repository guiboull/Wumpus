/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusworld.Kevins;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import wumpusworld.Cell;

/**
 *
 * @author Doryann
 */
public class KevinRedoublant extends Kevin{
    
    int x;
    int y;
    int previousX;
    int previousY;
    int age;
    boolean isAlive;
    int[][] board = new int[100][100];
    Cell currentCell;
    
    @Override
     public int[] go() {
        thinkKevinTHINK();
        int[] move = new int[2];
        move[0] = 0;
        move[1] = 0;
        
        if (board[x][y] == -1) {
            move[0] = previousX - x;
            move[1] = previousY - y;
            x = previousX;
            y = previousY;
        }
        else{
            move = randomMove();
        }
        
        return move;
     }
     
     public int[] randomMove(){
        int[] move = new int[2];
        move[0] = 0;
        move[1] = 0;
        boolean gotMove = false;
        
        List<Integer> liste = new ArrayList<Integer>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        int nbTry = 0;
        while (!gotMove && nbTry < 4){
            nbTry++;
            int randomNum = liste.get(ThreadLocalRandom.current().nextInt(0, liste.size()));
            switch ( randomNum ){
                case 1:
                    gotMove = tryAbsPl(move);
                    break;
                case 2:
                    gotMove = tryAbsMs(move);
                    break;
                case 3:
                    gotMove = tryOrdPl(move);
                    break;
                case 4:
                    gotMove = tryOrdMs(move);
                    break;
            }
            
            for (int i =0 ; i < liste.size() ; i++){
                if ( liste.get(i) == randomNum){
                    liste.remove(i);
                }
            }
        }
        return move;
     }
     
     private boolean tryAbsPl(int[] move){
        if (board[x + 1][y] == 2) {
            board[x][y] = 1;
            previousX = x;
            previousY = y;
            x += 1;
            move[0] = 1;
            move[1] = 0;
            return true;
        }else{
            return false;
        }
    }
    
    private boolean tryAbsMs(int[] move){
        if (board[x - 1][y] == 2) {
            board[x][y] = 1;
            previousX = x;
            previousY = y;
            x -= 1;
            move[0] = -1;
            move[1] = 0;
            return true;
        }else{
            return false;
        }
    }
    
    private boolean tryOrdPl(int[] move){
        if (board[x][y + 1] == 2) {
            board[x][y] = 1;
            previousY = y;
            previousX = x;
            y += 1;
            move[0] = 0;
            move[1] = 1;
            return true;
        }else{
            return false;
        }
    }
    
    private boolean tryOrdMs(int[] move){
        if (board[x][y - 1] == 2) {
            board[x][y] = 1;
            previousY = y;
            previousX = x;
            y -= 1;
            move[0] = 0;
            move[1] = -1;
            return true;
        }else{
            return false;
        }
    }
     
     private void thinkKevinTHINK() {
        if (currentCell.isWall()) {
            board[x][y] = -1;
        }
     }
     
    
}
