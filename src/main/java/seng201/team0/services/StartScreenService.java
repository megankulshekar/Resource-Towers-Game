package seng201.team0.services;

import seng201.team0.models.*;

/**
 * Class for providing services for the start screen controller
 */
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
     * Creates description of specific coal tower chosen
     * @param towerChosen Which tower is chosen
     */
    public String towerDescriptionCreation(int towerChosen){
        Tower coalTower = new CoalTower();
        if (towerChosen == 0){
            coalTower.increaseResourceAmount(1);
            coalTower.decreaseReloadSpeed(-1);
            return coalTower.getDescription();
        }
        else if (towerChosen == 1){
            return coalTower.getDescription();
        }
        else if (towerChosen == 2){
            coalTower.increaseResourceAmount(-1);
            coalTower.decreaseReloadSpeed(1);
            return coalTower.getDescription();
        }
        return null;
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

    /**
     * Creates and adds selected tower to player's inventory
     * @param selectedDescriptions Array of tower descriptions selected
     */
    public void addingTower(String[] selectedDescriptions){
        for (int i = 0; i < selectedDescriptions.length; i++){
            if (selectedDescriptions[i].contains("3")){
                game.addToInventory(towerCreation(0), towerDescriptionCreation(0));
            }
            else if (selectedDescriptions[i].contains("2")){
                game.addToInventory(towerCreation(1), towerDescriptionCreation(1));
            }
            else if (selectedDescriptions[i].contains("1")){
                game.addToInventory(towerCreation(2), towerDescriptionCreation(2));
            }
        }
    }
}