package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class PostgreSQLJDBC {
    public static Connection con;
    public static void Connect() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager
                    .getConnection("jdbc:postgresql://10.3.50.26:5432/Eindwerk",
                            "postgres", "gquauier");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
