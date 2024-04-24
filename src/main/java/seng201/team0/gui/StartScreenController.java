package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import seng201.team0.GameEnvironment;
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

    // Come back to this part
//    @FXML
//    private Button selectedTower1Button, selectedTower2Button, selectedTower3Button;
//
//    @FXML
//    private Button tower1Button, tower2Button, tower3Button;
//
//    private int selectedTowerIndex = -1;
//
//    private final Tower[] selectedTowers = new Tower[3];

    /**
     * Starts the display page
     * @param game The game environment
     */
    public StartScreenController(GameEnvironment game){
        this.game = game;
    }

    // Come back to adding functionality for button clicked
//    public void initialize(){
//        List<Button> selectedTowerButtons = List.of(selectedTower1Button, selectedTower2Button, selectedTower3Button);
//        List<Button> towerButtons = List.of(tower1Button, tower2Button, tower3Button);
//    }

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

    /**
     * When the Start Game button is clicked, the screen shown ends
     */
    @FXML
    public void onStartGame(){
        game.closeStartScreen();
    }
}
