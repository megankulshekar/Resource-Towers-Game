package seng201.team0.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import seng201.team0.models.Item;

public class UpgradeCellFactory implements Callback<ListView<Item>, ListCell<Item>> {
    @Override
    public ListCell<Item> call(ListView<Item> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(110);
                    VBox vBox = new VBox(100);
                    Label nameLabel = new Label(item.getDescription().replace("\n", " "));
                    nameLabel.setFont(new Font(10));
                    setGraphic(nameLabel);
                }
            }
        };
    }
}