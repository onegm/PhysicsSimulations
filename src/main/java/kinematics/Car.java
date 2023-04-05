package kinematics;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Car {
    private Rectangle car;
    private Slider speedSlider;
    private Label speedLabel;

    private double x, y,
            speedX, speedY,
            accX, accY;

    private AnchorPane pane;
    private ArrayList<Circle> marks;

    public Car(Rectangle car){
        this.car = car;
        x = car.getX();
        y = car.getY();
        marks = new ArrayList<>();
        pane = (AnchorPane) car.getParent();
    }

    public void setSpeedSlider(Slider sliderNode){
        speedSlider = sliderNode;
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setSpeed(speedSlider.getValue(), 0);
            }
        });

        setSpeed(speedSlider.getValue(), 0);
    }

    public void update(){
        car.setX(x);
        car.setY(y);
    }

    public void move(int timeInMillis){

        if(x + car.getWidth() < car.getScene().getWidth()){
            setPos(x + speedX*timeInMillis/1000, y + speedY*timeInMillis/1000);
            setSpeed(speedX + accX*timeInMillis/1000, speedY + accY*timeInMillis/1000);
            update();
        }
    }

    public void setSpeed(double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        speedLabel.setText(Math.round(speedX) + " m/s");
    }

    public void setSpeedLabel(Label label){
        speedLabel = label;
        speedLabel.setText(Math.round(speedX) + " m/s");
    }

    public void setPos(double x, double y){
        this.x = x;
        this.y = y;
        update();
    }

    public void setAccX(double accX) {
        this.accX = accX;
    }

    public void setAccY(double accY) {
        this.accY = accY;
    }

    public void reset(){
        setPos(0, 0);
        pane.getChildren().removeAll(marks);
        marks = new ArrayList<>();
    }

    public void mark(){
        Circle circle = new Circle(car.getLayoutX() + x, car.getLayoutY() + car.getHeight()/2, 5.0);
        pane.getChildren().add(circle);
        marks.add(circle);
    }

}
