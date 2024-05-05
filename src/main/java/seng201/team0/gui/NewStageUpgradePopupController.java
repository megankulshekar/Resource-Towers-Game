package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import seng201.team0.models.GameEnvironment;

import java.util.List;

public class NewStageUpgradePopupController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    @FXML
    private Label messageLabel;

    @FXML
    private MenuItem repairItem, upgradeXPItem, upgradeReloadSpeedItem, upgradeResourceAmountItem;

    private int upgradesListIndex = -1;

    private int index;

    public List<MenuItem> upgradesList;

    /**
     * Constructor
     * @param game The game environment
     */
    public NewStageUpgradePopupController(GameEnvironment game) {
        this.game = game;
    }

    public void initialize(){
        upgradesList = List.of(repairItem, upgradeXPItem, upgradeReloadSpeedItem, upgradeResourceAmountItem);

        for (int i = 0; i < upgradesList.size(); i++) {
            int finalI = i;
            upgradesList.get(i).setOnAction(event -> {
                upgradesListIndex = finalI;
            });
        }
    }
    
    @FXML
    public void onOkay(){
        String description = upgradesList.get(upgradesListIndex).getText();
        index = game.getInventory().getUpgradesBought().indexOf(description);
        if (index != -1){
            game.getInventory().getUpgradesBought().remove(index);
            messageLabel.setText("Success! Upgrade applied!");
        }
        else if (index == -1){
            messageLabel.setText("Sorry! You do not have that upgrade!");
        }
    }

    @FXML
    public void onCancel(){
        game.closeUpgradePopup();
    }

}