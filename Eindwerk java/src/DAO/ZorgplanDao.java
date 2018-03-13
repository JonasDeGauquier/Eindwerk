package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static DAO.PostgreSQLJDBC.Connect;
import static DAO.PostgreSQLJDBC.con;

public class ZorgplanDao {
    private static PreparedStatement stmt;
    private static ResultSet rs = null;

    public static boolean addZorgtaak(Zorgtaak taak) {
        Connect();
        try {
            stmt = con.prepareStatement("insert into zorgtaak(taak,actief) values (?,?)");
            stmt.setString(1, taak.getZorgtaak());
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

    public static boolean addZorgplan(Zorgplan zorgplan) {
        Connect();
        try {
            stmt = con.prepareStatement("insert into zorgplan(medicatie_id, zorgtaak_id, user_id, opmerking, bewoner_id) values (?,?,?,?,?)");
            stmt.setInt(1, zorgplan.getMedicatie().getId());
            stmt.setInt(2, zorgplan.getZorgtaak().getId());
            stmt.setInt(3, User.getCurrentUser());
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

    public static Zorgtaak getZorgtaak(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("select * from zorgtaak where id = ? AND actief = true");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Zorgtaak zorgtaak = new Zorgtaak(rs.getInt("id"), rs.getString("taak"));
                return zorgtaak;
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

    public static ObservableList getAllZorgtaak(){
        ObservableList zorgtaak = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from zorgtaak where actief = true");
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

    public static ObservableList getAllZorgtaakForTable(){
        ObservableList zorgtaak = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from zorgtaak where id > 1 AND actief = true");
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

    public static ObservableList getAllZorgtaakFromSearch(String search){
        ObservableList zorgtaak = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from zorgtaak WHERE taak LIKE ? AND id > 1 AND actief = true");
            stmt.setString(1, "%"+ search + "%");
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

    public static boolean DeleteZorgtaak(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("update zorgtaak set actief = false where id = ?");
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

    public static ArrayList<Zorgplan> getAllZorgplannenByPersoneel(int id){
        ArrayList<Zorgplan> zorgplan = new ArrayList<>();
        Connect();
        try{
            stmt = con.prepareStatement("select * from zorgplan WHERE user_id= ? ");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Zorgplan z = new Zorgplan();
                Medicatie m = new Medicatie();
                Zorgtaak zorgtaak = new Zorgtaak();
                User user = new User();
                Bewoner bewoner = new Bewoner();
                z.setId(rs.getInt("id"));
                m.setId(rs.getInt("medicatie_id"));
                z.setMedicatie(m);
                zorgtaak.setId(rs.getInt("zorgtaak_id"));
                z.setZorgtaak(zorgtaak);
                user.setUserId(rs.getInt("user_id"));
                z.setUser(user);
                z.setOpmerking(rs.getString("opmerking"));
                z.setTimestamp(rs.getTimestamp("timestamp"));
                bewoner.setId(rs.getInt("bewoner_id"));
                z.setBewoner(bewoner);
                zorgplan.add(z);
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
        return zorgplan;
    }

    public static ArrayList<Zorgplan> getAllZorgplannenByBewoner(int id){
        ArrayList<Zorgplan> zorgplan = new ArrayList<>();
        Connect();
        try{
            stmt = con.prepareStatement("select * from zorgplan WHERE bewoner_id = ? ");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Zorgplan z = new Zorgplan();
                Medicatie m = new Medicatie();
                Zorgtaak zorgtaak = new Zorgtaak();
                User user = new User();
                Bewoner bewoner = new Bewoner();
                z.setId(rs.getInt("id"));
                m.setId(rs.getInt("medicatie_id"));
                z.setMedicatie(m);
                zorgtaak.setId(rs.getInt("zorgtaak_id"));
                z.setZorgtaak(zorgtaak);
                user.setUserId(rs.getInt("user_id"));
                z.setUser(user);
                z.setOpmerking(rs.getString("opmerking"));
                z.setTimestamp(rs.getTimestamp("timestamp"));
                bewoner.setId(rs.getInt("bewoner_id"));
                z.setBewoner(bewoner);
                zorgplan.add(z);
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
        return zorgplan;
    }
}
