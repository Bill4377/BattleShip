/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import static GlobalVariables.StaticVariables.dimensions;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author Bill
 */
public class AI_Easy extends AI_Difficulty {

    private final Random rand = new Random();

    @Override
    public Pair<Integer, Integer> hit() {
        if (!initiated) {
            init();
        }

        int x = rand.nextInt(dimensions);
        int y = rand.nextInt(dimensions);

        while (!avaliable(x, y)) {
            x = rand.nextInt(dimensions);
            y = rand.nextInt(dimensions);
        }

        this.grid[x][y] = 1;

        Pair<Integer, Integer> p = new Pair<>(x, y);
        return p;
    }

    @Override
    protected void init() {

        grid = new int[dimensions][dimensions];

        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                grid[i][j] = 0;
            }
        }
        this.initiated = true;
    }

    @Override
    /**
     * Checks if we have already hit a block.
     */
    protected boolean avaliable(int x, int y) {
        if (initiated) {
            if (this.grid[x][y] == 1) {
                return false;
            }
        }
        return true;
    }

}
