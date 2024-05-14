package seng201.team0.services;

import seng201.team0.models.*;

/**
 * Class for providing services to inventory controller class
 */
public class InventoryService {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * Constructor
     * @param game The game environment
     */
    public InventoryService(GameEnvironment game){
        this.game = game;
    }

    /**
     * Selecting towers to swap based on amount in each main and reserve tower arrays
     * @param game The game environment
     * @param mainTowerIndex Location of main tower
     * @param reserveTowerIndex Location of reserve tower
     * @return
     */
    public boolean selectTowers(GameEnvironment game, int mainTowerIndex, int reserveTowerIndex){
        Tower[] mainTowers = game.getInventory().getAllMainTowers();
        Tower[] reserveTowers = game.getInventory().getAllReserveTowers();

        int mainContainsCount = 0;
        int reserveContainsCount = 0;

        for (Tower mainTower : mainTowers){
            if (mainTower != null){
                mainContainsCount++;
            }
        }
        for (Tower reserveTower : reserveTowers){
            if (reserveTower != null){
                reserveContainsCount++;
            }
        }

        Tower mainTower = game.getInventory().getMainTowers(mainTowerIndex);
        Tower reserveTower = game.getInventory().getReserveTowers(reserveTowerIndex);

        return mainContainsCount > 1 || mainContainsCount == 1 && (mainTower == null && reserveTower != null);
    }
}