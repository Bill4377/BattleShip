//////////////////////////////////////////////////////////////////
//
//
//
//      VBOXBORDER              CENTERHBOX
//          |                       |
//          V                       V
// (Center)VBOX                gamingBoard
//          |
//          |   
//          V
//     bottomPane
//
//
//
//
//
//
//
//
//
//
////////////////////////////////////////////////////////////////////
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiElements;

import AI.AI_Difficulty;
import AI.AI_Easy;
import AI.AI_Hard;
import javafx.scene.layout.BorderPane;
import GameElements.GamingBoard;
import GameElements.SeaBlock;
import GameElements.Ship;
import GameElements.ShipFactory;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.Event;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Bill
 */
public class ShipSetupWindow extends BorderPane {

    // Appearance Variables.
    private BorderPane vboxBorder;
    private VBox vBox;
    private HBox bottomLeftHBox;
    private Button orientationButton;
    private Button startButton;
    private HBox centerHBox;

    // Data holders.
    private ArrayList<Ship> shipList;
    private int currentSelectedShipSize = 0;
    private String orientation = "horizontal";

    // Class Objects.
    private AI_Difficulty difficulty;
    private GamingBoard gamingBoard1;
    private ShipFactory shipFactory;
    private Stage st;
    private Scene sc;

    // Event Handlers
    private EventHandler<Event> rightMouseHandlerClicked;
    private EventHandler<MouseEvent> mouseHandler;
    private EventHandler<MouseEvent> mouseHandlerEntered;
    private EventHandler<MouseEvent> mouseHandlerExited;
    private EventHandler<MouseEvent> orientationHandler;
    private EventHandler<MouseEvent> mouseHandlerClicked;
    private EventHandler<MouseEvent> startHandler;

    /**
     * Constructor of the window that will be used for placement of the ships.
     */
    public ShipSetupWindow(Stage st, Scene sc) {
        this.sc = sc;
        this.st = st;
        init();
    }

    /**
     * initializes the window.
     */
    private void init() {
        vBox = new VBox();
        bottomLeftHBox = new HBox();
        vboxBorder = new BorderPane();

        createEventHandlers();
        createShips();
        createGamingBoard();
        createButtons();
        addEventListeners();

        bottomLeftHBox.setPadding(new Insets(15, 12, 15, 12));
        bottomLeftHBox.setSpacing(10);
        bottomLeftHBox.getChildren().addAll(orientationButton, startButton);
        bottomLeftHBox.setAlignment(Pos.CENTER);

        vboxBorder.setBottom(bottomLeftHBox);

        this.setCenter(centerHBox);
        this.setLeft(vboxBorder);
    }

    /**
     * Creates and places the Ships.
     */
    private void createShips() {
        shipFactory = new ShipFactory();
        shipList = new ArrayList<>();

        // Create the ships from factory
        shipList.add(shipFactory.getShip("ship5"));
        shipList.add(shipFactory.getShip("ship4"));
        shipList.add(shipFactory.getShip("ship3"));
        shipList.add(shipFactory.getShip("ship3"));
        shipList.add(shipFactory.getShip("ship2"));

        // add the ships to the vBox and add the mouselistener to the ships
        for (int i = 0; i < shipList.size(); i++) {
            shipList.get(i).addMouseHandler(mouseHandler);
            vBox.getChildren().add(shipList.get(i).getGrid());
        }

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setStyle("-fx-background-color: #559ad8;");
        vBox.setMinWidth(calculateMinWidth(shipList.get(0)) + 25);
        vboxBorder.setStyle("-fx-background-color: #559ad8;");
        vboxBorder.setCenter(vBox);

    }

    /**
     * Creates the Grid of the game.
     */
    private void createGamingBoard() {
        centerHBox = new HBox();
        gamingBoard1 = new GamingBoard();
        centerHBox.setStyle("-fx-background-color: #005e80;");
        centerHBox.getChildren().add(gamingBoard1);
        HBox.setMargin(gamingBoard1, new Insets(2, 2, 2, 2));
        HBox.setHgrow(gamingBoard1, Priority.ALWAYS);
    }

