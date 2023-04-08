package kinematics;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Marker {
    private final Car car;
    private ArrayList<Circle> marks;
    private final AnchorPane pane;
    private final double MARK_SIZE = 5.0;


    public Marker(Car car){
        this.car = car;
        this.car.setMarker(this);
        marks = new ArrayList<>();
        pane = car.getPane();
    }

    public void mark(){
        Circle circle = new Circle();
        circle.setCenterX(car.getLayoutX() + car.getX());
        circle.setCenterY(car.getLayoutY() + car.getHeight()/2);
        circle.setRadius(MARK_SIZE);
        pane.getChildren().add(circle);
        marks.add(circle);
    }

    public int getNumOfMarks(){
        return marks.size();
    }

    public double getLastMarkX(){
        return marks.get(marks.size() - 1).getCenterX() - car.getLayoutX();
    }

    public void reset(){
        pane.getChildren().removeAll(marks);
        marks = new ArrayList<>();
    }
}
