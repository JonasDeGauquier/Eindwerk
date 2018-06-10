package DAO;

import model.Adress;
import model.Login;
import model.Rfid;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao extends PostgreSQLJDBC {
    private static PreparedStatement stmt;
    private static  ResultSet rs = null;

    public static boolean checkLogin(String username, String password) {
        Connect();
        try{
            stmt = con.prepareStatement("select * from login where username = ? and password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Login login = new Login(rs.getInt("id"),rs.getString("username"), rs.getString("password"));
                return login != null;
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
        return false;
    }

    public static boolean addLogin(Login login) {
        Connect();
        try {
            stmt = con.prepareStatement("insert into login(username, password, user_id) values (?,?,?)");
            stmt.setString(1, login.getUsername());
            stmt.setString(2, login.getPassword());
            stmt.setInt(3, login.getUser().getUserId());
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

    public static int getId(String username, String password) {
        Connect();
        try {
            stmt = con.prepareStatement("select id from login where username = ? and password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
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

    public static int getUserId(String username, String Password) {
        Connect();
        try {
            stmt = con.prepareStatement("select user_id from login where username = ? AND password = ?");
            stmt.setString(1, Login.getUsername());
            stmt.setString(2, Login.getPassword());
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
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

    public static int getUserIdByLoginId(Integer loginId) {
        Connect();
        try {
            stmt = con.prepareStatement("select user_id from login where id = ?");
            stmt.setInt(1, loginId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
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

    public static int getLoginId(Integer rfid) {
        Connect();
        try {
            stmt = con.prepareStatement("select login_id from rfid where rfid = ?");
            stmt.setInt(1, rfid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("login_id");
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

    public static int getLoginIdByUserId(Integer userId) {
        Connect();
        try {
            stmt = con.prepareStatement("select id from login where user_id = ?");
            stmt.setInt(1, userId);
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

    public static Boolean checkLoginrfid(Integer rfid) {
        Connect();
        try {
            stmt = con.prepareStatement("select * from rfid where rfid = ? and actief = true");
            stmt.setInt(1, rfid);
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                Login login = new Login(rs.getInt("id"));
                Rfid rfidModel = new Rfid(rs.getInt("id"),login, rs.getInt("rfid"));
                return rfidModel != null;
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
        return false;
    }

    public static Login getLogin(int Id){
        Connect();
        try {
            stmt = con.prepareStatement("select * from login where id = ?");
            stmt.setInt(1, Id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Login login = new Login(rs.getString("username"),rs.getString("password"));
                login.setLoginId(rs.getInt("Id"));
                return login;
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
