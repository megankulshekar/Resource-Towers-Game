package seng201.team0.gui;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.util.Duration;
import seng201.team0.models.*;
import seng201.team0.services.RoundService;

import java.util.List;
import java.util.concurrent.Callable;

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

    private List<Label> mainTowerLabels;
    private List<Label> cartLabels;

    private Timeline timeline;

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
     * Initialises GUI by setting text for each label
     */
    //Source for PauseTransition: https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
    @FXML
    public void initialize() throws InterruptedException {
        roundNumber.setText("Round "+(currentRoundIndex + 1));
        this.mainTowerLabels = List.of(mainTower1, mainTower2, mainTower3, mainTower4, mainTower5);
        this.cartLabels = List.of(cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10);

        updateLabels();

        Inventory inventory = game.getInventory();
        Tower tower = inventory.getMainTowers(0);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        PauseTransition pause1 = new PauseTransition(Duration.seconds(10));

        pause.setOnFinished(event -> {
            fillCart(tower);
            updateLabels();
            pause1.play();
            boolean allFull = roundService.allCartsFull();
            if (allFull){
                endRound();
            } else {
                pause.play();
            }
        });
        pause.play();
    }

    public void fillCart(Tower fillingTower) {
        PauseTransition pause1 = new PauseTransition(Duration.seconds(fillingTower.getReloadSpeed()));
        List<Cart> currentRoundCarts = currentRound.getCarts();
        for (Cart cart : currentRoundCarts) {
            if (!cart.isFull() && cart.getResourceType().equals(fillingTower.getResourceType())) {
                currentRound.fillCart(cart, fillingTower);
                pause1.play();
            }
        }
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
