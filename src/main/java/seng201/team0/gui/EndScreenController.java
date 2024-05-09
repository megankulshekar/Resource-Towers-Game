package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.models.GameEnvironment;

public class EndScreenController {
    private GameEnvironment game;
    private int numberOfRounds;
    private int currentRoundIndex;
    private int money;
    private boolean gameOver;

    @FXML
    private Label result, roundsCompleted, moneyGained;

    public EndScreenController(GameEnvironment game){
        this.game = game;
        numberOfRounds = this.game.getNumberRounds();
        currentRoundIndex = this.game.getCurrentRoundIndex();
        money = this.game.getMoney();
        if (currentRoundIndex+1 < numberOfRounds){
            gameOver = true;
        } else {
            gameOver = false;
        }
    }

    @FXML
    public void initialize(){
        String name = game.getName();
        if (gameOver){
            if (name != null) {
                result.setText("Game Over, " + game.getName() + "!");
            } else {
                result.setText("Game Over!");
            }
            roundsCompleted.setText("You completed "+currentRoundIndex+" out of "+numberOfRounds+" rounds");
        } else {
            if (name != null) {
                result.setText("Game Completed, " + game.getName() + "!");
            } else {
                result.setText("Game Completed!");
            }
            roundsCompleted.setText("You completed "+(currentRoundIndex+1)+" out of "+numberOfRounds+" rounds");
        }
        moneyGained.setText("You gained a total of $"+money);
    }
}
