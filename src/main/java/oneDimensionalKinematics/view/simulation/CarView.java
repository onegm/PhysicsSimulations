package oneDimensionalKinematics.view.simulation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarView extends ImageView {
    private static int numOfCars;
    public CarView(Image image){
        this.setImage(image);
        this.setFitWidth(70);
        this.setFitHeight(40);
        this.setLayoutY(70*(numOfCars));
        this.setLayoutX(20);
        numOfCars += 1;
    }
}
