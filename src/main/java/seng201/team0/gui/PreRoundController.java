package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Round;

public class PreRoundController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;
    private int currentRoundIndex;
    private Round currentRound;
    private String chosenDifficulty;

    @FXML
    private Label completionLabel;
    @FXML
    private Label easyOption, mediumOption, hardOption;
    @FXML
    private RadioButton easyButton, mediumButton, hardButton;

    public PreRoundController(GameEnvironment game) {
        this.game = game;
        this.game.increaseCurrentRoundIndex();
        currentRoundIndex = game.getCurrentRoundIndex();
        currentRound = game.getRounds().get(currentRoundIndex);
    }

    @FXML
    public void initialize(){
        completionLabel.setText("You completed the round, "+game.getName()+"!");
        if (currentRoundIndex % 2 == 0){
            currentRound.increaseNumCarts(1);
        }
        if (currentRoundIndex == 2){
            currentRound.addResourceType("Copper");
        } else if (currentRoundIndex == 4){
            currentRound.addResourceType("Iron");
        } else if (currentRoundIndex == 6) {
            currentRound.addResourceType("Gold");
        } else if (currentRoundIndex == 8){
            currentRound.addResourceType("Diamond");
        } else if (currentRoundIndex == 10){
            currentRound.addResourceType("Uranium");
        }
        displayOptions();
    }
    public void displayOptions(){
        easyOption.setText("Types of carts: "+currentRound.getResourceTypes()+"Number of Carts: "+currentRound.getNumCarts()+"\n\n" +
                "Cart Size: "+(currentRound.getCartSize()+1)+"\n\n" +
                "Cart Speed: "+(currentRound.getCartSpeed()+1));
        mediumOption.setText("Number of Carts: "+currentRound.getNumCarts()+"\n\n" +
                "Cart Size: "+(currentRound.getCartSize()+2)+"\n\n" +
                "Cart Speed: "+(currentRound.getCartSpeed()+2));
        hardOption.setText("Number of Carts: "+currentRound.getNumCarts()+"\n\n" +
                "Cart Size: "+(currentRound.getCartSize()+3)+"\n\n" +
                "Cart Speed: "+(currentRound.getCartSpeed()+3));
    }
    public void easyDifficulty(){
        currentRound.increaseCartSize(1);
    }
    public void mediumDifficulty(){
        currentRound.increaseCartSize(2);
    }
    public void hardDifficulty(){
        currentRound.increaseCartSize(3);
    }

    @FXML
    public void onDifficultyChosen(){
        if (easyButton.isSelected()){
            chosenDifficulty = "Easy";
        }
        else if (mediumButton.isSelected()){
            chosenDifficulty = "Medium";
        }
        else{
            chosenDifficulty = "Hard";
        }
    }

    @FXML
    public void onStartRound(){
        if (chosenDifficulty == "Easy"){
            easyDifficulty();
        }
        else if (chosenDifficulty == "Medium"){
            mediumDifficulty();
        }
        else {
            hardDifficulty();
        }
        game.getRounds().get(currentRoundIndex).createCarts();
        game.closePreRound();
    }
}
