/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiElements;

import GameElements.GamingBoard;
import javafx.geometry.Insets;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author Bill
 */
public class PlayWindow extends BorderPane {

    private SplitPane splitPane;
    private GamingBoard playersBoard;
    private GamingBoard computersBoard;

    public PlayWindow() {
        init();
    }
    
    
    private void init(){
        splitPane = new SplitPane();
        splitPane.setPrefSize(1200, 720);
        splitPane.setPadding(new Insets(50, 50, 50, 50));

        playersBoard = new GamingBoard();
        computersBoard = new GamingBoard();

        splitPane.getItems().addAll(playersBoard, computersBoard);

        splitPane.setStyle("-fx-background-color: #0c265b ");
        this.setCenter(splitPane);
    }

}
