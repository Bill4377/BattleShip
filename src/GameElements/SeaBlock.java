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
    private final double sizeH = 310;
    private final double sizeW = 310;
    private final String color = "-fx-background-color: #0099ff;";
    

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
    private void init(){
        this.setPrefSize(sizeW, sizeH);
        //this.setMinSize(sizeH, sizeH);
        this.setStyle(color);
    }
}
