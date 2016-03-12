/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teaeli;

import classes.PswrdEncrypt;
import classes.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author ASHI
 */
public class AddNewUser extends javax.swing.JFrame {

    //create an instance of the entity class
    User user1 = new User();
    
    private AdminPannel adminPannel;

    public void setAdminPannel(AdminPannel adminPannel) {
        this.adminPannel = adminPannel;
    }
    
    public AddNewUser() {
        //Add windows look and feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AdminPannel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        
        Dimension screenSize,frameSize;
        int x,y;
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frameSize=getSize();
        x=(screenSize.width-frameSize.width)/4;
        y=(screenSize.height-frameSize.height)/4;
        setLocation(x, y);
        setResizable(false);
    }

    //method to refresh related tables and close this window
    private void close(){
        this.setVisible(false);
        adminPannel.populateUserTable();
        this.dispose();
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
        jLabel1 = new javax.swing.JLabel();
        usernameTxt = new javax.swing.JTextField();
        passwordLbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        firstnameTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lastnameTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addUserDesignationCombo = new javax.swing.JComboBox();
        passwordTxt = new javax.swing.JPasswordField();
        cnfrmPasswordTxt = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add New User");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Add New User ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Username");

        passwordLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        passwordLbl.setText("Password");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("First Name");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Last Name");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Designation");

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        addBtn.setText("Add ");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Confirm Password");

        jLabel6.setForeground(java.awt.Color.red);
        jLabel6.setText("* Enter at least 8 characters");

        addUserDesignationCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Manager", "Admin" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordLbl)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(usernameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(addUserDesignationCombo, 0, 200, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelBtn)
                                .addGap(18, 18, 18)
                                .addComponent(addBtn))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(firstnameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(lastnameTxt)))
                        .addComponent(passwordTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                    .addComponent(cnfrmPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(firstnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lastnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(addUserDesignationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(usernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLbl)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cnfrmPasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(14, Short.MAX_VALUE))
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
        close();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        if (firstnameTxt.getText().isEmpty() || lastnameTxt.getText().isEmpty() || usernameTxt.getText().isEmpty() || passwordTxt.getText().isEmpty() || cnfrmPasswordTxt.getText().isEmpty() ){
            JOptionPane.showMessageDialog(this, "No text field should be empty!!!");
            return;
        }
        if(passwordTxt.getText().length()<8){
            JOptionPane.showMessageDialog(this, "Password length should be atleast 8 characters!!!");
            passwordTxt.setText("");
            cnfrmPasswordTxt.setText("");
            return;
        }
        
        if (passwordTxt.getText().equals(cnfrmPasswordTxt.getText())) {
            int x = user1.checkUserName(usernameTxt.getText());

            if (x == 1) {
                user1.setFirstName(firstnameTxt.getText());
                user1.setLastName(lastnameTxt.getText());
                user1.setDesignation(addUserDesignationCombo.getSelectedItem().toString());
                user1.setUserName(usernameTxt.getText());
                user1.setPassword(PswrdEncrypt.main2(passwordTxt.getText()));

                int result = user1.addNewUser(user1);
                if (result == 1) {                    
                    JOptionPane.showMessageDialog(this, "New entry has been entered succesfully");
                    this.setVisible(false);
                    close();                 
                } else {
                    //if insert is not successful
                    JOptionPane.showMessageDialog(this, "Sorry! Error occured while inserting!\nPlease enter again.");
                }
            } else if (x == 0) {
                JOptionPane.showMessageDialog(this, "UserName already exists!");
                usernameTxt.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error occured while checking the userName!");
                return;
            }

        } else {
            JOptionPane.showMessageDialog(this, "Password mismatched!");
            passwordTxt.setText("");
            cnfrmPasswordTxt.setText("");
        }
    }//GEN-LAST:event_addBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AddNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNewUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox addUserDesignationCombo;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPasswordField cnfrmPasswordTxt;
    private javax.swing.JTextField firstnameTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lastnameTxt;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JTextField usernameTxt;
    // End of variables declaration//GEN-END:variables
}
