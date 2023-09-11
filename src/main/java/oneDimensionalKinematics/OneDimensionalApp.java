package oneDimensionalKinematics;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.ApplicationStateController;
import controller.PropertyData;
import controller.TimelineController;
import oneDimensionalKinematics.view.InfoBar;
import view.*;
import view.simulation.Graph;
import util.event.ApplicationStateEvent;
import util.event.ApplicationStateRequest;
import util.event.EventBus;
import view.input.PropertySlider;
import view.input.Toolbar;
import oneDimensionalKinematics.view.CarView;
import oneDimensionalKinematics.view.MarkerView;
import view.simulation.SimulationPane;
import oneDimensionalKinematics.viewModel.CarViewModel;
import oneDimensionalKinematics.viewModel.MarkerViewModel;

import java.util.List;

public class OneDimensionalApp extends Application {
    @Override
    public void start(Stage stage) {
        EventBus eventBus = new EventBus();
        ApplicationStateController appStateController = new ApplicationStateController(eventBus);
        eventBus.addListener(ApplicationStateRequest.class, appStateController::handle);
        Toolbar toolbar = new Toolbar(eventBus);

        TimelineController timelineController60 = new TimelineController(60);
        eventBus.addListener(ApplicationStateEvent.class, timelineController60::handle);

        TimelineController timelineController1 = new TimelineController(1);
        eventBus.addListener(ApplicationStateEvent.class, timelineController1::handle);

        MarkerView markerView = new MarkerView();

        SimulationPane simulationPane = new SimulationPane(180);
        simulationPane.getChildren().add(markerView);

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
            CarViewModel carViewModel = new CarViewModel();
            timelineController60.addListener(carViewModel);
            CarView carView = new CarView(new Image(getClass().getResource(carImageNames[i]).toString()));
            carViewModel.getX().addListener(carView::setX);

            MarkerViewModel markerViewModel= new MarkerViewModel(markerView, carViewModel, carView);
            eventBus.addListener(ApplicationStateEvent.class, markerViewModel::handle);
            timelineController1.addListener(markerViewModel::mark);
            simulationPane.getChildren().add(carView);

            PropertyLabel positionLabel = new PropertyLabel("m");
            carViewModel.getInitialX().addListener(positionLabel::setValue);

            PropertySlider positionSlider = new PropertySlider(800);
            positionSlider.valueProperty().addListener(e -> carViewModel.getInitialX().setValue(positionSlider.getValue()));

            PropertyLabel velocityLabel = new PropertyLabel("m/s");
            carViewModel.getInitialSpeed().addListener(velocityLabel::setValue);

            PropertySlider velocitySlider = new PropertySlider(-50, 100);
            velocitySlider.valueProperty().addListener(e -> carViewModel.getInitialSpeed().setValue(velocitySlider.getValue()));

            PropertyLabel accelerationLabel = new PropertyLabel("m/s^2");
            carViewModel.getAcc().addListener(accelerationLabel::setValue);

            PropertySlider accelerationSlider = new PropertySlider(-10, 10);
            accelerationSlider.valueProperty().addListener(e -> carViewModel.getAcc().setValue(accelerationSlider.getValue()));
            accelerationSlider.setMinorTickCount(0);

            InfoBar infoBar = new InfoBar();
            carViewModel.getX().addListener(infoBar.getPositionLabel()::setValue);
            carViewModel.getSpeed().addListener(infoBar.getVelocityLabel()::setValue);

            positionPane.getChildren().addAll(positionSlider,positionLabel);
            velocityPane.getChildren().addAll(velocitySlider,velocityLabel);
            accelerationPane.getChildren().addAll(accelerationSlider,accelerationLabel);
            infoPane.getChildren().add(infoBar);

            PropertyData positionSeries = new PropertyData(carViewModel.getX());
            eventBus.addListener(ApplicationStateEvent.class, positionSeries::handle);
            timelineController1.addListener(positionSeries::addData);
            positionGraph.addSeries(positionSeries.getSeries());


            PropertyData velocitySeries = new PropertyData(carViewModel.getSpeed());
            eventBus.addListener(ApplicationStateEvent.class, velocitySeries::handle);
            timelineController1.addListener(velocitySeries::addData);
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

        MainView mainView = new MainView();
        mainView.getChildren().addAll(toolbar, simulationPane, controlsPane);

        Scene scene = new Scene(mainView);
        stage.setTitle("1D Kinematics");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
