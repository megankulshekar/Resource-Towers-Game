package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.GameEnvironment;

public class PreRoundController {
    private GameEnvironment game;

    public PreRoundController(GameEnvironment game) {
        this.game = game;
    }

    @FXML
    public void onStartNextRound(){
        game.closePreRound();
    }
}
