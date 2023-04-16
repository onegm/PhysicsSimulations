package oneDimensionalKinematics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import oneDimensionalKinematics.controller.TimelineController;
import oneDimensionalKinematics.util.event.ApplicationStateEvent;
import oneDimensionalKinematics.util.event.EventBus;
import oneDimensionalKinematics.view.*;
import oneDimensionalKinematics.view.input.PropertySlider;
import oneDimensionalKinematics.view.input.Toolbar;
import oneDimensionalKinematics.view.simulation.CarView;
import oneDimensionalKinematics.view.simulation.MarkerView;
import oneDimensionalKinematics.view.simulation.SimulationPane;
import oneDimensionalKinematics.viewModel.CarViewModel;
import oneDimensionalKinematics.viewModel.MarkerViewModel;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        EventBus eventBus = new EventBus();
        Toolbar toolbar = new Toolbar(eventBus);

        TimelineController timelineController = new TimelineController();
        eventBus.addListener(ApplicationStateEvent.class, timelineController::handle);

        CarViewModel redCarViewModel = new CarViewModel();
        CarViewModel blueCarViewModel = new CarViewModel();
        timelineController.addListener(redCarViewModel);
        timelineController.addListener(blueCarViewModel);

        CarView redCarView = new CarView(new Image(getClass().getResource("redCar.png").toString()));
        CarView blueCarView = new CarView(new Image(getClass().getResource("blueCar.png").toString()));

        redCarViewModel.getX().addListener(redCarView::setX);
        blueCarViewModel.getX().addListener(blueCarView::setX);

        MarkerView markerView = new MarkerView();
        MarkerViewModel markerViewModel= new MarkerViewModel(markerView);
        markerViewModel.addCarView(redCarView);
        markerViewModel.addCarView(blueCarView);
        timelineController.addListener(markerViewModel);

        SimulationPane simulationPane = new SimulationPane();
        simulationPane.getChildren().addAll(markerView, redCarView, blueCarView);

        PropertyLabel redPositionLabel = new PropertyLabel("m");
        PropertyLabel bluePositionLabel = new PropertyLabel("m");
        redCarViewModel.getInitialX().addListener(redPositionLabel::setValue);
        blueCarViewModel.getInitialX().addListener(bluePositionLabel::setValue);

        PropertySlider redPositionSlider = new PropertySlider(800);
        PropertySlider bluePositionSlider = new PropertySlider(800);
        redPositionSlider.valueProperty().addListener(e -> redCarViewModel.getInitialX().setValue(redPositionSlider.getValue()));
        bluePositionSlider.valueProperty().addListener(e -> blueCarViewModel.getInitialX().setValue(bluePositionSlider.getValue()));

        PropertyLabel redVelocityLabel = new PropertyLabel("m/s");
        PropertyLabel blueVelocityLabel = new PropertyLabel("m/s");
        redCarViewModel.getInitialSpeed().addListener(redVelocityLabel::setValue);
        blueCarViewModel.getInitialSpeed().addListener(blueVelocityLabel::setValue);

        PropertySlider redVelocitySlider = new PropertySlider(100);
        PropertySlider blueVelocitySlider = new PropertySlider(100);
        redVelocitySlider.valueProperty().addListener(e -> redCarViewModel.getInitialSpeed().setValue(redVelocitySlider.getValue()));
        blueVelocitySlider.valueProperty().addListener(e -> blueCarViewModel.getInitialSpeed().setValue(blueVelocitySlider.getValue()));

        PropertyLabel redAccelerationLabel = new PropertyLabel("m/s^2");
        PropertyLabel blueAccelerationLabel = new PropertyLabel("m/s^2");
        redCarViewModel.getAcc().addListener(redAccelerationLabel::setValue);
        blueCarViewModel.getAcc().addListener(blueAccelerationLabel::setValue);

        PropertySlider redAccelerationSlider = new PropertySlider(10);
        PropertySlider blueAccelerationSlider = new PropertySlider(10);
        redAccelerationSlider.valueProperty().addListener(e -> redCarViewModel.getAcc().setValue(redAccelerationSlider.getValue()));
        blueAccelerationSlider.valueProperty().addListener(e -> blueCarViewModel.getAcc().setValue(blueAccelerationSlider.getValue()));
        redAccelerationSlider.setMinorTickCount(0);
        blueAccelerationSlider.setMinorTickCount(0);

        InfoBar redInfoBar = new InfoBar();
        InfoBar blueInfoBar = new InfoBar();
        redCarViewModel.getX().addListener(redInfoBar.getPositionLabel()::setValue);
        blueCarViewModel.getX().addListener(blueInfoBar.getPositionLabel()::setValue);
        redCarViewModel.getSpeed().addListener(redInfoBar.getVelocityLabel()::setValue);
        blueCarViewModel.getSpeed().addListener(blueInfoBar.getVelocityLabel()::setValue);

        PropertyPane positionPane = new PropertyPane();
        PropertyPane velocityPane = new PropertyPane();
        PropertyPane accelerationPane = new PropertyPane();
        PropertyPane infoPane = new PropertyPane();

        positionPane.getChildren().addAll(redPositionSlider,redPositionLabel, bluePositionLabel, bluePositionSlider);
        velocityPane.getChildren().addAll(redVelocitySlider,redVelocityLabel, blueVelocityLabel, blueVelocitySlider);
        accelerationPane.getChildren().addAll(redAccelerationSlider,redAccelerationLabel, blueAccelerationLabel, blueAccelerationSlider);
        infoPane.getChildren().addAll(redInfoBar, blueInfoBar);

        positionPane.getChildren().forEach(child -> PropertyPane.setHgrow(child, Priority.ALWAYS));
        velocityPane.getChildren().forEach(child -> PropertyPane.setHgrow(child, Priority.ALWAYS));
        accelerationPane.getChildren().forEach(child -> PropertyPane.setHgrow(child, Priority.ALWAYS));
        infoPane.getChildren().forEach(child -> PropertyPane.setHgrow(child, Priority.ALWAYS));

        PropertiesTab propertiesTab = new PropertiesTab("Properties");
        propertiesTab.addChild(positionPane);
        propertiesTab.addChild(velocityPane);
        propertiesTab.addChild(accelerationPane);
        propertiesTab.addChild(infoPane);

        Tab graphsTab = new Tab("Graphs");
        graphsTab.setClosable(false);

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
