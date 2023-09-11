package projectileMotion;

import controller.ApplicationStateController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import controller.TimelineController;
import util.event.ApplicationStateEvent;
import util.event.ApplicationStateRequest;
import util.event.EventBus;
import view.ControlsPane;
import view.GraphsTab;
import view.MainView;
import view.PropertiesTab;
import view.input.Toolbar;
import view.simulation.SimulationPane;
import viewModels.PhysicalObject;

public class ProjectileMotionApp extends Application {
    @Override
    public void start(Stage stage){
        MainView mainView = new MainView();

        SimulationPane simulationPane = new SimulationPane(300);
        ControlsPane controlsPane = new ControlsPane();

        EventBus eventBus = new EventBus();

        ApplicationStateController appStateController = new ApplicationStateController(eventBus);
        eventBus.addListener(ApplicationStateRequest.class, appStateController::handle);

        TimelineController timelineController60 = new TimelineController(60);
        eventBus.addListener(ApplicationStateEvent.class, timelineController60::handle);

        Toolbar toolbar = new Toolbar(eventBus);

        PropertiesTab propertiesTab = new PropertiesTab("Properties");
        GraphsTab graphsTab = new GraphsTab("Graphs", new HBox());
        controlsPane.getTabs().addAll(propertiesTab, graphsTab);

        PhysicalObject ball = new PhysicalObject();
        eventBus.addListener(ApplicationStateEvent.class, ball::handle);
        ball.getXSpeed().setValue(-10.0);
        ball.getYSpeed().setValue(20.0);
        ball.getYAcc().setValue(-9.8);
        timelineController60.addListener(ball);


        Circle circle = new Circle(ball.getXValue(), ball.getYValue(), 10);
        simulationPane.getChildren().add(circle);

        Rotate rotate = new Rotate(180, circle.getCenterX(), simulationPane.getMaxHeight()/2);
        circle.getTransforms().add(rotate);

        ball.getX().addListener(circle::setCenterX);
        ball.getY().addListener(circle::setCenterY);

        mainView.getChildren().addAll(toolbar, simulationPane, controlsPane);

        Scene scene = new Scene(mainView);
        stage.setTitle("Projectile Motion");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
