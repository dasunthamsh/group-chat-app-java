package controller;/*
    @author Dasun
*/

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.Socket;

public class ClientOneForm  extends Thread{
    public Label lblUser;
    public VBox vBox;
    public JFXTextField txtTextField;
    public ImageView imgSendImages;
    public FileChooser fileChooser;
    public File path;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private PrintWriter printWriter;

    private void initialize() throws IOException {
        String userName = LoginForm.userName;
        lblUser.setText(userName);

        socket = new Socket("localhost",3000);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
        this.start();


    }

    public void sendOnAction(ActionEvent actionEvent) {

        String massage = txtTextField.getText();
        printWriter.println(lblUser.getText()+" : "+massage);
    }

    public void sendImgClicked(MouseEvent mouseEvent) {
    }
}
