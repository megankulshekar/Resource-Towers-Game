package seng201.team0.gui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import seng201.team0.models.*;

import java.util.ArrayList;
import java.util.List;

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
    private ListView sellTowerList;

    /**
     * Selling tower button and corresponding label
     */
    @FXML
    private Button sellTowerButton;

    @FXML
    private Label towerSoldLabel;

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
     * Stores upgrade descriptions for use in other classes
     */
    private ArrayList<String> upgradesBought = new ArrayList<String>();

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
     * When certain buttons are clicked, index value of that button is stored in a variable for later use
     */
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

        //the Uranium and Diamond towers cannot be bought until after 8 rounds have been passed
        uraniumButton.setDisable(true);
        diamondButton.setDisable(true);
        uraniumCostLabel.setVisible(false);
        diamondCostLabel.setVisible(false);

//        sellTowerList.setCellFactory(new TowerCellFactory());
//        sellTowerList.setItems(FXCollections.observableArrayList(game.getInventory().getMainTowers(0), game.getInventory().getMainTowers(1)));
//
//        // Handling user input with ListViews
//        // By default a user can only select one item, though we can allow multiple with
//        sellTowerList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        // Add a listener that runs when the selection changes (and just prints it for now)
//        sellTowerList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Tower>) r -> {
//            System.out.println("Action: " + r);
//            System.out.println("Current Selection: " + sellTowerList.getSelectionModel().getSelectedItems());
//        });
    }

    /**
     * Function to update the display of the player's money
     */
    //Reference for use of Timeline: https://stackoverflow.com/questions/65252152/how-to-update-a-label-continually-in-javafx
    public void updateMoneyLabel(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), actionEvent -> moneyRemaining()));
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
     * If the player has enough money, allows the player to buy the desired tower and displays a success message
     * Else, does not allow the player to buy the tower and instead displays a message saying there is not enough money
     */
    @FXML
    public void onBuyTower(){
        if (game.getMoney() > 0) {
            if (boughtTowerIndex == 0 && game.getMoney() - 7 > 0){
                    Tower copperTower = new CopperTower();
                    game.addToInventory(copperTower, copperTower.setDescription("Copper", 11, 11));
                    game.decreaseMoney(7);
                    towerBoughtLabel.setText("Tower bought");
            }
            else if (boughtTowerIndex == 1 && game.getMoney() - 8 > 0) {
                Tower ironTower = new IronTower();
                game.addToInventory(ironTower, ironTower.setDescription("Iron", 12, 12));
                game.decreaseMoney(8);
                towerBoughtLabel.setText("Tower bought");
            }
            else if (boughtTowerIndex == 2 && game.getMoney() - 9 > 0) {
                Tower goldTower = new GoldTower();
                game.addToInventory(goldTower, goldTower.setDescription("Gold", 13, 13));
                game.decreaseMoney(9);
                towerBoughtLabel.setText("Tower bought");
            }
            else if (boughtTowerIndex == 3 && game.getMoney() - 12 > 0) {
                Tower uraniumTower = new UraniumTower();
                game.addToInventory(uraniumTower, uraniumTower.setDescription("Uranium", 15, 15));
                game.decreaseMoney(12);
                towerBoughtLabel.setText("Tower bought");
            }
            else if (boughtTowerIndex == 4 && game.getMoney() - 14 > 0) {
                Tower diamondTower = new DiamondTower();
                game.addToInventory(diamondTower, diamondTower.setDescription("Diamond", 16, 16));
                game.decreaseMoney(14);
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
     * When the sell tower button is clicked, displays that the tower has been sold
     */
    @FXML
    public void onSellTower(){
        towerSoldLabel.setText("Tower sold");
        setLabelVisibility(towerSoldLabel);
    }

    /**
     * When an upgrade is bought, adds the upgrade to the Array List for future access
     */
    @FXML
    public void onBuyUpgrade(){
        upgradesBought.add(upgradesLabels.get(boughtUpgradeIndex).getText());
        //System.out.println(upgradesBought);
        upgradeBoughtLabel.setText("Upgrade bought");
    }

    /**
     * Closes the shop GUI and launches the round GUI
     */
    @FXML
    public void onExit(){
        game.closeShop();
    }
}