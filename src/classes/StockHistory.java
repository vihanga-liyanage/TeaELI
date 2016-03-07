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

        Connection connection = null;
        ResultSet resultSet = null;

        try {
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

        } catch (Exception e) {
            System.err.println("stckhis 96 err : " + e);
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
    public void populateStockBlendHistoryTable(DefaultTableModel tableModel) {

        Connection connection = null;
        ResultSet resultSet = null;

        try {
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
    /* end of populateStockBlendHistoryTable method */

    public void populateStockIngredientHistoryTableByDate(DefaultTableModel tableModel, String startdate, String enddate) {

        Connection connection = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT S.date,I.ingName,S.oldQty,S.updatedQty,S.reason,U.username FROM ingredient I, ingredientstockhistory S, user U WHERE S.updatedBy=U.userID AND S.ingID=I.ingID AND s.date BETWEEN '" + startdate + "' AND '" + enddate + "'";

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

    public void IngStockHistoryPdfGeneration() {
        AdminPannel admin = new AdminPannel();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy:MM:dd");
        String today = sdf2.format(date);
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        
        SimpleDateFormat javadate = new SimpleDateFormat("yyyy-MM-dd");
        Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED);
        Font smallItalic = new Font(Font.FontFamily.HELVETICA, 8,Font.ITALIC);
        /*Date start = StartDate.getDate();
         Date end = EndDate.getDate();
         String startdate = javadate.format(start);
         String enddate = javadate.format(end);*/
        try {
            String reportdate = today.replace(":", "_");
            //New PDF File will be created as ACCReport2016_01_01 //today's date
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Teaeli\\Ingredient Stock History\\" + reportdate + "" + ".pdf"));
            document.open();
            Image image2 = Image.getInstance("C:\\Teaeli\\Logos\\logo-new (Custom).png");
            document.add(image2);
            Paragraph paragraph1 = new Paragraph("leafspice (pvt)Ltd.\nAddress: 1/52, Galle Road,Colombo 03.\nT.P:0112552225\n\n");
            document.add(paragraph1);
            Paragraph paragraph2 = new Paragraph("Date : " + today + "", FontFactory.getFont(FontFactory.HELVETICA, 12));
            document.add(paragraph2);
            paragraph1 = new Paragraph("                                Ingredient Stock History\n\n", FontFactory.getFont(FontFactory.HELVETICA, 16));
            document.add(paragraph1);
            //adding a table
            PdfPTable t = new PdfPTable(6);
            t.setSpacingBefore(5);
            t.setSpacingAfter(5);
            t.setWidthPercentage(100);
            t.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            t.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            t.getDefaultCell().setFixedHeight(70);
            

            t.addCell(new PdfPCell(new Phrase("Date",redFont)));
            t.addCell(new PdfPCell(new Phrase("Ingredient Name",redFont)));
            t.addCell(new PdfPCell(new Phrase("Old Qty",redFont)));
            t.addCell(new PdfPCell(new Phrase("Updated Qty",redFont)));
            t.addCell(new PdfPCell(new Phrase("Reason",redFont)));
            t.addCell(new PdfPCell(new Phrase("Updated By",redFont)));
            int rows = admin.ingStockHistoryTbl.getRowCount();

            for (int i = 0; i < rows; i++) {
                t.addCell(new PdfPCell(new Phrase(admin.ingStockHistoryTbl.getValueAt(i, 0) + "",smallItalic)));
                if (admin.ingStockHistoryTbl.getValueAt(i, 1) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.ingStockHistoryTbl.getValueAt(i, 1) + "",smallItalic)));
                }
                if (admin.ingStockHistoryTbl.getValueAt(i, 2) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.ingStockHistoryTbl.getValueAt(i, 2) + "",smallItalic)));
                }
                if (admin.ingStockHistoryTbl.getValueAt(i, 3) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.ingStockHistoryTbl.getValueAt(i, 3) + "",smallItalic)));
                }
                if (admin.ingStockHistoryTbl.getValueAt(i, 4) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.ingStockHistoryTbl.getValueAt(i, 4) + "",smallItalic)));
                }
                if (admin.ingStockHistoryTbl.getValueAt(i, 5) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.ingStockHistoryTbl.getValueAt(i, 5) + "",smallItalic)));
                }

            }
            document.add(t);

            if ((new File("C:\\Teaeli\\Ingredient Stock History\\" + reportdate + "" + ".pdf")).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler C:\\Teaeli\\Ingredient Stock History\\" + reportdate + "" + ".pdf");
                p.waitFor();
            }

        } catch (DocumentException | IOException | InterruptedException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(admin, "File already exists!!!");
        }
        document.close();
    }
    
    public void BlendStockHistoryPdfGeneration() {
        AdminPannel admin = new AdminPannel();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy:MM:dd");
        String today = sdf2.format(date);
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        SimpleDateFormat javadate = new SimpleDateFormat("yyyy-MM-dd");
        Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED);
        Font smallItalic = new Font(Font.FontFamily.HELVETICA, 8,Font.ITALIC);
        /*Date start = StartDate.getDate();
         Date end = EndDate.getDate();
         String startdate = javadate.format(start);
         String enddate = javadate.format(end);*/
        try {
            String reportdate = today.replace(":", "_");
            //New PDF File will be created as ACCReport2016_01_01 //today's date
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Teaeli\\Blend Stock History\\" + reportdate + "" + ".pdf"));
            document.open();
            Image image2 = Image.getInstance("C:\\Teaeli\\Logos\\logo-new (Custom).png");
            document.add(image2);
            Paragraph paragraph1 = new Paragraph("leafspice (pvt)Ltd.\nAddress: 1/52, Galle Road,Colombo 03.\nT.P:0112552225\n\n");
            document.add(paragraph1);
            Paragraph paragraph2 = new Paragraph("Date : " + today + "", FontFactory.getFont(FontFactory.HELVETICA, 12));
            document.add(paragraph2);
            paragraph1 = new Paragraph("                        Blend Stock History\n\n", FontFactory.getFont(FontFactory.HELVETICA, 16));
            document.add(paragraph1);
            //adding a table
            PdfPTable t = new PdfPTable(6);
            t.setSpacingBefore(5);
            t.setSpacingAfter(5);
            t.setWidthPercentage(100);
            t.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            t.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            t.getDefaultCell().setFixedHeight(70);

            t.addCell(new PdfPCell(new Phrase("Date",redFont)));
            t.addCell(new PdfPCell(new Phrase("Blend Name",redFont)));
            t.addCell(new PdfPCell(new Phrase("Old Qty",redFont)));
            t.addCell(new PdfPCell(new Phrase("Updated Qty",redFont)));
            t.addCell(new PdfPCell(new Phrase("Reason",redFont)));
            t.addCell(new PdfPCell(new Phrase("Updated By",redFont)));
            int rows = admin.blendStockHistoryTbl.getRowCount();

            for (int i = 0; i < rows; i++) {
                t.addCell(new PdfPCell(new Phrase(admin.blendStockHistoryTbl.getValueAt(i, 0) + "",smallItalic)));
                if (admin.blendStockHistoryTbl.getValueAt(i, 1) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.blendStockHistoryTbl.getValueAt(i, 1) + "",smallItalic)));
                }
                if (admin.blendStockHistoryTbl.getValueAt(i, 2) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.blendStockHistoryTbl.getValueAt(i, 2) + "",smallItalic)));
                }
                if (admin.blendStockHistoryTbl.getValueAt(i, 3) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.blendStockHistoryTbl.getValueAt(i, 3) + "",smallItalic)));
                }
                if (admin.blendStockHistoryTbl.getValueAt(i, 4) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.blendStockHistoryTbl.getValueAt(i, 4) + "",smallItalic)));
                }
                if (admin.blendStockHistoryTbl.getValueAt(i, 5) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(admin.blendStockHistoryTbl.getValueAt(i, 5) + "",smallItalic)));
                }

            }
            document.add(t);

            if ((new File("C:\\Teaeli\\Blend Stock History\\" + reportdate + "" + ".pdf")).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler C:\\Teaeli\\Blend Stock History\\" + reportdate + "" + ".pdf");
                p.waitFor();
            }

        } catch (DocumentException | IOException | InterruptedException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(admin, "File already exists!!!");
        }
        document.close();
    }
}
