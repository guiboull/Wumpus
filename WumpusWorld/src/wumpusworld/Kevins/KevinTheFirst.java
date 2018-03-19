/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusworld.Kevins;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import wumpusworld.Kevins.Kevin;

/**
 *
 * @author Tancrède
 */
public class KevinTheFirst extends Kevin {

    int x;
    int y;
    int previousX;
    int previousY;
    int[][] board = new int[100][100];

    public KevinTheFirst() {
        //init position kevin
        x = 1;
        y = 1;
        previousX = 1;
        previousY = 1;
        //init age kevin
        age = 12;
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

        if (board[x][y] == -1) {
            move[0] = previousX - x;
            move[1] = previousY - y;
            x = previousX;
            y = previousY;
        }else if ( board[x][y] == 9){
            //cest fini
            status = 2;
            System.out.println("GG");
        }else if (board[x][y] == 3) {
            // si ca smell on fait demi tour
            move[0] = previousX - x;
            move[1] = previousY - y;
            x = previousX;
            y = previousY;
        } else if (board[x][y] == 4) {
            //si il y a une odeur, on fait demi tour
            move[0] = previousX - x;
            move[1] = previousY - y;
            x = previousX;
            y = previousY;
        } else {
            move = randomSafeMove();
        }
        return move;
    }
    
    private int[] randomSafeMove(){
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
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        nbTry = 0;
        while(!gotMove && nbTry < 4){
            nbTry++;
            int randomNum = liste.get(ThreadLocalRandom.current().nextInt(0, liste.size() ));
            switch ( randomNum ){
                case 1:
                    gotMove = tryAbsPlSafe(move);
                    break;
                case 2:
                    gotMove = tryAbsMsSafe(move);
                    break;
                case 3:
                    gotMove = tryOrdPlSafe(move);
                    break;
                case 4:
                    gotMove = tryOrdMsSafe(move);
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

    private boolean tryAbsPlSafe(int[] move){
        if (board[x + 1][y] == 1) {
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
    
    private boolean tryAbsMsSafe(int[] move){
        if (board[x - 1][y] == 1) {
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
    
    private boolean tryOrdPlSafe(int[] move){
        if (board[x][y + 1] == 1) {
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
    
    private boolean tryOrdMsSafe(int[] move){
        if (board[x][y - 1] == 1) {
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
        } else if (currentCell.isEmpty()) {
            if (board[x + 1][y] == 0) {
                board[x + 1][y] = 2;
            }
            if (board[x - 1][y] == 0) {
                board[x - 1][y] = 2;
            }
            if (board[x][y + 1] == 0) {
                board[x][y + 1] = 2;
            }
            if (board[x][y - 1] == 0) {
                board[x][y - 1] = 2;
            }
        } else if (currentCell.getWind()) {
            board[x][y] = 3;
            if (board[x + 1][y] == 0) {
                board[x + 1][y] = 5;
            }
            if (board[x - 1][y] == 0) {
                board[x - 1][y] = 5;
            }
            if (board[x][y + 1] == 0) {
                board[x][y + 1] = 5;
            }
            if (board[x][y - 1] == 0) {
                board[x][y - 1] = 5;
            }
        } else if (currentCell.getSmell()) {
            board[x][y] = 4;
            if (board[x + 1][y] == 0) {
                board[x + 1][y] = 6;
            }
            if (board[x - 1][y] == 0) {
                board[x - 1][y] = 6;
            }
            if (board[x][y + 1] == 0) {
                board[x][y + 1] = 6;
            }
            if (board[x][y - 1] == 0) {
                board[x][y - 1] = 6;
            }
        }else if(currentCell.getGold()){
           board[x][y] = 9;
        }else {
            board[x][y] = 1;
        }
        System.out.println("etat case "+ board[x][y] );
        System.out.print("");
    }

    /**
     * enum self board : -1 = wall 1 = safe + deja visité 2 = safe 3 = wind 4 =
     * smell 5 = possible hole 6 = possible monster 7 = hole 8 = monster 9 = gold
     */
}
