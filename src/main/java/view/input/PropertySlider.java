package view.input;

import javafx.scene.control.Slider;

public class PropertySlider extends Slider{

    public PropertySlider(double minValue, double maxValue){
        super(minValue, maxValue, 0);

        this.setShowTickMarks(true);
        this.setShowTickLabels(true);
        this.setMajorTickUnit(maxValue/10);
        this.setMinorTickCount(1);
        this.setSnapToTicks(true);
    }

    public PropertySlider(double maxValue){
        this(0, maxValue);
    }
}
