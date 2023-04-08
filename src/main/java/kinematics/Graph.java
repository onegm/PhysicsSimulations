package kinematics;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Graph extends LineChart<Number, Number> {
    private XYChart.Series<Number, Number> redPosSeries, bluePosSeries;

    public Graph(NumberAxis xAxis, NumberAxis yAxis){
        super(xAxis, yAxis);
        xAxis.setLabel("Time (s)");
        this.getStylesheets().add("graph.css");

        redPosSeries = new XYChart.Series<>();
        bluePosSeries = new XYChart.Series<>();
        this.getData().addAll(redPosSeries, bluePosSeries);
        reset();
    }

    public void addRed(int x, double y){
        redPosSeries.getData().add(new XYChart.Data<>(x, y));
    }
    public void addBlue(int x, double y){
        bluePosSeries.getData().add(new XYChart.Data<>(x, y));
    }

    public void reset(){
        this.getData().removeAll(redPosSeries, bluePosSeries);
        redPosSeries = new XYChart.Series<>();
        bluePosSeries = new XYChart.Series<>();
        redPosSeries.setName("RED");
        bluePosSeries.setName("BLUE");
        this.getData().addAll(redPosSeries, bluePosSeries);
    }

}
