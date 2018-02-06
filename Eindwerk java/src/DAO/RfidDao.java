package DAO;

import model.Adress;
import model.Rfid;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.PostgreSQLJDBC.Connect;
import static DAO.PostgreSQLJDBC.con;

public class RfidDao {

    private static PreparedStatement stmt;
    private static ResultSet rs = null;

    public static boolean addRfid(Rfid rfid) {
        Connect();
        try {
            stmt = con.prepareStatement("insert into rfid(login_id, rfid) values (?,?)");
            stmt.setInt(1, rfid.getLogin().getLoginId());
            stmt.setInt(2, rfid.getRfid());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            ex.printStackTrace();
            return false;
        } finally {
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
    }
}
