package oneDimensionalKinematics.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import oneDimensionalKinematics.viewModel.ApplicationState;
import oneDimensionalKinematics.viewModel.ApplicationViewModel;

public class Toolbar extends ToolBar {
    private final ApplicationViewModel appViewModel;

    public Toolbar(ApplicationViewModel appViewModel){
        this.appViewModel = appViewModel;

        Button start = new Button("Start");
        start.setOnAction(actionEvent -> handleStart());

        Button pause = new Button("Pause");
        pause.setOnAction(actionEvent -> handlePause());

        Button reset = new Button("Reset");
        reset.setOnAction(actionEvent -> handleReset());

        this.getItems().addAll(start, pause, reset);

        AnchorPane.setRightAnchor(this, 0d);
        AnchorPane.setLeftAnchor(this, 0d);
    }

    private void handleReset() {
        appViewModel.setState(ApplicationState.READY);
    }
    private void handlePause() {appViewModel.setState(ApplicationState.PAUSED);}
    private void handleStart() {appViewModel.setState(ApplicationState.RUNNING);}
}
