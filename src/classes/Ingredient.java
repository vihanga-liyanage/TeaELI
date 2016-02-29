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
    private int ingID, ingCategoryID, visibleStock, orderedStock, invisibleStock, supID;
    private int orderReqQty, orderExcessQty, oldStockQty, updatedStockQTy;
    private String ingName, ingCategoryName, stockUpdateReason;
    private float unitPrice;

    DBConnection dbConn = new DBConnection();

    //constructor
    public Ingredient() {
        this.ingID = 0;
        this.ingCategoryID = 0;
        this.visibleStock = 0;
        this.orderedStock = 0;
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

    public int getOrderExcessQty() {
        return orderExcessQty;
    }

    public void setOrderExcessQty(int orderExcessQty) {
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

    /* Get ingredient data when blend name is given */
   /* public ResultSet getIngDataByIngName(String ingName) {
        Connection conn = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM ingredient WHERE ingName='" + ingName + "'";
            conn = dbConn.setConnection();
            resultSet = dbConn.getResult(query, conn);
            return resultSet;
        } catch (Exception e) {
            System.err.println("err : " + e);
        }
        return null;
    }*/
    
    /* Get blend data when ing name is given -thisara */
    public List<List<String>> getIngDataByIngName(String ingName){
        Connection conn = null;
        ResultSet resultSet = null;
        try{
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
    /* end */

    /* start of populateIngredientTable method */
    public void populateIngredientTable(DefaultTableModel tableModel) {

        ResultArray resultSet;
        
        try {
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
        } catch (Exception e) {
            System.err.println("err : " + e);
        } 
    }
    /* end of populateIngredientTable method */

    /* start of initializing ing combo in AddNewBlend */
    public void initIngCombo(JComboBox ingCombo) {
        Connection conn = null;
        ResultSet resultSet = null;
        AutoSuggest autoSuggest = new AutoSuggest();

        try {
            String query = "SELECT ingName FROM ingredient WHERE ingCategoryID=1 OR ingCategoryID=3 OR ingCategoryID=4 OR ingCategoryID=5 OR ingCategoryID=6 ORDER BY ingName";

            conn = dbConn.setConnection();
            resultSet = dbConn.getResult(query, conn);

            autoSuggest.setAutoSuggest(ingCombo, resultSet);

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
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
    }

    /* start of initializing flavour combo in AddNewBlend */
    public void initFlavourCombo(JComboBox ingCombo) {
        Connection conn = null;
        ResultSet resultSet = null;
        AutoSuggest autoSuggest = new AutoSuggest();

        try {
            String query = "SELECT ingName FROM ingredient WHERE ingCategoryID=2 ORDER BY ingName";

            conn = dbConn.setConnection();
            resultSet = dbConn.getResult(query, conn);

            autoSuggest.setAutoSuggest(ingCombo, resultSet);

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
            System.err.println("Exception : " + e);
        }
        return resultSet;
    }
    /* end of loadNameForSearchStockIngComboBox method */

    /* start of initializing flavours combo in AddNewBlend */
    public void initBaseCombo(JComboBox ingCombo) {
        Connection conn = null;
        ResultSet resultSet = null;
        AutoSuggest autoSuggest = new AutoSuggest();

        try {
            String query = "SELECT ingName FROM ingredient WHERE ingCategoryID=2 OR ingCategoryID=3 OR ingCategoryID=4 OR ingCategoryID=5 OR ingCategoryID=6 ORDER BY ingName";

            conn = dbConn.setConnection();
            resultSet = dbConn.getResult(query, conn);

            autoSuggest.setAutoSuggest(ingCombo, resultSet);

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
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
    }

    /* start of checkAndLoadIngredientStockDetails method */
    public boolean checkAndLoadIngredientStockDetails(String selectedIngName) {

        Connection connection = null;
        ResultSet resultSet = null;
        boolean validIngName = false;

        try {
            connection = dbConn.setConnection();

            String query = "SELECT i.ingName, i.visibleStock, ing.categoryName FROM ingredient i JOIN ingredientcategory ing ON i.ingCategoryID = ing.ingCategoryID WHERE ingName = '" + selectedIngName + "'";

            resultSet = dbConn.getResult(query, connection);

            if (resultSet.next()) {

                validIngName = true;
                this.setIngName(resultSet.getString(1));
                this.setVisibleStock(Integer.parseInt(resultSet.getString(2)));
                this.setIngCategoryName(resultSet.getString(3));
            }
        } catch (SQLException | NumberFormatException e) {
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
        return validIngName;
    }
    /* end ofcheckAndLoadIngredientStockDetails method */

    /* start of getIngIDFromIngName method */
    public void getIngIDFromIngName() {
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = dbConn.setConnection();
            String query = "SELECT ingID FROM ingredient WHERE ingName = '" + this.getIngName() + "'";

            resultSet = dbConn.getResult(query, connection);

            if (resultSet.next()) {
                this.setIngID(Integer.parseInt(resultSet.getString(1)));
            }
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Exception : " + e);
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
    /* end of getIngIDFromIngName method */

    /* start of updateStockQty method */
    public boolean updateStockQty() {
        boolean updated = false;
        Connection connection = null;
        ResultSet resultSet = null;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = Calendar.getInstance().getTime();
        dateFormat.format(currentDate);
        Timestamp date = new Timestamp(currentDate.getTime());

        User updatedUser = new User();
        updatedUser.getIDByUsername();
        connection = dbConn.setConnection();
        this.getIngIDFromIngName();
        String query = "INSERT INTO ingredientstockhistory VALUES ('0','" + this.getIngID() + "','" + date + "','" + this.getOldStockQty() + "','" + this.getUpdatedStockQTy() + "','" + this.getStockUpdateReason() + "','" + updatedUser.getUserID() + "')";
        int i = dbConn.updateResult(query, connection);
        if (i == 1) {
            query = "UPDATE ingredient SET visibleStock = '" + this.getVisibleStock() + "' WHERE ingID = '" + this.getIngID() + "'";

            i = dbConn.updateResult(query, connection);

            if (i == 1) {
                updated = true;
            }
        }
        return updated;
    }
    /* end of updateStockQty method */

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
            System.err.println("err : " + e);
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

    //start of update ingredient method
    public int deleteIngredient(int ingredientID) throws SQLException {
        Connection connection = dbConn.setConnection();
        ResultSet resultSet = null;
        int ingUsed = 0;
        Statement statement;

        //set name of the ingredient
        String query = "SELECT ingID from recipie WHERE recipie.ingID ='" + ingredientID + "' ";
        try {
            resultSet = dbConn.getResult(query, connection);
            if (resultSet.next()) {
                ingUsed = 1;
            }

            if (ingUsed == 0) {
                String queryDelete = "DELETE FROM ingredient WHERE ingredient.ingID = '" + ingredientID + "' ";
                statement = connection.createStatement();
                int insertOK = statement.executeUpdate(queryDelete);
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
        System.out.println("ingUsed " + ingUsed);
        return ingUsed;
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
        ResultSet rs2 = dbConn.getResult(query1, connection);

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

     //end of update ingredient method
    
    
}
