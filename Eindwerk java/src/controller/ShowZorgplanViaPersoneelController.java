package controller;

import DAO.BewonerDao;
import DAO.MedicatieDao;
import DAO.ZorgplanDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import model.User;
import model.Zorgplan;
import model.Zorgtaak;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowZorgplanViaPersoneelController implements Initializable {
    @FXML
    Accordion accordion;

    private Zorgplan zorgplan;
    private User user;
    private Zorgtaak zorgtaak;
    private ArrayList<Zorgplan> zorgplannen = new ArrayList<Zorgplan>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        zorgplannen = ZorgplanDao.getAllZorgplannenByPersoneel(User.getSelectedId());

        for (int i = 0; i < zorgplannen.size(); i++) {
            TitledPane gridTitlePane = new TitledPane();
            GridPane grid = new GridPane();
            grid.setVgap(4);
            grid.setPadding(new Insets(5, 5, 5, 5));
            grid.add(new Label("Gegeven medicatie: "), 0, 0);
            grid.add(new Label(MedicatieDao.getMedicatie(zorgplannen.get(i).getMedicatie().getId()).toString()), 1, 0);
            grid.add(new Label("Zorgtaak: "), 0, 1);
            zorgtaak = ZorgplanDao.getZorgtaak(zorgplannen.get(i).getZorgtaak().getId());
            grid.add(new Label(zorgtaak.getZorgtaak()), 1, 1);
            grid.add(new Label("Opmerking: "), 0, 2);
            if (zorgplannen.get(i).getOpmerking().isEmpty()){
                grid.add(new Label("Geen opmerking"), 1, 2);
            } else {
                grid.add(new Label(zorgplannen.get(i).getOpmerking()), 1, 2);
            }
            grid.add(new Label("Bewoner: "), 0, 3);
            grid.add(new Label(BewonerDao.getBewoner(zorgplannen.get(i).getBewoner().getId()).toString()), 1, 3);
            gridTitlePane.setText(String.valueOf(new SimpleDateFormat("dd MMMM yyyy").format(zorgplannen.get(i).getTimestamp())));
            gridTitlePane.setContent(grid);

            accordion.getPanes().addAll(gridTitlePane);
        }
    }
}
