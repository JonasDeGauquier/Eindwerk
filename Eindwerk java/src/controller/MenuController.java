package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

public class MenuController{
    @FXML
    MenuBar myMenuBar;

    @FXML
    void switchToAllBewoners(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../gui/Bewoner.fxml");
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
            URL paneUrl = getClass().getResource("../gui/Zorgplan.fxml");
            Pane pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToShowZorgplanByPersoneel(ActionEvent event){
        try {
            URL paneUrl = getClass().getResource("../gui/ZorgplanViaPersoneel.fxml");
            Pane pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToShowZorgplanByBewoner(ActionEvent event){
        try {
            URL paneUrl = getClass().getResource("../gui/ZorgplanViaBewoner.fxml");
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
    void switchToPersonneel(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../gui/Personeel.fxml");
            VBox pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void zorgplanViaPersoneel(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../gui/zorgplanViaPersoneel.fxml");
            VBox pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void zorgplanViaBewoner(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../gui/zorgplanViaBewoner.fxml");
            VBox pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AddPersoneel(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../gui/PersoneelToevoegen.fxml");
            AnchorPane pane = FXMLLoader.load(paneUrl);

            BorderPane border = HomeController.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void instellingen(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../gui/ProfielBewerken.fxml");
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
