package oneDimensionalKinematics.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class InfoBar extends VBox {
    private final PropertyLabel positionLabel;
    private final PropertyLabel velocityLabel;

    public InfoBar(){
        this.positionLabel = new PropertyLabel("m");
        this.velocityLabel = new PropertyLabel("m/s");

        this.setPadding(new Insets(10, 30, 10, 30));
        this.setSpacing(20);

        this.positionLabel.setTextAlignment(TextAlignment.LEFT);
        this.velocityLabel.setTextAlignment(TextAlignment.LEFT);
        this.getChildren().addAll(positionLabel, velocityLabel);
    }

    public PropertyLabel getPositionLabel(){
        return  positionLabel;
    }
    public PropertyLabel getVelocityLabel() {
        return velocityLabel;
    }
}
