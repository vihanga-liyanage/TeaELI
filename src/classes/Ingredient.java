package classes;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import teaeli.LoginFrame;
import static teaeli.LoginFrame.adminPannel;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JComboBox;

public class Ingredient {

    // attributes
    private int ingID, ingCategoryID, visibleStock, orderedStock, invisibleStock, supID;
    private int orderReqQty, orderExcessQty;
    private String ingName;
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

    /* Get ingredient data when blend name is given */
    public ResultSet getIngDataByIngName(String ingName){
        Connection conn = null;
        ResultSet resultSet = null;
        
        try{
            String query = "SELECT * FROM ingredient WHERE ingName='" + ingName + "'";
            conn = dbConn.setConnection();
            resultSet = dbConn.getResult(query, conn);
            return resultSet;
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
        return null;
    }
    /* end */
    
    /* start of populateIngredientTable method */
    public void populateIngredientTable(DefaultTableModel tableModel) {

        Connection connection = null;
        ResultSet resultSet = null;

        try {
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
    /* end of populateIngredientTable method */
/* start of initializing ing combo in AddNewBlend */
    public void initIngCombo(JComboBox ingCombo){
        Connection conn = null;
        ResultSet resultSet = null;
        AutoSuggest autoSuggest = new AutoSuggest();
        
        try{
            String query = "SELECT ingName FROM ingredient ORDER BY ingName";
            
            conn = dbConn.setConnection();
            resultSet = dbConn.getResult(query, conn);
            
            autoSuggest.setAutoSuggest(ingCombo, resultSet);
            
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
    }
    /* start of loadNameForSearchStockIngComboBox method*/
    public ResultSet loadNameForSearchStockIngComboBox() {
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = dbConn.setConnection();

            String query = "SELECT ingName FROM ingredient";

            resultSet = dbConn.getResult(query, connection);

        } catch (Exception e) {
            System.err.println("");
        }
        return resultSet;
    }
    /* end of loadNameForSearchStockIngComboBox method */

    //start of view all ingredients method
    public void viewAllIngredients() throws SQLException {

        Connection connection = dbConn.setConnection();
        ResultSet resultSet = null;
        String query = "SELECT ingName,unitPrice,supName FROM ingredient,supplier where ingredient.supID = supplier.supID";
        try {
            resultSet = dbConn.getResult(query, connection);

            while (resultSet.next()) {
                DefaultTableModel model = (DefaultTableModel) adminPannel.settingsIngredientTable.getModel();
                model.addRow(new Object[]{resultSet.getString(1), resultSet.getString(3), resultSet.getString(2)});
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
    //end of view all ingredients method

    //start of view all details of a ingredient
    public String[] viewAllDetailsOfAIngredient(String ingredientName) throws SQLException {

        Connection connection = dbConn.setConnection();
        ResultSet resultSet = null;
        String[] resultArray = new String[4];
        //set name of the ingredient
        resultArray[0]=ingredientName;

        String query = "SELECT categoryName,supName,unitPrice  FROM ingredient I,supplier S,ingredientcategory IC where I.ingName = '" + ingredientName + "' and I.supID = S.supID and I.ingCategoryID = IC.ingCategoryID;";
        try {
            resultSet = dbConn.getResult(query, connection);
            while (resultSet.next()) {
                for (int i = 1; i<=3; i++) {
                    resultArray[i] = resultSet.getString(i);
                }
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
        return resultArray;
    }
   //end of view all details of a ingredient
    
    //start of update ingredient method
    public void updateIngredient(String ingredientName){
        
    }
    
    //end of update ingredient method
}
