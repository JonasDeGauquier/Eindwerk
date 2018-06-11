package controller;

import DAO.BewonerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Bewoner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ShowBewonerGegevensController implements Initializable {
    private Bewoner bewoner = new Bewoner();

    @FXML
    private Label Voornaam, Achternaam, Geboortedatum, Geboorteplaats, Geslacht, BurgerlijkeStaat, Gekoppeld, OpnameDatum, Geloofsovertuiging,
            Peter, Meter, Nationaliteit, Rijksregisternr, Identiteitskaartnr, Huisarts, Ziekenhuis, Kamernr, Straat, Huisnr,Postcode, Gemeente;
    @FXML
    private ImageView foto;

    private AnchorPane content;
    private SplitPane splitpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bewoner = BewonerDao.getBewoner(Bewoner.getSelectedId());

        Voornaam.setText(String.valueOf(bewoner.getVoornaam()));
        Achternaam.setText(String.valueOf(bewoner.getAchternaam()));
        Geboortedatum.setText(String.valueOf(bewoner.getGeboortedatum().toString()));
        Geboorteplaats.setText(String.valueOf(bewoner.getGeboorteplaats()));
        Geslacht.setText(String.valueOf(bewoner.getGeslacht()));
        BurgerlijkeStaat.setText(String.valueOf(bewoner.getBurgerlijkestaat()));
        Gekoppeld.setText(String.valueOf(bewoner.getGekoppeldMet()));
        OpnameDatum.setText(String.valueOf(bewoner.getOpnamedatum().toString()));
        Geloofsovertuiging.setText(String.valueOf(bewoner.getGeloofsovertuiging()));
        Peter.setText(String.valueOf(bewoner.getPeter()));
        Meter.setText(String.valueOf(bewoner.getMeter()));
        Nationaliteit.setText(String.valueOf(bewoner.getNationaliteit()));
        Rijksregisternr.setText(String.valueOf(bewoner.getRijksregisternr().toString()));
        Identiteitskaartnr.setText(String.valueOf(bewoner.getIndetiteitskaartnr()));
        Huisarts.setText(String.valueOf(bewoner.getHuisdokter()));
        Ziekenhuis.setText(String.valueOf(bewoner.getVoorkeurZiekenhuis()));
        Kamernr.setText(String.valueOf(bewoner.getKamernr().toString()));
        Straat.setText(String.valueOf(bewoner.getAdress().getStraat()));
        Huisnr.setText(String.valueOf(bewoner.getAdress().getHuisnr()));
        Postcode.setText(String.valueOf(bewoner.getAdress().getPostcode()));
        Gemeente.setText(String.valueOf(bewoner.getAdress().getGemeente()));

        Path path = null;
        try {
            File myFile = new File("src/images/"+ bewoner.getVoornaam() + " " + bewoner.getAchternaam() + ".png");
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
}
