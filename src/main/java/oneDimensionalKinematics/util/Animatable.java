package oneDimensionalKinematics.util;

public interface Animatable {
    void doStep(double timeInSeconds);

    default void reset(){}
}
