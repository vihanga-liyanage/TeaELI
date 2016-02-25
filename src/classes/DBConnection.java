
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnection {

    public Connection connection;
    public PreparedStatement pstStatement;
    public ResultSet resultSet;

    public DBConnection() {
    }

    public Connection setConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/leafspice", "teaeli", "teaeli");

        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't find database driver : " + e.getMessage());
        }

        return connection;
    }

    public ResultSet getResult(String query, Connection connection) {
        this.connection = connection;
        try {
            pstStatement = connection.prepareStatement(query);
            resultSet = pstStatement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("SQL Problem : " + e.getMessage());
            System.err.println("SQL State : " + e.getMessage());
            System.err.println("Vendor error : " + e.getMessage());
        }
        return resultSet;
    }
    
    public int updateResult(String query, Connection connection) {
        this.connection = connection;
        try {
            pstStatement = connection.prepareStatement(query);
            pstStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.err.println("SQL Problem : " + e.getMessage());
            System.err.println("SQL State : " + e.getMessage());
            System.err.println("Vendor error : " + e.getMessage());
            return 0;
        }
    }
}