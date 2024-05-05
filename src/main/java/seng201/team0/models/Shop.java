package seng201.team0.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing the shop in game
 */
public class Shop {
    /**
     * List of all towers available in the shop
     */
    private List<Tower> allTowers = new ArrayList<Tower>();

    /**
     * List of all items available in the shop
     */
    private List<Item> allItems = new ArrayList<Item>();

    public ObservableList<String> getItems() {
        return items;
    }

    private ObservableList<String> items = FXCollections.observableArrayList("Placeholder");

    /**
     * Constructor
     */
    public Shop(){
        Tower tower1 = new CoalTower();
        Tower tower2 = new CopperTower();
        Tower tower3 = new IronTower();
        Tower tower4 = new GoldTower();
        Tower tower5 = new DiamondTower();
        Tower tower6 = new UraniumTower();
        Collections.addAll(allTowers, tower1, tower2, tower3, tower4, tower5, tower6);
        Item item1 = new RepairItem();
        Item item2 = new UpgradeXPItem();
        Item item3 = new UpgradeResourceAmountItem();
        Item item4 = new UpgradeReloadSpeedItem();
        Collections.addAll(allItems, item1, item2, item3, item4);
    }

    /**
     * Buys purchasable by decreasing user's money by the buying price
     * and adding purchasable to user's inventory
     * @param purchasable Purchasable being bought
     * @param game The game environment
     */
    public void buy(Purchasable purchasable, GameEnvironment game, String description){
        game.decreaseMoney(purchasable.getBuyingPrice());
        game.addToInventory(purchasable, description);
    }
}
