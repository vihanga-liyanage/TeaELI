/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teaeli;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author ASHI
 */
public class DeliverBlend extends javax.swing.JFrame {

    private AdminPannel adminPannel;

    public void setAdminPannel(AdminPannel adminPannel) {
        this.adminPannel = adminPannel;
    }
    
    public DeliverBlend() {
        initComponents();

        Dimension screenSize, frameSize;
        int x, y;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameSize = getSize();
        x = (screenSize.width - frameSize.width) / 4;
        y = (screenSize.height - frameSize.height) / 4;
        setLocation(x, y);
        setResizable(false);
        
    }

    //method to refresh related tables and close this window
    private void close(){
        this.setVisible(false);
        adminPannel.populateBlendStockTable();
        adminPannel.populateBlendHistoryTable();
        this.dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        blendName = new javax.swing.JLabel();
        blendNameLbl = new javax.swing.JLabel();
        blendCatg = new javax.swing.JLabel();
        blendCatgLbl = new javax.swing.JLabel();
        allocatedQtyLabel = new javax.swing.JLabel();
        freeQtyLbl = new javax.swing.JLabel();
        freeStockQty = new javax.swing.JLabel();
        allocatedQty = new javax.swing.JLabel();
        deliverQty = new javax.swing.JLabel();
        deliverQtyTxt = new javax.swing.JTextField();
        deliverQtyTypeCombo = new javax.swing.JComboBox();
        allocateFreeStockCombo = new javax.swing.JComboBox();
        unallocatingQty = new javax.swing.JLabel();
        unallocateQtyTxt = new javax.swing.JTextField();
        unAllocatingQtyTypeCombo = new javax.swing.JComboBox();
        SampleQty = new javax.swing.JLabel();
        sampleQtyTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        sampleDeliverCombo = new javax.swing.JComboBox();
        cancelBtn = new javax.swing.JButton();
        deliverBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Deliver Blend");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Deliver Blend", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 16))); // NOI18N

        blendName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        blendName.setText("Blend Name");

        blendNameLbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        blendNameLbl.setText("Artisian Blend V2");

        blendCatg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        blendCatg.setText("Blend Category");

        blendCatgLbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        blendCatgLbl.setText("Almond Truffle - V1 ");

        allocatedQtyLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        allocatedQtyLabel.setText("100 g");

        freeQtyLbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        freeQtyLbl.setText("50 g");

        freeStockQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        freeStockQty.setText("Free Qty In Stock");

        allocatedQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        allocatedQty.setText("Allocated Qty In Stock");

        deliverQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deliverQty.setText("Delivering Qty");

        deliverQtyTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        deliverQtyTypeCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deliverQtyTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "g", "kg" }));

        allocateFreeStockCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        allocateFreeStockCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Yes", "Other" }));
        allocateFreeStockCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allocateFreeStockComboActionPerformed(evt);
            }
        });

        unallocatingQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unallocatingQty.setText("Allocate to free stock ?");

        unallocateQtyTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unallocateQtyTxt.setToolTipText("");
        unallocateQtyTxt.setEnabled(false);

        unAllocatingQtyTypeCombo.setEditable(true);
        unAllocatingQtyTypeCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unAllocatingQtyTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "g", "kg" }));
        unAllocatingQtyTypeCombo.setEnabled(false);

        SampleQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SampleQty.setText("Sample Deliver");
        SampleQty.setToolTipText("");

        sampleQtyTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sampleQtyTxt.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("(g)");
        jLabel1.setEnabled(false);

        sampleDeliverCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sampleDeliverCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Yes" }));
        sampleDeliverCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sampleDeliverComboActionPerformed(evt);
            }
        });

        cancelBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cancelBtn.setText("Cancel");

        deliverBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deliverBtn.setText("Deliver");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(blendCatg)
                    .addComponent(blendName)
                    .addComponent(allocatedQty)
                    .addComponent(freeStockQty)
                    .addComponent(deliverQty)
                    .addComponent(unallocatingQty)
                    .addComponent(SampleQty))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(unallocateQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(unAllocatingQtyTypeCombo, 0, 1, Short.MAX_VALUE))
                    .addComponent(blendCatgLbl)
                    .addComponent(blendNameLbl)
                    .addComponent(allocatedQtyLabel)
                    .addComponent(freeQtyLbl)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(deliverQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deliverQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(allocateFreeStockCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sampleDeliverCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deliverBtn))
                            .addComponent(sampleQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blendName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendNameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blendCatg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendCatgLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allocatedQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allocatedQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(freeStockQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(freeQtyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliverQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliverQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliverQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allocateFreeStockCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unallocatingQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unallocateQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unAllocatingQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sampleDeliverCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SampleQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sampleQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliverBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
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

    private void allocateFreeStockComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allocateFreeStockComboActionPerformed
        
        //enabled unallocate qty text field on selection of other
        if (allocateFreeStockCombo.getSelectedIndex() == 2){
            unallocateQtyTxt.setEnabled(true);
            unAllocatingQtyTypeCombo.setEnabled(true);
        }else{
            unallocateQtyTxt.setEnabled(false);
            unAllocatingQtyTypeCombo.setEnabled(false);
        }
    }//GEN-LAST:event_allocateFreeStockComboActionPerformed

    private void sampleDeliverComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sampleDeliverComboActionPerformed
        
        //enabled sample qty text field on selection of yes
        if (sampleDeliverCombo.getSelectedIndex() == 1){
            sampleQtyTxt.setEnabled(true);
            jLabel1.setEnabled(true);
        }else{
            sampleQtyTxt.setEnabled(false);
            jLabel1.setEnabled(false);
        }
    }//GEN-LAST:event_sampleDeliverComboActionPerformed

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
            java.util.logging.Logger.getLogger(DeliverBlend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeliverBlend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeliverBlend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeliverBlend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeliverBlend().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SampleQty;
    public javax.swing.JComboBox allocateFreeStockCombo;
    private javax.swing.JLabel allocatedQty;
    public javax.swing.JLabel allocatedQtyLabel;
    private javax.swing.JLabel blendCatg;
    public javax.swing.JLabel blendCatgLbl;
    private javax.swing.JLabel blendName;
    public javax.swing.JLabel blendNameLbl;
    public javax.swing.JButton cancelBtn;
    public javax.swing.JButton deliverBtn;
    private javax.swing.JLabel deliverQty;
    public javax.swing.JTextField deliverQtyTxt;
    public javax.swing.JComboBox deliverQtyTypeCombo;
    public javax.swing.JLabel freeQtyLbl;
    private javax.swing.JLabel freeStockQty;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JComboBox sampleDeliverCombo;
    public javax.swing.JTextField sampleQtyTxt;
    public javax.swing.JComboBox unAllocatingQtyTypeCombo;
    public javax.swing.JTextField unallocateQtyTxt;
    private javax.swing.JLabel unallocatingQty;
    // End of variables declaration//GEN-END:variables
}
