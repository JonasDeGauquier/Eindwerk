package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.PostgreSQLJDBC.Connect;
import static DAO.PostgreSQLJDBC.con;

public class PersoneelDao {

    private static PreparedStatement stmt;
    private static ResultSet rs = null;

    public static ObservableList getAllPersoneelForColum(){
        ObservableList personeel = FXCollections.observableArrayList();

        Connect();
        try{
            stmt = con.prepareStatement("select * from personeel where actief = true");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                User u = new User();
                u.setUserId(rs.getInt("id"));
                u.setVoornaam(rs.getString("voornaam"));
                u.setAchternaam(rs.getString("achternaam"));
                personeel.add(u);
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
        return personeel;
    }

    public static ObservableList getAllPersoneelFromSearch(String search){
        ObservableList personeel = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from personeel WHERE voornaam LIKE ? OR achternaam LIKE ? AND actief = true");
            stmt.setString(1, "%"+ search + "%");
            stmt.setString(2, "%"+ search + "%");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                User u = new User();
                u.setUserId(rs.getInt("id"));
                u.setVoornaam(rs.getString("voornaam"));
                u.setAchternaam(rs.getString("achternaam"));
                personeel.add(u);
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
        return personeel;
    }

    public static boolean Delete(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("update personeel set actief = false where id = ?");
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

    public static boolean addPersoneel(User user , Integer rolId) {
        AdressDao.addAddress(user.getAdress());
        int addressID = AdressDao.getId(user.getAdress());
        user.getAdress().setId(addressID);

        Connect();
        try {
            stmt = con.prepareStatement("insert into personeel(voornaam, achternaam, geboortedatum, email, adres_id,rol_id, actief) values (?,?,?,?,?,?,?)");
            stmt.setString(1, user.getVoornaam());
            stmt.setString(2, user.getAchternaam());
            stmt.setDate(3, (Date) user.getGeboortedatum());
            stmt.setString(4, user.getEmail());
            stmt.setInt(5,  user.getAdress().getId());
            stmt.setInt(6, rolId);
            stmt.setBoolean(7, true);
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

    public static boolean editProfiel(User user) {
        AdressDao.editAdres(user.getAdress());
        int addressID = AdressDao.getId(user.getAdress());
        user.getAdress().setId(addressID);

        Connect();
        try {
            stmt = con.prepareStatement("update personeel set voornaam =?, achternaam =?, geboortedatum =?, email =?, adres_id =?, actief =?  where id =?");
            stmt.setString(1, user.getVoornaam());
            stmt.setString(2, user.getAchternaam());
            stmt.setDate(3, (Date) user.getGeboortedatum());
            stmt.setString(4, user.getEmail());
            stmt.setInt(5,  user.getAdress().getId());
            stmt.setBoolean(6, true);
            stmt.setInt(7,user.getCurrentUser());
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

    public static User getPersoneel(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("select * from personeel where id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Adress adres = AdressDao.getAdress(rs.getInt("adres_id"));
                Rol rol = RolDao.getRolById(rs.getInt("rol_id"));
                User user = new User(rs.getInt("id"),rs.getString("voornaam"), rs.getString("achternaam"), rs.getDate("geboortedatum"),rs.getString("email"), adres, rol);
                return user;
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
}
