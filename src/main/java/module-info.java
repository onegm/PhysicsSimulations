module kinematics.physicssimulations {
    requires javafx.controls;
    requires javafx.fxml;


    opens kinematics to javafx.fxml;
    exports kinematics;
}