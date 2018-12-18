/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import GameElements.GamingBoard;
import javafx.util.Pair;

/**
 *
 * @author Bill
 */
public class AI {
    // The difficulty this AI will have
    // THIS IS FOR THE DESIGN PATTERN -STRATEGY PATTERN-.
    private final AI_Difficulty difficulty;
    private final GamingBoard enemyBoard;
    
    
    /**
     * Constructor of an AI that will simulate the enemy
     * @param difficulty The difficulty we cant the AI to have
     * @param enemyBoard The enemy's GamingBoard. (the player's board)
     */
    public AI(AI_Difficulty difficulty, GamingBoard enemyBoard){
        this.difficulty = difficulty;
        this.enemyBoard = enemyBoard;
    }
    
    
    /**
     * Hits a block based on the difficulty we choose.
     * THIS IS FOR THE DESIGN PATTERN -STRATEGY PATTERN-.
     */
    public void hit(){
        Pair<Integer, Integer> p = this.difficulty.hit();
        System.out.println("Coordinates TO HIT: "+p.getKey()+"  "+p.getValue());
        enemyBoard.attackShip(p.getKey(), p.getValue());
    }
    
}
