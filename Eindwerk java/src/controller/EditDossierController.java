package controller;

import DAO.BewonerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Bewoner;
import model.BewonersDossier;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDossierController implements Initializable {
    private BewonersDossier dossier = new BewonersDossier();
    private Bewoner bewoner = new Bewoner();

    @FXML
    private TextField Allergieën;
    @FXML
    private TextField GroteOperaties;
    @FXML
    private RadioButton ReanimatieJa, ReanimatieNee, PrivacyJa, PrivacyNee, IncontinentieNee, IncontinentieJa;

    ToggleGroup reanimatie =new ToggleGroup();
    ToggleGroup privacy =new ToggleGroup();
    ToggleGroup incontinentie =new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dossier = BewonerDao.getDossier(bewoner.getSelectedId());

        Allergieën.setText(String.valueOf(dossier.getAllergieën().toString()));
        GroteOperaties.setText(String.valueOf(dossier.getGroteOperaties().toString()));

        ReanimatieJa.setToggleGroup(reanimatie);
        ReanimatieJa.setUserData("True");
        ReanimatieNee.setToggleGroup(reanimatie);
        ReanimatieNee.setUserData("False");

        PrivacyJa.setToggleGroup(privacy);
        PrivacyJa.setUserData("True");
        PrivacyNee.setToggleGroup(privacy);
        PrivacyNee.setUserData("False");

        IncontinentieNee.setToggleGroup(incontinentie);
        IncontinentieJa.setUserData("True");
        IncontinentieJa.setToggleGroup(incontinentie);
        IncontinentieNee.setUserData("False");

        if (dossier.getIncontinentie() == true){
            IncontinentieJa.setSelected(true);
        }
        else {
            IncontinentieNee.setSelected(true);
        }
        if (dossier.getReanimatieWens() == true){
            ReanimatieJa.setSelected(true);
        }
        else {
            ReanimatieNee.setSelected(true);
        }
        if (dossier.getPrivacy() == true){
            PrivacyJa.setSelected(true);
        }
        else {
            PrivacyNee.setSelected(true);
        }
    }

    @FXML
    void addDossier(ActionEvent event) {
        GroteOperaties.getStyleClass().remove("error");
        Allergieën.getStyleClass().remove("error");
        if (GroteOperaties.getText() == null || GroteOperaties.getText().trim().isEmpty() || Allergieën.getText() == null || Allergieën.getText().trim().isEmpty()){
            if (GroteOperaties.getText() == null || GroteOperaties.getText().trim().isEmpty())
            {
                GroteOperaties.getStyleClass().add("error");
            }
            if (Allergieën.getText() == null || Allergieën.getText().trim().isEmpty())
            {
                Allergieën.getStyleClass().add("error");
            }
            Alert alertmis = new Alert(Alert.AlertType.ERROR);
            alertmis.setTitle("Aanpassen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Gelieve alle velden in te vullen!");
            alertmis.showAndWait();
        }else {
            if (Validation.checkAlphabetical(GroteOperaties.getText().toString()) == true && Validation.checkAlphabetical(Allergieën.getText().toString()) == true){
                BewonersDossier dossier = new BewonersDossier(bewoner.getSelectedId(),Boolean.valueOf(incontinentie.getSelectedToggle().getUserData().toString()), Boolean.valueOf(privacy.getSelectedToggle().getUserData().toString()), Boolean.valueOf(reanimatie.getSelectedToggle().getUserData().toString()) ,GroteOperaties.getText().toString(), Allergieën.getText().toString());
                Boolean edit;
                edit = BewonerDao.editDossier(dossier);
                if (edit == true)
                {
                    Alert alertsuc = new Alert(Alert.AlertType.CONFIRMATION);
                    alertsuc.setTitle("Aanpassen gelukt");
                    alertsuc.setHeaderText(null);
                    alertsuc.setContentText("Dossier is aangepast!");
                    alertsuc.showAndWait();
                }
                else {
                    Alert alertmis = new Alert(Alert.AlertType.ERROR);
                    alertmis.setTitle("Aanpassen mislukt");
                    alertmis.setHeaderText(null);
                    alertmis.setContentText("Dossier is niet aangepast! Probeer opnieuw!");
                    alertmis.showAndWait();
                }
            }
            else {
                if (Validation.checkAlphabetical(GroteOperaties.getText().toString()) == false)
                {
                    GroteOperaties.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(Allergieën.getText().toString()) == false)
                {
                    Allergieën.getStyleClass().add("error");
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
