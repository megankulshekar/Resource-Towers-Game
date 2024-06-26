package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Round;
import seng201.team0.services.PreRoundService;

import java.util.Random;

/**
 * Class for controlling the pre round GUI
 */
public class PreRoundController {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The service class for the controller
     */
    private PreRoundService preRoundService;

    /**
     * The index of the current round in the round list
     */
    private int currentRoundIndex;

    /**
     * The next round
     */
    private Round currentRound;

    /**
     * The text that will display how much money the played earned
     */
    private static String moneyEarnedText = "You earned $";

    /**
     * The text that will display what random events have occurred
     */
    private static String randomEventsText = "No random events occurred";

    /**
     * The index of the previous round that used the giveMoney method
     */
    private static int previousIndexUsingGiveMoney = -1;

    /**
     * The index of the previous round that randomly chose if a random event will happen
     */
    private static int previousIndexUsingRandomEvents = -1;

    /**
     * The index of the previous round that used the addNewCart method
     */
    private static int previousIndexUsingAddNewCart = -1;

    /**
     * The difficulty of the next round chosen by the user
     */
    private String chosenDifficulty;

    /**
     * Label for telling the user they completed the last round
     */
    @FXML
    private Label completionLabel;

    /**
     * Labels for showing money earned and random events after a round
     */
    @FXML
    private Label moneyEarned, randomEvents;

    /**
     * Label for telling user to select a difficulty for the next round
     */
    @FXML
    private Label selectDifficultyLabel;

    /**
     * Labels for showing the difficulty options for the next round
     */
    @FXML
    private Label easyOption, mediumOption, hardOption;

    /**
     * Radio buttons for choosing the difficulty of the next round
     */
    @FXML
    private RadioButton easyButton, mediumButton, hardButton;

    /**
     * Constructor
     * @param game The game environment
     */
    public PreRoundController(GameEnvironment game) {
        this.game = game;
        preRoundService = new PreRoundService(this.game);
        currentRoundIndex = game.getCurrentRoundIndex();
        currentRound = preRoundService.getCurrentRound();
    }

    /**
     * Initialises the GUI by setting text for the labels,
     * giving money to the user, performing a random event,
     * and increasing number of carts and cart resource types
     * for next round
     */
    @FXML
    public void initialize(){
        String name = game.getName();
        if (name != null) {
            completionLabel.setText("You completed the round, "+name+"!");
        }

        if (previousIndexUsingGiveMoney != currentRoundIndex) {
            previousIndexUsingGiveMoney = currentRoundIndex;
            String amountGiven = preRoundService.giveMoney();
            moneyEarnedText = "You earned " + amountGiven;
        }

        if (previousIndexUsingRandomEvents != currentRoundIndex) {
            previousIndexUsingRandomEvents = currentRoundIndex;
            Random random = new Random();
            int randomInt = random.nextInt(10);
            if (randomInt == 0) {
                int XPGained = preRoundService.towerGainsXP();
                randomEventsText = "One of your towers has gained " + XPGained + " XP";
            } else if (randomInt == 1) {
                preRoundService.towerBreaks();
                randomEventsText = "One of your towers has broken down";
            }
        }

        if (previousIndexUsingAddNewCart != currentRoundIndex) {
            previousIndexUsingAddNewCart = currentRoundIndex;
            preRoundService.addNewCart();
        }

        preRoundService.addNewResourceType();

        moneyEarned.setText(moneyEarnedText);
        randomEvents.setText(randomEventsText);
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
                "Cart Speed: "+(currentRound.getCartSpeed()+1));
        hardOption.setText("Types of carts: "+resourceTypes+"\n\n" +
                "Number of Carts: "+currentRound.getNumCarts()+"\n\n" +
                "Cart Size: "+(currentRound.getCartSize()+3)+"\n\n" +
                "Cart Speed: "+(currentRound.getCartSpeed()+1));
    }

    /**
     * Selects the difficulty based on the corresponding radio button
     */
    @FXML
    public void onDifficultyChosen(){
        if (easyButton.isSelected()){
            chosenDifficulty = "Easy";
        }
        else if (mediumButton.isSelected()){
            chosenDifficulty = "Medium";
        }
        else if (hardButton.isSelected()){
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
        if (chosenDifficulty == null){
            selectDifficultyLabel.setText("Please select a difficulty option for the next round!");
        } else {
            if (chosenDifficulty.equals("Easy")) {
                preRoundService.setEasyDifficulty();
            } else if (chosenDifficulty.equals("Medium")) {
                preRoundService.setMediumDifficulty();
            } else if (chosenDifficulty.equals("Hard")) {
                preRoundService.setHardDifficulty();
            }

            randomEventsText = "No random events occurred";

            game.increaseCurrentRoundIndex();
            int nextRoundIndex = game.getCurrentRoundIndex();
            game.getRounds().get(nextRoundIndex).createCarts();
            game.closePreRound();
        }
    }
}
