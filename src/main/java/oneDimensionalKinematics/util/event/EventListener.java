package oneDimensionalKinematics.util.event;

public interface EventListener<T extends Event> {
    void handle(T event);
}
