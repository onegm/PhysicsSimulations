package oneDimensionalKinematics;

import controller.MarkerController;
import controller.PropertyData;
import controller.TimelineController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import model.PhysicalObject;
import oneDimensionalKinematics.view.CarInfoBar;
import oneDimensionalKinematics.view.CarView;
import util.event.ApplicationStateEvent;
import util.event.EventBus;
import view.*;
import view.input.PropertySlider;
import view.input.Toolbar;
import view.marker.Marker;
import view.marker.Marker1D;
import view.simulation.Graph;
import view.simulation.SimulationPane;

import java.util.List;

public class Motion1D extends MainView {

    public Motion1D(EventBus eventBus){
        Toolbar toolbar = new Toolbar(eventBus);

        TimelineController timelineController60 = new TimelineController(60);
        eventBus.addListener(ApplicationStateEvent.class, timelineController60::handle);

        TimelineController markerTimeline = new TimelineController(1);
        eventBus.addListener(ApplicationStateEvent.class, markerTimeline::handle);

        Marker marker = new Marker1D();

        SimulationPane simulationPane = new SimulationPane(180);
        simulationPane.getChildren().add(marker);

        PropertyPane positionPane = new PropertyPane();
        PropertyPane velocityPane = new PropertyPane();
        PropertyPane accelerationPane = new PropertyPane();
        PropertyPane infoPane = new PropertyPane();

        Graph positionGraph = new Graph("Position (m)");
        Graph velocityGraph = new Graph("Velocity (m/s)");
        eventBus.addListener(ApplicationStateEvent.class, positionGraph::handle);
        eventBus.addListener(ApplicationStateEvent.class, velocityGraph::handle);

        HBox graphsPane = new HBox();
        graphsPane.setSpacing(100);
        graphsPane.setPadding(new Insets(10, 30, 10, 30));
        graphsPane.getChildren().addAll(positionGraph, velocityGraph);
        graphsPane.getChildren().forEach(child -> HBox.setHgrow(child, Priority.ALWAYS));

        GraphsTab graphsTab = new GraphsTab("Graphs", graphsPane);

        String[] carImageNames = {"redCar.png", "blueCar.png"};
        for(int i = 0; i < 2; i++){
            PhysicalObject carViewModel = new PhysicalObject();
            timelineController60.addListener(carViewModel);
            CarView carView = new CarView(new Image(getClass().getResource(carImageNames[i]).toString()));
            carViewModel.getX().addListener(carView::setX);

            MarkerController markerController = new MarkerController(marker, carViewModel, carView);
            eventBus.addListener(ApplicationStateEvent.class, markerController::handle);
            markerTimeline.addListener(markerController::mark);
            simulationPane.getChildren().add(carView);

            PropertyLabel positionLabel = new PropertyLabel("m");
            carViewModel.getInitialX().addListener(positionLabel::setValue);

            PropertySlider positionSlider = new PropertySlider(800);
            positionSlider.valueProperty().addListener(e -> carViewModel.getInitialX().setValue(positionSlider.getValue()));

            PropertyLabel velocityLabel = new PropertyLabel("m/s");
            carViewModel.getInitialSpeedX().addListener(velocityLabel::setValue);

            PropertySlider velocitySlider = new PropertySlider(-50, 100);
            velocitySlider.valueProperty().addListener(e -> carViewModel.getInitialSpeedX().setValue(velocitySlider.getValue()));

            PropertyLabel accelerationLabel = new PropertyLabel("m/s^2");
            carViewModel.getAccX().addListener(accelerationLabel::setValue);

            PropertySlider accelerationSlider = new PropertySlider(-10, 10);
            accelerationSlider.valueProperty().addListener(e -> carViewModel.getAccX().setValue(accelerationSlider.getValue()));
            accelerationSlider.setMinorTickCount(0);

            CarInfoBar carInfoBar = new CarInfoBar();
            carViewModel.getX().addListener(carInfoBar.getPositionLabel()::setValue);
            carViewModel.getSpeedX().addListener(carInfoBar.getVelocityLabel()::setValue);

            positionPane.getChildren().addAll(positionSlider,positionLabel);
            velocityPane.getChildren().addAll(velocitySlider,velocityLabel);
            accelerationPane.getChildren().addAll(accelerationSlider,accelerationLabel);
            infoPane.getChildren().add(carInfoBar);

            PropertyData positionSeries = new PropertyData(carViewModel.getX());
            eventBus.addListener(ApplicationStateEvent.class, positionSeries::handle);
            markerTimeline.addListener(positionSeries::addData);
            positionGraph.addSeries(positionSeries.getSeries());


            PropertyData velocitySeries = new PropertyData(carViewModel.getSpeedX());
            eventBus.addListener(ApplicationStateEvent.class, velocitySeries::handle);
            markerTimeline.addListener(velocitySeries::addData);
            velocityGraph.addSeries(velocitySeries.getSeries());

        }

        List<Node> list1 = positionPane.getChildren().stream().toList();
        positionPane.getChildren().clear();
        positionPane.getChildren().addAll(list1.get(0), list1.get(1), list1.get(3), list1.get(2));

        List<Node> list2 = velocityPane.getChildren().stream().toList();
        velocityPane.getChildren().clear();
        velocityPane.getChildren().addAll(list2.get(0), list2.get(1), list2.get(3), list2.get(2));

        List<Node> list3 = accelerationPane.getChildren().stream().toList();
        accelerationPane.getChildren().clear();
        accelerationPane.getChildren().addAll(list3.get(0), list3.get(1), list3.get(3), list3.get(2));

        positionPane.getChildren().forEach(child -> PropertyPane.setHgrow(child, Priority.ALWAYS));
        velocityPane.getChildren().forEach(child -> PropertyPane.setHgrow(child, Priority.ALWAYS));
        accelerationPane.getChildren().forEach(child -> PropertyPane.setHgrow(child, Priority.ALWAYS));
        infoPane.getChildren().forEach(child -> PropertyPane.setHgrow(child, Priority.ALWAYS));

        PropertiesTab propertiesTab = new PropertiesTab("Properties");
        Label initPositionLabel = new Label("Initial Position");
        initPositionLabel.setFont(new Font("Arial Bold", 24));
        propertiesTab.addChild(initPositionLabel);
        propertiesTab.addChild(positionPane);
        Label initVelocityLabel = new Label("Initial Velocity");
        initVelocityLabel.setFont(new Font("Arial Bold", 24));
        propertiesTab.addChild(initVelocityLabel);
        propertiesTab.addChild(velocityPane);
        Label initAccelerationLabel = new Label("Acceleration");
        initAccelerationLabel.setFont(new Font("Arial Bold", 24));
        propertiesTab.addChild(initAccelerationLabel);
        propertiesTab.addChild(accelerationPane);
        propertiesTab.addChild(infoPane);

        ControlsPane controlsPane = new ControlsPane();
        controlsPane.getTabs().addAll(propertiesTab, graphsTab);

        this.getChildren().addAll(toolbar, simulationPane, controlsPane);
    }
}
