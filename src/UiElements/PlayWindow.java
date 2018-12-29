/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiElements;

import AI.AI;
import AI.AI_Difficulty;
import GameElements.GamingBoard;
import GameElements.SeaBlock;
import static UiElements.MainMenu.playerName;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Bill
 */
public class PlayWindow extends BorderPane {

    // Data.
    private String round = "player";
    private AI ai;
    private AI_Difficulty difficulty;

    // GUI and Elements
    private Label pName;
    private Label cName = new Label("Computer");
    private HBox labelHolder;
    private SplitPane splitPane;
    private GamingBoard playersBoard;
    private GamingBoard computersBoard;
    private Stage st;
    private Scene sc;

    // Event Handlers.
    private EventHandler<MouseEvent> mouseHandlerClicked;

    /**
     * Constructor of a Play window.
     *
     * @param playersBoard
     * @param st
     * @param sc
     */
    public PlayWindow(GamingBoard playersBoard, Stage st, Scene sc, AI_Difficulty d) {
        this.st = st;
        this.sc = sc;
        this.difficulty = d;
        this.playersBoard = playersBoard;
        init();
    }

    /**
     * initializes the main components of the window
     */
    private void init() {
        ai = new AI(this.difficulty, playersBoard);

        pName = new Label(playerName);
        pName.setStyle("-fx-text-fill: #ea2a40;"
                + "-fx-font-size: 20pt;");
        cName.setStyle("-fx-text-fill: #9ac5ed;"
                + "-fx-font-size: 20pt;");

        labelHolder = new HBox();
        labelHolder.getChildren().addAll(this.pName, this.cName);
        labelHolder.setAlignment(Pos.CENTER);
        labelHolder.setSpacing(250);
        labelHolder.setPadding(new Insets(15, 12, 15, 12));
        labelHolder.setStyle("-fx-background-color: #0c265b;");

        splitPane = new SplitPane();
        splitPane.setPrefSize(1200, 720);
        splitPane.setPadding(new Insets(50, 50, 50, 50));

        computersBoard = new GamingBoard();

        splitPane.getItems().addAll(playersBoard, computersBoard);

        splitPane.setStyle("-fx-background-color: #0c265b ");
        this.setCenter(splitPane);
        this.setTop(labelHolder);

        computersBoard.createAutoGeneratedGrid();
        createEventHandlers();

        ai.hit();
    }

    /**
     * Creates the event handlers.
     */
    private void createEventHandlers() {
        // Event handler for placing a ship.
        mouseHandlerClicked = (MouseEvent event) -> {
            SeaBlock b = (SeaBlock) event.getSource();
            hit(b);
        };
        playersBoard.addMouseClickedHandler(mouseHandlerClicked);
        computersBoard.addMouseClickedHandler(mouseHandlerClicked);
    }

    /**
     * simulates the attack on a block by the user if its the users turn to
     * play.
     *
     * @param b
     */
    private void hit(SeaBlock b) {
        if (round.equals("player")) {
            System.out.println("parent " + b.getParent().getParent());
            if (b.hit()) {
                endCheck();
                changeRound();
            }
        }
    }

    /**
     * This method simulates the AI's attacks.
     */
    private void aiTurn() {
        ai.hit();
        endCheck();
        changeRound();
    }

    /**
     * changes the current player that can make a move.
     *
     */
    private void changeRound() {
        if (this.round.equals("player")) {
            pName.setStyle("-fx-text-fill: #ea2a40;"
                    + "-fx-font-size: 20pt;");
            cName.setStyle("-fx-text-fill: #9ac5ed;"
                    + "-fx-font-size: 20pt;");
            this.round = "computer";
            aiTurn();
        } else {
            pName.setStyle("-fx-text-fill: #9ac5ed;"
                    + "-fx-font-size: 20pt;");
            cName.setStyle("-fx-text-fill: #ea2a40;"
                    + "-fx-font-size: 20pt;");
            this.round = "player";
        }
    }

    /**
     * Checks if there are any ships left and ends the game if there are not
     * any.
     */
    private void endCheck() {
        if (this.round.equals("player")) {
            if (!computersBoard.shipsLeft()) {
                resultAnnouncement("player");
                endGame();
            }
        } else {
            if (!playersBoard.shipsLeft()) {
                resultAnnouncement("computer");
                endGame();
            }
        }
    }

    /**
     * Ends the game by opening a new ShipSetupWindow.
     */
    private void endGame() {
        sc.setRoot(new ShipSetupWindow(st, sc));

        st.setTitle("BattleShip - Place your Ships " + playerName + "!");

        st.setHeight(800);
        st.setWidth(1200);
    }

    /**
     * Displays the results of the game.
     *
     * @param name
     */
    private void resultAnnouncement(String name) {
        EndGameAnnouncementWindow e = new EndGameAnnouncementWindow();
        e.event(this.st, name);
    }

    private void difficultWindow(String name) {
        DifficultyWindow e = new DifficultyWindow();
        e.event(this.st, name);
    }

}
