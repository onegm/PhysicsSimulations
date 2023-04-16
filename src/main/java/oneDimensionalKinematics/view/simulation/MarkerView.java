package oneDimensionalKinematics.view.simulation;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class MarkerView extends Pane {
    private ArrayList<Circle> circles = new ArrayList<>();
    public static final double MARK_SIZE = 5.0;

    public void mark(double x, double y){
        Circle circle = new Circle(x, y, MARK_SIZE);
        circle.setOnMouseEntered(event -> circle.setRadius(MARK_SIZE*2));
        circle.setOnMouseExited(event -> circle.setRadius(MARK_SIZE));
        this.getChildren().add(circle);
        circles.add(circle);
    }

    public void reset(){
        this.getChildren().removeAll(circles);
        circles = new ArrayList<>();
    }
}