    /**
     * initiates the event handlers
     */
    private void createEventHandlers() {
        // Event handler for selecting a ship.
        mouseHandler = (MouseEvent event) -> {
            if (currentSelectedShipSize == 0) {
                Label l = (Label) event.getSource();
                currentSelectedShipSize = (int) l.getUserData();
                removeShip(currentSelectedShipSize);
            }
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

        // Event handler for placing a ship.
        mouseHandlerClicked = (MouseEvent event) -> {
            SeaBlock b = (SeaBlock) event.getSource();

            Pair<Integer, Integer> p = (Pair<Integer, Integer>) b.getUserData();
            int x = p.getKey();
            int y = p.getValue();
            if (gamingBoard1.placeShip(x, y, currentSelectedShipSize, orientation)) {
                // reseting the currentSelectedShipSize to 0.
                currentSelectedShipSize = 0;
            }
            checkForReady();
        };

        // When we Right Click a ship we fire this event to re position the ship
        rightMouseHandlerClicked = (Event event) -> {
            SeaBlock b = (SeaBlock) event.getSource();
            Pair<Integer, Integer> p = (Pair<Integer, Integer>) b.getUserData();
            int x = p.getKey();
            int y = p.getValue();

            int sizeToCall = gamingBoard1.rePlaceShip(x, y);

            if (sizeToCall != 0) {
                this.currentSelectedShipSize = sizeToCall;
            }
        };

        // Handler for the button of the orientation
        orientationHandler = (MouseEvent event) -> {
            if (orientation.equals("horizontal")) {
                orientation = "vertical";
            } else {
                orientation = "horizontal";
            }
        };

        // Handler for the start button.
        startHandler = (MouseEvent event) -> {
            if (vBox.getChildren().isEmpty()) {
                difficultyChoose();
                System.out.println("Choose difficulty.");
            }
        };

    }

    /**
     * creates the buttons orientation and start.
     */
    private void createButtons() {
        orientationButton = new Button("Orientation");
        orientationButton.setOnMouseClicked(orientationHandler);

        startButton = new Button("Start");
        startButton.setOnMouseClicked(startHandler);

        startButton.setStyle("-fx-background-color: #9cb0c1;"
                + "-fx-effect: dropshadow( one-pass-box , #4192dd , 8 , 0 , 2 , 2 );"
                + "");
        startButton.setMinHeight(50);
        startButton.setMinWidth(100);

        orientationButton.setStyle("-fx-background-color: #93bce1;"
                + "-fx-effect: dropshadow( one-pass-box , #4192dd , 8 , 0 , 2 , 2 );"
                + "");
        orientationButton.setMinHeight(50);
        orientationButton.setMinWidth(100);

    }

    /**
     * Assigns event listeners to the elements.
     */
    private void addEventListeners() {
        gamingBoard1.addMouseEnteredHandler(mouseHandlerEntered);
        gamingBoard1.addMouseExitedHandler(mouseHandlerExited);
        gamingBoard1.addMouseClickedHandler(mouseHandlerClicked);
        gamingBoard1.addRightMouseClickedHandler(rightMouseHandlerClicked);

    }

    /**
     * removes the ship from left pane.
     *
     * @param type
     */
    private void removeShip(int type) {
        for (Ship i : this.shipList) {
            if (i.getSize() == type) {
                try {
                    if (vBox.getChildren().remove(i.getGrid())) {
                        break;
                    }
                } catch (NullPointerException e) {
                    System.out.println("Ship Doesn't exist: -> " + e.getMessage());
                }
            }
        }
    }

    /**
     * Calculates the minWidth of the left pane relative to the biggest ship in
     * size
     *
     * @param size
     * @return
     */
    private double calculateMinWidth(Ship ship) {
        double x;
        x = ship.getSize() * ship.labelSz;
        System.out.println("X is " + x);
        return x;
    }

    /**
     * Checks if we can start the game
     */
    private void checkForReady() {
        if (vBox.getChildren().isEmpty()) {
            startButton.setStyle("-fx-background-color: #93bce1;");
        } else {
            startButton.setStyle("-fx-background-color: #9cb0c1;");
        }
    }

    /**
     * Creates a new window to choose the difficulty of the game.
     */
    private void difficultyChoose() {
        Stage window = new Stage();
        BorderPane pane = new BorderPane();
        HBox hBox = new HBox(10);

        Button hardBtn = new Button("Hard");
        Button easyBtn = new Button("Easy");

        EventHandler<ActionEvent> handler = (ActionEvent event) -> {
            Button b = (Button) event.getSource();

            if (b.getText().equals("Hard")) {
                System.out.println("Difficulty set to hard");
                this.difficulty = new AI_Hard();
                window.close();
                next(this.gamingBoard1, this.st, this.sc, this.difficulty);
            } else if (b.getText().equals("Easy")) {
                System.out.println("Difficulty set to easy");
                this.difficulty = new AI_Easy();
                window.close();
                next(this.gamingBoard1, this.st, this.sc, this.difficulty);
            }

        };

        hardBtn.setStyle("-fx-background-color: #93bce1;"
                + "-fx-effect: dropshadow( one-pass-box , #4192dd , 8 , 0 , 2 , 2 );"
                + "");
        hardBtn.setMinHeight(50);
        hardBtn.setMinWidth(100);

        easyBtn.setStyle("-fx-background-color: #93bce1;"
                + "-fx-effect: dropshadow( one-pass-box , #4192dd , 8 , 0 , 2 , 2 );"
                + "");
        easyBtn.setMinHeight(50);
        easyBtn.setMinWidth(100);

        hBox.setStyle("-fx-background-color: #559ad8;");
        pane.setStyle("-fx-background-color: #559ad8;");

        hardBtn.setOnAction(handler);
        easyBtn.setOnAction(handler);

        hBox.getChildren().addAll(easyBtn, hardBtn);
        hBox.setAlignment(Pos.CENTER);

        pane.setCenter(hBox);

        Scene scene = new Scene(pane);

        window.setScene(scene);

        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(this.st);
        window.setHeight(200);
        window.setWidth(500);
        window.show();

    }

    /**
     * Creates the next window which is the PlayWindow with the 2 Boards.
     *
     * @param st Our Stage
     * @param sc Our Scene
     */
    private void next(GamingBoard g, Stage st, Scene sc, AI_Difficulty d) {
        sc.setRoot(new PlayWindow(g, st, sc, d));

        st.setTitle("Battle");

        st.setHeight(800);
        st.setWidth(1200);
    }
}
