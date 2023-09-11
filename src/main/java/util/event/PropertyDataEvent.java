package util.event;

import javafx.scene.chart.XYChart;

public class PropertyDataEvent implements Event{
    private final XYChart.Data<Number, Number> data;

    public PropertyDataEvent(XYChart.Data<Number, Number> data) {
        this.data = data;
    }

    public XYChart.Data<Number, Number> getData(){
        return data;
    }
}
