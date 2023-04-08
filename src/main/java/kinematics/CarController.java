package kinematics;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class CarController {
    private final Car car;
    private Slider posSlider, speedSlider, accSlider;
    private Label initialPosLabel, posLabel,
            initialSpeedLabel, speedLabel,
            accLabel;

    public CarController(Car car) {
        this.car = car;
    }

    public void setSliders(Slider posSlider, Slider speedSlider, Slider accSlider){
        this.posSlider = posSlider;
        this.speedSlider = speedSlider;
        this.accSlider = accSlider;

        this.posSlider.valueProperty().addListener((observableValue, number, t1) -> {
            setPos(this.posSlider.getValue());
            initialPosLabel.setText(Math.round(car.getX()) + "");
        });
        setPos(this.posSlider.getValue());
        this.posSlider.setMax(car.getPane().getPrefWidth());
        this.posSlider.setMajorTickUnit(car.getPane().getPrefWidth()/10);
        initialPosLabel.setText(Math.round(car.getX()) + "");

        this.speedSlider.valueProperty().addListener((observableValue, number, t1) -> {
            setSpeed(this.speedSlider.getValue());
            initialSpeedLabel.setText(Math.round(car.getSpeed()) + "");
        });
        setSpeed(this.speedSlider.getValue());
        initialSpeedLabel.setText(Math.round(car.getSpeed()) + "");

        this.accSlider.valueProperty().addListener((observableValue, number, t1) -> {
            car.setAcc(this.accSlider.getValue());
            accLabel.setText(Math.round(car.getAcc()) + "");
        });
        car.setAcc(this.accSlider.getValue());
        accLabel.setText(Math.round(car.getAcc()) + "");

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


    public void reset(){
        setPos(posSlider.getValue());
        setSpeed(speedSlider.getValue());
    }

    public void move(long timeInMillis){
        double x = car.getX();
        double speed = car.getSpeed();
        double acc = car.getAcc();
        if(x + car.getWidth() < car.getPane().getWidth()){
            setPos(x + speed * timeInMillis/1000.0 + 0.5* acc *Math.pow(timeInMillis/1000.0, 2));
            setSpeed(speed + acc * timeInMillis/1000.0);
            if(!car.canMove()){
                car.setCanMove(true);
            }
        }
        else if(car.canMove()){
            car.setCanMove(false);
        }
    }

    public void setPos(double x){
        car.setPos(x);
        String posLabelFormat = "%d m";
        posLabel.setText(String.format(posLabelFormat, Math.round(x)));
    }
    public void setSpeed(double speed){
        car.setSpeed(speed);
        String speedLabelFormat = "%d m/s";
        speedLabel.setText(String.format(speedLabelFormat, Math.round(speed)));
    }
}
