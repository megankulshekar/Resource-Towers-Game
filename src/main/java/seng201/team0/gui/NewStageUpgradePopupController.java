package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Tower;


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
    private MenuButton upgradesMenuButton;

    @FXML
    private MenuItem repairItem, upgradeXPItem, upgradeReloadSpeedItem, upgradeResourceAmountItem;

    @FXML
    private Button exitButton;

    private int indexOfUpgradeItem;

    private String description;

    /**
     * Constructor
     * @param game The game environment
     */
    public NewStageUpgradePopupController(GameEnvironment game) {
        this.game = game;
    }

    /**
     * When the repair item button is chosen from the drop-down menu, finds the index of the repair item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onRepair(){
        description = repairItem.getText();
        upgradeChosenLabel.setText("You have selected: " + description);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(description);
    }

    /**
     * When the upgrade XP button is chosen from the drop-down menu, finds the index of the upgrade XP item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onUpgradeXP(){
        description = upgradeXPItem.getText();
        upgradeChosenLabel.setText("You have selected: " + description);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(description);
    }

    /**
     * When the upgrade reload speed button is chosen from the drop-down menu, finds the index of the upgrade reload speed item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onUpgradeReloadSpeed(){
        description = upgradeReloadSpeedItem.getText();
        upgradeChosenLabel.setText("You have selected: " + description);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(description);
    }

    /**
     * When the upgrade resource amount button is chosen from the drop-down menu, finds the index of the upgrade resource amount item within the upgrades list and sets that value to a variable
     */
    @FXML
    public void onUpgradeResourceAmount(){
        description = upgradeResourceAmountItem.getText();
        upgradeChosenLabel.setText("You have selected: " + description);
        indexOfUpgradeItem = game.getInventory().getUpgradesBought().indexOf(description);
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
            }
            else if (towerIndexValue >= 4){
                towerIndexValue = towerIndexValue - 4;
                Tower tower = game.getInventory().getReserveTowers(towerIndexValue);
                game.getInventory().upgradeTower(indexOfUpgradeItem, tower);
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