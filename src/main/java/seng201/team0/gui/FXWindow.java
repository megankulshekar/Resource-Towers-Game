package seng201.team0.gui;
// Following code is reused from Tutorial 2 - Structuring applications with JavaFX
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class starts the JavaFX application window
 * @author seng201 teaching team
 */
public class FXWindow extends Application {
    /**
     * Opens the gui with the fxml content specified in resources/fxml/fx_wrapper.fxml
     * @param primaryStage The current fxml stage, handled by JavaFX Application class
     * @throws IOException If there is an issue loading fxml file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/fx_wrapper.fxml"));
        Parent root = baseLoader.load();
        FXWrapper fxWrapper = baseLoader.getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game");
        primaryStage.show();
        fxWrapper.init(primaryStage);
    }

    /**
     * Launches the FXML application
     * @param args Command line arguments
     */
    public static void launchWrapper(String[] args) {
        launch(args);
    }

}