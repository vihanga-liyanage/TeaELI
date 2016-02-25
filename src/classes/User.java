package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class User {
    private String userName, Password, firstName, lastName, designation;

    public User() {
        this.userName = "";
        this.Password = "";
        this.firstName = "";
        this.lastName = "";
        this.designation = "";
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    //Method to add the result set of the user table in the DB to the user table in the Admin Pannel
    public void viewUser(DefaultTableModel tModel){
        DBConnection dbConn = new DBConnection();
        Connection connection = null;
        ResultSet resultSet;
        try{
          connection = dbConn.setConnection();  
        }catch(SQLException e){
            
        }

        String query = "SELECT userID, username, firstname, lastname FROM user";
        
        resultSet = dbConn.getResult(query, connection);
        
        try {
            tModel.setRowCount(0);
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            while (resultSet.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= 4; i++) {
                    newRow.addElement(resultSet.getObject(i));
                }
                tModel.addRow(newRow);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Resultset close error : " + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
    }
    
    //method to remove user from the user table in the admin pannel(completely remove user from the system)
    public int removeUser(int id){
        DBConnection dbConn = new DBConnection();
        Connection connection = null;
        try{
          connection = dbConn.setConnection();  
        }catch(SQLException e){
            
        }

        String query = "DELETE FROM user WHERE userID = " + id;
        
        int rslt = dbConn.updateResult(query, connection);
        
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Connection close error : " + e);
            }
        }

        return rslt;
    }
    
    //Method to check whether the username is already in the database when adding a new user
    public int checkUserName(String userName){
        DBConnection dbConn = new DBConnection();
        Connection connection = null;
        ResultSet resultSet;
        try {
            connection = dbConn.setConnection();
        } catch (SQLException e) {

        }
        
        String query = "SELECT username FROM user";
        
        resultSet = dbConn.getResult(query, connection);
        
        try {    
            while (resultSet.next()) {
                if (userName.equals(resultSet.getString(1))) {
                    return 0;//the provided username already exists in the db
                }
            }
            return 1;//the provided username does not exist in the db
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Resultset close error : " + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
    }
    
    public int addNewUser(User user){
        DBConnection dbConn = new DBConnection();
        Connection connection = null;
        try {
            connection = dbConn.setConnection();
        } catch (SQLException e) {

        }
        
        String query = "INSERT INTO user values(0, '"+ user.getUserName()+"', '"+user.getFirstName()+"', '"+user.getLastName()+"', sha1('"+user.getPassword()+"'), '"+user.getDesignation()+"')";
        int rslt = dbConn.updateResult(query, connection);
        
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Connection close error : " + e);
            }
        }

        return rslt;
    }
}
