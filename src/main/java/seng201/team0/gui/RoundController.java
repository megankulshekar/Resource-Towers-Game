package seng201.team0.gui;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.util.Duration;
import seng201.team0.models.*;
import seng201.team0.services.RoundService;

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
    public void initialize() {
        roundNumber.setText("Round "+(currentRoundIndex + 1));
        this.mainTowerLabels = List.of(mainTower1, mainTower2, mainTower3, mainTower4, mainTower5);
        this.cartLabels = List.of(cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10);

        updateLabels();

        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
                    roundService.fillCart();
                    updateLabels();
                    pause.play();
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

    public void tower1Fills(){
        Tower tower1 = game.getInventory().getMainTowers(0);
        List<Cart> currentRoundCarts = currentRound.getCarts();
        PauseTransition pause1 = new PauseTransition(Duration.seconds(tower1.getReloadSpeed()));
        pause1.setOnFinished(event -> {
            roundService.fillCart();
            updateLabels();
            pause1.play();
        });
        pause1.play();

        int currentlyFillingIndex = 0;
        Cart currentlyFilling;
        currentlyFilling = currentRoundCarts.get(currentlyFillingIndex);
        if (!currentlyFilling.isFull() && currentlyFilling.getResourceType().equals(tower1.getResourceType())) {
            currentRound.fillCart(currentlyFilling, tower1);
            updateLabels();
            pause1.play();
        } else {
            currentlyFillingIndex++;
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
    public void onEndRound(){
        game.closeRound();
        game.launchPreRound();
    }
}
