/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusworld.Kevins;

import wumpusworld.Cell;

/**
 *
 * @author Tancrède
 */
public class Kevin {
    
    public int age;

    public Cell currentCell;
    
    
    public int[] go() {
        //must be override
        int[] move = new int[2];
        
        move[0] = 0;
        move[1] = 0;
        
        return move;

    }
    
}
