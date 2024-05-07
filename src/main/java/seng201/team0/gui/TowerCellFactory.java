package seng201.team0.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import seng201.team0.models.Tower;

public class TowerCellFactory implements Callback<ListView<Tower>, ListCell<Tower>> {
    @Override
    public ListCell<Tower> call(ListView<Tower> param) {
        return new ListCell<>() {
            @Override
            public void updateItem(Tower tower, boolean empty) {
                super.updateItem(tower, empty);
                if (empty || tower == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(110);
                    VBox vBox = new VBox(100);
                    Label nameLabel = new Label(tower.getDescription().replace("\n", " "));
                    nameLabel.setFont(new Font(10));
                    setGraphic(nameLabel);
                }
            }
        };
    }
}