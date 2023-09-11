package oneDimensionalKinematics.controller;

import util.Property;
import util.SimpleChangeListener;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void constructWithInitialValue() {
        String expected = "Hello World";
        Property<String> stringProperty = new Property<String>("Hello World");

        assertEquals(expected, stringProperty.getValue());
    }

    @Test
    void constructWithNoInitialValue() {
        Property<String> stringProperty = new Property<String>();

        assertNull(stringProperty.getValue());
    }

    @Test
    void notifiesListeners() {
        double expected = 2.0;
        DoubleListener listener = new DoubleListener();
        Property<Double> doubleProperty = new Property<>();
        doubleProperty.addListener(listener);

        doubleProperty.setValue(expected);

        assertTrue(listener.notified);
        assertEquals(expected, listener.value);
    }

    private class DoubleListener implements SimpleChangeListener<Double>{
        private double value;
        private boolean notified = false;
        @Override
        public void valueChanged(Double newValue) {
            notified = true;
            this.value = newValue;
        }
    }
}