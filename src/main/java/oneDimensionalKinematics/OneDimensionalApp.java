package oneDimensionalKinematics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.ApplicationStateController;
import util.event.ApplicationStateRequest;
import util.event.EventBus;

public class OneDimensionalApp extends Application {
    @Override
    public void start(Stage stage) {
        EventBus eventBus = new EventBus();
        ApplicationStateController appStateController = new ApplicationStateController(eventBus);
        eventBus.addListener(ApplicationStateRequest.class, appStateController::handle);

        Motion1D motion1D = new Motion1D(eventBus);

        Scene scene = new Scene(motion1D);
        stage.setTitle("1D Motion");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
