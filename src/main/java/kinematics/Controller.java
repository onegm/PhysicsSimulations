package kinematics;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    @FXML
    private Rectangle redRect, blueRect;
    @FXML
    AnchorPane posGraphPane, speedGraphPane;
    @FXML
    Slider redSpeedSlider, blueSpeedSlider, redAccSlider, blueAccSlider;
    @FXML
    Label redSpeedLabel, blueSpeedLabel, redAccLabel, blueAccLabel;

    private Car redCar, blueCar;
    private  Timer timer;
    private TimerTask timerTask;
    private long lastMarkTime, timeSinceLastMark;
    private long lastFrameTime;

    private Graph posGraph, speedGraph;

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
        timeSinceLastMark = 1000;

        posGraph = new Graph(new NumberAxis(), new NumberAxis());
        posGraph.setTitle("Position");
        posGraphPane.getChildren().add(posGraph);
        AnchorPane.setBottomAnchor(posGraph, 0d);
        AnchorPane.setTopAnchor(posGraph, 0d);
        AnchorPane.setLeftAnchor(posGraph, 0d);
        AnchorPane.setRightAnchor(posGraph, 0d);

        speedGraph = new Graph(new NumberAxis(), new NumberAxis());
        speedGraph.setTitle("Speed");
        speedGraphPane.getChildren().add(speedGraph);
        AnchorPane.setBottomAnchor(speedGraph, 0d);
        AnchorPane.setTopAnchor(speedGraph, 0d);
        AnchorPane.setLeftAnchor(speedGraph, 0d);
        AnchorPane.setRightAnchor(speedGraph, 0d);
    }

    public void animate(){

        long timeNow = System.currentTimeMillis();
        long elapsedTime = timeNow - lastFrameTime;
        redCar.move(elapsedTime);
        blueCar.move(elapsedTime);

        lastFrameTime = timeNow;

        timeSinceLastMark = System.currentTimeMillis() - lastMarkTime;
    }

    public void startAnimation(){
        lastFrameTime = System.currentTimeMillis();
        animationTimer.start();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    //update UI thread from here.
                    if(redCar.canMove()){
                        redCar.mark();
                        posGraph.addRed(redCar.getNumOfMarks()-1, redCar.getLastMarkX());
                        speedGraph.addRed(redCar.getNumOfMarks()-1, redCar.getSpeedX());
                    }
                    if(blueCar.canMove()){
                        blueCar.mark();
                        posGraph.addBlue(blueCar.getNumOfMarks()-1, blueCar.getLastMarkX());
                        speedGraph.addBlue(blueCar.getNumOfMarks()-1, blueCar.getSpeedX());
                    }

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
        posGraph.reset();
        speedGraph.reset();
        animationTimer.stop();
        timeSinceLastMark = 0;

        if(timerTask != null)
            timerTask.cancel();
    }

    public void shutDown(){
        animationTimer.stop();
        timer.cancel();
        timer.purge();
    }


}