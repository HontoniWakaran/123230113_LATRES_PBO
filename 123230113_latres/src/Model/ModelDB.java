
package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModelDB {
 private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/latres_pbo";
                String user = "root";
                String pass = "";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Connected to DB");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
