package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.input.PropertySlider;

public class DefaultPropertyPane extends VBox {
    Slider slider1;
    Slider slider2;
    PropertyLabel label1;
    PropertyLabel label2;
    Label title;
    public DefaultPropertyPane(String title, String units){
        this.setSpacing(40);
        this.setPadding(new Insets(20,20,20,20));

        this.title = new Label(title);

        this.slider1 = new PropertySlider(800);
        this.label1 = new PropertyLabel(units);
        slider1.valueProperty().addListener(e->label1.setValue(slider1.getValue()));

        this.slider2 = new PropertySlider(800);
        this.label2 = new PropertyLabel(units);
        slider2.valueProperty().addListener(e->label2.setValue(slider2.getValue()));

        HBox hBox = new HBox();
        hBox.getChildren().addAll(slider1, label1, label2, slider2);

        this.getChildren().addAll(this.title, hBox);
    }



}