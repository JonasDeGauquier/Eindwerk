package controller;

import DAO.BewonerDao;
import DAO.PersoneelDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.Adress;
import model.Rol;
import model.User;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ProfielbewerkenController implements Initializable {
    @FXML
    private TextField voornaam;
    @FXML
    private TextField achternaam;
    @FXML
    private DatePicker geboortedatum;
    @FXML
    private TextField email;
    @FXML
    private TextField straat;
    @FXML
    private TextField huisnr;
    @FXML
    private TextField gemeente;
    @FXML
    private TextField postcode;

    LocalDate date;
    User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = PersoneelDao.getPersoneel(user.getCurrentUser());
        voornaam.setText(String.valueOf(user.getVoornaam().toString()));
        achternaam.setText(String.valueOf(user.getAchternaam().toString()));
        email.setText(String.valueOf(user.getEmail().toString()));
        straat.setText(String.valueOf(user.getAdress().getStraat().toString()));
        huisnr.setText(String.valueOf(user.getAdress().getHuisnr()));
        gemeente.setText(String.valueOf(user.getAdress().getGemeente().toString()));
        postcode.setText(String.valueOf(user.getAdress().getPostcode().toString()));
        user.getAdress().setId(user.getAdress().getId());

        // Bron: https://stackoverflow.com/questions/18873014/parse-string-date-in-yyyy-mm-dd-format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedGeboortedatum = null;
        try {
            convertedGeboortedatum = sdf.parse(String.valueOf(user.getGeboortedatum()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Bron: https://stackoverflow.com/questions/9474121/i-want-to-get-year-month-day-etc-from-java-date-to-compare-with-gregorian-cal
        LocalDate localDateGeboortedatum = convertedGeboortedatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        geboortedatum.setValue(LocalDate.of(localDateGeboortedatum.getYear(), localDateGeboortedatum.getMonth(), localDateGeboortedatum.getDayOfMonth()));
    }

    @FXML
    public void EditProfiel(){
        voornaam.getStyleClass().remove("error");
        achternaam.getStyleClass().remove("error");
        straat.getStyleClass().remove("error");
        huisnr.getStyleClass().remove("error");
        gemeente.getStyleClass().remove("error");
        postcode.getStyleClass().remove("error");
        email.getStyleClass().remove("error");

        if (voornaam.getText() == null || voornaam.getText().trim().isEmpty() || achternaam.getText() == null || achternaam.getText().trim().isEmpty() || geboortedatum.getValue() == null || email.getText() == null || email.getText().trim().isEmpty()
                || straat.getText() == null || straat.getText().trim().isEmpty() || huisnr.getText() == null || huisnr.getText().trim().isEmpty() || gemeente.getText() == null || gemeente.getText().trim().isEmpty() || postcode.getText() == null || postcode.getText().trim().isEmpty())
        {
            if (voornaam.getText() == null || voornaam.getText().trim().isEmpty())
            {
                voornaam.getStyleClass().add("error");
            }
            if (achternaam.getText() == null || achternaam.getText().trim().isEmpty())
            {
                achternaam.getStyleClass().add("error");
            }
            if (straat.getText() == null || straat.getText().trim().isEmpty())
            {
                straat.getStyleClass().add("error");
            }
            if (huisnr.getText() == null || huisnr.getText().trim().isEmpty())
            {
                huisnr.getStyleClass().add("error");
            }
            if (gemeente.getText() == null || gemeente.getText().trim().isEmpty())
            {
                gemeente.getStyleClass().add("error");
            }
            if (postcode.getText() == null || postcode.getText().trim().isEmpty())
            {
                postcode.getStyleClass().add("error");
            }
            if (email.getText() == null || email.getText().trim().isEmpty())
            {
                email.getStyleClass().add("error");
            }
            Alert alertmis = new Alert(Alert.AlertType.ERROR);
            alertmis.setTitle("Aanpassen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Gelieve alle velden in te vullen!");
            alertmis.showAndWait();
        }  else {
            if (Validation.checkFirstName(voornaam.getText().toString()) == true && Validation.checkLastName(achternaam.getText().toString()) == true && Validation.checkAlphabetical(straat.getText().toString()) == true && Validation.checkHouseNumber(huisnr.getText().toString()) == true && Validation.checkAlphabetical(gemeente.getText().toString()) == true && Validation.checkPostalCode(postcode.getText().toString()) == true && Validation.checkEmail(email.getText().toString()) == true) {
                date = geboortedatum.getValue();
                Date sqlDate = java.sql.Date.valueOf(date);
                Adress adres = new Adress(user.getAdress().getId(), straat.getText().toString(), Integer.parseInt(huisnr.getText()), gemeente.getText().toString(), Integer.parseInt(postcode.getText()));
                User personeel = new User(voornaam.getText().toString(), achternaam.getText().toString(), sqlDate, email.getText().toString(), adres);
                Boolean add = PersoneelDao.editProfiel(personeel);
                if (add == true) {
                    // Bron: https://github.com/PlusHaze/TrayNotification
                    String title = "Aanpassen gelukt";
                    String message = "Uw profiel is aangepast!";
                    TrayNotification tray = new TrayNotification(title, message, NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(4));
                } else {
                    Alert alertmis = new Alert(Alert.AlertType.CONFIRMATION);
                    alertmis.setTitle("Aanpassen mislukt");
                    alertmis.setHeaderText(null);
                    alertmis.setContentText("Uw profiel is niet aangepast! Probeer opnieuw!");
                    alertmis.showAndWait();
                }
            } else {
                if (Validation.checkFirstName(voornaam.getText().toString()) == false) {
                    voornaam.getStyleClass().add("error");
                }
                if (Validation.checkLastName(achternaam.getText().toString()) == false) {
                    achternaam.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(straat.getText().toString()) == false)
                {
                    straat.getStyleClass().add("error");
                }
                if (Validation.checkHouseNumber(huisnr.getText().toString()) == false)
                {
                    huisnr.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(gemeente.getText().toString()) == false)
                {
                    gemeente.getStyleClass().add("error");
                }
                if (Validation.checkPostalCode(postcode.getText().toString()) == false)
                {
                    postcode.getStyleClass().add("error");
                }
                if (Validation.checkEmail(email.getText().toString()) == false)
                {
                    email.getStyleClass().add("error");
                }
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Aanpassen mislukt");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve alle velden correct in te vullen!");
                alertmis.showAndWait();
            }
        }
    }
}
