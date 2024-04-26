package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.CoalTower;
import seng201.team0.GameEnvironment;
import seng201.team0.Round;
import seng201.team0.Tower;

import java.util.List;

public class StartScreenController {
    /**
     * sets the game environment attribute
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
            inputDifficulty = easyButton.getText();
        }
        else if(mediumButton.isSelected()){
            inputDifficulty = mediumButton.getText();
        }
        else{
            inputDifficulty = hardButton.getText();
        }
        game.setDifficulty(inputDifficulty);
    }

    @FXML
    public void onTower1Chosen(){
        CoalTower coalTower1 = new CoalTower();
        descriptionLabel.setText(coalTower1.getDescription(10, 10));
        towerChosen = 0;
    }

    @FXML
    public void onTower2Chosen(){
        CoalTower coalTower2 = new CoalTower();
        descriptionLabel.setText(coalTower2.getDescription(5, 5));
        towerChosen = 1;
    }

    @FXML
    public void onTower3Chosen(){
        CoalTower coalTower1 = new CoalTower();
        descriptionLabel.setText(coalTower1.getDescription(1, 3));
        towerChosen = 2;
    }

    public void towerTypeChosen(int towerChosen, Label name){
        CoalTower coalTower = new CoalTower();
        if(towerChosen == 0){
            name.setText(coalTower.getDescription(10, 10));
        }
        else if(towerChosen == 1){
            name.setText(coalTower.getDescription(5, 5));
        }
        else if(towerChosen == 2){
            name.setText(coalTower.getDescription(1, 3));
        }
    }

    @FXML
    public void onTower1Selected(){
        if(towerChosen == 0) {
            towerTypeChosen(0, selectedTower1Label);
        }
        else if(towerChosen == 1){
            towerTypeChosen(1, selectedTower1Label);
        }
        else if(towerChosen == 2){
            towerTypeChosen(2, selectedTower1Label);
        }
    }

    @FXML
    public void onTower2Selected(){
        if(towerChosen == 0) {
            towerTypeChosen(0, selectedTower2Label);
        }
        else if(towerChosen == 1){
            towerTypeChosen(1, selectedTower2Label);
        }
        else if(towerChosen == 2){
            towerTypeChosen(2, selectedTower2Label);
        }
    }

    @FXML
    public void onTower3Selected(){
        if(towerChosen == 0) {
            towerTypeChosen(0, selectedTower3Label);
        }
        else if(towerChosen == 1){
            towerTypeChosen(1, selectedTower3Label);
        }
        else if(towerChosen == 2){
            towerTypeChosen(2, selectedTower3Label);
        }
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
        game.closeStartScreen();
    }
}
