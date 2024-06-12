package hangman;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HangmanController {
    private Stage stage;
    @FXML
    private Stage signupStage;
    private Scene scene;
    private Scene signupScene;
    private Parent root;
    @FXML
    private Text signup;
    @FXML
    private Text login;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField logInUsername;
    @FXML
    private TextField logInPassword;
    @FXML
    private Button back;
    @FXML
    private Button logInBack;
    @FXML
    private Button signup2;
    public void switchToMenu(ActionEvent event) {

    }

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
    public void setSignupStage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup-view.fxml"));
        signupScene = new Scene(root,200,200);
        signupStage = new Stage();
        signupStage.initModality(Modality.APPLICATION_MODAL);
        signupStage.setScene(signupScene);
        signupStage.setResizable(false);
        signupStage.show();
    }
    @FXML
    public void backClick(){
        back.setOnAction(actionEvent -> {
            Stage stage1 = (Stage) back.getScene().getWindow();
            stage1.close();
        });
    }
    @FXML
    public void logInBackClick(){
        logInBack.setOnAction(event -> {
            try {
                switchToScene1(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    public void signupClick2(){
        signup2.setOnAction(action -> {
            String usernameIn;
            String passwordIn;
            if (username.getText().equals("") || password.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input error");
                alert.setContentText("The text field cannot be empty");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("scratch_1.css").toExternalForm());
                dialogPane.getStyleClass().add("custom-alert");
                alert.showAndWait();
            }
            else {
                usernameIn = username.getText();
                passwordIn = password.getText();
                Stage stage1 = (Stage) signup2.getScene().getWindow();
                stage1.hide();
            }
        });
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