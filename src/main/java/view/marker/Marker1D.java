package view.marker;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Marker1D extends Marker{
    private static final String formattedLabel = "X: %.1f\nV: %.1f\nT: %.1f";

    public Marker1D(){
        MARK_SIZE = 5.0;
    }

    public void mark(double x, double y, double speed, double speedY, double time){
        Circle circle = new Circle(x, y, MARK_SIZE);

        Text text = createMarkerInfo( x,  y, String.format(formattedLabel, x, speed, time));

        circle.setOnMouseEntered(event -> mouseEntered(circle, text));
        circle.setOnMouseExited(event -> mouseExited(circle, text));

        this.getChildren().addAll(circle, text);
    }

    private void mouseExited(Circle circle, Text text) {
        circle.setRadius(MARK_SIZE);
        text.setFill(Color.TRANSPARENT);
    }

    private void mouseEntered(Circle circle, Text text) {
        circle.setRadius(MARK_SIZE*2);
        text.setFill(Color.BLACK);
    }
}
