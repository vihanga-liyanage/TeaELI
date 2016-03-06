package classes;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import static teaeli.LoginFrame.adminPannel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

public class Ingredient {

    // attributes
    private int ingID, ingCategoryID, supID;
    private float orderReqQty, orderExcessQty, oldStockQty, updatedStockQTy;
    private String ingName, ingCategoryName, stockUpdateReason;
    private float unitPrice, visibleStock, alocatedStock, invisibleStock;

    DBConnection dbConn = new DBConnection();

    //constructor
    public Ingredient() {
        this.ingID = 0;
        this.ingCategoryID = 0;
        this.visibleStock = 0;
        this.alocatedStock = 0;
        this.invisibleStock = 0;
        this.ingName = "";
        this.ingCategoryName = "";
        this.stockUpdateReason = "";
        this.supID = 0;
        this.oldStockQty = 0;
        this.updatedStockQTy = 0;
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

    public float getVisibleStock() {
        return visibleStock;
    }

    public void setVisibleStock(float visibleStock) {
        this.visibleStock = visibleStock;
    }

    public float getAlocatedStock() {
        return alocatedStock;
    }

    public void setAlocatedStock(float alocatedStock) {
        this.alocatedStock = alocatedStock;
    }

    public float getInvisibleStock() {
        return invisibleStock;
    }

    public void setInvisibleStock(float invisibleStock) {
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

    public float getOrderReqQty() {
        return orderReqQty;
    }

    public void setOrderReqQty(float orderReqQty) {
        this.orderReqQty = orderReqQty;
    }

    public float getOrderExessQty() {
        return orderExcessQty;
    }

    public void setOrderExessQty(float orderExessQty) {
        this.orderExcessQty = orderExessQty;
    }

    public float getOrderExcessQty() {
        return orderExcessQty;
    }

    public void setOrderExcessQty(float orderExcessQty) {
        this.orderExcessQty = orderExcessQty;
    }

    public String getIngCategoryName() {
        return ingCategoryName;
    }

    public void setIngCategoryName(String ingCategoryName) {
        this.ingCategoryName = ingCategoryName;
    }

    public String getStockUpdateReason() {
        return stockUpdateReason;
    }

    public void setStockUpdateReason(String stockUpdateReason) {
        this.stockUpdateReason = stockUpdateReason;
    }

    public float getOldStockQty() {
        return oldStockQty;
    }

    public void setOldStockQty(float oldStockQty) {
        this.oldStockQty = oldStockQty;
    }

    public float getUpdatedStockQTy() {
        return updatedStockQTy;
    }

    public void setUpdatedStockQTy(float updatedStockQTy) {
        this.updatedStockQTy = updatedStockQTy;
    }

    /* Get blend data when ing name is given -thisara */
    public List<List<String>> getIngDataByIngName(String ingName) {
        Connection conn = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM ingredient WHERE ingName='" + ingName + "'";
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

        } catch (Exception e) {
            System.err.println("ing 213 err : " + e);
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
    /* end */

    /* start of populateIngredientTable method */
    public void populateIngredientTable(DefaultTableModel tableModel) {
        ResultArray resultSet;
        String query = "SELECT ing.categoryName , i.ingName,i.visibleStock,i.invisibleStock FROM ingredient i JOIN ingredientcategory ing ON i.ingCategoryID = ing.ingCategoryID ORDER BY ing.categoryName,i.ingName ";
        resultSet = dbConn.getResultArray(query);
        tableModel.setRowCount(0);
        while (resultSet.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 4; i++) {
                newRow.addElement(resultSet.getString(i));
            }
            tableModel.addRow(newRow);
        }
    }
    
    //Populate Blend detail's ingredients table according to blend
    public void populateBlendIngTable(DefaultTableModel tableModel, String blendID){
        ResultArray resultSet;
        String query = "SELECT I.ingName, R.ingPercent FROM ingredient I, recipie R WHERE I.ingID = R.ingID AND R.blendID = '" + blendID + "' AND R.type = 0";
        resultSet = dbConn.getResultArray(query);
        tableModel.setRowCount(0);
        while (resultSet.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 2; i++) {
                newRow.addElement(resultSet.getString(i));
            }
            tableModel.addRow(newRow);
        }
    }
    
    //Populate Blend detail's flavours table according to blend
    public void populateBlendFlavourTable(DefaultTableModel tableModel, String blendID){
        ResultArray resultSet;
        String query = "SELECT I.ingName, R.ingPercent FROM ingredient I, recipie R WHERE I.ingID = R.ingID AND R.blendID = '" + blendID + "' AND R.type = 1";
        resultSet = dbConn.getResultArray(query);
        tableModel.setRowCount(0);
        while (resultSet.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 2; i++) {
                newRow.addElement(resultSet.getString(i));
            }
            tableModel.addRow(newRow);
        }
    }

