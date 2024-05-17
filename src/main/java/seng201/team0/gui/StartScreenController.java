package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.*;
import seng201.team0.services.StartScreenService;

/**
 * Class for controlling the start screen GUI
 */
public class StartScreenController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    /**
     * The service class for the controller
     */
    private StartScreenService startScreenService;

    /**
     * Text field for entering the user's name
     */
    @FXML
    private TextField nameTextField;

    /**
     * Slider for choosing the number of rounds
     */
    @FXML
    private Slider numRoundsField;

    /**
     * Radio buttons for choosing the difficulty of the game
     */
    @FXML
    private RadioButton easyButton, mediumButton, hardButton;

    /**
     * Label for showing if user hasn't chosen a difficulty
     */
    @FXML
    private Label difficultyWarningLabel;

    /**
     * Label for showing the descriptions of the three tower options
     */
    @FXML
    private Label descriptionLabel;

    /**
     * Labels for showing the selected towers
     */
    @FXML
    private Label selectedTower1Label, selectedTower2Label, selectedTower3Label;

    /**
     * Label for showing if user hasn't selected a tower
     */
    @FXML
    private Label towerWarningLabel;

    /**
     * Index of a chosen tower
     */
    private int towerChosen = -1;

    /**
     * Starts the display page
     * @param game The game environment
     */
    public StartScreenController(GameEnvironment game){
        this.game = game;
        startScreenService = new StartScreenService(this.game);
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
        Tower coalTower = startScreenService.towerCreation(0);
        descriptionLabel.setText(coalTower.getDescription());
        towerChosen = 0;
    }

    /**
     * If the middle block is chosen, displays the medium resource amount & medium reload speed coal tower
     */
    @FXML
    public void onTower2Chosen(){
        Tower coalTower = startScreenService.towerCreation(1);
        descriptionLabel.setText(coalTower.getDescription());
        towerChosen = 1;
    }

    /**
     * If the right most block is chosen, displays the low resource amount & high reload speed coal tower
     */
    @FXML
    public void onTower3Chosen(){
        Tower coalTower = startScreenService.towerCreation(2);
        descriptionLabel.setText(coalTower.getDescription());
        towerChosen = 2;
    }

    /**
     * Helper function for setting labels
     * @param towerChosen Which tower is chosen
     * @param name Label to change
     */
    public void towerTypeChosen(int towerChosen, Label name){
        Tower coalTower = startScreenService.towerCreation(towerChosen);
        name.setText(coalTower.getDescription());
        game.addToInventory(coalTower, coalTower.getDescription());
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
        if (game.getDifficulty() == null){
            difficultyWarningLabel.setText("Please choose a difficulty to start the game!");
        } else if (game.getInventory().getMainTowers(0) == null){
            towerWarningLabel.setText("Please select at least one tower to start the game!");
        } else {
            for (int i = 0; i < game.getNumberRounds(); i++) {
                Round newRound = new Round(game.getDifficulty());
                game.addRound(newRound);
            }
            game.getRounds().get(0).createCarts();
            game.closeStartScreen();
        }
    }
}
