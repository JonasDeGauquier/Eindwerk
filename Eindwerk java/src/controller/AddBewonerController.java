package controller;

import DAO.BewonerDao;
import DAO.PersoneelDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Adress;
import model.Bewoner;
import org.postgresql.util.PGBinaryObject;
import org.postgresql.util.PGbytea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class AddBewonerController {
    @FXML
    private TextField voornaam, achternaam, geboorteplaats, geslacht, burgerlijkestaat, gekoppeldmet, geloofsovertuiging, peter, meter, nationaliteit, rijksregisternr, identiteitskaartnr, huisarts, voorkeursziekenhuis, kamernr, straat, huisnr, postcode, gemeente;
    @FXML
    private DatePicker geboortedatum, opnamedatum;
    @FXML
    private Label foto;

    LocalDate geboorteDate, opnameDate;
    private File byteaFile;

    @FXML
    void addFoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        Window stage = null;
        byteaFile = fileChooser.showOpenDialog(stage);


        if (byteaFile != null) {
            foto.setText("Foto geselecteerd!");
        }else {

        }
    }

    @FXML
    void AddBewoner(ActionEvent event) {
        geboorteDate = geboortedatum.getValue();
        Date sqlGeboorteDate = java.sql.Date.valueOf( geboorteDate );

        opnameDate = opnamedatum.getValue();
        Date sqlOpnameDate = java.sql.Date.valueOf( opnameDate );

        File img = new File(String.valueOf(byteaFile));
        byte[] bFile = new byte[(int) img.length()];

        //read file into bytes[]
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileInputStream.read(bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Adress adres = new Adress(straat.getText().toString(), Integer.parseInt(huisnr.getText()), gemeente.getText().toString(), Integer.parseInt(postcode.getText()));
        Bewoner bewoner = new Bewoner(voornaam.getText().toString(), achternaam.getText().toString(), sqlGeboorteDate, geboorteplaats.getText().toString(),
                geslacht.getText().toString(), burgerlijkestaat.getText().toString(), gekoppeldmet.getText().toString(), sqlOpnameDate,
                geloofsovertuiging.getText().toString(), meter.getText().toString(), peter.getText().toString(), nationaliteit.getText().toString(), Integer.parseInt(rijksregisternr.getText()),
                Integer.parseInt(identiteitskaartnr.getText()), huisarts.getText().toString(), voorkeursziekenhuis.getText().toString(), Integer.parseInt(kamernr.getText()), adres, bFile);
        Boolean add = BewonerDao.addBewoner(bewoner);
        if (add == true)
        {
            Alert alertsuc = new Alert(Alert.AlertType.CONFIRMATION);
            alertsuc.setTitle("Toevoegen gelukt");
            alertsuc.setHeaderText(null);
            alertsuc.setContentText("Bewoner is toegevoegd!");
            alertsuc.showAndWait();
        }
        else {
            Alert alertmis = new Alert(Alert.AlertType.CONFIRMATION);
            alertmis.setTitle("Toevoegen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Bewoner is niet toegevoegd! Probeer opnieuw!");
            alertmis.showAndWait();
        }
    }
}
