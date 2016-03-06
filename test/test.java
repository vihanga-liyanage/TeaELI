
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
import javax.swing.JOptionPane;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vihanga Liyanage
 */
public class test {

    public static String font = "Calibri";
    public static void main(String[] args) throws IOException {
        


            //Opening the new file
            Desktop.getDesktop().open(new File("C:\\"));

        
        
    }
    
    //required methods
    private static PdfPCell getTableHeaderCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 10, Font.BOLD, BaseColor.WHITE)));
        cell.setPadding(5);
        cell.setPaddingBottom(7);
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        return cell;
    }
    
    private static PdfPCell getIngCategoryCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 9, Font.BOLD, BaseColor.WHITE)));
        cell.setPadding(2);
        cell.setPaddingBottom(3);
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setColspan(100);
        return cell;
    }
    
    private static PdfPCell getTableDataCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 9, Font.NORMAL, BaseColor.BLACK)));
        cell.setPadding(2);
        cell.setPaddingBottom(3);
        cell.setBackgroundColor(new BaseColor(242, 242, 242));
        return cell;
    }
    
    private static PdfPCell getHeaderNameCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 9, Font.BOLD, BaseColor.BLACK)));
        cell.setPadding(2);
        cell.setPaddingBottom(3);
        cell.setBackgroundColor(new BaseColor(242, 242, 242));
        return cell;
    }
    
    private static PdfPCell getHeaderDataCell(String name){
        PdfPCell cell = new PdfPCell(new Paragraph(name, FontFactory.getFont(font, 9, Font.BOLD, BaseColor.BLACK)));
        cell.setPadding(2);
        cell.setPaddingBottom(3);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }
}
