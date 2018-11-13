/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiElements;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import GameElements.GamingBoard;
import GameElements.Ship;
import GameElements.ShipFactory;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author Bill
 */
public class ShipSetupWindow extends BorderPane {

    // Will be the Ship container.
    private Pane leftPane;
    private VBox vBox;
    private HBox hBox;
    private ArrayList<Pane> shipPanes;
    private ArrayList<Ship> shipList;
    private GamingBoard gamingBoard1;
    private ShipFactory shipFactory;

    /**
     * Constructor of the window that will be used for placement of the ships.
     */
    public ShipSetupWindow() {

        init();
        this.setCenter(hBox);
        this.setLeft(leftPane);
    }

    /**
     * initializes the window.
     */
    private void init() {
        vBox = new VBox();
        leftPane = new Pane();

        createShips();
        createGamingBoard();

    }

    /**
     * Creates and places the Ships.
     */
    private void createShips() {
        shipFactory = new ShipFactory();
        shipList = new ArrayList<>();
        shipPanes = new ArrayList<>();

        // Create the ships from factory
        for (int i = 3; i < 6; i++) {
            shipList.add(shipFactory.getShip("ship" + i));
        }

        // create the panes for the ships.
        for (int i = 3; i < 6; i++) {
            shipPanes.add(new Pane());
        }

        for (int i = 0; i < shipList.size(); i++) {
            Pane p = shipPanes.get(i);
            p.getChildren().add(shipList.get(i).getGrid());
            vBox.getChildren().add(p);
        }
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: #99afdb;");
        leftPane.setStyle("-fx-background-color: #99afdb;");
        leftPane.getChildren().add(vBox);
    }

    /**
     * Creates the Grid of the game.s
     */
    private void createGamingBoard() {
        hBox = new HBox();
        gamingBoard1 = new GamingBoard();
        hBox.setStyle("-fx-background-color: #005e80;");
        hBox.getChildren().add(gamingBoard1);
        HBox.setMargin(gamingBoard1, new Insets(2, 2, 2, 2));
        HBox.setHgrow(gamingBoard1, Priority.ALWAYS);
    }

}
