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
    private Slider speedSlider, accSlider;
    private Label speedLabel, accLabel;

    private double x, y,
            speedX, speedY,
            accX, accY;
    private boolean canMove = true;
    private final AnchorPane pane;
    private ArrayList<Circle> marks;

    public Car(Rectangle car){
        this.car = car;
        x = car.getX();
        y = car.getY();
        marks = new ArrayList<>();
        pane = (AnchorPane) car.getParent();
    }

    public void setSpeedSlider(Slider slider){
        speedSlider = slider;
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setSpeed(speedSlider.getValue(), 0);
            }
        });

        setSpeed(speedSlider.getValue(), 0);
    }

    public void setAccSlider(Slider slider){
        accSlider = slider;
        accSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setAcc(accSlider.getValue(), 0);
            }
        });
        setAcc(accSlider.getValue(), 0);
    }

    public void move(long timeInMillis){
        if(x + car.getWidth() < pane.getWidth()){
            setPos(x + speedX*timeInMillis/1000.0 + 0.5*accX*Math.pow(timeInMillis/1000.0, 2), 0);
            setSpeed(speedX + accX*timeInMillis/1000.0, 0);
        }
        else if(canMove){
            canMove = false;
        }
    }

    public void update(){
        car.setX(x);
        car.setY(y);
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

    public void setAccLabel(Label label){
        accLabel = label;
        accLabel.setText(Math.round(accX) + " m/s^2");
    }

    public void setPos(double x, double y){
        this.x = x;
        this.y = y;
        update();
    }

    public double getX(){
        return x;
    }

    public void setAcc(double accX, double accY) {
        this.accX = accX;
        this.accY = accY;
        accLabel.setText(Math.round(accX) + " m/s^2");
    }

    public void reset(){
        setPos(0, 0);
        setSpeed(speedSlider.getValue(), 0);
        pane.getChildren().removeAll(marks);
        marks = new ArrayList<>();
        canMove = true;
    }

    public void mark(){
        Circle circle = new Circle(car.getLayoutX() + x, car.getLayoutY() + car.getHeight()/2, 5.0);
        pane.getChildren().add(circle);
        marks.add(circle);
    }

    public int getNumOfMarks(){
        return marks.size();
    }

    public double getLastMarkX(){
        return marks.get(marks.size() - 1).getCenterX() - car.getLayoutX();
    }

    public boolean canMove() {
        return canMove;
    }

    public double getSpeedX() {
        return speedX;
    }
}
