package oneDimensionalKinematics.model;

import javafx.util.Pair;

public class Car {
    private Pair<Double, Double> location = new Pair<>(0.0, 0.0);
    private double speed, acc;

    public void setLocation(Pair<Double, Double> newLocation){
        this.location = newLocation;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAcc(double accX) {
        this.acc = accX;
    }

    public double getX(){
        return location.getKey();
    }
    public double getY(){
        return location.getValue();
    }

    public double getSpeed() {
        return speed;
    }

    public double getAcc(){
        return acc;
    }

    public void step(double timeInSec){
        double x = this.getX() + this.getSpeed()*timeInSec + 0.5*this.getAcc()*Math.pow(timeInSec, 2);
        this.location = new Pair<>(x, this.getY());
    }
}
