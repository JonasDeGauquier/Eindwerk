package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Adress;
import model.Bewoner;
import model.Rol;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.PostgreSQLJDBC.Connect;
import static DAO.PostgreSQLJDBC.con;

public class RolDao {
    private static PreparedStatement stmt;
    private static ResultSet rs = null;

    public static ObservableList getAllRol(){
        ObservableList<Rol> rol = FXCollections.observableArrayList();
        Rol r = new Rol();
        Connect();
        try{
            stmt = con.prepareStatement("select * from rol");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                rol.addAll(new Rol(rs.getInt("id"), rs.getString("rol")));
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
        return rol;
    }

    public static int getRol(int userId) {
        Connect();
        try{
            stmt = con.prepareStatement("select rol_id from personeel where id = ?");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("rol_id");
            }
        }catch(Exception e) {
            //Handle errors for Class.forName
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
        return userId;
    }

    public static Rol getRolById(int id) {
        Connect();
        try{
            stmt = con.prepareStatement("select * from rol where id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Rol r = new Rol(rs.getInt("id"), rs.getString("rol"));
                return r;
            }
        }catch(Exception e) {
            //Handle errors for Class.forName
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
        return null;
    }
}
