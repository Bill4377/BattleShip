/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameElements;

import javafx.scene.layout.GridPane;
/**
 *
 * @author Bill
 */
public class GamingBoard extends GridPane {

    private final int dimensions = 10;
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
                seaBlocks[i][j] = b;
                
                this.add(b, i, j, 1, 1);
            }
        }

    }

}
