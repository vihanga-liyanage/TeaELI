package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {

    public Connection connection;
    public PreparedStatement pstStatement;
    public ResultSet resultSet;

    public DBConnection() {
    }

    public Connection setConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/leafspice", "teaeli", "teaeli");
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't find database driver : " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public ResultArray getResultArray(String query) {
        setConnection();
        ResultArray res = null;
        try {
            pstStatement = connection.prepareStatement(query);
            resultSet = pstStatement.executeQuery(query);
            res = new ResultArray(resultSet);
        } catch (SQLException e) {
            System.err.println("SQL Problem : " + e.getMessage());
            System.err.println("SQL State : " + e.getMessage());
            System.err.println("Vendor error : " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    System.err.println("Resultset close error : " + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
        return res;
    }
}
