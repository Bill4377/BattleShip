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
public class Ship3 extends Ship {

    private ArrayList<Label> labels;
    private GridPane grid;
    private final int size;

    public Ship3() {
        size = 3;
        labels = null;
        grid = null;
        constructLabels();
    }

    @Override
    public void _draw() {
        super.setText("Ship3");
        System.out.println("Ship3");
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public ArrayList<Label> getLabels() {
        return this.labels;
    }

    @Override
    public GridPane getGrid() {
        return this.grid;
    }

    @Override
    public void constructLabels() {
        if (labels == null && grid == null) {
            labels = new ArrayList<>();
            grid = new GridPane();
            for (int i = 0; i < this.size; i++) {
                Label label = new Label();
                label.setStyle("-fx-background-color: " + shipColor + ";");
                label.setPrefSize(labelSz, labelSz);
                label.setUserData(this.size);
                labels.add(label);
                grid.add(label, i, 0);
            }
        } else {
            System.out.println("Cant re-Construct labels or Grid!");
        }
    }

    @Override
    public void addMouseHandler(EventHandler<MouseEvent> m) {
        for (int i = 0; i < this.size; i++) {
            this.labels.get(i).setOnMouseClicked(m);
        }
    }

}
