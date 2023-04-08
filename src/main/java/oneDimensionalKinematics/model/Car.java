package oneDimensionalKinematics.model;

public class Car {
    private double x, speed, acc;
    private boolean canMove = true;

    public Car(){}

    public void setPos(double x){
        this.x = x;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setAcc(double accX) {
        this.acc = accX;
    }

    public double getX(){
        return x;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAcc(){
        return acc;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean b){
        canMove = b;
    }

    public void move(long time){
        x += speed*time + 0.5*acc*Math.pow(time, 2);
    }
}
