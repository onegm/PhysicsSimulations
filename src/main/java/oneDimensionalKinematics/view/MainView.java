package oneDimensionalKinematics.view;

import javafx.scene.layout.AnchorPane;

public class MainView extends AnchorPane {
    private CarPane carPane;

    public MainView(CarPane carPane) {
        this.carPane = carPane;

        this.getChildren().add(carPane);
    }
}
