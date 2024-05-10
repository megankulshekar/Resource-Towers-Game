package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Tower;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class NewStageUpgradePopupController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    @FXML
    private Label messageLabel;

    @FXML
    private Label upgradeChosenLabel;

    @FXML
    private Label mainTowerChosenLabel;

    @FXML
    private Label reserveTowerChosenLabel;

    @FXML
    private ListView<Tower>mainTowerList;

    @FXML
    private ListView<Tower>reserveTowerList;

    @FXML
    private MenuButton upgradesMenuButton;

    @FXML
    private MenuItem repairItem, upgradeXPItem, upgradeReloadSpeedItem, upgradeResourceAmountItem;

    @FXML
    private Button okayMainTowerButton;

    @FXML
    private Button okayReserveTowerButton;

    @FXML
    private Button okayButton;

    @FXML
    private Button exitButton;

    private int indexOfUpgradeItem;

    private String upgradeDescription;

    private String originalDescription;

    private String newDescription;

    private Boolean mainSelected = false;

    private Boolean reserveSelected = false;

    private int towerIndex = -1;

    /**
     * Constructor
     * @param game The game environment
     */
    public NewStageUpgradePopupController(GameEnvironment game) {
        this.game = game;
    }

    public void initialize(){
        Tower[] mainTowers = game.getInventory().getAllMainTowers();
        Tower[] reserveTowers = game.getInventory().getAllReserveTowers();

//        List<Tower> sellableTowers = Stream.concat(Arrays.stream(mainTowers), Arrays.stream(reserveTowers))
////                        .filter(tower -> tower != null && !(tower.getDescription().isEmpty()))
//                .filter(tower -> tower != null)
//                .toList();

//        System.out.println("Sellable towers" + sellableTowers);

        mainTowerList.setCellFactory(new TowerCellFactory());
        mainTowerList.setItems(FXCollections.observableArrayList(mainTowers));
        mainTowerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        reserveTowerList.setCellFactory(new TowerCellFactory());
        reserveTowerList.setItems(FXCollections.observableArrayList(reserveTowers));
        reserveTowerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void onOkayMainTower(){
//        System.out.println("m" + mainSelected);
//        System.out.println("r" + reserveSelected);
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

    @FXML
    public void onOkayReserveTower(){
//        System.out.println("main" + mainSelected);
//        System.out.println("reserve" + reserveSelected);
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
            System.out.println("Tower index: " + towerIndex);
            if (towerIndex >= 5){
                towerIndex = towerIndex - 5;
                Tower tower = reserveTowerList.getSelectionModel().getSelectedItem();
//                System.out.println(game.getInventory().getReserveTowerDescriptions(towerIndex));
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
     * When the cancel button is clicked, the Upgrade GUI is closed and the Inventory GUI is relaunched
     */
    @FXML
    public void onCancel(){
        game.closeUpgradePopup();
    }

    /**
     * When the exit button is clicked, the Upgrade GUI is closed and the Inventory GUI is relaunched
     */
    @FXML
    public void onExit(){
        game.closeUpgradePopup();
    }
}