package oneDimensionalKinematics.viewModel;

import oneDimensionalKinematics.util.Property;
import oneDimensionalKinematics.util.Animatable;

public class CarViewModel implements Animatable {
    private final Property<Double> x = new Property<>(0d);
    private final Property<Double> speed = new Property<>(0d);
    private final Property<Double> acc = new Property<>(0d);
    private final Property<Double> initialX = new Property<>(0d);
    private final Property<Double> initialSpeed = new Property<>(0d);

    public CarViewModel(){
        this.getInitialX().addListener(this.getX()::setValue);
        this.getInitialSpeed().addListener(this.getSpeed()::setValue);
    }
    public Property<Double> getX() {
        return x;
    }
    public Property<Double> getSpeed() {
        return speed;
    }
    public Property<Double> getAcc() {
        return acc;
    }
    public Property<Double> getInitialX() {
        return initialX;
    }

    public Property<Double> getInitialSpeed() {
        return initialSpeed;
    }

    public void doStep(double timeInSec){
        x.setValue(step(timeInSec));
    }

    public void reset() {
        x.setValue(initialX.getValue());
        speed.setValue(initialSpeed.getValue());
    }

    public double step(double timeInSec){
        double newX = x.getValue() + speed.getValue()*timeInSec + 0.5*acc.getValue()*Math.pow(timeInSec, 2);
        double newSpeed = speed.getValue() + acc.getValue()*timeInSec;
        x.setValue(newX);
        speed.setValue(newSpeed);
        return newX;
    }
}
