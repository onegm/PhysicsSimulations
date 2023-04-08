package kinematics;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Car {
    private final ImageView car;
    private Slider posSlider, speedSlider, accSlider;
    private Label initialPosLabel, posLabel,
            initialSpeedLabel, speedLabel,
            accLabel;
    private final String posLabelFormat = "%d m";
    private final String speedLabelFormat = "%d m/s";

    private double x, speed, acc;
    private boolean canMove = true;
    private final AnchorPane pane;
    private ArrayList<Circle> marks;
    private static final double MARK_SIZE = 5.0;

    public Car(ImageView car){
        this.car = car;
        x = car.getX();
        marks = new ArrayList<>();
        pane = (AnchorPane) car.getParent();
    }

    public void setSliders(Slider posSlider, Slider speedSlider, Slider accSlider) {
        this.posSlider = posSlider;
        this.speedSlider = speedSlider;
        this.accSlider = accSlider;

        this.posSlider.valueProperty().addListener((observableValue, number, t1) -> {
            setPos(this.posSlider.getValue());
            initialPosLabel.setText(Math.round(x) + "");
        });
        setPos(this.posSlider.getValue());
        this.posSlider.setMax(pane.getPrefWidth());
        this.posSlider.setMajorTickUnit(pane.getPrefWidth()/10);
        initialPosLabel.setText(Math.round(x) + "");

        this.speedSlider.valueProperty().addListener((observableValue, number, t1) -> {
            setSpeed(this.speedSlider.getValue());
            initialSpeedLabel.setText(Math.round(speed) + "");
        });
        setSpeed(this.speedSlider.getValue());
        initialSpeedLabel.setText(Math.round(speed) + "");

        this.accSlider.valueProperty().addListener((observableValue, number, t1) -> setAcc(this.accSlider.getValue()));
        setAcc(this.accSlider.getValue());
    }

    public void setLabels(Label initialPosLabel, Label posLabel,
                          Label initialSpeedLabel, Label speedLabel,
                          Label accLabel) {
        this.initialPosLabel = initialPosLabel;
        this.posLabel = posLabel;
        this.initialSpeedLabel = initialSpeedLabel;
        this.speedLabel = speedLabel;
        this.accLabel = accLabel;
    }

    public void setPos(double x){
        this.x = x;
        posLabel.setText(String.format(posLabelFormat, Math.round(x)));
        update();
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        speedLabel.setText(String.format(speedLabelFormat, Math.round(speed)));
    }

    public void setAcc(double accX) {
        this.acc = accX;
        accLabel.setText(Math.round(accX) + "");
    }
    public void move(long timeInMillis){
        if(x + car.getFitWidth() < pane.getWidth()){
            setPos(x + speed * timeInMillis/1000.0 + 0.5* acc *Math.pow(timeInMillis/1000.0, 2));
            setSpeed(speed + acc * timeInMillis/1000.0);
            if(!canMove){
                canMove = true;
            }
        }
        else if(canMove){
            canMove = false;
        }
    }

    public void update(){
        car.setX(x);
    }

    public void reset(){
        setPos(posSlider.getValue());
        setSpeed(speedSlider.getValue());
        pane.getChildren().removeAll(marks);
        marks = new ArrayList<>();
    }

    public void mark(){
        Circle circle = new Circle(car.getLayoutX() + x, car.getLayoutY() + car.getFitHeight()/2 - MARK_SIZE/2, MARK_SIZE);
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

    public double getSpeed() {
        return speed;
    }
}
