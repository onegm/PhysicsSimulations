package oneDimensionalKinematics.viewModel;

import oneDimensionalKinematics.util.Animatable;
import oneDimensionalKinematics.view.simulation.CarView;
import oneDimensionalKinematics.view.simulation.MarkerView;

import java.util.LinkedList;
import java.util.List;


public class MarkerViewModel implements Animatable {
    private final List<CarView> carViews;
    private final MarkerView markerView;
    private double timeSinceLastMark;
    public static final double MARK_FREQUENCY = 1d;

    public MarkerViewModel(MarkerView markerView) {
        this.markerView = markerView;
        this.carViews = new LinkedList<>();
        this.timeSinceLastMark = MARK_FREQUENCY;
    }

    public void addCarView(CarView carView){
        carViews.add(carView);
    }

    public void doStep(double timeSinceLastFrame){
        timeSinceLastMark += timeSinceLastFrame;
        if(timeSinceLastMark < MARK_FREQUENCY){
            return;
        }
        carViews.forEach(carView ->{
            markerView.mark(carView.getLayoutX() + carView.getX(),
                    carView.getLayoutY() + carView.getFitHeight()/2);
        });
        timeSinceLastMark = 0;
    }
    public void reset(){
        markerView.reset();
        this.timeSinceLastMark = MARK_FREQUENCY;
    }
}
