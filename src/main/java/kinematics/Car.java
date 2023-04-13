package kinematics;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Car {
    private final ImageView car;
    private double x, speed, acc;
    private boolean canMove = true;
    private final AnchorPane pane;
    private Marker marker;

    public Car(ImageView car){
        this.car = car;
        x = car.getX();
        pane = (AnchorPane) car.getParent();
    }

    public void setPos(double x){
        this.x = x;
        car.setX(x);
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
    public double getWidth(){
        return car.getFitWidth();
    }

    public double getHeight(){
        return car.getFitHeight();
    }

    public double getLayoutX(){
        return car.getLayoutX();
    }

    public double getLayoutY(){
        return car.getLayoutY();
    }

    public AnchorPane getPane() {
        return pane;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean b){
        canMove = b;
    }

    public Marker getMarker(){
        return marker;
    }

    public void setMarker(Marker marker){
        this.marker = marker;
    }
}
