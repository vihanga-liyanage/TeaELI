package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Blend {

    //attributes
    private String blendID, blendName, baseName, blendCategory, stockUpdateReason;
    private int baseID, visibleStock, orderedStock, invisibleStock;
    private int orderReqQty, orderExcessQty, oldStockQty, updatedStockQTy;
    private ArrayList<Ingredient> ingredientArray;
    private ArrayList<Ingredient> flavourArray;

    DBConnection dbConn = new DBConnection();

    //constructor
    public Blend() {
        this.blendID = "";
        this.blendName = "";
        this.baseName = "";
        this.blendCategory = "";
        this.stockUpdateReason = "";
        this.baseID = 0;
        this.visibleStock = 0;
        this.orderedStock = 0;
        this.invisibleStock = 0;
        this.orderExcessQty = 0;
        this.orderReqQty = 0;
        this.oldStockQty = 0;
        this.updatedStockQTy = 0;
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

    public String getBlendCategory() {
        return blendCategory;
    }

    public void setBlendCategory(String blendCategory) {
        this.blendCategory = blendCategory;
    }

    public String getStockUpdateReason() {
        return stockUpdateReason;
    }

    public void setStockUpdateReason(String stockUpdateReason) {
        this.stockUpdateReason = stockUpdateReason;
    }

    public int getOldStockQty() {
        return oldStockQty;
    }

    public void setOldStockQty(int oldStockQty) {
        this.oldStockQty = oldStockQty;
    }

    public int getUpdatedStockQTy() {
        return updatedStockQTy;
    }

    public void setUpdatedStockQTy(int updatedStockQTy) {
        this.updatedStockQTy = updatedStockQTy;
    }
    /* End of setters and getters */

    /* start of populateBlendTable method */
    public void populateBlendTable(DefaultTableModel tableModel) {

        ResultArray resultArray;

        try {
            String query = "SELECT blendCategory,blendName,visibleStock,invisibleStock FROM blend ORDER BY blendCategory, blendName";

            resultArray = dbConn.getResultArray(query);

            tableModel.setRowCount(0);

            while (resultArray.next()) {
                Vector newRow = new Vector();
                for (int i = 0; i <= 4; i++) {
                    newRow.addElement(resultArray.getString(i));
                }
                tableModel.addRow(newRow);
            }

        } catch (Exception e) {
            System.err.println("err : " + e);
        }
    }
    /* end of populateBlendTable method */

    /* start of initializing blend combo in CreateNewBlendOrder */
    public void initBlendCombo(JComboBox blendsCombo) {
        AutoSuggest autoSuggest = new AutoSuggest();
        String query = "SELECT blendName FROM blend ORDER BY blendName";
        ResultArray res = dbConn.getResultArray(query);
        autoSuggest.setAutoSuggest(blendsCombo, res);
    }

    /* start of loadNameForSearchStockBlendsComboBox method */
    public ResultArray loadNameForSearchStockBlendsComboBox() {

        ResultArray resultArray = null;

        try {

            String query = "SELECT blendName FROM blend ";

            resultArray = dbConn.getResultArray(query);

        } catch (Exception e) {
            System.err.println("Exception : " + e);
        }
        return resultArray;
    }
    /* end of loadNameForSearchStockBlendsComboBox method */

    /* start of checkAndLoadBlendStockDetails method */
    public boolean checkAndLoadBlendStockDetails(String selectedBlendName) {

        boolean validBlendName = false;

        ResultArray resultArray;

        try {

            String query = "SELECT blendName, visibleStock, blendCategory FROM blend WHERE blendName = '" + selectedBlendName + "'";

            resultArray = dbConn.getResultArray(query);

            if (resultArray.next()) {

                //set blend details
                this.setBlendName(resultArray.getString(0));
                this.setVisibleStock(Integer.parseInt(resultArray.getString(1)));
                this.setBlendCategory(resultArray.getString(2));

                validBlendName = true;
            }
        } catch (NumberFormatException e) {
            System.err.println("err : " + e);
        }
        return validBlendName;
    }
    /* end ofcheckAndLoadBlendStockDetails method */

    /* start of getBlendIDFromBlendName method */
    public void getBlendIDFromBlendName() {

        ResultArray resultArray;

        try {
            String query = "SELECT blendID FROM blend WHERE blendName = '" + this.getBlendName() + "'";

            resultArray = dbConn.getResultArray(query);

            if (resultArray.next()) {
                this.setBlendID(resultArray.getString(0));
            }
        } catch (Exception e) {
            System.err.println("Exception : " + e);
        }
    }
    /* end of getBlendIDFromBlendName method */

    /* start of updateStockQty method */
    public boolean updateStockQty() {

        boolean updated = false;

        //to get current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();
        dateFormat.format(currentDate);
        Timestamp date = new Timestamp(currentDate.getTime());

        //get current user id
        User updatedUser = new User();
        updatedUser.getIDByUsername();

        //get blendID
        this.getBlendIDFromBlendName();

        //query to update blend stock
        String query = "UPDATE blend SET visibleStock = '" + this.getVisibleStock() + "' WHERE blendID = '" + this.getBlendID() + "'";

        int i = dbConn.updateResult(query);

        if (i == 1) {

            //query to insert into stock history table
            query = "INSERT INTO blendstockhistory VALUES ('0','" + this.getBlendID() + "','" + date + "','" + this.getOldStockQty() + "','" + this.getUpdatedStockQTy() + "','" + this.getStockUpdateReason() + "','" + updatedUser.getUserID() + "')";

            i = dbConn.updateResult(query);

            if (i == 1) {
                updated = true;
            }
        }
        return updated;
    }
    /* end of updateStockQty method */

    /* start of loadNameForSearchStockBlendsComboBox method*/
    public ResultSet loadNameForsearchBlendIngComboBox() {
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
    /* end */

    /* start of loadNameForSearchStockBlendsComboBox method*/
    public ResultSet loadNameForsearchBlendBaseComboBox() {
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
    /* end */

    /* Get blend data when blend name is given */
    public ResultArray getBlendDataByBlendName(String blendName) {
        String query = "SELECT * FROM blend WHERE blendName='" + blendName + "'";
        return dbConn.getResultArray(query);
    }
    /* end */

    public ResultArray getIngIDByIngName(String base) {
        String query = "SELECT ingID FROM ingredient WHERE ingName = '" + base + "' ";
        return dbConn.getResultArray(query);
    }

    /* start of the method to load values to the productTable in the blends tab*/
    public void populateProductTable(DefaultTableModel tModel) {
        Connection connection = null;
        ResultSet resultSet;
        connection = dbConn.setConnection();

        String query = "SELECT b.blendID, b.blendName, i.ingName FROM ingredient i JOIN blend b ON i.ingID = b.baseID ORDER BY b.blendName";

        resultSet = dbConn.getResult(query, connection);
        try {
            tModel.setRowCount(0);

            while (resultSet.next()) {
                tModel.addRow(new Object[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)});
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
    /* end */

    //Add new blend method
    public int addNewBlend(String blendID, String blendName, String base, String blendCategory) {

        ResultArray res = getIngIDByIngName(base);
        String baseCom = "";
        if (res.next()) {
            baseCom = res.getString(0);
            //System.out.println("base is " +baseCom);
        }
        String query = "INSERT INTO blend values('" + blendID + "','" + blendName + "','" + baseCom + "',0,0,0,'" + blendCategory + "') ";
        int ret = dbConn.updateResult(query);
        if (ret == 1) {
            JOptionPane.showMessageDialog(null, "New Blend Succesfully Added");
        } else {
            JOptionPane.showMessageDialog(null, "Error!, Data not Saved");
        }

        return 1;
    }

    /* start of checkAndLoadBlendDeliverDetails method */
    public boolean checkAndLoadBlendDeliverDetails(String selectedBlendName) {

        boolean validBlendName = false;
        
        ResultArray resultArray;

        try {

            String query = "SELECT blendName, visibleStock, orderedStock, blendCategory FROM blend WHERE blendName = '" + selectedBlendName + "'";

            resultArray = dbConn.getResultArray(query);

            if (resultArray.next()) {

                //set blend details
                this.setBlendName(resultArray.getString(0));
                this.setVisibleStock(Integer.parseInt(resultArray.getString(1)));
                this.setOrderedStock(Integer.parseInt(resultArray.getString(2)));
                this.setBlendCategory(resultArray.getString(3));
                
                validBlendName = true;
            }
        } catch (NumberFormatException e) {
            System.err.println("err : " + e);
        } 
        return validBlendName;
    }
    /* end ofcheckAndLoadBlendDeliverDetails method */
    
}
