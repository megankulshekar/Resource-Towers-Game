package seng201.team0.gui;
// Some of the following code is reused from Tutorial 2 - Structuring applications with JavaFX
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.*;
import seng201.team0.services.StartScreenService;

import java.util.Arrays;

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
     * Label for outputting error message if name is invalid
     */
    @FXML
    private Label nameWarningLabel;

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
     * Specific tower description
     */
    private String description;

    /**
     * Array of tower descriptions selected
     */
    private String[] selectedDescriptions = new String[3];

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
     * If the left most block is chosen, displays the high resource amount and high reload speed coal tower
     */
    @FXML
    public void onTower1Chosen(){
        towerChosen = 0;
        description = startScreenService.towerDescriptionCreation(towerChosen);
        descriptionLabel.setText(description);
    }

    /**
     * If the middle block is chosen, displays the medium resource amount and medium reload speed coal tower
     */
    @FXML
    public void onTower2Chosen(){
        towerChosen = 1;
        description = startScreenService.towerDescriptionCreation(towerChosen);
        descriptionLabel.setText(description);
    }

    /**
     * If the right most block is chosen, displays the low resource amount and high reload speed coal tower
     */
    @FXML
    public void onTower3Chosen(){
        towerChosen = 2;
        description = startScreenService.towerDescriptionCreation(towerChosen);
        descriptionLabel.setText(description);
    }

    /**
     * Displays the details of the first tower in the chosen block
     */
    @FXML
    public void onTower1Selected(){
        description = startScreenService.towerDescriptionCreation(towerChosen);
        selectedTower1Label.setText(description);
        selectedDescriptions[0] = description;
    }

    /**
     * Displays the details of the second tower in the chosen block
     */
    @FXML
    public void onTower2Selected(){
        description = startScreenService.towerDescriptionCreation(towerChosen);
        selectedTower2Label.setText(description);
        selectedDescriptions[1] = description;
    }

    /**
     * Displays the details of the third tower in the chosen block
     */
    @FXML
    public void onTower3Selected(){
        description = startScreenService.towerDescriptionCreation(towerChosen);
        selectedTower3Label.setText(description);
        selectedDescriptions[2] = description;
    }

    /**
     * When the Start Game button is clicked, the towers selected are added to inventory and the Round GUI is launched
     */
    @FXML
    public void onStartGame(){
        if (game.getName() == null){
            nameWarningLabel.setText("Name must be between 3-15 characters");
        }
        else if (game.getName().length() >= 3 && game.getName().length() <= 15) {
            if (startScreenService.validCharacters(game.getName())){
                if (game.getDifficulty() == null){
                    difficultyWarningLabel.setText("Please choose a difficulty to start the game!");
                }
                else if (Arrays.asList(selectedDescriptions).contains(null)){
                    towerWarningLabel.setText("Please select three towers to start the game!");
                }
                else {
                    startScreenService.addingTower(selectedDescriptions);
                    for (int i = 0; i < game.getNumberRounds(); i++) {
                        Round newRound = new Round(game.getDifficulty());
                        game.addRound(newRound);
                    }
                    game.getRounds().get(0).createCarts();
                    game.closeStartScreen();
                }
            }
            else{
                nameWarningLabel.setText("No special characters allowed");
            }
        }
        else{
            nameWarningLabel.setText("Name must be between 3-15 characters");
        }
    }
}
