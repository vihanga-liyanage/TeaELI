package teaeli;

import classes.Blend;
import classes.DBConnection;
import classes.Ingredient;
import classes.ResultArray;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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
        Dimension screenSize, frameSize;
        int x, y;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameSize = getSize();
        x = (screenSize.width - frameSize.width) / 2;
        y = (screenSize.height - frameSize.height) / 2;
        setLocation(x, y);
        setResizable(false);

        //Changing table headers to bold
        ingTable.getTableHeader().setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        flavourTable.getTableHeader().setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        setResizable(false);

        Ingredient ingredient = new Ingredient();
        ingredient.initIngCombo(ingCombo);

        Ingredient base = new Ingredient();
        base.initIngCombo(baseCombo);

        Ingredient flavour = new Ingredient();
        flavour.initFlavourCombo(flavoursCombo);

        //Validation on Ing percentage, when key pressed
        ingPerTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        String per = ingPerTxt.getText();
                        if (!per.equalsIgnoreCase("")) {
                            Float val = 0f;
                            try{
                                val = Float.parseFloat(per);
                                if(val < 0){
                                    JOptionPane.showMessageDialog(ingPerTxt, "Ingredient percentage cannot be less than 0", "Invalid Ingredient Percentage", 2);
                                    ingPerTxt.setText("");
                                }
                            }catch(NumberFormatException ex){
                                JOptionPane.showMessageDialog(ingPerTxt, "Ingredient percentage must be a valid number", "Invalid Ingredient Percentage", 2);
                                ingPerTxt.setText("");
                            }
                        }
                    }
                });
            }
        });
        
        //Validation on Flavour percentage, when key pressed
        flavoursPerTxt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        String per = flavoursPerTxt.getText();
                        if (!per.equalsIgnoreCase("")) {
                            Float val = 0f;
                            try{
                                val = Float.parseFloat(per);
                                if(val < 0){
                                    JOptionPane.showMessageDialog(flavoursPerTxt, "Flavour percentage cannot be less than 0", "Invalid Ingredient Percentage", 2);
                                    flavoursPerTxt.setText("");
                                }
                            }catch(NumberFormatException ex){
                                JOptionPane.showMessageDialog(flavoursPerTxt, "Flavour percentage must be a valid number", "Invalid Ingredient Percentage", 2);
                                flavoursPerTxt.setText("");
                            }
                        }
                    }
                });
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
        
        //setting focus to flavour per txt when item selected
        flavoursCombo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    flavoursPerTxt.requestFocus();
                }
            }
        });
        
        //enabling delete button on row select
        final ListSelectionModel ingModel = ingTable.getSelectionModel();
        ingModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!ingModel.isSelectionEmpty()) {
                    btnDeleteIng.setEnabled(true);
                }
            }
        });
        final ListSelectionModel flvModel = flavourTable.getSelectionModel();
        flvModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!flvModel.isSelectionEmpty()) {
                    btnDeleteFlv.setEnabled(true);
                }
            }
        });
        
        ingredient1 = new Ingredient();
        blend = new Blend();
        
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
        blendAddnewBtn = new javax.swing.JButton();
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
        blendUpdateBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        updateRadioBtn = new javax.swing.JRadioButton();
        addnewRadioBtn = new javax.swing.JRadioButton();
        btnDeleteFlv = new javax.swing.JButton();
        btnDeleteIng = new javax.swing.JButton();

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

        flavoursPerAddBtn.setText("Add");
        flavoursPerAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flavoursPerAddBtnActionPerformed(evt);
            }
        });

        blendAddnewBtn.setText("Add as new");
        blendAddnewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blendAddnewBtnActionPerformed(evt);
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
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ingredient", "Percentage (%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ingTable.setRowHeight(20);
        jScrollPane3.setViewportView(ingTable);

        flavourTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Flavour", "Percentage (%)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        flavourTable.setRowHeight(20);
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

        blendCategoryCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Artisian Special V2", "Artisian Special V26", "Black", "Green Tea", "Organic Black", "Organic Green", "Organic Infusion", "Infusions", "Iced Teas" }));
        blendCategoryCombo.setToolTipText("");
        blendCategoryCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blendCategoryComboActionPerformed(evt);
            }
        });

        blendUpdateBtn.setText("Update");
        blendUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blendUpdateBtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Select Your Choice");

        updateRadioBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateRadioBtn.setText("Update");
        updateRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRadioBtnActionPerformed(evt);
            }
        });

        addnewRadioBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addnewRadioBtn.setText("Add As New");
        addnewRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addnewRadioBtnActionPerformed(evt);
            }
        });

        btnDeleteFlv.setText("Delete Flavour");
        btnDeleteFlv.setEnabled(false);
        btnDeleteFlv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFlvActionPerformed(evt);
            }
        });

        btnDeleteIng.setText("Delete Ingerdient");
        btnDeleteIng.setEnabled(false);
        btnDeleteIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteIngActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(10, 10, 10)
                                .addComponent(ingPerAddBtn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(updateRadioBtn)
                                .addGap(18, 18, 18)
                                .addComponent(addnewRadioBtn)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(flavoursCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(flavoursPerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(flavoursPerAddBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blendCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(category)
                                .addGap(18, 18, 18)
                                .addComponent(blendCategoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(blendNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(baseCombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blendUpdateBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDeleteIng)
                                .addGap(344, 344, 344)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDeleteFlv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(blendAddnewBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(updateRadioBtn)
                    .addComponent(addnewRadioBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendCategoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(baseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteFlv)
                    .addComponent(btnDeleteIng))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blendAddnewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void blendNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendNameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blendNameTxtActionPerformed

    private void flavoursPerTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flavoursPerTxtActionPerformed
        flavoursPerAddBtn.requestFocus();
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
        int rowCount = ingTable.getRowCount();
        float totalPercentage = ingPer;
        for (int i = 0; i < rowCount; i++) {
            if (ingName.equals(ingTable.getValueAt(i, 0))) {
                isNew = false;
            }
            totalPercentage += Float.parseFloat(ingTable.getValueAt(i, 1).toString());
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

            DefaultTableModel model = (DefaultTableModel) ingTable.getModel();
            model.addRow(newRow);
        } else {
            int dialogResult = JOptionPane.showConfirmDialog(ingTable, "Ingredient already exists. Do you wish to update the precentage?", "Confirm Update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == JOptionPane.YES_OPTION){
                for (int i = 0; i < ingTable.getRowCount(); i++) {
                    if (ingName.equals(ingTable.getValueAt(i, 0))) {
                        float oldPercentage = Float.parseFloat(ingTable.getValueAt(i, 1).toString());
                        ingTable.setValueAt(ingPer + oldPercentage, i, 1);
                    }
                }
            }
        }
        
        ingPerTxt.setText("");
        ingCombo.setSelectedIndex(-1);
        ingCombo.requestFocus();
        
    }//GEN-LAST:event_ingPerAddBtnActionPerformed


    private void flavoursPerAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flavoursPerAddBtnActionPerformed
        if (flavoursCombo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(flavoursCombo, "Please select a flavour to add.", "Empty Flavour Selection", 2);
            flavoursCombo.requestFocus();
            return;
        }
        if (flavoursPerTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(flavoursPerTxt, "Please enter a flavour percentage to add.", "Empty Flavour Percentage", 2);
            flavoursPerTxt.requestFocus();
            return;
        }
        String flavourName = (String) flavoursCombo.getSelectedItem();
        float flavourPer = Float.parseFloat(flavoursPerTxt.getText());
        boolean isNew = true;
        //Search if the ingredient is already added
        int rowCount = flavourTable.getRowCount();
        float totalPercentage = flavourPer;
        for (int i = 0; i < rowCount; i++) {
            if (flavourName.equals(flavourTable.getValueAt(i, 0))) {
                isNew = false;
            }
            totalPercentage += Float.parseFloat(flavourTable.getValueAt(i, 1).toString());
        }
        if (totalPercentage > 99) {
            JOptionPane.showMessageDialog(flavoursPerTxt, "Total of flabour percentages cannot be greater than 100.", "Invalid Flavour Percentages", 2);
            flavoursPerTxt.setText("");
            flavoursPerTxt.requestFocus();
            return;
        }
        if (isNew) {
            Vector newRow = new Vector();
            newRow.addElement(flavourName);
            newRow.addElement(flavourPer);

            DefaultTableModel model = (DefaultTableModel) flavourTable.getModel();
            model.addRow(newRow);
        } else {
            int dialogResult = JOptionPane.showConfirmDialog(flavourTable, "Flavour already exists. Do you wish to update the precentage?", "Confirm Update", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (dialogResult == JOptionPane.YES_OPTION){
                for (int i = 0; i < flavourTable.getRowCount(); i++) {
                    if (flavourName.equals(flavourTable.getValueAt(i, 0))) {
                        flavourTable.setValueAt(flavourPer, i, 1);
                    }
                }
            }
        }
        
        flavoursPerTxt.setText("");
        flavoursCombo.setSelectedIndex(-1);
        flavoursCombo.requestFocus();
    }//GEN-LAST:event_flavoursPerAddBtnActionPerformed

    String blendID, blendName, blendCategory, base;//Decalration for add new blend data

    private void blendAddnewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendAddnewBtnActionPerformed
        try {
            ingTable.getCellEditor().stopCellEditing();
            flavourTable.getCellEditor().stopCellEditing();
        } catch (NullPointerException ex) {
           
        }
        
        blendID = blendCodeTxt.getText();
        blendName = blendNameTxt.getText();
        blendCategory = blendCategoryCombo.getSelectedItem().toString();
       
        base = baseCombo.getSelectedItem().toString();
        DefaultComboBoxModel model = (DefaultComboBoxModel) baseCombo.getModel();
        if(model.getIndexOf(base) == -1){
            JOptionPane.showMessageDialog(this, "Please select a valid base composition", "Invalid Base Composition", 2);
            return;
        }
        
        int ID, Name;
        ID = blend.checkExistingBlendID(blendID);
        Name = blend.checkExistingBlendName(blendName);
        if (blendID.isEmpty() || blendName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Every field must be filled", "Empty Fields", 2);
        } else {
            if (ID != 0) {
                JOptionPane.showMessageDialog(this, "This ID is already Exsists", "Duplicate Blend ID", 0);
                blendCodeTxt.setText("");
                blendCodeTxt.requestFocus();
            } else {
                if (Name != 0) {
                    JOptionPane.showMessageDialog(this, "This Name is already Exsists", "Duplicate Blend Name", 0);
                    blendNameTxt.setText("");
                    blendNameTxt.requestFocus();
                } else {
                    float ingPerCount = 0;
                    float flavPerCount = 0;
                    int ingCount = ingTable.getRowCount();
                    int flavCount = flavourTable.getRowCount();
                    int recCount = ingCount + flavCount;

                    if (ingCount == 0) {
                        JOptionPane.showMessageDialog(this, "A blend must have at least one ingredient", "No Ingredient Added", 0);
                    } else {
                        if (flavCount == 0) {
                            for (int i = 0; i < ingCount; i++) {
                                float initPer = Float.parseFloat(ingTable.getValueAt(i, 1).toString());;
                                ingPerCount = ingPerCount + initPer;
                            }

                            if (ingPerCount <= 0 || ingPerCount >= 100) {
                                JOptionPane.showMessageDialog(this, "Invalid percentage");

                            } else {
                                int ret = blend.addNewBlend(blendID, blendName, base, blendCategory);

                                ArrayList<Integer> ingID = new ArrayList<>();
                                for (int i = 0; i < ingCount; i++) {
                                    Blend a = new Blend();
                                    ingID.add(a.getIngIDRecByIngName(ingTable.getValueAt(i, 0).toString()));
                                }

                                int x = 0;
                                for (int i = 0; i < ingCount; i++) {
                                    int a = ingID.get(i);
                                    double b = Double.parseDouble(ingTable.getValueAt(i, 1).toString());
                                    String query1 = "INSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('" + blendID + "','" + a + "','" + b + "',0)";
                                    x = dbConn.updateResult(query1);
                                }

                                if (x == 1) {
                                    JOptionPane.showMessageDialog(this, "New Blend Added Successfuly.", "Successflly Added", 1);
                                    this.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(this, "There were some issues with the database. Please contact developers.\n\nError code : BlendDetails 634", "Error", 0);
                                    System.exit(0);
                                }
                            }

                        } else {
                            //addedboth ing and flavour data to recipie
                            for (int i = 0; i < ingCount; i++) {
                                float initPer = Float.parseFloat(ingTable.getValueAt(i, 1).toString());;
                                ingPerCount = ingPerCount + initPer;
                            }

                            for (int i = 0; i < flavCount; i++) {
                                float initPer = Float.parseFloat(flavourTable.getValueAt(i, 1).toString());;
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

                                    ingID.add(a.getIngIDRecByIngName(ingTable.getValueAt(i, 0).toString()));
                                }

                                for (int i = 0; i < flavCount; i++) {
                                    Blend a = new Blend();
                                    //ingID.add(Integer.parseInt(addNewBlendIngTbl.getValueAt(i, 0).toString()));
                                    flavourID.add(a.getIngIDRecByIngName(flavourTable.getValueAt(i, 0).toString()));
                                }
                                int x = 0;
                                int y = 0;
                                for (int i = 0; i < ingCount; i++) {
                                    int a = ingID.get(i);
                                    double b = Double.parseDouble(ingTable.getValueAt(i, 1).toString());
                                    String query1 = "INSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('" + blendID + "','" + a + "','" + b + "',0)";
                                    x = dbConn.updateResult(query1);

                                }

                                for (int j = 0; j < flavCount; j++) {
                                    int c = flavourID.get(j);
                                    double d = Double.parseDouble(flavourTable.getValueAt(j, 1).toString());
                                    String query2 = "INSERT INTO recipie (blendID, ingID, ingPercent, type) VALUES ('" + blendID + "','" + c + "','" + d + "',1)";
                                    y = dbConn.updateResult(query2);

                                }

                                if (x == 1 && y == 1) {
                                    JOptionPane.showMessageDialog(this, "New Blend Added Successfuly.", "Successflly Added", 1);
                                    this.dispose();
                                    //this.setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(this, "There were some issues with the database. Please contact developers.\n\nError code : BlendDetails 695", "Error", 0);
                                    System.exit(0);
                                }

                            }
                        }
                    }

                }
            }
        }
    }//GEN-LAST:event_blendAddnewBtnActionPerformed

    private void updateRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateRadioBtnActionPerformed
        if (updateRadioBtn.isSelected()) {
            addnewRadioBtn.setSelected(false);
            int a = JOptionPane.showConfirmDialog(this, "Updating a blend would result changing all the past records regarding it.\nAre you sure you want to update?", "Update Warning", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION){
                blendUpdateBtn.setEnabled(true);
                ingPerAddBtn.setEnabled(false);
                flavoursPerAddBtn.setEnabled(false);
                blendAddnewBtn.setEnabled(false);
                blendCodeTxt.setEditable(false);
                blendNameTxt.setEditable(false);
                addnewRadioBtn.setSelected(false);
                blendCategoryCombo.setEnabled(true);
                baseCombo.setEnabled(true);

                ingCombo.setEnabled(false);
                ingPerTxt.setEnabled(false);
                flavoursCombo.setEnabled(false);
                flavoursPerTxt.setEnabled(false);
                ingTable.setEnabled(true);
                flavourTable.setEnabled(true);
            }else{
                updateRadioBtn.setSelected(false);
                
                blendUpdateBtn.setEnabled(false);
                ingPerAddBtn.setEnabled(false);
                flavoursPerAddBtn.setEnabled(false);
                blendAddnewBtn.setEnabled(false);
                blendCodeTxt.setEditable(false);
                blendNameTxt.setEditable(false);
                addnewRadioBtn.setSelected(false);
                blendCategoryCombo.setEnabled(false);
                baseCombo.setEnabled(false);

                ingCombo.setEnabled(false);
                ingPerTxt.setEnabled(false);
                flavoursCombo.setEnabled(false);
                flavoursPerTxt.setEnabled(false);
                
                ingTable.setEnabled(false);
                flavourTable.setEnabled(false);
                btnDeleteIng.setEnabled(false);
                btnDeleteFlv.setEnabled(false);
            }
            
        }else{
            blendUpdateBtn.setEnabled(false);
            ingPerAddBtn.setEnabled(false);
            flavoursPerAddBtn.setEnabled(false);
            blendAddnewBtn.setEnabled(false);
            blendCodeTxt.setEditable(false);
            blendNameTxt.setEditable(false);
            addnewRadioBtn.setSelected(false);
            blendCategoryCombo.setEnabled(false);
            baseCombo.setEnabled(false);

            ingCombo.setEnabled(false);
            ingPerTxt.setEnabled(false);
            flavoursCombo.setEnabled(false);
            flavoursPerTxt.setEnabled(false);
            
            ingTable.setEnabled(false);
            flavourTable.setEnabled(false);
            btnDeleteIng.setEnabled(false);
            btnDeleteFlv.setEnabled(false);
        }
    }//GEN-LAST:event_updateRadioBtnActionPerformed

    private void addnewRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addnewRadioBtnActionPerformed
        if (addnewRadioBtn.isSelected()) {
            updateRadioBtn.setSelected(false);
            
            blendAddnewBtn.setEnabled(true);
            ingPerAddBtn.setEnabled(true);
            flavoursPerAddBtn.setEnabled(true);
            blendCodeTxt.setEditable(true);
            blendNameTxt.setEditable(true);
            blendUpdateBtn.setEnabled(false);
            updateRadioBtn.setSelected(false);
            blendCategoryCombo.setEnabled(true);
            baseCombo.setEnabled(true);
            
            ingCombo.setEnabled(true);
            ingPerTxt.setEnabled(true);
            flavoursCombo.setEnabled(true);
            flavoursPerTxt.setEnabled(true);
            ingTable.setEnabled(true);
            flavourTable.setEnabled(true);
        }else{
            blendAddnewBtn.setEnabled(false);
            ingPerAddBtn.setEnabled(false);
            flavoursPerAddBtn.setEnabled(false);
            blendCodeTxt.setEditable(false);
            blendNameTxt.setEditable(false);
            blendUpdateBtn.setEnabled(false);
            updateRadioBtn.setSelected(false);
            blendCategoryCombo.setEnabled(false);
            baseCombo.setEnabled(false);
            
            ingCombo.setEnabled(false);
            ingPerTxt.setEnabled(false);
            flavoursCombo.setEnabled(false);
            flavoursPerTxt.setEnabled(false);
            
            ingTable.setEnabled(false);
            flavourTable.setEnabled(false);
            btnDeleteIng.setEnabled(false);
            btnDeleteFlv.setEnabled(false);
        }
    }//GEN-LAST:event_addnewRadioBtnActionPerformed

    private void blendUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendUpdateBtnActionPerformed
        try {
            ingTable.getCellEditor().stopCellEditing();
            flavourTable.getCellEditor().stopCellEditing();
        } catch (NullPointerException ex) {

        }
        
        blendID = blendCodeTxt.getText();
        blendName = blendNameTxt.getText();
        blendCategory = blendCategoryCombo.getSelectedItem().toString();
        
        base = baseCombo.getSelectedItem().toString();
        DefaultComboBoxModel model = (DefaultComboBoxModel) baseCombo.getModel();
        if(model.getIndexOf(base) == -1){
            JOptionPane.showMessageDialog(this, "Please select a valid base composition", "Invalid Base Composition", 2);
            return;
        }

        float ingPerCount = 0;
        float flavPerCount = 0;
        int ingCount = ingTable.getRowCount();
        int flavCount = flavourTable.getRowCount();
        int recCount = ingCount + flavCount;

        if (flavCount == 0) {
            for (int i = 0; i < ingCount; i++) {
                float initPer = Float.parseFloat(ingTable.getValueAt(i, 1).toString());;
                ingPerCount = ingPerCount + initPer;
            }

            if (ingPerCount <= 0 || ingPerCount >= 100) {
                JOptionPane.showMessageDialog(this, "Invalid percentage");

            } else {
                int ret = blend.updateBlend(blendID, blendName, base, blendCategory);

                ArrayList<Integer> ingID = new ArrayList<>();
                for (int i = 0; i < ingCount; i++) {
                    Blend a = new Blend();
                    ingID.add(a.getIngIDRecByIngName(ingTable.getValueAt(i, 0).toString()));
                }

                int x = 0;
                for (int i = 0; i < ingCount; i++) {
                    int a = ingID.get(i);
                    double b = Double.parseDouble(ingTable.getValueAt(i, 1).toString());
                    String query1 = "UPDATE recipie SET ingID = '" + a + "',ingPercent = '" + b + "' WHERE blendID = '" + blendID + "'  ";
                    x = dbConn.updateResult(query1);
                }

                if (x == 1) {
                    JOptionPane.showMessageDialog(this, "Blend Updated Successfuly.", "Successflly Updated", 1);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "There were some issues with the database. Please contact developers.\n\nError code : BlendDetails 789", "Error", 0);
                    System.exit(0);
                }
            }

        } else if (ingCount > 0 && flavCount > 0) {
            //addedboth ing and flavour data to recipie
            for (int i = 0; i < ingCount; i++) {
                float initPer = Float.parseFloat(ingTable.getValueAt(i, 1).toString());;
                ingPerCount = ingPerCount + initPer;
            }

            for (int i = 0; i < flavCount; i++) {
                float initPer = Float.parseFloat(flavourTable.getValueAt(i, 1).toString());;
                flavPerCount = flavPerCount + initPer;
            }

            if (ingPerCount <= 0 || ingPerCount >= 100) {
                JOptionPane.showMessageDialog(this, "Invalid percentage");

            } else if (flavPerCount <= 0 || flavPerCount >= 100) {
                JOptionPane.showMessageDialog(this, "Invalid percentage");
            } else {
                int ret = blend.updateBlend(blendID, blendName, base, blendCategory);

                ArrayList<Integer> ingID = new ArrayList<>();
                ArrayList<Integer> flavourID = new ArrayList<>();
                for (int i = 0; i < ingCount; i++) {
                    Blend a = new Blend();

                    ingID.add(a.getIngIDRecByIngName(ingTable.getValueAt(i, 0).toString()));
                }

                for (int i = 0; i < flavCount; i++) {
                    Blend a = new Blend();
                    //ingID.add(Integer.parseInt(addNewBlendIngTbl.getValueAt(i, 0).toString()));
                    flavourID.add(a.getIngIDRecByIngName(flavourTable.getValueAt(i, 0).toString()));
                }
                int x = 0;
                int y = 0;
                for (int i = 0; i < ingCount; i++) {
                    int a = ingID.get(i);
                    double b = Double.parseDouble(ingTable.getValueAt(i, 1).toString());
                    String query1 = "UPDATE recipie SET ingID = '" + a + "',ingPercent = '" + b + "' WHERE blendID = '" + blendID + "' AND ingID = '" + a + "' AND type=0 ";
                    x = dbConn.updateResult(query1);

                }

                for (int j = 0; j < flavCount; j++) {
                    int c = flavourID.get(j);
                    double d = Double.parseDouble(flavourTable.getValueAt(j, 1).toString());
                    String query2 = "UPDATE recipie SET ingID = '" + c + "',ingPercent = '" + d + "' WHERE blendID = '" + blendID + "' AND ingID = '" + c + "'AND type=1  ";
                    y = dbConn.updateResult(query2);

                }

                if (x == 1 && y == 1) {
                   JOptionPane.showMessageDialog(this, "Blend Updated Successfuly.", "Successflly Updated", 1);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "There were some issues with the database. Please contact developers.\n\nError code : BlendDetails 849", "Error", 0);
                    System.exit(0);
                }

            }
        }
    }//GEN-LAST:event_blendUpdateBtnActionPerformed

    private void ingPerTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingPerTxtActionPerformed
        ingPerAddBtn.requestFocus();
    }//GEN-LAST:event_ingPerTxtActionPerformed

    private void btnDeleteFlvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFlvActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(flavourTable, "Please confirm record deletion", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (dialogResult == JOptionPane.YES_OPTION){
            DefaultTableModel model = (DefaultTableModel)flavourTable.getModel();
            model.removeRow(flavourTable.getSelectedRow());
            btnDeleteFlv.setEnabled(false);
        }
    }//GEN-LAST:event_btnDeleteFlvActionPerformed

    private void btnDeleteIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteIngActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(ingTable, "Please confirm record deletion", "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (dialogResult == JOptionPane.YES_OPTION){
            DefaultTableModel model = (DefaultTableModel)ingTable.getModel();
            model.removeRow(ingTable.getSelectedRow());
            btnDeleteIng.setEnabled(false);
        }
    }//GEN-LAST:event_btnDeleteIngActionPerformed

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
    private javax.swing.JRadioButton addnewRadioBtn;
    public javax.swing.JComboBox baseCombo;
    public javax.swing.JButton blendAddnewBtn;
    public javax.swing.JComboBox blendCategoryCombo;
    public javax.swing.JTextField blendCodeTxt;
    public javax.swing.JTextField blendNameTxt;
    public javax.swing.JButton blendUpdateBtn;
    private javax.swing.JButton btnDeleteFlv;
    private javax.swing.JButton btnDeleteIng;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel category;
    public javax.swing.JTable flavourTable;
    public javax.swing.JComboBox flavoursCombo;
    public javax.swing.JButton flavoursPerAddBtn;
    public javax.swing.JTextField flavoursPerTxt;
    public javax.swing.JComboBox ingCombo;
    public javax.swing.JButton ingPerAddBtn;
    public javax.swing.JTextField ingPerTxt;
    public javax.swing.JTable ingTable;
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
    private javax.swing.JRadioButton updateRadioBtn;
    // End of variables declaration//GEN-END:variables

    void identifyBlend(String blendID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getBlendNameByBlendID(String blendID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
