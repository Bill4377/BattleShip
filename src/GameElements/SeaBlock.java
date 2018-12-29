/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElements;

import javafx.scene.control.Label;

/**
 *
 * @author Bill
 */
public class SeaBlock extends Label {

    private boolean ship = false;
    private boolean blockHit = false;       // if we have hit this block
    private boolean destroyedShip = false;
    private final double sizeH = 300;
    private final double sizeW = 300;

    /**
     * The color of a SeaBlock
     */
    public static final String color = "-fx-background-color: #0f5e9c;";
    public static final String hitColor = "-fx-background-color: #ff2222;";
    public static final String missColor = "-fx-background-color: #ffffff;";

    /**
     * Constructor for the sea block
     *
     * @param flag if there is a ship on this block.
     */
    public SeaBlock(boolean flag) {
        super();
        this.ship = flag;
        init();

    }

    /**
     * initializes the label with color etc.
     */
    private void init() {
        this.setPrefSize(sizeW, sizeH);
        //this.setMinSize(sizeH, sizeH);
        this.setStyle(color);
    }

    /**
     * returns true if a ship is on this block. else returns false.
     *
     * @return
     */
    public boolean getShipFlag() {
        return this.ship;
    }

    /**
     * changes the value of the ship variable (if exists or not a ship on this
     * cell)
     */
    public void shipPlacement() {
        this.ship = true;
    }

    /**
     * Returns if this block has a destroyed ship
     *
     * @return
     */
    public boolean destroyedShip() {
        return this.destroyedShip;
    }

    /**
     * Changes the style of the block according to a ship existence or not. this
     * method simulates the hit we did.
     *
     * @return if we hit a new block or not.
     */
    public boolean hit() {
        if (!blockHit) {
            if (this.ship) {
                this.setStyle(hitColor);
                this.destroyedShip = true;
            } else {
                this.setStyle(missColor);
            }
            blockHit = true;
            return true;
        }
        return false;
    }

    /**
     * changes the value of the ship variable (if exists or not a ship on this
     * cell)
     */
    public void shipRemovement() {
        this.ship = false;
        this.setStyle(color);
    }

}
