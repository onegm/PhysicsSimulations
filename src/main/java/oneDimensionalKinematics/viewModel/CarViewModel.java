package oneDimensionalKinematics.viewModel;

import javafx.util.Pair;
import oneDimensionalKinematics.model.Car;
import oneDimensionalKinematics.util.Animatable;
import oneDimensionalKinematics.view.CarView;

public class CarViewModel implements Animatable {
    private final Car car;
    private final CarView carView;
    public CarViewModel(CarView carView){
        this.car = new Car();
        this.carView = carView;

        this.car.setSpeed(30);
    }

    public void doStep(double timeInSec){
        car.step(timeInSec);
        carView.setLocation(car.getX(), car.getY());
    }

    public void reset() {
        car.setLocation(new Pair<>(0d, 0d));
        carView.setLocation(0d, 0d);
    }

    public Pair<Double, Double> getCenterLocation(){
        return new Pair<>(carView.getLayoutX() + carView.getX(),
                carView.getLayoutY() + carView.getY() + carView.getFitHeight()/2);
    }
}
