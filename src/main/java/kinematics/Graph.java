package kinematics;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Graph extends LineChart<Number, Number> {
    private XYChart.Series<Number, Number> redSeries, blueSeries;

    public Graph(NumberAxis xAxis, NumberAxis yAxis){
        super(xAxis, yAxis);
        xAxis.setLabel("Time (s)");
        this.getStylesheets().add("graph.css");

        redSeries = new XYChart.Series<>();
        blueSeries = new XYChart.Series<>();
        this.getData().addAll(redSeries, blueSeries);
        reset();
    }

    public void addRed(int x, double y){
        redSeries.getData().add(new XYChart.Data<>(x, y));
    }
    public void addBlue(int x, double y){
        blueSeries.getData().add(new XYChart.Data<>(x, y));
    }

    public void reset(){
        this.getData().removeAll(redSeries, blueSeries);
        redSeries = new XYChart.Series<>();
        blueSeries = new XYChart.Series<>();
        redSeries.setName("RED");
        blueSeries.setName("BLUE");
        this.getData().addAll(redSeries, blueSeries);
    }

}
