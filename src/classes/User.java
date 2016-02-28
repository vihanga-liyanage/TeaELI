package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import static teaeli.TeaELI.loginFrame;

public class User {

    private String userName, Password, firstName, lastName, designation;
    private int userID;

    DBConnection dbConn = new DBConnection();

    public User() {
        this.userName = "";
        this.Password = "";
        this.firstName = "";
        this.lastName = "";
        this.designation = "";
        this.userID = 0;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    //Method to add the result set of the user table in the DB to the user table in the Admin Pannel
    public void viewUser(DefaultTableModel tModel) {
        Connection connection = null;
        ResultSet resultSet;
        connection = dbConn.setConnection();

        String query = "SELECT userID, username, firstname, lastname FROM user";

        resultSet = dbConn.getResult(query, connection);
        try {
            tModel.setRowCount(0);

            while (resultSet.next()) {
                tModel.addRow(new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)});
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
    public int removeUser(int id) {
        Connection connection = null;
        connection = dbConn.setConnection();

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
    public int checkUserName(String userName) {
        Connection connection = null;
        ResultSet resultSet;
        connection = dbConn.setConnection();

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

    public int addNewUser(User user) {
        Connection connection = null;
        connection = dbConn.setConnection();

        String query = "INSERT INTO user values(0, '" + user.getUserName() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', sha1('" + user.getPassword() + "'), '" + user.getDesignation() + "')";
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

    public void getIDByUsername() {

        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = dbConn.setConnection();

            String query = "SELECT userID FROM user WHERE username = '" + loginFrame.user + "'";

            resultSet = dbConn.getResult(query, connection);

            if (resultSet.next()) {
                this.setUserID(Integer.parseInt(resultSet.getString(1)));
            }

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Exception : " + e);
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

    public int checkLogin(String userName, String password) {
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = dbConn.setConnection();//get the connection
            String query = "SELECT username,designation FROM user where password = sha1('" + password + "') and username = ('" + userName + "')";
            ResultSet rs = dbConn.getResult(query, connection);

            while (rs.next()) {
                if (rs.getString(2).equals("Admin")) {
                    return 1;
                } else if (rs.getString(2).equals("Manager")) {
                    return 2;
                } else {
                    return 3;
                }
            }
            return 4;

        } catch (SQLException e) {
            System.out.println(e);//an error occured while executing
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }

    public int updateUserName(String firstname, String lastname,String username) {
        Connection connection = null;
        PreparedStatement pst = null;
        connection = dbConn.setConnection();
        String query = "UPDATE user SET firstname =' " + firstname + "', lastname = '" + lastname + "' WHERE username = '" + username + "'";
        if (dbConn.updateResult(query, connection) == 1) {
            return 1;
        } else {
            return 2;
        }
    }
    
    public  int updatePassword(String firstname, String lastname,String username,String newpassword,String currentpassword){
        Connection connection = null;
        PreparedStatement pst = null;
        connection = dbConn.setConnection();
        String query = "UPDATE user SET firstname =' "+firstname+"', lastname = '"+lastname+"',password = sha1('"+newpassword+"') WHERE username = '"+username+"' and password = sha1('"+currentpassword+"')";
        if (dbConn.updateResult(query, connection)==1){
            return 1;
        }else{
            return 2;
        }
    }
}


