package view.input;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import util.event.ApplicationStateRequest;
import util.event.EventBus;

import static util.event.ApplicationStateEvent.Type.*;

public class Toolbar extends ToolBar {
    private final EventBus eventBus;

    public Toolbar(EventBus eventBus){
        this.eventBus = eventBus;

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

    private void handleStart() {
        eventBus.emit(new ApplicationStateRequest(START));
    }
    private void handlePause() {
        eventBus.emit(new ApplicationStateRequest(PAUSE));}
    private void handleReset() {
        eventBus.emit(new ApplicationStateRequest(RESET));
    }

}
