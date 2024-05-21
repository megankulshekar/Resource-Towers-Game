package seng201.team0.gui;
// Some of the following code is reused from Tutorial 2 Extension - Advanced JavaFX features examples
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Tower;
import seng201.team0.services.UpgradeService;

/**
 * Class for controlling the upgrade popup GUI
 */
public class UpgradeController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    /**
     * The service class for the controller
     */
    private UpgradeService upgradeService;

    /**
     * Label for outputting success message of upgrade being applied or not applied
     */
    @FXML
    private Label mainMessageLabel, reserveMessageLabel;

    /**
     * Label showing which upgrade the user has chosen
     */
    @FXML
    private Label mainUpgradeChosenLabel, reserveUpgradeChosenLabel;

    /**
     * Label showing the main tower chosen
     */
    @FXML
    private Label mainTowerChosenLabel;

    /**
     * Label showing the reserve tower chosen
     */
    @FXML
    private Label reserveTowerChosenLabel;

    /**
     * List view of scrollable main towers list
     */
    @FXML
    private ListView<Tower>mainTowerList;

    /**
     * List view of scrollable reserve towers list
     */
    @FXML
    private ListView<Tower>reserveTowerList;

    /**
     * Button for drop down menu of upgrades
     */
    @FXML
    private MenuButton upgradesMenuButton;

    /**
     * List of items in main tower upgrade drop down menu
     */
    @FXML
    private MenuItem repairItem, upgradeXPItem, upgradeReloadSpeedItem, upgradeResourceAmountItem;

    /**
     * List of items in reserve tower upgrade drop down menu
     */
    @FXML
    private MenuItem reserveRepairItem, reserveUpgradeXPItem, reserveUpgradeReloadSpeedItem, reserveUpgradeResourceAmountItem;

    /**
     * Button for confirming main tower selection
     */
    @FXML
    private Button okayMainTowerButton;

    /**
     * Button for confirming reserve tower selection
     */
    @FXML
    private Button okayReserveTowerButton;

    /**
     * Buttons for confirming selection of upgrade that will be applied to tower
     */
    @FXML
    private Button okayMainButton, okayReserveButton;

    /**
     * Button for leaving the Upgrade GUI
     */
    @FXML
    private Button exitButton;

    /**
     * Variable to store index of upgrade item description
     */
    private int indexOfUpgradeItem;

    /**
     * Variable to store upgrade item description
     */
    private String upgradeDescription;

    /**
     * Variable to retrieve proper tower description, initialized to 0
     */
    private int towerDescriptionIndex = 0;

    /**
     * Keeps track of whether a main tower has been selected to upgrade
     */
    private Boolean mainSelected = false;

    /**
     * Keeps track of whether a reserve tower has been selected to upgrade
     */
    private Boolean reserveSelected = false;

    /**
     * Variable to store which tower has been chosen within the inventory arrays, initialized to -1
     */
    private int towerIndex = -1;

    /**
     * Constructor
     * @param game The game environment
     */
    public UpgradeController(GameEnvironment game) {
        this.game = game;
        upgradeService = new UpgradeService(this.game);
    }

    /**
     * Initializes the list view and buttons
     */
    public void initialize(){
        Tower[] mainTowers = game.getInventory().getAllMainTowers();
        Tower[] reserveTowers = game.getInventory().getAllReserveTowers();

        mainTowerList.setCellFactory(new TowerCellFactory());
        mainTowerList.setItems(FXCollections.observableArrayList(mainTowers));
        mainTowerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        reserveTowerList.setCellFactory(new TowerCellFactory());
        reserveTowerList.setItems(FXCollections.observableArrayList(reserveTowers));
        reserveTowerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        okayMainTowerButton.setDisable(true);
        okayReserveTowerButton.setDisable(true);

        disableButtons(mainTowerList, okayMainTowerButton);
        disableButtons(reserveTowerList, okayReserveTowerButton);
    }

    /**
     * Helper function for buttons that cannot initially be clicked
     * @param items List of towers
     * @param button Button that is disabled
     * @param <T> Type of objects in list
     */
    public <T> void disableButtons(ListView<T> items, Button button){
        items.getSelectionModel().selectedItemProperty().addListener((observer, oldSelection, newSelection) -> {
            if (newSelection != null){
                button.setDisable(false);
            }
            else{
                button.setDisable(true);
            }
        });
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
     * Sets the main tower selected by the user
     */
    @FXML
    public void onOkayMainTower(){
        if (reserveSelected == true){
            mainTowerChosenLabel.setText("You have chosen a reserve tower to upgrade. A main tower cannot be chosen. \n" + "If this is a mistake, click exit and re-enter the upgrade page.");
            okayMainButton.setDisable(true);
        }
        else if (mainSelected == false) {
            Tower tower = mainTowerList.getSelectionModel().getSelectedItem();
            if (tower != null) {
                mainTowerChosenLabel.setText("You selected: " + tower.getDescription().replace("\n", " "));
                towerIndex = mainTowerList.getSelectionModel().getSelectedIndex();
            } else {
                mainTowerChosenLabel.setText("");
            }
            mainSelected = true;
        }

    }

    /**
     * Sets the reserve tower selected by the user
     */
    @FXML
    public void onOkayReserveTower(){
        if (mainSelected == true){
            reserveTowerChosenLabel.setText("You have chosen a main tower to upgrade. A reserve tower cannot be chosen. \n" + "If this is a mistake, click exit and re-enter the upgrade page.");
            okayReserveButton.setDisable(true);
        }
        else if (reserveSelected == false){
            Tower tower = reserveTowerList.getSelectionModel().getSelectedItem();
            if (tower != null) {
                reserveTowerChosenLabel.setText("You selected: " + tower.getDescription().replace("\n", " "));
                towerIndex = reserveTowerList.getSelectionModel().getSelectedIndex() + 5;
            }
            else {
                reserveTowerChosenLabel.setText("");
            }
            reserveSelected = true;
        }
    }

    /**
     * When the main tower repair item button is chosen from the drop-down menu, finds the index of the repair item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onRepair(){
        upgradeDescription = repairItem.getText();
        mainUpgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the reserve tower repair item button is chosen from the drop-down menu, finds the index of the repair item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onReserveRepair(){
        upgradeDescription = reserveRepairItem.getText();
        reserveUpgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the main tower upgrade XP button is chosen from the drop-down menu, finds the index of the upgrade XP item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onUpgradeXP(){
        upgradeDescription = upgradeXPItem.getText();
        mainUpgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the reserve tower upgrade XP button is chosen from the drop-down menu, finds the index of the upgrade XP item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onReserveUpgradeXP(){
        upgradeDescription = reserveUpgradeXPItem.getText();
        reserveUpgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the main tower upgrade reload speed button is chosen from the drop-down menu, finds the index of the upgrade reload speed item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onUpgradeReloadSpeed(){
        upgradeDescription = upgradeReloadSpeedItem.getText();
        mainUpgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the reserve tower upgrade reload speed button is chosen from the drop-down menu, finds the index of the upgrade reload speed item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onReserveUpgradeReloadSpeed(){
        upgradeDescription = reserveUpgradeReloadSpeedItem.getText();
        reserveUpgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the main tower upgrade resource amount button is chosen from the drop-down menu, finds the index of the upgrade resource amount item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onUpgradeResourceAmount(){
        upgradeDescription = upgradeResourceAmountItem.getText();
        mainUpgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the reserve tower upgrade resource amount button is chosen from the drop-down menu, finds the index of the upgrade resource amount item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onReserveUpgradeResourceAmount(){
        upgradeDescription = reserveUpgradeResourceAmountItem.getText();
        reserveUpgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the okay button on the main towers tab is clicked, checks if the upgrade exists in the player's upgrades list and if it does, upgrades the main tower selected
     * If the upgrade does not exist, outputs an error message on the GUI to the player
     */
    @FXML
    public void onOkayMain() {
        if (mainTowerChosenLabel.getText() == "") {
            mainMessageLabel.setText("You have not chosen a tower");
            setLabelVisibility(mainMessageLabel);
        } else if (mainUpgradeChosenLabel.getText() == "") {
            mainMessageLabel.setText("You have not chosen an upgrade");
            setLabelVisibility(mainMessageLabel);
        } else {
            upgradeItem(mainMessageLabel);
        }
    }

    /**
     * When the okay button on the reserve towers tab is clicked, checks if the upgrade exists in the player's upgrades list and if it does, upgrades the reserve tower selected
     * If the upgrade does not exist, outputs an error message on the GUI to the player
     */
    @FXML
    public void onOkayReserve(){
        if (reserveTowerChosenLabel.getText() == "") {
            reserveMessageLabel.setText("You have not chosen a tower");
            setLabelVisibility(reserveMessageLabel);
        }
        else if (reserveUpgradeChosenLabel.getText() == "") {
            reserveMessageLabel.setText("You have not chosen an upgrade");
            setLabelVisibility(reserveMessageLabel);
        }
        else{
            upgradeItem(reserveMessageLabel);
        }
    }

    /**
     * Helper function that applies upgrade and upgrade description to tower label if it exists
     * @param messageLabel Label for the tower the upgrade is applied to
     */
    public void upgradeItem(Label messageLabel){
        if (indexOfUpgradeItem != -1 && towerIndex != -1) {
            if (towerIndex >= 5) {
                towerIndex = towerIndex - 5;
                Tower tower = reserveTowerList.getSelectionModel().getSelectedItem();
                towerDescriptionIndex = 1;
                String message = upgradeService.applyUpgrade(towerIndex, towerDescriptionIndex, tower, indexOfUpgradeItem, upgradeDescription);
                messageLabel.setText(message);
                setLabelVisibility(messageLabel);
            } else if (towerIndex < 5) {
                Tower tower = mainTowerList.getSelectionModel().getSelectedItem();
                towerDescriptionIndex = 2;
                String message = upgradeService.applyUpgrade(towerIndex, towerDescriptionIndex, tower, indexOfUpgradeItem, upgradeDescription);
                messageLabel.setText(message);
                setLabelVisibility(messageLabel);
            }
        } else if (indexOfUpgradeItem == -1) {
            messageLabel.setText("Sorry! You do not have that upgrade!");
            setLabelVisibility(messageLabel);
        }
    }

    /**
     * When the exit button is clicked, the Upgrade GUI is closed and the Inventory GUI is relaunched
     */
    @FXML
    public void onExit(){
        game.closeUpgradePopup();
    }
}