package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.*;

import java.util.List;

/**
 * Class for controlling the round GUI
 */
public class RoundController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    @FXML
    private Label roundNumber;

    @FXML
    private Label mainTower1, mainTower2, mainTower3, mainTower4, mainTower5;

    @FXML
    private Label cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10;

    /**
     * Constructor
     * @param game The game environment
     */
    public RoundController(GameEnvironment game) {
        this.game = game;
    }

    /**
     * Initialises GUI by setting text for each label
     */
    @FXML
    public void initialize() {
        int currentRoundIndex = game.getCurrentRoundIndex();

        /*Round round = new Round("Hard");
        round.increaseNumCarts(7);
        round.createCarts();
        game.addRound(round);
        game.addToInventory(new DiamondTower());*/

        Round currentRound = game.getRounds().get(currentRoundIndex);

        roundNumber.setText("Round " + (currentRoundIndex + 1));
        List<Label> mainTowerLabels = List.of(mainTower1, mainTower2, mainTower3, mainTower4, mainTower5);
        List<Label> cartLabels = List.of(cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10);

        for (int i = 0; i < mainTowerLabels.size(); i++){
            Tower tower = game.getInventory().getMainTowers()[i];
            if (tower != null) {
                mainTowerLabels.get(i).setText(tower.getDescription("Coal", 2, 2));
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
}
