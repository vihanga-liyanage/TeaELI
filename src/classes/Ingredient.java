package classes;

// attributes
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import teaeli.LoginFrame;
import static teaeli.LoginFrame.adminPannel;

import static teaeli.TeaELI.resultSet;


public class Ingredient {

    // attributes
    private int ingID, ingCategoryID, visibleStock, orderedStock, invisibleStock, supID;
    private int orderReqQty, orderExcessQty;
    private String ingName;
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
        this.unitPrice = 0.0f;
        this.orderReqQty = 0;
        this.orderExcessQty = 0;
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
    //start of view all ingredients method
   public void viewAllIngredients() throws SQLException {

        DBConnection dbConn = new DBConnection();
        Connection connection = dbConn.setConnection();
        String query = "SELECT ingName,unitPrice,supName FROM ingredient,supplier where ingredient.supID = supplier.supID";
        try {
            resultSet = dbConn.getResult(query, connection);
            int count=0;
            while (resultSet.next()) {
                
                DefaultTableModel model = (DefaultTableModel) adminPannel.settingsIngredientTable.getModel();
                model.addRow(new Object[]{resultSet.getString(1),resultSet.getString(3),resultSet.getString(2)});
                
                
                /*
                
                adminPannel.settingsIngredientTable.setValueAt(resultSet.getString(1), count, 0);
                adminPannel.settingsIngredientTable.setValueAt(resultSet.getString(2), count,1);
                adminPannel.settingsIngredientTable.setValueAt("0", count, 2);
                count = count+1;*/
            }

        } catch (Exception e) {
            System.err.println("jerr : " + e);
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

    //end of view all ingredients method
}
