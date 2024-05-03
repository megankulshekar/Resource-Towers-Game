package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import seng201.team0.models.*;

import java.util.List;

public class ShopController {
    private GameEnvironment game;

    @FXML
    private Button copperButton, ironButton, goldButton, uraniumButton, diamondButton;

    @FXML
    private Label copperLabel, ironLabel, goldLabel, uraniumLabel, diamondLabel;

    @FXML
    private MenuButton upgradeMenu;

    @FXML
    private Button buyTowerButton;

    @FXML
    private Button exitButton;

    private int boughtTowerIndex = -1;

    public List<Button> boughtTowerButtons;

    public ShopController(GameEnvironment game) {
        this.game = game;
    }

    public void initialize(){
        Tower copperTower = new CopperTower();
        Tower ironTower = new IronTower();
        Tower goldTower = new GoldTower();
        Tower uraniumTower = new UraniumTower();
        Tower diamondTower = new DiamondTower();
        copperLabel.setText(copperTower.setDescription("Copper", 11, 11));
        ironLabel.setText(ironTower.setDescription("Iron", 12, 12));
        goldLabel.setText(goldTower.setDescription("Gold", 13, 13));
        uraniumLabel.setText(uraniumTower.setDescription("Uranium", 15, 15));
        diamondLabel.setText(diamondTower.setDescription("Diamond", 16, 16));

        boughtTowerButtons = List.of(copperButton, ironButton, goldButton, uraniumButton, diamondButton);

        for (int i = 0; i < boughtTowerButtons.size(); i++) {
            int finalI = i;
            boughtTowerButtons.get(i).setOnAction(event -> {
                boughtTowerIndex = finalI;
            });
        }
    }

    @FXML
    public void onBuyTower(){
        if (boughtTowerIndex == 0){
            Tower copperTower = new CopperTower();
            game.addToInventory(copperTower, copperTower.setDescription("Copper", 11, 11));
        }
        else if (boughtTowerIndex == 1){
            Tower ironTower = new IronTower();
            game.addToInventory(ironTower, ironTower.setDescription("Iron", 12, 12));
        }
        else if (boughtTowerIndex == 2){
            Tower goldTower = new GoldTower();
            game.addToInventory(goldTower, goldTower.setDescription("Gold", 13, 13));
        }
        else if (boughtTowerIndex == 3){
            Tower uraniumTower = new UraniumTower();
            game.addToInventory(uraniumTower, uraniumTower.setDescription("Uranium", 15, 15));
        }
        else if (boughtTowerIndex == 4){
            Tower diamondTower = new DiamondTower();
            game.addToInventory(diamondTower, diamondTower.setDescription("Diamond", 16, 16));
        }
    }

    @FXML
    public void onExit(){
        game.closeShop();
    }
}
