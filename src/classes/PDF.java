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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vihanga Liyanage
 */
public class PDF {
    private String font = "Calibri";

    public PDF() {
    }
    
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
        cell.setPadding(2);
        cell.setPaddingBottom(3);
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
    
    public void generateMasterPlanPDF(DefaultTableModel model){
         try {
            Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter.getInstance(doc, new FileOutputStream("test.pdf"));
            doc.open();
            
            float[] coloumWidths = {5, 3, 3, 2.2f, 5};
            PdfPTable masterTable = new PdfPTable(coloumWidths);
            masterTable.setWidthPercentage(100);
        
            //Adding logo
            PdfPCell logoCell = new PdfPCell(Image.getInstance("D:\\Developer\\My Projects\\TeaELI\\TeaELI\\src\\teaeli\\logo-new (Custom).png"));
            logoCell.setColspan(2);
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
            headerTable.addCell(getHeaderDataCell("RM_MSTR-025"));
            headerTable.addCell(getHeaderNameCell("PRODUCTION PLAN(S) #"));
            headerTable.addCell(getHeaderDataCell("TCAD_008"));
            headerTable.addCell(getHeaderNameCell("DATE"));
            headerTable.addCell(getHeaderDataCell("03.08.2015"));
            headerTable.addCell(getHeaderNameCell("Client"));
            headerTable.addCell(getHeaderDataCell(""));
            headerTable.addCell(getHeaderNameCell("Invoice Ref"));
            headerTable.addCell(getHeaderDataCell("SAMPLES"));
            
            PdfPCell headerDataCell = new PdfPCell(headerTable);
            headerDataCell.setColspan(3);
            masterTable.addCell(headerDataCell);
                 
            //Adding master table headers
            masterTable.addCell(getTableHeaderCell("Item Description"));
            masterTable.addCell(getTableHeaderCell("Qty needed (g)"));
            masterTable.addCell(getTableHeaderCell("Qty in stock (g)"));
            masterTable.addCell(getTableHeaderCell("Balance (g)"));
            masterTable.addCell(getTableHeaderCell("Supplier Name"));
            
            //Adding category
            masterTable.addCell(getIngCategoryCell("HERBS"));
            
            for(int i=0; i<100; i++){
                masterTable.addCell(getTableDataCell("data " + i));
            }
            doc.add(masterTable);
            
            
            doc.close();
            //Opening the new file
            if ((new File("test.pdf")).exists()) {
                Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler test.pdf");
                p.waitFor();
            }
        
        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        }
    }
}
