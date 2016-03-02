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
public class DeliverBlendTwo extends javax.swing.JFrame {

    private AdminPannel adminPannel;

    public void setAdminPannel(AdminPannel adminPannel) {
        this.adminPannel = adminPannel;
    }
    
    public DeliverBlendTwo() {
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
        jComboBox1 = new javax.swing.JComboBox();
        unallocatingQty = new javax.swing.JLabel();
        unallocateQtyTxt = new javax.swing.JTextField();
        unAllocatingQtyTypeCombo = new javax.swing.JComboBox();
        ExcessQty = new javax.swing.JLabel();
        excessQtyTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        cancelBtn = new javax.swing.JButton();
        deliverBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Yes", "Other" }));

        unallocatingQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unallocatingQty.setText("Allocate to free stock ?");

        unallocateQtyTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unallocateQtyTxt.setToolTipText("");
        unallocateQtyTxt.setEnabled(false);

        unAllocatingQtyTypeCombo.setEditable(true);
        unAllocatingQtyTypeCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unAllocatingQtyTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "g", "kg" }));
        unAllocatingQtyTypeCombo.setEnabled(false);

        ExcessQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ExcessQty.setText("Sample Deliver");
        ExcessQty.setToolTipText("");

        excessQtyTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        excessQtyTxt.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("(g)");
        jLabel1.setEnabled(false);

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Yes" }));

        cancelBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cancelBtn.setText("Cancel");

        deliverBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deliverBtn.setText("Deliver");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ExcessQty)
                    .addComponent(deliverQty)
                    .addComponent(freeStockQty)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(blendCatg)
                                .addComponent(blendName)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(allocatedQty)))
                    .addComponent(unallocatingQty))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(unallocateQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unAllocatingQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allocatedQtyLabel)
                    .addComponent(blendNameLbl)
                    .addComponent(blendCatgLbl)
                    .addComponent(freeQtyLbl)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(deliverQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deliverQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deliverBtn))
                            .addComponent(excessQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)))
                .addContainerGap(15, Short.MAX_VALUE))
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
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unallocatingQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unallocateQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unAllocatingQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ExcessQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(excessQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliverBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            java.util.logging.Logger.getLogger(DeliverBlendTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeliverBlendTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeliverBlendTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeliverBlendTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeliverBlendTwo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ExcessQty;
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
    public javax.swing.JTextField excessQtyTxt;
    public javax.swing.JLabel freeQtyLbl;
    private javax.swing.JLabel freeStockQty;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JComboBox unAllocatingQtyTypeCombo;
    public javax.swing.JTextField unallocateQtyTxt;
    private javax.swing.JLabel unallocatingQty;
    // End of variables declaration//GEN-END:variables
}
