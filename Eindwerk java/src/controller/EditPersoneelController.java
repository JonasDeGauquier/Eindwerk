package controller;

import DAO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.*;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditPersoneelController  implements Initializable {
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
    @FXML
    private Label lblBadge;

    private LocalDate date;
    private User personeel = new User();
    private Integer badge;
    private Rfid rfid;
    private Login login = new Login();
    private Boolean addBadge;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personeel = PersoneelDao.getPersoneel(User.getSelectedId());

        voornaam.setText(personeel.getVoornaam().toString());
        achternaam.setText(personeel.getAchternaam().toString());
        email.setText(personeel.getEmail().toString());
        straat.setText(personeel.getAdress().getStraat().toString());
        huisnr.setText(String.valueOf(personeel.getAdress().getHuisnr()));
        gemeente.setText(personeel.getAdress().getGemeente().toString());
        postcode.setText(String.valueOf(personeel.getAdress().getPostcode()));

        // Bron: https://stackoverflow.com/questions/18873014/parse-string-date-in-yyyy-mm-dd-format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedGeboortedatum = null;
        Date convertedOpnamedatum = null;
        try {
            convertedGeboortedatum = sdf.parse(String.valueOf(personeel.getGeboortedatum()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Bron: https://stackoverflow.com/questions/9474121/i-want-to-get-year-month-day-etc-from-java-date-to-compare-with-gregorian-cal
        LocalDate localDateGeboortedatum = convertedGeboortedatum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        geboortedatum.setValue(LocalDate.of(localDateGeboortedatum.getYear(), localDateGeboortedatum.getMonth(), localDateGeboortedatum.getDayOfMonth()));
    }

    @FXML
    protected void addBadge(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Badge aanpassen");
        dialog.setContentText("Scan de badge");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String rf = result.get();
            if (Validation.checkNumeric(rf)) {
                if (rf.length() == 10) {
                    if (LoginDao.checkLoginrfid(Integer.valueOf(rf))) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Badge toevoegen niet gelukt");
                        alert.setHeaderText(null);
                        alert.setContentText("Badge wordt al gebruikt. Probeer opnieuw!");
                        alert.showAndWait();
                    } else {
                        badge = Integer.valueOf(rf);
                        login.setLoginId(LoginDao.getLoginId(badge));
                        rfid = new Rfid(login,badge);
                        lblBadge.setText("Badge toegevoegd");
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Badge toevoegen niet gelukt");
                    alert.setHeaderText(null);
                    alert.setContentText("Badge niet toegevoegd. Probeer opnieuw!");
                    alert.showAndWait();
                }
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Badge toevoegen niet gelukt");
                alert.setHeaderText(null);
                alert.setContentText("Badge niet gevonden. Probeer opnieuw!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Badge toevoegen niet gelukt");
            alert.setHeaderText(null);
            alert.setContentText("Badge niet toegevoegd. Probeer opnieuw!");
            alert.showAndWait();
        }
    }

    @FXML
    void AddPersoneel(ActionEvent event) {
        voornaam.getStyleClass().remove("error");
        achternaam.getStyleClass().remove("error");
        straat.getStyleClass().remove("error");
        huisnr.getStyleClass().remove("error");
        gemeente.getStyleClass().remove("error");
        postcode.getStyleClass().remove("error");
        email.getStyleClass().remove("error");

        date = geboortedatum.getValue();
        Date sqlDate = java.sql.Date.valueOf( date );

        if (voornaam.getText() == null || voornaam.getText().trim().isEmpty() || achternaam.getText() == null || achternaam.getText().trim().isEmpty() || geboortedatum.getValue() == null || email.getText() == null || email.getText().trim().isEmpty()
                || straat.getText() == null || straat.getText().trim().isEmpty() || huisnr.getText() == null || huisnr.getText().trim().isEmpty() || gemeente.getText() == null || gemeente.getText().trim().isEmpty() || postcode.getText() == null || postcode.getText().trim().isEmpty())
        {
            if (voornaam.getText() == null || voornaam.getText().trim().isEmpty())
            {
                voornaam.getStyleClass().add("error");
            }
            if (achternaam.getText() == null || achternaam.getText().trim().isEmpty())
            {
                achternaam.getStyleClass().add("error");
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
            if (email.getText() == null || email.getText().trim().isEmpty())
            {
                email.getStyleClass().add("error");
            }
            Alert alertmis = new Alert(Alert.AlertType.ERROR);
            alertmis.setTitle("Personeel aanpassen mislukt");
            alertmis.setHeaderText(null);
            alertmis.setContentText("Gelieve alle velden in te vullen!");
            alertmis.showAndWait();
        }  else {
            if (Validation.checkFirstName(voornaam.getText().toString()) && Validation.checkLastName(achternaam.getText().toString()) && Validation.checkAlphabetical(straat.getText().toString()) && Validation.checkHouseNumber(huisnr.getText()) && Validation.checkAlphabetical(gemeente.getText().toString()) && Validation.checkPostalCode(postcode.getText().toString()) && Validation.checkEmail(email.getText().toString()))
            {
                Adress adres = new Adress(personeel.getAdress().getId(), straat.getText().toString(), Integer.parseInt(huisnr.getText()), gemeente.getText().toString(), Integer.parseInt(postcode.getText()));
                User personeel = new User(voornaam.getText().toString(), achternaam.getText().toString(), sqlDate, email.getText().toString(), adres);
                Boolean eddit = PersoneelDao.editPersoneel(personeel);
                if (rfid == null) {
                    addBadge = true;
                } else {
                    // Bron: https://codereview.stackexchange.com/questions/137964/string-hash-generator
                    MessageDigest objMD5 = null;
                    try {
                        objMD5 = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    byte[] bytMD5 = objMD5.digest(voornaam.getText().getBytes());
                    BigInteger intNumMD5 = new BigInteger(1, bytMD5);
                    String password = String.format("%032x", intNumMD5);

                    int login_id = LoginDao.getId(email.getText(), password );

                    if (RfidDao.checkRfid(login_id) == 0) {
                        System.out.println("sdfsdfdsf");
                        Login login = new Login();

                        // Bron: https://codereview.stackexchange.com/questions/137964/string-hash-generator
                        objMD5 = null;
                        try {
                            objMD5 = MessageDigest.getInstance("MD5");
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        bytMD5 = objMD5.digest(voornaam.getText().getBytes());
                        intNumMD5 = new BigInteger(1, bytMD5);
                        password = String.format("%032x", intNumMD5);

                        login.setLoginId(LoginDao.getId(email.getText(), password ));
                        rfid.setLogin(login);
                        addBadge = RfidDao.addRfid(rfid);
                    } else {
                        Login login = new Login();

                        // Bron: https://codereview.stackexchange.com/questions/137964/string-hash-generator
                        objMD5 = null;
                        try {
                            objMD5 = MessageDigest.getInstance("MD5");
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        bytMD5 = objMD5.digest(voornaam.getText().getBytes());
                        intNumMD5 = new BigInteger(1, bytMD5);
                        password = String.format("%032x", intNumMD5);

                        login.setLoginId(LoginDao.getId(email.getText(), password ));
                        rfid.setLogin(login);
                        addBadge = RfidDao.editRfid(rfid);
                    }
                }
                if (eddit && addBadge)
                {
                    // Bron: https://github.com/PlusHaze/TrayNotification
                    String title = "Aanpassen gelukt";
                    String message = "Persoon is aangepast!";
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
                }
                else {
                    Alert alertmis = new Alert(Alert.AlertType.CONFIRMATION);
                    alertmis.setTitle("Aanpassen mislukt");
                    alertmis.setHeaderText(null);
                    alertmis.setContentText("Persoon is niet aangepast! Probeer opnieuw!");
                    alertmis.showAndWait();
                }
            }  else {
                if (!Validation.checkFirstName(voornaam.getText())) {
                    voornaam.getStyleClass().add("error");
                }
                if (!Validation.checkLastName(achternaam.getText())) {
                    achternaam.getStyleClass().add("error");
                }
                if (!Validation.checkAlphabetical(straat.getText()))
                {
                    straat.getStyleClass().add("error");
                }
                if (!Validation.checkHouseNumber(huisnr.getText()))
                {
                    huisnr.getStyleClass().add("error");
                }
                if (!Validation.checkAlphabetical(gemeente.getText()))
                {
                    gemeente.getStyleClass().add("error");
                }
                if (!Validation.checkPostalCode(postcode.getText()))
                {
                    postcode.getStyleClass().add("error");
                }
                if (!Validation.checkEmail(email.getText().toString()))
                {
                    email.getStyleClass().add("error");
                }
                Alert alertmis = new Alert(Alert.AlertType.ERROR);
                alertmis.setTitle("Personeel aanpassen mislukt");
                alertmis.setHeaderText(null);
                alertmis.setContentText("Gelieve alle velden correct in te vullen!");
                alertmis.showAndWait();
            }
        }
    }

}
