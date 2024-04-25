package seng201.team0;
// Some of the following code is reused from Tutorial 2 - Structuring applications with JavaFX
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Class representing the game environment
 */
public class GameEnvironment {
    /**
     * Name of the user
     */
    private String name;

    /**
     * Number of rounds user picks for the game
     */
    private int numberOfRounds;

    /**
     * List of rounds generated for the game
     */
    private ArrayList<Round> rounds = new ArrayList<Round>();

    /**
     * Difficulty setting user picks for the game
     */
    private String difficulty;

    /**
     * Amount of money the user has
     */
    private int money;

    /**
     * Inventory of the user
     */
    private Inventory inventory = new Inventory();

    /**
     * Shop used for the game
     */
    private Shop shop = new Shop();

    /**
     * Method for launching the start screen
     */
    private final Consumer<GameEnvironment> startScreenLauncher;

    /**
     * Method for clearing the game window
     */
    private final Runnable clearScreen;

    /**
     * Constructor
     * @param clearScreen Method that clears current GUI displayed on the game window
     * @param startScreenLauncher Method that displays the start screen GUI on the game window
     */
    public GameEnvironment(Runnable clearScreen, Consumer<GameEnvironment> startScreenLauncher){
        this.clearScreen = clearScreen;
        this.startScreenLauncher = startScreenLauncher;
        launchStartScreen();
    }

    /**
     * Gets the user's name
     * @return User's name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the user's name
     * @param inputName Name user has chosen
     */
    public void setName(String inputName) {
        name = inputName;
    }

    /**
     * Gets the number of rounds for the game
     * @return Number of rounds
     */
    public int getNumberRounds(){
        return numberOfRounds;
    }

    /**
     * Sets the number of rounds for the game
     * @param inputNumberRounds Number of rounds user has chosen
     */
    public void setNumberRounds(int inputNumberRounds) {
        numberOfRounds = inputNumberRounds;
    }

    /**
     * Adds a round to list of rounds
     * @param newRound New round being added
     */
    public void addRound(Round newRound){
        rounds.add(newRound);
    }

    /**
     * Gets the difficulty of the game
     * @return Difficulty
     */
    public String getDifficulty(){
        return difficulty;
    }

    /**
     * Sets the difficulty of the game and sets the starting amount of money
     * @param inputDifficulty Difficulty user has chosen
     */
    public void setDifficulty(String inputDifficulty) {
        difficulty = inputDifficulty;
        if (difficulty == "Easy"){
            money = 30;
        } else if (difficulty == "Medium"){
            money = 20;
        } else {
            money = 10;
        }
    }

    /**
     * Gets the amount of money the user has
     * @return Amount of money
     */
    public int getMoney(){
        return money;
    }

    /**
     * Increases the user's money
     * @param increaseAmount Amount money is increased by
     */
    public void increaseMoney(int increaseAmount){
        money += increaseAmount;
    }

    /**
     * Decreases the user's money
     * @param decreaseAmount Amount money is decreased by
     */
    public void decreaseMoney(int decreaseAmount){
        money -= decreaseAmount;
    }

    /**
     * Adds purchasable to inventory
     * @param purchasable Purchasable being added
     */
    public void addToInventory(Purchasable purchasable){
        inventory.add(purchasable);
    }

    /**
     * Removes purchasable from inventory
     * @param purchasable Purchasable being removed
     */
    public void removeFromInventory(Purchasable purchasable){
        inventory.remove(purchasable);
    }

    /**
     * Launches the start screen GUI
     */
    public void launchStartScreen(){
        startScreenLauncher.accept(this);
    }

    /**
     * Clears the start screen GUI
     */
    public void closeStartScreen(){
        clearScreen.run();
    }
}