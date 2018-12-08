/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElements;
import static GlobalVariables.StaticVariables.shipColor;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


/**
 *
 * @author Bill
 */
public abstract class Ship extends Label {

   
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
    
    
    /**
     * adds the specific event handler on every label
     * @param m 
     */
    public abstract void addMouseHandler(EventHandler<MouseEvent> m);
}
