package controller.Verpleegster;

import DAO.BewonerDao;
import controller.HomeController;
import controller.ShowDossierController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.Bewoner;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BewonerVerpleegsterController implements Initializable {
    private ShowDossierController showDossier;
    @FXML
    TableView<Bewoner> BewonersTable = new TableView<Bewoner>();
    @FXML
    TableColumn Voornaam, achternaam;
    @FXML
    private Button ShowDossier, ChangeDossier, ShowVerpleegDossier, ChangeVerpleegDossier, ShowContactpersoon;
    @FXML
    private TextField search = new TextField();
    @FXML
    private SplitPane splitpane;

    ObservableList bewoners = FXCollections.observableArrayList();
    ObservableList searchList = FXCollections.observableArrayList();
    Bewoner bewoner;

    @FXML
    private void handleKeyPressed(KeyEvent ke) {
        if (search.textProperty().get().isEmpty()) {
            BewonersTable.getItems().setAll(bewoners);
        } else {
            searchList = BewonerDao.getAllBewonersFromSearch(search.getText().toString());
            Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
            achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
            BewonersTable.getItems().setAll(searchList);
        }
    }

    @FXML
    void switchToShowDossier(ActionEvent event) {
        Bewoner selectedBewoner = BewonersTable.getSelectionModel().getSelectedItem();
        if (selectedBewoner == null || selectedBewoner.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen bewoner gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een bewoner te selecteren!");
            notSelected.show();
        } else {
            if (BewonerDao.getDossier(selectedBewoner.getId()) == null || BewonerDao.getDossier(selectedBewoner.getId()).equals("")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Geen dossier gevonden");
                alert.setHeaderText(null);
                alert.setContentText("De geselecteerde bewoneer heeft nog geen dossier.");
            } else {

                Bewoner bewoner = new Bewoner();
                Bewoner.setSelectedId(selectedBewoner.getId());
                ShowDossierController showDossier = new ShowDossierController();

                try {
                    URL paneUrl = getClass().getResource("../../gui/BewonerDossierBekijken.fxml");
                    Pane pane = FXMLLoader.load(paneUrl);

                    splitpane.getItems().remove(1);
                    splitpane.getItems().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void switchToShowVerpleegdossier(ActionEvent event) {
        Bewoner selectedBewoner = BewonersTable.getSelectionModel().getSelectedItem();
        if (selectedBewoner == null || selectedBewoner.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen bewoner gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een bewoner te selecteren!");
            notSelected.show();
        } else {
            if (BewonerDao.getVerpleegDossier(selectedBewoner.getId()) == null || BewonerDao.getVerpleegDossier(selectedBewoner.getId()).equals("")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Geen verpleegdossier gevonden");
                alert.setHeaderText(null);
                alert.setContentText("De geselecteerde bewoneer heeft nog geen verpleegdossier.");
            } else {
                Bewoner bewoner = new Bewoner();
                Bewoner.setSelectedId(selectedBewoner.getId());

                try {
                    URL paneUrl = getClass().getResource("../../gui/VerpleegDossierBekijken.fxml");
                    Pane pane = FXMLLoader.load(paneUrl);

                    splitpane.getItems().remove(1);
                    splitpane.getItems().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void switchToShowContactpersoon(ActionEvent event) {
        Bewoner selectedBewoner = BewonersTable.getSelectionModel().getSelectedItem();
        if (selectedBewoner == null || selectedBewoner.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen bewoner gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een bewoner te selecteren!");
            notSelected.show();
        } else {

            if (BewonerDao.getContactpersoon(selectedBewoner.getId()) == null || BewonerDao.getContactpersoon(selectedBewoner.getId()).equals("")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Geen contactpersoon gevonden");
                alert.setHeaderText(null);
                alert.setContentText("De geselecteerde bewoneer heeft nog geen contactpersoon.");
            } else {
                Bewoner bewoner = new Bewoner();
                Bewoner.setSelectedId(selectedBewoner.getId());

                try {
                    URL paneUrl = getClass().getResource("../../gui/ContactpersoonBekijken.fxml");
                    Pane pane = FXMLLoader.load(paneUrl);

                    splitpane.getItems().remove(1);
                    splitpane.getItems().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bewoners =  BewonerDao.getAllBewonersForColum();
        Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
        achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
        BewonersTable.getItems().setAll(bewoners);
    }
}
