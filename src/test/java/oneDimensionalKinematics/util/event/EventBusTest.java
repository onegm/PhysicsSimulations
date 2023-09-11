package oneDimensionalKinematics.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.event.Event;
import util.event.EventBus;
import util.event.EventListener;

import static org.junit.jupiter.api.Assertions.*;

class EventBusTest {
    private EventBus eventBus;
    
    @BeforeEach
    void setUp() {
        this.eventBus = new EventBus();
    }

    @Test
    void singleListenerCalled() {
        HelloEvent helloEvent = new HelloEvent();
        TestEventListener<HelloEvent> eventListener = new TestEventListener<>();
        eventBus.addListener(HelloEvent.class, eventListener);
        eventBus.emit(helloEvent);

        assertTrue(eventListener.listenerCalled);
    }
    @Test
    void multipleListenersCalled() {
        HelloEvent helloEvent = new HelloEvent();
        TestEventListener<HelloEvent> eventListener1 = new TestEventListener<>();
        TestEventListener<HelloEvent> eventListener2 = new TestEventListener<>();
        eventBus.addListener(HelloEvent.class, eventListener1);
        eventBus.addListener(HelloEvent.class, eventListener2);
        eventBus.emit(helloEvent);

        assertTrue(eventListener1.listenerCalled);
        assertTrue(eventListener2.listenerCalled);
    }

    @Test
    void multipleEventTypes() {
        WorldEvent worldEvent = new WorldEvent();

        TestEventListener<HelloEvent> helloListener = new TestEventListener<>();
        TestEventListener<WorldEvent> worldListener = new TestEventListener<>();

        eventBus.addListener(HelloEvent.class, helloListener);
        eventBus.addListener(WorldEvent.class, worldListener);

        eventBus.emit(worldEvent);

        assertTrue(worldListener.listenerCalled);
        assertFalse(helloListener.listenerCalled);
    }

    private static class TestEventListener<T extends Event> implements EventListener<T> {
        boolean listenerCalled = false;

        @Override
        public void handle(T event) {
            listenerCalled = true;
        }
    }

    private static class HelloEvent implements Event{
    }
    private static class WorldEvent implements Event{
    }
}