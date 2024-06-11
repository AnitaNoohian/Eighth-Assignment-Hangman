package hangman;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HangmanController {
    private Stage stage;
    private Stage signupStage;
    private Scene scene;
    private Scene signupScene;
    private Parent root;
    @FXML
    private Text signup;
    @FXML
    private Text login;
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hangman-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene2(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void signupClick(){
        signup.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                setSignupStage(event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void setSignupStage(MouseEvent event) throws IOException, InterruptedException {
        Parent root = FXMLLoader.load(getClass().getResource("signup-view.fxml"));
        signupStage = new Stage();
        signupScene = new Scene(root,200,200);
        signupStage.initModality(Modality.WINDOW_MODAL);
        signupStage.setScene(signupScene);
        signupStage.showAndWait();
    }
    @FXML
    public void loginClick(){
        login.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                switchToScene2(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    public void loginEnter(){
        login.setFill(Color.YELLOW);
    }
    @FXML
    public void loginExit(){
        login.setFill(Color.BLACK);
    }
    @FXML
    public void signupEnter(){
        signup.setFill(Color.YELLOW);
    }
    @FXML
    public void signupExit(){
        signup.setFill(Color.BLACK);
    }
}