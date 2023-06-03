package controller;/*
    @author Dasun
*/

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginForm {
    public JFXTextField txtUserName;

    public static String userName;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        userName =txtUserName.getText();
        Stage stage = (Stage) txtUserName.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ClientOneForm.fxml"))));
        stage1.setTitle("Online Chat ");
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.show();

    }
}
