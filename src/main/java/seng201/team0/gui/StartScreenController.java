package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.*;

/**
 * Class for controlling the start screen GUI
 */
public class StartScreenController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button startGameButton;

    @FXML
    private Slider numRoundsField;

    @FXML
    private RadioButton easyButton, mediumButton, hardButton;

    @FXML
    private Button selectedTower1Button, selectedTower2Button, selectedTower3Button;

    @FXML
    private Button tower1Button, tower2Button, tower3Button;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label selectedTower1Label, selectedTower2Label, selectedTower3Label;

    private int towerChosen = -1;

    /**
     * Starts the display page
     * @param game The game environment
     */
    public StartScreenController(GameEnvironment game){
        this.game = game;
        this.game.setNumberRounds(5);
    }

    /**
     * Stores user-inputted value for name
     */
    @FXML
    public void onNameEntered(){
        String inputName = nameTextField.getText();
        game.setName(inputName);
    }

    /**
     * Stores user-inputted value for number of rounds
     */
    @FXML
    public void onNumChosen(){
        int inputNumberRounds = (int) numRoundsField.getValue();
        game.setNumberRounds(inputNumberRounds);
    }

    /**
     * Stores user-inputted value for level chosen
     */
    @FXML
    public void onLevelChosen(){
        String inputDifficulty;
        if(easyButton.isSelected()){
            inputDifficulty = "Easy";
        }
        else if(mediumButton.isSelected()){
            inputDifficulty = "Medium";
        }
        else{
            inputDifficulty = "Hard";
        }
        game.setDifficulty(inputDifficulty);
    }

    /**
     * If the left most block is chosen, displays the high resource amount & high reload speed coal tower
     */
    @FXML
    public void onTower1Chosen(){
        Tower coalTower = new CoalTower();
        coalTower.increaseResourceAmount(8);
        coalTower.decreaseReloadSpeed(-8);
        descriptionLabel.setText(coalTower.getDescription());
        towerChosen = 0;
    }

    /**
     * If the middle block is chosen, displays the medium resource amount & medium reload speed coal tower
     */
    @FXML
    public void onTower2Chosen(){
        Tower coalTower = new CoalTower();
        coalTower.increaseResourceAmount(3);
        coalTower.decreaseReloadSpeed(-3);
        descriptionLabel.setText(coalTower.getDescription());
        towerChosen = 1;
    }

    /**
     * If the right most block is chosen, displays the low resource amount & high reload speed coal tower
     */
    @FXML
    public void onTower3Chosen(){
        Tower coalTower = new CoalTower();
        coalTower.increaseResourceAmount(-1);
        coalTower.decreaseReloadSpeed(-1);
        descriptionLabel.setText(coalTower.getDescription());
        towerChosen = 2;
    }

    /**
     * Helper function for setting labels
     * @param towerChosen Which tower is chosen
     * @param name Label to change
     */
    public void towerTypeChosen(int towerChosen, Label name){
        Tower coalTower = new CoalTower();
        if(towerChosen == 0){
            coalTower.increaseResourceAmount(8);
            coalTower.decreaseReloadSpeed(-8);
            name.setText(coalTower.getDescription());
            game.addToInventory(coalTower, coalTower.getDescription());
        }
        else if(towerChosen == 1){
            coalTower.increaseResourceAmount(3);
            coalTower.decreaseReloadSpeed(-3);
            name.setText(coalTower.getDescription());
            game.addToInventory(coalTower, coalTower.getDescription());
        }
        else if(towerChosen == 2){
            coalTower.increaseResourceAmount(-1);
            coalTower.decreaseReloadSpeed(-1);
            name.setText(coalTower.getDescription());
            game.addToInventory(coalTower, coalTower.getDescription());
        }
    }

    /**
     * Displays the selected tower details in the chosen block
     */
    @FXML
    public void onTower1Selected(){
        towerTypeChosen(towerChosen, selectedTower1Label);
    }

    @FXML
    public void onTower2Selected(){
        towerTypeChosen(towerChosen, selectedTower2Label);
    }

    @FXML
    public void onTower3Selected(){
        towerTypeChosen(towerChosen, selectedTower3Label);
    }

    /**
     * When the Start Game button is clicked, the screen shown ends
     */
    @FXML
    public void onStartGame(){
        for (int i = 0; i < game.getNumberRounds(); i++){
            Round newRound = new Round(game.getDifficulty());
            game.addRound(newRound);
        }
        game.getRounds().get(0).createCarts();
        game.closeStartScreen();
    }
}
