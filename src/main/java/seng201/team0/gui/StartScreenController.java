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
    }

    @FXML
    public void onTower2Chosen(){
        CoalTower coalTower2 = new CoalTower();
        descriptionLabel.setText(coalTower2.getDescription(5, 5));
    }

    @FXML
    public void onTower3Chosen(){
        CoalTower coalTower1 = new CoalTower();
        descriptionLabel.setText(coalTower1.getDescription(1, 3));
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
