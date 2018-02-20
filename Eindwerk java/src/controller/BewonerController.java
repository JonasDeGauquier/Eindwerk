package controller;

import DAO.BewonerDao;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXToolbar;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Callback;
import javafx.util.Duration;
import model.Bewoner;
import model.BewonersDossier;
import model.User;

import javax.swing.text.View;
import javax.tools.Tool;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class BewonerController implements Initializable {

    private ShowDossierController showDossier;
    @FXML
    private TableView<Bewoner> BewonersTable = new TableView<Bewoner>();
    @FXML
    private TableColumn Voornaam, achternaam;
    @FXML
    private TextField search = new TextField();
    @FXML
    private AnchorPane content;
    @FXML
    private SplitPane splitpane;
    @FXML
    private JFXToolbar toolbar;

    private ObservableList bewoners = FXCollections.observableArrayList();
    private ObservableList searchList = FXCollections.observableArrayList();
    private Bewoner bewoner;

    private Button save = new Button();
    private Button add = new Button();
    private Button edit = new Button();
    private Button delete = new Button();

    @FXML
    private void handleKeyPressed(KeyEvent ke){
        if(search.textProperty().get().isEmpty()) {
            BewonersTable.getItems().setAll(bewoners);
        }else {
            searchList =  BewonerDao.getAllBewonersFromSearch(search.getText().toString());
            Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
            achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
            BewonersTable.getItems().setAll(searchList);
        }
    }

    @FXML
    void switchToShowBewoner(ActionEvent event){
        editIcon("../gui/BewonerBewerken.fxml");
        edit.setDisable(false);
        editIconTooltip("Bewoner bewerken");

        Bewoner selectedBewoner = BewonersTable.getSelectionModel().getSelectedItem();
        if (selectedBewoner == null || selectedBewoner.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen bewoner gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een bewoner te selecteren!");
            notSelected.show();
        } else {
            if (BewonerDao.getBewoner(selectedBewoner.getId()) == null || BewonerDao.getBewoner(selectedBewoner.getId()).equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Geen gegevens gevonden");
                alert.setHeaderText(null);
                alert.setContentText("Er is een fout opgetreden. Probeer opnieuw!");
                alert.show();
            } else {
                Bewoner bewoner = new Bewoner();
                bewoner.setSelectedId(selectedBewoner.getId());

                setSplitpane("../gui/BewonergegevensBekijken.fxml");
            }
        }
    }

    @FXML
    void switchToEditBewoner(ActionEvent event) {
        Bewoner selectedBewoner = BewonersTable.getSelectionModel().getSelectedItem();
        if (selectedBewoner == null || selectedBewoner.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen bewoner gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een bewoner te selecteren!");
            notSelected.show();
        } else {
            if (BewonerDao.getBewoner(selectedBewoner.getId()) == null || BewonerDao.getBewoner(selectedBewoner.getId()).equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Geen gegevens gevonden");
                alert.setHeaderText(null);
                alert.setContentText("Er is een fout opgetreden. Probeer opnieuw!");
                alert.show();
            } else {
                Bewoner bewoner = new Bewoner();
                bewoner.setSelectedId(selectedBewoner.getId());

                setSplitpane("../gui/BewonerBewerken.fxml");
            }
        }
    }

    @FXML
    void switchToShowDossier(ActionEvent event) {
        editIcon("../gui/BewonerDossierBewerken.fxml");
        edit.setDisable(false);

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
                alert.setContentText("De geselecteerde bewoneer heeft nog geen dossier. Wilt u een dossier toevoegen?");
                ButtonType buttonTypeYes = new ButtonType("Ja");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes) {
                    Bewoner bewoner = new Bewoner();
                    bewoner.setSelectedId(selectedBewoner.getId());
                    try {
                        URL paneUrl = getClass().getResource("../gui/BewonerDossierToevoegen.fxml");
                        Pane pane = FXMLLoader.load(paneUrl);

                        splitpane.getItems().remove(1);
                        splitpane.getItems().add(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Bewoner bewoner = new Bewoner();
                bewoner.setSelectedId(selectedBewoner.getId());

                setSplitpane("../gui/BewonerDossierBekijken.fxml");
            }
        }
    }

    @FXML
    void switchToEditDossier(ActionEvent event) {
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
                alert.setContentText("De geselecteerde bewoneer heeft nog geen dossier. Wilt u een dossier toevoegen?");
                ButtonType buttonTypeYes = new ButtonType("Ja");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes) {
                    Bewoner bewoner = new Bewoner();
                    bewoner.setSelectedId(selectedBewoner.getId());
                    try {
                        URL paneUrl = getClass().getResource("../gui/BewonerDossierToevoegen.fxml");
                        Pane pane = FXMLLoader.load(paneUrl);

                        splitpane.getItems().remove(1);
                        splitpane.getItems().add(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Bewoner bewoner = new Bewoner();
                bewoner.setSelectedId(selectedBewoner.getId());

                setSplitpane("../gui/BewonerDossierBewerken.fxml");
            }
        }
    }

    @FXML
    void switchToShowVerpleegdossier(ActionEvent event) {
        editIcon("../gui/VerpleegDossierBekijken.fxml");
        edit.setDisable(false);

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
                alert.setContentText("De geselecteerde bewoneer heeft nog geen verpleegdossier. Wilt u een dossier toevoegen?");
                ButtonType buttonTypeYes = new ButtonType("Ja");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes) {
                    Bewoner bewoner = new Bewoner();
                    bewoner.setSelectedId(selectedBewoner.getId());
                    try {
                        URL paneUrl = getClass().getResource("../gui/verpleegdossierToevoegen.fxml");
                        Pane pane = FXMLLoader.load(paneUrl);

                        splitpane.getItems().remove(1);
                        splitpane.getItems().add(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Bewoner bewoner = new Bewoner();
                bewoner.setSelectedId(selectedBewoner.getId());
                try {
                    URL paneUrl = getClass().getResource("../gui/VerpleegDossierBekijken.fxml");
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
    void switchToEditVerpleegDossier(ActionEvent event) {
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
                alert.setTitle("Geen dossier gevonden");
                alert.setHeaderText(null);
                alert.setContentText("De geselecteerde bewoneer heeft nog geen dossier. Wilt u een dossier toevoegen?");
                ButtonType buttonTypeYes = new ButtonType("Ja");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes) {
                    try {
                        URL paneUrl = getClass().getResource("../gui/verpleegdossierToevoegen.fxml");
                        Pane pane = FXMLLoader.load(paneUrl);

                        splitpane.getItems().remove(1);
                        splitpane.getItems().add(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Bewoner bewoner = new Bewoner();
                bewoner.setSelectedId(selectedBewoner.getId());

                try {
                    URL paneUrl = getClass().getResource("../gui/VerpleegdossierBewerken.fxml");
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
        editIcon("../gui/ContactpersoonBewerken.fxml");
        edit.setDisable(false);

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
                alert.setContentText("De geselecteerde bewoneer heeft nog geen contactpersoon. Wilt u een contactpersoon toevoegen?");
                ButtonType buttonTypeYes = new ButtonType("Ja");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes) {
                    Bewoner bewoner = new Bewoner();
                    bewoner.setSelectedId(selectedBewoner.getId());
                    try {
                        URL paneUrl = getClass().getResource("../gui/ContactpersoonToevoegen.fxml");
                        Pane pane = FXMLLoader.load(paneUrl);

                        splitpane.getItems().remove(1);
                        splitpane.getItems().add(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Bewoner bewoner = new Bewoner();
                bewoner.setSelectedId(selectedBewoner.getId());
                try {
                    URL paneUrl = getClass().getResource("../gui/ContactpersoonBekijken.fxml");
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
    void switchToEditContactpersoon(ActionEvent event) {
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
                alert.setContentText("De geselecteerde bewoneer heeft nog geen dossier. Wilt u een dossier toevoegen?");
                ButtonType buttonTypeYes = new ButtonType("Ja");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeYes) {
                    Bewoner bewoner = new Bewoner();
                    bewoner.setSelectedId(selectedBewoner.getId());
                    try {
                        URL paneUrl = getClass().getResource("../gui/ContactpersoonToevoegen.fxml");
                        Pane pane = FXMLLoader.load(paneUrl);

                        splitpane.getItems().remove(1);
                        splitpane.getItems().add(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Bewoner bewoner = new Bewoner();
                bewoner.setSelectedId(selectedBewoner.getId());
                try {
                    URL paneUrl = getClass().getResource("../gui/ContactpersoonBewerken.fxml");
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
    void ShowZorgplan(ActionEvent event) {
        Bewoner selectedBewoner = BewonersTable.getSelectionModel().getSelectedItem();
        if (selectedBewoner == null || selectedBewoner.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen bewoner gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een bewoner te selecteren!");
            notSelected.show();
        } else  {
            Bewoner bewoner = new Bewoner();
            bewoner.setSelectedId(selectedBewoner.getId());
            try {
                URL paneUrl = getClass().getResource("../gui/ZorgplanBekijkenViaBewoner.fxml");
                Pane pane = FXMLLoader.load(paneUrl);

                splitpane.getItems().remove(1);
                splitpane.getItems().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bewoners =  BewonerDao.getAllBewonersForColum();
        Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
        achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
        BewonersTable.getItems().setAll(bewoners);

        save.setGraphic(new ImageView("/images/save-icon.png" ));
        add.setGraphic(new ImageView("/images/add-icon.png" ));
        edit.setGraphic(new ImageView("/images/edit-icon.png" ));
        delete.setGraphic(new ImageView("/images/delete-icon.png" ));

        toolbar.getLeftItems().addAll(add, edit, delete);

        edit.setDisable(true);
    }

    private void setSplitpane(String url){
        try {
            URL paneUrl = getClass().getResource(url.toString());
            content = FXMLLoader.load(paneUrl);

            splitpane.getItems().remove(1);
            splitpane.getItems().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editIcon(String url){
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSplitpane(url.toString());
            }
        });
    }

    private void editIconTooltip(String text){
        Tooltip editTooltip = new Tooltip();
        editTooltip.setText(text.toString());
        Duration duration = new Duration(1);
        editTooltip.setShowDelay(duration);
        edit.setTooltip(editTooltip);
    }
}
