package seng201.team0.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.util.Duration;
import seng201.team0.models.*;
import seng201.team0.services.CartThreads;
import seng201.team0.services.RoundService;
import seng201.team0.services.RoundThreads;

import java.util.List;

/**
 * Class for controlling the round GUI
 */
public class RoundController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    /**
     * The service class for the controller
     */
    private RoundService roundService;

    /**
     * The index of the current round in the list of rounds
     */
    private int currentRoundIndex;

    /**
     * The current round of the game
     */
    private Round currentRound;

    @FXML
    private Label roundNumber;

    @FXML
    private Label mainTower1, mainTower2, mainTower3, mainTower4, mainTower5;

    @FXML
    private Label cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10;

    /**
     * List of labels for user's main towers
     */
    private List<Label> mainTowerLabels;

    /**
     * List of labels for carts in the round
     */
    private List<Label> cartLabels;

    /**
     * Constructor
     * @param game The game environment
     */
    public RoundController(GameEnvironment game) {
        this.game = game;
        roundService = new RoundService(this.game);
        currentRoundIndex = game.getCurrentRoundIndex();
        currentRound = roundService.getCurrentRound();
        System.out.println("Current round index: " + currentRoundIndex);
        System.out.println("Current round: " + currentRound.toString());
    }

    /**
     * Initialises GUI by setting text for each label,
     * creates a new thread for each tower in user's main towers
     * which fills carts in the round, and constantly checks if all carts are full
     */
    //Source for PauseTransition: https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
    @FXML
    public void initialize() throws InterruptedException {
        roundNumber.setText("Round " + (currentRoundIndex + 1));
        this.mainTowerLabels = List.of(mainTower1, mainTower2, mainTower3, mainTower4, mainTower5);
        this.cartLabels = List.of(cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10);

        updateLabels();

        Inventory inventory = game.getInventory();
        for (int i = 0; i < 5; i++) {
            Tower tower = inventory.getMainTowers(i);
            if (tower != null) {
                Thread roundthread = new Thread(new RoundThreads(game, tower));
                roundthread.start();
            }
        }

        List<Cart> carts = currentRound.getCarts();
        for (Cart cart : carts){
            Thread cartThread = new Thread(new CartThreads(cart));
            cartThread.start();
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            updateLabels();
            boolean allFull = roundService.allCartsFull();
            if (allFull){
                endRound();
            } else {
                pause.play();
            }
        });
        pause.play();
    }

    /**
     * Displays the descriptions of towers and carts used in the round
     */
    public void updateLabels(){
        for (int i = 0; i < mainTowerLabels.size(); i++){
            Tower tower = game.getInventory().getMainTowers(i);
            if (tower != null) {
                mainTowerLabels.get(i).setText(tower.getDescription());
            } else{
                mainTowerLabels.get(i).setText("");
            }
        }

        for (int i = 0; i < cartLabels.size(); i++){
            if (i < currentRound.getCarts().size()){
                Cart cart = currentRound.getCarts().get(i);
                cartLabels.get(i).setText(cart.getDescription());
            } else{
                cartLabels.get(i).setText("");
            }
        }
    }

    /**
     * Closes round GUI and launches inventory GUI
     */
    @FXML
    public void onInventory(){
        game.closeRound();
        game.launchInventory();
    }

    /**
     * Closes round GUI and launches shop GUI
     */
    @FXML
    public void onShop(){
        game.closeRound();
        game.launchShop();
    }

    /**
     * Closes round GUI and launches pre round GUI
     */
    public void endRound(){
        game.closeRound();
        game.launchPreRound();
    }
}
