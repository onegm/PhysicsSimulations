package oneDimensionalKinematics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import oneDimensionalKinematics.controller.TimelineController;
import oneDimensionalKinematics.util.Animatable;
import oneDimensionalKinematics.view.*;
import oneDimensionalKinematics.viewModel.ApplicationViewModel;
import oneDimensionalKinematics.viewModel.CarViewModel;

import java.util.LinkedList;
import java.util.List;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        ApplicationViewModel appViewModel = new ApplicationViewModel();
        Toolbar toolbar = new Toolbar(appViewModel);
        MainView mainView = new MainView();

        CarView redCarView = new CarView(new Image(getClass().getResource("redCar.png").toString()));
        redCarView.setLayoutX(20);
        redCarView.setLayoutY(50);

        CarView blueCarView = new CarView(new Image(getClass().getResource("blueCar.png").toString()));
        blueCarView.setLayoutX(20);
        blueCarView.setLayoutY(120);

        List<Animatable> animatables = new LinkedList<>();
        CarViewModel redCarViewModel = new CarViewModel(redCarView);
        CarViewModel blueCarViewModel = new CarViewModel(blueCarView);
        animatables.add(redCarViewModel);
        animatables.add(blueCarViewModel);

        MarkerViewModel redMarkerViewModel = new MarkerViewModel(redCarViewModel, mainView);
        MarkerViewModel blueMarkerViewModel = new MarkerViewModel(blueCarViewModel, mainView);
        animatables.add(redMarkerViewModel);
        animatables.add(blueMarkerViewModel);

        TimelineController timelineController = new TimelineController();
        timelineController.addListeners(animatables);

        appViewModel.listenToAppState(timelineController::onAppStateChanged);

        mainView.getChildren().addAll(redCarView, toolbar, blueCarView);

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
