<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
=======
>>>>>>> 14f4b0fe6befcae80607d9f0b8a7ed784e839feb
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.sql.Statement;
=======
>>>>>>> 14f4b0fe6befcae80607d9f0b8a7ed784e839feb

public class DBConnection {

    public Connection connection;
    public PreparedStatement pstStatement;
    public ResultSet resultSet;
<<<<<<< HEAD

=======
    
>>>>>>> 14f4b0fe6befcae80607d9f0b8a7ed784e839feb
    public DBConnection() {
    }

    public Connection setConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
<<<<<<< HEAD
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/leafspice", "root", "");
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't find database driver : " + e.getMessage());
        }
        
=======
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/leafspice", "teaeli", "teaeli");
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't find database driver : " + e.getMessage());
        }
>>>>>>> 14f4b0fe6befcae80607d9f0b8a7ed784e839feb
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
