package seng201.team0.services;

import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Tower;

/**
 * Class for providing services for the upgrade controller
 */
public class UpgradeService {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * Constructor
     * @param game The game environment
     */
    public UpgradeService(GameEnvironment game){
        this.game = game;
    }

    /**
     * Adds upgrade to tower and corresponding description if upgrade exists in player's inventory
     * @param towerIndex Location of tower in main or reserve tower array
     * @param towerDescriptionIndex Signifies main or reserve tower description
     * @param tower Type of tower
     * @param indexOfUpgradeItem Location of upgrade in upgrade array list
     * @param upgradeDescription Description of upgrade
     * @return Success message
     */
    public String applyUpgrade(int towerIndex, int towerDescriptionIndex, Tower tower, int indexOfUpgradeItem, String upgradeDescription){
        if (towerDescriptionIndex == 1){
            game.getInventory().upgradeTower(indexOfUpgradeItem, tower);
            String originalDescription = game.getInventory().getReserveTowerDescriptions(towerIndex);
            String newDescription = originalDescription.concat("\n\n" + upgradeDescription);
            game.getInventory().setReserveTowerDescriptions(towerIndex, newDescription);
            return "Success! Upgrade applied!";
        }
        else if (towerDescriptionIndex == 2){
            game.getInventory().upgradeTower(indexOfUpgradeItem, tower);
            String originalDescription = game.getInventory().getMainTowerDescriptions(towerIndex);
            String newDescription = originalDescription.concat("\n\n" + upgradeDescription);
            game.getInventory().setMainTowerDescriptions(towerIndex, newDescription);
            return "Success! Upgrade applied!";
        }
        return "The tower does not exist.";
    }

}
