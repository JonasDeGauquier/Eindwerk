package controller;

import DAO.BewonerDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.Bewoner;
import model.BewonersDossier;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowDossierController implements Initializable{

    private BewonersDossier dossier = new BewonersDossier();
    private Bewoner bewoner = new Bewoner();

    @FXML
    private Label Allergieën;
    @FXML
    private Label GroteOperaties;
    @FXML
    private Label ReanimatieWens;
    @FXML
    private Label Privacy;
    @FXML
    private Label Incontinentie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dossier = BewonerDao.getDossier(bewoner.getSelectedId());

        Allergieën.setText(String.valueOf(dossier.getAllergieën().toString()));
        GroteOperaties.setText(String.valueOf(dossier.getGroteOperaties().toString()));
        if (dossier.getIncontinentie() == true){
            Incontinentie.setText("Ja");
        }
        else {
            Incontinentie.setText("Nee");
        }
        if (dossier.getReanimatieWens() == true){
            ReanimatieWens.setText("Ja");
        }
        else {
            ReanimatieWens.setText("Nee");
        }
        if (dossier.getPrivacy() == true){
            Privacy.setText("Ja");
        }
        else {
            Privacy.setText("Nee");
        }
    }
}