    /* start of initializing ing combo in AddNewBlend */
    public void initIngCombo(JComboBox ingCombo) {
        ResultArray res = null;
        AutoSuggest autoSuggest = new AutoSuggest();
        String query = "SELECT ingName FROM ingredient WHERE ingCategoryID=1 OR ingCategoryID=3 OR ingCategoryID=4 OR ingCategoryID=5 OR ingCategoryID=6 OR ingCategoryID=7 ORDER BY ingName";
        res = dbConn.getResultArray(query);
        autoSuggest.setAutoSuggest(ingCombo, res);

    }

    /* start of initializing flavour combo in AddNewBlend */
    public void initFlavourCombo(JComboBox ingCombo) {
        ResultArray res = null;
        AutoSuggest autoSuggest = new AutoSuggest();
        String query = "SELECT ingName FROM ingredient WHERE ingCategoryID=2 ORDER BY ingName";
        res = dbConn.getResultArray(query);
        autoSuggest.setAutoSuggest(ingCombo, res);
    }

    /* start of loadNameForSearchStockIngComboBox method*/
    public ResultArray loadNameForSearchStockIngComboBox() {
        String query = "SELECT ingName FROM ingredient";
        return dbConn.getResultArray(query);
    }

    /* start of initializing flavours combo in AddNewBlend */
    public void initBaseCombo(JComboBox ingCombo) {
        ResultArray res = null;
        AutoSuggest autoSuggest = new AutoSuggest();
        String query = "SELECT ingName FROM ingredient WHERE ingCategoryID=2 OR ingCategoryID=3 OR ingCategoryID=4 OR ingCategoryID=5 OR ingCategoryID=6 ORDER BY ingName";
        res = dbConn.getResultArray(query);
        autoSuggest.setAutoSuggest(ingCombo, res);
    }

    /* start of checkAndLoadIngredientStockDetails method */
    public boolean checkAndLoadIngredientStockDetails(String selectedIngName) {
        boolean validIngName = false;
        ResultArray resultArray;
        try {
            //query to load ingredient details
            String query = "SELECT i.ingName, i.visibleStock, ing.categoryName FROM ingredient i JOIN ingredientcategory ing ON i.ingCategoryID = ing.ingCategoryID WHERE ingName = '" + selectedIngName + "'";
            resultArray = dbConn.getResultArray(query);
            if (resultArray.next()) {

                //set ingeredient attribute values
                this.setIngName(resultArray.getString(0));
                this.setVisibleStock(Float.parseFloat(resultArray.getString(1)));
                this.setIngCategoryName(resultArray.getString(2));

                validIngName = true;
            }
        } catch (NumberFormatException e) {
            System.err.println("Number Format Exception : " + e);
        }
        return validIngName;
    }
    /* end of checkAndLoadIngredientStockDetails method */
     
    /* start of getIngIDFromIngName method */
    public void getIngIDFromIngName() {
        ResultArray resultArray;
        try {
            String query = "SELECT ingID FROM ingredient WHERE ingName = '" + this.getIngName() + "'";
            resultArray = dbConn.getResultArray(query);
            if (resultArray.next()) {
                this.setIngID(Integer.parseInt(resultArray.getString(0)));
            }
        } catch (NumberFormatException e) {
            System.err.println("Exception : " + e);
        }
    }
    /* end of getIngIDFromIngName method */
    
