package viewModels;

import util.Animatable;
import util.Property;
import util.event.ApplicationStateEvent;

public class PhysicalObject implements Animatable{
    private final Property<Double> x = new Property<>(0d);
    private final Property<Double> y = new Property<>(0d);
    private final Property<Double> xSpeed = new Property<>(0d);
    private final Property<Double> ySpeed = new Property<>(0d);
    private final Property<Double> xAcc = new Property<>(0d);
    private final Property<Double> yAcc = new Property<>(0d);

    public void doStep(double timeInSec){
        double newX = x.getValue() + xSpeed.getValue()*timeInSec + 0.5*xAcc.getValue()*Math.pow(timeInSec, 2);
        double newY = y.getValue() + ySpeed.getValue()*timeInSec + 0.5*yAcc.getValue()*Math.pow(timeInSec, 2);
        double newXSpeed = xSpeed.getValue() + xAcc.getValue()*timeInSec;
        double newYSpeed = ySpeed.getValue() + yAcc.getValue()*timeInSec;
        x.setValue(newX);
        y.setValue(newY);
        xSpeed.setValue(newXSpeed);
        ySpeed.setValue(newYSpeed);
    }

    public Property<Double> getX() {
        return x;
    }

    public Property<Double> getY() {
        return y;
    }

    public Property<Double> getXSpeed() {
        return xSpeed;
    }

    public Property<Double> getYSpeed() {
        return ySpeed;
    }

    public Property<Double> getXAcc() {
        return xAcc;
    }

    public Property<Double> getYAcc() {
        return yAcc;
    }

    public Double getXValue() {
        return x.getValue();
    }

    public Double getYValue() {
        return y.getValue();
    }

    public Double getXSpeedValue() {
        return xSpeed.getValue();
    }

    public Double getYSpeedValue() {
        return ySpeed.getValue();
    }

    public Double getXAccValue() {
        return xAcc.getValue();
    }

    public Double getYAccValue() {
        return yAcc.getValue();
    }

    public void handle(ApplicationStateEvent event){
        if(event.getEventType() == ApplicationStateEvent.Type.RESET){
            this.getX().setValue(0d);
            this.getY().setValue(0d);
        }
    }
}
