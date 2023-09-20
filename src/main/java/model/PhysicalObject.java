package model;

import util.Animatable;
import util.Property;
import util.event.ApplicationStateEvent;

public class PhysicalObject implements Animatable{
    private final Property<Double> x = new Property<>(0d);
    private final Property<Double> y = new Property<>(0d);
    private final Property<Double> speedX = new Property<>(0d);
    private final Property<Double> speedY = new Property<>(0d);
    private final Property<Double> accX = new Property<>(0d);
    private final Property<Double> accY = new Property<>(0d);

    private final Property<Double> initialX = new Property<>(0d);
    private final Property<Double> initialY = new Property<>(0d);
    private final Property<Double> initialSpeedX = new Property<>(0d);
    private final Property<Double> initialSpeedY = new Property<>(0d);

    public PhysicalObject(){
        this.getInitialX().addListener(this.getX()::setValue);
        this.getInitialY().addListener(this.getY()::setValue);
        this.getInitialSpeedX().addListener(this.getSpeedX()::setValue);
        this.getInitialSpeedY().addListener(this.getSpeedY()::setValue);
    }

    public void doStep(double timeInSec){
        double newX = x.getValue() + speedX.getValue()*timeInSec + 0.5*accX.getValue()*Math.pow(timeInSec, 2);
        double newY = y.getValue() + speedY.getValue()*timeInSec + 0.5*accY.getValue()*Math.pow(timeInSec, 2);
        double newSpeedX = speedX.getValue() + accX.getValue()*timeInSec;
        double newSpeedY = speedY.getValue() + accY.getValue()*timeInSec;
        x.setValue(newX);
        y.setValue(newY);
        speedX.setValue(newSpeedX);
        speedY.setValue(newSpeedY);
    }

    public void reset() {
        this.x.setValue(initialX.getValue());
        this.y.setValue(initialY.getValue());
        this.speedX.setValue(initialSpeedX.getValue());
        this.speedY.setValue(initialSpeedY.getValue());
    }

    public void handle(ApplicationStateEvent event){
        if(event.getEventType() == ApplicationStateEvent.Type.RESET){
            reset();
        }
    }

    public Property<Double> getX() {
        return x;
    }

    public Property<Double> getY() {
        return y;
    }

    public Property<Double> getSpeedX() {
        return speedX;
    }

    public Property<Double> getSpeedY() {
        return speedY;
    }

    public Property<Double> getAccX() {
        return accX;
    }

    public Property<Double> getAccY() {
        return accY;
    }

    public double getXValue() {
        return x.getValue();
    }

    public double getYValue() {
        return y.getValue();
    }

    public double getSpeedXValue() {
        return speedX.getValue();
    }

    public double getSpeedYValue() {
        return speedY.getValue();
    }

    public double getAccXValue() {
        return accX.getValue();
    }

    public double getAccYValue() {
        return accY.getValue();
    }

    public Property<Double> getInitialX() {
        return initialX;
    }

    public Property<Double> getInitialY() {
        return initialY;
    }

    public Property<Double> getInitialSpeedX() {
        return initialSpeedX;
    }

    public Property<Double> getInitialSpeedY() {
        return initialSpeedY;
    }
}
