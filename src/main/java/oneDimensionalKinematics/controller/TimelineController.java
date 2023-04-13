package oneDimensionalKinematics.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import oneDimensionalKinematics.util.Animatable;
import oneDimensionalKinematics.viewModel.ApplicationState;

import java.util.LinkedList;
import java.util.List;

public class TimelineController{
    private final Timeline timeline;
    private final List<Animatable> listeners;
    private long timeOfLastFrame = System.currentTimeMillis();


    public TimelineController(){
        double FRAME_RATE = 1.0 / 60.0*1000;

        this.timeline = new Timeline(new KeyFrame(Duration.millis(FRAME_RATE), event -> this.notifyListeners()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);

        this.listeners = new LinkedList<>();
    }

    public void onAppStateChanged(ApplicationState state) {
        if (state == ApplicationState.RUNNING) {
            this.timeOfLastFrame = System.currentTimeMillis();
            this.timeline.play();
        }
        else if(state == ApplicationState.PAUSED){
            this.timeline.stop();
        }
        else if (state == ApplicationState.READY) {
            this.timeline.stop();
            listeners.forEach(Animatable::reset);
        }
    }

    private void notifyListeners() {
        double timeSinceLastFrame = (double)(System.currentTimeMillis() - timeOfLastFrame) / 1000.0;
        listeners.forEach(listener -> listener.doStep(timeSinceLastFrame));
        timeOfLastFrame = System.currentTimeMillis();
    }

    public void addListeners(List<Animatable> newListeners){
        listeners.addAll(newListeners);
    }
}
