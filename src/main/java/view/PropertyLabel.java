package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class PropertyLabel extends Label {
    private int value;
    private final String units;
    private String formattedLabel = "%d %s";

    public PropertyLabel(String units){
        this.units = units;
        this.setText(String.format(formattedLabel, value, units));
        this.setFont(new Font("Arial", 24));
        this.setMinWidth(100);
        this.setAlignment(Pos.CENTER);
    }

    public void setValue(double newValue){
        this.value = (int) Math.round(newValue);
        this.setText(String.format(formattedLabel, value, units));
    }

    public void setFormattedLabel(String label){
        this.formattedLabel = label;
        this.setText(String.format(formattedLabel, value, units));
    }

    public String getFormattedLabel() {
        return formattedLabel;
    }
}
