package controller;

import DAO.BewonerDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Bewoner;
import model.BewonersDossier;
import model.Contactpersoon;

import java.net.URL;
import java.util.ResourceBundle;

public class showContactpersoonController implements Initializable {
    private Contactpersoon contactpersoon = new Contactpersoon();
    private Bewoner bewoner = new Bewoner();

    @FXML
    private Label voornaam, achternaam, identiteitiskaartnr, relatie, telefoon ,email , straat, huisnr, gemeente, postcode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactpersoon = BewonerDao.getContactpersoon(Bewoner.getSelectedId());

        voornaam.setText(String.valueOf(contactpersoon.getVoornaam()));
        achternaam.setText(String.valueOf(contactpersoon.getAchternaam()));
        identiteitiskaartnr.setText(String.valueOf(contactpersoon.getIdentiteitskaartnr()));
        relatie.setText(String.valueOf(contactpersoon.getRelatie()));
        telefoon.setText(String.valueOf(contactpersoon.getTelefoon().toString()));
        email.setText(String.valueOf(contactpersoon.getEmail()));
        straat.setText(String.valueOf(contactpersoon.getAdress().getStraat()));
        huisnr.setText(String.valueOf(contactpersoon.getAdress().getHuisnr()));
        gemeente.setText(String.valueOf(contactpersoon.getAdress().getGemeente()));
        postcode.setText(String.valueOf(contactpersoon.getAdress().getPostcode()));

    }
}
