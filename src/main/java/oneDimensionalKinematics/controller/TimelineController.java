package oneDimensionalKinematics.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import oneDimensionalKinematics.util.Animatable;
import oneDimensionalKinematics.util.event.ApplicationStateEvent;

import java.util.LinkedList;
import java.util.List;

public class TimelineController{
    private final Timeline timeline;
    private final List<Animatable> listeners;
    private long timeOfLastFrame;
    private long timeSinceLastFrame;
    private long startTime;
    private long timeOfPause;


    public TimelineController(double frameRateInMillis){
//        this.timeline = new Timeline(new KeyFrame(Duration.millis(frameRateInMillis), event -> this.notifyListeners()));
        double fps = 1/(frameRateInMillis/1000);
        this.timeline = new Timeline(fps, new KeyFrame(Duration.seconds(1/fps), event -> this.notifyListeners()));
        this.timeline.setDelay(Duration.ZERO);
        this.timeline.setCycleCount(Timeline.INDEFINITE);

        this.listeners = new LinkedList<>();
    }

    public void handle(ApplicationStateEvent event) {
        switch (event.getEventType()) {
            case START, START_AGAIN -> start();
            case PAUSE -> pause();
            case RESET -> reset();
        }
    }

    private void start() {
        this.startTime = System.currentTimeMillis();
        this.timeline.play();
    }

    private void pause(){
        this.timeline.pause();
        this.timeOfPause = System.currentTimeMillis();
    }

    private void reset() {
        this.timeline.stop();
        listeners.forEach(Animatable::reset);
        timeOfLastFrame = 0;
        timeOfPause = 0;
    }

    private void notifyListeners() {
        timeSinceLastFrame = System.currentTimeMillis() - (startTime - (timeOfPause - timeOfLastFrame));
        listeners.forEach(listener -> listener.doStep(timeSinceLastFrame / 1000d));
        timeOfLastFrame = System.currentTimeMillis();
        timeOfPause = 0;
        startTime = 0;
    }

    public void addListener(Animatable listener) {
        listeners.add(listener);
    }
}
