package controller.Verpleegster;

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

public class MenuVerpleegster {
    @FXML
    MenuBar myMenuBar;

    @FXML
    void switchToAllBewoners(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../../gui/BewonersVerpleegster.fxml");
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
    void instellingen(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../../gui/ProfielBewerken.fxml");
            AnchorPane pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logOut(ActionEvent event){
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        BorderPane border = HomeController.getRoot();
        border.setCenter(null);
        border.setTop(null);
        border.setDisable(true);
        stage.close();

        LoginController loginController = new LoginController();
        loginController.launchLogingController(stage);
        javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
