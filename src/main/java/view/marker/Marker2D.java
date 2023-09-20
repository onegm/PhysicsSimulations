package view.marker;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Marker2D extends Marker {
    private MarkerState state;
    private static final String formattedLabelX = "X: %.1f\nVx: %.1f\nT: %.1f";
    private static final String formattedLabelY = "Y: %.1f\nVy: %.1f\nT: %.1f";

    public Marker2D(){
        MARK_SIZE = 5.0;
        state = MarkerState.X_INFO;
    }

    public void mark(double x, double y, double speedX, double speedY, double time){
        Circle circle = new Circle(x, y, MARK_SIZE);

        Text textX = createMarkerInfo( x,  y, String.format(formattedLabelX, x, speedX, time));
        Text textY = createMarkerInfo( x,  y, String.format(formattedLabelY, y, speedY, time));

        circle.setOnMouseEntered(event -> mouseEntered(circle, textX, textY));
        circle.setOnMouseExited(event -> mouseExited(circle, textX, textY));
        circle.setOnMouseClicked(event -> {
            switchState();
            mouseEntered(circle, textX, textY);
        });

        this.getChildren().addAll(circle, textX, textY);
    }

    private void mouseExited(Circle circle, Text textX, Text textY) {
        circle.setRadius(MARK_SIZE);
        textX.setFill(Color.TRANSPARENT);
        textY.setFill(Color.TRANSPARENT);
    }

    private void mouseEntered(Circle circle, Text textX, Text textY) {
        circle.setRadius(MARK_SIZE*2);
        if(this.state == MarkerState.X_INFO){
            textX.setFill(Color.BLACK);
            textY.setFill(Color.TRANSPARENT);
        }
        else if(this.state == MarkerState.Y_INFO){
            textX.setFill(Color.TRANSPARENT);
            textY.setFill(Color.BLACK);
        }
    }

    public void switchState(){
        if(this.state == MarkerState.X_INFO)
            this.state = MarkerState.Y_INFO;
        else if(this.state == MarkerState.Y_INFO)
            this.state = MarkerState.X_INFO;
    }
}
