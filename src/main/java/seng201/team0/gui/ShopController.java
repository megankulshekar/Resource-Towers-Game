package seng201.team0.gui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import seng201.team0.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class for controlling the shop GUI
 */
public class ShopController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    /**
     * Buttons and labels for each of the tower types
     */
    @FXML
    private Button copperButton, ironButton, goldButton, uraniumButton, diamondButton;

    @FXML
    private Label copperLabel, ironLabel, goldLabel, uraniumLabel, diamondLabel;

    /**
     * Label to display player's money
     */
    @FXML
    private Label moneyLabel;

    /**
     * Buying tower button and corresponding label
     */
    @FXML
    private Button buyTowerButton;

    @FXML
    private Label towerBoughtLabel;

    /**
     * List view of player's tower inventory
     */
    @FXML
    private ListView <Tower>sellMainTowerList;

    @FXML
    private ListView <Tower>sellReserveTowerList;

    /**
     * Selling tower button and corresponding label
     */
    @FXML
    private Button sellMainTowerButton;

    @FXML
    private Button sellReserveTowerButton;

    @FXML
    private Label mainTowerSoldLabel;

    @FXML
    private Label reserveTowerSoldLabel;

    /**
     * Buttons and labels for each of the upgrades available
     */
    @FXML
    private Button repairItemButton, upgradeXPButton, upgradeReloadSpeedButton, upgradeResourceAmountButton;

    @FXML
    private Label repairItem, upgradeXP, upgradeReloadSpeed, upgradeResourceAmount;

    /**
     * Upgrading button and corresponding label
     */
    @FXML
    private Button buyUpgradeButton;

    @FXML
    private Label upgradeBoughtLabel;

    @FXML
    private ListView<String> sellUpgradeList;

    @FXML
    private Button sellUpgradeButton;

    @FXML
    private Label upgradeSoldLabel;

    /**
     * Physical button to exit the shop GUI
     */
    @FXML
    private Button exitButton;

    /**
     * Identification for uranium and diamond cost labels
     */
    @FXML
    private Label uraniumCostLabel, diamondCostLabel;

    /**
     * Initializes indices of tower types and upgrade selection button to -1
     */
    private int boughtTowerIndex = -1;

    private int boughtUpgradeIndex = -1;

    /**
     * Lists are created for tower type and upgrade buttons to keep track of the index selected by the user
     */
    public List<Button> boughtTowerButtons;

    public List<Button> boughtUpgradeButtons;

    /**
     * List of upgrades labels created to add a description to the array of descriptions by index value
     */
    public List<Label> upgradesLabels;

    /**
     * Constructor
     * @param game The game environment
     */
    public ShopController(GameEnvironment game) {
        this.game = game;
    }

    /**
     * Creates an instance of each tower and initializes the buttons and output text
     * Selling items buttons cannot initially be clicked unless there is an item that can be sold
     */
    public void initialize(){
        Tower copperTower = new CopperTower();
        Tower ironTower = new IronTower();
        Tower goldTower = new GoldTower();
        Tower uraniumTower = new UraniumTower();
        Tower diamondTower = new DiamondTower();
        copperLabel.setText(copperTower.getDescription());
        ironLabel.setText(ironTower.getDescription());
        goldLabel.setText(goldTower.getDescription());
        uraniumLabel.setText(uraniumTower.getDescription());
        diamondLabel.setText(diamondTower.getDescription());

        boughtTowerButtons = List.of(copperButton, ironButton, goldButton, uraniumButton, diamondButton);
        boughtUpgradeButtons = List.of(repairItemButton, upgradeXPButton, upgradeReloadSpeedButton, upgradeResourceAmountButton);
        upgradesLabels = List.of(repairItem, upgradeXP, upgradeReloadSpeed, upgradeResourceAmount);

        specialTowersVisibility();

        //updates the player's remaining money periodically
        updateMoneyLabel();

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

        Tower[] mainTowers = game.getInventory().getAllMainTowers();
        Tower[] reserveTowers = game.getInventory().getAllReserveTowers();

        sellMainTowerList.setCellFactory(new TowerCellFactory());
        sellMainTowerList.setItems(FXCollections.observableArrayList(mainTowers));

        sellReserveTowerList.setCellFactory(new TowerCellFactory());
        sellReserveTowerList.setItems(FXCollections.observableArrayList(reserveTowers));

        List<String> upgrades = game.getInventory().getUpgradesBought();
        sellUpgradeList.setCellFactory(new UpgradeCellFactory());
        sellUpgradeList.setItems(FXCollections.observableArrayList(upgrades));

        sellMainTowerButton.setDisable(true);
        sellReserveTowerButton.setDisable(true);
        sellUpgradeButton.setDisable(true);

        disableButtons(sellMainTowerList, sellMainTowerButton);
        disableButtons(sellReserveTowerList, sellReserveTowerButton);
        disableButtons(sellUpgradeList, sellUpgradeButton);
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
     * The Uranium and Diamond towers cannot be bought until after 8 rounds have been successfully passed
     */
    public void specialTowersVisibility(){
        if (game.getCurrentRoundIndex() < 8){
            uraniumButton.setDisable(true);
            diamondButton.setDisable(true);
//            uraniumCostLabel.setVisible(false);
//            diamondCostLabel.setVisible(false);
        }
    }

    /**
     * Function to update the display of the player's money
     */
    //Reference for use of Timeline: https://stackoverflow.com/questions/65252152/how-to-update-a-label-continually-in-javafx
    public void updateMoneyLabel(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), actionEvent -> moneyRemaining()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Helper function for displaying the amount of money the player has
     */
    public void moneyRemaining(){
        if (game.getMoney() > 0) {
            moneyLabel.setText("$" + game.getMoney() + " remaining");
        }
        else{
            moneyLabel.setText("$0 remaining");
        }
    }

    /**
     * Displays the visibility of labels for 3 seconds at a time when the buy, sell, or upgrade button is clicked
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
     * If the player has enough money, allows the player to buy the desired tower and displays a success message
     * Else, does not allow the player to buy the tower and instead displays a message saying there is not enough money
     */
    @FXML
    public void onBuyTower(){
        if (game.getMoney() >= 0) {
            if (boughtTowerIndex == 0 && game.getMoney() - 7 >= 0){
                Tower copperTower = new CopperTower();
                game.buyTowerInShop(copperTower, game, copperTower.getDescription());
                towerBoughtLabel.setText("Tower bought");
            }
            else if (boughtTowerIndex == 1 && game.getMoney() - 8 >= 0) {
                Tower ironTower = new IronTower();
                game.buyTowerInShop(ironTower, game, ironTower.getDescription());
                towerBoughtLabel.setText("Tower bought");
            }
            else if (boughtTowerIndex == 2 && game.getMoney() - 9 >= 0) {
                Tower goldTower = new GoldTower();
                game.buyTowerInShop(goldTower, game, goldTower.getDescription());
                towerBoughtLabel.setText("Tower bought");
            }
            else if (boughtTowerIndex == 3 && game.getMoney() - 12 >= 0) {
                Tower uraniumTower = new UraniumTower();
                game.buyTowerInShop(uraniumTower, game, uraniumTower.getDescription());
                towerBoughtLabel.setText("Tower bought");
            }
            else if (boughtTowerIndex == 4 && game.getMoney() - 14 >= 0) {
                Tower diamondTower = new DiamondTower();
                game.buyTowerInShop(diamondTower, game, diamondTower.getDescription());
                towerBoughtLabel.setText("Tower bought");
            }
            else{
                towerBoughtLabel.setText("You do not have enough money");
            }
            setLabelVisibility(towerBoughtLabel);
        }
        else{
            towerBoughtLabel.setText("You do not have enough money");
        }
    }

    /**
     * When the sell tower button is clicked, displays that the main tower has been sold
     */
    @FXML
    public void onSellMainTower(){
        for (Integer index : sellMainTowerList.getSelectionModel().getSelectedIndices()){
            System.out.println(index);
            Tower tower = game.getInventory().getMainTowers(index);
            game.sellTowerInShop(tower, game);
        }
        mainTowerSoldLabel.setText("Main Tower Sold");
        setLabelVisibility(mainTowerSoldLabel);
    }

    /**
     * When the sell tower button is clicked, displays that the reserve tower has been sold
     */
    @FXML
    public void onSellReserveTower(){
        for (Integer index : sellReserveTowerList.getSelectionModel().getSelectedIndices()) {
            System.out.println(index);
            Tower tower = game.getInventory().getReserveTowers(index);
            game.sellTowerInShop(tower, game);
        }
        reserveTowerSoldLabel.setText("Reserve Tower Sold");
        setLabelVisibility(reserveTowerSoldLabel);
    }

    /**
     * When an upgrade is bought, adds the upgrade to the Items array list for future access
     */
    @FXML
    public void onBuyUpgrade(){
        if (game.getMoney() >= 0) {
            if (boughtUpgradeIndex == 0 && game.getMoney() - 15 >= 0){
                Item repairItem = new RepairItem();
                game.buyUpgrades(repairItem, game, upgradesLabels.get(boughtUpgradeIndex).getText());
                upgradeBoughtLabel.setText("Upgrade bought");
            }
            else if (boughtUpgradeIndex == 1 && game.getMoney() - 15 >= 0) {
                Item upgradeXPItem = new UpgradeXPItem();
                game.buyUpgrades(upgradeXPItem, game, upgradesLabels.get(boughtUpgradeIndex).getText());
                upgradeBoughtLabel.setText("Upgrade bought");
            }
            else if (boughtUpgradeIndex == 2 && game.getMoney() - 15 >= 0) {
                Item upgradeReloadSpeedItem = new UpgradeReloadSpeedItem();
                game.buyUpgrades(upgradeReloadSpeedItem, game, upgradesLabels.get(boughtUpgradeIndex).getText());
                upgradeBoughtLabel.setText("Upgrade bought");
            }
            else if (boughtUpgradeIndex == 3 && game.getMoney() - 15 >= 0) {
                Item upgradeResourceAmountItem = new UpgradeResourceAmountItem();
                game.buyUpgrades(upgradeResourceAmountItem, game, upgradesLabels.get(boughtUpgradeIndex).getText());
                upgradeBoughtLabel.setText("Upgrade bought");
            }
            else{
                upgradeBoughtLabel.setText("You do not have enough money");
            }
            setLabelVisibility(upgradeBoughtLabel);
        }
        else{
            upgradeBoughtLabel.setText("You do not have enough money");
        }
    }

    /**
     * When the sell upgrade button is clicked, displays that the upgrade has been sold
     */
    @FXML
    public void onSellUpgrade(){
        for (Integer index : sellUpgradeList.getSelectionModel().getSelectedIndices()){
            Item item = game.getInventory().getItems().get(index);
            game.sellUpgrades(item, index, game);
        }
        upgradeSoldLabel.setText("Upgrade sold");
        setLabelVisibility(upgradeSoldLabel);
    }

    /**
     * Closes the shop GUI and launches the round GUI
     */
    @FXML
    public void onExit(){
        game.closeShop();
    }
}