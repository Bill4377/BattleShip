/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPoint;

import UiElements.MainMenu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Bill
 */
public class Main extends Application {

    private MainMenu mainMenu;
    private String playerName;

    @Override
    public void start(Stage primaryStage) {
        init(primaryStage);

    }

    /**
     * initializes the games main menu.
     *
     * @param primaryStage
     */
    private void init(Stage primaryStage) {
        primaryStage.setTitle("BattleShip");

        mainMenu = new MainMenu();

        // Event handler for the Start Button
        EventHandler<ActionEvent> eventH = (ActionEvent event) -> {
            if (!mainMenu.getName().equals("")) {
                playerName = mainMenu.getName();
                startNextOperation(primaryStage);
            }
        };
        mainMenu.getStartButton().setOnAction(eventH);

        Scene firstScene = new Scene(mainMenu, 600, 300);
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }

    /**
     * We don't need a main class. The entry point for this project is the start
     * function.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Continues from the main menu display to the next window.
     *
     * @param primaryStage
     */
    public void startNextOperation(Stage primaryStage) {

    }

}
