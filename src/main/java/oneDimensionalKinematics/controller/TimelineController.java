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
    private long timeOfLastFrame = System.currentTimeMillis();
    private long timeSinceLastFrame;
    private final double frameRateInMillis;


    public TimelineController(double frameRateInMillis){
        this.timeline = new Timeline(new KeyFrame(Duration.millis(frameRateInMillis), event -> this.notifyListeners()));
        this.timeline.setDelay(Duration.ZERO);
        this.frameRateInMillis = frameRateInMillis;
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeSinceLastFrame = (long) frameRateInMillis;

        this.listeners = new LinkedList<>();
    }

    public void handle(ApplicationStateEvent event) {
        switch (event.getEventType()) {
            case START -> start();
            case PAUSE -> this.timeline.pause();
            case END -> stop();
            case RESET -> reset();
        }
    }

    private void start() {
        this.timeOfLastFrame = System.currentTimeMillis();
        this.timeline.play();
    }

    private void stop() {
        this.timeline.stop();
        timeSinceLastFrame = System.currentTimeMillis() - timeOfLastFrame;
    }

    private void reset() {
        this.timeline.stop();
        listeners.forEach(Animatable::reset);
        timeSinceLastFrame = (long) frameRateInMillis;
    }

    private void notifyListeners() {
        timeSinceLastFrame = System.currentTimeMillis() - timeOfLastFrame;
        listeners.forEach(listener -> listener.doStep(timeSinceLastFrame / 1000d));
        timeOfLastFrame = System.currentTimeMillis();
    }

    public void addListener(Animatable listener) {
        listeners.add(listener);
    }
}
