package kinematics;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    @FXML
    private ImageView redCarImage, blueCarImage;
    @FXML
    private AnchorPane posGraphPane, speedGraphPane;
    @FXML
    private Slider redPosSlider, redSpeedSlider,
            bluePosSlider, blueSpeedSlider,
            redAccSlider, blueAccSlider;
    @FXML
    private Label redInitialPosLabel, redPosLabel,
            blueInitialPosLabel, bluePosLabel,
            redInitialSpeedLabel, redSpeedLabel,
            blueInitialSpeedLabel, blueSpeedLabel,
            redAccLabel, blueAccLabel;

    private Car redCar, blueCar;
    private  Timer timer;
    private TimerTask timerTask;
    private long lastMarkTime, timeSinceLastMark;
    private long lastFrameTime;
    public static long MARK_PERIOD = 1000;

    private Graph posGraph, speedGraph;

    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            animate();
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        redCar = new Car(redCarImage);
        blueCar = new Car(blueCarImage);

        redCar.setLabels(redInitialPosLabel, redPosLabel,
                redInitialSpeedLabel, redSpeedLabel,
                redAccLabel);
        blueCar.setLabels(blueInitialPosLabel, bluePosLabel,
                blueInitialSpeedLabel, blueSpeedLabel,
                blueAccLabel);

        redCar.setSliders(redPosSlider, redSpeedSlider, redAccSlider);
        blueCar.setSliders(bluePosSlider, blueSpeedSlider, blueAccSlider);


        timer = new Timer();
        timeSinceLastMark = MARK_PERIOD;

        posGraph = new Graph(new NumberAxis(), new NumberAxis());
        posGraph.setTitle("Position (m)");
        posGraphPane.getChildren().add(posGraph);
        AnchorPane.setBottomAnchor(posGraph, 0d);
        AnchorPane.setTopAnchor(posGraph, 0d);
        AnchorPane.setLeftAnchor(posGraph, 0d);
        AnchorPane.setRightAnchor(posGraph, 0d);

        speedGraph = new Graph(new NumberAxis(), new NumberAxis());
        speedGraph.setTitle("Speed (m/s)");
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
                        speedGraph.addRed(redCar.getNumOfMarks()-1, redCar.getSpeed());
                    }
                    if(blueCar.canMove()){
                        blueCar.mark();
                        posGraph.addBlue(blueCar.getNumOfMarks()-1, blueCar.getLastMarkX());
                        speedGraph.addBlue(blueCar.getNumOfMarks()-1, blueCar.getSpeed());
                    }

                    lastMarkTime = System.currentTimeMillis();
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, MARK_PERIOD - timeSinceLastMark, MARK_PERIOD);
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
        timeSinceLastMark = MARK_PERIOD;

        if(timerTask != null)
            timerTask.cancel();
    }

    public void shutDown(){
        animationTimer.stop();
        timer.cancel();
        timer.purge();
    }


}