    /* start of updateStockQty method */
    public boolean updateStockQty() {

        boolean updated = false;

        //set current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();
        dateFormat.format(currentDate);
        Timestamp date = new Timestamp(currentDate.getTime());

        //get user id 
        User updatedUser = new User();
        updatedUser.getIDByUsername();

        try {
            //get ingID of the ingredient
            this.getIngIDFromIngName();

            //query to update ingredient stock
            String query = "UPDATE ingredient SET visibleStock = '" + this.getVisibleStock() + "' WHERE ingID = '" + this.getIngID() + "'";

            int i = dbConn.updateResult(query);

            if (i == 1) {

                //query to inesrt into stock history
                query = "INSERT INTO ingredientstockhistory VALUES ('0','" + this.getIngID() + "','" + date + "','" + this.getOldStockQty() + "','" + this.getUpdatedStockQTy() + "','" + this.getStockUpdateReason() + "','" + updatedUser.getUserID() + "')";

                i = dbConn.updateResult(query);

                if (i == 1) {
                    updated = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Exception : " + e);
        }
        return updated;
    }
    /* end of updateStockQty method */

    //start of view all ingredients method
    public void viewAllIngredients() throws SQLException {
        ResultArray res = null;
        String query = "SELECT ingName,unitPrice,supName FROM ingredient,supplier where ingredient.supID = supplier.supID";
        res = dbConn.getResultArray(query);

        while (res.next()) {
            DefaultTableModel model = (DefaultTableModel) adminPannel.settingsIngredientTable.getModel();
            model.addRow(new Object[]{res.getString(0), res.getString(2), res.getString(3)});
        }
    }
    
    public String getUnitPriceByIngName(String ingName){
        String unitPrice = "";
        ResultArray res = null;
        String query = "SELECT unitPrice FROM ingredient where ingredient.ingName = '" + ingName + "' ";
        res = dbConn.getResultArray(query);
        if(res.next()){
            unitPrice = res.getString(0);
        }
        return unitPrice;
    }

    //start of view all details of a ingredient
    public String[] viewAllDetailsOfAIngredient(String ingredientName) throws SQLException {

        Connection connection = dbConn.setConnection();
        ResultSet resultSet = null;
        String[] resultArray = new String[5];
        //set name of the ingredient
        resultArray[0] = ingredientName;

        String query = "SELECT ingID,categoryName,supName,unitPrice  FROM ingredient I,supplier S,ingredientcategory IC where I.ingName = '" + ingredientName + "' and I.supID = S.supID and I.ingCategoryID = IC.ingCategoryID;";
        try {
            resultSet = dbConn.getResult(query, connection);
            while (resultSet.next()) {
                for (int i = 1; i <= 4; i++) {
                    resultArray[i] = resultSet.getString(i);
                }
            }
        } catch (Exception e) {
            System.err.println("ing 389 err : " + e);
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
    public int updateIngredient(int ingredientID, String ingredientName, int ingCategory, int supID, float unitPrice) throws SQLException {

        Connection connection = dbConn.setConnection();
        ResultSet resultSet = null;
        Statement statement;
        int insertOK = 0;
        //set name of the ingredient
        String query = "Update ingredient SET ingName = '" + ingredientName + "', ingCategoryID = '" + ingCategory + "',supID= '" + supID + "',unitPrice = '" + unitPrice + "' WHERE ingID = '" + ingredientID + "'";
        try {
            statement = connection.createStatement();
            insertOK = statement.executeUpdate(query);
        } catch (Exception e) {
            System.err.println("ing 423 err : " + e);
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
        return insertOK;
    }

    public ArrayList<String> getSupplierDetails() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Statement st = null;
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM supplier";
            con = dbConn.setConnection();
            rs = dbConn.getResult(query, con);
            while (rs.next()) {
                result.add(rs.getString(2));
            }

            return result;

        } catch (Exception e) {
            System.err.println("ing 461 err : " + e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
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
        return null;
    }

    public int addNewSupplier(String Name) {
        Connection connection = null;
        connection = dbConn.setConnection();

        String query = "INSERT INTO supplier values(0,'" + Name + "')";

        int rslt = dbConn.updateResult(query, connection);

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Connection close error : " + e);
            }
        }

        return rslt;
    }

    public int addNewIngredient(String Name, String type, String supplier, float price) {
        Connection connection = null;
        int rslt1 = 0, rslt2 = 0;
        connection = dbConn.setConnection();
        String query1 = "SELECT ingCategoryID FROM ingredientcategory WHERE categoryName = '" + type + "' ";
        ResultSet rs1 = dbConn.getResult(query1, connection);

        try {
            while (rs1.next()) {
                rslt1 = Integer.parseInt(rs1.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingredient.class.getName()).log(Level.SEVERE, null, ex);

        }

        String query2 = "SELECT supID FROM supplier WHERE supName = '" + supplier + "' ";
        ResultSet rs2 = dbConn.getResult(query2, connection);

        try {
            while (rs2.next()) {
                rslt2 = Integer.parseInt(rs2.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Ingredient.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query3 = "INSERT INTO ingredient values(0,'" + Name + "','" + rslt1 + "',0,0,0,'" + rslt2 + "','" + price + "') ";

        int rslt3 = dbConn.updateResult(query3, connection);

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Connection close error : " + e);
            }
        }

        return rslt3;
    }

    //getting ingredient data by ingID
    public ResultArray getIngDataByID(String ingID){
        String query = "SELECT i.ingID, i.ingName, ic.categoryName, i.visibleStock, i.alocatedStock, i.invisibleStock, s.supName  \n" +
                        "FROM ingredient i INNER JOIN supplier s ON i.supID=s.supID INNER JOIN ingredientcategory ic ON i.ingCategoryID=ic.ingCategoryID\n" +
                        "WHERE ingID='" + ingID + "'";

        return dbConn.getResultArray(query);
    }

    public String getIngIDByIngName(String base) {
        String query = "SELECT ingID FROM ingredient WHERE ingName = '" + base + "' ";
        ResultArray res = dbConn.getResultArray(query);
        res.next();
        return res.getString(0);
    }

    //updating blend stocks after a new order
    public boolean updateIngredientStock(String[] data) {
        String query = "UPDATE ingredient SET visibleStock='" + data[0] + "', invisibleStock='" + data[1] + "' WHERE ingID='" + data[2] + "'";
        return (dbConn.updateResult(query) == 1);
    }
}
