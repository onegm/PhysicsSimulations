package oneDimensionalKinematics.view;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import oneDimensionalKinematics.util.Animatable;
import oneDimensionalKinematics.viewModel.CarViewModel;

import java.util.ArrayList;


public class MarkerViewModel implements Animatable {
    private final CarViewModel carViewModel;
    private final Pane pane;
    private  ArrayList<Circle> circles;
    private double timeSinceLastMark;
    public static final double MARK_SIZE = 5.0;

    public MarkerViewModel(CarViewModel carViewModel, Pane pane) {
        this.carViewModel = carViewModel;
        this.pane = pane;
        circles = new ArrayList<>();
    }

    public void doStep(double timeSinceLastFrame){
        timeSinceLastMark += timeSinceLastFrame;
        if(timeSinceLastMark < 1){
            return;
        }
        Circle circle = new Circle(carViewModel.getCenterLocation().getKey(),
                carViewModel.getCenterLocation().getValue(),
                MARK_SIZE);
        circles.add(circle);
        pane.getChildren().add(circle);
        timeSinceLastMark = 0;
    }

    public void reset(){
        pane.getChildren().removeAll(circles);
        circles = new ArrayList<>();
    }
}
