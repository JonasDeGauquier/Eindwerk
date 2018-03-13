package controller;

import DAO.LoginDao;
import DAO.RolDao;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Login;

import java.io.IOException;
import java.net.URL;

public class HomeController {
    private Parent parent;
    private Scene scene;

    private static BorderPane root = new BorderPane();

    public static BorderPane getRoot() {
        return root;
    }

    public HomeController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/Home.fxml"));
        try {
            parent = fxmlLoader.load();
            scene = new Scene(parent, 600, 400);
            scene.getStylesheets().add("css/Layout.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectHomeRFID(Stage stage, String rfid) throws IOException {
        stage.setTitle("Home");
        URL menuBarUrl = null;

        int loginId = LoginDao.getLoginId(Integer.parseInt(rfid));
        int rolId = RolDao.getRol(loginId);
        if (rolId == 1){
            menuBarUrl = getClass().getResource("../gui/AdminMenu.fxml");
        }else if (rolId == 3){
            menuBarUrl = getClass().getResource("../gui/HoofdverpleegsterMenu.fxml");
        }else if (rolId == 4){
            menuBarUrl = getClass().getResource("../gui/VerpleegsterMenu.fxml");
        }else if (rolId == 5){
            menuBarUrl = getClass().getResource("../gui/ZorgkundigeMenu.fxml");
        }
        MenuBar bar = FXMLLoader.load( menuBarUrl );

        URL paneOneUrl = getClass().getResource("../gui/Home.fxml");
        VBox paneOne = FXMLLoader.load( paneOneUrl );

        if (root == null){
            root.setTop(bar);
            root.setCenter(paneOne);
            Scene scene = new Scene(root,640,480);
            scene.getStylesheets().add("css/Layout.css");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.hide();
            stage.show();
            System.out.println("dfdf");
        }
        else {
            root = new BorderPane();
            root.setTop(bar);
            root.setCenter(paneOne);
            Scene scene = new Scene(root,640,480);
            scene.getStylesheets().add("css/Layout.css");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.hide();
            stage.show();
        }
    }


    public void redirectHome(Stage stage) throws IOException {
        stage.setTitle("Home");
        URL menuBarUrl = null;

        Login login = new Login();
        int loginId = LoginDao.getId(Login.getUsername(), Login.getPassword());
        int rolId = RolDao.getRol(loginId);
        if (rolId == 1){
             menuBarUrl = getClass().getResource("../gui/AdminMenu.fxml");
        }else if (rolId == 3){
            menuBarUrl = getClass().getResource("../gui/HoofdverpleegsterMenu.fxml");
        }else if (rolId == 4){
            menuBarUrl = getClass().getResource("../gui/VerpleegsterMenu.fxml");
        }else if (rolId == 5){
            menuBarUrl = getClass().getResource("../gui/ZorgkundigeMenu.fxml");
        }
        MenuBar bar = FXMLLoader.load( menuBarUrl );

        URL paneOneUrl = getClass().getResource("../gui/Home.fxml");
        VBox paneOne = FXMLLoader.load( paneOneUrl );



        if (root == null){
            root.setTop(bar);
            root.setCenter(paneOne);
            Scene scene = new Scene(root,640,480);
            scene.getStylesheets().add("css/Layout.css");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.hide();
            stage.show();
        }
        else {
            root = new BorderPane();
            root.setTop(bar);
            root.setCenter(paneOne);
            Scene scene = new Scene(root,640,480);
            scene.getStylesheets().add("css/Layout.css");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.hide();
            stage.show();
        }
    }
}
