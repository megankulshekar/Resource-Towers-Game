package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.models.GameEnvironment;

/**
 * Class for controlling the end screen GUI
 */
public class EndScreenController {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The number of rounds of the game
     */
    private int numberOfRounds;

    /**
     * The index of the current round
     */
    private int currentRoundIndex;

    /**
     * The amount of money the user has at the end of the game
     */
    private int money;

    /**
     * Indicates whether the user lost or won the game
     */
    private boolean gameOver;

    /**
     * Labels showing whether the user won or lost,
     * number of rounds chosen,
     * number of rounds completed,
     * and amount of money the user has at the end of the game
     */
    @FXML
    private Label result, roundsChosen, roundsCompleted, moneyGained;

    /**
     * Constructor
     * @param game The game environment
     */
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

    /**
     * Initialises the GUI by setting text for the labels
     */
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
        roundsChosen.setText("You chose "+numberOfRounds+" rounds");
        moneyGained.setText("You gained a total of $"+money);
    }
}
