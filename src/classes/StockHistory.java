package classes;

import com.itextpdf.text.BaseColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import teaeli.AdminPannel;

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
    public void populateStockIngredientHistoryTable(DefaultTableModel tableModel) {
        String date;
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
    /* end of populateStockIngredientHistoryTable method */

    /* start of populateStockBlendHistoryTable method */
    public void populateStockBlendHistoryTable(DefaultTableModel tableModel) {
        String date;
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
        String date;
        ResultArray res = null;

        String query = "SELECT S.date,I.ingName,S.oldQty,S.updatedQty,S.reason,U.username FROM ingredient I, "
                + "ingredientstockhistory S, user U WHERE S.updatedBy=U.userID AND S.ingID=I.ingID AND "
                + "s.date BETWEEN '" + startdate + "' and '" + enddate + "'";
        
        res = dbConn.getResultArray(query);
        if(res.size()==0){
            JOptionPane.showMessageDialog(null, "No records during this date range");
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
        String date;
        ResultArray res = null;

        String query = "SELECT S.date,I.ingName,S.oldQty,S.updatedQty,S.reason,U.username FROM ingredient I, ingredientstockhistory S,"
                + " user U WHERE S.updatedBy=U.userID AND S.ingID=I.ingID AND s.dateS.date Between '" + startdate + "' AND '"+endDate+"'";
        
        res = dbConn.getResultArray(query);
        if(res.size()==0){
            JOptionPane.showMessageDialog(null, "No records during this date range");
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
        String date;
        ResultArray res = null;

        String query = "SELECT S.date,B.blendName,S.oldQty,S.updatedQty,S.reason,U.username FROM blend B, "
                + "blendstockhistory S, user U WHERE S.updatedBy=U.userID AND "
                + "S.blendID=B.blendID AND S.date Between '" + startdate + "' and  '" + enddate + "'";
        
        res = dbConn.getResultArray(query);
        if(res.size()==0){
            JOptionPane.showMessageDialog(null, "No records during this date range");
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

        String query = "SELECT S.date,B.blendName,S.oldQty,S.updatedQty,S.reason,U.username FROM blend B, blendstockhistory S, "
                + "user U WHERE S.updatedBy=U.userID AND S.blendID=B.blendID AND S.date Between '" + startdate + "' AND '"+endDate+"'";
        
        res = dbConn.getResultArray(query);
        
        if(res.size()==0){
            JOptionPane.showMessageDialog(null, "No records during this date range");
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
