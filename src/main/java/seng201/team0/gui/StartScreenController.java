package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.*;
import seng201.team0.services.StartScreenService;

import java.lang.reflect.Array;

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

    private String description;

    private String selectedDescription1, selectedDescription2, selectedDescription3;

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
        description = "Tower Type: Coal\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 3\n" +
                "Reload Speed: 3";
        descriptionLabel.setText(description);
        towerChosen = 0;
    }

    /**
     * If the middle block is chosen, displays the medium resource amount and medium reload speed coal tower
     */
    @FXML
    public void onTower2Chosen(){
        description = "Tower Type: Coal\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 2\n" +
                "Reload Speed: 2";
        descriptionLabel.setText(description);
        towerChosen = 1;
    }

    /**
     * If the right most block is chosen, displays the low resource amount and high reload speed coal tower
     */
    @FXML
    public void onTower3Chosen(){
        description = "Tower Type: Coal\n" +
                "Level: 1\n" +
                "XP: 0\n" +
                "Resource Amount: 1\n" +
                "Reload Speed: 1";
        descriptionLabel.setText(description);
        towerChosen = 2;
    }

    /**
     * Helper function for setting labels
     * @param towerChosen Which tower is chosen
     * @param name Label to change
     */
    public void towerTypeChosen(int towerChosen, Label name){
        if (towerChosen == 0){
            description = "Tower Type: Coal\n" +
                    "Level: 1\n" +
                    "XP: 0\n" +
                    "Resource Amount: 3\n" +
                    "Reload Speed: 3";
            name.setText(description);
        }
        else if (towerChosen == 1){
            description = "Tower Type: Coal\n" +
                    "Level: 1\n" +
                    "XP: 0\n" +
                    "Resource Amount: 2\n" +
                    "Reload Speed: 2";
            name.setText(description);
        }
        else if (towerChosen == 2){
            description = "Tower Type: Coal\n" +
                    "Level: 1\n" +
                    "XP: 0\n" +
                    "Resource Amount: 1\n" +
                    "Reload Speed: 1";
            name.setText(description);
        }
    }

    public void addingTower(){
        for (int i = 0; i < selectedDescriptions.length; i++){
            if (selectedDescriptions[i].contains("3")){
                Tower coalTower = startScreenService.towerCreation(0);
                game.addToInventory(coalTower, coalTower.getDescription());
            }
            else if (selectedDescriptions[i].contains("2")){
                Tower coalTower = startScreenService.towerCreation(1);
                game.addToInventory(coalTower, coalTower.getDescription());
            }
            else if (selectedDescriptions[i].contains("1")){
                Tower coalTower = startScreenService.towerCreation(2);
                game.addToInventory(coalTower, coalTower.getDescription());
            }
        }
    }

    /**
     * Displays the details of the first tower in the chosen block
     */
    @FXML
    public void onTower1Selected(){
        towerTypeChosen(towerChosen, selectedTower1Label);
        selectedDescription1 = selectedTower1Label.getText();
        selectedDescriptions[0] = selectedDescription1;
    }

    /**
     * Displays the details of the second tower in the chosen block
     */
    @FXML
    public void onTower2Selected(){
        towerTypeChosen(towerChosen, selectedTower2Label);
        selectedDescription2 = selectedTower2Label.getText();
        selectedDescriptions[1] = selectedDescription2;
    }

    /**
     * Displays the details of the third tower in the chosen block
     */
    @FXML
    public void onTower3Selected(){
        towerTypeChosen(towerChosen, selectedTower3Label);
        selectedDescription3 = selectedTower3Label.getText();
        selectedDescriptions[2] = selectedDescription3;
    }

    /**
     * When the Start Game button is clicked, the screen shown ends
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
                else {
                    addingTower();
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
