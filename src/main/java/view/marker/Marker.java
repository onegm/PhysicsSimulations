package view.marker;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class Marker extends Pane {
    protected double MARK_SIZE;
    public abstract void mark(double x, double y, double speed, double speedY, double time);

    public void reset(){
        this.getChildren().clear();
    }

    public Text createMarkerInfo(double x, double y, String formattedString){
        Text text = new Text(x - MARK_SIZE*4, y + MARK_SIZE*5, formattedString);
        text.setFill(Color.TRANSPARENT);
        return text;
    }

    public enum MarkerState {
        X_INFO,
        Y_INFO
    }
}
