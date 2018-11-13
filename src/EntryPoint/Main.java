/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPoint;

import UiElements.MainMenu;
import UiElements.ShipSetupWindow;
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
    private ShipSetupWindow shipSetupWindow;
    private String playerName;
    private static Scene scene;

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
        primaryStage.setTitle("BattleShip - MainMenu");

        mainMenu = new MainMenu();
        shipSetupWindow = new ShipSetupWindow();

        // Event handler for the Start Button
        EventHandler<ActionEvent> eventH = (ActionEvent event) -> {
            if (!mainMenu.getName().equals("")) {
                playerName = mainMenu.getName();
                startNextOperation(primaryStage);
            }
        };
        mainMenu.getStartButton().setOnAction(eventH);

        scene = new Scene(mainMenu, 600, 300);
        primaryStage.setScene(scene);
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
    private void startNextOperation(Stage primaryStage) {
        scene.setRoot(shipSetupWindow);

        primaryStage.setTitle("BattleShip - Place your Ships " + playerName + "!");

        primaryStage.setHeight(800);
        primaryStage.setWidth(1200);
    }

}
