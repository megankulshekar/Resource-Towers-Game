package seng201.team0.models;

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
     * Buys tower by decreasing user's money by the buying price
     * and adding tower and corresponding description to user's inventory
     * @param purchasable Tower being bought
     * @param game The game environment
     * @param description Tower description
     */
    public void buy(Purchasable purchasable, GameEnvironment game, String description){
        game.decreaseMoney(purchasable.getBuyingPrice());
        game.addToInventory(purchasable, description);
    }

    /**
     * Buys upgrade by decreasing user's money by the buying price
     * and adding upgrade and corresponding description to user's inventory
     * @param item Upgrade
     * @param game The game environment
     * @param description Upgrade description
     */
    public void buyUpgrade(Item item, GameEnvironment game, String description) {
        game.decreaseMoney(item.getBuyingPrice());
        game.addToUpgrades(item, description);
    }

    /**
     * Sells tower by increasing user's money by the selling price
     * and removing tower and tower description from user's inventory
     * @param purchasable Tower being sold
     * @param game The game environment
     */
    public void sell(Purchasable purchasable, GameEnvironment game){
        game.increaseMoney(purchasable.getSellingPrice());
        game.removeFromInventory(purchasable);
    }

    /**
     * Sells upgrade by increasing user's money by the selling price
     * and removing upgrade and upgrade description from user's inventory
     * @param item Upgrade
     * @param index Location of item description
     * @param game The game environment
     */
    public void sellUpgrade(Item item, int index, GameEnvironment game) {
        game.increaseMoney(item.getSellingPrice());
        game.removeFromUpgrades(item, index);
    }
}