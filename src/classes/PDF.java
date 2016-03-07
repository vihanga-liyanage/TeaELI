/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Vihanga Liyanage
 */
public class PDF {
    private String font = "Segoe UI Semilight";
    private String path = "C:\\Teaeli\\";

    public PDF() {
    }
    //private methods ==========================================================
    private PdfPCell getTableHeaderCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 10, Font.BOLD, BaseColor.WHITE)));
        cell.setPadding(5);
        cell.setPaddingBottom(7);
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        return cell;
    }
    
    private PdfPCell getIngCategoryCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 9, Font.BOLD, BaseColor.WHITE)));
        cell.setPadding(2);
        cell.setPaddingBottom(3);
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setColspan(100);
        return cell;
    }
    
    private PdfPCell getTableDataCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 9, Font.NORMAL, BaseColor.BLACK)));
        cell.setPadding(3);
        cell.setPaddingBottom(4);
        cell.setBackgroundColor(new BaseColor(242, 242, 242));
        return cell;
    }
    
    private PdfPCell getHeaderNameCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 9, Font.BOLD, BaseColor.BLACK)));
        cell.setPadding(2);
        cell.setPaddingBottom(3);
        cell.setBackgroundColor(new BaseColor(242, 242, 242));
        return cell;
    }
    
    private PdfPCell getHeaderDataCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 9, Font.BOLD, BaseColor.BLACK)));
        cell.setPadding(2);
        cell.setPaddingBottom(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }
    //end of private methods ===================================================
    
    public void generateMasterPlanPDF(JTable table, String[] data){
         try {
            Document doc = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
            
            //Creating the directory for the order
            String tempPath = path + "RM-Orders\\" + data[0] + "\\";
            new File(tempPath).mkdirs();

            PdfWriter.getInstance(doc, new FileOutputStream(tempPath + "RM_Order_Master_Plan-" + data[0] + ".pdf"));
            doc.open();
            
            float[] coloumWidths = {5, 2, 2, 2, 2, 2, 2, 5};
            PdfPTable masterTable = new PdfPTable(coloumWidths);
            masterTable.setWidthPercentage(100);
        
            //Adding logo
            PdfPCell logoCell = new PdfPCell(Image.getInstance("C:\\Teaeli\\Logos\\logo-new (Custom).png"));
            logoCell.setColspan(3);
            masterTable.addCell(logoCell);
            
            //Adding master plan header data as another table
            coloumWidths = new float[]{5.2f, 5};
            PdfPTable headerTable = new PdfPTable(coloumWidths);
            PdfPCell titleCell = new PdfPCell(new Paragraph("RAW MATERIAL ORDERING MASTER PLAN", FontFactory.getFont(font, 11, Font.BOLD)));
            titleCell.setPadding(15);
            titleCell.setColspan(10);
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerTable.addCell(titleCell);
            headerTable.addCell(getHeaderNameCell("MASTER PLAN #"));
            headerTable.addCell(getHeaderDataCell(data[0]));
            headerTable.addCell(getHeaderNameCell("DATE"));
            headerTable.addCell(getHeaderDataCell(data[1]));
            headerTable.addCell(getHeaderNameCell("Client"));
            headerTable.addCell(getHeaderDataCell(""));
            headerTable.addCell(getHeaderNameCell("Invoice Ref"));
            headerTable.addCell(getHeaderDataCell("SAMPLES"));
            
            PdfPCell headerDataCell = new PdfPCell(headerTable);
            headerDataCell.setColspan(5);
            masterTable.addCell(headerDataCell);
                 
            //Adding master table headers
            masterTable.addCell(getTableHeaderCell("Item Description"));
            masterTable.addCell(getTableHeaderCell("Qty Needed"));
            masterTable.addCell(getTableHeaderCell("Visible Stock"));
            masterTable.addCell(getTableHeaderCell("Invisible Stock"));
            masterTable.addCell(getTableHeaderCell("Balance"));
            masterTable.addCell(getTableHeaderCell("Excess Qty"));
            masterTable.addCell(getTableHeaderCell("Final Order"));
            masterTable.addCell(getTableHeaderCell("Supplier Name"));
            
            //Adding data from master table
            String category = table.getValueAt(0, 8).toString();
            masterTable.addCell(getIngCategoryCell(category.toUpperCase()));
            
            for(int i=0; i<table.getRowCount(); i++){
                if (!category.equals(table.getValueAt(i, 8).toString())){
                    category = table.getValueAt(i, 8).toString();
                    masterTable.addCell(getIngCategoryCell(category.toUpperCase()));
                }
                masterTable.addCell(getTableDataCell(table.getValueAt(i, 0).toString()));
                masterTable.addCell(getTableDataCell(table.getValueAt(i, 1).toString()));
                masterTable.addCell(getTableDataCell(table.getValueAt(i, 2).toString()));
                masterTable.addCell(getTableDataCell(table.getValueAt(i, 3).toString()));
                masterTable.addCell(getTableDataCell(table.getValueAt(i, 4).toString()));
                masterTable.addCell(getTableDataCell(table.getValueAt(i, 5).toString()));
                masterTable.addCell(getTableDataCell(table.getValueAt(i, 6).toString()));
                masterTable.addCell(getTableDataCell(table.getValueAt(i, 7).toString()));
            }
            doc.add(masterTable);

            doc.close();
            
            //Opening the new directory
            Desktop.getDesktop().open(new File(tempPath));
        
        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        }
    }
    
}
