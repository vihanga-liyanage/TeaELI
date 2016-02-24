package teaeli;

import classes.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeaELI {

    public static ResultSet resultSet = null;          //for temp

    public static void main(String[] args) throws SQLException {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        loginFrame.setSize(740, 400);

        /* for temp */
        DBConnection dbConn = new DBConnection();
        Connection connection = dbConn.setConnection();

        String query = "SELECT * FROM supplier";

        try {
            resultSet = dbConn.getResult(query, connection);
            while (resultSet.next()) {
                System.out.println("results: " + resultSet.getString(1));
            }
        } catch (Exception e) {
            System.err.println("err : " + e);
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

    }

}
