package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Zorgplan;
import model.Zorgtaak;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.PostgreSQLJDBC.Connect;
import static DAO.PostgreSQLJDBC.con;

public class ZorgplanDao {
    private static PreparedStatement stmt;
    private static ResultSet rs = null;

    public static boolean addZorgtaak(Zorgtaak taak) {
        Connect();
        try {
            stmt = con.prepareStatement("insert into zorgtaak(taak) values (?)");
            stmt.setString(1, taak.getZorgtaak());
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

    public static boolean addZorgplan(Zorgplan zorgplan) {
        Connect();
        try {
            stmt = con.prepareStatement("insert into zorgplan(medicatie_id, zorgtaak_id, user_id, opmerking, bewoner_id) values (?,?,?,?,?)");
            stmt.setInt(1, zorgplan.getMedicatie().getId());
            stmt.setInt(2, zorgplan.getZorgtaak().getId());
            stmt.setInt(3, zorgplan.getUser().getCurrentUser());
            stmt.setString(4, zorgplan.getOpmerking());
            stmt.setInt(5, zorgplan.getBewoner().getId());
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

    public static ObservableList getAllZorgtaak(){
        ObservableList zorgtaak = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from zorgtaak");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Zorgtaak z = new Zorgtaak();
                z.setId(rs.getInt("id"));
                z.setZorgtaak(rs.getString("taak"));
                zorgtaak.add(z);
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
        return zorgtaak;
    }
}
