package oneDimensionalKinematics.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarView extends ImageView {
    public CarView(Image image){
        this.setImage(image);
        this.setFitWidth(70);
        this.setFitHeight(40);
    }

    public void setLocation(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
}
