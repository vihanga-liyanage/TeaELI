package teaeli;

import classes.Ingredient;
import classes.Supplier;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class IngredientDetails extends javax.swing.JFrame {

    public Object pannel;
    private Ingredient ingredient = new Ingredient();
    private Supplier supplier = new Supplier();

    public IngredientDetails() {
        //Setting icon
        ImageIcon img = new ImageIcon(".\\src\\teaeli\\icon-1.png");
        this.setIconImage(img.getImage());

        //Add windows look and feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
        initComponents();

        Dimension screenSize, frameSize;
        int x, y;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameSize = getSize();
        x = (screenSize.width - frameSize.width) / 2;
        y = (screenSize.height - frameSize.height) / 2;
        setLocation(x, y);
        setResizable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        itemNameTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        unitPriceTxt = new javax.swing.JTextField();
        cancelBtn = new javax.swing.JButton();
        updateItemBtn = new javax.swing.JButton();
        itemTypeCombo = new javax.swing.JComboBox();
        supplierCombobox = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ingredient Details");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Ingredient Details ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Ingredient Name");

        itemNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameTxtActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Ingredient Type");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Supplier Name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Unit Price");

        unitPriceTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitPriceTxtActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        updateItemBtn.setText("Update");
        updateItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateItemBtnActionPerformed(evt);
            }
        });

        itemTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tea", "Flavour", "Herbs", "Flowers", "Fruits", "Leaves", "Granule Flavour", "Other" }));
        itemTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTypeComboActionPerformed(evt);
            }
        });

        supplierCombobox.setEditable(true);
        supplierCombobox.setRequestFocusEnabled(true);
        supplierCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierComboboxActionPerformed(evt);
            }
        });

        jButton1.setText("Add New Supplier");
        jButton1.setPreferredSize(new java.awt.Dimension(117, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(itemNameTxt)
                            .addComponent(itemTypeCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supplierCombobox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 300, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelBtn)
                                .addGap(18, 18, 18)
                                .addComponent(updateItemBtn))
                            .addComponent(unitPriceTxt))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemNameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unitPriceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        supplierCombobox.getAccessibleContext().setAccessibleName("");
        supplierCombobox.getAccessibleContext().setAccessibleDescription("");

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
        this.setVisible(false);
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void itemTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTypeComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemTypeComboActionPerformed

    private void itemNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameTxtActionPerformed

    private void unitPriceTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitPriceTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitPriceTxtActionPerformed

    private void updateItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateItemBtnActionPerformed

        int response = JOptionPane.showConfirmDialog(null, "Are you sure you need to update the ingredient? ", "Confirm Update",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            this.setVisible(false);
            this.dispose();
        } else if (response == JOptionPane.YES_OPTION) {
            String ingName;
            int supID = 0, ingCategoryID = 0, ingID = 0;
            float unitPrice = 0;

            //get ingID
            try {
                ingID = Integer.parseInt(this.getName());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "There were some issues with the database. Please contact developers.\n\nError code : IngredientDetails 256", "Error", 0);
                System.exit(0);

            }

            //get ingredient name
            ingName = this.itemNameTxt.getText();

            if (ingName.equals("")) {
                JOptionPane.showMessageDialog(itemNameTxt, "Please enter ingredient name.", "Invalid Ingredient Name", 2);
                itemNameTxt.requestFocusInWindow();
            } else {
                //get ingredient categoryid
                int comboSelectedIgCat = this.itemTypeCombo.getSelectedIndex();

                if (comboSelectedIgCat == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a ingredient category!!!", "Empty Category Selection", 2);
                } else {
                    ingCategoryID = comboSelectedIgCat + 1;

                }

                //get supplier id by name
                String SupName = (String) this.supplierCombobox.getSelectedItem();

                try {
                    supID = supplier.getSupplierIDByName(SupName);
                } catch (SQLException ex) {
                    System.out.println("SQL eror : " + ex);
                }

                //get unit price
                String unitPriceString = this.unitPriceTxt.getText();

                int unitPriceOK = 0;
                try {
                    unitPrice = Float.parseFloat(unitPriceString);
                    if (unitPrice < 0) {
                        JOptionPane.showMessageDialog(unitPriceTxt, "Please enter valid amount for unit price.", "Invalide Unit Price", 2);
                        unitPriceTxt.requestFocusInWindow();
                        unitPriceTxt.setText("");
                    } else {
                        unitPriceOK = 1;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(unitPriceTxt, "Please enter valid amount for unit price.", "Invalide Unit Price", 2);
                    unitPriceTxt.requestFocusInWindow();
                    unitPriceTxt.setText("");
                }

                if (unitPriceOK == 1) {
                    // call update ingredient method
                    try {
                        int updateOK = ingredient.updateIngredient(ingID, ingName, ingCategoryID, supID, unitPrice);

                        if (updateOK == 1) {
                            //Re-generating the admin panel since the data is changed

                            JOptionPane.showMessageDialog(this, "Updated Successfuly !", "Update Success", 1);
                            
                            if ("teaeli.AdminPannel".equals(pannel.getClass().getName())) {
                                AdminPannel adminPannel = new AdminPannel();
                                adminPannel.mainTabbedPane.setSelectedIndex(2);
                                adminPannel.setVisible(true);
                                AdminPannel old = (AdminPannel) pannel;
                                old.dispose();
                                this.dispose();
                            } else if ("teaeli.ManagerPannel".equals(pannel.getClass().getName())) {
                                ManagerPannel managerPannel = new ManagerPannel();
                                managerPannel.setVisible(true);
                                ManagerPannel old = (ManagerPannel) pannel;
                                old.dispose();
                            }

                        } else if (updateOK == 2) {
                            JOptionPane.showMessageDialog(this, "Ingredient Name must be unique.", "Duplicate Ingredient Name", 0);
                            itemNameTxt.requestFocusInWindow();
                            itemNameTxt.setText("");
                        } else {
                            JOptionPane.showMessageDialog(this, "There were some issues with the database. Please contact developers.\n\nError code : IngredientDetails 323", "Error", 0);
                            System.exit(0);
                        }

                    } catch (SQLException ex) {
                        //Logger.getLogger(IngredientDetails.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("sql error id:" + ex);
                    }
                }

            }

        } else if (response == JOptionPane.CLOSED_OPTION) {
            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_updateItemBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String suplierName = JOptionPane.showInputDialog(null, "Enter Supplier Name");

        if (suplierName.equals("")) { // check for null input
            JOptionPane.showMessageDialog(this, "Please enter supplier name!!!", "Enpty Supplier Name", 2);
        } else {
            try {
                int inserted = supplier.addNewSupplier(suplierName);

                if (inserted == 1) {
                    JOptionPane.showMessageDialog(null, "New supplier added successfully", "New Supplier Added", 1);
                    supplierCombobox.addItem(suplierName);
                    supplierCombobox.setSelectedItem(suplierName);
                } else {
                    JOptionPane.showMessageDialog(this, "There were some issues with the database. Please contact developers.\n\nError code : IngredientDetails 355", "Error", 0);
                    System.exit(0);
                }
            } catch (SQLException ex) {
                System.out.println("SQL error : " + ex);
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void supplierComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierComboboxActionPerformed

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
            java.util.logging.Logger.getLogger(IngredientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngredientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngredientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngredientDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngredientDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    public static javax.swing.JTextField itemNameTxt;
    public static javax.swing.JComboBox itemTypeCombo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    public static javax.swing.JComboBox supplierCombobox;
    public static javax.swing.JTextField unitPriceTxt;
    private javax.swing.JButton updateItemBtn;
    // End of variables declaration//GEN-END:variables
}
