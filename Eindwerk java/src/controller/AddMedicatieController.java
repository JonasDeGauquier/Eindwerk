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
import model.Adress;
import model.Medicatie;
import model.Rol;
import model.User;

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
            if (Validation.checkLenght(naam.getText().toString(), 4) == true && Validation.checkAlphabeticalAndNumeric(naam.getText().toString()) == true){
                Medicatie medicatie = new Medicatie(naam.getText().toString());
                Boolean add = MedicatieDao.addMedicatie(medicatie);
                if (add == true) {
                    Alert alertsuc = new Alert(Alert.AlertType.CONFIRMATION);
                    alertsuc.setTitle("Toevoegen gelukt");
                    alertsuc.setHeaderText(null);
                    alertsuc.setContentText("Medicatie is toegevoegd!");
                    alertsuc.showAndWait();
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
