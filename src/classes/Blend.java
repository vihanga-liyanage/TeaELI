package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


public class Blend {
    
    //attributes
    private String blendID, blendName, baseName;
    private int baseID, visibleStock, orderedStock, invisibleStock;
    private int orderReqQty , orderExcessQty ;
    private ArrayList<Ingredient> ingredientArray;
    private ArrayList<Ingredient> flavourArray;
    
    DBConnection dbConn = new DBConnection();

    //constructor
    public Blend() {
        this.blendID = "";
        this.blendName = "";
        this.baseName = "";
        this.baseID = 0;
        this.visibleStock = 0;
        this.orderedStock = 0;
        this.invisibleStock = 0;
        this.orderExcessQty = 0;
        this.orderReqQty = 0;
        this.ingredientArray = new ArrayList();
        this.flavourArray = new ArrayList();
    }
    
    /* Start of setters and getters */
    public String getBlendID() {
        return blendID;
    }

    public void setBlendID(String blendID) {
        this.blendID = blendID;
    }

    public String getBlendName() {
        return blendName;
    }

    public void setBlendName(String blendName) {
        this.blendName = blendName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public int getBaseID() {
        return baseID;
    }

    public void setBaseID(int baseID) {
        this.baseID = baseID;
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

    public int getOrderReqQty() {
        return orderReqQty;
    }

    public void setOrderReqQty(int orderReqQty) {
        this.orderReqQty = orderReqQty;
    }

    public int getOrderExcessQty() {
        return orderExcessQty;
    }

    public void setOrderExcessQty(int orderExcessQty) {
        this.orderExcessQty = orderExcessQty;
    }

    public ArrayList<Ingredient> getIngredientArray() {
        return ingredientArray;
    }

    public void setIngredientArray(ArrayList<Ingredient> ingredientArray) {
        this.ingredientArray = ingredientArray;
    }

    public ArrayList<Ingredient> getFlavourArray() {
        return flavourArray;
    }

    public void setFlavourArray(ArrayList<Ingredient> flavourArray) {
        this.flavourArray = flavourArray;
    }
    /* End of setters and getters */
    
    /* start of populateBlendTable method */
    public void populateBlendTable(DefaultTableModel tableModel){
        
        Connection connection = null;
        ResultSet resultSet = null;
        
        try{
            String query = "SELECT blendCategory,blendName,visibleStock,invisibleStock FROM blend ORDER BY blendCategory";
            
            connection = dbConn.setConnection();
            resultSet = dbConn.getResult(query, connection);
            
            tableModel.setRowCount(0);
            
            while (resultSet.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= 4; i++) {
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
    /* end */
    
    /* start of initializing blend combo in CreateNewBlendOrder */
    public void initBlendCombo(JComboBox blendsCombo){
        Connection conn = null;
        ResultSet resultSet = null;
        AutoSuggest autoSuggest = new AutoSuggest();
        
        try{
            String query = "SELECT blendName FROM blend ORDER BY blendName";
            
            conn = dbConn.setConnection();
            resultSet = dbConn.getResult(query, conn);
            
            autoSuggest.setAutoSuggest(blendsCombo, resultSet);
            
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
    
    
    
    /* start of loadNameForSearchStockBlendsComboBox method*/
    public ResultSet loadNameForsearchStockBlendsComboBox(){
        Connection connection = null;
        ResultSet resultSet = null;
        
        try{
            connection = dbConn.setConnection();
            String query = "SELECT blendName FROM blend";
            resultSet = dbConn.getResult(query, connection);
        } catch(Exception e){
            System.err.println("");
        }
        return resultSet; 
    }
    /* end */
    
    /* start of loadNameForSearchStockBlendsComboBox method*/
    public ResultSet loadNameForsearchBlendIngComboBox(){
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
    /* end */
    
    /* start of loadNameForSearchStockBlendsComboBox method*/
    public ResultSet loadNameForsearchBlendBaseComboBox(){
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
    /* end */
    
    /* Get blend data when blend name is given */
    public List<List<String>> getBlendDataByBlendName(String blendName){
        Connection conn = null;
        ResultSet resultSet = null;
        try{
            String query = "SELECT * FROM blend WHERE blendName='" + blendName + "'";
            conn = dbConn.setConnection();
            resultSet = dbConn.getResult(query, conn);

            List<List<String>> result = new ArrayList<>();  // List of list, one per row
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int numcols = rsmd.getColumnCount();
            
            while (resultSet.next()) { 
                List<String> row = new ArrayList<>(numcols); // new list per row
                int i = 1;
                while (i <= numcols) {  // don't skip the last column, use <=
                    row.add(resultSet.getString(i++));
                }
                result.add(row); // add it to the result
            }
            
            return result;
            
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
}
