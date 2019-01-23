/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import GameElements.ShipCoordinates;
import static GlobalVariables.StaticVariables.dimensions;
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author Bill
 */
public class AI_Hard extends AI_Difficulty {

    // indicates the rate that the computer will hit a ship
    // we choose a random number from 0 to rate
    // and if the number is 0 we hit a ship for sure
    // otherwise we go on a random x, y
    // The bigger the rate the easier is for the player.
    private final int rate = 2;
    private final Random rand = new Random();
    private ArrayList<ShipCoordinates> ships;
    private ArrayList<Pair<Integer, Integer>> list;
    private int counter = 0;

    @Override
    protected void init() {

        grid = new int[dimensions][dimensions];
        this.ships = this.board.getShipCoordianatesList();

        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                grid[i][j] = 0;
            }
        }
        this.initiated = true;

        unPackCoordinates();
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

    @Override
    public Pair<Integer, Integer> hit() {

        int choose = rand.nextInt(this.rate);
        int x = rand.nextInt(dimensions);
        int y = rand.nextInt(dimensions);

        if (!this.initiated) {
            init();
        }
        if (choose == 0) {
            Pair<Integer, Integer> p = this.getCoordinateWithShip();
            x = p.getKey();
            y = p.getValue();
            System.out.println("Hard Is On");
        } else if (choose == 1) {
            while (!avaliable(x, y)) {
                x = rand.nextInt(dimensions);
                y = rand.nextInt(dimensions);
            }
        }

        grid[x][y] = 1;

        Pair<Integer, Integer> p = new Pair<>(x, y);
        return p;

    }

    /**
     * This function will unPack all the coordinates of the ships to a single
     * list.
     */
    private void unPackCoordinates() {
        list = new ArrayList<>();

        for (ShipCoordinates i : ships) {
            for (Pair<Integer, Integer> o : i.getList()) {
                list.add(o);
            }
        }
        counter = list.size() - 1;
    }

    /**
     * returns a pair with a ships coordinates.
     *
     * @return
     */
    private Pair<Integer, Integer> getCoordinateWithShip() {
        Pair<Integer, Integer> p = null;
        if (counter >= 0) {
            p = list.get(counter);
            list.remove(counter);
            counter--;
        }
        return p;
    }

}
