package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import seng201.team0.GameEnvironment;

public class PreRoundController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    @FXML
    private Label completionLabel;
    @FXML
    private Label easyOption, mediumOption, hardOption;
    @FXML
    private RadioButton easyButton, mediumButton, hardButton;

    public PreRoundController(GameEnvironment game) {
        this.game = game;
    }

    @FXML
    public void initialize(){
        int currentRoundIndex = game.getCurrentRoundIndex();

        completionLabel.setText("You completed the round, "+game.getName()+"!");
    }

    @FXML
    public void onDifficultyChosen(){

    }

    @FXML
    public void onStartRound(){
        game.increaseCurrentRoundIndex();
        game.closePreRound();
    }
}
