package oneDimensionalKinematics.util.event;

public class ApplicationStateEvent implements Event{

    public enum Type{
        START,
        PAUSE,
        START_AGAIN,
        RESET
    }

    private final Type eventType;

    public ApplicationStateEvent(Type eventType) {
        this.eventType = eventType;
    }

    public Type getEventType(){
        return eventType;
    }
}
