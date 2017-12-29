package controller;

import DAO.BewonerDao;
import DAO.PersoneelDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Bewoner;
import model.BewonersDossier;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowPersoneelGegevensController implements Initializable {
    private User personeel = new User();

    @FXML
    private Label voornaam, achternaam ,geboortedatum, email ,rol, straat, huisnr, gemeente, postcode;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personeel = PersoneelDao.getPersoneel(personeel.getSelectedId());

        voornaam.setText(String.valueOf(personeel.getVoornaam().toString()));
        achternaam.setText(String.valueOf(personeel.getAchternaam().toString()));
        geboortedatum.setText(String.valueOf(personeel.getGeboortedatum().toString()));
        email.setText(String.valueOf(personeel.getEmail().toString()));
        rol.setText(String.valueOf(personeel.getRol().getRol().toString()));
        straat.setText(String.valueOf(personeel.getAdress().getStraat().toString()));
        huisnr.setText(String.valueOf(personeel.getAdress().getHuisnr()));
        gemeente.setText(String.valueOf(personeel.getAdress().getGemeente().toString()));
        postcode.setText(String.valueOf(personeel.getAdress().getPostcode().toString()));
    }
}
