package controller;

import DAO.MedicatieDao;
import DAO.PersoneelDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Bewoner;
import model.Medicatie;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MedicatieController implements Initializable {

    @FXML
    TableView<Medicatie> MedicatieTable = new TableView<Medicatie>();
    @FXML
    TableColumn MedicatieNaam;
    @FXML
    private TextField search = new TextField();
    @FXML
    private Pane content;
    @FXML
    private SplitPane splitpane;

    ObservableList medicaties = FXCollections.observableArrayList();
    ObservableList searchList = FXCollections.observableArrayList();
    Medicatie medicatie;

    @FXML
    private void handleKeyPressed(KeyEvent ke){
        if(search.textProperty().get().isEmpty()) {
            MedicatieTable.getItems().setAll(medicaties);
        }else {
            searchList =  MedicatieDao.getAllMedicatieFromSearch(search.getText().toString());
            MedicatieNaam.setCellValueFactory(new PropertyValueFactory<Medicatie, String>("medicatie"));
            MedicatieTable.getItems().setAll(searchList);
        }
    }

    @FXML
    private void AddMedicatie(MouseEvent mouseEven){
        try {
            URL paneUrl = getClass().getResource("../gui/MedicatieToevoegen.fxml");
            content = FXMLLoader.load(paneUrl);

            splitpane.getItems().remove(1);
            splitpane.getItems().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DeleteMedicatie(MouseEvent mouseEvent) {
       Medicatie selectedMedicatie = MedicatieTable.getSelectionModel().getSelectedItem();
        if (selectedMedicatie == null || selectedMedicatie.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen medicatie gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve medicatie te selecteren!");
            notSelected.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Medicatie verwijderen");
            alert.setHeaderText(null);
            alert.setContentText("Bent u zeker dat u deze medicatie wilt verwijderen?");

            ButtonType buttonTypeYes = new ButtonType("Ja");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                Boolean del = MedicatieDao.Delete(selectedMedicatie.getId());
                if (del == true) {
                    // Bron: https://github.com/PlusHaze/TrayNotification
                    String title = "Medicatie";
                    String message = "De medicatie is succesvol verwjiderd!";
                    TrayNotification tray = new TrayNotification(title, message, NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(4));
                    try {
                        URL paneUrl = getClass().getResource("../gui/Medicatie.fxml");
                        VBox pane = FXMLLoader.load(paneUrl);

                        BorderPane border = HomeController.getRoot();
                        border.setCenter(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alertAgain = new Alert(Alert.AlertType.ERROR);
                    alertAgain.setTitle("Medicatie");
                    alertAgain.setHeaderText(null);
                    alertAgain.setContentText("De medicatie is niet verwijderd! Probeer opnieuw");
                    alertAgain.showAndWait();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        medicaties = MedicatieDao.getAllMedicatieForTable();
        MedicatieNaam.setCellValueFactory(new PropertyValueFactory<Medicatie, String>("medicatie"));
        MedicatieTable.getItems().setAll(medicaties);
    }
}
