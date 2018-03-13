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

public class BewonerDao {

    private static PreparedStatement stmt;
    private static ResultSet rs = null;


    public static ObservableList getAllBewoners(){
        ObservableList bewoner = FXCollections.observableArrayList();
        Connect();
        try{
            stmt = con.prepareStatement("select * from bewoner where actief = true");
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
            stmt = con.prepareStatement("select * from bewoner where actief = true");
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Bewoner b = new Bewoner();
                b.setId(rs.getInt("id"));
                b.setVoornaam(rs.getString("voornaam"));
                b.setAchternaam(rs.getString("achternaam"));
                b.setPlaats(rs.getString("plaats"));
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
            stmt = con.prepareStatement("select * from bewoner WHERE voornaam LIKE ? OR achternaam LIKE ? AND actief  = true");
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

    public static boolean addDossier(BewonersDossier b) {
        Connect();
        try {
            stmt = con.prepareStatement("INSERT into bewonersdossier(bewoner_id, incontinentie, privacy, reanimatie_wens, grote_operaties, allergieën) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, Bewoner.getSelectedId());
            stmt.setBoolean(2, b.getIncontinentie());
            stmt.setBoolean(3, b.getPrivacy());
            stmt.setBoolean(4, b.getReanimatieWens());
            stmt.setString(5, b.getGroteOperaties());
            stmt.setString(6, b.getAllergieën());
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

    public static boolean addVerpleegDossier(Verpleegdossier v) {
        Connect();
        try {
            stmt = con.prepareStatement("INSERT INTO verpleegdossier (bewoner_id, wondzorg, bloedafname, suikerziekte, beroep_vroeger, specifiekewensen) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, Bewoner.getSelectedId());
            stmt.setString(2, v.getWondzorg());
            stmt.setString(3, v.getBloedafname());
            stmt.setBoolean(4, v.getSuikerziekte());
            stmt.setString(5, v.getBeroepVroeger());
            stmt.setString(6, v.getSpecifiekeWensen());
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

    public static boolean addContactpersoon(Contactpersoon c) {
        AdressDao.addAddress(c.getAdress());
        int addressID = AdressDao.getId(c.getAdress());
        c.getAdress().setId(addressID);
        Connect();
        try {
            stmt = con.prepareStatement("INSERT INTO contactpersoon(bewoner_id, adres_id, achternaam, voornaam, telefoon, email, relatie, identiteitskaartnr) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            stmt.setInt(1, Bewoner.getSelectedId());
            stmt.setInt(2, c.getAdress().getId());
            stmt.setString(3, c.getAchternaam());
            stmt.setString(4, c.getVoornaam());
            stmt.setInt(5, c.getTelefoon());
            stmt.setString(6,c.getEmail());
            stmt.setString(7, c.getRelatie());
            stmt.setString(8, c.getIdentiteitskaartnr());
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
            stmt.setInt(7, Bewoner.getSelectedId());
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

        Connect();
        try {
            stmt = con.prepareStatement("insert into bewoner(voornaam, achternaam, geboortedatum, geboorteplaats, geslacht, burgerlijke_staat, gekoppeld_met, opnamedatum, geloofsovertuiging, meter, peter, nationaliteit, rijskregisternr, identiteitskaartnr, dokter, voorkeur_ziekenhuis, kamernr, adres_id, foto, actief, plaats) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, true, 'kamer')");
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
            stmt.setLong(13,  bewoner.getRijksregisternr());
            stmt.setString(14,  bewoner.getIndetiteitskaartnr());
            stmt.setString(15,  bewoner.getHuisdokter());
            stmt.setString(16,  bewoner.getVoorkeurZiekenhuis());
            stmt.setInt(17,  bewoner.getKamernr());
            stmt.setInt(18, addressID);
            stmt.setBytes(19, bewoner.getFoto());
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

    public static Bewoner getBewoner(int id) {
        Connect();
        try {
            stmt = con.prepareStatement("select * from bewoner where id = ? and actief = true");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Adress adres = AdressDao.getAdress(rs.getInt("adres_id"));
                Bewoner bewoner = new Bewoner(rs.getInt("id"),rs.getString("voornaam"), rs.getString("achternaam"), rs.getDate("geboortedatum"), rs.getString("geboorteplaats"), rs.getString("geslacht"), rs.getString("burgerlijke_staat"),
                        rs.getString("gekoppeld_met"), rs.getDate("opnamedatum"), rs.getString("geloofsovertuiging"), rs.getString("meter"), rs.getString("peter"), rs.getString("nationaliteit"), rs.getLong("rijskregisternr"),
                        rs.getString("identiteitskaartnr"), rs.getString("dokter"), rs.getString("voorkeur_ziekenhuis"), rs.getInt("kamernr"), adres, rs.getBytes("foto"), rs.getString("plaats"));
                return bewoner;
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
            stmt = con.prepareStatement("update bewoner set actief = false where id = ?");
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

    public static boolean UpdatePlaats(int id, String plaats) {
        Connect();
        try {
            stmt = con.prepareStatement("update bewoner set plaats = ? where id = ?");
            stmt.setString(1, plaats);
            stmt.setInt(2, id);
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
}
