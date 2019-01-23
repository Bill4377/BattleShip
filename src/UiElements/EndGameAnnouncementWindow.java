/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiElements;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Bill
 */
public class EndGameAnnouncementWindow {

    /**
     * Creates a Window to announce the winner of the Game.
     *
     * @param stage
     * @param name
     */
    public void event(Stage stage, String name) {
        Stage window = new Stage();

        BorderPane pane = new BorderPane();
        Label label = new Label(name + " has Won!");
        pane.setCenter(label);

        pane.setStyle("-fx-background-color: #559ad8;");
        label.setStyle("-fx-text-fill: #93bce1;"
                + "-fx-font-size: 20pt;");

        Scene scene = new Scene(pane);
        window.setScene(scene);

        window.setHeight(200);
        window.setWidth(500);

        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(stage);

        window.show();

    }

}
