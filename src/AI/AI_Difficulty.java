/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import javafx.util.Pair;

/**
 *
 * @author Bill
 */
abstract public class AI_Difficulty{

    // The grid that we hit.
    protected int[][] grid;
    // Variable to initiate or not the 2d array with 0's
    protected boolean initiated = false;

    /**
     * Initiates the 2d array grid with 0's
    */
    abstract protected void init();
    
    /**
     * This is the Main Algorithm that we use to hit a target
     * (this is from the strategy design pattern)
     * @return 
     */
    abstract public Pair<Integer, Integer> hit();
    
    /**
     * Checks if we have hit the current coordinates.
     * @param x
     * @param y
     * @return 
     */
    abstract protected boolean avaliable(int x, int y);
    
}
