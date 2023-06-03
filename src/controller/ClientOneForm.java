package controller;/*
    @author Dasun
*/

import com.jfoenix.controls.JFXTextField;
import com.sun.xml.internal.messaging.saaj.soap.impl.TextImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ClientOneForm  extends Thread{

    public JFXTextField txtTextField;
    public VBox vBox;
    public ImageView imgSendImages;
    public Label lblUser;
    public FileChooser chooser;
    public File path;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;













    private String username;



    private PrintWriter printWriter;

    public void initialize() throws IOException {

        String userName = LoginForm.userName;
        lblUser.setText(userName);

        try {
            socket = new Socket("localhost", 8000);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendOnAction(ActionEvent actionEvent) {
        String massage = txtTextField.getText();
        printWriter.println(lblUser.getText() + ": " + massage);
        txtTextField.clear();
        printWriter.flush();
        if (massage.equalsIgnoreCase("exit")) {
            Stage stage = (Stage) txtTextField.getScene().getWindow();


            stage.close();
        }

    }

    public void sendImgClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        chooser = new FileChooser();
        chooser.setTitle("Open Image");
        this.path = chooser.showOpenDialog(stage);
        printWriter.println(lblUser.getText() + " " + "img" + path.getPath());
        printWriter.flush();


    }


    public void run() {
        try {
            while (true) {
                String massage = bufferedReader.readLine();
                String[] tokens = massage.split(" ");
                String command = tokens[0];

                StringBuilder clientMassage = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    clientMassage.append(tokens[i] + " ");
                }

                String[] massageAr = massage.split(" ");
                String string = "";
                for (int i = 0; i < massageAr.length - 1; i++) {
                    string += massageAr[i + 1] + " ";
                }

                Text text = new Text(string);
                String fChar = "";

                if (string.length() > 3) {
                    fChar = string.substring(0, 3);
                }

                if (fChar.equalsIgnoreCase("img")) {
                    string = string.substring(3, string.length() - 1);

                    File file = new File(string);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitWidth(150);
                    imageView.setFitHeight(200);

                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);

                    if (!command.equalsIgnoreCase(lblUser.getText())) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);

                        Text text1 = new Text("  " + command + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);
                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);
                    }

                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));

                } else {
                    TextFlow tempTextFlow = new TextFlow();

                    if (!command.equalsIgnoreCase(lblUser.getText() + ":")) {
                        Text name = new Text(command + " ");
                        name.getStyleClass().add("name");
                        tempTextFlow.getChildren().add(name);
                    }

                    tempTextFlow.getChildren().add(text);
                    tempTextFlow.setMaxWidth(200);

                    TextFlow textFlow = new TextFlow(tempTextFlow);
                    HBox hBox = new HBox(12);

                    if (!command.equalsIgnoreCase(lblUser.getText() + ":")) {
                        vBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(textFlow);
                    } else {
                        Text text1 = new Text(clientMassage + ": Me");
                        TextFlow textFlow1 = new TextFlow(text1);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(textFlow1);
                    }
                    Platform.runLater(() -> vBox.getChildren().addAll(hBox));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
