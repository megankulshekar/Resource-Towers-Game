package seng201.team0.services;

import seng201.team0.models.*;

import java.util.Random;

/**
 * Class for providing services to pre round controller class
 */
public class PreRoundService {
    /**
     * The game environment
     */
    private GameEnvironment game;

    /**
     * The index of the next round in round list
     */
    private int currentRoundIndex;

    /**
     * The next round of the game
     */
    private Round currentRound;

    /**
     * Constructor
     * @param game The game environment
     */
    public PreRoundService(GameEnvironment game){
        this.game = game;
        currentRoundIndex = game.getCurrentRoundIndex();
        currentRound = game.getRounds().get(currentRoundIndex);
    }

    /**
     * Gets the next round
     * @return Next round
     */
    public Round getCurrentRound(){
        System.out.println(game.getCurrentRoundIndex());
        return currentRound;
    }

    /**
     * Gives money to the user based on difficulty of the last round
     * @return Amount of money given
     */
    public String giveMoney(){
        if (currentRound.getDifficulty().equals("Easy")){
            game.increaseMoney(5);
            return "$5";
        } else if (currentRound.getDifficulty().equals("Medium")){
            game.increaseMoney(10);
            return "$10";
        } else {
            game.increaseMoney(15);
            return "$15";
        }
    }

    /**
     * Chooses a random tower from main tower list
     * @return Randomly chosen main tower
     */
    public Tower randomMainTower(){
        Random random = new Random();
        Tower randomTower;
        do {
            int randomIndex = random.nextInt(5);
            randomTower = game.getInventory().getMainTowers(randomIndex);
        } while (randomTower == null);
        return randomTower;
    }

    /**
     * Increases a random main tower's XP by a random amount
     */
    public int towerGainsXP(){
        Tower randomTower = randomMainTower();
        Random random = new Random();
        int randomAmount = random.nextInt(1, 6);
        randomTower.increaseXP(randomAmount);
        return randomAmount;
    }

    /**
     * Sets a random main tower in the inventory as broken
     */
    public void towerBreaks() {
        Tower randomTower = randomMainTower();
        randomTower.setBroken(true);
    }

    /**
     * Adds new resource type a cart can have in a round
     */
    public void addNewResourceType(){
        if (currentRoundIndex+1 == 2){
            currentRound.addResourceType("Copper");
        } else if (currentRoundIndex+1 == 4){
            currentRound.addResourceType("Iron");
        } else if (currentRoundIndex+1 == 6) {
            currentRound.addResourceType("Gold");
        } else if (currentRoundIndex+1 == 8){
            currentRound.addResourceType("Diamond");
        } else if (currentRoundIndex+1 == 10){
            currentRound.addResourceType("Uranium");
        }
    }

    /**
     * Increases the number of carts in a round by 1
     */
    public void addNewCart(){
        if (currentRoundIndex % 2 == 1){
            currentRound.increaseNumCarts(1);
        }
    }

    /**
     * Sets the easy difficulty option for the next round
     */
    public void setEasyDifficulty(){
        currentRound.increaseCartSize(1);
        currentRound.increaseCartSpeed(1);
        currentRound.setDifficulty("Easy");
    }

    /**
     * Sets the medium difficulty option for the next round
     */
    public void setMediumDifficulty(){
        currentRound.increaseCartSize(2);
        currentRound.increaseCartSpeed(1);
        currentRound.setDifficulty("Medium");
    }

    /**
     * Sets the hard difficulty option for the next round
     */
    public void setHardDifficulty(){
        currentRound.increaseCartSize(3);
        currentRound.increaseCartSpeed(1);
        currentRound.setDifficulty("Hard");
    }
}
