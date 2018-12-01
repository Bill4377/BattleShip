/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElements;

import static GameElements.SeaBlock.color;
import static GameElements.Ship.shipColor;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 * @author Bill
 */
public class GamingBoard extends GridPane {

    /**
     * The Dimensions of the Board
     */
    public static final int dimensions = 10;
    private SeaBlock[][] seaBlocks;

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

        this.setHgap(2);
        this.setVgap(2);
        this.setStyle("-fx-background-color: #005e80;");

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
     * ship2, indicates the size of the ship
     * if set to 0 means we have currently selected no ship to preview
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
                        System.out.println("Type: " + returnSize);
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
     * places the ship on the grid
     *
     * @param x coordinates on x axis
     * @param y coordinates on y axis
     * @param type what type of ship we want to place ship4, ship5, ship3, ship2
     * @param orientation horizontal or vertical placement.
     */
    public void placeShip(int x, int y, int type, String orientation) {
        if (type != 0) {
            if (!seaBlocks[x][y].getShipFlag()) {
                if (orientation.equals("horizontal")) {

                    int returnSize = calculate(x, y, type, orientation);

                    // If we can place it
                    if (returnSize == type) {
                        for (int i = x; i < x + type; i++) {
                            seaBlocks[i][y].setStyle("-fx-background-color: " + shipColor + ";");
                            seaBlocks[i][y].shipPlacement();
                        }
                    }
                } else if (orientation.equals("vertical")) {
                    int returnSize = calculate(x, y, type, orientation);

                    // If we can place it
                    if (returnSize == type) {
                        for (int i = y; i < y + type; i++) {
                            seaBlocks[x][i].setStyle("-fx-background-color: " + shipColor + ";");
                            seaBlocks[x][i].shipPlacement();
                        }
                    }
                }
            }
        }// end-if
    }
    
    public void rePlaceShip(int x, int y, String orientation){
        // TO DO
    }
    
}
