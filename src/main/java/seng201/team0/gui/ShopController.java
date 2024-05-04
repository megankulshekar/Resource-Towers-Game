package seng201.team0.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import seng201.team0.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShopController {
    private GameEnvironment game;

    @FXML
    private Button copperButton, ironButton, goldButton, uraniumButton, diamondButton;

    @FXML
    private Label copperLabel, ironLabel, goldLabel, uraniumLabel, diamondLabel;

    @FXML
    private Button buyTowerButton;

    @FXML
    private Label towerBoughtLabel;

    @FXML
    private Label moneyLabel;

    @FXML
    private ListView sellTowerList;

    @FXML
    private Button sellTowerButton;

    @FXML
    private Label towerSoldLabel;

    @FXML
    private Button repairItemButton, upgradeXPButton, upgradeReloadSpeedButton, upgradeResourceAmountButton;

    @FXML
    private Label repairItem, upgradeXP, upgradeReloadSpeed, upgradeResourceAmount;

    @FXML
    private Button buyUpgradeButton;

    @FXML
    private Label upgradeBoughtLabel;

    @FXML
    private Button exitButton;

    private int boughtTowerIndex = -1;

    private int boughtUpgradeIndex = -1;

    private ArrayList<String> upgradesBought = new ArrayList<String>();

    public List<Button> boughtTowerButtons;

    public List<Button> boughtUpgradeButtons;

    public List<Label> upgradesLabels;

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
        boughtUpgradeButtons = List.of(repairItemButton, upgradeXPButton, upgradeReloadSpeedButton, upgradeResourceAmountButton);
        upgradesLabels = List.of(repairItem, upgradeXP, upgradeReloadSpeed, upgradeResourceAmount);

        moneyRemaining();

        for (int i = 0; i < boughtTowerButtons.size(); i++) {
            int finalI = i;
            boughtTowerButtons.get(i).setOnAction(event -> {
                boughtTowerIndex = finalI;
            });
        }

        for (int i = 0; i < boughtUpgradeButtons.size(); i++) {
            int finalI = i;
            boughtUpgradeButtons.get(i).setOnAction(event -> {
                boughtUpgradeIndex = finalI;
            });
        }

        sellTowerList.setCellFactory(new TowerCellFactory());
        sellTowerList.setItems(FXCollections.observableArrayList(game.getInventory().getMainTowers(0), game.getInventory().getMainTowers(1)));

        // Handling user input with ListViews
        // By default a user can only select one item, though we can allow multiple with
        sellTowerList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Add a listener that runs when the selection changes (and just prints it for now)
        sellTowerList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) r -> {
            System.out.println("Action: " + r);
            System.out.println("Current Selection: " + sellTowerList.getSelectionModel().getSelectedItems());
        });
    }

    public void moneyRemaining(){
        if (game.getMoney() > 0) {
            moneyLabel.setText("$" + game.getMoney() + " remaining");
        }
        else{
            moneyLabel.setText("$0 remaining");
        }
    }

    //    public void updateMoneyLabel(){
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        moneyRemaining();
//                    }});}}, 1000, 1000);
//    }

    //Reference: https://stackoverflow.com/questions/29487645/how-to-make-a-label-visible-for-a-certain-time-and-then-should-be-invisible-with
    @FXML
    public void onBuyTower(){
        if (game.getMoney() > 0) {
            if (boughtTowerIndex == 0){
                if (game.getMoney() - 7 > 0) {
                    Tower copperTower = new CopperTower();
                    game.addToInventory(copperTower, copperTower.setDescription("Copper", 11, 11));
                    game.decreaseMoney(7);
                }
                else{
                    towerBoughtLabel.setText("You do not have enough money");
                }
            } else if (boughtTowerIndex == 1) {
                if (game.getMoney() - 8 > 0) {
                    Tower ironTower = new IronTower();
                    game.addToInventory(ironTower, ironTower.setDescription("Iron", 12, 12));
                    game.decreaseMoney(8);
                }
                else{
                    towerBoughtLabel.setText("You do not have enough money");
                }
            } else if (boughtTowerIndex == 2) {
                if (game.getMoney() - 9 > 0) {
                    Tower goldTower = new GoldTower();
                    game.addToInventory(goldTower, goldTower.setDescription("Gold", 13, 13));
                    game.decreaseMoney(9);
                }
                else{
                    towerBoughtLabel.setText("You do not have enough money");
                }
            } else if (boughtTowerIndex == 3) {
                if (game.getMoney() - 12 > 0) {
                    Tower uraniumTower = new UraniumTower();
                    game.addToInventory(uraniumTower, uraniumTower.setDescription("Uranium", 15, 15));
                    game.decreaseMoney(12);
                }
                else{
                    towerBoughtLabel.setText("You do not have enough money");
                }
            } else if (boughtTowerIndex == 4) {
                if (game.getMoney() - 14 > 0) {
                    Tower diamondTower = new DiamondTower();
                    game.addToInventory(diamondTower, diamondTower.setDescription("Diamond", 16, 16));
                    game.decreaseMoney(14);
                }
                else{
                    towerBoughtLabel.setText("You do not have enough money");
                }
            }
            towerBoughtLabel.setText("Tower bought");
//            PauseTransition labelDisappear = new PauseTransition(
//                    Duration.seconds(3)
//            );
//            labelDisappear.setOnFinished(
//                    event -> towerBoughtLabel.setVisible(false)
//            );
//            labelDisappear.play();
        }
        else{
            towerBoughtLabel.setText("You do not have enough money");
        }
    }

    @FXML
    public void onSellTower(){
        towerSoldLabel.setText("Tower sold");
    }

    @FXML
    public void onBuyUpgrade(){
        upgradesBought.add(upgradesLabels.get(boughtUpgradeIndex).getText());
        upgradesBought.stream().forEach(System.out::println);
        upgradeBoughtLabel.setText("Upgrade bought");
    }

    @FXML
    public void onExit(){
        game.closeShop();
    }
}
