package controller;

import DAO.PersoneelDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.User;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ShowPersoneelGegevensController implements Initializable {
    private User personeel = new User();

    @FXML
    private Label voornaam, achternaam ,geboortedatum, email ,rol, straat, huisnr, gemeente, postcode;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personeel = PersoneelDao.getPersoneel(User.getSelectedId());

        voornaam.setText(String.valueOf(personeel.getVoornaam().toString()));
        achternaam.setText(String.valueOf(personeel.getAchternaam().toString()));
        geboortedatum.setText(String.valueOf(personeel.getGeboortedatum().toString()));
        email.setText(String.valueOf(personeel.getEmail().toString()));
        rol.setText(String.valueOf(personeel.getRol().getRol().toString()));
        straat.setText(String.valueOf(personeel.getAdress().getStraat().toString()));
        huisnr.setText(String.valueOf(personeel.getAdress().getHuisnr()));
        gemeente.setText(String.valueOf(personeel.getAdress().getGemeente().toString()));
        postcode.setText(String.valueOf(personeel.getAdress().getPostcode().toString()));
    }

    @FXML
    void DeletePersoneel(MouseEvent mouseEvent) {
        Integer selectedPersoon = personeel.getUserId();
        if (selectedPersoon == null || selectedPersoon.equals("")) {
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
                Boolean del = PersoneelDao.Delete(selectedPersoon);
                if (del == true) {
                    // Bron: https://github.com/PlusHaze/TrayNotification
                    String title = "Personeel";
                    String message = "Deze persoon is succesvol verwijderd!";
                    TrayNotification tray = new TrayNotification(title, message, NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(4));
                    try {
                        URL paneUrl = getClass().getResource("../gui/Personeel.fxml");
                        VBox pane = FXMLLoader.load(paneUrl);

                        BorderPane border = HomeController.getRoot();
                        border.setCenter(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
