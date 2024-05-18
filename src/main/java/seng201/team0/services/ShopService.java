package seng201.team0.services;

import seng201.team0.models.*;

/**
 * Class for providing services to shop controller class
 */
public class ShopService {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * Constructor
     * @param game The game environment
     */
    public ShopService(GameEnvironment game){
        this.game = game;
    }

    /**
     * Buying tower on backend
     * @param boughtTowerIndex Location of which tower will be bought
     * @return Success message
     */
    public String buyTower(int boughtTowerIndex){
        if (game.getMoney() >= 0) {
            Tower tower;
            int cost;
            switch (boughtTowerIndex) {
                case 0:
                    tower = new CopperTower();
                    cost = 7;
                    break;
                case 1:
                    tower = new IronTower();
                    cost = 8;
                    break;
                case 2:
                    tower = new GoldTower();
                    cost = 9;
                    break;
                case 3:
                    tower = new UraniumTower();
                    cost = 12;
                    break;
                case 4:
                    tower = new DiamondTower();
                    cost = 14;
                    break;
                default:
                    return "You haven't selected a valid tower";
            }
            if (game.getMoney() - cost >= 0) {
                game.buyTowerInShop(tower, game, tower.getDescription());
                return "Tower bought";
            } else {
                return "You do not have enough money";
            }
        }
        else{
            return "You do not have enough money";
        }
    }

    /**
     * Selling main tower on backend
     * @param index Location of main tower selected in list
     * @return Success message
     */
    public String sellMainTower(int index){
        if (index >= 0 && index <= 4) {
            Tower[] mainTowers = game.getInventory().getAllMainTowers();
            int mainContainsCount = 0;

            for (Tower mainTower : mainTowers) {
                if (mainTower != null) {
                    mainContainsCount++;
                }
            }
            if (mainContainsCount > 1) {
                Tower tower = game.getInventory().getMainTowers(index);
                game.sellTowerInShop(tower, game);
                return "Main Tower Sold";
            } else {
                return "Only one tower left! You cannot sell this tower.";
            }
        }
        else{
            return "Index value does not exist";
        }
    }

    /**
     * Selling reserve tower on backend
     * @param index Location of reserve tower selected in list
     * @return Success message
     */
    public String sellReserveTower(int index){
        if (index >= 0 && index <= 4) {
            Tower tower = game.getInventory().getReserveTowers(index);
            game.sellTowerInShop(tower, game);
            return "Reserve Tower Sold";
        }
        else{
            return "Index value does not exist";
        }
    }

    /**
     * Buying upgrade on backend
     * @param boughtUpgradeIndex Location of upgrade selected in list
     * @param upgradesLabel Corresponding description of upgrade
     * @return Success message
     */
    public String buyUpgrade(int boughtUpgradeIndex, String upgradesLabel){
        if (game.getMoney() >= 15){
            Item item;
            switch (boughtUpgradeIndex) {
                case 0:
                    item = new RepairItem();
                    break;
                case 1:
                    item = new UpgradeXPItem();
                    break;
                case 2:
                    item = new UpgradeReloadSpeedItem();
                    break;
                case 3:
                    item = new UpgradeResourceAmountItem();
                    break;
                default:
                    return "You haven't selected an upgrade";
            }
            game.buyUpgrades(item, game, upgradesLabel);
            return "Upgrade bought";
            }
        else{
            return "You do not have enough money";
        }
    }

    /**
     * Selling upgrade on backend
     * @param index Location of upgrade selected in list
     * @return Success message
     */
    public String sellUpgrade(int index){
        try{
            Item item = game.getInventory().getItems().get(index);
            game.sellUpgrades(item, index, game);
            return "Upgrade sold";
        }
        catch (IndexOutOfBoundsException e){
            return "Index value does not exist";
        }
    }
}
