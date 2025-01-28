package loginServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class AuthenticateUser {

    public boolean authenticate(String username, String password) {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:test.db");

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            String tableName = "user_db";
            String query = "SELECT * FROM " + tableName + " WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(query);

            return rs.next(); // If there's a result, authentication is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
