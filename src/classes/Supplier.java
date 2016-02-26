package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Supplier {

    private int supplierID;
    private String SupplierName;

    DBConnection dbConn = new DBConnection();

    public Supplier() {
        this.supplierID = 0;
        this.SupplierName = null;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }

    public String[] loadSuppliersForCombobox() throws SQLException {
        System.out.println("inside loadSuppliersForCombobox()");
        Connection con = dbConn.setConnection();
        ResultSet resultSet = null;
        String[] supplierList = null;
        try {
            String queryRowCount = "SELECT COUNT(supID) FROM supplier";
            resultSet = dbConn.getResult(queryRowCount, con);

            int numOfRows = 0;
            if (resultSet.next()) {
                numOfRows = Integer.parseInt(resultSet.getString(1));
            }

            String query = "SELECT supName FROM supplier";
            resultSet = dbConn.getResult(query, con);

            supplierList = new String[numOfRows];
            int i = 0;
            while (resultSet.next()) {
                supplierList[i] = resultSet.getString(1);
                i++;
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
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
        return supplierList;
    }

}
