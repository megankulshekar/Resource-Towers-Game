package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;

import java.util.Random;

/**
 * Class for controlling the pre round GUI
 */
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
    private Label moneyEarned, randomEvents;
    @FXML
    private Label easyOption, mediumOption, hardOption;
    @FXML
    private RadioButton easyButton, mediumButton, hardButton;

    /**
     * Constructor
     * @param game The game environment
     */
    public PreRoundController(GameEnvironment game) {
        this.game = game;
        this.game.increaseCurrentRoundIndex();
        currentRoundIndex = game.getCurrentRoundIndex();
        currentRound = game.getRounds().get(currentRoundIndex);
    }

    @FXML
    public void initialize(){
        String name = game.getName();
        if (name != null) {
            completionLabel.setText("You completed the round, " + name + "!");
        }

        Random random = new Random();
        int randomInt = random.nextInt(1);
        if (randomInt == 0){
            towerBreaks();
        }

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

    /**
     * Displays three options for the next round
     */
    public void displayOptions(){
        String resourceTypes = currentRound.getResourceTypes()
                .toString().replace("[","").replace("]","");
        easyOption.setText("Types of carts: "+resourceTypes+"\n\n" +
                "Number of Carts: "+currentRound.getNumCarts()+"\n\n" +
                "Cart Size: "+(currentRound.getCartSize()+1)+"\n\n" +
                "Cart Speed: "+(currentRound.getCartSpeed()+1));
        mediumOption.setText("Types of carts: "+resourceTypes+"\n\n" +
                "Number of Carts: "+currentRound.getNumCarts()+"\n\n" +
                "Cart Size: "+(currentRound.getCartSize()+2)+"\n\n" +
                "Cart Speed: "+(currentRound.getCartSpeed()+2));
        hardOption.setText("Types of carts: "+resourceTypes+"\n\n" +
                "Number of Carts: "+currentRound.getNumCarts()+"\n\n" +
                "Cart Size: "+(currentRound.getCartSize()+3)+"\n\n" +
                "Cart Speed: "+(currentRound.getCartSpeed()+3));
    }

    public void towerBreaks() {
        Random random = new Random();
        Tower randomTower;
        do {
            int randomIndex = random.nextInt(5);
            randomTower = game.getInventory().getMainTowers(randomIndex);
        } while (randomTower == null);
        randomTower.setBroken(true);
        randomEvents.setText("One of your towers has broken down");
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

    /**
     * Sets the difficulty of the next round,
     * creates the carts for the next round,
     * and closes the GUI
     */
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
