package oneDimensionalKinematics.controller;

import oneDimensionalKinematics.util.event.ApplicationStateEvent;
import oneDimensionalKinematics.util.event.ApplicationStateRequest;
import oneDimensionalKinematics.util.event.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationStateControllerTest {
    private ApplicationStateController appStateController;
    private AppStateListener listener;
    private EventBus eventBus;
    @BeforeEach
    void setUp() {
        this.eventBus = new EventBus();
        this.appStateController = new ApplicationStateController(eventBus);
        this.listener = new AppStateListener();

        this.eventBus.addListener(ApplicationStateEvent.class, listener::handle);
    }

    @Test
    void changeStateTest() {
        appStateController.handle(new ApplicationStateRequest(ApplicationStateEvent.Type.START));

        assertEquals(ApplicationStateController.State.RUNNING, appStateController.getState());
        assertTrue(listener.notified);
    }

    @Test
    void repeatedChangeRequests() {
        appStateController.handle(new ApplicationStateRequest(ApplicationStateEvent.Type.START));
        listener.setNotified(false);
        appStateController.handle(new ApplicationStateRequest(ApplicationStateEvent.Type.START));

        assertFalse(listener.notified);
    }

    @Test
    void pauseRequestDeniedIfReady() {
        appStateController.handle(new ApplicationStateRequest(ApplicationStateEvent.Type.PAUSE));
        assertEquals(ApplicationStateController.State.READY, appStateController.getState());
        assertFalse(listener.notified);
    }
    @Test
    void resetRequestDeniedIfReady() {
        appStateController.handle(new ApplicationStateRequest(ApplicationStateEvent.Type.RESET));
        assertEquals(ApplicationStateController.State.READY, appStateController.getState());
        assertFalse(listener.notified);
    }

    @Test
    void startPauseStartAgain() {
        appStateController.handle(new ApplicationStateRequest(ApplicationStateEvent.Type.START));
        appStateController.handle(new ApplicationStateRequest(ApplicationStateEvent.Type.PAUSE));
        appStateController.handle(new ApplicationStateRequest(ApplicationStateEvent.Type.START));
        assertEquals(ApplicationStateController.State.RUNNING, appStateController.getState());
        assertEquals(ApplicationStateEvent.Type.START_AGAIN, listener.lastEvent.getEventType());
    }

    private class AppStateListener{
        boolean notified = false;
        ApplicationStateEvent lastEvent;

        public void handle(ApplicationStateEvent event){
            notified = true;
            lastEvent = event;
        }

        public void setNotified(boolean b) {
            notified = b;
        }
    }
}