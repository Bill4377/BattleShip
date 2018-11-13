/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElements;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Bill
 */
public abstract class Ship extends Label {

    public String color = "#1f2e2e";
    public final double labelSz = 50;
    
    public abstract void _draw();

    public abstract void constructLabels();

    /**
     * Returns the size of the Ship.
     *
     * @return
     */
    public abstract int getSize();

    /**
     * Returns the labels that the ship is made of.
     *
     * @return
     */
    public abstract ArrayList<Label> getLabels();

    /**
     * Returns the Grid with the ship Panels.
     *
     * @return
     */
    public abstract GridPane getGrid();
}
