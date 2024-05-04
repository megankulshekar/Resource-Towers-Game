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
    private int nextRoundIndex;

    /**
     * The next round of the game
     */
    private Round nextRound;

    /**
     * Constructor
     * @param game The game environment
     */
    public PreRoundService(GameEnvironment game){
        this.game = game;
        this.game.increaseCurrentRoundIndex();
        nextRoundIndex = game.getCurrentRoundIndex();
        nextRound = game.getRounds().get(nextRoundIndex);
    }

    /**
     * Gets the next round
     * @return Next round
     */
    public Round getNextRound(){
        return nextRound;
    }

    /**
     * Gives money to the user based on difficulty of the last round
     * @return Amount of money given
     */
    public String giveMoney(){
        Round lastRound = game.getRounds().get(nextRoundIndex - 1);
        if (lastRound.getDifficulty() == "Easy"){
            game.increaseMoney(5);
            return "$5";
        } else if (lastRound.getDifficulty() == "Medium"){
            game.increaseMoney(10);
            return "$10";
        } else{
            game.increaseMoney(15);
            return "$15";
        }
    }

    /**
     * Sets a random main tower in the inventory as broken
     */
    public void towerBreaks() {
        Random random = new Random();
        Tower randomTower;
        do {
            int randomIndex = random.nextInt(5);
            randomTower = game.getInventory().getMainTowers(randomIndex);
        } while (randomTower == null);
        randomTower.setBroken(true);
    }

    /**
     * Adds new resource type a cart can have in a round
     */
    public void addNewResourceType(){
        if (nextRoundIndex == 2){
            nextRound.addResourceType("Copper");
        } else if (nextRoundIndex == 4){
            nextRound.addResourceType("Iron");
        } else if (nextRoundIndex == 6) {
            nextRound.addResourceType("Gold");
        } else if (nextRoundIndex == 8){
            nextRound.addResourceType("Diamond");
        } else if (nextRoundIndex == 10){
            nextRound.addResourceType("Uranium");
        }
    }

    /**
     * Increases the number of carts in a round by 1
     */
    public void addNewCart(){
        if (nextRoundIndex % 2 == 0){
            nextRound.increaseNumCarts(1);
        }
    }

    /**
     * Sets the easy difficulty option for the next round
     */
    public void setEasyDifficulty(){
        nextRound.increaseCartSize(1);
        nextRound.increaseCartSpeed(1);
        nextRound.setDifficulty("Easy");
    }

    /**
     * Sets the medium difficulty option for the next round
     */
    public void setMediumDifficulty(){
        nextRound.increaseCartSize(2);
        nextRound.increaseCartSpeed(2);
        nextRound.setDifficulty("Medium");
    }

    /**
     * Sets the hard difficulty option for the next round
     */
    public void setHardDifficulty(){
        nextRound.increaseCartSize(3);
        nextRound.increaseCartSpeed(3);
        nextRound.setDifficulty("Hard");
    }
}
