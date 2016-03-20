package classes;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class StockHistory {

    DBConnection dbConn = new DBConnection();

    public StockHistory() { }

    /* start of populateStockIngredientHistoryTable method */
    public void populateStockIngredientHistoryTable(DefaultTableModel tableModel) {
        ResultArray res = null;

        String query = "SELECT S.date,I.ingName,S.oldQty,S.updatedQty,S.reason,U.username "
                + "FROM ingredient I INNER JOIN ingredientstockhistory S ON S.ingID=I.ingID INNER JOIN user U ON S.updatedBy=U.userID;";

        res = dbConn.getResultArray(query);
        tableModel.setRowCount(0);

        while (res.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 5; i++) {
                newRow.addElement(res.getString(i));
            }

            tableModel.addRow(newRow);
        }
    }

    /* start of populateStockBlendHistoryTable method */
    public void populateStockBlendHistoryTable(DefaultTableModel tableModel) {
        ResultArray res = null;

        String query = "SELECT S.date,B.blendName,S.oldQty,S.updatedQty,S.reason,U.username "
                + "FROM blend B, blendstockhistory S, user U "
                + "WHERE S.updatedBy=U.userID AND S.blendID=B.blendID";

        res = dbConn.getResultArray(query);
        tableModel.setRowCount(0);

        while (res.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 5; i++) {
                newRow.addElement(res.getString(i));
            }
            tableModel.addRow(newRow);
        }
    }
    /* end of populateStockBlendHistoryTable method */

    public void populateStockIngredientHistoryTableByDate(DefaultTableModel tableModel, String startdate, String enddate) {
        ResultArray res = null;

        String query = "SELECT S.date,I.ingName,S.oldQty,S.updatedQty,S.reason,U.username "
                + "FROM ingredient I, ingredientstockhistory S, user U "
                + "WHERE S.updatedBy=U.userID AND S.ingID=I.ingID AND s.date BETWEEN '" + startdate + "' and '" + enddate + "'";
        
        res = dbConn.getResultArray(query);
        if(res.size()==0){
            JOptionPane.showMessageDialog(null, "No records to show in this date range","No Records",0);
            return;
        }
        tableModel.setRowCount(0);

        while (res.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 5; i++) {
                newRow.addElement(res.getString(i));
            }
            tableModel.addRow(newRow);
        }
    }

    public void populateStockIngredientHistoryTableForDate(DefaultTableModel tableModel, String startdate,String endDate) {
        ResultArray res = null;

        String query = "SELECT S.date,I.ingName,S.oldQty,S.updatedQty,S.reason,U.username "
                + "FROM ingredient I, ingredientstockhistory S, user U "
                + "WHERE S.updatedBy=U.userID AND S.ingID=I.ingID AND s.dateS.date Between '" + startdate + "' AND '"+endDate+"'";
        
        res = dbConn.getResultArray(query);
        if(res.size()==0){
            JOptionPane.showMessageDialog(null, "No records to show in this date range","No Records",0);
            return;
        }
        tableModel.setRowCount(0);

        while (res.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 5; i++) {
                newRow.addElement(res.getString(i));
            }
            tableModel.addRow(newRow);
        }
    }
    
    public void populateStockBlendHistoryTableByDate(DefaultTableModel tableModel, String startdate, String enddate) {
        ResultArray res = null;

        String query = "SELECT S.date,B.blendName,S.oldQty,S.updatedQty,S.reason,U.username FROM blend B, "
                + "blendstockhistory S, user U WHERE S.updatedBy=U.userID AND "
                + "S.blendID=B.blendID AND S.date Between '" + startdate + "' and  '" + enddate + "'";
        
        res = dbConn.getResultArray(query);
        if(res.size()==0){
            JOptionPane.showMessageDialog(null, "No records to show in this date range","No Records",0);
            return;
        }
        tableModel.setRowCount(0);

        while (res.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 5; i++) {
                newRow.addElement(res.getString(i));
            }
            tableModel.addRow(newRow);
        }
    }

    public void populateStockBlendHistoryTableForDate(DefaultTableModel tableModel, String startdate,String endDate) {
        String date;
        ResultArray res = null;

        String query = "SELECT S.date,B.blendName,S.oldQty,S.updatedQty,S.reason,U.username "
                + "FROM blend B, blendstockhistory S, user U "
                + "WHERE S.updatedBy=U.userID AND S.blendID=B.blendID AND S.date Between '" + startdate + "' AND '"+endDate+"'";
        
        res = dbConn.getResultArray(query);
        
        if(res.size()==0){
            JOptionPane.showMessageDialog(null, "No records to show in this date range","No Records",0);
            return;
        }
        tableModel.setRowCount(0);
        while (res.next()) {
            Vector newRow = new Vector();
            for (int i = 0; i <= 5; i++) {
                newRow.addElement(res.getString(i));
                
            }
            tableModel.addRow(newRow);
            
        }
    }
}
