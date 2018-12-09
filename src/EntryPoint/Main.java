/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPoint;

import UiElements.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Bill
 */
public class Main extends Application {

    private static Scene scene;
    private MainMenu mainMenu;

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
        scene = new Scene(mainMenu, 720, 360);
        mainMenu.updateData(primaryStage, scene);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
