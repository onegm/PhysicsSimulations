package controller;

import util.event.ApplicationStateEvent;
import util.event.ApplicationStateRequest;
import util.event.EventBus;

public class ApplicationStateController {


    public enum State{
        READY,
        RUNNING,
        PAUSED
    }

    private final EventBus eventBus;

    private ApplicationStateController.State state = State.READY;

    public ApplicationStateController(EventBus eventBus){
        this.eventBus = eventBus;
    }

    public ApplicationStateController(){
        this.eventBus = null;
    }
    public void handle(ApplicationStateRequest event){
        switch (event.getEventType()) {
            case START -> start();
            case PAUSE -> pause();
            case RESET -> reset();
        }
    }
    public State getState() {
        return state;
    }

    private void start() {
        if(state == State.RUNNING)
            return;
        if(state == State.PAUSED) {
            eventBus.emit(new ApplicationStateEvent(ApplicationStateEvent.Type.START_AGAIN));
        }
        else{
            eventBus.emit(new ApplicationStateEvent(ApplicationStateEvent.Type.START));
        }
        System.out.println("START REQUEST");
        this.state = State.RUNNING;
    }

    private void pause() {
        if(state == State.PAUSED || state == State.READY)
            return;
        System.out.println("PAUSE REQUEST");
        this.state = State.PAUSED;
        eventBus.emit(new ApplicationStateEvent(ApplicationStateEvent.Type.PAUSE));
    }

    private void reset() {
        if(state == State.READY)
            return;
        System.out.println("RESET REQUEST");
        this.state = State.READY;
        eventBus.emit(new ApplicationStateEvent(ApplicationStateEvent.Type.RESET));
    }
}
