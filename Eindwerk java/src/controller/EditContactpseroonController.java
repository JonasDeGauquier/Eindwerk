package controller;

import DAO.AdressDao;
import DAO.BewonerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Adress;
import model.Bewoner;
import model.BewonersDossier;
import model.Contactpersoon;

import java.net.URL;
import java.util.ResourceBundle;

public class EditContactpseroonController implements Initializable{
    private Contactpersoon contactpersoon = new Contactpersoon();
    private Bewoner bewoner = new Bewoner();

    @FXML
    private TextField voornaam, achternaam, identiteitskaartnr, relatie, telefoon ,email , straat, huisnr, gemeente, postcode;
    Adress a = new Adress();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactpersoon = BewonerDao.getContactpersoon(bewoner.getSelectedId());

        voornaam.setText(String.valueOf(contactpersoon.getVoornaam().toString()));
        achternaam.setText(String.valueOf(contactpersoon.getAchternaam().toString()));
        relatie.setText(String.valueOf(contactpersoon.getRelatie().toString()));
        telefoon.setText(String.valueOf(contactpersoon.getTelefoon().toString()));
        email.setText(String.valueOf(contactpersoon.getEmail().toString()));
        straat.setText(String.valueOf(contactpersoon.getAdress().getStraat()));
        huisnr.setText(String.valueOf(contactpersoon.getAdress().getHuisnr()));
        gemeente.setText(String.valueOf(contactpersoon.getAdress().getGemeente()));
        postcode.setText(String.valueOf(contactpersoon.getAdress().getPostcode()));
        identiteitskaartnr.setText(String.valueOf(contactpersoon.getIdentiteitskaartnr().toString()));

        a.setStraat(contactpersoon.getAdress().getStraat());
        a.setHuisnr(contactpersoon.getAdress().getHuisnr());
        a.setGemeente(contactpersoon.getAdress().getGemeente());
        a.setPostcode(contactpersoon.getAdress().getPostcode());
    }

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
                || email.getText() == null || email.getText().trim().isEmpty() || straat.getText() == null || straat.getText().trim().isEmpty() || huisnr.getText() == null || huisnr.getText().trim().isEmpty() || gemeente.getText() == null || gemeente.getText().trim().isEmpty() || postcode.getText() == null || postcode.getText().trim().isEmpty()){
            if (voornaam.getText() == null || voornaam.getText().trim().isEmpty())
            {
                voornaam.getStyleClass().add("error");
            }
            if (achternaam.getText() == null || achternaam.getText().trim().isEmpty())
            {
                achternaam.getStyleClass().add("error");
            }
            if (identiteitskaartnr.getText() == null || identiteitskaartnr.getText().trim().isEmpty())
            {
                identiteitskaartnr.getStyleClass().add("error");
            }
            if (relatie.getText() == null || relatie.getText().trim().isEmpty())
            {
                relatie.getStyleClass().add("error");
            }
            if (telefoon.getText() == null || telefoon.getText().trim().isEmpty())
            {
                telefoon.getStyleClass().add("error");
            }
            if (email.getText() == null || email.getText().trim().isEmpty())
            {
                email.getStyleClass().add("error");
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
            Alert alertmis = new Alert(Alert.AlertType.ERROR);
            alertmis.setTitle("Aanpassen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Gelieve alle velden in te vullen!");
            alertmis.showAndWait();
        }else {
            if (Validation.checkFirstName(voornaam.getText().toString()) == true && Validation.checkLastName(achternaam.getText().toString()) == true && Validation.checkIdentitiecard(identiteitskaartnr.getText().toString()) == true && Validation.checkAlphabetical(relatie.getText().toString()) == true && Validation.checkPhone(telefoon.getText()) == true
                    && Validation.checkEmail(email.getText()) == true && Validation.checkAlphabetical(straat.getText().toString()) == true && Validation.checkHouseNumber(huisnr.getText().toString()) == true && Validation.checkAlphabetical(gemeente.getText().toString()) == true && Validation.checkPostalCode(postcode.getText().toString()) == true) {
                    Adress adres = new Adress(straat.getText().toString(), Integer.parseInt(huisnr.getText().toString()), gemeente.getText().toString(), Integer.parseInt(postcode.getText().toString()));
                    int adresId = AdressDao.getId(a);
                    adres.setId(adresId);
                contactpersoon = new Contactpersoon(bewoner, adres, voornaam.getText().toString(), achternaam.getText().toString(), Integer.parseInt(telefoon.getText().toString()), email.getText().toString(),
                        relatie.getText().toString(), identiteitskaartnr.getText().toString());
                Boolean edit;
                edit = BewonerDao.editContactpersoon(contactpersoon);
                if (edit == true) {
                    Alert alertsuc = new Alert(Alert.AlertType.CONFIRMATION);
                    alertsuc.setTitle("Aanpassen gelukt");
                    alertsuc.setHeaderText(null);
                    alertsuc.setContentText("Contactpseroon is aangepast!");
                    alertsuc.showAndWait();
                } else {
                    Alert alertmis = new Alert(Alert.AlertType.ERROR);
                    alertmis.setTitle("Aanpassen mislukt");
                    alertmis.setHeaderText(null);
                    alertmis.setContentText("Contactpersoon is niet aangepast! Probeer opnieuw!");
                    alertmis.showAndWait();
                }
            }else {
                if (Validation.checkFirstName(voornaam.getText().toString()) == false)
                {
                    voornaam.getStyleClass().add("error");
                }
                if (Validation.checkLastName(achternaam.getText().toString()) == false)
                {
                    achternaam.getStyleClass().add("error");
                }
                if (Validation.checkIdentitiecard(identiteitskaartnr.getText().toString()) == false)
                {
                    identiteitskaartnr.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(relatie.getText().toString()) == false)
                {
                    relatie.getStyleClass().add("error");
                }
                if (Validation.checkPhone(telefoon.getText()) == false)
                {
                    telefoon.getStyleClass().add("error");
                }
                if (Validation.checkEmail(email.getText()) == false)
                {
                    email.getStyleClass().add("error");
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
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Aanpassen mislukt");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve alle velden correct in te vullen!");
                alertmis.showAndWait();
            }
        }
    }
}
