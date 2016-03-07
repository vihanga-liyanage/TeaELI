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
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class Blend {

    //attributes
    private String blendID, blendName, baseName, blendCategory, stockUpdateReason;
    private int baseID, visibleStock, alocatedStock, invisibleStock;
    private int orderReqQty, orderExcessQty, oldStockQty, updatedStockQTy;
    private int deliverQty, delRemoveQty, sampleQty;
    private ArrayList<Ingredient> ingredientArray;
    private ArrayList<Ingredient> flavourArray;

    Ingredient ingredient;
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
        this.alocatedStock = 0;
        this.invisibleStock = 0;
        this.orderExcessQty = 0;
        this.orderReqQty = 0;
        this.oldStockQty = 0;
        this.updatedStockQTy = 0;
        this.deliverQty = 0;
        this.delRemoveQty = 0;
        this.sampleQty = 0;
        this.ingredientArray = new ArrayList();
        this.flavourArray = new ArrayList();

        ingredient = new Ingredient();
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

    public int getAlocatedStock() {
        return alocatedStock;
    }

    public void setAlocatedStock(int alocatedStock) {
        this.alocatedStock = alocatedStock;
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

    public int getDeliverQty() {
        return deliverQty;
    }

    public void setDeliverQty(int deliverQty) {
        this.deliverQty = deliverQty;
    }

    public int getDelRemoveQty() {
        return delRemoveQty;
    }

    public void setDelRemoveQty(int delRemoveQty) {
        this.delRemoveQty = delRemoveQty;
    }

    public int getSampleQty() {
        return sampleQty;
    }

    public void setSampleQty(int sampleQty) {
        this.sampleQty = sampleQty;
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
            System.err.println("blend err : " + e);
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
            System.err.println("blend 245 err : " + e);
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

    public String getBlendNameByBlendID(String blendID) {
        String query = "SELECT blendName FROM blend WHERE blendID = '" + blendID + "' ";
        ResultArray res = dbConn.getResultArray(query);
        res.next();
        return res.getString(0);
    }

    public int getBaseByBlendID(String blendID) {
        String query = "SELECT baseID FROM blend WHERE blendID = '" + blendID + "' ";
        ResultArray res = dbConn.getResultArray(query);
        res.next();
        return Integer.parseInt(res.getString(0));
    }

    public String getIngByBaseName(int base) {
        String query = "SELECT ingName FROM ingredient WHERE ingID = '" + base + "' ";
        ResultArray res = dbConn.getResultArray(query);
        res.next();
        return res.getString(0);
    }

    public String getBlendIDByBlendName(String blendName) {
        String query = "SELECT blendID FROM blend WHERE blendName = '" + blendName + "' ";
        ResultArray res = dbConn.getResultArray(query);
        res.next();
        return res.getString(0);
    }

    public int getIngIDRecByIngName(String ingName) {
        String query = "SELECT ingID FROM ingredient WHERE ingName = '" + ingName + "' ";
        int ID = 0;
        ResultArray res = new ResultArray();
        res = dbConn.getResultArray(query);
        if (res.next()) {
            ID = Integer.parseInt(res.getString(0));
        }
        return ID;
    }

    /* start of the method to load values to the productTable in the blends tab*/
    public void populateProductTable(DefaultTableModel tModel) {

        ResultArray resultArray;

        String query = "SELECT b.blendID, b.blendName, i.ingName FROM ingredient i JOIN blend b ON i.ingID = b.baseID ORDER BY b.blendName";

        resultArray = dbConn.getResultArray(query);
        
        tModel.setRowCount(0);
        
        while (resultArray.next()) {
            tModel.addRow(new Object[]{resultArray.getString(0), resultArray.getString(1), resultArray.getString(2)});
        }
    }
    /* end of the method to load values to the productTable in the blends tab*/

    //Add new blend method
    public int addNewBlend(String blendID, String blendName, String base, String blendCategory) {

        String baseCom = ingredient.getIngIDByIngName(base);
        String query = "INSERT INTO blend values('" + blendID + "','" + blendName + "','" + baseCom + "',0,0,0,'" + blendCategory + "') ";
        int ret = dbConn.updateResult(query);
        return ret;
    }

    /* start of checkAndLoadBlendDeliverDetails method */
    public boolean checkAndLoadBlendDeliverDetails(String selectedBlendName) {

        boolean validBlendName = false;

        ResultArray resultArray;

        try {

            String query = "SELECT blendName, visibleStock, alocatedStock, blendCategory FROM blend WHERE blendName = '" + selectedBlendName + "'";

            resultArray = dbConn.getResultArray(query);

            if (resultArray.next()) {

                //set blend details
                this.setBlendName(resultArray.getString(0));
                this.setVisibleStock(Integer.parseInt(resultArray.getString(1)));
                this.setAlocatedStock(Integer.parseInt(resultArray.getString(2)));
                this.setBlendCategory(resultArray.getString(3));

                validBlendName = true;
            }
        } catch (NumberFormatException e) {
            System.err.println("err : " + e);
        }
        return validBlendName;
    }
    /* end ofcheckAndLoadBlendDeliverDetails method */

    /* start of updateDeliverDetails method */
    public boolean updateDeliverDetails() {

        boolean updated = false;

        //get current user id
        User updatedUser = new User();
        updatedUser.getIDByUsername();

        //get blendID
        this.getBlendIDFromBlendName();

        try {

            //to get current date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = Calendar.getInstance().getTime();
            dateFormat.format(currentDate);
            Timestamp date = new Timestamp(currentDate.getTime());

            //query to update blend stock
            String query = "UPDATE blend SET visibleStock = '" + this.getVisibleStock() + "' , alocatedStock = '"
                    + this.getAlocatedStock() + "' WHERE blendID = '" + this.getBlendID() + "'";

            int i = dbConn.updateResult(query);

            if (i == 1) {

                String reason = "Blend Deliver (From allocated stock) : " + this.getStockUpdateReason();

                //query to insert into stock history table about delivered amount
                query = "INSERT INTO blendstockhistory VALUES ('0','" + this.getBlendID() + "','" + date + "','"
                        + this.getOldStockQty() + "','" + this.getUpdatedStockQTy() + "','" + reason
                        + "','" + updatedUser.getUserID() + "')";

                i = dbConn.updateResult(query);

                if (this.getSampleQty() > 0 && i == 1) {

                    int oldStock = ((this.getVisibleStock() + this.sampleQty) - this.delRemoveQty);
                    int updatedStock = this.sampleQty;
                    reason = "Deliver Sample Amount (From free stock) : " + this.getStockUpdateReason();

                    //query to insert into stock history table about sample deliver amount
                    query = "INSERT INTO blendstockhistory VALUES ('0','" + this.getBlendID() + "','" + date + "','"
                            + oldStock + "','" + updatedStock + "','" + reason + "','" + updatedUser.getUserID() + "')";

                    i = dbConn.updateResult(query);

                    if (this.delRemoveQty > 0 && i == 1) {

                        oldStock = this.getVisibleStock() - this.delRemoveQty;
                        updatedStock = this.delRemoveQty;

                        reason = "Undelivered Amount (To free stock) : " + this.getStockUpdateReason();

                        //query to insert into stock history table about ubdelivered amount
                        query = "INSERT INTO blendstockhistory VALUES ('0','" + this.getBlendID() + "','" + date + "','"
                                + oldStock + "','" + updatedStock + "','" + reason + "','" + updatedUser.getUserID() + "')";

                        i = dbConn.updateResult(query);

                        if (i == 1) {
                            updated = true;
                        }
                    } else if (i == 1) {
                        updated = true;
                    }
                } else if (i == 1) {
                    updated = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Exception : " + e);
        }
        return updated;
    }
    /* end of updateDeliverDetails method */

    //getting recipie data for blend
    public ResultArray getRecipie(String blendName) {

        String query = "SELECT b.baseID, r.ingID, r.ingPercent, r.type \n"
                + "FROM blend b \n"
                + "INNER JOIN recipie r on b.blendID=r.blendID \n"
                + "WHERE b.blendName='" + blendName + "'";
        return dbConn.getResultArray(query);
    }

    //updating blend stocks after a new order
    public boolean updateBlendStock(String[] data) {
        String query = "UPDATE blend SET visibleStock='" + data[0] + "', invisibleStock='" + data[1] + "' WHERE blendID='" + data[2] + "'";
        return (dbConn.updateResult(query) == 1);
    }

    /* start of loadBlendIngredientDetails method */
    public boolean loadBlendIngredientDetails(DefaultTableModel defaultTableModel) {

        ResultArray resultArray;
        boolean load = false;

        String query = "SELECT i.ingName, r.ingPercent FROM ingredient i JOIN "
                + "recipie r ON i.ingID = r.ingID WHERE r.blendID = '" + this.getBlendID() + "' AND type = '0'";

        resultArray = dbConn.getResultArray(query);

        defaultTableModel.setRowCount(0);

        while (resultArray.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 4; i++) {
                newRow.addElement(resultArray.getString(i));
            }
            defaultTableModel.addRow(newRow);
            load = true;
        }

        return load;
    }
    /* end of loadBlendIngredientDetails method */

    /* start of loadBlendFlavourDetails */
    public boolean loadBlendFlavourDetails(DefaultTableModel defaultTableModel) {

        ResultArray resultArray;
        boolean load = false;

        String query = "SELECT i.ingName, r.ingPercent FROM ingredient i JOIN "
                + "recipie r ON i.ingID = r.ingID WHERE r.blendID = '" + this.getBlendID() + "' AND type = '1'";

        resultArray = dbConn.getResultArray(query);

        Vector mainRow = new Vector();
        defaultTableModel.addRow(mainRow);
        
        mainRow = new Vector();
        mainRow.addElement("Flavour");
        mainRow.addElement("Percentage (%)");
        defaultTableModel.addRow(mainRow);
        
        while (resultArray.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 4; i++) {
                newRow.addElement(resultArray.getString(i));
            }
            defaultTableModel.addRow(newRow);
            load = true;
        }
        return load;
    }
    /* end of loadBlendFlavourDetails method */
    
    public void getBlendCatgFromBlendName(){
        
        ResultArray resultArray;
        
        String query = "SELECT blendCategory FROM blend WHERE blendName = '" + this.getBlendName() + "'";
        
        resultArray = dbConn.getResultArray(query);
        
        if(resultArray.next()){
            this.setBlendCategory(resultArray.getString(0));
        }
    }
}
