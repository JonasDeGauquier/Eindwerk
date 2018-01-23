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
        bewoner = BewonerDao.getBewoner(bewoner.getSelectedId());

        Voornaam.setText(String.valueOf(bewoner.getVoornaam().toString()));
        Achternaam.setText(String.valueOf(bewoner.getAchternaam().toString()));
        Geboortedatum.setText(String.valueOf(bewoner.getGeboortedatum().toString()));
        Geboorteplaats.setText(String.valueOf(bewoner.getGeboorteplaats().toString()));
        Geslacht.setText(String.valueOf(bewoner.getGeslacht().toString()));
        BurgerlijkeStaat.setText(String.valueOf(bewoner.getBurgerlijkestaat().toString()));
        Gekoppeld.setText(String.valueOf(bewoner.getGekoppeldMet().toString()));
        OpnameDatum.setText(String.valueOf(bewoner.getOpnamedatum().toString()));
        Geloofsovertuiging.setText(String.valueOf(bewoner.getGeloofsovertuiging().toString()));
        Peter.setText(String.valueOf(bewoner.getPeter().toString()));
        Meter.setText(String.valueOf(bewoner.getMeter().toString()));
        Nationaliteit.setText(String.valueOf(bewoner.getNationaliteit().toString()));
        Rijksregisternr.setText(String.valueOf(bewoner.getRijksregisternr().toString()));
        Identiteitskaartnr.setText(String.valueOf(bewoner.getIndetiteitskaartnr().toString()));
        Huisarts.setText(String.valueOf(bewoner.getHuisdokter().toString()));
        Ziekenhuis.setText(String.valueOf(bewoner.getVoorkeurZiekenhuis().toString()));
        Kamernr.setText(String.valueOf(bewoner.getKamernr().toString()));
        Straat.setText(String.valueOf(bewoner.getAdress().getStraat().toString()));
        Huisnr.setText(String.valueOf(bewoner.getAdress().getHuisnr()));
        Postcode.setText(String.valueOf(bewoner.getAdress().getPostcode()));
        Gemeente.setText(String.valueOf(bewoner.getAdress().getGemeente()).toString());

        Path path = null;
        try {
            File myFile = new File("src/images/"+ bewoner.getVoornaam().toString() + " " + bewoner.getAchternaam().toString() + ".png");
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
