package controller;

import DAO.BewonerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Bewoner;
import model.BewonersDossier;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
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
                    // Bron: https://github.com/PlusHaze/TrayNotification
                    String title = "Aanpassen gelukt";
                    String message = "Dossier is aangepast!";
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
