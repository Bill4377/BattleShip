/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiElements;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import GameElements.GamingBoard;
import GameElements.SeaBlock;
import GameElements.Ship;
import GameElements.ShipFactory;

import java.util.ArrayList;
import javafx.event.Event;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 *
 * @author Bill
 */
public class ShipSetupWindow extends BorderPane {

    // Will be the Ship container.
    private VBox vBox;
    private HBox hBox;
    private Button orientationButton;
    private BorderPane vboxBorder;
    private ArrayList<Ship> shipList;
    private GamingBoard gamingBoard1;
    private ShipFactory shipFactory;
    private int currentSelectedShipSize = 0;
    private String orientation = "horizontal";
    private EventHandler<Event> rightMouseHandlerClicked;
    private EventHandler<MouseEvent> mouseHandler;
    private EventHandler<MouseEvent> mouseHandlerEntered;
    private EventHandler<MouseEvent> mouseHandlerExited;
    private EventHandler<MouseEvent> orientationHandler;
    private EventHandler<MouseEvent> mouseHandlerClicked;

    /**
     * Constructor of the window that will be used for placement of the ships.
     */
    public ShipSetupWindow() {
        init();
        this.setCenter(hBox);
        this.setLeft(vboxBorder);
    }

    /**
     * initializes the window.
     */
    private void init() {
        vBox = new VBox();
        vboxBorder = new BorderPane();

        createEventHandlers();
        createShips();
        createGamingBoard();
        createButtons();

        gamingBoard1.addMouseEnteredHandler(mouseHandlerEntered);
        gamingBoard1.addMouseExitedHandler(mouseHandlerExited);
        gamingBoard1.addMouseClickedHandler(mouseHandlerClicked);
        gamingBoard1.addRightMouseClickedHandler(rightMouseHandlerClicked);

        vboxBorder.setBottom(orientationButton);
    }

    /**
     * Creates and places the Ships.
     */
    private void createShips() {
        shipFactory = new ShipFactory();
        shipList = new ArrayList<>();

        // Create the ships from factory
        for (int i = 2; i < 6; i++) {
            shipList.add(shipFactory.getShip("ship" + i));
        }

        // add the ships to the vBox and add the mouselistener to the ships
        for (int i = 0; i < shipList.size(); i++) {
            shipList.get(i).addMouseHandler(mouseHandler);
            vBox.getChildren().add(shipList.get(i).getGrid());
        }

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: #99afdb;");
        vboxBorder.setStyle("-fx-background-color: #99afdb;");
        vboxBorder.setCenter(vBox);

    }

    /**
     * Creates the Grid of the game.
     */
    private void createGamingBoard() {
        hBox = new HBox();
        gamingBoard1 = new GamingBoard();
        hBox.setStyle("-fx-background-color: #005e80;");
        hBox.getChildren().add(gamingBoard1);
        HBox.setMargin(gamingBoard1, new Insets(2, 2, 2, 2));
        HBox.setHgrow(gamingBoard1, Priority.ALWAYS);
    }

    /**
     * initiates the event handlers
     */
    private void createEventHandlers() {
        // Event handler for selecting a ship.
        mouseHandler = (MouseEvent event) -> {
            Label l = (Label) event.getSource();
            currentSelectedShipSize = (int) l.getUserData();
        };

        // Event handler for previewing a ship placement.
        mouseHandlerEntered = (MouseEvent event) -> {
            SeaBlock b = (SeaBlock) event.getSource();

            if (!b.getShipFlag()) {
                Pair<Integer, Integer> p = (Pair<Integer, Integer>) b.getUserData();
                int x = p.getKey();
                int y = p.getValue();

                // we call the gamingboard1 function that checks if we can
                // put a ship at the specific block.
                gamingBoard1.previewShip(x, y, currentSelectedShipSize, orientation);
            }
        };

        // Event handler for previewing a ship placement.
        mouseHandlerExited = (MouseEvent event) -> {
            SeaBlock b = (SeaBlock) event.getSource();
            if (!b.getShipFlag()) {
                Pair<Integer, Integer> p = (Pair<Integer, Integer>) b.getUserData();
                int x = p.getKey();
                int y = p.getValue();

                gamingBoard1.unCheck(x, y, currentSelectedShipSize, orientation);
            }
        };

        // Event handler for previewing a ship placement.
        mouseHandlerClicked = (MouseEvent event) -> {
            SeaBlock b = (SeaBlock) event.getSource();

            Pair<Integer, Integer> p = (Pair<Integer, Integer>) b.getUserData();
            int x = p.getKey();
            int y = p.getValue();
            gamingBoard1.placeShip(x, y, currentSelectedShipSize, orientation);
            // reseting the currentSelectedShipSize to 0.
            currentSelectedShipSize = 0;
        };

        rightMouseHandlerClicked = (Event event) -> {
            SeaBlock b = (SeaBlock) event.getSource();
            Pair<Integer, Integer> p = (Pair<Integer, Integer>) b.getUserData();
            int x = p.getKey();
            int y = p.getValue();
            
            gamingBoard1.rePlaceShip(x, y, orientation);
            
        };
        
        
        // Handler for the button of the orientation
        orientationHandler = (MouseEvent event) -> {
            if (orientation.equals("horizontal")) {
                orientation = "vertical";
            } else {
                orientation = "horizontal";
            }
        };

    }

    /**
     * creates the buttons orientation and start.
     */
    private void createButtons() {
        orientationButton = new Button("Orientation");
        orientationButton.setOnMouseClicked(orientationHandler);
    }

}
