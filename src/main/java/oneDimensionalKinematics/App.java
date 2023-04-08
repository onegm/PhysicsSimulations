package oneDimensionalKinematics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import kinematics.Controller;
import oneDimensionalKinematics.model.Car;
import oneDimensionalKinematics.model.Marker;
import oneDimensionalKinematics.view.CarPane;
import oneDimensionalKinematics.view.CarView;
import oneDimensionalKinematics.view.MarkerView;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Marker marker = new Marker();
        marker.add(100, 100);
        marker.add(200, 100);
        marker.add(300, 100);
        marker.add(400, 100);
        marker.add(500, 100);
        MarkerView markerView = new MarkerView(marker);
        CarView carView = new CarView(new Image("redCar.png"));
        carView.setX(200);
        carView.setY(200);
        carView.setFitHeight(50);
        carView.setFitWidth(100);

        CarView[] carViews = {carView};
        MarkerView[] markerViews = {markerView};

        CarPane carPane = new CarPane(carViews, markerViews);

        Scene scene = new Scene(carPane);
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
