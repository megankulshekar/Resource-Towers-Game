package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import seng201.team0.models.Cart;
import seng201.team0.models.CoalTower;
import seng201.team0.models.GameEnvironment;
import seng201.team0.models.Tower;
import seng201.team0.services.InventoryService;
import seng201.team0.services.RoundService;

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
     * The service class for the controller
     */
    private InventoryService inventoryService;

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

    /**
     * Constructor
     * @param game The game environment
     */
    public InventoryController(GameEnvironment game){
        this.game = game;
        inventoryService = new InventoryService(this.game);
    }

    /**
     * Initializes the buttons and output text
     * When certain buttons are clicked, index value of that button is stored in a variable for later use
     */
    public void initialize() {
        invenTowerButtons = List.of(invenTower1Button, invenTower2Button, invenTower3Button, invenTower4Button, invenTower5Button);
        reserveTowerButtons = List.of(reserveTower1Button, reserveTower2Button, reserveTower3Button, reserveTower4Button, reserveTower5Button);

        mainTowerLabels = List.of(tower1Label, tower2Label, tower3Label, tower4Label, tower5Label);
        reserveTowerLabels = List.of(reserveTower1Label, reserveTower2Label, reserveTower3Label, reserveTower4Label, reserveTower5Label);

        updateLabels();

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

    /**
     * Displays the main and reserve towers descriptions
     */
    public void updateLabels(){
        for (int i = 0; i < mainTowerLabels.size(); i++){
            Tower tower = game.getInventory().getMainTowers(i);
            if (tower != null) {
                mainTowerLabels.get(i).setText(game.getInventory().getMainTowerDescriptions(i));
            } else{
                mainTowerLabels.get(i).setText("");
            }
        }

        for (int i = 0; i < reserveTowerLabels.size(); i++){
            Tower tower = game.getInventory().getReserveTowers(i);
            if (tower != null) {
                reserveTowerLabels.get(i).setText(game.getInventory().getReserveTowerDescriptions(i));
            } else{
                reserveTowerLabels.get(i).setText("");
            }
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
//        System.out.println(reserveTowerIndex);
//        System.out.println(mainTowerIndex);

        game.getInventory().swapTowers(mainTowerIndex, reserveTowerIndex);
        String reserveText = reserveTowerLabels.get(reserveTowerIndex).getText();
        String mainText = mainTowerLabels.get(mainTowerIndex).getText();
        reserveTowerLabels.get(reserveTowerIndex).setText(mainText);
        mainTowerLabels.get(mainTowerIndex).setText(reserveText);
        mainTowerIndex = -1;
        reserveTowerIndex = -1;
    }

    /**
     * Sets index value of tower clicked and then launches Upgrade GUI
     */
    @FXML
    public void onUpgradeTower(){
        if (mainTowerIndex != -1 && reserveTowerIndex == -1){
            game.getInventory().setTowerIndexValue(mainTowerIndex);
        }
        else if (mainTowerIndex == -1 && reserveTowerIndex != -1){
            game.getInventory().setTowerIndexValue(reserveTowerIndex + 4);
        }
        game.closeInventoryForUpgrade();
        game.launchUpgradePopup();
    }

    /**
     * Exit returns back to the main game
     */
    @FXML
    public void onExit(){
        game.closeInventory();
    }
}