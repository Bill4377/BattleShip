/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElements;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Bill
 */
public class ShipCoordinates {

    private int x;
    private int y;
    private int size;
    private String orientation;
    private ArrayList<Pair<Integer, Integer>> shipCoordinatesList;

    /**
     * Constructor of ShipCoordinates which hold information about the ship's
     * position.
     *
     * @param x
     * @param y
     * @param size
     * @param orientation
     */
    public ShipCoordinates(int x, int y, int size, String orientation) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.orientation = orientation;

        createCoordinates(orientation);
    }

    /**
     * calculates the ships position on the grid.
     *
     * @param ori
     */
    private void createCoordinates(String ori) {
        shipCoordinatesList = new ArrayList<>();

        if (ori.equals("horizontal")) {
            for (int i = 0; i < this.size; i++) {
                Pair<Integer, Integer> p = new Pair<>(x + i, y);
                shipCoordinatesList.add(p);
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                Pair<Integer, Integer> p = new Pair<>(x, y + i);
                shipCoordinatesList.add(p);
            }
        }
    }

    /**
     * returns the orientation of the ship.
     *
     * @return
     */
    public String getOrientation() {
        return this.orientation;
    }

    /**
     * returns the x position where the ship starts.
     *
     * @return
     */
    public int getStartX() {
        return this.x;
    }

    /**
     * returns the y position where the ship starts.
     *
     * @return
     */
    public int getStartY() {
        return this.y;
    }

    /**
     * returns the size of the ship
     *
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * returns the list with the ships full coordinates
     *
     * @return
     */
    public ArrayList<Pair<Integer, Integer>> getList() {
        return this.shipCoordinatesList;
    }

    /**
     * Prints all the coordinates of the ship
     */
    public void printCoordinates() {
        for (int i = 0; i < shipCoordinatesList.size(); i++) {
            System.out.println("Coordinate: " + this.shipCoordinatesList.get(i));
        }
    }

    /**
     * Searches if the ships coordinates match with the given ones.
     * @param x
     * @param y
     * @return 
     */
    public boolean searchForCoordinates(int x, int y) {
        for (Pair<Integer, Integer> i : this.shipCoordinatesList) {
            if (i.getKey() == x && i.getValue() == y) {
                return true;
            }
        }
        return false;
    }

}
