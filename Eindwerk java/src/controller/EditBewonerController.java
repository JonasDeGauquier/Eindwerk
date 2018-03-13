package controller;

import DAO.BewonerDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Bewoner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
            peter, meter, nationaliteit, rijksregisternr, identiteitskaartnr, huisarts, ziekenhuis, kamernr, straat, huisnr,postcode, gemeente;
    @FXML
    private ImageView foto;

    @FXML
    private DatePicker opnamedatum, geboortedatum;

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
