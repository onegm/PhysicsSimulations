package controller;

import javafx.scene.image.ImageView;
import view.marker.Marker;
import util.event.ApplicationStateEvent;
import model.PhysicalObject;


public class MarkerController {
    private final Marker marker;
    private final PhysicalObject physicalObject;
    private final ImageView imgView;
    private double time;

    public MarkerController(Marker marker, PhysicalObject physicalObject, ImageView imgView) {
        this.marker = marker;
        this.physicalObject = physicalObject;
        this.imgView = imgView;
    }

    public void mark(double value){
        this.time += value;
        marker.mark(imgView.getLayoutX() + imgView.getX(),
                    imgView.getY() + imgView.getFitHeight()/2,
                       physicalObject.getSpeedXValue(),
                       physicalObject.getSpeedYValue(),
                       time);
    }
    public void handle(ApplicationStateEvent event){
        if(event.getEventType() == ApplicationStateEvent.Type.RESET){
            marker.reset();
            this.time = 0;
        }
        else if(event.getEventType() == ApplicationStateEvent.Type.START){
            mark(0);
        }
    }
}
