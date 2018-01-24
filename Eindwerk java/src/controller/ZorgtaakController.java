package controller;

import DAO.MedicatieDao;
import DAO.ZorgplanDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Bewoner;
import model.Medicatie;
import model.Zorgtaak;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ZorgtaakController implements Initializable {
    @FXML
    TableView<Zorgtaak> ZorgtaakTable = new TableView<Zorgtaak>();
    @FXML
    TableColumn ZorgtaakNaam;
    @FXML
    private TextField search = new TextField();
    @FXML
    private Pane content;
    @FXML
    private SplitPane splitpane;

    ObservableList zorgtaken = FXCollections.observableArrayList();
    ObservableList searchList = FXCollections.observableArrayList();

    @FXML
    private void handleKeyPressed(KeyEvent ke){
        if(search.textProperty().get().isEmpty()) {
            ZorgtaakTable.getItems().setAll(zorgtaken);
        }else {
            searchList =  ZorgplanDao.getAllZorgtaakFromSearch(search.getText().toString());
            ZorgtaakNaam.setCellValueFactory(new PropertyValueFactory<Zorgtaak, String>("zorgtaak"));
            ZorgtaakTable.getItems().setAll(searchList);
        }
    }

    @FXML
    private void AddZorgtaak(MouseEvent mouseEven){
        try {
            URL paneUrl = getClass().getResource("../gui/ZorgtaakToevoegen.fxml");
            content = FXMLLoader.load(paneUrl);

            splitpane.getItems().remove(1);
            splitpane.getItems().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DeleteZorgtaak(MouseEvent mouseEvent) {
        Zorgtaak selectedZorgtaak = ZorgtaakTable.getSelectionModel().getSelectedItem();
        if (selectedZorgtaak == null || selectedZorgtaak.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen zorgtaak gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een zorgtaak te selecteren!");
            notSelected.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Zorgtaak verwijderen");
            alert.setHeaderText(null);
            alert.setContentText("Bent u zeker dat u deze zorgtaak wilt verwijderen?");

            ButtonType buttonTypeYes = new ButtonType("Ja");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                Boolean del = ZorgplanDao.DeleteZorgtaak(selectedZorgtaak.getId());
                if (del == true) {
                    Alert alertsuc = new Alert(Alert.AlertType.INFORMATION);
                    alertsuc.setTitle("Zorgtaak");
                    alertsuc.setHeaderText(null);
                    alertsuc.setContentText("De zorgtaak is succesvol verwijderd!");
                    alertsuc.show();
                    try {
                        URL paneUrl = getClass().getResource("../gui/Zorgtaak.fxml");
                        VBox pane = FXMLLoader.load(paneUrl);

                        BorderPane border = HomeController.getRoot();
                        border.setCenter(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alertAgain = new Alert(Alert.AlertType.ERROR);
                    alertAgain.setTitle("Zorgtaak");
                    alertAgain.setHeaderText(null);
                    alertAgain.setContentText("De zorgtaak is niet verwijderd! Probeer opnieuw");
                    alertAgain.showAndWait();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zorgtaken = ZorgplanDao.getAllZorgtaakForTable();
        ZorgtaakNaam.setCellValueFactory(new PropertyValueFactory<Zorgtaak, String>("zorgtaak"));
        ZorgtaakTable.getItems().setAll(zorgtaken);
    }
}
