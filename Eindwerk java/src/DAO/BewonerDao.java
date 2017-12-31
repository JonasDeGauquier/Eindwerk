package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static DAO.PostgreSQLJDBC.Connect;
import static DAO.PostgreSQLJDBC.con;

public class BewonerDao {

    private static PreparedStatement stmt;
    private static ResultSet rs = null;


    public static ObservableList getAllBewoners(){
        ObservableList bewoner = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from bewoner");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Bewoner b = new Bewoner();
                b.setId(rs.getInt("id"));
                b.setVoornaam(rs.getString("voornaam"));
                b.setAchternaam(rs.getString("achternaam"));
                bewoner.add(b);
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
        return bewoner;
    }

    public static ObservableList getAllBewonersForColum(){
        ObservableList bewoner = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from bewoner");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Bewoner b = new Bewoner();
                b.setId(rs.getInt("id"));
                b.setVoornaam(rs.getString("voornaam"));
                b.setAchternaam(rs.getString("achternaam"));
                bewoner.add(b);
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
        return bewoner;
    }

    public static ObservableList getAllBewonersFromSearch(String search){
        ObservableList bewoner = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from bewoner WHERE voornaam LIKE ? OR achternaam LIKE ?");
            stmt.setString(1, "%"+ search + "%");
            stmt.setString(2, "%"+ search + "%");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Bewoner b = new Bewoner();
                b.setId(rs.getInt("id"));
                b.setVoornaam(rs.getString("voornaam"));
                b.setAchternaam(rs.getString("achternaam"));
                bewoner.add(b);
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
        return bewoner;
    }

    public static BewonersDossier getDossier(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("select * from bewonersdossier where bewoner_id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                BewonersDossier dossier = new BewonersDossier(rs.getBoolean("incontinentie"), rs.getBoolean("privacy"), rs.getBoolean("reanimatie_wens"), rs.getString("grote_operaties"), rs.getString("allergieën"));
                return dossier;
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

    public static boolean editDossier(BewonersDossier b) {
        Connect();
        try {
            stmt = con.prepareStatement("UPDATE bewonersdossier SET incontinentie=?, privacy=?, reanimatie_wens=?, grote_operaties=?, allergieën=? where id=?");
            stmt.setBoolean(1, b.getIncontinentie());
            stmt.setBoolean(2, b.getPrivacy());
            stmt.setBoolean(3, b.getReanimatieWens());
            stmt.setString(4, b.getGroteOperaties());
            stmt.setString(5, b.getAllergieën());
            stmt.setInt(6, b.getId());
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

    public static Verpleegdossier getVerpleegDossier(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("select * from verpleegdossier where bewoner_id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Verpleegdossier verpleegdossier = new Verpleegdossier(rs.getString("wondzorg"), rs.getString("bloedafname"), rs.getBoolean("suikerziekte"), rs.getString("beroep_vroeger"), rs.getString("specifiekewensen"));
                return verpleegdossier;
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

    public static boolean editVerpleegDossier(Verpleegdossier v) {
        Connect();
        try {
            stmt = con.prepareStatement("UPDATE verpleegdossier SET wondzorg=?, bloedafname=?, suikerziekte=?, beroep_vroeger=?, specifiekewensen=? where id=?");
            stmt.setString(1, v.getWondzorg());
            stmt.setString(2, v.getBloedafname());
            stmt.setBoolean(3, v.getSuikerziekte());
            stmt.setString(4, v.getBeroepVroeger());
            stmt.setString(5, v.getSpecifiekeWensen());
            stmt.setInt(6, v.getId());
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

    public static Contactpersoon getContactpersoon(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("select * from contactpersoon where bewoner_id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Adress adres = AdressDao.getAdress(rs.getInt("adres_id"));
                Contactpersoon contactpersoon = new Contactpersoon(adres, rs.getString("achternaam"), rs.getString("voornaam"), rs.getInt("telefoon"), rs.getString("email"), rs.getString("relatie"), rs.getString("identiteitskaartnr"));
                return contactpersoon;
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

    public static boolean editContactpersoon(Contactpersoon c) {
        AdressDao.editAdres(c.getAdress());
        Connect();
        try {
            stmt = con.prepareStatement("UPDATE contactpersoon SET achternaam=?, voornaam=?, identiteitskaartnr=?, telefoon=?, email=?, relatie=? where bewoner_id=?");
            stmt.setString(1, c.getAchternaam());
            stmt.setString(2, c.getVoornaam());
            stmt.setString(3, c.getIdentiteitskaartnr());
            stmt.setInt(4, c.getTelefoon());
            stmt.setString(5,c.getEmail());
            stmt.setString(6, c.getRelatie());
            stmt.setInt(7, c.getBewoner().getSelectedId());
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

    public static boolean addBewoner(Bewoner bewoner) {
        AdressDao.addAddress(bewoner.getAdress());
        int addressID = AdressDao.getId(bewoner.getAdress());
        bewoner.getAdress().setId(addressID);

        File img = new File(String.valueOf(bewoner.getFoto()));
        System.out.println(img);
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Connect();
        try {
            stmt = con.prepareStatement("insert into bewoner(voornaam, achternaam, geboortedatum, geboorteplaats, geslacht, burgerlijke_staat, gekoppeld_met, opnamedatum, geloofsovertuiging, meter, peter, nationaliteit, rijskregisternr, identiteitskaartnr, dokter, voorkeur_ziekenhuis, kamernr, adres_id, foto) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, bewoner.getVoornaam());
            stmt.setString(2, bewoner.getAchternaam());
            stmt.setDate(3, (Date) bewoner.getGeboortedatum());
            stmt.setString(4, bewoner.getGeboorteplaats());
            stmt.setString(5,  bewoner.getGeslacht());
            stmt.setString(6,  bewoner.getBurgerlijkestaat());
            stmt.setString(7,  bewoner.getGekoppeldMet());
            stmt.setDate(8,  (Date) bewoner.getOpnamedatum());
            stmt.setString(9,  bewoner.getGeloofsovertuiging());
            stmt.setString(10,  bewoner.getMeter());
            stmt.setString(11,  bewoner.getPeter());
            stmt.setString(12,  bewoner.getNationaliteit());
            stmt.setInt(13,  bewoner.getRijksregisternr());
            stmt.setInt(14,  bewoner.getIndetiteitskaartnr());
            stmt.setString(15,  bewoner.getHuisdokter());
            stmt.setString(16,  bewoner.getVoorkeurZiekenhuis());
            stmt.setInt(17,  bewoner.getKamernr());
            stmt.setInt(18, addressID);
            stmt.setBinaryStream(19, fin, (int) img.length());
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
