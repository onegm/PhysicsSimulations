package kinematics;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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

    private  Timer timer;
    private TimerTask timerTask;
    private long lastMarkTime, timeSinceLastMark;

    private int lastFrameTime;

    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            animate();
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

        timer = new Timer();
    }

    public void animate(){

        int timeNow = (int) (System.currentTimeMillis() % 100_000);
        int elapsedTime = timeNow - lastFrameTime;
        redCar.move(elapsedTime);
        blueCar.move(elapsedTime);

        lastFrameTime = timeNow;

        timeSinceLastMark = System.currentTimeMillis() - lastMarkTime;
    }

    public void startAnimation(){
        lastFrameTime = (int) (System.currentTimeMillis()  % 100_000);
        animationTimer.start();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    //update UI thread from here.
                    redCar.mark();
                    blueCar.mark();
                    lastMarkTime = System.currentTimeMillis();
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000 - timeSinceLastMark, 1000);
    }

    public void pauseAnimation(){
        animationTimer.stop();
        timerTask.cancel();
    }

    public void resetAnimation(){
        redCar.reset();
        blueCar.reset();
        animationTimer.stop();
        timerTask.cancel();
        timeSinceLastMark = 0;
    }

    public void shutDown(){
        animationTimer.stop();
        timer.cancel();
        timer.purge();
    }


}