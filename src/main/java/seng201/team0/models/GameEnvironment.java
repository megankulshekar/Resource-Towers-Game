package seng201.team0.models;
// Some of the following code is reused from Tutorial 2 - Structuring applications with JavaFX
import java.util.ArrayList;
import java.util.List;
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
     * Index of the current round in list of rounds
     */
    private int currentRoundIndex = 0;

    /**
     * List of rounds generated for the game
     */
    private List<Round> rounds = new ArrayList<Round>();

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
     * Method for launching the start screen GUI
     */
    private final Consumer<GameEnvironment> startScreenLauncher;

    /**
     * Method for launching the pre round GUI
     */
    private final Consumer<GameEnvironment> preRoundLauncher;

    /**
     * Method for launching the round GUI
     */
    private final Consumer<GameEnvironment> RoundLauncher;

    /**
     * Method for launching the inventory GUI
     */
    private final Consumer<GameEnvironment> InventoryLauncher;

    /**
     * Method for launching the upgrade popup GUI
     */
    private final Consumer<GameEnvironment> UpgradePopupLauncher;

    /**
     * Method for launching the shop GUI
     */
    private final Consumer<GameEnvironment> ShopLauncher;

    /**
     * Method for clearing the game window
     */
    private final Runnable clearScreen;

    /**
     * Constructor
     * @param clearScreen Method that clears current GUI displayed on the game window
     * @param startScreenLauncher Method that displays the start screen GUI on the game window
     */
    public GameEnvironment(Runnable clearScreen, Consumer<GameEnvironment> startScreenLauncher,
                           Consumer<GameEnvironment> preRoundLauncher, Consumer<GameEnvironment> RoundLauncher,
                           Consumer<GameEnvironment> InventoryLauncher, Consumer<GameEnvironment> UpgradePopupLauncher, Consumer<GameEnvironment> ShopLauncher){
        this.clearScreen = clearScreen;
        this.startScreenLauncher = startScreenLauncher;
        this.preRoundLauncher = preRoundLauncher;
        this.RoundLauncher = RoundLauncher;
        this.InventoryLauncher = InventoryLauncher;
        this.UpgradePopupLauncher = UpgradePopupLauncher;
        this.ShopLauncher = ShopLauncher;
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
     * Gets the list index of the current round
     * @return Current round index
     */
    public int getCurrentRoundIndex(){
        return currentRoundIndex;
    }

    /**
     * Increases the index of the current round by 1
     */
    public void increaseCurrentRoundIndex(){
        currentRoundIndex++;
    }

    /**
     * Gets the list of rounds for the game
     * @return List of rounds
     */
    public List<Round> getRounds(){
        return rounds;
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
     * Gets the user's inventory
     * @return User's inventory
     */
    public Inventory getInventory(){
        return inventory;
    }

    /**
     * Adds purchasable to inventory
     * @param purchasable Purchasable being added
     */
    public void addToInventory(Purchasable purchasable, String description){
        inventory.add(purchasable, description);
    }

    /**
     * Removes purchasable from inventory
     * @param purchasable Purchasable being removed
     */
    public void removeFromInventory(Purchasable purchasable){
        inventory.remove(purchasable);
    }

    public void buyInShop(Purchasable purchasable, GameEnvironment game, String description){
        shop.buy(purchasable, game, description);
    }

    public void addToUpgrades(Item item, String description){
        inventory.addUpgrade(item, description);
    }

    public void buyUpgrades(Item item, GameEnvironment game, String description){
        shop.buyUpgrade(item, game, description);
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
        launchRound();
    }

    /**
     * Launches the pre-round GUI
     */
    public void launchPreRound(){
        preRoundLauncher.accept(this);
    }

    /**
     * Clears the pre-round GUI
     */
    public void closePreRound(){
        clearScreen.run();
        launchRound();
    }

    /**
     * Launches the round GUI
     */
    public void launchRound(){
        RoundLauncher.accept(this);
    }

    /**
     * Clears the round GUI
     */
    public void closeRound(){
        clearScreen.run();
    }

    /**
     * Launches the inventory GUI
     */
    public void launchInventory(){
        InventoryLauncher.accept(this);
    }

    /**
     * Clears the inventory GUI
     */
    public void closeInventory(){
        clearScreen.run();
        launchRound();
    }

    /**
     * Clears the inventory GUI for upgrade popup GUI
     */
    public void closeInventoryForUpgrade(){
        clearScreen.run();
        launchUpgradePopup();
    }

    /**
     * Launches the upgrade popup GUI
     */
    public void launchUpgradePopup() {
        UpgradePopupLauncher.accept(this);
    }

    /**
     * Clears the upgrade popup GUI
     */
    public void closeUpgradePopup() {
        clearScreen.run();
        launchInventory();
    }

    /**
     * Launches the shop GUI
     */
    public void launchShop(){
        ShopLauncher.accept(this);
    }

    /**
     * Clears the shop GUI
     */
    public void closeShop(){
        clearScreen.run();
        launchRound();
    }
}