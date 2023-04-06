package kinematics;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Rectangle redRect, blueRect;
    @FXML
    Pane pane;
    @FXML
    Slider redSpeedSlider, blueSpeedSlider, redAccSlider, blueAccSlider;
    @FXML
    Label redSpeedLabel, blueSpeedLabel, redAccLabel, blueAccLabel;

    private Car redCar, blueCar;

    private int lastUpdatedTime;

    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            animate(l);
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        redCar = new Car(redRect);
        blueCar = new Car(blueRect);

        redCar.setSpeedLabel(redSpeedLabel);
        blueCar.setSpeedLabel(blueSpeedLabel);

        redCar.setSpeedSlider(redSpeedSlider);
        blueCar.setSpeedSlider(blueSpeedSlider);

        redCar.setAccLabel(redAccLabel);
        blueCar.setAccLabel(blueAccLabel);

        redCar.setAccSlider(redAccSlider);
        blueCar.setAccSlider(blueAccSlider);

    }

    public void animate(long time){

        int timeNow = (int) Math.round(time / 1_000_000.0);
        int elapsedTime = timeNow - lastUpdatedTime;
        redCar.move(elapsedTime);
        blueCar.move(elapsedTime);

        if(timeNow % 1000 < 15 || timeNow%1000 > 985 ){
            redCar.mark();
            blueCar.mark();
        }
        lastUpdatedTime = timeNow;
    }

    public void startTimer(){
        lastUpdatedTime = (int) Math.round(System.nanoTime() / 1_000_000.0);
        timer.start();
    }

    public void stopTimer(){timer.stop();}

    public void reset(){
        redCar.reset();
        blueCar.reset();
        timer.stop();
    }
}