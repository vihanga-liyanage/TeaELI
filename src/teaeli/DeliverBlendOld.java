package teaeli;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DeliverBlendOld extends javax.swing.JFrame {

    private AdminPannel adminPannel;

    public void setAdminPannel(AdminPannel adminPannel) {
        this.adminPannel = adminPannel;
    }
    
    public DeliverBlendOld() {
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
        allocatedQty = new javax.swing.JLabel();
        allocatedQtyLabel = new javax.swing.JLabel();
        deliverQty = new javax.swing.JLabel();
        deliverQtyTxt = new javax.swing.JTextField();
        deliverQtyTypeCombo = new javax.swing.JComboBox();
        unallocatingQty = new javax.swing.JLabel();
        unallocateQtyTxt = new javax.swing.JTextField();
        unAllocatingQtyTypeCombo = new javax.swing.JComboBox();
        freeStockQty = new javax.swing.JLabel();
        freeQtyLbl = new javax.swing.JLabel();
        ExcessQty = new javax.swing.JLabel();
        excessQtyTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        deliverNote = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        deliverNoteTxt = new javax.swing.JTextArea();
        deliverBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

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

        allocatedQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        allocatedQty.setText("Allocated Qty In Stock");

        allocatedQtyLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        allocatedQtyLabel.setText("100 g");

        deliverQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deliverQty.setText("Delivering Qty");

        deliverQtyTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        deliverQtyTypeCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deliverQtyTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "g", "kg" }));

        unallocatingQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unallocatingQty.setText("Unallocating Qty");

        unallocateQtyTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unallocateQtyTxt.setToolTipText("");

        unAllocatingQtyTypeCombo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        unAllocatingQtyTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "g", "kg" }));

        freeStockQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        freeStockQty.setText("Free Qty In Stock");

        freeQtyLbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        freeQtyLbl.setText("50 g");

        ExcessQty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ExcessQty.setText("Excess Qty Deliver");
        ExcessQty.setToolTipText("");

        excessQtyTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("(g)");

        deliverNote.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deliverNote.setText("Deliver Note");

        deliverNoteTxt.setColumns(20);
        deliverNoteTxt.setRows(5);
        jScrollPane1.setViewportView(deliverNoteTxt);

        deliverBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deliverBtn.setText("Deliver");

        cancelBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cancelBtn.setText("Cancel");

        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("* This qty will be added to free stock");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(blendName)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deliverNote, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(blendCatg))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(allocatedQty, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(freeStockQty, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deliverQty, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(unallocatingQty, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ExcessQty, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(excessQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(deliverQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deliverQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(unallocateQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unAllocatingQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(cancelBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deliverBtn))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(blendCatgLbl)
                    .addComponent(blendNameLbl)
                    .addComponent(allocatedQtyLabel)
                    .addComponent(freeQtyLbl))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
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
                    .addComponent(freeQtyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(freeStockQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliverQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliverQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliverQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unallocatingQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unallocateQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unAllocatingQtyTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(excessQtyTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExcessQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deliverNote, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliverBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(DeliverBlendOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeliverBlendOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeliverBlendOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeliverBlendOld.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeliverBlendOld().setVisible(true);
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
    private javax.swing.JLabel deliverNote;
    public javax.swing.JTextArea deliverNoteTxt;
    private javax.swing.JLabel deliverQty;
    public javax.swing.JTextField deliverQtyTxt;
    public javax.swing.JComboBox deliverQtyTypeCombo;
    public javax.swing.JTextField excessQtyTxt;
    public javax.swing.JLabel freeQtyLbl;
    private javax.swing.JLabel freeStockQty;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JComboBox unAllocatingQtyTypeCombo;
    public javax.swing.JTextField unallocateQtyTxt;
    private javax.swing.JLabel unallocatingQty;
    // End of variables declaration//GEN-END:variables
}
