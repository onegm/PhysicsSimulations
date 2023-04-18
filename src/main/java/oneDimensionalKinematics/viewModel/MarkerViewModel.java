package oneDimensionalKinematics.viewModel;

import oneDimensionalKinematics.util.event.ApplicationStateEvent;
import oneDimensionalKinematics.view.simulation.CarView;
import oneDimensionalKinematics.view.simulation.MarkerView;

import java.util.LinkedList;
import java.util.List;


public class MarkerViewModel  {
    private final List<CarView> carViews;
    private final MarkerView markerView;

    public MarkerViewModel(MarkerView markerView) {
        this.markerView = markerView;
        this.carViews = new LinkedList<>();
    }

    public void addCarView(CarView carView){
        carViews.add(carView);
    }

    public void mark(double value){
        carViews.forEach(carView -> markerView.mark(carView.getLayoutX() + carView.getX(),
                carView.getLayoutY() + carView.getFitHeight()/2));
    }
    public void handle(ApplicationStateEvent event){
        if(event.getEventType() == ApplicationStateEvent.Type.RESET){
            markerView.reset();
        }
    }
}
