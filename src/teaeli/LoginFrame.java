/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teaeli;

import classes.DBConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author CHAM PC
 */

public class LoginFrame extends javax.swing.JFrame {
    
    public static AdminPannel adminPannel = new AdminPannel();
    public String user;        
    public LoginFrame() {
        //Add windows look and feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AdminPannel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initComponents();
        jPanel1.setBackground(new Color(0,0,0,125));
        Dimension screenSize,frameSize;
        int x,y;
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frameSize=getSize();
        x=(screenSize.width-frameSize.width)/4;
        y=(screenSize.height-frameSize.height)/4;
        setLocation(x, y);
        setResizable(false);
    }
    
    DBConnection dbcon = new DBConnection();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st = null;
    
    String userName,password;
    int passwrdCount =0;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Login");
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 255, 255));
        jLabel2.setText("User Name");

        txtUsername.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 255, 255));
        jLabel3.setText("Password");

        btnSubmit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        btnSubmit.setText("Login");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(txtPassword))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(220, 110, 290, 160);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teaeli/login background.jpg"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 740, 400);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed

         userName = txtUsername.getText();
         password = txtPassword.getText();
         
         if (checkLogin(userName, password)==1){
             
                AdminPannel adminPannel = new AdminPannel();//the provided username & password matched
                adminPannel.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);//Keep the window fullscreen
                adminPannel.setVisible(true);
                user =userName;
                this.setVisible(false);
                
            }else if (checkLogin(userName, password)==2){
                
                ManagerPannel managerPannel = new ManagerPannel();//the provided username & password matched
                managerPannel.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);//Keep the window fullscreen
                managerPannel.setVisible(true);
                user =userName;
                this.setVisible(false);
                
            /*}else if (checkLogin(userName, password)==3){
                
                if (passwrdCount !=3){
                JOptionPane.showMessageDialog(this, "Wrong user name or password");//the provided password does not exist in the db
                txtUsername.setText("");
                txtPassword.setText("");
                txtUsername.requestFocusInWindow();
                passwrdCount++;
                }else if (passwrdCount==3){
                    JOptionPane.showMessageDialog(this, "ERROR!!! System will close!");
                    this.dispose();
                }*/
                
            }else if (checkLogin(userName, password)== 4 | checkLogin(userName, password)==3){
                
                if (passwrdCount !=3){
                JOptionPane.showMessageDialog(this, "Wrong user name or password");//the provided password does not exist in the db
                txtUsername.setText("");
                txtPassword.setText("");
                txtUsername.requestFocusInWindow();
                passwrdCount++;
                }else if (passwrdCount==3){
                    JOptionPane.showMessageDialog(this, "ERROR!!! System will close!");
                    this.dispose();
                }
            }

    }//GEN-LAST:event_btnSubmitActionPerformed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        int code = evt.getKeyCode();
        if (code== KeyEvent.VK_ENTER) {
            userName = txtUsername.getText();
            password = txtPassword.getText();
         
            if (checkLogin(userName, password)==1){
                
                AdminPannel adminPannel = new AdminPannel();//the provided username & password matched
                adminPannel.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);//Keep the window fullscreen
                adminPannel.setVisible(true);
                user =userName;
                this.setVisible(false);
                
            }else if (checkLogin(userName, password)==2){
                
                ManagerPannel managerPannel = new ManagerPannel();//the provided username & password matched
                managerPannel.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);//Keep the window fullscreen
                managerPannel.setVisible(true);
                user =userName;
                this.setVisible(false);
                
            /*}else if (checkLogin(userName, password)==3){
                
                if (passwrdCount !=3){
                JOptionPane.showMessageDialog(this, "Wrong user name or password");//the provided password does not exist in the db
                txtUsername.setText("");
                txtPassword.setText("");
                txtUsername.requestFocusInWindow();
                passwrdCount++;
                }else if (passwrdCount==3){
                    JOptionPane.showMessageDialog(this, "ERROR!!! System will close!");
                    this.dispose();
                    
                }*/
            }else if (checkLogin(userName, password)== 4 | checkLogin(userName, password)==3){
                
                if (passwrdCount !=3){
                JOptionPane.showMessageDialog(this, "Wrong user name or password");//the provided password does not exist in the db
                txtUsername.setText("");
                txtPassword.setText("");
                txtUsername.requestFocusInWindow();
                passwrdCount++;
                }else if (passwrdCount==3){
                    JOptionPane.showMessageDialog(this, "ERROR!!! System will close!");
                    this.dispose();
                }
            }
        }
        
    }//GEN-LAST:event_txtPasswordKeyPressed
    
    public int checkLogin(String userName, String password) {
        try {
            con = dbcon.setConnection();//get the connection
            String query = "SELECT username,designation FROM user where password = sha1('"+password+"') and username = ('"+userName+"')";
            ResultSet rs =dbcon.getResult(query, con);
            
            while (rs.next()) {
                if (rs.getString(2).equals("Admin")) {
                    return 1;     
                } else if (rs.getString(2).equals("Manager")) {
                    return 2;  
                } else{
                    return 3;  
                }
            }
            return 4;
            
            
        } catch (SQLException e) {
            System.out.println(e);//an error occured while executing
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {

            }
        }
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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
