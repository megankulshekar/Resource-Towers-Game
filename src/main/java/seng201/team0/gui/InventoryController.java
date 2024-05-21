package seng201.team0.gui;
// Some of the following code is reused from Tutorial 2 - Structuring applications with JavaFX
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Tower;
import seng201.team0.services.InventoryService;

import java.util.List;

/**
 * Class for controlling the inventory GUI
 */
public class InventoryController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    /**
     * The service class for the controller
     */
    private InventoryService inventoryService;

    /**
     * Buttons for main inventory towers
     */
    @FXML
    private Button invenTower1Button, invenTower2Button, invenTower3Button, invenTower4Button, invenTower5Button;

    /**
     * Labels for main inventory towers
     */
    @FXML
    private Label tower1Label, tower2Label, tower3Label, tower4Label, tower5Label;

    /**
     * Buttons for reserve inventory towers
     */
    @FXML
    private Button reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button, reserveTower5Button;

    /**
     * Labels for reserve inventory towers
     */
    @FXML
    private Label reserveTower1Label, reserveTower2Label, reserveTower3Label, reserveTower4Label, reserveTower5Label;

    /**
     * Label for error message if towers cannot be swapped
     */
    @FXML
    private Label errorMessageLabel;

    /**
     * Button to swap two towers
     */
    @FXML
    private Button moveTowerButton;

    /**
     * Button to launch Upgrade GUI page
     */
    @FXML
    private Button upgradeTowerButton;

    /**
     * Button to exit Inventory GUI page
     */
    @FXML
    private Button exitButton;

    /**
     * Starting index when the GUI is initially launched
     */
    private int mainTowerIndex = -1;

    /**
     * Starting index when the GUI is initially launched
     */
    private int reserveTowerIndex = -1;

    /**
     * Lists for keeping track of all main tower buttons
     */
    public List<Button> invenTowerButtons;

    /**
     * Lists for keeping track of all reserve tower buttons
     */
    public List<Button> reserveTowerButtons;

    /**
     * Lists for keeping track of all main tower labels
     */
    public List<Label> mainTowerLabels;

    /**
     * Lists for keeping track of all reserve tower labels
     */
    public List<Label> reserveTowerLabels;

    /**
     * Constructor
     * @param game The game environment
     */
    public InventoryController(GameEnvironment game){
        this.game = game;
        inventoryService = new InventoryService(this.game);
    }

    /**
     * Initializes the buttons and output text
     * When certain buttons are clicked, index value of that button is stored in a variable for later use
     */
    public void initialize() {
        invenTowerButtons = List.of(invenTower1Button, invenTower2Button, invenTower3Button, invenTower4Button, invenTower5Button);
        reserveTowerButtons = List.of(reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button, reserveTower5Button);

        mainTowerLabels = List.of(tower1Label, tower2Label, tower3Label, tower4Label, tower5Label);
        reserveTowerLabels = List.of(reserveTower1Label, reserveTower2Label, reserveTower3Label, reserveTower4Label, reserveTower5Label);

        updateLabels();

        for (int i = 0; i < invenTowerButtons.size(); i++) {
            int finalI = i;
            invenTowerButtons.get(i).setOnAction(event -> {
                mainTowerIndex = finalI;
            });
        }

        for (int i = 0; i < reserveTowerButtons.size(); i++) {
            int finalI = i;
            reserveTowerButtons.get(i).setOnAction(event -> {
                reserveTowerIndex = finalI;
            });
        }
    }

    /**
     * Displays the main and reserve towers descriptions
     */
    public void updateLabels(){
        for (int i = 0; i < mainTowerLabels.size(); i++){
            Tower tower = game.getInventory().getMainTowers(i);
            if (tower != null) {
                mainTowerLabels.get(i).setText(game.getInventory().getMainTowerDescriptions(i));
            } else{
                mainTowerLabels.get(i).setText("");
            }
        }

        for (int i = 0; i < reserveTowerLabels.size(); i++){
            Tower tower = game.getInventory().getReserveTowers(i);
            if (tower != null) {
                reserveTowerLabels.get(i).setText(game.getInventory().getReserveTowerDescriptions(i));
            } else{
                reserveTowerLabels.get(i).setText("");
            }
        }
    }

    /**
     * Displays the visibility of labels for 3 seconds at a time when the buy, sell, or upgrade button is clicked
     * @param label The label being made visible
     */
    //Reference for Label Visibility: https://stackoverflow.com/questions/29487645/how-to-make-a-label-visible-for-a-certain-time-and-then-should-be-invisible-with
    public void setLabelVisibility(Label label){
        label.setVisible(true);
        PauseTransition labelDisappear = new PauseTransition(
                Duration.seconds(3)
        );
        labelDisappear.setOnFinished(
                event -> label.setVisible(false)
        );
        labelDisappear.play();
    }

    /**
     * Initiates which towers are being swapped and ensures there is always at least one tower in main
     */
    @FXML
    public void onMoveTower() {
        if (mainTowerIndex != -1 && reserveTowerIndex != -1) {
            if (inventoryService.selectTowers(game, mainTowerIndex, reserveTowerIndex)) {
                swappingTowers();
            }
            else{
                errorMessageLabel.setText("You must have at least one tower in Main Towers");
                setLabelVisibility(errorMessageLabel);
            }
        }
    }

    /**
     * Helper function for swapping towers and corresponding labels
     */
    public void swappingTowers(){
        game.getInventory().swapTowers(mainTowerIndex, reserveTowerIndex);
        String reserveText = reserveTowerLabels.get(reserveTowerIndex).getText();
        String mainText = mainTowerLabels.get(mainTowerIndex).getText();
        reserveTowerLabels.get(reserveTowerIndex).setText(mainText);
        mainTowerLabels.get(mainTowerIndex).setText(reserveText);
    }

    /**
     * Closes the Inventory GUI and launches the Upgrade GUI
     */
    @FXML
    public void onUpgradeTower(){
        game.closeInventoryForUpgrade();
        game.launchUpgradePopup();
    }

    /**
     * Exit returns back to the main game
     */
    @FXML
    public void onExit(){
        game.closeInventory();
    }
}