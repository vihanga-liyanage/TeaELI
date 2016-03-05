/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teaeli;

import classes.Ingredient;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import teaeli.CreateNewBlendOrder2;

/**
 *
 * @author Janith
 */
public class OrderConfirmation extends javax.swing.JFrame {

    public Object pannel;
    Ingredient ing = new Ingredient();
    List<List> mainList;
    List<List> mainList2;
    Set<String> supplierList;

    /**
     * Creates new form OrderConfirmation
     */
    public OrderConfirmation(CreateNewBlendOrder2 cnb) { // pass CreateNewBlendOrder2 object to get the master list in the interface
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //set supplier names in the table
        supplierList = getSupplierNames(cnb);
        DefaultTableModel model = (DefaultTableModel) tblMasterPlanEditingView.getModel();
        for (String supplierName : supplierList) {
            model.addRow(new Object[]{supplierName, 0.00, 0.00});
        }
        //group details supplierwise
        final List<List> mainList = groupDetailsSupplierwise(supplierList, cnb);

        //method for view supplierwise details when supplier name is seleced in the table
        final ListSelectionModel selectionalModForSupplierwiseTable = tblMasterPlanEditingView.getSelectionModel();
        selectionalModForSupplierwiseTable.addListSelectionListener(new ListSelectionListener() {
            DefaultTableModel tpmodel = (DefaultTableModel) tblMasterPlanEditingView1.getModel();

            @Override
            public void valueChanged(ListSelectionEvent lsevt) {
                tpmodel.setRowCount(0);
                if (!selectionalModForSupplierwiseTable.isSelectionEmpty()) {
                    int row = selectionalModForSupplierwiseTable.getMinSelectionIndex();
                    int rowid = tblMasterPlanEditingView.getSelectedRow();
                    viewPOOrder(rowid, mainList, tpmodel);
                }
            }

        });

        /*
         String [][] supplierwiseDetails = new String [numOfInsideArrays][3];
         for(int i = 0; i<numOfInsideArrays ; i++ ){
         for(int j =0 ; j<3 ; j++){
                            
         }
         }*/
        /*
         //creaate a object of a master table         
         DefaultTableModel model = (DefaultTableModel) cnb.masterPlanTbl.getModel();
         for (int i = 0; i < model.getRowCount(); i++) {
         System.out.println(model.getValueAt(i, 7).toString());
            
         }*/
    }

