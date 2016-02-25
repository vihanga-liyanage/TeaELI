
package classes;

// attributes

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Ingredient {
    // attributes
    private int ingID ,ingCategoryID ,visibleStock ,orderedStock , invisibleStock ,supID;
    private int orderReqQty , orderExcessQty ;
    private String ingName ;
    private float unitPrice;
    
    DBConnection dbConn = new DBConnection();
    
    //constructor
    public Ingredient() {
        this.ingID = 0;
        this.ingCategoryID = 0;
        this.visibleStock = 0;
        this.orderedStock = 0;
        this.invisibleStock = 0;
        this.ingName = null;
        this.supID = 0;
        this.unitPrice =  0.0f;
        this.orderReqQty =  0;
        this.orderExcessQty =  0;
    }
    
    //getters and setters

    public int getIngID() {
        return ingID;
    }

    public void setIngID(int ingID) {
        this.ingID = ingID;
    }

    public int getIngCategoryID() {
        return ingCategoryID;
    }

    public void setIngCategoryID(int ingCategoryID) {
        this.ingCategoryID = ingCategoryID;
    }

    public int getVisibleStock() {
        return visibleStock;
    }

    public void setVisibleStock(int visibleStock) {
        this.visibleStock = visibleStock;
    }

    public int getOrderedStock() {
        return orderedStock;
    }

    public void setOrderedStock(int orderedStock) {
        this.orderedStock = orderedStock;
    }

    public int getInvisibleStock() {
        return invisibleStock;
    }

    public void setInvisibleStock(int invisibleStock) {
        this.invisibleStock = invisibleStock;
    }

    public int getSupID() {
        return supID;
    }

    public void setSupID(int supID) {
        this.supID = supID;
    }

    public String getIngName() {
        return ingName;
    }

    public void setIngName(String ingName) {
        this.ingName = ingName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    public int getOrderReqQty() {
        return orderReqQty;
    }

    public void setOrderReqQty(int orderReqQty) {
        this.orderReqQty = orderReqQty;
    }

    public int getOrderExessQty() {
        return orderExcessQty;
    }

    public void setOrderExessQty(int orderExessQty) {
        this.orderExcessQty = orderExessQty;
    }
    
    /* start of populateIngredientTable method */
    public void populateIngredientTable(DefaultTableModel tableModel){
        
        Connection connection = null;
        ResultSet resultSet = null;
        
        try{
            String query = "SELECT ingName,visibleStock,invisibleStock FROM ingredient";
            
            connection = dbConn.setConnection();
            resultSet = dbConn.getResult(query, connection);
            
            tableModel.setRowCount(0);
            
            while (resultSet.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= 3; i++) {
                    newRow.addElement(resultSet.getObject(i));
                }
                tableModel.addRow(newRow);
            }
            
        }catch(Exception e){
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
    /* end of populateIngredientTable method */
    
    /* start of loadNameForSearchStockIngComboBox method*/
    public ResultSet loadNameForSearchStockIngComboBox(){
        Connection connection = null;
        ResultSet resultSet = null;
        
        try{
            connection = dbConn.setConnection();
            
            String query = "SELECT ingName FROM ingredient";
            
            resultSet = dbConn.getResult(query, connection);
            
        } catch(Exception e){
            System.err.println("");
        }
        return resultSet; 
    }
    /* end of loadNameForSearchStockIngComboBox method */
}





