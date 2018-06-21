package controller.Verpleegster;

import DAO.BewonerDao;
import com.jfoenix.controls.JFXToolbar;
import controller.HomeController;
import controller.ShowDossierController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Bewoner;
import model.BewonersDossier;
import model.Contactpersoon;
import model.Verpleegdossier;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

public class BewonerVerpleegsterController implements Initializable {
    @FXML
    private TableView<Bewoner> BewonersTable = new TableView<>();
    @FXML
    private TableColumn voornaam, achternaam, plaats;
    @FXML
    private TextField search = new TextField();
    private AnchorPane content;
    @FXML
    private SplitPane splitpane;
    @FXML
    private JFXToolbar toolbar;
    @FXML
    private Accordion accordion;
    @FXML
    private TitledPane toonBewonerGegevens, toonBewonerDossier, toonVerpleegDossier, toonContactpersoon;

    private ObservableList bewoners = FXCollections.observableArrayList();
    private ObservableList searchList = FXCollections.observableArrayList();
    private ObservableList plaatsen = FXCollections.observableArrayList();

    private Button save = new Button();
    private Button add = new Button();
    private Button edit = new Button();
    private Button delete = new Button();

    private BewonersDossier dossier = new BewonersDossier();
    private Bewoner bewoner = new Bewoner();
    private Verpleegdossier verpleegDossier = new Verpleegdossier();
    private Contactpersoon contactpersoon = new Contactpersoon();

    @FXML
    private Label Allergieën, GroteOperaties, ReanimatieWens, Privacy, Incontinentie, VoornaamTonen, Achternaam,
            Geboortedatum, Geboorteplaats, Geslacht, BurgerlijkeStaat, Gekoppeld, OpnameDatum, Geloofsovertuiging,
            Peter, Meter, Nationaliteit, Rijksregisternr, Identiteitskaartnr, Huisarts, Ziekenhuis, Kamernr, Straat,
            Huisnr, Postcode, Gemeente, Wondzorg, Bloedafname, Suikerziekte, VroegerBeroep, Specifiekewensen,
            voornaamContactpersoon, achternaamContactpersoon, identiteitiskaartnrContactpersoon, relatieContactpersoon,
            telefoonContactpersoon, emailContactpersoon, straatContactpersoon, huisnrContactpersoon, gemeenteContactpersoon,
            postcodeContactpersoon;

    @FXML
    private ImageView foto;

    @FXML
    private void handleKeyPressed(KeyEvent ke) {
        if (search.textProperty().get().isEmpty()) {
            BewonersTable.getItems().setAll(bewoners);
        } else {
            searchList = BewonerDao.getAllBewonersFromSearch(search.getText());
            voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
            achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
            BewonersTable.getItems().setAll(searchList);
        }
    }

    @FXML
    public void bewonerGegevens(MouseEvent event) {
        editIcon("../gui/BewonerBewerken.fxml");
        edit.setDisable(false);
        editIconTooltip("Bewoner gegevens bewerken");
    }

