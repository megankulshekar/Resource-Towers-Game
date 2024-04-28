package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.GameEnvironment;

public class InventoryController {
    private GameEnvironment game;

    @FXML
    private Button invenTower1Button, invenTower2Button, invenTower3Button, invenTower4Button, invenTower5Button;

    @FXML
    private Label tower1Label, tower2Label, tower3Label, tower4Label, tower5Label;

    @FXML
    private Button reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button, reserveTower5Button;

    @FXML
    private Label reserveTower1Label, reserveTower2Label, reserveTower3Label, reserveTower4Label, reserveTower5Label;

    @FXML
    private Button moveTowerButton;

    private int mainTowerIndex = -1;

    private int reserveTowerIndex = -1;

    public InventoryController(GameEnvironment game){
        this.game = game;
    }

//    public void initialize(){
//        tower1Label.setText(game.getInventory().getMainTowers(0));
//    }

    @FXML
    public void onSelectedInvenTower1(){
        mainTowerIndex = 0;
    }

    @FXML
    public void onSelectedInvenTower2(){
        mainTowerIndex = 1;
    }

    @FXML
    public void onSelectedInvenTower3(){
        mainTowerIndex = 2;
    }

    @FXML
    public void onSelectedInvenTower4(){
        mainTowerIndex = 3;
    }

    @FXML
    public void onSelectedInvenTower5(){
        mainTowerIndex = 4;
    }

    @FXML
    public void onSelectedReserve1(){
        reserveTowerIndex = 0;
    }

    @FXML
    public void onSelectedReserve2(){
        reserveTowerIndex = 1;
    }

    @FXML
    public void onSelectedReserve3(){
        reserveTowerIndex = 2;
    }

    @FXML
    public void onSelectedReserve4(){
        reserveTowerIndex = 3;
    }

    @FXML
    public void onSelectedReserve5(){
        reserveTowerIndex = 4;
    }

    @FXML
    public void onMoveTower(){
        game.getInventory().swapTowers(mainTowerIndex, reserveTowerIndex);
    }
}
