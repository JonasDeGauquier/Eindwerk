package controller;

import DAO.BewonerDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Bewoner;
import model.BewonersDossier;
import model.Verpleegdossier;

import java.net.URL;
import java.util.ResourceBundle;

public class VerpleegDossierBekijkenController implements Initializable {

    private Verpleegdossier dossier = new Verpleegdossier();
    private Bewoner bewoner = new Bewoner();

    @FXML
    private Label Wondzorg;
    @FXML
    private Label Bloedafname, Suikerziekte,VroegerBeroep,Specifiekewensen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dossier = BewonerDao.getVerpleegDossier(Bewoner.getSelectedId());

        Wondzorg.setText(String.valueOf(dossier.getWondzorg().toString()));
        Bloedafname.setText(String.valueOf(dossier.getBloedafname().toString()));
        if (dossier.getSuikerziekte() == true){
            Suikerziekte.setText("Ja");
        }
        else {
            Suikerziekte.setText("Nee");
        }
        VroegerBeroep.setText(String.valueOf(dossier.getBeroepVroeger().toString()));
        Specifiekewensen.setText(String.valueOf(dossier.getSpecifiekeWensen().toString()));
    }
}
