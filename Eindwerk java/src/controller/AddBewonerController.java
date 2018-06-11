package controller;

import DAO.BewonerDao;
import DAO.PersoneelDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import model.Adress;
import model.Bewoner;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AddBewonerController  implements Initializable{
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
        }
        else {
            foto.setText("Geen foto geselecteerd!");
        }
    }

    @FXML
    void AddBewoner(ActionEvent event) {
        voornaam.getStyleClass().remove("error");
        achternaam.getStyleClass().remove("error");
        geboorteplaats.getStyleClass().remove("error");
        geslacht.getStyleClass().remove("error");
        burgerlijkestaat.getStyleClass().remove("error");
        gekoppeldmet.getStyleClass().remove("error");
        geloofsovertuiging.getStyleClass().remove("error");
        peter.getStyleClass().remove("error");
        meter.getStyleClass().remove("error");
        nationaliteit.getStyleClass().remove("error");
        rijksregisternr.getStyleClass().remove("error");
        identiteitskaartnr.getStyleClass().remove("error");
        huisarts.getStyleClass().remove("error");
        voorkeursziekenhuis.getStyleClass().remove("error");
        kamernr.getStyleClass().remove("error");
        straat.getStyleClass().remove("error");
        huisnr.getStyleClass().remove("error");
        gemeente.getStyleClass().remove("error");
        postcode.getStyleClass().remove("error");

        if (voornaam.getText() == null || voornaam.getText().trim().isEmpty() || achternaam.getText() == null || achternaam.getText().trim().isEmpty() || geboorteplaats.getText() == null || geboorteplaats.getText().trim().isEmpty() || geboortedatum.getValue() == null
                || straat.getText() == null || straat.getText().trim().isEmpty() || huisnr.getText() == null || huisnr.getText().trim().isEmpty() || gemeente.getText() == null || gemeente.getText().trim().isEmpty() || postcode.getText() == null || postcode.getText().trim().isEmpty()
                || geslacht.getText() == null || geslacht.getText().trim().isEmpty() || burgerlijkestaat.getText() == null || burgerlijkestaat.getText().trim().isEmpty() || gekoppeldmet.getText() == null || gekoppeldmet.getText().trim().isEmpty() || opnamedatum.getValue() == null
                || geloofsovertuiging.getText() == null || geloofsovertuiging.getText().trim().isEmpty() || meter.getText() == null || meter.getText().trim().isEmpty() || peter.getText() == null || peter.getText().trim().isEmpty() || nationaliteit.getText() == null || nationaliteit.getText().trim().isEmpty()
                || rijksregisternr.getText() == null || rijksregisternr.getText().trim().isEmpty() || identiteitskaartnr.getText() == null || identiteitskaartnr.getText().trim().isEmpty() || huisarts.getText() == null || huisarts.getText().trim().isEmpty()
                || voorkeursziekenhuis.getText() == null || voorkeursziekenhuis.getText().trim().isEmpty() || kamernr.getText() == null || kamernr.getText().trim().isEmpty() || byteaFile == null
                )
        {
            if (voornaam.getText() == null || voornaam.getText().trim().isEmpty())
            {
                voornaam.getStyleClass().add("error");
            }
            if (achternaam.getText() == null || achternaam.getText().trim().isEmpty())
            {
                achternaam.getStyleClass().add("error");
            }
            if (geboorteplaats.getText() == null || geboorteplaats.getText().trim().isEmpty())
            {
                geboorteplaats.getStyleClass().add("error");
            }
            if (straat.getText() == null || straat.getText().trim().isEmpty())
            {
                straat.getStyleClass().add("error");
            }
            if (huisnr.getText() == null || huisnr.getText().trim().isEmpty())
            {
                huisnr.getStyleClass().add("error");
            }
            if (gemeente.getText() == null || gemeente.getText().trim().isEmpty())
            {
                gemeente.getStyleClass().add("error");
            }
            if (postcode.getText() == null || postcode.getText().trim().isEmpty())
            {
                postcode.getStyleClass().add("error");
            }
            if (geslacht.getText() == null || geslacht.getText().trim().isEmpty())
            {
                geslacht.getStyleClass().add("error");
            }
            if (burgerlijkestaat.getText() == null || burgerlijkestaat.getText().trim().isEmpty())
            {
                burgerlijkestaat.getStyleClass().add("error");
            }
            if (gekoppeldmet.getText() == null || gekoppeldmet.getText().trim().isEmpty())
            {
                gekoppeldmet.getStyleClass().add("error");
            }
            if (geloofsovertuiging.getText() == null || geloofsovertuiging.getText().trim().isEmpty())
            {
                geloofsovertuiging.getStyleClass().add("error");
            }
            if (meter.getText() == null || meter.getText().trim().isEmpty())
            {
                meter.getStyleClass().add("error");
            }
            if (peter.getText() == null || peter.getText().trim().isEmpty())
            {
                peter.getStyleClass().add("error");
            }
            if (nationaliteit.getText() == null || nationaliteit.getText().trim().isEmpty())
            {
                nationaliteit.getStyleClass().add("error");
            }
            if (rijksregisternr.getText() == null || rijksregisternr.getText().trim().isEmpty())
            {
                rijksregisternr.getStyleClass().add("error");
            }
            if (identiteitskaartnr.getText() == null || identiteitskaartnr.getText().trim().isEmpty())
            {
                identiteitskaartnr.getStyleClass().add("error");
            }
            if (huisarts.getText() == null || huisarts.getText().trim().isEmpty())
            {
                huisarts.getStyleClass().add("error");
            }
            if (voorkeursziekenhuis.getText() == null || voorkeursziekenhuis.getText().trim().isEmpty())
            {
                voorkeursziekenhuis.getStyleClass().add("error");
            }
            if (kamernr.getText() == null || kamernr.getText().trim().isEmpty())
            {
                kamernr.getStyleClass().add("error");
            }
            if (byteaFile == null)
            {
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Gelieve een foto te selecteren");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve een foto te selecteren!");
                alertmis.showAndWait();
            }
            if (opnamedatum.getValue() == null )
            {
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Gelieve een opnamedatum in te vullen");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve een opnamedatum in te vullen!");
                alertmis.showAndWait();
            }
            if (geboortedatum.getValue() == null )
            {
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Gelieve een geboortedatum in te vullen");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve een geboortedatum in te vullen!");
                alertmis.showAndWait();
            }

            Alert alertmis = new Alert(Alert.AlertType.ERROR);
            alertmis.setTitle("Bewoner toevoegen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Gelieve alle velden in te vullen!");
            alertmis.showAndWait();
        }
        else {
            if (Validation.checkFirstName(voornaam.getText()) == true && Validation.checkLastName(achternaam.getText()) == true && Validation.checkAlphabetical(geboorteplaats.getText()) == true && Validation.checkAlphabetical(straat.getText()) == true && Validation.checkHouseNumber(huisnr.getText()) == true && Validation.checkAlphabetical(gemeente.getText()) == true && Validation.checkPostalCode(postcode.getText()) == true
                    && Validation.checkAlphabetical(geslacht.getText()) == true && Validation.checkAlphabetical(burgerlijkestaat.getText()) == true && Validation.checkAlphabetical(gekoppeldmet.getText()) == true && Validation.checkAlphabetical(geloofsovertuiging.getText()) == true && Validation.checkAlphabetical(meter.getText()) == true && Validation.checkAlphabetical(peter.getText()) == true && Validation.checkAlphabetical(nationaliteit.getText()) == true
                    && Validation.checkRijksregisternr(rijksregisternr.getText()) == true  && Validation.checkIdentitiecard(identiteitskaartnr.getText()) == true && Validation.checkAlphabetical(huisarts.getText()) == true && Validation.checkAlphabetical(voorkeursziekenhuis.getText()) == true && Validation.checkNumeric(kamernr.getText()) == true)
            {
                geboorteDate = geboortedatum.getValue();
                Date sqlGeboorteDate = java.sql.Date.valueOf( geboorteDate );

                opnameDate = opnamedatum.getValue();
                Date sqlOpnameDate = java.sql.Date.valueOf( opnameDate );

                // Mkyong. (2010, 6 april). Java – How to convert File to byte[]. Geraadpleegd op 2 januari 2018, van Https://www.mkyong.com/java/how-to-convert-file-into-an-array-of-bytes/
                File img = new File(String.valueOf(byteaFile));
                byte[] bFile = new byte[(int) img.length()];

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

                Adress adres = new Adress(straat.getText(), Integer.parseInt(huisnr.getText()), gemeente.getText(), Integer.parseInt(postcode.getText()));
                Bewoner bewoner = new Bewoner(voornaam.getText(), achternaam.getText(), sqlGeboorteDate, geboorteplaats.getText(),
                        geslacht.getText(), burgerlijkestaat.getText(), gekoppeldmet.getText(), sqlOpnameDate,
                        geloofsovertuiging.getText(), meter.getText(), peter.getText(), nationaliteit.getText(),Long.parseLong(rijksregisternr.getText()),
                        identiteitskaartnr.getText(), huisarts.getText(), voorkeursziekenhuis.getText(), Integer.parseInt(kamernr.getText()), adres, bFile);
                Boolean add = BewonerDao.addBewoner(bewoner);
                if (add == true)
                {
                    // PlusHaze. (2016, 3 maart). Tray notification. Geraadpleegd op 29 januari 2018, van https://github.com/PlusHaze/TrayNotification
                    String title = "Toevoegen gelukt";
                    String message = "Bewoner is toegevoegd!";
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
                    Alert alertmis = new Alert(Alert.AlertType.CONFIRMATION);
                    alertmis.setTitle("Toevoegen mislukt");
                    alertmis.setHeaderText(null);
                    alertmis.setContentText("Bewoner is niet toegevoegd! Probeer opnieuw!");
                    alertmis.showAndWait();
                }
            } else {
                if (Validation.checkFirstName(voornaam.getText()) == false)
                {
                    voornaam.getStyleClass().add("error");
                }
                if (Validation.checkLastName(achternaam.getText()) == false)
                {
                    achternaam.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(geboorteplaats.getText()) == false)
                {
                    geboorteplaats.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(straat.getText()) == false)
                {
                    straat.getStyleClass().add("error");
                }

                if (Validation.checkHouseNumber(huisnr.getText()) == false)
                {
                    huisnr.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(gemeente.getText()) == false)
                {
                    gemeente.getStyleClass().add("error");
                }
                if (Validation.checkPostalCode(postcode.getText()) == false)
                {
                    postcode.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(geslacht.getText()) == false)
                {
                    geslacht.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(burgerlijkestaat.getText()) == false)
                {
                    burgerlijkestaat.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(gekoppeldmet.getText()) == false)
                {
                    gekoppeldmet.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(gekoppeldmet.getText()) == false)
                {
                    geloofsovertuiging.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(meter.getText()) == false)
                {
                    meter.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(peter.getText()) == false)
                {
                    peter.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(nationaliteit.getText()) == false)
                {
                    nationaliteit.getStyleClass().add("error");
                }
                if (Validation.checkRijksregisternr(rijksregisternr.getText()) == false)
                {
                    rijksregisternr.getStyleClass().add("error");
                }
                if (Validation.checkIdentitiecard(identiteitskaartnr.getText()) == false)
                {
                    identiteitskaartnr.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(huisarts.getText()) == false)
                {
                    huisarts.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(voorkeursziekenhuis.getText()) == false)
                {
                    voorkeursziekenhuis.getStyleClass().add("error");
                }
                if (Validation.checkNumeric(kamernr.getText()) == false)
                {
                    kamernr.getStyleClass().add("error");
                }
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Bewoner toevoegen mislukt");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve alle velden correct in te vullen!");
                alertmis.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        geboortedatum.setShowWeekNumbers(false);
        geboortedatum.setValue(LocalDate.now().minusYears(65));
        geboortedatum.setEditable(false);
        final Callback<DatePicker, DateCell> afterCurrent = new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isAfter(LocalDate.now().minusYears(65))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #EEEEEE;");
                        }
                    }
                };
            }
        };
        geboortedatum.setDayCellFactory(afterCurrent);

        opnamedatum.setShowWeekNumbers(false);
        opnamedatum.setValue(LocalDate.now());
        opnamedatum.setEditable(false);
        final Callback<DatePicker, DateCell> beforeCurrent = new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #EEEEEE;");
                        }
                    }
                };
            }
        };
        opnamedatum.setDayCellFactory(beforeCurrent);
    }
}
