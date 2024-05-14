package seng201.team0.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import seng201.team0.models.Item;

/**
 * Class for controlling the creation of upgrade cells in the list view of GUI
 */
public class UpgradeCellFactory implements Callback<ListView<String>, ListCell<String>> {
    /**
     * Creates the upgrade cell for list view
     * @param param List of upgrades to make cells for
     * @return Upgrade description
     */
    @Override
    public ListCell<String> call(ListView<String> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(String string, boolean empty) {
                super.updateItem(string, empty);
                if (empty || string == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(110);
                    VBox vBox = new VBox(100);
                    Label nameLabel = new Label(string.replace("\n", " "));
                    nameLabel.setFont(new Font(10));
                    setGraphic(nameLabel);
                }
            }
        };
    }
}