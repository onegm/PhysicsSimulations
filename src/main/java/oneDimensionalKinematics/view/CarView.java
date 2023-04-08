package oneDimensionalKinematics.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarView extends ImageView {
    private Image image;

    public CarView(Image image){
        this.image = image;
        this.setImage(image);
    }
}
