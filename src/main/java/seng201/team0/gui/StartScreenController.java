package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import seng201.team0.GameEnvironment;

public class StartScreenController {
    private GameEnvironment game;

    @FXML
    private TextField nameTextField;
    @FXML
    private Button startGameButton;

    public StartScreenController(GameEnvironment game){
        this.game = game;
    }

    @FXML
    public void onNameEntered(){
        String inputName = nameTextField.getText();
        game.setName(inputName);
    }

    @FXML
    public void onStartGame(){
        game.closeStartScreen();
    }
}
