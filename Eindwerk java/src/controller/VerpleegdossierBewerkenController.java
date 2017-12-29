package controller;

import DAO.BewonerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Bewoner;
import model.BewonersDossier;
import model.Verpleegdossier;

import java.net.URL;
import java.util.ResourceBundle;

public class VerpleegdossierBewerkenController implements Initializable{
    private Verpleegdossier dossier = new Verpleegdossier();
    private Bewoner bewoner = new Bewoner();

    ToggleGroup suikerziekte =new ToggleGroup();

    @FXML
    private TextField Wondzorg, Bloedafname, VroegerBeroep, Specifiekewensen;

    @FXML
    private RadioButton SuikerziekteJa, SuikerziekteNee;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dossier = BewonerDao.getVerpleegDossier(bewoner.getSelectedId());

        Wondzorg.setText(String.valueOf(dossier.getWondzorg().toString()));
        Bloedafname.setText(String.valueOf(dossier.getBloedafname().toString()));
        VroegerBeroep.setText(String.valueOf(dossier.getBeroepVroeger().toString()));
        Specifiekewensen.setText(String.valueOf(dossier.getSpecifiekeWensen().toString()));

        SuikerziekteJa.setToggleGroup(suikerziekte);
        SuikerziekteJa.setUserData("True");
        SuikerziekteNee.setToggleGroup(suikerziekte);
        SuikerziekteNee.setUserData("False");

        if (dossier.getSuikerziekte() == true){
            SuikerziekteJa.setSelected(true);
        }
        else {
            SuikerziekteNee.setSelected(true);
        }

    }

    @FXML
    void addVerpleegDossier(ActionEvent event) {
        Wondzorg.getStyleClass().remove("error");
        Bloedafname.getStyleClass().remove("error");
        VroegerBeroep.getStyleClass().remove("error");
        Specifiekewensen.getStyleClass().remove("error");

        if (Wondzorg.getText() == null || Wondzorg.getText().trim().isEmpty() || Bloedafname.getText() == null || Bloedafname.getText().trim().isEmpty() || VroegerBeroep.getText() == null || VroegerBeroep.getText().trim().isEmpty() || Specifiekewensen.getText() == null || Specifiekewensen.getText().trim().isEmpty()){
            if (Wondzorg.getText() == null || Wondzorg.getText().trim().isEmpty())
            {
                Wondzorg.getStyleClass().add("error");
            }
            if (Bloedafname.getText() == null || Bloedafname.getText().trim().isEmpty())
            {
                Bloedafname.getStyleClass().add("error");
            }
            if (VroegerBeroep.getText() == null || VroegerBeroep.getText().trim().isEmpty())
            {
                VroegerBeroep.getStyleClass().add("error");
            }
            if (Specifiekewensen.getText() == null || Specifiekewensen.getText().trim().isEmpty())
            {
                Specifiekewensen.getStyleClass().add("error");
            }
            Alert alertmis = new Alert(Alert.AlertType.ERROR);
            alertmis.setTitle("Aanpassen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Gelieve alle velden in te vullen!");
            alertmis.showAndWait();
        }else {
            if (Validation.checkAlphabetical(Wondzorg.getText().toString()) == true && Validation.checkAlphabetical(Bloedafname.getText().toString()) == true  && Validation.checkAlphabetical(VroegerBeroep.getText().toString()) == true  && Validation.checkText(Specifiekewensen.getText().toString()) == true){
                Verpleegdossier dossier = new Verpleegdossier(bewoner.getSelectedId(), Wondzorg.getText().toString(), Bloedafname.getText().toString() ,Boolean.valueOf(suikerziekte.getSelectedToggle().getUserData().toString()),VroegerBeroep.getText().toString(), Specifiekewensen.getText().toString());
                Boolean edit;
                edit = BewonerDao.editVerpleegDossier(dossier);
                if (edit == true)
                {
                    Alert alertsuc = new Alert(Alert.AlertType.CONFIRMATION);
                    alertsuc.setTitle("Aanpassen gelukt");
                    alertsuc.setHeaderText(null);
                    alertsuc.setContentText("Verpleegdossier is aangepast!");
                    alertsuc.showAndWait();
                }
                else {
                    Alert alertmis = new Alert(Alert.AlertType.ERROR);
                    alertmis.setTitle("Aanpassen mislukt");
                    alertmis.setHeaderText(null);
                    alertmis.setContentText("Verpleegdossier is niet aangepast! Probeer opnieuw!");
                    alertmis.showAndWait();
                }
            }else {
                if (Validation.checkAlphabetical(Wondzorg.getText().toString()) == false)
                {
                    Wondzorg.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(Bloedafname.getText().toString()) == false)
                {
                    Bloedafname.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(VroegerBeroep.getText().toString()) == false)
                {
                    VroegerBeroep.getStyleClass().add("error");
                }
                if (Validation.checkText(Specifiekewensen.getText().toString()) == false)
                {
                    Specifiekewensen.getStyleClass().add("error");
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
