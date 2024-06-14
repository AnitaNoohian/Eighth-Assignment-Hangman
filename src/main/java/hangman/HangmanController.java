package hangman;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HangmanController {
    @FXML
    private ImageView hang1;
    @FXML
    private ImageView hang2;
    @FXML
    private ImageView hang3;
    @FXML
    private ImageView tail;
    @FXML
    private ImageView head;
    @FXML
    private ImageView body;
    @FXML
    private ImageView hand1;
    @FXML
    private ImageView hand2;
    @FXML
    private ImageView foot1;
    @FXML
    private ImageView foot2;
    @FXML
    private TextFlow textFlow = new TextFlow();
    @FXML
    private Text textNode ;
    int mistake = 0;
    int correct = 0;
    int test = 0;
    private static final String[] WORDS = {"programming", "java", "hangman", "keyboard", "code"};
    private static String secretWord;
    private Stage stage;
    private Stage signupStage;
    private Stage gameOverStage;
    private Scene scene;
    private Scene signupScene;
    private Scene gameOverScene;
    @FXML
    private Text startGame;
    @FXML
    private Text gameInfo;
    @FXML
    private Text leaderBoard;
    @FXML
    private Text exit;
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
    @FXML
    private Button logIn2;
    @FXML
    private Button gameBack;
    private String chooseRandomWord() {
        Random random = new Random();
        return WORDS[random.nextInt(WORDS.length)].toLowerCase();
    }
    @FXML
    public void hideButton(ActionEvent actionEvent) throws IOException {
        Button clickedButton = (Button) actionEvent.getSource();
        String selectletter = clickedButton.getText();
        clickedButton.setVisible(false);
        if (test == 0){
            showUnderLine();
        }
        test = 1;
        Boolean lose = true;
        for (int i = 0 ; i <  secretWord.length(); i++ ){
            if ( secretWord.charAt(i)+'A'-'a' == selectletter.charAt(0)){
                Text replace = new Text(selectletter);
                replace.setFont(Font.font("Arial", 40));
                replace.setFill(Color.YELLOW);
                textFlow.getChildren().set(i+1,replace);
                lose = false;
                correct += 1;
            }
        }
        if (lose){
            mistake = mistake + 1;
        }
        if (mistake == 11){
            gameOver();
        }
        if (correct == secretWord.length()){
            winTheGame();
        }
        showHangMan();
    }

    public void showUnderLine(){
        secretWord = chooseRandomWord();
        for (int i = 0; i < secretWord.length(); i++) {
            textNode = new Text("_ ");
            textNode.setFont(Font.font("Arial", 40));
            textNode.setFill(Color.YELLOW);
            textFlow.getChildren().add(textNode);
        }
        textFlow.setVisible(true);
    }
    public void showHangMan(){
        if (mistake > 0){
            hang1.setVisible(true);
        }
        if(mistake > 1){
            hang2.setVisible(true);
        }
        if (mistake > 2){
            hang3.setVisible(true);
        }
        if (mistake > 3){
            head.setVisible(true);
        }
        if (mistake > 4){
            body.setVisible(true);
        }
        if (mistake > 5){
            hand1.setVisible(true);
        }
        if (mistake > 6){
            hand2.setVisible(true);
        }
        if(mistake > 7){
            foot1.setVisible(true);
        }
        if (mistake > 8){
            foot2.setVisible(true);
        }
        if (mistake > 9){
            tail.setVisible(true);
        }
    }
    public void winTheGame() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WIN");
        alert.setHeaderText("WIN");
        alert.setContentText("You find the word^^");
        alert.showAndWait();
        Stage stage1 = (Stage) gameBack.getScene().getWindow();
        stage1.close();
        Parent root = FXMLLoader.load(getClass().getResource("menu-view.fxml"));
        stage = new Stage();
        scene = new Scene(root,500,650);
        stage.setScene(scene);
        stage.show();
    }
    public void gameOver() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText("GAME OVER");
        alert.setContentText("You lose the game:(");
        alert.showAndWait();
        Stage stage1 = (Stage) gameBack.getScene().getWindow();
        stage1.close();
        Parent root = FXMLLoader.load(getClass().getResource("menu-view.fxml"));
        stage = new Stage();
        scene = new Scene(root,500,650);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToPlay(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("play-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //showUnderLine();
    }
    public void switchToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,500,650);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene1(MouseEvent event) throws IOException {
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
    public void exitClick(){
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                switchToScene1(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    public void startGameClick(){
        startGame.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                switchToPlay(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    public void logInBackClick(){
        logInBack.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                switchToScene1(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @FXML
    public void gameBack(){
        gameBack.setOnAction(actionEvent -> {
            try {
                switchToMenu(actionEvent);
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
    public void logInClick2(){
        logIn2.setOnAction(event -> {
            try {
                switchToMenu(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
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
    @FXML
    public void exitEnter(){
        exit.setFill(Color.YELLOW);
    }
    @FXML
    public void exitExit(){
        exit.setFill(Color.BLACK);
    }
    @FXML
    public void gameInfoEnter(){
        gameInfo.setFill(Color.YELLOW);
    }
    @FXML
    public void gameInfoExit(){
        gameInfo.setFill(Color.BLACK);
    }
    @FXML
    public void leaderBoardEnter(){
        leaderBoard.setFill(Color.YELLOW);
    }
    @FXML
    public void leaderBoardExit(){
        leaderBoard.setFill(Color.BLACK);
    }
    @FXML
    public void startGameEnter(){
        startGame.setFill(Color.YELLOW);
    }
    @FXML
    public void startGameExit(){
        startGame.setFill(Color.BLACK);
    }
}