    private OrderConfirmation() {
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    //get distinct supplier list from master table
    public Set<String> getSupplierNames(CreateNewBlendOrder2 cnb) {
        String[] suppliers = null;

        DefaultTableModel model = (DefaultTableModel) cnb.masterPlanTbl.getModel();
        Set<String> set = new HashSet<String>();
        String supName;
        for (int i = 0; i < model.getRowCount(); i++) {
            supName = model.getValueAt(i, 7).toString();
            set.add(supName);
        }
        TreeSet sortedSet = new TreeSet<String>(set);
        System.out.println(set);
        System.out.println(sortedSet);

        return sortedSet;
    }

    public List<List> groupDetailsSupplierwise(Set<String> supplierList, CreateNewBlendOrder2 cnb) {
        //create arrays for the suppliers 
        mainList = new ArrayList<List>();
        DefaultTableModel masterTableModel = (DefaultTableModel) cnb.masterPlanTbl.getModel();
        for (String supName : supplierList) {
            List<String> supwiseDetails = new ArrayList<String>();
            for (int i = 0; i < masterTableModel.getRowCount(); i++) {
                String supNameFromMaster = masterTableModel.getValueAt(i, 7).toString();
                if (supName.equals(supNameFromMaster)) {
                    supwiseDetails.add(masterTableModel.getValueAt(i, 0).toString());
                    supwiseDetails.add(masterTableModel.getValueAt(i, 6).toString());

                }
            }
            mainList.add(supwiseDetails);
        }
        return mainList;
    }

    public void viewPOOrder(int rowid, List<List> mainList, DefaultTableModel tpmodel) {
        List lst = mainList.get(rowid);

        for (int i = 0; i < lst.size(); i++) {
            if (i % 2 == 0) {
                float unitPrice = parseFloat(ing.getUnitPriceByIngName(lst.get(i).toString()));
                float qty = parseFloat(lst.get(i + 1).toString());
                float total = (qty / 1000) * unitPrice;
                tpmodel.addRow(new Object[]{lst.get(i), lst.get(i + 1), unitPrice, total});

            }
        }

    }

    public List<List> setUnitPriceAndTotal() {
        mainList2 = new ArrayList<List>();

        for (List lst : this.mainList) {
            List<String> unitPriceAndTotalList = new ArrayList<String>();
            for (int i = 0; i < lst.size(); i++) {

                if (i % 2 == 0) {
                    float unitPrice = parseFloat(ing.getUnitPriceByIngName(lst.get(i).toString()));
                    float qty = parseFloat(lst.get(i + 1).toString());
                    float total = (qty / 1000) * unitPrice;
                    unitPriceAndTotalList.add(Float.toString(unitPrice));
                    unitPriceAndTotalList.add(Float.toString(total));
                }
            }
            mainList2.add(unitPriceAndTotalList);
        }

        return mainList2;
    }

    //overiding Float.parseFloat() to accept nums with commas
    private float parseFloat(String num) {
        try {
            return Float.parseFloat(num);
        } catch (NumberFormatException e) {
            if (num.matches("[[0-9]{1,2}+,]*.[0-9]*")) {
                num = num.replace(",", "");
                return Float.parseFloat(num);
            }
        }
        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tblMasterPlanScrollPane = new javax.swing.JScrollPane();
        tblMasterPlanEditingView = new javax.swing.JTable();
        tblMasterPlanScrollPane1 = new javax.swing.JScrollPane();
        tblMasterPlanEditingView1 = new javax.swing.JTable();
        generatePdfBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Order Confirmation");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Order Confirmation ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 16))); // NOI18N

        tblMasterPlanEditingView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier Name", "Discount", "Tax"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMasterPlanEditingView.setRowHeight(24);
        tblMasterPlanEditingView.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblMasterPlanEditingViewPropertyChange(evt);
            }
        });
        tblMasterPlanScrollPane.setViewportView(tblMasterPlanEditingView);
        if (tblMasterPlanEditingView.getColumnModel().getColumnCount() > 0) {
            tblMasterPlanEditingView.getColumnModel().getColumn(0).setMinWidth(25);
            tblMasterPlanEditingView.getColumnModel().getColumn(1).setMinWidth(70);
            tblMasterPlanEditingView.getColumnModel().getColumn(1).setPreferredWidth(10);
            tblMasterPlanEditingView.getColumnModel().getColumn(1).setMaxWidth(70);
            tblMasterPlanEditingView.getColumnModel().getColumn(2).setMinWidth(70);
            tblMasterPlanEditingView.getColumnModel().getColumn(2).setPreferredWidth(10);
            tblMasterPlanEditingView.getColumnModel().getColumn(2).setMaxWidth(70);
        }

        tblMasterPlanEditingView1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ingridient", "Quantity", "Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMasterPlanEditingView1.setRowHeight(24);
        tblMasterPlanEditingView1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblMasterPlanEditingView1PropertyChange(evt);
            }
        });
        tblMasterPlanScrollPane1.setViewportView(tblMasterPlanEditingView1);
        if (tblMasterPlanEditingView1.getColumnModel().getColumnCount() > 0) {
            tblMasterPlanEditingView1.getColumnModel().getColumn(1).setMinWidth(80);
            tblMasterPlanEditingView1.getColumnModel().getColumn(1).setPreferredWidth(2);
            tblMasterPlanEditingView1.getColumnModel().getColumn(1).setMaxWidth(80);
            tblMasterPlanEditingView1.getColumnModel().getColumn(2).setMinWidth(80);
            tblMasterPlanEditingView1.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblMasterPlanEditingView1.getColumnModel().getColumn(2).setMaxWidth(80);
            tblMasterPlanEditingView1.getColumnModel().getColumn(3).setMinWidth(80);
            tblMasterPlanEditingView1.getColumnModel().getColumn(3).setPreferredWidth(20);
            tblMasterPlanEditingView1.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        generatePdfBtn.setText("Generate PDF");
        generatePdfBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePdfBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Supplier-wise order details");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel3.setText("Suppliers");
        jLabel3.setMaximumSize(new java.awt.Dimension(31, 14));
        jLabel3.setMinimumSize(new java.awt.Dimension(31, 14));
        jLabel3.setPreferredSize(new java.awt.Dimension(31, 14));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Click on each supplier to view order details");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(generatePdfBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tblMasterPlanScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tblMasterPlanScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tblMasterPlanScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addComponent(tblMasterPlanScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(generatePdfBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblMasterPlanEditingViewPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblMasterPlanEditingViewPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMasterPlanEditingViewPropertyChange

    private void tblMasterPlanEditingView1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblMasterPlanEditingView1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMasterPlanEditingView1PropertyChange

    private void generatePdfBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePdfBtnActionPerformed
        //generating pdfs
        setUnitPriceAndTotal();
        int count = 0;
        String[] supNameArray = this.supplierList.toArray(new String[this.supplierList.size()]);
        for (List lst : this.mainList) {
            int listCounter = 0;
            Document doc = new Document();
            try {

                String suppName = supNameArray[count];
                String fileName = suppName.concat(".pdf");
                PdfWriter.getInstance(doc, new FileOutputStream(fileName));
            } catch (DocumentException ex) {
                //Logger.getLogger(OrderConfirmation.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("DocumentException : " + ex);
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(OrderConfirmation.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("FileNotFoundException : " + ex);
            }
            doc.open();
            try {
                doc.add(new Paragraph("test doc"));
                PdfPTable table = new PdfPTable(4);
                table.addCell("Ingredient Name");
                table.addCell("Quantity");
                table.addCell("Price");
                table.addCell("total");

                List lst2 = mainList2.get(count);
                for (int i = 0; i < lst.size(); i++) {
                    table.addCell(lst.get(i).toString());
                    table.addCell(lst2.get(i).toString());
                }
                /*
                for (int j = 0; j < lst.size(); j++) {
                    
                }*/
                

                doc.add(table);
            } catch (DocumentException ex) {
                //Logger.getLogger(OrderConfirmation.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("DocumentException : " + ex);
            }
            doc.close();
            count++;

        }

        /*
         //Re-generating the admin panel since the data is changed
         if ("teaeli.AdminPannel".equals(pannel.getClass().getName())) {
         AdminPannel adminPannel = new AdminPannel();
         adminPannel.setVisible(true);
         AdminPannel old = (AdminPannel) pannel;
         old.dispose();
         } else if ("teaeli.ManagerPannel".equals(pannel.getClass().getName())) {
         ManagerPannel managerPannel = new ManagerPannel();
         managerPannel.setVisible(true);
         ManagerPannel old = (ManagerPannel) pannel;
         old.dispose();
         }
         this.dispose();
         */
    }//GEN-LAST:event_generatePdfBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderConfirmation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generatePdfBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTable tblMasterPlanEditingView;
    public javax.swing.JTable tblMasterPlanEditingView1;
    public javax.swing.JScrollPane tblMasterPlanScrollPane;
    public javax.swing.JScrollPane tblMasterPlanScrollPane1;
    // End of variables declaration//GEN-END:variables
}
