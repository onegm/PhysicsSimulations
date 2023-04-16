package oneDimensionalKinematics.view;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import oneDimensionalKinematics.util.Property;

public class PropertyLabel extends Label {

    private int value;
    private final String units;

    private static final String formattedLabel = "%d %s";

    public PropertyLabel(String units){
        this.units = units;
        this.setText(String.format(formattedLabel, value, units));
        this.setFont(new Font("Arial", 24));
    }

    public void setValue(double newValue){
        this.value = (int) Math.round(newValue);
        this.setText(String.format(formattedLabel, value, units));
    }
}
