package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameEnvironment;

public class RoundController {
    private GameEnvironment game;

    @FXML
    private Label roundNumber;
    @FXML
    private Label mainTower1, mainTower2, mainTower3, mainTower4, mainTower5;
    @FXML
    private Label cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10;

    public RoundController(GameEnvironment game){
        this.game = game;
    }

    @FXML
    public void initialize(){
        roundNumber.setText("Round "+(game.getCurrentRoundIndex()+1));
    }

    @FXML
    public void onInventory(){
        game.closeRound();
        game.launchInventory();
    }
    @FXML
    public void onShop(){
        game.closeRound();
        game.launchShop();
    }
}
