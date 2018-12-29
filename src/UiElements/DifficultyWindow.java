/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UiElements;

import AI.AI_Difficulty;
import AI.AI_Easy;
import AI.AI_Hard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Bill
 */
public class DifficultyWindow {

    private AI_Difficulty difficulty;
    
    public AI_Difficulty event(Stage stage, String name) {
        Stage window = new Stage();
        HBox hBox = new HBox(10);
        
        
        EventHandler<ActionEvent> handler = (ActionEvent event) -> {
            Button b = (Button) event.getSource();
            if(b.getText().equals("hard")){
                this.difficulty = new AI_Hard();
            }else{
                this.difficulty = new AI_Easy();
            }
            
        };

        
        
        Button hardBtn = new Button("Hard");
        Button easyBtn = new Button("Easy");
        
        
        
        hardBtn.setOnAction(handler);
        
        hBox.getChildren().addAll(easyBtn, hardBtn);
        
        Scene scene = new Scene(hBox);
        
        
        window.setScene(scene);
        
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(stage);
        
        window.show();
        
        
        
        return new AI_Easy();
    }
    
}
