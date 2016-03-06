/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teaeli;

import classes.Blend;
import classes.DBConnection;
import classes.Ingredient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thisara Salgado
 */
   

public class BlendDetails extends javax.swing.JFrame {

    /**
     * Creates new form UpdateProduct
     */
    
     private Ingredient ingredient1;
     private Blend blend;
     private BlendDetails blendDetails;
     
     DBConnection dbConn = new DBConnection();
     
    public BlendDetails() {
        initComponents();
        setResizable(false);
        
        Ingredient ingredient = new Ingredient();
        ingredient.initIngCombo(ingCombo);
        
        Ingredient base = new Ingredient();
        base.initIngCombo(baseCombo);
        
        Ingredient flavour = new Ingredient();
        flavour.initFlavourCombo(flavoursCombo);
        
        //BlendDetails blendDetails = new BlendDetails();
        //blendDetails.identifyBlendDetails(b);
        
        ingredient1 = new Ingredient();
        blend = new Blend();
        //blendCodeTxt.setText("Thisara");
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
        cancelBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        blendNameTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        flavoursPerTxt = new javax.swing.JTextField();
        ingPerTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ingPerAddBtn = new javax.swing.JButton();
        flavoursPerAddBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        blendCodeTxt = new javax.swing.JTextField();
        ingCombo = new javax.swing.JComboBox();
        flavoursCombo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ingTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        flavourTable = new javax.swing.JTable();
        baseCombo = new javax.swing.JComboBox();
        category = new javax.swing.JLabel();
        blendCategoryCombo = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Blend Details ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Flavours");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Ingredients");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Blend Name");

        blendNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blendNameTxtActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Blend Code");

        flavoursPerTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flavoursPerTxtActionPerformed(evt);
            }
        });

        jLabel7.setText("%");

        jLabel6.setText("%");

        ingPerAddBtn.setText("Add");
        ingPerAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingPerAddBtnActionPerformed(evt);
            }
        });

        flavoursPerAddBtn.setText("Add");
        flavoursPerAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flavoursPerAddBtnActionPerformed(evt);
            }
        });

        updateBtn.setText("Update & Add new");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        blendCodeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blendCodeTxtActionPerformed(evt);
            }
        });

        ingCombo.setEditable(true);
        ingCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ingCombo.setSelectedIndex(-1);
        ingCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingComboActionPerformed(evt);
            }
        });

        flavoursCombo.setEditable(true);
        flavoursCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        flavoursCombo.setSelectedIndex(-1);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Base Composition");

        ingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ingredient", "Precentage (%)"
            }
        ));
        jScrollPane3.setViewportView(ingTable);

        flavourTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Flavour", "Precentage (%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(flavourTable);

        baseCombo.setEditable(true);
        baseCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        baseCombo.setSelectedIndex(-1);
        baseCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseComboActionPerformed(evt);
            }
        });

        category.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        category.setText("Category");

        blendCategoryCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Artisian Special V26", "Artisian Special V27", "Artisian Special V28", "Artisian Special V29", "Artisian Special V30", "Artisian Special V31", "Artisian Special V32", "Artisian Special V33", "Artisian Special V34", "Artisian Special V35", "Artisian Special V36", "Artisian Special V37", "Artisian Special V38", "Artisian Special V39", "Artisian Special V40", "Artisian Special V41", "Artisian Special V42", "Artisian Special V43", "Artisian Special V44", "Artisian Special V45", "Artisian Special V46", "Artisian Special V47", "Artisian Special V48", "Artisian Special V49", "Artisian Special V50", "Black", "Green Tea", "Organic Black", "Infusions", "Iced Teas" }));
        blendCategoryCombo.setToolTipText("");
        blendCategoryCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blendCategoryComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blendCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blendNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(baseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingPerAddBtn)))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(flavoursCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(flavoursPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(flavoursPerAddBtn))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(187, 187, 187)
                                        .addComponent(cancelBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(updateBtn)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(category)
                        .addGap(18, 18, 18)
                        .addComponent(blendCategoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(baseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendCategoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ingPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(ingPerAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flavoursCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flavoursPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(flavoursPerAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Identify belnd name by ID and populte ingredient and flavour table to update blend details
    
   /* public String sendBlendID(String blendID){
        String res = blendID;
        return res;
    }
    public void identifyBlendDetails(String blendID){
        String blendName = "";
        blendName = blend.getBlendNameByBlendID(blendID);
        blendCodeTxt.setText("Thisara");
        
        
       
        System.out.println(blendName);
        
        
        
        
    }*/
    /*public void populateIngredients(DefaultTableModel tableModel) {

        Connection connection = null;
        ResultSet resultSet = null;
        
        String blendID = "";

        try {
            String query = "SELECT I.ingName,R.ingPercent FROM ingredient I, recipie R WHERE R.blendID = '"+ blendID + "' AND R.type=1";

            connection = dbConn.setConnection();
            resultSet = dbConn.getResult(query, connection);

            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= 6; i++) {
                    newRow.addElement(resultSet.getObject(i));
                }
                tableModel.addRow(newRow);
            }

        } catch (Exception e) {
            System.err.println("stckhis 96 err : " + e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    System.err.println("Resultset close error : " + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.err.println("Connection close error : " + e);
                }
            }
        }
    }*/
    
    
    
    private void blendNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendNameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blendNameTxtActionPerformed

    private void flavoursPerTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flavoursPerTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_flavoursPerTxtActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
           this.setVisible(false);
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void blendCodeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendCodeTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blendCodeTxtActionPerformed

    private void ingComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingComboActionPerformed

    private void baseComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_baseComboActionPerformed

    private void blendCategoryComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendCategoryComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blendCategoryComboActionPerformed

    private void ingPerAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingPerAddBtnActionPerformed
        if (ingCombo.getSelectedItem().equals("")){
            //System.out.println("ing combo");
            JOptionPane.showMessageDialog(ingCombo, "Please select a ingredient to add.", "Error", JOptionPane.WARNING_MESSAGE);
            ingCombo.requestFocus();      
    }                                            
        else {
            String ingName = (String) ingCombo.getSelectedItem();
            float ingPer = Float.parseFloat(ingPerTxt.getText());
            boolean isNew = true;
            //Search if the ingredient is already added
            int rowCount = ingTable.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                if (ingName.equals(ingTable.getValueAt(i, 0))) {
                    isNew = false;
                    break;
                }
            }
            if (isNew) {
                List<List<String>> res = ingredient1.getIngDataByIngName(ingName);
                //System.out.println(res);
                Vector newRow = new Vector();
                newRow.addElement(res.get(0).get(1));
                newRow.addElement(ingPer);
              
                DefaultTableModel model = (DefaultTableModel) ingTable.getModel();
                model.addRow(newRow);
            }
            
            ingPerTxt.setText("");
            ingCombo.setSelectedIndex(-1);
            ingCombo.requestFocus();
        }
    }//GEN-LAST:event_ingPerAddBtnActionPerformed
    
    
    private void flavoursPerAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flavoursPerAddBtnActionPerformed
        if (flavoursCombo.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(flavoursCombo, "Please select a flavour to add.", "Error", JOptionPane.WARNING_MESSAGE);
            flavoursCombo.requestFocus();
    }                                                
        else {
            String flavourName = (String) flavoursCombo.getSelectedItem();
            float ingPer = Float.parseFloat(flavoursPerTxt.getText());
            boolean isNew = true;
            //Search if the ingredient is already added
            int rowCount = flavourTable.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                if (flavourName.equals(flavourTable.getValueAt(i, 0))) {
                    isNew = false;
                    break;
                }
            }
            if (isNew) {
                List<List<String>> res = ingredient1.getIngDataByIngName(flavourName);
                //System.out.println(res);
                Vector newRow = new Vector();
                newRow.addElement(res.get(0).get(1));
                newRow.addElement(ingPer);
              
                DefaultTableModel model = (DefaultTableModel) flavourTable.getModel();
                model.addRow(newRow);
            }
            
            flavoursPerTxt.setText("");
            flavoursCombo.setSelectedIndex(-1);
            flavoursCombo.requestFocus();
        }
    }//GEN-LAST:event_flavoursPerAddBtnActionPerformed
    String blendID, blendName, blendCategory, base;
    
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        blendID = blendCodeTxt.getText();
        blendName = blendNameTxt.getText();
        blendCategory = blendCategoryCombo.getSelectedItem().toString();
        base = baseCombo.getSelectedItem().toString();
        
        if (blendID.isEmpty() || blendName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Any feild cannot be empty");
        }else{
        
            float ingPerCount = 0;
            float flavPerCount = 0;
            int ingCount = ingTable.getRowCount();
            int flavCount = flavourTable.getRowCount();
            int recCount = ingCount+flavCount;
            for(int i=0; i < ingCount; i++){
                float initPer = Float.parseFloat(ingTable.getValueAt(i, 1).toString()); ;
                ingPerCount = ingPerCount + initPer;
                /*System.out.println(a);*/
            }
            for(int i=0; i <flavCount;i++){
                float initPer = Float.parseFloat(flavourTable.getValueAt(i, 1).toString()); ;
                flavPerCount = flavPerCount + initPer;
            }

            if(ingPerCount <= 0 || ingPerCount>=100){
                JOptionPane.showMessageDialog(this, "Invalid percentage");
                
            }else if(flavPerCount <= 0 || flavPerCount>=100){
                JOptionPane.showMessageDialog(this, "Invalid percentage");
            }else{
                //System.out.println(blendID);
                int ret = blend.addNewBlend(blendID, blendName, base, blendCategory);
                
                ArrayList <Integer> ingID = new ArrayList<>();
                ArrayList <Integer> flavourID = new ArrayList<>();
                for(int i=0;i<ingCount;i++){
                    Blend a = new Blend();
                    //ingID.add(Integer.parseInt(addNewBlendIngTbl.getValueAt(i, 0).toString()));
                    ingID.add(a.getIngIDRecByIngName(ingTable.getValueAt(i, 0).toString()));
                }
                
                for(int i=0;i<flavCount;i++){
                    Blend a = new Blend();
                    //ingID.add(Integer.parseInt(addNewBlendIngTbl.getValueAt(i, 0).toString()));
                    flavourID.add(a.getIngIDRecByIngName(flavourTable.getValueAt(i, 0).toString()));
                }
                
                
                //System.out.println(ingID.get(0)+2);
                
                //String query1 = "INSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('"+ blendID +"','"+ a +"','"+ b +"',0)";
                //String query1 = "TNSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('"+ blendID +"',26,2.3,0)";
                
                //System.out.println("x is"+ x);
                //String q1 ="";
                int x = 0;
                int y = 0;
                for(int i =0; i<ingCount ; i++){
                   int a = ingID.get(i);
                   double b = Double.parseDouble(ingTable.getValueAt(i, 1).toString());
                   String query1 = "INSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('"+ blendID +"','"+ a +"','"+ b +"',0)";
                   x = dbConn.updateResult(query1);
                   
                }
                
                for(int j =0; j<flavCount ; j++){
                   int c = flavourID.get(j);
                   double d = Double.parseDouble(flavourTable.getValueAt(j, 1).toString());
                   String query2 = "INSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('"+ blendID +"','"+ c +"','"+ d +"',1)";
                   y = dbConn.updateResult(query2);
                   
                }
                
                if(x==1 && y==1){
                       JOptionPane.showMessageDialog(null, "New Blend Succesfully Added");
                   }else{
                       JOptionPane.showMessageDialog(null, "Error!, Data not Saved");
                   }
                    
            }
        
        }
    }//GEN-LAST:event_updateBtnActionPerformed
        
    
    
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
            java.util.logging.Logger.getLogger(BlendDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlendDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlendDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlendDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlendDetails().setVisible(true);
            }
        });
    }
    
        
	

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox baseCombo;
    private javax.swing.JComboBox blendCategoryCombo;
    private javax.swing.JTextField blendCodeTxt;
    public javax.swing.JTextField blendNameTxt;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel category;
    public javax.swing.JTable flavourTable;
    public javax.swing.JComboBox flavoursCombo;
    private javax.swing.JButton flavoursPerAddBtn;
    private javax.swing.JTextField flavoursPerTxt;
    public javax.swing.JComboBox ingCombo;
    private javax.swing.JButton ingPerAddBtn;
    private javax.swing.JTextField ingPerTxt;
    public javax.swing.JTable ingTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

    void identifyBlend(String blendID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getBlendNameByBlendID(String blendID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
