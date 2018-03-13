package controller;

import DAO.BewonerDao;
import com.jfoenix.controls.JFXToolbar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import model.Bewoner;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BewonerController implements Initializable {

    @FXML
    private TableView<Bewoner> BewonersTable = new TableView<>();
    @FXML
    private TableColumn Voornaam, achternaam, plaats;
    @FXML
    private TextField search = new TextField();
    private AnchorPane content;
    @FXML
    private SplitPane splitpane;
    @FXML
    private JFXToolbar toolbar;

    private ObservableList bewoners = FXCollections.observableArrayList();
    private ObservableList searchList = FXCollections.observableArrayList();
    private  ObservableList plaatsen = FXCollections.observableArrayList();

    private Button save = new Button();
    private Button add = new Button();
    private Button edit = new Button();
    private Button delete = new Button();

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
    void switchToShowBewoner(ActionEvent event) {
        editIcon("../gui/BewonerBewerken.fxml");
        edit.setDisable(false);
        editIconTooltip("Bewoner gegevens bewerken");

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
                Bewoner.setSelectedId(selectedBewoner.getId());

                setSplitpane("../gui/BewonergegevensBekijken.fxml");
            }
        }
    }

    @FXML
    void switchToShowDossier(ActionEvent event) {
        editIcon("../gui/BewonerDossierBewerken.fxml");
        edit.setDisable(false);
        editIconTooltip("Bewonerdossier bewerken");

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
                    Bewoner.setSelectedId(selectedBewoner.getId());

                    setSplitpane("../gui/BewonerDossierToevoegen.fxml");
                }
            } else {
                Bewoner.setSelectedId(selectedBewoner.getId());

                setSplitpane("../gui/BewonerDossierBekijken.fxml");
            }
        }
    }

    @FXML
    void switchToShowVerpleegdossier(ActionEvent event) {
        editIcon("../gui/VerpleegdossierBewerken.fxml");
        edit.setDisable(false);
        editIconTooltip("Verpleegdossier bewerken");

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
                    Bewoner.setSelectedId(selectedBewoner.getId());

                    setSplitpane("../gui/verpleegdossierToevoegen.fxml");
                }
            } else {
                Bewoner.setSelectedId(selectedBewoner.getId());

                setSplitpane("../gui/VerpleegDossierBekijken.fxml");
            }
        }
    }

    @FXML
    void switchToShowContactpersoon(ActionEvent event) {
        editIcon("../gui/ContactpersoonBewerken.fxml");
        edit.setDisable(false);
        editIconTooltip("Contactpersoon bewerken");

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
                    Bewoner.setSelectedId(selectedBewoner.getId());

                    setSplitpane("../gui/ContactpersoonToevoegen.fxml");
                }
            } else {
                Bewoner.setSelectedId(selectedBewoner.getId());

                setSplitpane("../gui/ContactpersoonBekijken.fxml");
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
        } else {
            Bewoner.setSelectedId(selectedBewoner.getId());

            setSplitpane("../gui/ZorgplanBekijkenViaBewoner.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bewoners = BewonerDao.getAllBewonersForColum();
        Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
        achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));

        //Bron: https://stackoverflow.com/questions/21860019/javafx-create-combobox-tablecell
        plaatsen = FXCollections.observableArrayList("kamer", "Kiné");
        plaats.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("plaats"));
        plaats.setCellFactory(ComboBoxTableCell.forTableColumn(plaatsen));
        plaats.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Bewoner, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Bewoner, String> t) {
                    BewonerDao.UpdatePlaats(t.getRowValue().getId(),t.getNewValue().toString());
                }
            }
        );

        BewonersTable.getItems().setAll(bewoners);

        save.setGraphic(new ImageView("/images/save-icon.png"));
        add.setGraphic(new ImageView("/images/add-icon.png"));
        edit.setGraphic(new ImageView("/images/edit-icon.png"));
        delete.setGraphic(new ImageView("/images/delete-icon.png"));

        if (toolbar != null){
            toolbar.getLeftItems().addAll(add, edit, delete);
        }


        edit.setDisable(true);

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Bewoner selectedItem = BewonersTable.getSelectionModel().getSelectedItem();
                if (selectedItem == null || selectedItem.equals("")) {
                    Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
                    notSelected.setTitle("Geen bewoner gekozen");
                    notSelected.setHeaderText(null);
                    notSelected.setContentText("Gelieve een bewoner te selecteren!");
                    notSelected.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Bewoner verwijderen");
                    alert.setHeaderText(null);
                    alert.setContentText("Bent u zeker dat u deze bewoner wilt verwijderen?");

                    ButtonType buttonTypeYes = new ButtonType("Ja");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeYes) {
                        Boolean del = BewonerDao.Delete(selectedItem.getId());
                        if (del == true) {
                            // Bron: https://github.com/PlusHaze/TrayNotification
                            String title = "Bewoner";
                            String message = "De bewoner is succesvol verwjiderd!";
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
                        } else {
                            Alert alertAgain = new Alert(Alert.AlertType.ERROR);
                            alertAgain.setTitle("Bewoner");
                            alertAgain.setHeaderText(null);
                            alertAgain.setContentText("De bewoner is niet verwijderd! Probeer opnieuw");
                            alertAgain.showAndWait();
                        }
                    }
                }
            }
        });

        Tooltip addTooltip = new Tooltip();
        addTooltip.setText("Bewoner toevoegen");
        Duration duration = new Duration(1);
        addTooltip.setShowDelay(duration);
        add.setTooltip(addTooltip);

        Tooltip deleteTooltip = new Tooltip();
        deleteTooltip.setText("Bewoner verwijderen");
        deleteTooltip.setShowDelay(duration);
        delete.setTooltip(deleteTooltip);
    }

    private void setSplitpane(String url) {
        try {
            URL paneUrl = getClass().getResource(url);
            content = FXMLLoader.load(paneUrl);

            splitpane.getItems().remove(1);
            splitpane.getItems().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editIcon(String url) {
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSplitpane(url);
            }
        });
    }

    private void editIconTooltip(String text) {
        Tooltip editTooltip = new Tooltip();
        editTooltip.setText(text);
        Duration duration = new Duration(1);
        editTooltip.setShowDelay(duration);
        edit.setTooltip(editTooltip);
    }
}
