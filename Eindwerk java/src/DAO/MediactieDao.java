package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Bewoner;
import model.Medicatie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.PostgreSQLJDBC.Connect;
import static DAO.PostgreSQLJDBC.con;

public class MediactieDao {
    private static PreparedStatement stmt;
    private static ResultSet rs = null;


    public static ObservableList getAllMedicatie(){
        ObservableList mediactie = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from mediactie");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Medicatie m = new Medicatie();
                m.setId(rs.getInt("id"));
                m.setMedicatie(rs.getString("medicatie"));
                mediactie.add(m);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return mediactie;
    }
}
