package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import static teaeli.LoginFrame.adminPannel;
import static teaeli.TeaELI.resultSet;

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
    public static void updateTableModelData(DefaultTableModel tModel, ResultSet rs) throws Exception {
        tModel.setRowCount(0);
        ResultSetMetaData metaData = rs.getMetaData();

        while (rs.next()) {
            Vector newRow = new Vector();
            for (int i = 1; i <= 5; i++) {
                newRow.addElement(rs.getObject(i));
            }
            tModel.addRow(newRow);
        }
    }
    
    //Set user details to the users table in the Admin Pannel
    public void viewUser() throws SQLException, Exception{
        DBConnection dbConn = new DBConnection();
        Connection connection = dbConn.setConnection();

        String query = "SELECT userID, username, firstname, lastname FROM user";
        
        try {
            
            resultSet = dbConn.getResult(query, connection);
            
        } catch (Exception e) {
            System.err.println("err : " + e);
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
        
        updateTableModelData((DefaultTableModel) adminPannel.userTable.getModel(), resultSet);
    }
    
}
