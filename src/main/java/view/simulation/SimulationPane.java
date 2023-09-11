package view.simulation;

import javafx.scene.layout.Pane;

public class SimulationPane extends Pane {

    public SimulationPane(int height){
        this.setMaxHeight(height);
        this.setMinHeight(height);
    }
}
