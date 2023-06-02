import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Appinitilizer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/group_chat_application/view/login-from.fxml"));
        Scene mainScene = new Scene(parent);
        primaryStage.setTitle("Live Chat");
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }
}
