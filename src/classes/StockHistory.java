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

        Connection connection = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT S.date,I.ingName,S.oldQty,S.updatedQty,S.reason,U.username FROM ingredient I, ingredientstockhistory S, user U WHERE S.updatedBy=U.userID AND S.ingID=I.ingID AND s.date BETWEEN '" + startdate + "' AND '" + enddate + "'";

            connection = dbConn.setConnection();
            resultSet = dbConn.getResult(query, connection);

            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "No Stock History during this Date range");
                return;     
            }
            tableModel.setRowCount(0);
            while (resultSet.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= 6; i++) {
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

    public void populateStockBlendHistoryTableByDate(DefaultTableModel tableModel, String startdate, String enddate) {

        Connection connection = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT S.date,B.blendName,S.oldQty,S.updatedQty,S.reason,U.username FROM blend B, blendstockhistory S, user U WHERE S.updatedBy=U.userID AND S.blendID=B.blendID AND S.date BETWEEN '" + startdate + "' AND '" + enddate + "'";

            connection = dbConn.setConnection();
            resultSet = dbConn.getResult(query, connection);
            
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "No Stock History during this Date range");
                return;     
            }
            
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= 6; i++) {
                    newRow.addElement(resultSet.getObject(i));
                }
                tableModel.addRow(newRow);
            }

        } catch (Exception e) {
            System.err.println("stckhis 139 err : " + e);
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
}
