package controller;

import DAO.*;
import com.jfoenix.controls.JFXToolbar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Bewoner;
import model.User;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PersoneelController implements Initializable{
    @FXML
    TableView<User> PersoneelTable = new TableView<User>();
    @FXML
    TableColumn Voornaam, achternaam;
    @FXML
    private TextField search = new TextField();
    @FXML
    private SplitPane splitpane;
    @FXML
    private JFXToolbar toolbar;

    private AnchorPane content;

    private ObservableList personeel = FXCollections.observableArrayList();
    private ObservableList searchList = FXCollections.observableArrayList();


    private Button save = new Button();
    private Button add = new Button();
    private Button edit = new Button();
    private Button delete = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personeel = PersoneelDao.getAllPersoneelForColum();
        Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
        achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
        PersoneelTable.getItems().setAll(personeel);

        save.setGraphic(new ImageView("/images/save-icon.png"));
        add.setGraphic(new ImageView("/images/add-icon.png"));
        edit.setGraphic(new ImageView("/images/edit-icon.png"));
        delete.setGraphic(new ImageView("/images/delete-icon.png"));

        if (toolbar != null){
            toolbar.getLeftItems().addAll(add, edit, delete);
        }

        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EditPersoneel(event);
            }
        });

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    URL paneUrl = getClass().getResource("../gui/PersoneelToevoegen.fxml");
                    AnchorPane pane = FXMLLoader.load(paneUrl);

                    BorderPane border = HomeController.getRoot();
                    border.setCenter(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User selectedItem = PersoneelTable.getSelectionModel().getSelectedItem();
                if (selectedItem == null || selectedItem.equals("")) {
                    Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
                    notSelected.setTitle("Geen persoon gekozen");
                    notSelected.setHeaderText(null);
                    notSelected.setContentText("Gelieve een persoon te selecteren!");
                    notSelected.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Persoon verwijderen");
                    alert.setHeaderText(null);
                    alert.setContentText("Bent u zeker dat u deze persoon wilt verwijderen?");

                    ButtonType buttonTypeYes = new ButtonType("Ja");
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeYes) {
                        Boolean del = PersoneelDao.Delete(selectedItem.getUserId());
                        if (del == true) {
                            // Bron: https://github.com/PlusHaze/TrayNotification
                            String title = "Personeel";
                            String message = "De persoon is succesvol verwjiderd!";
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
                        } else {
                            Alert alertAgain = new Alert(Alert.AlertType.ERROR);
                            alertAgain.setTitle("Personeel");
                            alertAgain.setHeaderText(null);
                            alertAgain.setContentText("De persoon is niet verwijderd! Probeer opnieuw");
                            alertAgain.showAndWait();
                        }
                    }
                }
            }
        });

        Tooltip addTooltip = new Tooltip();
        addTooltip.setText("Personeel toevoegen");
        Duration duration = new Duration(1);
        addTooltip.setShowDelay(duration);
        add.setTooltip(addTooltip);

        Tooltip deleteTooltip = new Tooltip();
        deleteTooltip.setText("Persoon verwijderen");
        deleteTooltip.setShowDelay(duration);
        delete.setTooltip(deleteTooltip);

        Tooltip editTooltip = new Tooltip();
        editTooltip.setText("Personeel aanpassen");
        editTooltip.setShowDelay(duration);
        edit.setTooltip(editTooltip);
    }

    @FXML
    private void handleKeyPressed(KeyEvent key){
        if(search.textProperty().get().isEmpty()) {
            PersoneelTable.getItems().setAll(personeel);
        }else {
            searchList =  PersoneelDao.getAllPersoneelFromSearch(search.getText().toString());
            Voornaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("voornaam"));
            achternaam.setCellValueFactory(new PropertyValueFactory<Bewoner, String>("achternaam"));
            PersoneelTable.getItems().setAll(searchList);
        }
    }

    @FXML
    void ShowPersoneelGegevens(ActionEvent event) {
        User selectedItem = PersoneelTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null || selectedItem.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen persoon gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een persoon te selecteren!");
            notSelected.show();
        } else {
            User user = new User();
            User.setSelectedId(selectedItem.getUserId());
            setSplitpane("../gui/PersoneelgegevensBekijken.fxml");
        }
    }

    @FXML
    void EditPersoneel(ActionEvent event) {
        User selectedItem = PersoneelTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null || selectedItem.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen persoon gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een persoon te selecteren!");
            notSelected.show();
        } else {
            User user = new User();
            User.setSelectedId(selectedItem.getUserId());
            setSplitpane("../gui/PersoneelBewerken.fxml");
        }
    }

    @FXML
    void deleteBadge(ActionEvent event){
        User selectedItem = PersoneelTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null || selectedItem.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen persoon gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een persoon te selecteren!");
            notSelected.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Badge blokkeren");
            alert.setHeaderText(null);
            alert.setContentText("Bent u zeker dat u deze badge wilt blokkeren?");

            ButtonType buttonTypeYes = new ButtonType("Ja");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                int loginId = LoginDao.getLoginIdByUserId(selectedItem.getUserId());
                Boolean del = RfidDao.Delete(loginId);
                if (del == true) {
                    // Bron: https://github.com/PlusHaze/TrayNotification
                    String title = "Badge";
                    String message = "De badge is succesvol geblokeerd!";
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
                } else {
                    Alert alertAgain = new Alert(Alert.AlertType.ERROR);
                    alertAgain.setTitle("Badge");
                    alertAgain.setHeaderText(null);
                    alertAgain.setContentText("De badge is niet geblokkeerd! Probeer opnieuw");
                    alertAgain.showAndWait();
                }
            }
        }
    }

    @FXML
    void ShowZorgplan(ActionEvent event) {
        User selectedItem = PersoneelTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null || selectedItem.equals("")) {
            Alert notSelected = new Alert(Alert.AlertType.INFORMATION);
            notSelected.setTitle("Geen persoon gekozen");
            notSelected.setHeaderText(null);
            notSelected.setContentText("Gelieve een persoon te selecteren!");
            notSelected.show();
        } else {
            User user = new User();
            User.setSelectedId(selectedItem.getUserId());
            setSplitpane("../gui/ZorgplanBekijkenViaPersoneel.fxml");
        }
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
}
