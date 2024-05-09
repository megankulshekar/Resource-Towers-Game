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
    private Label towerChosenLabel;

    @FXML
    private ListView<Tower>sellTowerList;

    @FXML
    private MenuButton upgradesMenuButton;

    @FXML
    private MenuItem repairItem, upgradeXPItem, upgradeReloadSpeedItem, upgradeResourceAmountItem;

    @FXML
    private Button okayTowerButton;

    @FXML
    private Button okayButton;

    @FXML
    private Button exitButton;

    private int indexOfUpgradeItem;

    private String upgradeDescription;

    private String originalDescription;

    private String newDescription;

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

        List<Tower> sellableTowers = Stream.concat(Arrays.stream(mainTowers), Arrays.stream(reserveTowers))
//                        .filter(tower -> tower != null && !(tower.getDescription().isEmpty()))
                .filter(tower -> tower != null)
                .toList();

        System.out.println("Sellable towers" + sellableTowers);

        sellTowerList.setCellFactory(new TowerCellFactory());
        sellTowerList.setItems(FXCollections.observableArrayList(sellableTowers));
        sellTowerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void onOkayTower(){
        int descriptionIndex = sellTowerList.getSelectionModel().getSelectedIndex();
        System.out.println("Description index: " + descriptionIndex);
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
        if (indexOfUpgradeItem != -1){
            int towerIndexValue = game.getInventory().getTowerIndexValue();
            if (towerIndexValue < 4){
                Tower tower = game.getInventory().getMainTowers(towerIndexValue);
                game.getInventory().upgradeTower(indexOfUpgradeItem, tower);
                originalDescription = game.getInventory().getMainTowerDescriptions(towerIndexValue);
                newDescription = originalDescription.concat("\n\n" + upgradeDescription);
                game.getInventory().setMainTowerDescriptions(towerIndexValue, newDescription);
            }
            else if (towerIndexValue >= 4){
                towerIndexValue = towerIndexValue - 4;
                Tower tower = game.getInventory().getReserveTowers(towerIndexValue);
                game.getInventory().upgradeTower(indexOfUpgradeItem, tower);
                originalDescription = game.getInventory().getReserveTowerDescriptions(towerIndexValue);
                newDescription = originalDescription.concat("\n\n" + upgradeDescription);
                game.getInventory().setReserveTowerDescriptions(towerIndexValue, newDescription);
            }
            //game.getInventory().getUpgradesBought().remove(index);
            messageLabel.setText("Success! Upgrade applied!");
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