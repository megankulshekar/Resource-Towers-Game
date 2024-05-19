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
import seng201.team0.services.ShopService;

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
     * The service class for the controller
     */
    private ShopService shopService;

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
     * List created for tower type buttons to keep track of the index selected by the user
     */
    public List<Button> boughtTowerButtons;

    /**
     * List created for upgrade buttons to keep track of the index selected by the user
     */
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
        shopService = new ShopService(this.game);
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
     * @param items
     * @param button
     * @param <T>
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
     * If the player has enough money, allows the player to buy the desired tower and displays a success message
     * Else, does not allow the player to buy the tower and instead displays a message saying there is not enough money
     */
    @FXML
    public void onBuyTower(){
        String message = shopService.buyTower(boughtTowerIndex);
        towerBoughtLabel.setText(message);
        setLabelVisibility(towerBoughtLabel);
    }

    /**
     * When the sell tower button is clicked, displays that the main tower has been sold
     */
    @FXML
    public void onSellMainTower(){
        for (Integer index : sellMainTowerList.getSelectionModel().getSelectedIndices()){
            String message = shopService.sellMainTower(index);
            mainTowerSoldLabel.setText(message);
            setLabelVisibility(mainTowerSoldLabel);
        }
    }

    /**
     * When the sell tower button is clicked, displays that the reserve tower has been sold
     */
    @FXML
    public void onSellReserveTower(){
        for (Integer index : sellReserveTowerList.getSelectionModel().getSelectedIndices()) {
            String message = shopService.sellReserveTower(index);
            reserveTowerSoldLabel.setText(message);
            setLabelVisibility(reserveTowerSoldLabel);
        }
    }

    /**
     * When an upgrade is bought, adds the upgrade to the Items array list for future access
     */
    @FXML
    public void onBuyUpgrade(){
        String upgradesLabel;
        if (boughtUpgradeIndex == -1) {
            upgradesLabel = "";
        } else {
            upgradesLabel = upgradesLabels.get(boughtUpgradeIndex).getText();
        }
        String message = shopService.buyUpgrade(boughtUpgradeIndex, upgradesLabel);
        upgradeBoughtLabel.setText(message);
        setLabelVisibility(upgradeBoughtLabel);
    }

    /**
     * When the sell upgrade button is clicked, displays that the upgrade has been sold
     */
    @FXML
    public void onSellUpgrade(){
        for (Integer index : sellUpgradeList.getSelectionModel().getSelectedIndices()) {
            String message = shopService.sellUpgrade(index);
            upgradeSoldLabel.setText(message);
            setLabelVisibility(upgradeSoldLabel);
        }
    }

    /**
     * Closes the shop GUI and launches the round GUI
     */
    @FXML
    public void onExit(){
        game.closeShop();
    }
}