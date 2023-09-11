package oneDimensionalKinematics.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarView extends ImageView {
    private static int numOfCars;
    public CarView(Image image){
        this.setImage(image);
        this.setFitWidth(84);
        this.setFitHeight(48);
        this.setY(90*(numOfCars) + 10);
        this.setLayoutX(20);
        numOfCars += 1;
    }
}
