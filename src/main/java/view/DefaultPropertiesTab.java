package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DefaultPropertiesTab extends Tab {
    private final VBox vBox;
    private DefaultPropertyPane property1;
    private DefaultPropertyPane property2;
    private DefaultPropertyPane property3;
    public DefaultPropertiesTab(String title){
        this.setText(title);
        this.vBox = new VBox();
        vBox.setFillWidth(true);
        this.setClosable(false);
        this.setContent(vBox);

        this.property1 = new DefaultPropertyPane("Initial Position", "m");
        this.property2 = new DefaultPropertyPane("Initial Velocity", "m/s");
        this.property3 = new DefaultPropertyPane("Acceleration", "m/s^2");

        this.vBox.getChildren().addAll(property1, property2, property3);
    }

    public void addChild(Node node){
        vBox.getChildren().add(node);
        vBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(node, Priority.ALWAYS);
    }

    public DefaultPropertyPane getProperty1() {
        return property1;
    }

    public DefaultPropertyPane getProperty2() {
        return property2;
    }

    public DefaultPropertyPane getProperty3() {
        return property3;
    }


}
