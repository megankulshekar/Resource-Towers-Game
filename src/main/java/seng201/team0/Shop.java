package seng201.team0;

import java.util.ArrayList;
import java.util.Collections;

public class Shop {
    ArrayList<Tower> allTowers = new ArrayList<Tower>();
    ArrayList<Item> allItems = new ArrayList<Item>();

    /**
     * Constructor
     */
    public Shop(){
        CoalTower tower1 = new CoalTower();
        CopperTower tower2 = new CopperTower();
        IronTower tower3 = new IronTower();
        GoldTower tower4 = new GoldTower();
        DiamondTower tower5 = new DiamondTower();
        UraniumTower tower6 = new UraniumTower();
        Collections.addAll(allTowers, tower1, tower2, tower3, tower4, tower5, tower6);
        RepairItem item1 = new RepairItem();
        UpgradeXPItem item2 = new UpgradeXPItem();
        UpgradeResourceAmountItem item3 = new UpgradeResourceAmountItem();
        UpgradeReloadSpeedItem item4 = new UpgradeReloadSpeedItem();
        Collections.addAll(allItems, item1, item2, item3, item4);
    }

    /**
     * Buys purchasable by decreasing user's money by the buying price
     * and adding purchasable to user's inventory
     * @param purchasable Purchasable being bought
     * @param game The game environment
     */
    public void buy(Purchasable purchasable, GameEnvironment game){
        game.decreaseMoney(purchasable.getBuyingPrice());
        game.inventory.add(purchasable);
    }

    /**
     * Sells purchasable by increasing user's money by the selling price
     * and removing purchasable from user's inventory
     * @param purchasable Purchasable being sold
     * @param game The game environment
     */
    public void sell(Purchasable purchasable, GameEnvironment game){
        game.increaseMoney(purchasable.getSellingPrice());
        game.inventory.remove(purchasable);
    }
}
