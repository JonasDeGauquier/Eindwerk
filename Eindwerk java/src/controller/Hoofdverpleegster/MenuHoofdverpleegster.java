package controller.Hoofdverpleegster;

import controller.HomeController;
import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MenuHoofdverpleegster {
    @FXML
    MenuBar myMenuBar;
    @FXML
    private MenuItem ShowAllBewoners;

    @FXML
    private MenuItem AddBewoner;

    @FXML
    private MenuItem ShowZorgplan;

    @FXML
    private MenuItem AddZorgtaak;
    @FXML
    private MenuItem LogOut;
    @FXML
    private MenuItem Personeel;


    @FXML
    void switchToAllBewoners(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../../gui/BewonersHoofdverpleegster.fxml");
            VBox pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToZorgplan(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../../gui/Zorgplan.fxml");
            Pane pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToZorgtaak(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../gui/Zorgtaak.fxml");
            Pane pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToMedicatie(ActionEvent event){
        try {
            URL paneUrl = getClass().getResource("../gui/Medicatie.fxml");
            Pane pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logOut(ActionEvent event){
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.close();

        LoginController loginController = new LoginController();
        loginController.launchLogingController(stage);
        javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
