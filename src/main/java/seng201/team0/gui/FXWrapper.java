package seng201.team0.gui;
// Some of the following code is reused from Tutorial 2 - Structuring applications with JavaFX
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.models.GameEnvironment;

import java.io.IOException;

/**
 * Class defines functionality for displaying all GUIs onto game window
 */
public class FXWrapper {
    /**
     * The game window
     */
    @FXML
    private Pane pane;

    /**
     * Current FXML stage
     */
    private Stage stage;

    /**
     * Creates new game environment
     * @param stage Current FXML stage
     */
    public void init(Stage stage) {
        this.stage = stage;
        new GameEnvironment(this::clearPane, this::launchStartScreen, this::launchPreRound, this::launchRound,
                            this::launchInventory, this::launchShop);
    }

    /**
     * Clears the GUI currently displayed on the game window
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    /**
     * Displays start screen GUI on the game window
     * @param game The game environment
     */
    public void launchStartScreen(GameEnvironment game){
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/start_screen.fxml"));
            setupLoader.setControllerFactory(param -> new StartScreenController(game));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays pre-round GUI on the game window
     * @param game The game environment
     */
    public void launchPreRound(GameEnvironment game){
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/pre_round.fxml"));
            setupLoader.setControllerFactory(param -> new PreRoundController(game));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays round GUI on the game window
     * @param game The game environment
     */
    public void launchRound(GameEnvironment game){
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/round.fxml"));
            setupLoader.setControllerFactory(param -> new RoundController(game));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays inventory GUI on the game window
     * @param game The game environment
     */
    public void launchInventory(GameEnvironment game){
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/inventory.fxml"));
            setupLoader.setControllerFactory(param -> new InventoryController(game));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays shop GUI on the game window
     * @param game The game environment
     */
    public void launchShop(GameEnvironment game){
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/shop.fxml"));
            setupLoader.setControllerFactory(param -> new ShopController(game));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
