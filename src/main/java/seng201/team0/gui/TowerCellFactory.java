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
                    // Create a new HBox to store the elements to be displayed
                    HBox hBox = new HBox(110);
                    // Add an inner VBox to hold rocket info
                    VBox vBox = new VBox(100);
                    // Add rocket name label (separated from the others, so we can also change the font size)
                    Label nameLabel = new Label(tower.getDescription());
                    nameLabel.setFont(new Font(10));
                    // Add the fuel and cleanliness labels to the VBox
//                    vBox.getChildren().addAll(
//                            nameLabel,
//                            new Label(String.format("Fuel level: %s",rocket.getFuel())),
//                            new Label(String.format("Cleanliness level: %s", rocket.getCleanliness()))
//                    );
                    // Add the image and the VBox to the HBox
                    hBox.getChildren().addAll(
                          //  imageView,
                            vBox
                    );
                    setGraphic(hBox);
                }
            }
        };
    }
}