package oneDimensionalKinematics.model;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        car.setLocation(new Pair<>(10.0, 0.0));
        car.setSpeed(5);
    }

    @Test
    void move_constSpeed() {
        car.step(1.0);
        assertEquals(15, car.getX());
    }

    @Test
    void move_increasingSpeed() {
        car.setAcc(2);
        car.step(3.0);
        assertEquals(34, car.getX());
    }


}