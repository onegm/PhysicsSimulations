package oneDimensionalKinematics.controller;

import javafx.scene.chart.XYChart;
import oneDimensionalKinematics.util.Property;
import oneDimensionalKinematics.util.SimpleChangeListener;
import oneDimensionalKinematics.util.event.ApplicationStateEvent;

import java.util.LinkedList;
import java.util.List;

public class PropertyData {
    private final XYChart.Series<Number, Number> series;
    private final List<SimpleChangeListener<Double>> listeners;
    private final Property<Double> property;
    private double currentTime;

    public PropertyData(Property<Double> property) {
        this.property = property;
        this.listeners = new LinkedList<>();
        this.series = new XYChart.Series<>();
        this.currentTime = 0d;
    }

    public void addData(double timeSinceLastFrame){
        currentTime += timeSinceLastFrame;
        XYChart.Data<Number, Number> propertyData = new XYChart.Data<>(currentTime, property.getValue());
        series.getData().add(propertyData);
    }

    public XYChart.Series<Number, Number> getSeries() {
        return series;
    }

    public void handle(ApplicationStateEvent event){
        if(event.getEventType() == ApplicationStateEvent.Type.RESET){
            series.getData().clear();
            currentTime = 0d;
        }
        if(event.getEventType() == ApplicationStateEvent.Type.START){
            addData(0);
        }
    }
}
