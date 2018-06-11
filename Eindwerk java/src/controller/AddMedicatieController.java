package controller;

import DAO.MedicatieDao;
import DAO.PersoneelDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Adress;
import model.Medicatie;
import model.Rol;
import model.User;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class AddMedicatieController {
    @FXML
    private TextField naam;

    @FXML
    void AddMedicatie(ActionEvent event) {
        naam.getStyleClass().remove("error");

        if (naam.getText() == null || naam.getText().trim().isEmpty()){
            naam.getStyleClass().add("error");

            Alert alertmis = new Alert(Alert.AlertType.ERROR);
            alertmis.setTitle("Medicatie toevoegen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Gelieve alle velden in te vullen!");
            alertmis.showAndWait();
        } else {
            if (Validation.checkLenght(naam.getText(), 4) == true && Validation.checkAlphabeticalAndNumeric(naam.getText()) == true){
                Medicatie medicatie = new Medicatie(naam.getText());
                Boolean add = MedicatieDao.addMedicatie(medicatie);
                if (add == true) {
                    // PlusHaze. (2016, 3 maart). Tray notification. Geraadpleegd op 29 januari 2018, van https://github.com/PlusHaze/TrayNotification
                    String title = "Toevoegen gelukt";
                    String message = "Medicatie is toegevoegd!";
                    TrayNotification tray = new TrayNotification(title, message, NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(4));
                    try {
                        URL paneUrl = getClass().getResource("../gui/Medicatie.fxml");
                        VBox pane = FXMLLoader.load(paneUrl);

                        BorderPane border = HomeController.getRoot();
                        border.setCenter(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alertmis = new Alert(Alert.AlertType.ERROR);
                    alertmis.setTitle("Toevoegen mislukt");
                    alertmis.setHeaderText(null);
                    alertmis.setContentText("Medicatie is niet toegevoegd! Probeer opnieuw!");
                    alertmis.showAndWait();
                }
            }else {
                naam.getStyleClass().add("error");

                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Medicatie toevoegen mislukt");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve alle velden correct in te vullen!");
                alertmis.showAndWait();
            }
        }
    }
}
