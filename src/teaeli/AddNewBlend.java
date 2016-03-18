package teaeli;

import classes.Blend;
import classes.DBConnection;
import classes.Ingredient;
import classes.ResultArray;
import classes.Validation;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
//import sun.org.mozilla.javascript.internal.Token;

public class AddNewBlend extends javax.swing.JFrame {

    private Ingredient ingredient1;
    private Blend blend;
    private AdminPannel adminpanel;

    DBConnection dbConn = new DBConnection();

    public AddNewBlend() {

        initComponents();

        //Changing table headers to bold
        addNewBlendFlavourTbl.getTableHeader().setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        addNewBlendIngTbl.getTableHeader().setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        setResizable(false);

        Ingredient ingredient = new Ingredient();
        ingredient.initIngCombo(ingCombo);

        Ingredient base = new Ingredient();
        base.initIngCombo(baseCombo);

        Ingredient flavour = new Ingredient();
        flavour.initFlavourCombo(flavourCombo);

        ingredient1 = new Ingredient();
        blend = new Blend();
        adminpanel = new AdminPannel();

        ingCombo.setSelectedIndex(-1);
        flavourCombo.setSelectedIndex(-1);
        blendCategoryCombo.setSelectedIndex(-1);
        //baseCombo.setSelectedIndex(-1);

        //Validation on Ing percentage, when key released
        ingPerTxt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String per = ingPerTxt.getText();
                if (per.length() > 0) {
                    if (!(new Validation().isFloat(per))) {
                        JOptionPane.showMessageDialog(ingPerTxt, "Ingredient percentage must be a valid number!!!", "Invalid Ingredient Percentage", 0);
                        ingPerTxt.setText(per.substring(0, per.length() - 1));
                    } else if (Float.parseFloat(per) < 0) {
                        JOptionPane.showMessageDialog(ingPerTxt, "Ingredient percentage cannot be less than 0!!!", "Invalid Ingredient Percentage", 0);
                        ingPerTxt.setText(per.substring(0, per.length() - 1));
                    }
                }
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }
        });

        //setting focus to ing per txt when item selected
        ingCombo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    ingPerTxt.requestFocus();
                }
            }
        });

        flavourPerTxt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String per = flavourPerTxt.getText();
                if (per.length() > 0) {
                    if (!(new Validation().isFloat(per))) {
                        JOptionPane.showMessageDialog(flavourPerTxt, "Flavour percentage must be a valid number!!!", "Invalid Flavour Percentage", 0);
                        ingPerTxt.setText(per.substring(0, per.length() - 1));
                    } else if (Float.parseFloat(per) < 0) {
                        JOptionPane.showMessageDialog(flavourPerTxt, "Flavour percentage cannot be less than 0!!!", "Invalid Flavour Percentager", 0);
                        flavourPerTxt.setText(per.substring(0, per.length() - 1));
                    }
                }
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }
        });

        //setting focus to flavour per txt when item selected
        flavourCombo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    flavourPerTxt.requestFocus();
                }
            }
        });
        
        blendCodeTxt.requestFocus();
        
        //setting focus to base combo when blend category is selected
        blendCategoryCombo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    baseCombo.requestFocus();
                }
            }
        });
        
        //setting focus to ing combo when base ing is selected
        baseCombo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    ingCombo.requestFocus();
                }
            }
        });
        
        //enabling delete button on row select
        final ListSelectionModel ingModel = addNewBlendIngTbl.getSelectionModel();
        ingModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!ingModel.isSelectionEmpty()) {
                    dltIngBtn.setEnabled(true);
                }
            }
        });
        final ListSelectionModel flvModel = addNewBlendFlavourTbl.getSelectionModel();
        flvModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!flvModel.isSelectionEmpty()) {
                    dltFlavourBtn.setEnabled(true);
                }
            }
        });
        
    }

    //End of the Constructor

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //method to refresh related tables and close this window
    private void close() {
        this.setVisible(false);
        adminpanel.populateProductTable();

        this.dispose();
    }

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
        flavourPerTxt = new javax.swing.JTextField();
        ingPerTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ingPerAddBtn = new javax.swing.JButton();
        flavourPerAddBtn = new javax.swing.JButton();
        addNewBlendBtn = new javax.swing.JButton();
        blendCodeTxt = new javax.swing.JTextField();
        ingCombo = new javax.swing.JComboBox();
        flavourCombo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        addNewBlendFlavourTbl = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        addNewBlendIngTbl = new javax.swing.JTable();
        baseCombo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        blendCategoryCombo = new javax.swing.JComboBox();
        dltIngBtn = new javax.swing.JButton();
        dltFlavourBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Add New Blend ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 0, 14))); // NOI18N

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

        flavourPerTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flavourPerTxtActionPerformed(evt);
            }
        });

        ingPerTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingPerTxtActionPerformed(evt);
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

        flavourPerAddBtn.setText("Add");
        flavourPerAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flavourPerAddBtnActionPerformed(evt);
            }
        });

        addNewBlendBtn.setText("Add New");
        addNewBlendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewBlendBtnActionPerformed(evt);
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

        flavourCombo.setEditable(true);
        flavourCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        flavourCombo.setSelectedIndex(-1);
        flavourCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flavourComboActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Base Composition ");

        addNewBlendFlavourTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flavour", "Precentage (%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        addNewBlendFlavourTbl.setRowHeight(24);
        jScrollPane3.setViewportView(addNewBlendFlavourTbl);

        addNewBlendIngTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ingredient", "Precentage (%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        addNewBlendIngTbl.setRowHeight(24);
        jScrollPane4.setViewportView(addNewBlendIngTbl);

        baseCombo.setEditable(true);
        baseCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseComboActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Category");

        blendCategoryCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Artisian Special V2", "Artisian Special V26", "Black", "Green Tea", "Organic Black", "Organic Green", "Organic Infusion", "Infusions", "Iced Teas" }));
        blendCategoryCombo.setToolTipText("");
        blendCategoryCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blendCategoryComboActionPerformed(evt);
            }
        });

        dltIngBtn.setText("Delete Ingredient");
        dltIngBtn.setEnabled(false);
        dltIngBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dltIngBtnActionPerformed(evt);
            }
        });

        dltFlavourBtn.setText("Delete Flavour");
        dltFlavourBtn.setEnabled(false);
        dltFlavourBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dltFlavourBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cancelBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addNewBlendBtn)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(blendCategoryCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(flavourPerAddBtn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(186, 186, 186)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(baseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(blendNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(blendCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dltIngBtn)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(367, 367, 367)
                                    .addComponent(ingPerAddBtn))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(flavourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(flavourPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dltFlavourBtn)))))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blendNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(baseCombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(blendCategoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ingCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ingPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(ingPerAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(flavourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(flavourPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(flavourPerAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dltIngBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dltFlavourBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addNewBlendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void blendNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendNameTxtActionPerformed
        blendCategoryCombo.requestFocus();
    }//GEN-LAST:event_blendNameTxtActionPerformed

    private void flavourPerTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flavourPerTxtActionPerformed
        flavourPerAddBtn.requestFocus();
    }//GEN-LAST:event_flavourPerTxtActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void blendCodeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendCodeTxtActionPerformed
        blendNameTxt.requestFocus();
    }//GEN-LAST:event_blendCodeTxtActionPerformed

    private void ingPerAddBtnActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if (ingCombo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(ingCombo, "Please select an ingredient to add.", "Empty Ingredient Selection", 0);
            ingCombo.requestFocus();
            return;
        }
        if (ingPerTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(ingPerTxt, "Please enter an ingredient percentage to add.", "Empty Ingredient Percentage", 0);
            ingPerTxt.requestFocus();
            return;
        }
        String ingName = (String) ingCombo.getSelectedItem();
        float ingPer = Float.parseFloat(ingPerTxt.getText());
        boolean isNew = true;
        //Search if the ingredient is already added
        int rowCount = addNewBlendIngTbl.getRowCount();
        float totalPercentage = ingPer;
        for (int i = 0; i < rowCount; i++) {
            if (ingName.equals(addNewBlendIngTbl.getValueAt(i, 0))) {
                isNew = false;
            }
            totalPercentage += Float.parseFloat(addNewBlendIngTbl.getValueAt(i, 1).toString());
        }
        if (totalPercentage > 99) {
            JOptionPane.showMessageDialog(ingPerTxt, "Total of ingredient percentages cannot be greater than 100.", "Invalid Ingredient Percentages", 2);
            ingPerTxt.setText("");
            ingPerTxt.requestFocus();
            return;
        }
        if (isNew) {
            Vector newRow = new Vector();
            newRow.addElement(ingName);
            newRow.addElement(ingPer);

            DefaultTableModel model = (DefaultTableModel) addNewBlendIngTbl.getModel();
            model.addRow(newRow);
        } else {
            int dialogResult = JOptionPane.showConfirmDialog(addNewBlendIngTbl, "Ingredient already exists. Do you wish to update the precentage?", "Confirm Update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == JOptionPane.YES_OPTION){
                for (int i = 0; i < addNewBlendIngTbl.getRowCount(); i++) {
                    if (ingName.equals(addNewBlendIngTbl.getValueAt(i, 0))) {
                        float oldPercentage = Float.parseFloat(addNewBlendIngTbl.getValueAt(i, 1).toString());
                        addNewBlendIngTbl.setValueAt(ingPer + oldPercentage, i, 1);
                    }
                }
            }
        }
        
        ingPerTxt.setText("");
        ingCombo.setSelectedIndex(-1);
        ingCombo.requestFocus();
    }

    private void flavourComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flavourComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_flavourComboActionPerformed

    private void flavourPerAddBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        if (flavourCombo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(flavourCombo, "Please select a flavour to add.", "Empty Flavour Selection", 0);
            flavourCombo.requestFocus();
            return;
        }
        if (flavourPerTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(flavourPerTxt, "Please enter a flavour percentage to add.", "Empty Flavour Percentage", 0);
            flavourPerTxt.requestFocus();
            return;
        }
        String flavourName = (String) flavourCombo.getSelectedItem();
        float flavourPer = Float.parseFloat(flavourPerTxt.getText());
        boolean isNew = true;
        //Search if the ingredient is already added
        int rowCount = addNewBlendFlavourTbl.getRowCount();
        float totalPercentage = flavourPer;
        for (int i = 0; i < rowCount; i++) {
            if (flavourName.equals(addNewBlendFlavourTbl.getValueAt(i, 0))) {
                isNew = false;
            }
            totalPercentage += Float.parseFloat(addNewBlendFlavourTbl.getValueAt(i, 1).toString());
        }
        if (totalPercentage > 99) {
            JOptionPane.showMessageDialog(flavourPerTxt, "Total of flabour percentages cannot be greater than 100.", "Invalid Flavour Percentages", 2);
            flavourPerTxt.setText("");
            flavourPerTxt.requestFocus();
            return;
        }
        if (isNew) {
            Vector newRow = new Vector();
            newRow.addElement(flavourName);
            newRow.addElement(flavourPer);

            DefaultTableModel model = (DefaultTableModel) addNewBlendFlavourTbl.getModel();
            model.addRow(newRow);
        } else {
            int dialogResult = JOptionPane.showConfirmDialog(addNewBlendFlavourTbl, "Flavour already exists. Do you wish to update the precentage?", "Confirm Update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == JOptionPane.YES_OPTION){
                for (int i = 0; i < addNewBlendFlavourTbl.getRowCount(); i++) {
                    if (flavourName.equals(addNewBlendFlavourTbl.getValueAt(i, 0))) {
                        addNewBlendFlavourTbl.setValueAt(flavourPer, i, 1);
                    }
                }
            }
        }
        
        flavourPerTxt.setText("");
        flavourCombo.setSelectedIndex(-1);
        flavourCombo.requestFocus();

    }


    private void blendCategoryComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendCategoryComboActionPerformed
       
    }//GEN-LAST:event_blendCategoryComboActionPerformed

    String blendID, blendName, blendCategory, base;

    private void addNewBlendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewBlendBtnActionPerformed
        //validation
        if (blendCodeTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a blend code.", "Empty Fields", 2);
            blendCodeTxt.requestFocus();
            return;
        } else if (blend.checkExistingBlendID(blendCodeTxt.getText()) == 1) {
            JOptionPane.showMessageDialog(this, "Blend code already exists.", "Error", JOptionPane.WARNING_MESSAGE);
            blendCodeTxt.requestFocus();
            return;
        }
        
        if (blendNameTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add a blend name.", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            blendNameTxt.requestFocus();
            return;
        } else if (blend.checkExistingBlendName(blendCodeTxt.getText()) == 1) {
            JOptionPane.showMessageDialog(this, "Blend name already exists.", "Error", JOptionPane.WARNING_MESSAGE);
            blendNameTxt.requestFocus();
            return;
        }
        
        if (blendCategoryCombo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a blend category.", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            blendCategoryCombo.requestFocus();
            return;
        }
        
        if (baseCombo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a base composition.", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            baseCombo.requestFocus();
            return;
        }
        
        blendID = blendCodeTxt.getText();
        blendName = blendNameTxt.getText();
        blendCategory = blendCategoryCombo.getSelectedItem().toString();
        base = baseCombo.getSelectedItem().toString();

        float ingPerCount = 0;
        float flavPerCount = 0;
        int ingCount = addNewBlendIngTbl.getRowCount();
        int flavCount = addNewBlendFlavourTbl.getRowCount();
        int recCount = ingCount + flavCount;

        if (ingCount == 0) {
            JOptionPane.showMessageDialog(this, "Blend must have at least one ingredient", "No Ingredient Added", 0);
            return;
        }
        if (flavCount == 0) {
            for (int i = 0; i < ingCount; i++) {
                float initPer = Float.parseFloat(addNewBlendIngTbl.getValueAt(i, 1).toString());;
                ingPerCount = ingPerCount + initPer;
            }

            if (ingPerCount <= 0 || ingPerCount >= 100) {
                JOptionPane.showMessageDialog(this, "Invalid percentage");

            } else {
                int ret = blend.addNewBlend(blendID, blendName, base, blendCategory);

                ArrayList<Integer> ingID = new ArrayList<>();
                for (int i = 0; i < ingCount; i++) {
                    Blend a = new Blend();
                    ingID.add(a.getIngIDRecByIngName(addNewBlendIngTbl.getValueAt(i, 0).toString()));
                }

                int x = 0;
                for (int i = 0; i < ingCount; i++) {
                    int a = ingID.get(i);
                    double b = Double.parseDouble(addNewBlendIngTbl.getValueAt(i, 1).toString());
                    String query1 = "INSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('" + blendID + "','" + a + "','" + b + "',0)";
                    x = dbConn.updateResult(query1);
                }

                if (x == 1) {
                    JOptionPane.showMessageDialog(this, "New Blend Added Successfuly.", "Successflly Added", 1);
                    close();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Unable to add the new ingredient.Please try again.", "Unable to add", 0);
                }
            }

        } else {
            //addedboth ing and flavour data tor ecipie
            for (int i = 0; i < ingCount; i++) {
                float initPer = Float.parseFloat(addNewBlendIngTbl.getValueAt(i, 1).toString());;
                ingPerCount = ingPerCount + initPer;
            }

            for (int i = 0; i < flavCount; i++) {
                float initPer = Float.parseFloat(addNewBlendFlavourTbl.getValueAt(i, 1).toString());;
                flavPerCount = flavPerCount + initPer;
            }

            if (ingPerCount <= 0 || ingPerCount >= 100) {
                JOptionPane.showMessageDialog(this, "Invalid percentage");

            } else if (flavPerCount <= 0 || flavPerCount >= 100) {
                JOptionPane.showMessageDialog(this, "Invalid percentage");
            } else {
                int ret = blend.addNewBlend(blendID, blendName, base, blendCategory);

                ArrayList<Integer> ingID = new ArrayList<>();
                ArrayList<Integer> flavourID = new ArrayList<>();
                for (int i = 0; i < ingCount; i++) {
                    Blend a = new Blend();

                    ingID.add(a.getIngIDRecByIngName(addNewBlendIngTbl.getValueAt(i, 0).toString()));
                }

                for (int i = 0; i < flavCount; i++) {
                    Blend a = new Blend();
                    flavourID.add(a.getIngIDRecByIngName(addNewBlendFlavourTbl.getValueAt(i, 0).toString()));
                }
                int x = 0;
                int y = 0;
                for (int i = 0; i < ingCount; i++) {
                    int a = ingID.get(i);
                    double b = Double.parseDouble(addNewBlendIngTbl.getValueAt(i, 1).toString());
                    String query1 = "INSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('" + blendID + "','" + a + "','" + b + "',0)";
                    x = dbConn.updateResult(query1);

                }

                for (int j = 0; j < flavCount; j++) {
                    int c = flavourID.get(j);
                    double d = Double.parseDouble(addNewBlendFlavourTbl.getValueAt(j, 1).toString());
                    String query2 = "INSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('" + blendID + "','" + c + "','" + d + "',1)";
                    y = dbConn.updateResult(query2);

                }

                if (x == 1 && y == 1) {
                    JOptionPane.showMessageDialog(this, "New Blend Added Successfuly.", "Successflly Added", 1);
                    close();

                } else {
                    JOptionPane.showMessageDialog(this, "Unable to add the new ingredient.Please try again.", "Unable to add", 0);
                }

            }
        }
     

    }//GEN-LAST:event_addNewBlendBtnActionPerformed


    private void ingPerTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingPerTxtActionPerformed
        ingPerAddBtn.requestFocus();
    }//GEN-LAST:event_ingPerTxtActionPerformed

    private void baseComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseComboActionPerformed
        
    }//GEN-LAST:event_baseComboActionPerformed

    private void ingComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingComboActionPerformed

    private void dltIngBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dltIngBtnActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(addNewBlendIngTbl, "Please confirm record deletion", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (dialogResult == JOptionPane.YES_OPTION){
            DefaultTableModel model = (DefaultTableModel)addNewBlendIngTbl.getModel();
            model.removeRow(addNewBlendIngTbl.getSelectedRow());
            dltIngBtn.setEnabled(false);
        }
    }//GEN-LAST:event_dltIngBtnActionPerformed

    private void dltFlavourBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dltFlavourBtnActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(addNewBlendFlavourTbl, "Please confirm record deletion", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (dialogResult == JOptionPane.YES_OPTION){
            DefaultTableModel model = (DefaultTableModel)addNewBlendFlavourTbl.getModel();
            model.removeRow(addNewBlendFlavourTbl.getSelectedRow());
            dltFlavourBtn.setEnabled(false);
        }
    }//GEN-LAST:event_dltFlavourBtnActionPerformed

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
            java.util.logging.Logger.getLogger(AddNewBlend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewBlend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewBlend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewBlend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNewBlend().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewBlendBtn;
    private javax.swing.JTable addNewBlendFlavourTbl;
    private javax.swing.JTable addNewBlendIngTbl;
    private javax.swing.JComboBox baseCombo;
    private javax.swing.JComboBox blendCategoryCombo;
    private javax.swing.JTextField blendCodeTxt;
    private javax.swing.JTextField blendNameTxt;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton dltFlavourBtn;
    private javax.swing.JButton dltIngBtn;
    private javax.swing.JComboBox flavourCombo;
    private javax.swing.JButton flavourPerAddBtn;
    private javax.swing.JTextField flavourPerTxt;
    private javax.swing.JComboBox ingCombo;
    private javax.swing.JButton ingPerAddBtn;
    private javax.swing.JTextField ingPerTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
