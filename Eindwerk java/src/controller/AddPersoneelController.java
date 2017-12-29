package controller;

import DAO.PersoneelDao;
import DAO.RolDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Adress;
import model.Rol;
import model.User;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddPersoneelController implements Initializable {
    @FXML
    private ComboBox<Rol> cmbRol = new ComboBox();
    ObservableList<Rol> rol = FXCollections.observableArrayList();

    @FXML
    private TextField voornaam;
    @FXML
    private TextField achternaam;
    @FXML
    private DatePicker geboortedatum;
    @FXML
    private TextField email;
    @FXML
    private TextField straat;
    @FXML
    private TextField huisnr;
    @FXML
    private TextField gemeente;
    @FXML
    private TextField postcode;

    LocalDate date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rol = RolDao.getAllRol();
        cmbRol.setItems(rol);
    }


    @FXML
    void AddPersoneel(ActionEvent event) {

        date = geboortedatum.getValue();
        Date sqlDate = java.sql.Date.valueOf( date );

        int output = cmbRol.getSelectionModel().getSelectedItem().getId();

        Adress adres = new Adress(straat.getText().toString(), Integer.parseInt(huisnr.getText()), gemeente.getText().toString(), Integer.parseInt(postcode.getText()));
        Rol rol = new Rol(output);
        User personeel = new User(voornaam.getText().toString(), achternaam.getText().toString(), sqlDate, email.getText().toString(), adres, rol);
        Boolean add = PersoneelDao.addPersoneel(personeel, output);
        if (add == true)
        {
            System.out.println("yes");
            Alert alertsuc = new Alert(Alert.AlertType.CONFIRMATION);
            alertsuc.setTitle("Toevoegen gelukt");
            alertsuc.setHeaderText(null);
            alertsuc.setContentText("Persoon is toegevoegd!");
            alertsuc.showAndWait();
        }
        else {
            System.out.println("mis");
            Alert alertmis = new Alert(Alert.AlertType.CONFIRMATION);
            alertmis.setTitle("Toevoegen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Persoon is niet toegevoegd! Probeer opnieuw!");
            alertmis.showAndWait();
        }
    }
}
