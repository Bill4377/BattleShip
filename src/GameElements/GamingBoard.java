/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElements;

import static GameElements.SeaBlock.color;
import static GlobalVariables.StaticVariables.shipColor;
import static GlobalVariables.StaticVariables.dimensions;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import java.util.Random;

/**
 *
 * @author Bill
 */
public class GamingBoard extends GridPane {

    private boolean autoGen = false;    // variable to know if we are using the auto generated method.
    private final Random rand = new Random();
    private SeaBlock[][] seaBlocks;
    private ArrayList<ShipCoordinates> ships = new ArrayList<>();

    /**
     * Gaming Board Constructor This is the board that contains the ships.
     */
    public GamingBoard() {
        initTheGrid();
    }

    /**
     * initiates the gridPane with seaBlocks according to the dimensions of the
     * game.
     */
    private void initTheGrid() {

        // Styling.
        this.setHgap(2);
        this.setVgap(2);
        this.setStyle("-fx-background-color: #74ccf4;");

        // Initialization of Elements.
        seaBlocks = new SeaBlock[dimensions][dimensions];

        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                SeaBlock b = new SeaBlock(false);
                Pair<Integer, Integer> p = new Pair<>(i, j);

                b.setUserData(p);
                seaBlocks[i][j] = b;

                this.add(b, i, j, 1, 1);
            }
        }
    }

    /**
     * Adds mouseListeners to the SeaBlocks
     *
     * @param m
     */
    public void addMouseEnteredHandler(EventHandler<MouseEvent> m) {
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                seaBlocks[i][j].setOnMouseEntered(m);
            }
        }
    }

    /**
     * Adds mouseListeners to the SeaBlocks
     *
     * @param m
     */
    public void addMouseExitedHandler(EventHandler<MouseEvent> m) {
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                seaBlocks[i][j].setOnMouseExited(m);
            }
        }
    }

    /**
     * Adds mouseListeners to the SeaBlocks
     *
     * @param m
     */
    public void addMouseClickedHandler(EventHandler<MouseEvent> m) {
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                seaBlocks[i][j].setOnMouseClicked(m);
            }
        }
    }

    /**
     * Adds mouseListeners to the SeaBlocks
     *
     * @param m
     */
    public void addRightMouseClickedHandler(EventHandler<Event> m) {
        for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                seaBlocks[i][j].setOnContextMenuRequested(m);
            }
        }
    }

    /**
     * Previews if we can place a ship on the current block at [x][y]
     *
     * @param x coordinates on x axis
     * @param y coordinates on y axis
     * @param type what type of ship we want to place ship4, ship5, ship3,
     * ship2, indicates the size of the ship if set to 0 means we have currently
     * selected no ship to preview
     * @param orientation horizontal or vertical placement.
     */
    public void previewShip(int x, int y, int type, String orientation) {
        if (type != 0) {
            if (!seaBlocks[x][y].getShipFlag()) {
                // Horizontal Placement
                if (orientation.equals("horizontal")) {

                    int returnSize = calculate(x, y, type, orientation);

                    // If we can place it
                    if (returnSize == type) {
                        for (int i = x; i < x + type; i++) {
                            seaBlocks[i][y].setStyle("-fx-background-color: #44fc41");
                        }
                    } else if (returnSize < type) {
                        for (int i = x; i < x + returnSize; i++) {
                            seaBlocks[i][y].setStyle("-fx-background-color: #fc4a40");
                        }
                    } else if (returnSize > type) {
                        for (int i = x; i < x + type; i++) {
                            seaBlocks[i][y].setStyle("-fx-background-color: #fc4a40");
                        }
                    }
                }

                // Vertical Placement
                if (orientation.equals("vertical")) {

                    int returnSize = calculate(x, y, type, orientation);

                    // If we can place it
                    if (returnSize == type) {
                        for (int i = y; i < y + type; i++) {
                            seaBlocks[x][i].setStyle("-fx-background-color: #44fc41");
                        }
                    } else if (returnSize < type) {
                        for (int i = y; i < y + returnSize; i++) {
                            seaBlocks[x][i].setStyle("-fx-background-color: #fc4a40");
                        }
                    } else if (returnSize > type) {
                        for (int i = y; i < y + type; i++) {
                            seaBlocks[x][i].setStyle("-fx-background-color: #fc4a40");
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if we can place a ship on the current block at [x][y]
     *
     * @param x coordinates on x axis
     * @param y coordinates on y axis
     * @param type what type of ship we want to place ship4, ship5, ship3, ship2
     * @param orientation horizontal or vertical placement.
     */
    public void unCheck(int x, int y, int type, String orientation) {
        if (type != 0) {
            if (!seaBlocks[x][y].getShipFlag()) {
                // Horizontal Placement
                if (orientation.equals("horizontal")) {

                    int returnSize = calculate(x, y, type, orientation);

                    // If we can place it
                    if (returnSize == type || returnSize == type + 1) {
                        for (int i = x; i < x + type; i++) {
                            seaBlocks[i][y].setStyle(color);
                        }
                    } else if (returnSize < type) {
                        for (int i = x; i < x + returnSize; i++) {
                            seaBlocks[i][y].setStyle(color);
                        }
                    }

                }

                // Vertical Placement
                if (orientation.equals("vertical")) {

                    int returnSize = calculate(x, y, type, orientation);

                    // If we can place it
                    if (returnSize == type || returnSize == type + 1) {
                        for (int i = y; i < y + type; i++) {
                            seaBlocks[x][i].setStyle(color);
                        }
                    } else if (returnSize < type) {
                        for (int i = y; i < y + returnSize; i++) {
                            seaBlocks[x][i].setStyle(color);
                        }
                    }
                }
            } // end-if
        }

    }

    /**
     * places the ship on the grid
     *
     * @param x coordinates on x axis
     * @param y coordinates on y axis
     * @param type what type of ship we want to place ship4, ship5, ship3, ship2
     * @param orientation horizontal or vertical placement.
     * @return
     */
    public boolean placeShip(int x, int y, int type, String orientation) {
        boolean placed = false;

        if (type != 0) {
            if (!seaBlocks[x][y].getShipFlag()) {
                if (orientation.equals("horizontal")) {

                    int returnSize = calculate(x, y, type, orientation);

                    // If we can place it
                    if (returnSize == type) {
                        for (int i = x; i < x + type; i++) {
                            if (!autoGen) {
                                seaBlocks[i][y].setStyle("-fx-background-color: " + shipColor + ";");
                            }
                            seaBlocks[i][y].shipPlacement();
                        }
                        placed = true;
                    }
                } else if (orientation.equals("vertical")) {
                    int returnSize = calculate(x, y, type, orientation);

                    // If we can place it
                    if (returnSize == type) {
                        for (int i = y; i < y + type; i++) {
                            if (!autoGen) {
                                seaBlocks[x][i].setStyle("-fx-background-color: " + shipColor + ";");
                            }
                            seaBlocks[x][i].shipPlacement();
                        }
                        placed = true;
                    }
                }
            }

            if (placed) {
                // here we add the ship's position to an array.
                ShipCoordinates shipCoordinates = new ShipCoordinates(x, y, type, orientation);
                shipCoordinates.printCoordinates();
                ships.add(shipCoordinates);
            }

        }// end-if
        return placed;
    }

    /**
     * if we click on a ship then we have the ability to re position it.
     *
     * @param x coordinate on the x axis
     * @param y coordinate on the y axis
     * @return returns the size of the ship or zero if we didn't click on a
     * ship.
     */
    public int rePlaceShip(int x, int y) {
        if (seaBlocks[x][y].getShipFlag()) {
            // The list with the ship's coordinates.
            ArrayList<Pair<Integer, Integer>> p = searchCoordinates(x, y);
            if (p != null) {
                for (Pair<Integer, Integer> i : p) {
                    seaBlocks[i.getKey()][i.getValue()].shipRemovement();
                }
                return p.size();
            }
        }
        return 0;
    }

    /**
     * Creates an auto generated grid.
     */
    public void createAutoGeneratedGrid() {
        int[] list = {5, 4, 3, 3, 2};
        boolean placed = false;
        this.autoGen = true;

        for (int i = 0; i < list.length; i++) {
            while (!placed) {
                int x = randInt(dimensions);
                int y = randInt(dimensions);
                placed = this.placeShip(x, y, list[i], orientationR());
            }
            placed = false;
        }
    }

    /**
     * returns an ArrayList with all of the ships coordinates.
     *
     * @return
     */
    public ArrayList<ShipCoordinates> getShipCoordianatesList() {
        return this.ships;
    }

    /**
     * Hits a ship at those coordinates.
     * @param x
     * @param y 
     */
    public void attackShip(int x, int y){
        seaBlocks[x][y].hit();
    }
    
    
    /**
     * Checks and returns if there are any ships left.
     *
     * @return
     */
    public boolean shipsLeft() {
        for (ShipCoordinates crd : ships) {
            for (Pair<Integer, Integer> i : crd.getList()) {
                int x = i.getKey();
                int y = i.getValue();

                // if a block has an undestroyed ship then return true.
                if (!seaBlocks[x][y].destroyedShip()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the first coordinates (where the ship starts from) of the ship
     * that is at the x, y position.
     *
     * @param x
     * @param y
     * @return returns 0 or higher for a successful search or -1 for a failure.
     */
    private ArrayList<Pair<Integer, Integer>> searchCoordinates(int x, int y) {
        ArrayList<Pair<Integer, Integer>> p;
        for (ShipCoordinates i : this.ships) {
            if (i.searchForCoordinates(x, y)) {
                this.ships.remove(i);
                p = i.getList();
                return p;
            }
        }
        return p = null;
    }

    /**
     * calculates if the ship can be placed on the grid.
     *
     * @param x the coordinate on the x axis. If it is set to -1 then its
     * vertical orientation
     * @param y the coordinate on the y axis. If it is set to -1 then its
     * vertical orientation
     * @param s size of the ship.
     * @param ori the orientation of the placement.
     * @return returns an integer equal to the size of the ship if we can place
     * it else returns the number of boxes the we will have to paint red because
     * we cant place it. if returns the size of the ship + 1 it means that we
     * cant place it because even though we have space we will be placing the
     * ship next to another ship.
     */
    private int calculate(int x, int y, int s, String ori) {

        int counter = 0;

        // First we check if we are inside the bounds of the grid.
        if (ori.equals("horizontal") && (((x + s) - dimensions) <= 0)) {
            // Then we check if we encounter ships.
            for (int i = x; i < x + s; i++) {
                if (!seaBlocks[i][y].getShipFlag()) {
                    counter += 1;
                    // we continue searching and dont break from the loop.
                    continue;
                }
                // if we find a ship we just break from the loop.
                break;
            }
        } else if (ori.equals("vertical") && (((y + s) - dimensions) <= 0)) {
            // Then we check if we encounter ships.
            for (int i = y; i < y + s; i++) {
                if (!seaBlocks[x][i].getShipFlag()) {
                    counter += 1;
                    continue;
                }
                // if we find a ship we just break from the loop.
                break;
            }
        } else if (ori.equals("horizontal") && (((x + s) - dimensions) >= 0)) {
            counter = dimensions - x;
            for (int i = x; i < dimensions; i++) {
                if (seaBlocks[i][y].getShipFlag()) {
                    counter -= 1;
                }
            }
            return counter;

        } else if (ori.equals("vertical") && (((y + s) - dimensions) >= 0)) {
            counter = dimensions - y;
            for (int i = y; i < dimensions; i++) {
                if (seaBlocks[x][i].getShipFlag()) {
                    counter -= 1;
                }
            }
            return counter;
        }

        // check for ships
        if (counter == s) {

            if (counter + x <= dimensions && ori.equals("horizontal")) {
                // check if we are placing our ship next to a ship
                // this checks the block next to the end of our ship
                if (counter + x < dimensions && seaBlocks[x + s][y].getShipFlag()) {
                    counter += 1;
                    return counter;
                } // and this checks the block before the beggining of our ship.
                if (x > 0 && seaBlocks[x - 1][y].getShipFlag()) {
                    counter += 1;
                    return counter;
                }

                // Check if we have ship neighbour on the sides of our ship
                for (int i = x; i < x + counter; i++) {
                    if (y > 0 && seaBlocks[i][y - 1].getShipFlag()) {
                        return counter += 1;
                    }
                    if (y < dimensions - 1 && seaBlocks[i][y + 1].getShipFlag()) {
                        return counter += 1;
                    }
                }
            } else if (counter + y <= dimensions && ori.equals("vertical")) {
                if (counter + y < dimensions && seaBlocks[x][y + s].getShipFlag()) {
                    counter += 1;
                    return counter;
                }
                if (y > 0 && seaBlocks[x][y - 1].getShipFlag()) {
                    counter += 1;
                    return counter;
                }
                for (int i = y; i < y + counter; i++) {
                    if (x > 0 && seaBlocks[x - 1][i].getShipFlag()) {
                        return counter += 1;
                    }
                    if (x < dimensions - 1 && seaBlocks[x + 1][i].getShipFlag()) {
                        return counter += 1;
                    }
                }
            }
        }
        return counter;
    }

    /**
     * returns a random integer with upper bound max.
     *
     * @param max upper bound
     * @return
     */
    private int randInt(int max) {
        int randomNum = this.rand.nextInt(max);
        return randomNum;
    }

    /**
     * returns an orientation based on a random integer between 0 and 2.
     *
     * @return
     */
    private String orientationR() {
        int x = randInt(2);
        if (x <= 1) {
            return "horizontal";
        } else {
            return "vertical";
        }
    }

}
