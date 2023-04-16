package oneDimensionalKinematics.view;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class PropertiesTab extends Tab {
    private final VBox vBox;
    public PropertiesTab(String title){
        this.setText(title);
        this.vBox = new VBox();
        vBox.setFillWidth(true);
        this.setClosable(false);
        this.setContent(vBox);
    }

    public void addChild(Node node){
        vBox.getChildren().add(node);
    }
}
