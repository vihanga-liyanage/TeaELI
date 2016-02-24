
package classes;

// attributes

import java.sql.Connection;
import java.sql.ResultSet;

public class Ingredient {
    // attributes
    private int ingID ,ingCategoryID ,visibleStock ,orderedStock , invisibleStock ,supID;
    private int orderReqQty , orderExcessQty ;
    private String ingName ;
    private float unitPrice; 
    
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
    
    // Start of methods
    public void populateIngredientTable(){
        DBConnection dbConn = new DBConnection();
        Connection connection = null;
        ResultSet resultSet = null;
        
        try{
            String query = "SELECT ingName,visibleStock,invisibleStock FROM ingredient";
            
            connection = dbConn.setConnection();
            resultSet = dbConn.getResult(query, connection);
            
            //inventryIngredientTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            
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
}