    @FXML
    public void bewonerDossier(MouseEvent event) {
        editIcon("../gui/BewonerDossierBewerken.fxml");
        edit.setDisable(false);
        editIconTooltip("Bewonerdossier bewerken");
        dossier = null;
        dossier = BewonerDao.getDossier(Bewoner.getSelectedId());
        if (dossier == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Geen dossier gevonden");
            alert.setHeaderText(null);
            alert.setContentText("De geselecteerde bewoneer heeft nog geen dossier. Wilt u een dossier toevoegen?");
            ButtonType buttonTypeYes = new ButtonType("Ja");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                Bewoner bewoner = new Bewoner();
                Bewoner.setSelectedId(Bewoner.getSelectedId());
                try {
                    URL paneUrl = getClass().getResource("../gui/BewonerDossierToevoegen.fxml");
                    Pane pane = FXMLLoader.load(paneUrl);

                    splitpane.getItems().remove(1);
                    splitpane.getItems().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void verpleegDossier(MouseEvent event) {
        editIcon("../gui/VerpleegdossierBewerken.fxml");
        edit.setDisable(false);
        editIconTooltip("Verpleegdossier bewerken");
        verpleegDossier = null;
        verpleegDossier = BewonerDao.getVerpleegDossier(Bewoner.getSelectedId());

        if (verpleegDossier == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Geen verpleegdossier gevonden");
            alert.setHeaderText(null);
            alert.setContentText("De geselecteerde bewoneer heeft nog geen verpleegdossier. Wilt u een dossier toevoegen?");
            ButtonType buttonTypeYes = new ButtonType("Ja");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                Bewoner bewoner = new Bewoner();
                Bewoner.setSelectedId(Bewoner.getSelectedId());
                try {
                    URL paneUrl = getClass().getResource("../gui/verpleegdossierToevoegen.fxml");
                    Pane pane = FXMLLoader.load(paneUrl);

                    splitpane.getItems().remove(1);
                    splitpane.getItems().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void contactpersoon(MouseEvent event) {
        editIcon("../gui/ContactpersoonBewerken.fxml");
        edit.setDisable(false);
        editIconTooltip("Contactpersoon bewerken");
        contactpersoon = null;
        contactpersoon = BewonerDao.getContactpersoon(Bewoner.getSelectedId());

        if (contactpersoon == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Geen contactpersoon gevonden");
            alert.setHeaderText(null);
            alert.setContentText("De geselecteerde bewoneer heeft nog geen contactpersoon. Wilt u een contactpersoon toevoegen?");
            ButtonType buttonTypeYes = new ButtonType("Ja");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                Bewoner bewoner = new Bewoner();
                Bewoner.setSelectedId(Bewoner.getSelectedId());
                try {
                    URL paneUrl = getClass().getResource("../gui/ContactpersoonToevoegen.fxml");
                    Pane pane = FXMLLoader.load(paneUrl);

                    splitpane.getItems().remove(1);
                    splitpane.getItems().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void clickItem(MouseEvent event) {
        Bewoner selectedBewoner = BewonersTable.getSelectionModel().getSelectedItem();
        Bewoner.setSelectedId(selectedBewoner.getId());
        laadBewonerGegevens(selectedBewoner);
        laadBewonerDossier(selectedBewoner);
        laadVerpleegDossier(selectedBewoner);
        laadContactpersoon(selectedBewoner);
        accordion.setExpandedPane(toonBewonerGegevens);
        bewonerGegevens(event);
    }

    private void laadBewonerGegevens(Bewoner selectedBewoner) {
        bewoner = null;
        bewoner = BewonerDao.getBewoner(selectedBewoner.getId());

        if (bewoner == null) {
            toonBewonerGegevens.setCollapsible(false);
        } else {
            VoornaamTonen.setText(String.valueOf(bewoner.getVoornaam()));
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
                File myFile = new File("src/images/" + bewoner.getVoornaam() + " " + bewoner.getAchternaam() + ".png");
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
            toonBewonerGegevens.setCollapsible(true);
        }
    }

    private void laadBewonerDossier(Bewoner selectedBewoner) {
        dossier = null;
        dossier = BewonerDao.getDossier(selectedBewoner.getId());

        if (dossier == null) {
            toonBewonerDossier.setCollapsible(false);
        } else {
            Allergieën.setText(String.valueOf(dossier.getAllergieën()));
            GroteOperaties.setText(String.valueOf(dossier.getGroteOperaties()));
            if (dossier.getIncontinentie()) {
                Incontinentie.setText("Ja");
            } else {
                Incontinentie.setText("Nee");
            }
            if (dossier.getReanimatieWens()) {
                ReanimatieWens.setText("Ja");
            } else {
                ReanimatieWens.setText("Nee");
            }
            if (dossier.getPrivacy()) {
                Privacy.setText("Ja");
            } else {
                Privacy.setText("Nee");
            }
            toonBewonerDossier.setCollapsible(true);
        }
    }

    private void laadVerpleegDossier(Bewoner selectedBewoner) {
        verpleegDossier = null;
        verpleegDossier = BewonerDao.getVerpleegDossier(selectedBewoner.getId());

        if (verpleegDossier == null) {
            toonVerpleegDossier.setCollapsible(false);
        } else {
            Wondzorg.setText(String.valueOf(verpleegDossier.getWondzorg()));
            Bloedafname.setText(String.valueOf(verpleegDossier.getBloedafname()));
            if (verpleegDossier.getSuikerziekte()) {
                Suikerziekte.setText("Ja");
            } else {
                Suikerziekte.setText("Nee");
            }
            VroegerBeroep.setText(String.valueOf(verpleegDossier.getBeroepVroeger()));
            Specifiekewensen.setText(String.valueOf(verpleegDossier.getSpecifiekeWensen()));
            toonVerpleegDossier.setCollapsible(true);
        }
    }

    private void laadContactpersoon(Bewoner selectedBewoner) {
        contactpersoon = null;
        contactpersoon = BewonerDao.getContactpersoon(selectedBewoner.getId());

        if (contactpersoon == null) {
            toonContactpersoon.setCollapsible(false);
        } else {
            voornaamContactpersoon.setText(String.valueOf(contactpersoon.getVoornaam()));
            achternaamContactpersoon.setText(String.valueOf(contactpersoon.getAchternaam()));
            identiteitiskaartnrContactpersoon.setText(String.valueOf(contactpersoon.getIdentiteitskaartnr()));
            relatieContactpersoon.setText(String.valueOf(contactpersoon.getRelatie()));
            telefoonContactpersoon.setText(String.valueOf(contactpersoon.getTelefoon().toString()));
            emailContactpersoon.setText(String.valueOf(contactpersoon.getEmail()));
            straatContactpersoon.setText(String.valueOf(contactpersoon.getAdress().getStraat()));
            huisnrContactpersoon.setText(String.valueOf(contactpersoon.getAdress().getHuisnr()));
            gemeenteContactpersoon.setText(String.valueOf(contactpersoon.getAdress().getGemeente()));
            postcodeContactpersoon.setText(String.valueOf(contactpersoon.getAdress().getPostcode()));
            toonContactpersoon.setCollapsible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (toonBewonerGegevens != null) {
            toonBewonerGegevens.setCollapsible(false);
            toonBewonerDossier.setCollapsible(false);
            toonVerpleegDossier.setCollapsible(false);
            toonContactpersoon.setCollapsible(false);

        }

        bewoners = BewonerDao.getAllBewonersForColum();
        voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
        achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));

        if (plaats != null) {
            //Bron: Jerome. (2014, 19 februari). javafx create ComboBox TableCell [Blogreactie]. Geraadpleegd op 11 maart 2018, van https://stackoverflow.com/questions/21860019/javafx-create-combobox-tablecell
            plaatsen = FXCollections.observableArrayList("kamer", "Kiné", "Naar huis", "Leefruimte");
            plaats.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("plaats"));
            plaats.setCellFactory(ComboBoxTableCell.forTableColumn(plaatsen));
            plaats.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Bewoner, String>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Bewoner, String> t) {
                            BewonerDao.UpdatePlaats(t.getRowValue().getId(), t.getNewValue());
                        }
                    }
            );
        }

        BewonersTable.getItems().setAll(bewoners);
    }

    private void setSplitpane(String url) {
        try {
            URL paneUrl = getClass().getResource(url);
            content = FXMLLoader.load(paneUrl);

            splitpane.getItems().remove(1);
            splitpane.getItems().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editIcon(String url) {
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSplitpane(url);
            }
        });
    }

    private void editIconTooltip(String text) {
        Tooltip editTooltip = new Tooltip();
        editTooltip.setText(text);
        Duration duration = new Duration(1);
        editTooltip.setShowDelay(duration);
        edit.setTooltip(editTooltip);
    }
}
