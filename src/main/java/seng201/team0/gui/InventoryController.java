package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import seng201.team0.CoalTower;
import seng201.team0.GameEnvironment;
import seng201.team0.Tower;

import java.util.List;

/**
 * Class for controlling the inventory GUI
 */
public class InventoryController {
    /**
     * Sets the game environment attribute
     */
    private GameEnvironment game;

    /**
     * Buttons and labels are separate attributes in the GUI layout
     */
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

    @FXML
    private Button upgradeTowerButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label upgradeAppliedLabel;

    @FXML
    private ListView<String> upgradesList;

    /**
     * Starting indices when the GUI is initially launched
     */
    private int mainTowerIndex = -1;

    private int reserveTowerIndex = -1;

    /**
     * Lists for keeping track of all buttons and labels
     */
    public List<Button> invenTowerButtons;

    public List<Button> reserveTowerButtons;

    public List<Label> mainTowerLabels;

    public List<Label> reserveTowerLabels;

//    public List<String> mainTowerDescriptions = List.of(game.getInventory().getMainTowerDescriptions(0), game.getInventory().getMainTowerDescriptions(1), game.getInventory().getMainTowerDescriptions(2), game.getInventory().getMainTowerDescriptions(3), game.getInventory().getMainTowerDescriptions(4));

//    public List<String> reserveTowerDescriptions = List.of(game.getInventory().getReserveTowersDescriptions(0), game.getInventory().getReserveTowersDescriptions(1), game.getInventory().getReserveTowersDescriptions(2), game.getInventory().getReserveTowersDescriptions(3), game.getInventory().getReserveTowersDescriptions(4));

    /**
     * Constructor
     * @param game The game environment
     */
    public InventoryController(GameEnvironment game){
        this.game = game;
    }

    /**
     * Initializes the buttons and output text
     * When certain buttons are clicked, index value of that button is stored in a variable
     */
    public void initialize() {
        invenTowerButtons = List.of(invenTower1Button, invenTower2Button, invenTower3Button, invenTower4Button, invenTower5Button);
        reserveTowerButtons = List.of(reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button, reserveTower5Button);

//        mainTowerDescriptions = List.of(game.getInventory().getMainTowerDescriptions(0), game.getInventory().getMainTowerDescriptions(1), game.getInventory().getMainTowerDescriptions(2), game.getInventory().getMainTowerDescriptions(3), game.getInventory().getMainTowerDescriptions(4));
//        reserveTowerDescriptions = List.of(game.getInventory().getReserveTowersDescriptions(0), game.getInventory().getReserveTowersDescriptions(1), game.getInventory().getReserveTowersDescriptions(2), game.getInventory().getReserveTowersDescriptions(3), game.getInventory().getReserveTowersDescriptions(4));

        mainTowerLabels = List.of(tower1Label, tower2Label, tower3Label, tower4Label, tower5Label);
        reserveTowerLabels = List.of(reserveTower1Label, reserveTower2Label, reserveTower3Label, reserveTower4Label, reserveTower5Label);

        tower1Label.setText(game.getInventory().getMainTowerDescriptions(0));
        tower2Label.setText(game.getInventory().getMainTowerDescriptions(1));
        tower3Label.setText(game.getInventory().getMainTowerDescriptions(2));
        tower4Label.setText("No tower");
        tower5Label.setText("No tower");

        reserveTower1Label.setText("No tower");
        reserveTower2Label.setText("No tower");
        reserveTower3Label.setText("No tower");
        reserveTower4Label.setText("No tower");
        reserveTower5Label.setText("No tower");

        for (int i = 0; i < invenTowerButtons.size(); i++) {
            int finalI = i;
            invenTowerButtons.get(i).setOnAction(event -> {
                mainTowerIndex = finalI;
            });
        }

        for (int i = 0; i < reserveTowerButtons.size(); i++) {
            int finalI = i;
            reserveTowerButtons.get(i).setOnAction(event -> {
                reserveTowerIndex = finalI;
            });
        }
    }

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

    /**
     * When the move tower button is clicked, tower indices and labels are swapped
     */
    @FXML
    public void onMoveTower(){
        //System.out.println(reserveTowerIndex);
        //System.out.println(mainTowerIndex);

        if (game.getInventory().getMainTowers(mainTowerIndex) != null && game.getInventory().getReserveTowers(reserveTowerIndex) != null) {
            game.getInventory().swapTowers(mainTowerIndex, reserveTowerIndex);
            String reserveText = reserveTowerLabels.get(reserveTowerIndex).getText();
            String mainText = mainTowerLabels.get(mainTowerIndex).getText();
            reserveTowerLabels.get(reserveTowerIndex).setText(mainText);
            mainTowerLabels.get(mainTowerIndex).setText(reserveText);
        }
    }

    /**
     * Alerts user that the upgrade has been applied
     */
    @FXML
    public void onUpgradeTower(){
        upgradeAppliedLabel.setText("Upgrade Applied");
    }

    /**
     * Exit returns back to the main game
     */
    @FXML
    public void onExit(){
        game.closeInventory();
    }
}