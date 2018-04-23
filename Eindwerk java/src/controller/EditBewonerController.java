package controller;

import DAO.BewonerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Adress;
import model.Bewoner;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class EditBewonerController implements Initializable {
    private Bewoner bewoner = new Bewoner();

    @FXML
    private TextField voornaam, achternaam, geboorteplaats, geslacht, burgerlijkeStaat, gekoppeld, geloofsovertuiging,
            peter, meter, nationaliteit, rijksregisternr, identiteitskaartnr, huisarts, ziekenhuis, kamernr, straat, huisnr, postcode, gemeente;
    @FXML
    private ImageView foto;

    @FXML
    private DatePicker opnamedatum, geboortedatum;

    private LocalDate geboorteDate, opnameDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bewoner = BewonerDao.getBewoner(Bewoner.getSelectedId());

        voornaam.setText(String.valueOf(bewoner.getVoornaam().toString()));
        achternaam.setText(String.valueOf(bewoner.getAchternaam().toString()));
        geboorteplaats.setText(String.valueOf(bewoner.getGeboorteplaats().toString()));
        geslacht.setText(String.valueOf(bewoner.getGeslacht().toString()));
        burgerlijkeStaat.setText(String.valueOf(bewoner.getBurgerlijkestaat().toString()));
        gekoppeld.setText(String.valueOf(bewoner.getGekoppeldMet().toString()));
        geloofsovertuiging.setText(String.valueOf(bewoner.getGeloofsovertuiging().toString()));
        peter.setText(String.valueOf(bewoner.getPeter().toString()));
        meter.setText(String.valueOf(bewoner.getMeter().toString()));
        nationaliteit.setText(String.valueOf(bewoner.getNationaliteit().toString()));
        rijksregisternr.setText(String.valueOf(bewoner.getRijksregisternr().toString()));
        rijksregisternr.setText(String.valueOf(bewoner.getRijksregisternr().toString()));
        identiteitskaartnr.setText(String.valueOf(bewoner.getIndetiteitskaartnr().toString()));
        huisarts.setText(String.valueOf(bewoner.getHuisdokter().toString()));
        ziekenhuis.setText(String.valueOf(bewoner.getVoorkeurZiekenhuis().toString()));
        kamernr.setText(String.valueOf(bewoner.getKamernr().toString()));
        straat.setText(String.valueOf(bewoner.getAdress().getStraat().toString()));
        huisnr.setText(String.valueOf(bewoner.getAdress().getHuisnr()));
        postcode.setText(String.valueOf(bewoner.getAdress().getPostcode()));
        gemeente.setText(String.valueOf(bewoner.getAdress().getGemeente()));

        // Bron: https://stackoverflow.com/questions/18873014/parse-string-date-in-yyyy-mm-dd-format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedGeboortedatum = null;
        Date convertedOpnamedatum = null;
        try {
            convertedGeboortedatum = sdf.parse(String.valueOf(bewoner.getGeboortedatum()));
            convertedOpnamedatum = sdf.parse(String.valueOf(bewoner.getOpnamedatum()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Bron: https://stackoverflow.com/questions/9474121/i-want-to-get-year-month-day-etc-from-java-date-to-compare-with-gregorian-cal
        LocalDate localDateGeboortedatum = convertedGeboortedatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localOpnamedatum = convertedOpnamedatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        geboortedatum.setValue(LocalDate.of(localDateGeboortedatum.getYear(), localDateGeboortedatum.getMonth(), localDateGeboortedatum.getDayOfMonth()));
        opnamedatum.setValue(LocalDate.of(localOpnamedatum.getYear(), localOpnamedatum.getMonth(), localOpnamedatum.getDayOfMonth()));

        Path path = null;
        try {
            File myFile = new File("src/images/" + bewoner.getVoornaam().toString() + " " + bewoner.getAchternaam().toString() + ".png");
            FileOutputStream image = new FileOutputStream(myFile);

            int length = bewoner.getFoto().length;
            byte[] bytes = bewoner.getFoto();
            image.write(bytes, 0, length);

            path = Paths.get(String.valueOf(myFile));
            image.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image2 = new Image(path.toUri().toString());
        foto.setImage(image2);

    }

    @FXML
    void editBeowner(ActionEvent event) {
        voornaam.getStyleClass().remove("error");
        achternaam.getStyleClass().remove("error");
        geboorteplaats.getStyleClass().remove("error");
        geslacht.getStyleClass().remove("error");
        burgerlijkeStaat.getStyleClass().remove("error");
        gekoppeld.getStyleClass().remove("error");
        geloofsovertuiging.getStyleClass().remove("error");
        peter.getStyleClass().remove("error");
        meter.getStyleClass().remove("error");
        nationaliteit.getStyleClass().remove("error");
        rijksregisternr.getStyleClass().remove("error");
        identiteitskaartnr.getStyleClass().remove("error");
        huisarts.getStyleClass().remove("error");
        ziekenhuis.getStyleClass().remove("error");
        kamernr.getStyleClass().remove("error");
        straat.getStyleClass().remove("error");
        huisnr.getStyleClass().remove("error");
        gemeente.getStyleClass().remove("error");
        postcode.getStyleClass().remove("error");

        if (voornaam.getText() == null || voornaam.getText().trim().isEmpty() || achternaam.getText() == null || achternaam.getText().trim().isEmpty() || geboorteplaats.getText() == null || geboorteplaats.getText().trim().isEmpty() || geboortedatum.getValue() == null
                || straat.getText() == null || straat.getText().trim().isEmpty() || huisnr.getText() == null || huisnr.getText().trim().isEmpty() || gemeente.getText() == null || gemeente.getText().trim().isEmpty() || postcode.getText() == null || postcode.getText().trim().isEmpty()
                || geslacht.getText() == null || geslacht.getText().trim().isEmpty() || burgerlijkeStaat.getText() == null || burgerlijkeStaat.getText().trim().isEmpty() || gekoppeld.getText() == null || gekoppeld.getText().trim().isEmpty() || opnamedatum.getValue() == null
                || geloofsovertuiging.getText() == null || geloofsovertuiging.getText().trim().isEmpty() || meter.getText() == null || meter.getText().trim().isEmpty() || peter.getText() == null || peter.getText().trim().isEmpty() || nationaliteit.getText() == null || nationaliteit.getText().trim().isEmpty()
                || rijksregisternr.getText() == null || rijksregisternr.getText().trim().isEmpty() || identiteitskaartnr.getText() == null || identiteitskaartnr.getText().trim().isEmpty() || huisarts.getText() == null || huisarts.getText().trim().isEmpty()
                || ziekenhuis.getText() == null || ziekenhuis.getText().trim().isEmpty() || kamernr.getText() == null || kamernr.getText().trim().isEmpty()
                ) {
            if (voornaam.getText() == null || voornaam.getText().trim().isEmpty()) {
                voornaam.getStyleClass().add("error");
            }
            if (achternaam.getText() == null || achternaam.getText().trim().isEmpty()) {
                achternaam.getStyleClass().add("error");
            }
            if (geboorteplaats.getText() == null || geboorteplaats.getText().trim().isEmpty()) {
                geboorteplaats.getStyleClass().add("error");
            }
            if (straat.getText() == null || straat.getText().trim().isEmpty()) {
                straat.getStyleClass().add("error");
            }
            if (huisnr.getText() == null || huisnr.getText().trim().isEmpty()) {
                huisnr.getStyleClass().add("error");
            }
            if (gemeente.getText() == null || gemeente.getText().trim().isEmpty()) {
                gemeente.getStyleClass().add("error");
            }
            if (postcode.getText() == null || postcode.getText().trim().isEmpty()) {
                postcode.getStyleClass().add("error");
            }
            if (geslacht.getText() == null || geslacht.getText().trim().isEmpty()) {
                geslacht.getStyleClass().add("error");
            }
            if (burgerlijkeStaat.getText() == null || burgerlijkeStaat.getText().trim().isEmpty()) {
                burgerlijkeStaat.getStyleClass().add("error");
            }
            if (gekoppeld.getText() == null || gekoppeld.getText().trim().isEmpty()) {
                gekoppeld.getStyleClass().add("error");
            }
            if (geloofsovertuiging.getText() == null || geloofsovertuiging.getText().trim().isEmpty()) {
                geloofsovertuiging.getStyleClass().add("error");
            }
            if (meter.getText() == null || meter.getText().trim().isEmpty()) {
                meter.getStyleClass().add("error");
            }
            if (peter.getText() == null || peter.getText().trim().isEmpty()) {
                peter.getStyleClass().add("error");
            }
            if (nationaliteit.getText() == null || nationaliteit.getText().trim().isEmpty()) {
                nationaliteit.getStyleClass().add("error");
            }
            if (rijksregisternr.getText() == null || rijksregisternr.getText().trim().isEmpty()) {
                rijksregisternr.getStyleClass().add("error");
            }
            if (identiteitskaartnr.getText() == null || identiteitskaartnr.getText().trim().isEmpty()) {
                identiteitskaartnr.getStyleClass().add("error");
            }
            if (huisarts.getText() == null || huisarts.getText().trim().isEmpty()) {
                huisarts.getStyleClass().add("error");
            }
            if (ziekenhuis.getText() == null || ziekenhuis.getText().trim().isEmpty()) {
                ziekenhuis.getStyleClass().add("error");
            }
            if (kamernr.getText() == null || kamernr.getText().trim().isEmpty()) {
                kamernr.getStyleClass().add("error");
            }
            if (opnamedatum.getValue() == null) {
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Gelieve een opnamedatum in te vullen");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve een opnamedatum in te vullen!");
                alertmis.showAndWait();
            }
            if (geboortedatum.getValue() == null) {
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
        } else {
            if (Validation.checkFirstName(voornaam.getText().toString()) == true && Validation.checkLastName(achternaam.getText().toString()) == true && Validation.checkAlphabetical(geboorteplaats.getText().toString()) == true && Validation.checkAlphabetical(straat.getText().toString()) == true && Validation.checkHouseNumber(huisnr.getText().toString()) == true && Validation.checkAlphabetical(gemeente.getText().toString()) == true && Validation.checkPostalCode(postcode.getText().toString()) == true
                    && Validation.checkAlphabetical(geslacht.getText().toString()) == true && Validation.checkAlphabetical(burgerlijkeStaat.getText().toString()) == true && Validation.checkAlphabetical(gekoppeld.getText().toString()) == true && Validation.checkAlphabetical(geloofsovertuiging.getText().toString()) == true && Validation.checkAlphabetical(meter.getText().toString()) == true && Validation.checkAlphabetical(peter.getText().toString()) == true && Validation.checkAlphabetical(nationaliteit.getText().toString()) == true
                    && Validation.checkRijksregisternr(rijksregisternr.getText().toString()) == true && Validation.checkIdentitiecard(identiteitskaartnr.getText().toString()) == true && Validation.checkAlphabetical(huisarts.getText().toString()) == true && Validation.checkAlphabetical(ziekenhuis.getText().toString()) == true && Validation.checkNumeric(kamernr.getText().toString()) == true) {
                geboorteDate = geboortedatum.getValue();
                Date sqlGeboorteDate = java.sql.Date.valueOf(geboorteDate);

                opnameDate = opnamedatum.getValue();
                Date sqlOpnameDate = java.sql.Date.valueOf(opnameDate);

                Adress adres = new Adress(straat.getText().toString(), Integer.parseInt(huisnr.getText().toString()), gemeente.getText().toString(), Integer.parseInt(postcode.getText()));
                Bewoner bewoner = new Bewoner(voornaam.getText().toString(), achternaam.getText().toString(), sqlGeboorteDate, geboorteplaats.getText().toString(),
                        geslacht.getText().toString(), burgerlijkeStaat.getText().toString(), gekoppeld.getText().toString(), sqlOpnameDate,
                        geloofsovertuiging.getText().toString(), meter.getText().toString(), peter.getText().toString(), nationaliteit.getText().toString(), Long.parseLong(rijksregisternr.getText().toString()),
                        identiteitskaartnr.getText().toString(), huisarts.getText().toString(), ziekenhuis.getText().toString(), Integer.parseInt(kamernr.getText().toString()), adres);
                Boolean edit = BewonerDao.editBewoner(bewoner);
                if (edit == true) {
                    // Bron: https://github.com/PlusHaze/TrayNotification
                    String title = "Aanpassen gelukt";
                    String message = "Bewoner is aangepast!";
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
                    Alert alertmis = new Alert(Alert.AlertType.CONFIRMATION);
                    alertmis.setTitle("Toevoegen mislukt");
                    alertmis.setHeaderText(null);
                    alertmis.setContentText("Bewoner is niet aangepast! Probeer opnieuw!");
                    alertmis.showAndWait();
                }
            } else {
                if (Validation.checkFirstName(voornaam.getText().toString()) == false) {
                    voornaam.getStyleClass().add("error");
                }
                if (Validation.checkLastName(achternaam.getText().toString()) == false) {
                    achternaam.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(geboorteplaats.getText().toString()) == false) {
                    geboorteplaats.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(straat.getText().toString()) == false) {
                    straat.getStyleClass().add("error");
                }

                if (Validation.checkHouseNumber(huisnr.getText().toString()) == false) {
                    huisnr.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(gemeente.getText().toString()) == false) {
                    gemeente.getStyleClass().add("error");
                }
                if (Validation.checkPostalCode(postcode.getText().toString()) == false) {
                    postcode.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(geslacht.getText().toString()) == false) {
                    geslacht.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(burgerlijkeStaat.getText().toString()) == false) {
                    burgerlijkeStaat.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(gekoppeld.getText().toString()) == false) {
                    gekoppeld.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(gekoppeld.getText().toString()) == false) {
                    geloofsovertuiging.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(meter.getText().toString()) == false) {
                    meter.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(peter.getText().toString()) == false) {
                    peter.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(nationaliteit.getText().toString()) == false) {
                    nationaliteit.getStyleClass().add("error");
                }
                if (Validation.checkRijksregisternr(rijksregisternr.getText().toString()) == false) {
                    rijksregisternr.getStyleClass().add("error");
                }
                if (Validation.checkIdentitiecard(identiteitskaartnr.getText().toString()) == false) {
                    identiteitskaartnr.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(huisarts.getText().toString()) == false) {
                    huisarts.getStyleClass().add("error");
                }
                if (Validation.checkAlphabetical(ziekenhuis.getText().toString()) == false) {
                    ziekenhuis.getStyleClass().add("error");
                }
                if (Validation.checkNumeric(kamernr.getText().toString()) == false) {
                    kamernr.getStyleClass().add("error");
                }
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Bewoner bewerken mislukt");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve alle velden correct in te vullen!");
                alertmis.showAndWait();
            }
        }
    }
}
