package oneDimensionalKinematics.view.simulation;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import oneDimensionalKinematics.util.event.ApplicationStateEvent;

import java.util.LinkedList;
import java.util.List;

public class Graph extends LineChart<Number, Number> {
    private final List<Series<Number, Number>> seriesList;

    public Graph(Axis<Number> xAxis, Axis<Number> yAxis) {
        super(xAxis, yAxis);
        xAxis.setLabel("Time (s)");
        this.getStylesheets().add("graph.css");
        this.setLegendVisible(false);

        seriesList = new LinkedList<>();
    }

    public Graph(String title){
        this(new NumberAxis(), new NumberAxis());
        this.setTitle(title);
    }

    public void addSeries(Series<Number, Number> series){
        seriesList.add(series);
        this.getData().add(series);
    }
    public void reset(){
        for (Series<Number, Number> series : seriesList) {
            series.getData().clear();
        }
    }

    public void handle(ApplicationStateEvent event) {
        if(event.getEventType() == ApplicationStateEvent.Type.RESET){
            reset();
        }
    }
}
