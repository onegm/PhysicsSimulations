package oneDimensionalKinematics.view;

import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

public class GraphsTab extends Tab{
    private final HBox hBox;

    public GraphsTab(String title, HBox hBox) {
        this.setText(title);
        this.hBox = hBox;
        this.setClosable(false);
        this.setContent(hBox);
    }
}
