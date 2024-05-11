package seng201.team0.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.util.Duration;
import seng201.team0.models.*;
import seng201.team0.services.CartThreads;
import seng201.team0.services.RoundService;
import seng201.team0.services.TowerThreads;

import java.util.ArrayList;
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

    /**
     * List of labels for user's main towers
     */
    private List<Label> mainTowerLabels;

    /**
     * List of labels for carts in the round
     */
    private List<Label> cartLabels;

    /**
     * List of tower threads in the round
     */
    private List<Thread> towerThreads = new ArrayList<Thread>();

    /**
     * List of cart threads in the round
     */
    private List<Thread> cartThreads = new ArrayList<Thread>();

    /**
     * Label for showing the round number
     */
    @FXML
    private Label roundNumber;

    /**
     * Labels for showing each tower in main towers list in inventory
     */
    @FXML
    private Label mainTower1, mainTower2, mainTower3, mainTower4, mainTower5;

    /**
     * Labels for showing each cart in the round
     */
    @FXML
    private Label cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10;

    /**
     * Constructor
     * @param game The game environment
     */
    public RoundController(GameEnvironment game) {
        this.game = game;
        roundService = new RoundService(this.game);
        currentRoundIndex = game.getCurrentRoundIndex();
        currentRound = roundService.getCurrentRound();
    }

    /**
     * Initialises GUI by setting text for each label,
     * creates a new thread for each tower in user's main towers
     * which fills carts in the round, and constantly checks if all carts are full
     */
    //Code for creating tower and cart threads is reused from Tutorial 3 - Advanced Java
    //Source for PauseTransition: https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
    @FXML
    public void initialize() {
        roundNumber.setText("Round " + (currentRoundIndex + 1));
        this.mainTowerLabels = List.of(mainTower1, mainTower2, mainTower3, mainTower4, mainTower5);
        this.cartLabels = List.of(cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10);

        updateLabels();

        Inventory inventory = game.getInventory();
        for (int i = 0; i < 5; i++) {
            Tower tower = inventory.getMainTowers(i);
            if (tower != null) {
                Thread towerThread = new Thread(new TowerThreads(game, tower));
                towerThreads.add(towerThread);
                towerThread.start();
            }
        }

        List<Cart> carts = currentRound.getCarts();
        for (Cart cart : carts){
            Thread cartThread = new Thread(new CartThreads(cart));
            cartThreads.add(cartThread);
            cartThread.start();
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            updateLabels();
            boolean timeRunOut = roundService.cartTimeRunOut();
            boolean allFull = roundService.allCartsFull();
            if (timeRunOut){
                loseRound();
            } else if (allFull){
                winRound();
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
        for (Thread thread : towerThreads){
            thread.interrupt();
        }
        for (Thread thread : cartThreads){
            thread.interrupt();
        }

        game.closeRound();
        game.launchInventory();
    }

    /**
     * Closes round GUI and launches shop GUI
     */
    @FXML
    public void onShop(){
        for (Thread thread : towerThreads){
            thread.interrupt();
        }
        for (Thread thread : cartThreads){
            thread.interrupt();
        }

        game.closeRound();
        game.launchShop();
    }

    /**
     * Closes round GUI and launches end screen GUI if all rounds have been completed,
     * otherwise it launches pre round GUI
     */
    public void winRound(){
        for (Thread thread : towerThreads){
            thread.interrupt();
        }
        for (Thread thread : cartThreads){
            thread.interrupt();
        }

        game.closeRound();
        if (currentRoundIndex+1 == game.getRounds().size()){
            game.launchEndScreen();
        } else {
            game.launchPreRound();
        }
    }

    /**
     * Closes round GUI and launches end screen GUI
     */
    public void loseRound(){
        for (Thread thread : towerThreads){
            thread.interrupt();
        }
        for (Thread thread : cartThreads){
            thread.interrupt();
        }

        game.closeRound();
        game.launchEndScreen();
    }
}
