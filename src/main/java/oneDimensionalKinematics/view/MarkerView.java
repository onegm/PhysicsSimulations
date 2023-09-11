package oneDimensionalKinematics.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class MarkerView extends Pane {
    public static final double MARK_SIZE = 5.0;
    private static final String formattedLabel = "X: %.1f\nV: %.1f\nT: %.1f";


    public void mark(double x, double y, double speed, double time){
        Circle circle = new Circle(x, y, MARK_SIZE);


        Text text = new Text(x - MARK_SIZE*4, y + MARK_SIZE*5, String.format(formattedLabel, x, speed, time));
        text.setFill(Color.TRANSPARENT);
        circle.setOnMouseEntered(event -> mouseEntered(circle, text));
        circle.setOnMouseExited(event -> mouseExited(circle, text));

        this.getChildren().addAll(circle, text);
    }

    private static void mouseExited(Circle circle, Text text) {
        circle.setRadius(MARK_SIZE);
        text.setFill(Color.TRANSPARENT);
    }

    private static void mouseEntered(Circle circle, Text text) {
        circle.setRadius(MARK_SIZE*2);
        text.setFill(Color.BLACK);

    }

    public void reset(){
        this.getChildren().clear();
    }
}
