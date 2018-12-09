/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiElements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 *
 * @author Bill
 */
public class MainMenu extends BorderPane {

    private String playerName = "";
    
    private Label nameLabel;
    private TextField nameTextField;
    private Button startButton;
    private Stage st;
    private Scene sc;
    
    /**
     * constructor for the main menu pane.
     */
    public MainMenu() {
        init();
    }

    /**
     * initializes the main menu pane.
     */
    private void init() {
        this.setCenter(addHBox());
    }

    /**
     * Create a HBox element with labels and button.
     *
     * @return
     */
    private HBox addHBox() {
        /**
         * Layout and container for the form.
         */
        HBox hbox = new HBox();

        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #559ad8;");

        /**
         * Label for the name.
         */
        nameLabel = new Label("Name: ");
        nameLabel.setStyle("-fx-text-fill: #93bce1;"
                + "-fx-font-size: 20pt;");

        /**
         * Text Field for the name input.
         */
        nameTextField = new TextField();
        nameTextField.setStyle("-fx-background-color: #559ad8;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-color: #599edd;"
                + "-fx-text-fill: #93bce1;"
                + "-fx-font-size: 20pt;"
                + "-fx-border-radius: 5;"
                + "-fx-effect: dropshadow( one-pass-box , #3a8dd8 , 8 , 0.0 , 2 , 0 );"
                + "-fx-alignment: center;"
        );

        /**
         * Button to start the game.
         */
        startButton = new Button("Start");
        startButton.setStyle("-fx-background-color: #93bce1;"
                + "-fx-effect: dropshadow( one-pass-box , #4192dd , 8 , 0 , 2 , 2 );"
                + "");
        startButton.setMinHeight(50);
        startButton.setMinWidth(100);

        // Event handler for the Start Button
        EventHandler<ActionEvent> eventH = (ActionEvent event) -> {
            if (!nameTextField.getText().equals("")) {
                playerName = nameTextField.getText();
                startNextOperation(st, sc);
            }
        };

        startButton.setOnAction(eventH);

        /**
         * Second HBox
         */
        HBox hbox2 = new HBox();
        hbox2.setStyle("-fx-background-color: #559ad8;");
        HBox.setHgrow(startButton, Priority.ALWAYS);
        HBox.setMargin(startButton, new Insets(1, 1, 80, 1));

        hbox2.getChildren().addAll(startButton);
        hbox2.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(nameLabel, nameTextField);
        hbox.setAlignment(Pos.CENTER);
        this.setBottom(hbox2);
        return hbox;
    }

    private void startNextOperation(Stage st, Scene sc){
        sc.setRoot(new ShipSetupWindow(st, sc));

        st.setTitle("BattleShip - Place your Ships " + playerName + "!");

        st.setHeight(800);
        st.setWidth(1200);
    }
    
    public void updateData(Stage st, Scene sc){
        this.st = st;
        this.sc = sc;
    }
}
