package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Bewoner;
import model.BewonersDossier;
import model.Medicatie;
import model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.PostgreSQLJDBC.Connect;
import static DAO.PostgreSQLJDBC.con;

public class MedicatieDao {
    private static PreparedStatement stmt;
    private static ResultSet rs = null;


    public static ObservableList getAllMedicatie(){
        ObservableList mediactie = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from medicatie where actief = true");
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

    public static ObservableList getAllMedicatieForTable(){
        ObservableList mediactie = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from medicatie where id > 1 AND actief = true");
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

    public static ObservableList getAllMedicatieFromSearch(String search){
        ObservableList medicatie = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from medicatie WHERE medicatie LIKE ? AND id > 1 AND actief = true");
            stmt.setString(1, "%"+ search + "%");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Medicatie m = new Medicatie();
                m.setId(rs.getInt("id"));
                m.setMedicatie(rs.getString("medicatie"));
                medicatie.add(m);
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
        return medicatie;
    }

    public static Medicatie getMedicatie(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("select * from medicatie where id = ? AND actief = true");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Medicatie medicatie = new Medicatie(rs.getInt("id"), rs.getString("medicatie"));
                return medicatie;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
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
        return null;
    }

    public static boolean Delete(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("update medicatie set actief = false where id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
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

    public static boolean addMedicatie(Medicatie medicatie) {
        Connect();
        try {
            stmt = con.prepareStatement("insert into medicatie(medicatie, actief) values (?,?)");
            stmt.setString(1, medicatie.getMedicatie());
            stmt.setBoolean(2, true);
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
