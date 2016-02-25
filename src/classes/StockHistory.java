package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class StockHistory {
    private String name, reason, updatedBy;
    private Date date;
    private int oldQty, updatedQty;
    
    DBConnection dbConn = new DBConnection();

    public StockHistory() {
        this.name = "";
        this.reason = "";
        this.updatedBy = "";
        this.date = null;
        this.oldQty = 0;
        this.updatedQty = 0;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOldQty() {
        return oldQty;
    }

    public void setOldQty(int oldQty) {
        this.oldQty = oldQty;
    }

    public int getUpdatedQty() {
        return updatedQty;
    }

    public void setUpdatedQty(int updatedQty) {
        this.updatedQty = updatedQty;
    }
    
    /* start of populateStockIngredientHistoryTable method */
    public void populateStockIngredientHistoryTable(DefaultTableModel tableModel){
        
        Connection connection = null;
        ResultSet resultSet = null;
        
        try{
            String query = "SELECT S.date,I.ingName,S.oldQty,S.updatedQty,S.reason,U.username FROM ingredient I, ingredientstockhistory S, user U WHERE S.updatedBy=U.userID AND S.ingID=I.ingID";
            
            connection = dbConn.setConnection();
            resultSet = dbConn.getResult(query, connection);
            
            tableModel.setRowCount(0);
            
            while (resultSet.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= 6; i++) {
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
    /* end of populateStockIngredientHistoryTable method */
    
    /* start of populateStockBlendHistoryTable method */
    public void populateStockBlendHistoryTable(DefaultTableModel tableModel){
        
        Connection connection = null;
        ResultSet resultSet = null;
        
        try{
            String query = "SELECT S.date,B.blendName,S.oldQty,S.updatedQty,S.reason,U.username FROM blend B, blendstockhistory S, user U WHERE S.updatedBy=U.userID AND S.blendID=B.blendID";
            
            connection = dbConn.setConnection();
            resultSet = dbConn.getResult(query, connection);
            
            tableModel.setRowCount(0);
            
            while (resultSet.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= 6; i++) {
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
    /* end of populateStockBlendHistoryTable method */
}
