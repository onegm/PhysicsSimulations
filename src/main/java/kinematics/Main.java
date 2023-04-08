package kinematics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("kinematics.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Controller controller = fxmlLoader.getController();
        stage.setTitle("1D Kinematics");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(scene);

        stage.show();

        stage.setOnCloseRequest(event -> {
            controller.shutDown();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}