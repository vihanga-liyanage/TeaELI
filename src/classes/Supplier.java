package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    
    //start of load suppliers for combobox
    public ResultSet loadSuppliersForCombobox() throws SQLException {
         Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = dbConn.setConnection();

            String query = "SELECT supName FROM supplier";

            resultSet = dbConn.getResult(query, connection);

        } catch (Exception e) {
            System.err.println("");
        }
        return resultSet;
       
    }
    //end of load suppliers for combobox
    
    
   public int addNewSupplier(String Name) throws SQLException {
        DBConnection dbConn = new DBConnection();
        Connection connection =dbConn.setConnection();
        Statement statement;
        int insertOK =0;
        
        String query = "INSERT INTO supplier(supName) values('" + Name + "')";
        try {
            statement = connection.createStatement();
            insertOK = statement.executeUpdate(query);
            
        } catch (Exception e) {
            System.err.println("sup 70 err : " + e);
        } finally {            
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
        return insertOK;
    } 
    
    
    
    
    
        //start of get suplier id by name
    public int getSupplierIDByName(String supplierName) throws SQLException {
        int supplierID = 0;
        Connection connection = dbConn.setConnection();
        ResultSet resultSet = null;
        

        //set name of the ingredient
        String query = "SELECT supID from supplier WHERE supplier.supName= '" + supplierName + "' ";
        try {
            resultSet = dbConn.getResult(query, connection);

            if (resultSet.next()) {
                supplierID = Integer.parseInt(resultSet.getString(1));
            }

        } catch (Exception e) {
            System.err.println("sup 104 err : " + e);
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
        System.out.println("supplierID  " + supplierID);
        return supplierID;
    }
    //end of get suplier id by name
    

}
