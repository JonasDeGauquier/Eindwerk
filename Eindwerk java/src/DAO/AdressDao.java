package DAO;

import model.Adress;
import model.Contactpersoon;
import model.Verpleegdossier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.PostgreSQLJDBC.Connect;
import static DAO.PostgreSQLJDBC.con;

public class AdressDao {

    private static PreparedStatement stmt;
    private static ResultSet rs = null;

    public static boolean addAddress(Adress a) {
        Connect();
        try {
            stmt = con.prepareStatement("insert into adres(straat,huisnr,gemeente,postcode) values (?,?,?,?)");
            stmt.setString(1, a.getStraat());
            stmt.setInt(2, a.getHuisnr());
            stmt.setString(3, a.getGemeente());
            stmt.setInt(4, a.getPostcode());
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

    public static int getId(Adress a) {
        Connect();
        try {
            stmt = con.prepareStatement(
                    "select id from Adres where straat = ? and huisnr = ? and gemeente = ? and postcode = ?");
            stmt.setString(1, a.getStraat());
            stmt.setInt(2, a.getHuisnr());
            stmt.setString(3, a.getGemeente());
            stmt.setInt(4, a.getPostcode());
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
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
        return -1;
    }

    public static Adress getAdress(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("select * from adres where id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Adress adres = new Adress(rs.getInt("id"), rs.getString("straat"), rs.getInt("huisnr"), rs.getString("gemeente"), rs.getInt("postcode"));
                return adres;
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

    public static boolean editAdres(Adress a) {
        Connect();
        try {
            stmt = con.prepareStatement("UPDATE adres SET straat=?, huisnr=?, gemeente=?, postcode=? where id=?");
            stmt.setString(1, a.getStraat());
            stmt.setInt(2, a.getHuisnr());
            stmt.setString(3, a.getGemeente());
            stmt.setInt(4, a.getPostcode());
            stmt.setInt(5, a.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
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
