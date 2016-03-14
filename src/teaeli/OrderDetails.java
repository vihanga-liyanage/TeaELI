/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teaeli;

import classes.Blend;
import classes.Ingredient;
import classes.Order;
import classes.PDF;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Vihanga Liyanage
 */
public class OrderDetails extends javax.swing.JFrame  {
    Order order = new Order();
    private AdminPannel adminPannel;
    private PDF pdf;

    public void setAdminPannel(AdminPannel adminPannel) {
        this.adminPannel = adminPannel;
    }
    
    public OrderDetails() {
        //Add windows look and feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AdminPannel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        
        //Changing table headers to bold
        blendTable.getTableHeader().setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        orderDetailsTable.getTableHeader().setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        
        /*Dimension screenSize,frameSize;
        int x,y;
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frameSize=getSize();
        x=(screenSize.width-frameSize.width)/4;
        y=(screenSize.height-frameSize.height)/4;
        setLocation(x, y);
        setResizable(false);*/
        
        //Adding listner to prompt confirmation on window close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel viewing the order", "Confirm window close",JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        pdf = new PDF();
        
        //enabling sorting for tables
        blendTable.setAutoCreateRowSorter(true);
        orderDetailsTable.setAutoCreateRowSorter(true);
        
        //Hiding the ingredient category column from the orderDetailsTable
        orderDetailsTable.removeColumn(orderDetailsTable.getColumn(orderDetailsTable.getColumnName(8)));
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
        rawMaterialLbl = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        orderDetailsTable = new javax.swing.JTable();
        blendLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        blendTable = new javax.swing.JTable();
        updateOrderBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        orderReceivedBtn = new javax.swing.JButton();
        orderCompletedBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        orderIDLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        dateLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Order Details ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 16))); // NOI18N

        rawMaterialLbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        rawMaterialLbl.setText("Raw Material Details");

        jScrollPane3.setPreferredSize(new java.awt.Dimension(620, 620));

        orderDetailsTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        orderDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Ingredient", "Required Qty (g)", "Visible Stock (g)", "Invisible Stock (g)", "Balance", "Excess Qty (g)", "Final Order", "Supplier", "Category", "Additional Qty (g)", "Remarks"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderDetailsTable.setRowHeight(24);
        jScrollPane3.setViewportView(orderDetailsTable);
        if (orderDetailsTable.getColumnModel().getColumnCount() > 0) {
            orderDetailsTable.getColumnModel().getColumn(0).setResizable(false);
            orderDetailsTable.getColumnModel().getColumn(0).setPreferredWidth(200);
            orderDetailsTable.getColumnModel().getColumn(7).setPreferredWidth(200);
        }

        blendLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        blendLabel.setText("Blends");

        blendTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        blendTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Blend Code", "Blend Name", "Required Qty (g)", "Visible Stock (g)", "Invisible Stock (g)", "Balance", "Excess Qty (g)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        blendTable.setRowHeight(24);
        jScrollPane1.setViewportView(blendTable);

        updateOrderBtn.setText("Update Order");
        updateOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateOrderBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        orderReceivedBtn.setText("Order Received");
        orderReceivedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderReceivedBtnActionPerformed(evt);
            }
        });

        orderCompletedBtn.setText("Order Completed");
        orderCompletedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderCompletedBtnActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        orderIDLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        orderIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        orderIDLabel.setText("0001");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Order No :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(orderIDLabel)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dateLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dateLabel.setText("Feb 18, 2016");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Placed Date :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderCompletedBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderReceivedBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateOrderBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(blendLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rawMaterialLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(blendLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rawMaterialLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateOrderBtn)
                    .addComponent(cancelBtn)
                    .addComponent(orderReceivedBtn)
                    .addComponent(orderCompletedBtn))
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

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel viewing the order", "Confirm window close", dialogButton);
        if (a == JOptionPane.YES_OPTION){
            dispose();
        }
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void updateOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateOrderBtnActionPerformed
        String tempPath = "C:\\Teaeli\\RM-Orders\\" + orderIDLabel.getText() + "\\RM_Order_Master_Plan-" + orderIDLabel.getText() + ".pdf";
        File file = new File(tempPath);
        File sameFileName = new File(tempPath);
        if (!file.renameTo(sameFileName)) {
            JOptionPane.showMessageDialog(this, "The file you are going to update is already opened!!!\nPlease close the file before update the order", "File already open", 0);
            return;
        }
        
        try{
          orderDetailsTable.getCellEditor().stopCellEditing();  
        }catch(NullPointerException ex){
            
        }
        for(int i = 0; i < orderDetailsTable.getRowCount(); i++){
            double additional = 0;
            String str,remark = "";
            
            try{
                str = orderDetailsTable.getValueAt(i, 8).toString();
                remark = orderDetailsTable.getValueAt(i, 9).toString();
            }catch(NullPointerException e){
                str = "0";
                remark = "";
            }
            
            try{
                additional = Double.parseDouble(str);
                if(additional < 0){
                    JOptionPane.showMessageDialog(this, "<html>Please enter only positive numbers for the additional qty column at row number <b>" +(i+1)+ "</b>!</html>", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }catch(NumberFormatException ex){
                additional = 0;
            }
            
            String orderID = orderIDLabel.getText();           
            String ing = orderDetailsTable.getValueAt(i, 0).toString();
            
            int result = order.updateOrderRowWise(orderID, additional, remark, ing);
        }
        JOptionPane.showMessageDialog(this, "Values saved successfully !\nThe new RM Master plan PDF will be replaced with the old one", "Update Success", 1);
        order.viewOrder((DefaultTableModel) blendTable.getModel(), (DefaultTableModel) orderDetailsTable.getModel(), orderIDLabel.getText());
        adminPannel.populateIngStockTable();
        
        //Generating updated master plan PDF
        DefaultTableModel model = (DefaultTableModel) orderDetailsTable.getModel();
        JTable temp = new JTable(model);
        temp.setAutoCreateRowSorter(true);
        temp.getRowSorter().toggleSortOrder(8);
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date today = new Date();
        String[] data = {orderIDLabel.getText(), formatter.format(today)};
        pdf.generateMasterPlanPDF(temp, data);
    }//GEN-LAST:event_updateOrderBtnActionPerformed

    private void orderReceivedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderReceivedBtnActionPerformed
        
        ArrayList<Ingredient> ingredientsOrdered = new ArrayList();
        
        TableModel orderTableModel = orderDetailsTable.getModel();
        
        for (int r = 0; r < orderDetailsTable.getRowCount(); r++){
            
            Ingredient ing = new Ingredient();
            
            ing.setIngName((String) orderTableModel.getValueAt(r, 0));
            ing.setOrderReqQty(this.parseFloat( (String) orderTableModel.getValueAt(r, 1)));
            ing.setOrderExcessQty(this.parseFloat((String) orderTableModel.getValueAt(r, 5)));
            
            ingredientsOrdered.add(ing);
        }
        
        boolean updated = order.updateIngredientStock(ingredientsOrdered, orderIDLabel.getText());
        
        int result = order.updateOrderStatus(1, orderIDLabel.getText());
        
        if(result == 1 && updated){
            JOptionPane.showMessageDialog(this, "Order status changed successfully !", "Changes Succeeded", 1);
            adminPannel.populateOrderListTable();
            orderCompletedBtn.setVisible(true);
            orderReceivedBtn.setVisible(false);
            
            //set table editing false after order recieved
            orderDetailsTable.setEnabled(false);
            
            adminPannel.populateIngStockTable();
        }else{
            JOptionPane.showMessageDialog(this, "Changes did not affected !", "Changes Failed", 0);
        }
    }//GEN-LAST:event_orderReceivedBtnActionPerformed

    private void orderCompletedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderCompletedBtnActionPerformed
        
        ArrayList<Blend> blendsOrdered = new ArrayList();
        
        TableModel orderTableModel = blendTable.getModel();
        
        for (int r = 0; r < blendTable.getRowCount(); r++){
            
            Blend blend = new Blend();
            
            blend.setBlendName((String) orderTableModel.getValueAt(r, 1));
            blend.setOrderReqQty(this.parseInt((String) orderTableModel.getValueAt(r, 2)));
            blend.setOrderExcessQty(this.parseInt((String) orderTableModel.getValueAt(r, 6)));
            
            blendsOrdered.add(blend);
        }
        
        boolean updated = order.updateBlendStock(blendsOrdered, orderIDLabel.getText());
        
        int result = order.updateOrderStatus(2, orderIDLabel.getText());
        
        if(result == 1 && updated){
            JOptionPane.showMessageDialog(this, "Order status changed successfully !", "Changes Succeeded", 1);
            adminPannel.populateOrderListTable();
            orderCompletedBtn.setVisible(false);
            orderReceivedBtn.setVisible(false);
            updateOrderBtn.setVisible(false);
            
            adminPannel.populateBlendStockTable();
            
        }else{
            JOptionPane.showMessageDialog(this, "Changes did not affected !", "Changes Failed", 0);
        }
    }//GEN-LAST:event_orderCompletedBtnActionPerformed

    //overiding Integer.parseInt() to accept nums with commas
    private int parseInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            if (num.matches("[[0-9]{1,2}+,]*")) {
                num = num.replace(",", "");
                return Integer.parseInt(num);
            }
        }
        return 0;
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
            java.util.logging.Logger.getLogger(OrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel blendLabel;
    public javax.swing.JTable blendTable;
    private javax.swing.JButton cancelBtn;
    public javax.swing.JLabel dateLabel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JButton orderCompletedBtn;
    public javax.swing.JTable orderDetailsTable;
    public javax.swing.JLabel orderIDLabel;
    public javax.swing.JButton orderReceivedBtn;
    private javax.swing.JLabel rawMaterialLbl;
    public javax.swing.JButton updateOrderBtn;
    // End of variables declaration//GEN-END:variables
}
