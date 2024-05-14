package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Tower;


public class UpgradeController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    /**
     * Label for outputting success message of upgrade being applied or not applied
     */
    @FXML
    private Label messageLabel;

    /**
     * Label showing which upgrade the user has chosen
     */
    @FXML
    private Label upgradeChosenLabel;

    /**
     * Label showing which tower the user has chosen
     */
    @FXML
    private Label mainTowerChosenLabel;

    @FXML
    private Label reserveTowerChosenLabel;

    /**
     * List view of scrollable main towers and reserve towers list
     */
    @FXML
    private ListView<Tower>mainTowerList;

    @FXML
    private ListView<Tower>reserveTowerList;

    /**
     * Button for drop down menu of upgrades
     */
    @FXML
    private MenuButton upgradesMenuButton;

    /**
     * List of items in drop down menu
     */
    @FXML
    private MenuItem repairItem, upgradeXPItem, upgradeReloadSpeedItem, upgradeResourceAmountItem;

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
     * Button for confirming selection of upgrade that will be applied to tower
     */
    @FXML
    private Button okayButton;

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
     * Variable to store original and new description
     */
    private String originalDescription;

    private String newDescription;

    /**
     * Keeps track of whether a main tower or reserve tower has been selected to upgrade
     */
    private Boolean mainSelected = false;

    private Boolean reserveSelected = false;

    private int towerIndex = -1;

    /**
     * Constructor
     * @param game The game environment
     */
    public UpgradeController(GameEnvironment game) {
        this.game = game;
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
     * Sets the main tower selected by the user
     */
    @FXML
    public void onOkayMainTower(){
        if (reserveSelected == true){
            mainTowerChosenLabel.setText("You have chosen a reserve tower to upgrade. A main tower cannot be chosen. \n" + "If this is a mistake, click exit and re-enter the upgrade page.");
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
     * When the repair item button is chosen from the drop-down menu, finds the index of the repair item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onRepair(){
        upgradeDescription = repairItem.getText();
        upgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the upgrade XP button is chosen from the drop-down menu, finds the index of the upgrade XP item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onUpgradeXP(){
        upgradeDescription = upgradeXPItem.getText();
        upgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the upgrade reload speed button is chosen from the drop-down menu, finds the index of the upgrade reload speed item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onUpgradeReloadSpeed(){
        upgradeDescription = upgradeReloadSpeedItem.getText();
        upgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the upgrade resource amount button is chosen from the drop-down menu, finds the index of the upgrade resource amount item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onUpgradeResourceAmount(){
        upgradeDescription = upgradeResourceAmountItem.getText();
        upgradeChosenLabel.setText("You have selected: " + upgradeDescription);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(upgradeDescription);
    }

    /**
     * When the okay button is clicked, checks if the upgrade exists in the player's upgrades list and if it does, upgrades the selected tower
     * If the upgrade does not exist, outputs an error message on the GUI to the player
     */
    @FXML
    public void onOkay(){
        if (indexOfUpgradeItem != -1) {
            if (towerIndex >= 5){
                towerIndex = towerIndex - 5;
                Tower tower = reserveTowerList.getSelectionModel().getSelectedItem();
                game.getInventory().upgradeTower(indexOfUpgradeItem, tower);
                originalDescription = game.getInventory().getReserveTowerDescriptions(towerIndex);
                newDescription = originalDescription.concat("\n\n" + upgradeDescription);
                game.getInventory().setReserveTowerDescriptions(towerIndex, newDescription);
                messageLabel.setText("Success! Upgrade applied!");
            }
            else if (towerIndex < 5){
                Tower tower = mainTowerList.getSelectionModel().getSelectedItem();
                game.getInventory().upgradeTower(indexOfUpgradeItem, tower);
                originalDescription = game.getInventory().getMainTowerDescriptions(towerIndex);
                newDescription = originalDescription.concat("\n\n" + upgradeDescription);
                game.getInventory().setMainTowerDescriptions(towerIndex, newDescription);
                messageLabel.setText("Success! Upgrade applied!");
            }
            else{
                System.out.println("The tower does not exist.");
            }
        }
        else if (indexOfUpgradeItem == -1){
            messageLabel.setText("Sorry! You do not have that upgrade!");
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