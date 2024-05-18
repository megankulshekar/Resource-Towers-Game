package seng201.team0.services;

import seng201.team0.models.*;

public class StartScreenService {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * Constructor
     * @param game The game environment
     */
    public StartScreenService(GameEnvironment game){
        this.game = game;
    }

    /**
     * Helper function to check if name has no special characters
     * @param name Player's name
     * @return true if name is valid or false if name is not valid
     */
    public Boolean validCharacters(String name){
        char[] characters = name.toCharArray();
        for (char c : characters) {
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates the type of tower depending on which one the player has selected
     * @param towerType Index of button clicked
     * @return Coal tower with specific attributes
     */
    public Tower towerCreation(int towerType){
        Tower coalTower = new CoalTower();
        if (towerType == 0){
            coalTower.increaseResourceAmount(1);
            coalTower.decreaseReloadSpeed(-1);
        }
        else if (towerType == 1){
            coalTower.increaseResourceAmount(0);
            coalTower.decreaseReloadSpeed(0);
        }
        else if (towerType == 2){
            coalTower.increaseResourceAmount(-1);
            coalTower.decreaseReloadSpeed(1);
        }
        return coalTower;
    }
}