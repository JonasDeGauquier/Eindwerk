package controller;

import DAO.BewonerDao;
import DAO.PersoneelDao;
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
import model.User;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PersoneelController implements Initializable{
    @FXML
    TableView<User> PersoneelTable = new TableView<User>();
    @FXML
    TableColumn Voornaam, achternaam;
    @FXML
    private TextField search = new TextField();
    @FXML
    private SplitPane splitpane;

    ObservableList personeel = FXCollections.observableArrayList();
    ObservableList searchList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personeel = PersoneelDao.getAllPersoneelForColum();
        Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
        achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
        PersoneelTable.getItems().setAll(personeel);
    }

    @FXML
    private void handleKeyPressed(KeyEvent ke){
        if(search.textProperty().get().isEmpty()) {
            PersoneelTable.getItems().setAll(personeel);
        }else {
            searchList =  PersoneelDao.getAllPersoneelFromSearch(search.getText().toString());
            Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
            achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
            PersoneelTable.getItems().setAll(searchList);
        }
    }

    @FXML
    void AddPersoneel(ActionEvent event) {
        try {
            URL paneUrl = getClass().getResource("../gui/PersoneelToevoegen.fxml");
            Pane pane = FXMLLoader.load(paneUrl);

            splitpane.getItems().remove(1);
            splitpane.getItems().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ShowPersoneelGegevens(ActionEvent event) {
        User selectedItem = PersoneelTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null || selectedItem.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen persoon gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een persoon te selecteren!");
            notSelected.show();
        } else {
            User user = new User();
            user.setSelectedId(selectedItem.getUserId());
            try {
                URL paneUrl = getClass().getResource("../gui/PersoneelgegevensBekijken.fxml");
                Pane pane = FXMLLoader.load(paneUrl);

                splitpane.getItems().remove(1);
                splitpane.getItems().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void DeletePersoneel(ActionEvent event) {
        User selectedItem = PersoneelTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null || selectedItem.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen persoon gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een persoon te selecteren!");
            notSelected.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Persoon verwijderen");
            alert.setHeaderText(null);
            alert.setContentText("Bent u zeker dat u deze persoon wilt verwijderen?");

            ButtonType buttonTypeYes = new ButtonType("Ja");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                Boolean del = PersoneelDao.Delete(selectedItem.getUserId());
                if (del == true) {
                    personeel = PersoneelDao.getAllPersoneelForColum();
                    Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
                    achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
                    PersoneelTable.getItems().setAll(personeel);
                    PersoneelTable.refresh();
                    Alert alertsuc = new Alert(Alert.AlertType.INFORMATION);
                    alertsuc.setTitle("Personeel");
                    alertsuc.setHeaderText(null);
                    alertsuc.setContentText("Deze persoon is succesvol verwijderd!");

                    alertsuc.showAndWait();
                } else {
                    Alert alertAgain = new Alert(Alert.AlertType.INFORMATION);
                    alertAgain.setTitle("Personeel");
                    alertAgain.setHeaderText(null);
                    alertAgain.setContentText("Deze persoon is niet verwijderd! Probeer opnieuw");
                    alertAgain.showAndWait();
                }
            }
        }
    }
}
