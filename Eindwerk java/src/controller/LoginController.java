package controller;

import DAO.LoginDao;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Login;
import model.User;

public class LoginController {

    private Parent parent;
    private Scene scene;
    private Stage stage;

    private HomeController homeController;
    private static Login login;
    private static User user;

    @FXML
    private TextField username = new TextField();
    @FXML
    private PasswordField passwordField = new PasswordField();

    public LoginController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/Login.fxml"));
        fxmlLoader.setController(this);
        try {
            parent = (Parent) fxmlLoader.load();
            scene = new Scene(parent, 600, 400);
            scene.getStylesheets().add("css/Layout.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException{
        Login();
    }

    @FXML
    protected void loginWithBadge(ActionEvent event) throws IOException{
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Text Input Dialog");
        dialog.setContentText(" scan your badge");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String rfid = result.get();
            if (rfid.length() == 10 )
            {
                if (LoginDao.checkLoginrfid(Integer.valueOf(rfid)) == true){
                    homeController = new HomeController();
                    homeController.redirectHomeRFID(stage, rfid);
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login niet gelukt");
                    alert.setHeaderText(null);
                    alert.setContentText("Badge niet gevonden. Probeer opnieuw!");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login niet gelukt");
                alert.setHeaderText(null);
                alert.setContentText("Badge niet gevonden. Probeer opnieuw!");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login niet gelukt");
            alert.setHeaderText(null);
            alert.setContentText("Badge niet gevonden. Probeer opnieuw!");
            alert.showAndWait();
        }
    }

    public void Login() throws IOException{
        username.setStyle("-fx-background-color: white;");
        passwordField.setStyle("-fx-background-color: white;");

        if (username.getText().trim().length() > 0 && passwordField.getText().trim().length() > 0) {
            // Bron: https://codereview.stackexchange.com/questions/137964/string-hash-generator
            MessageDigest objMD5 = null;
            try {
                objMD5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] bytMD5 = objMD5.digest(passwordField.getText().getBytes());
            BigInteger intNumMD5 = new BigInteger(1, bytMD5);
            String password = String.format("%032x", intNumMD5);

            if (LoginDao.checkLogin(username.getText(), password)==true){
                login = new Login(username.getText(), password);
                homeController = new HomeController();
                homeController.redirectHome(stage);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login niet gelukt");
                alert.setHeaderText(null);
                alert.setContentText("Gebruikersnaam of wachtwoord komen niet overeen. Probeer opnieuw!");
                alert.showAndWait();
                passwordField.setText("");
            }
        }
        else{
            if (username.getText().trim().length() == 0){
                username.setStyle("-fx-background-color: red;");
            }
            if (passwordField.getText().trim().length() == 0){
                passwordField.setStyle("-fx-background-color: red;");
            }
        }
    }

    public void launchLogingController(Stage stage) {
        this.stage = stage;
        stage.setTitle("Log in");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.hide();
        stage.show();
    }
}
