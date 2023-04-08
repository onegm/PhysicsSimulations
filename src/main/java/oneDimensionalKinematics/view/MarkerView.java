package oneDimensionalKinematics.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import oneDimensionalKinematics.model.Marker;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkerView {
    private Marker marker;
    private ArrayList<Circle> circles = new ArrayList<>();
    public static final double MARK_SIZE = 5.0;

    public MarkerView(Marker marker){
        this.marker = marker;

        for(HashMap.Entry<Double, Double> location : marker.getLocations().entrySet()){
            Circle circle = new Circle(location.getKey(), location.getValue(), MARK_SIZE);
            circles.add(circle);
        }
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }

}
