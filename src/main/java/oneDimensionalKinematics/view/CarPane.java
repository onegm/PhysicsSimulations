package oneDimensionalKinematics.view;

import javafx.scene.layout.AnchorPane;

public class CarPane extends AnchorPane {
    private final CarView[] carViews;
    private final MarkerView[] markerViews;

    public CarPane(CarView[] carViews, MarkerView[] markerViews) {
        this.carViews = carViews;
        this.markerViews = markerViews;

        for(MarkerView markerView : markerViews){
            this.getChildren().addAll(markerView.getCircles());
        }
        this.getChildren().addAll(carViews);
    }
}
