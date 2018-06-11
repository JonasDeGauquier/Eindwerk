package controller;

import DAO.AdressDao;
import DAO.BewonerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Adress;
import model.Bewoner;
import model.Contactpersoon;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;

public class AddContactpersoonController {
    private Contactpersoon contactpersoon = new Contactpersoon();
    private Bewoner bewoner = new Bewoner();

    @FXML
    private TextField voornaam, achternaam, identiteitskaartnr, relatie, telefoon, email, straat, huisnr, gemeente, postcode;
    Adress a = new Adress();

    @FXML
    void addContactpersoon(ActionEvent event) {
        voornaam.getStyleClass().remove("error");
        achternaam.getStyleClass().remove("error");
        identiteitskaartnr.getStyleClass().remove("error");
        relatie.getStyleClass().remove("error");
        telefoon.getStyleClass().remove("error");
        email.getStyleClass().remove("error");
        straat.getStyleClass().remove("error");
        huisnr.getStyleClass().remove("error");
        gemeente.getStyleClass().remove("error");
        postcode.getStyleClass().remove("error");

        if (voornaam.getText() == null || voornaam.getText().trim().isEmpty() || achternaam.getText() == null || achternaam.getText().trim().isEmpty() || identiteitskaartnr.getText() == null || identiteitskaartnr.getText().trim().isEmpty() || relatie.getText() == null || relatie.getText().trim().isEmpty() || telefoon.getText() == null || telefoon.getText().trim().isEmpty()
                || email.getText() == null || email.getText().trim().isEmpty() || straat.getText() == null || straat.getText().trim().isEmpty() || huisnr.getText() == null || huisnr.getText().trim().isEmpty() || gemeente.getText() == null || gemeente.getText().trim().isEmpty() || postcode.getText() == null || postcode.getText().trim().isEmpty()) {
            if (voornaam.getText() == null || voornaam.getText().trim().isEmpty()) {
                voornaam.getStyleClass().add("error");
            }
            if (achternaam.getText() == null || achternaam.getText().trim().isEmpty()) {
                achternaam.getStyleClass().add("error");
            }
            if (identiteitskaartnr.getText() == null || identiteitskaartnr.getText().trim().isEmpty()) {
                identiteitskaartnr.getStyleClass().add("error");
            }
            if (relatie.getText() == null || relatie.getText().trim().isEmpty()) {
                relatie.getStyleClass().add("error");
            }
            if (telefoon.getText() == null || telefoon.getText().trim().isEmpty()) {
                telefoon.getStyleClass().add("error");
            }
            if (email.getText() == null || email.getText().trim().isEmpty()) {
                email.getStyleClass().add("error");
            }
            if (straat.getText() == null || straat.getText().trim().isEmpty()) {
                straat.getStyleClass().add("error");
            }
            if (huisnr.getText() == null || huisnr.getText().trim().isEmpty()) {
                huisnr.getStyleClass().add("error");
            }
            if (gemeente.getText() == null || gemeente.getText().trim().isEmpty()) {
                gemeente.getStyleClass().add("error");
            }
            if (postcode.getText() == null || postcode.getText().trim().isEmpty()) {
                postcode.getStyleClass().add("error");
            }
            Alert alertmis = new Alert(Alert.AlertType.ERROR);
            alertmis.setTitle("Aanpassen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Gelieve alle velden in te vullen!");
            alertmis.showAndWait();
        } else {
            if (Validation.checkFirstName(voornaam.getText()) == true && Validation.checkLastName(achternaam.getText()) == true && Validation.checkIdentitiecard(identiteitskaartnr.getText()) == true && Validation.checkAlphabetical(relatie.getText()) == true && Validation.checkPhone(telefoon.getText()) == true
                    && Validation.checkEmail(email.getText()) == true && Validation.checkAlphabetical(straat.getText()) == true && Validation.checkHouseNumber(huisnr.getText()) == true && Validation.checkAlphabetical(gemeente.getText()) == true && Validation.checkPostalCode(postcode.getText()) == true) {
                Adress adres = new Adress(straat.getText(), Integer.parseInt(huisnr.getText()), gemeente.getText(), Integer.parseInt(postcode.getText()));
                contactpersoon = new Contactpersoon(bewoner, adres, voornaam.getText(), achternaam.getText(), Integer.parseInt(telefoon.getText()), email.getText(),
                        relatie.getText(), identiteitskaartnr.getText());
                Boolean add;
                add = BewonerDao.addContactpersoon(contactpersoon);
                if (add == true) {
                    // PlusHaze. (2016, 3 maart). Tray notification. Geraadpleegd op 29 januari 2018, van https://github.com/PlusHaze/TrayNotification
                    String title = "Toevoegen gelukt";
                    String message = "Contactpersoon is toegevoegd!";
                    TrayNotification tray = new TrayNotification(title, message, NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(4));
                    try {
                        URL paneUrl = getClass().getResource("../gui/Bewoner.fxml");
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
                    alertmis.setContentText("Contactpersoon is niet toegeveogd! Probeer opnieuw!");
                    alertmis.showAndWait();
                }
            } else {
                if (Validation.checkFirstName(voornaam.getText()) == false) {
                    voornaam.getStyleClass().add("error");
                }
                if (Validation.checkLastName(achternaam.getText()) == false) {
                    achternaam.getStyleClass().add("error");
                }
                if (Validation.checkIdentitiecard(identiteitskaartnr.getText()) == false) {
                    identiteitskaartnr.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(relatie.getText()) == false) {
                    relatie.getStyleClass().add("error");
                }
                if (Validation.checkPhone(telefoon.getText()) == false) {
                    telefoon.getStyleClass().add("error");
                }
                if (Validation.checkEmail(email.getText()) == false) {
                    email.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(straat.getText()) == false) {
                    straat.getStyleClass().add("error");
                }
                if (Validation.checkHouseNumber(huisnr.getText()) == false) {
                    huisnr.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(gemeente.getText()) == false) {
                    gemeente.getStyleClass().add("error");
                }
                if (Validation.checkPostalCode(postcode.getText()) == false) {
                    postcode.getStyleClass().add("error");
                }
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Toevoegen mislukt");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve alle velden correct in te vullen!");
                alertmis.showAndWait();
            }
        }
    }
}
