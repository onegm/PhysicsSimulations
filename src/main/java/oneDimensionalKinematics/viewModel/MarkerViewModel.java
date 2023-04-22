package oneDimensionalKinematics.viewModel;

import oneDimensionalKinematics.util.event.ApplicationStateEvent;
import oneDimensionalKinematics.view.simulation.CarView;
import oneDimensionalKinematics.view.simulation.MarkerView;


public class MarkerViewModel  {
    private final MarkerView markerView;
    private final CarViewModel carViewModel;
    private final CarView carView;
    private double time;

    public MarkerViewModel(MarkerView markerView, CarViewModel carViewModel, CarView carView) {
        this.markerView = markerView;
        this.carViewModel = carViewModel;
        this.carView = carView;
    }

    public void mark(double value){
        this.time += value;
        markerView.mark(carView.getLayoutX() + carView.getX(),
                carView.getY() + carView.getFitHeight()/2, carViewModel.getSpeed().getValue(), time);
    }
    public void handle(ApplicationStateEvent event){
        if(event.getEventType() == ApplicationStateEvent.Type.RESET){
            markerView.reset();
            this.time = 0;
        }
        if(event.getEventType() == ApplicationStateEvent.Type.START){
            mark(0);
        }
    }
}
