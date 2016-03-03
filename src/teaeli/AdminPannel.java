package teaeli;

import classes.Blend;
import classes.Ingredient;
import classes.StockHistory;
import classes.DBConnection;
import classes.AutoSuggest;
import classes.Order;
import classes.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import classes.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import static teaeli.TeaELI.loginFrame;

public class AdminPannel extends javax.swing.JFrame {

    User user = new User();
    Ingredient ingredient = new Ingredient();
    Blend blend = new Blend();
    StockHistory blendHistoryStock = new StockHistory();
    StockHistory ingredientHistoryStock = new StockHistory();
    public static IngredientDetails ingredientDetails = new IngredientDetails();
    Order order = new Order();

    /**
     * Creates new form AdminPannel
     */
    public AdminPannel() {

        try {
            setUIFont(new javax.swing.plaf.FontUIResource("Segoe UI", Font.PLAIN, 14));
        } catch (Exception e) {
        }

        //Changing look and feel
        //for metal - javax.swing.plaf.metal.MetalLookAndFeel
        //for windows - com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AdminPannel.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();

        startClock();

        //method to view the selected row details of a jtable
        final ListSelectionModel mod = productTable.getSelectionModel();
        mod.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!mod.isSelectionEmpty()) {
                    int row = mod.getMinSelectionIndex();
                    JOptionPane.showMessageDialog(null, productTable.getValueAt(row, 0));
                }
            }

        });

        //view the blend details when enter key pressed  in blends tab
        searchBlendComboBox.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                BlendDetails updateProduct = new BlendDetails();
                updateProduct.setVisible(true);
                updateProduct.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                searchBlendComboBox.setSelectedIndex(-1);
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }
        });

        //set all users details to the users table in the users tab
        populateUserTable();

        /*Start of ingredient class method calls*/
        //populate serch ingredient combobox in settings->ingredient
        AutoSuggest searchIngredientComboBoxAutoSuggest = new AutoSuggest();

        //searchIngredientComboBoxAutoSuggest.setAutoSuggest(searchIngredientComboBox, ingredient.loadNameForSearchStockIngComboBox2());

        searchIngredientComboBoxAutoSuggest.setAutoSuggest(searchIngredientComboBox, ingredient.loadNameForSearchStockIngComboBox());


        //start of view all ingredients
        try {
            ingredient.viewAllIngredients();

        } catch (SQLException ex) {
            //Logger.getLogger(AdminPannel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL error in view all ingredients method" + ex);
        }
        //end of view all ingredients

        /* populate inventryIngredientTable in inventory management -- dont remove this*/
        populateIngStockTable();

        /*populate inventryBlendTable in the inventory management*/
        populateBlendStockTable();

        /*Populate ingredientstock history*/
        populateIngHistoryTable();

        /*Populate blendstock history*/
        populateBlendHistoryTable();

        /* populate product table in the blend tab*/
        blend.populateProductTable((DefaultTableModel) productTable.getModel());

        /*populate main order table in the order details tab*/
        order.populateOrderListTable((DefaultTableModel) orderListTable.getModel());

        /*load the orderComboBox in the order details tab*/
        AutoSuggest searchOrderComboBoxAutoSuggest = new AutoSuggest();

        //ArrayList<String> result = order.populateOrderListTable((DefaultTableModel) orderListTable.getModel());
        //searchOrderComboBoxAutoSuggest.setAutoSuggestTwo(orderSearchCombo, result);

        searchOrderComboBoxAutoSuggest.setAutoSuggest(orderSearchCombo, order.loadOrderComboBox());

        orderSearchCombo.setSelectedIndex(-1);

        /* combox auto suggests in inventory management */
        AutoSuggest searchStockIngComboBoxAutoSuggest = new AutoSuggest();

        //searchStockIngComboBoxAutoSuggest.setAutoSuggestTwo(searchStockIngComboBox, ingredient.loadNameForSearchStockIngComboBox2());

        searchStockIngComboBoxAutoSuggest.setAutoSuggest(searchStockIngComboBox, ingredient.loadNameForSearchStockIngComboBox());



        searchStockIngComboBox.setSelectedIndex(-1);


        AutoSuggest searchStockBlendComboBoxAutoSuggest = new AutoSuggest();
        searchStockBlendComboBoxAutoSuggest.setAutoSuggest(searchStockBlendComboBox, blend.loadNameForSearchStockBlendsComboBox());

        searchStockBlendComboBox.setSelectedIndex(-1);

        /*Auto suggest method loads for the combo box in blends tab*/
        blend.initBlendCombo(searchBlendComboBox);

        //method for combox value setting when table row select in inventryIngredient
        final ListSelectionModel selectionalModForStockIngTable = inventryIngredientTable.getSelectionModel();
        selectionalModForStockIngTable.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lsevt) {
                if (!selectionalModForStockIngTable.isSelectionEmpty()) {
                    int row = selectionalModForStockIngTable.getMinSelectionIndex();
                    searchStockIngComboBox.setSelectedItem(inventryIngredientTable.getValueAt(row, 1));
                }
            }

        });

        //methods for combox value settings when table row select in inventryBlend
        final ListSelectionModel selectionalModForStockBlendTable = inventryBlendTable.getSelectionModel();
        selectionalModForStockBlendTable.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lsevt) {
                if (!selectionalModForStockBlendTable.isSelectionEmpty()) {
                    int row = selectionalModForStockBlendTable.getMinSelectionIndex();
                    searchStockBlendComboBox.setSelectedItem(inventryBlendTable.getValueAt(row, 1));
                }
            }

        });

        //method for combox value setting when table row select in settings Ingredient
        final ListSelectionModel selectionalModForSettingsIngTable = settingsIngredientTable.getSelectionModel();
        selectionalModForSettingsIngTable.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lsevt) {
                if (!selectionalModForSettingsIngTable.isSelectionEmpty()) {
                    int row = selectionalModForSettingsIngTable.getMinSelectionIndex();
                    searchIngredientComboBox.setSelectedItem(settingsIngredientTable.getValueAt(row, 0));
                }
            }

        });

        //method for combox value setting when table row select in order main table
        final ListSelectionModel selectionalModForOrderMainTable = orderListTable.getSelectionModel();
        selectionalModForOrderMainTable.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lsevt) {
                if (!selectionalModForOrderMainTable.isSelectionEmpty()) {
                    int row = selectionalModForOrderMainTable.getMinSelectionIndex();
                    orderSearchCombo.setSelectedItem(orderListTable.getValueAt(row, 0));
                }
            }

        });

        //method for enter key pressed in ingredient
        searchStockIngComboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchStockIngredientCombo();
                }
            }
        });

        //method for enter key pressed in blend
        searchStockBlendComboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchStockBlendCombo();
                }
            }
        });

        //method for enter key pressed in ingredient in settings tab
        searchIngredientComboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    serchIngredientCombo();
                }
            }
        });

    }

    DBConnection dbcon = new DBConnection();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st = null;

    public void populateIngStockTable() {
        ingredient.populateIngredientTable((DefaultTableModel) inventryIngredientTable.getModel());
    }

    public void populateBlendStockTable() {
        blend.populateBlendTable((DefaultTableModel) inventryBlendTable.getModel());
    }

    public void populateIngHistoryTable() {
        ingredientHistoryStock.populateStockIngredientHistoryTable((DefaultTableModel) ingStockHistoryTbl.getModel());
    }

    public void populateBlendHistoryTable() {
        blendHistoryStock.populateStockBlendHistoryTable((DefaultTableModel) blendStockHistoryTbl.getModel());
    }

    public void populateUserTable() {
        user.viewUser((DefaultTableModel) userTable.getModel());
    }

    //Setting default font
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    private void startClock() {
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tickTock();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
    }

    public void tickTock() {
        timeLabel.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTabbedPane = new javax.swing.JTabbedPane();
        orderHandlingPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        orderListTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        addNewBlendsBtn = new javax.swing.JButton();
        searchOrderBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        orderSearchCombo = new javax.swing.JComboBox();
        inventoryPanel = new javax.swing.JPanel();
        inventoryManagementSplitPane = new javax.swing.JSplitPane();
        inventoryManagementIngredientPanel = new javax.swing.JPanel();
        inventoryIngredientsLbl = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        searchStockIngBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        inventryIngredientTable = new javax.swing.JTable();
        searchStockIngComboBox = new javax.swing.JComboBox();
        inventoryManagementBlendPanel = new javax.swing.JPanel();
        inventoryBlendLbl = new javax.swing.JLabel();
        searchStockBlendsBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        inventryBlendTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        searchStockBlendComboBox = new javax.swing.JComboBox();
        blendDeliveryBtn = new javax.swing.JButton();
        settingsPanel = new javax.swing.JPanel();
        settingsTabbedPane = new javax.swing.JTabbedPane();
        settingsIngPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        searchIngredientBtn = new javax.swing.JButton();
        addItemBtn = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        settingsIngredientTable = new javax.swing.JTable();
        searchIngredientComboBox = new javax.swing.JComboBox();
        settingsBlendPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        searchProductBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        addProductBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        inventoryBlendLbl1 = new javax.swing.JLabel();
        searchBlendComboBox = new javax.swing.JComboBox();
        settingsIngHistoryPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        ingStockHistoryTbl = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        StartDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel8 = new javax.swing.JLabel();
        EndDate = new org.jdesktop.swingx.JXDatePicker();
        btnGo = new javax.swing.JButton();
        btnIngredientHistoryReport = new javax.swing.JButton();
        settingsBlendHistoryPanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        blendStockHistoryTbl = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        BlendStartDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel12 = new javax.swing.JLabel();
        blendEndDate = new org.jdesktop.swingx.JXDatePicker();
        btnGo1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        settingsUserPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        addUserBtn = new javax.swing.JButton();
        deleteUserBtn = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        profileBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("System Name");
        setBackground(new java.awt.Color(51, 51, 255));
        setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        mainTabbedPane.setBackground(new java.awt.Color(153, 153, 153));
        mainTabbedPane.setFont(new java.awt.Font("Segoe UI Symbol", 0, 15)); // NOI18N

        orderHandlingPanel.setBackground(new java.awt.Color(255, 255, 255));

        orderListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Order ID", "Status", "Placed Date", "Placed By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderListTable.setRowHeight(24);
        jScrollPane7.setViewportView(orderListTable);
        if (orderListTable.getColumnModel().getColumnCount() > 0) {
            orderListTable.getColumnModel().getColumn(0).setResizable(false);
            orderListTable.getColumnModel().getColumn(0).setPreferredWidth(200);
            orderListTable.getColumnModel().getColumn(1).setResizable(false);
            orderListTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            orderListTable.getColumnModel().getColumn(2).setResizable(false);
            orderListTable.getColumnModel().getColumn(2).setPreferredWidth(120);
            orderListTable.getColumnModel().getColumn(3).setResizable(false);
            orderListTable.getColumnModel().getColumn(3).setPreferredWidth(250);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel1.setText("Raw Material Orders");
        jLabel1.setMaximumSize(new java.awt.Dimension(31, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(31, 14));
        jLabel1.setPreferredSize(new java.awt.Dimension(31, 14));

        addNewBlendsBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addNewBlendsBtn.setText("Create New Blend Order");
        addNewBlendsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewBlendsBtnActionPerformed(evt);
            }
        });

        searchOrderBtn.setText("Go");
        searchOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchOrderBtnActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Select order ID to view details");

        orderSearchCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderSearchComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout orderHandlingPanelLayout = new javax.swing.GroupLayout(orderHandlingPanel);
        orderHandlingPanel.setLayout(orderHandlingPanelLayout);
        orderHandlingPanelLayout.setHorizontalGroup(
            orderHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderHandlingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderHandlingPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(orderHandlingPanelLayout.createSequentialGroup()
                        .addGroup(orderHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1255, Short.MAX_VALUE)
                            .addGroup(orderHandlingPanelLayout.createSequentialGroup()
                                .addGroup(orderHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(orderHandlingPanelLayout.createSequentialGroup()
                                        .addComponent(orderSearchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchOrderBtn))
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 823, Short.MAX_VALUE)
                                .addComponent(addNewBlendsBtn)))
                        .addContainerGap())))
        );
        orderHandlingPanelLayout.setVerticalGroup(
            orderHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderHandlingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addNewBlendsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(orderHandlingPanelLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(orderHandlingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderSearchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainTabbedPane.addTab("    Order Handling    ", orderHandlingPanel);

        inventoryPanel.setBackground(new java.awt.Color(255, 255, 255));

        inventoryManagementSplitPane.setBorder(null);
        inventoryManagementSplitPane.setDividerLocation(650);
        inventoryManagementSplitPane.setDividerSize(0);
        inventoryManagementSplitPane.setResizeWeight(0.5);

        inventoryManagementIngredientPanel.setBackground(new java.awt.Color(255, 255, 255));
        inventoryManagementIngredientPanel.setPreferredSize(new java.awt.Dimension(715, 498));

        inventoryIngredientsLbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        inventoryIngredientsLbl.setText("Ingredients");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Start typing ingredient name to update stock");

        searchStockIngBtn.setText("Go");
        searchStockIngBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStockIngBtnActionPerformed(evt);
            }
        });

        inventryIngredientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Cateogry", "Ingredient Name", "Visible Stock (g)", "Invisible Stock (g)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inventryIngredientTable.setRowHeight(24);
        jScrollPane6.setViewportView(inventryIngredientTable);
        if (inventryIngredientTable.getColumnModel().getColumnCount() > 0) {
            inventryIngredientTable.getColumnModel().getColumn(1).setResizable(false);
            inventryIngredientTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        searchStockIngComboBox.setEditable(true);
        searchStockIngComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStockIngComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inventoryManagementIngredientPanelLayout = new javax.swing.GroupLayout(inventoryManagementIngredientPanel);
        inventoryManagementIngredientPanel.setLayout(inventoryManagementIngredientPanelLayout);
        inventoryManagementIngredientPanelLayout.setHorizontalGroup(
            inventoryManagementIngredientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryManagementIngredientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryManagementIngredientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addGroup(inventoryManagementIngredientPanelLayout.createSequentialGroup()
                        .addGroup(inventoryManagementIngredientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inventoryIngredientsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addGroup(inventoryManagementIngredientPanelLayout.createSequentialGroup()
                                .addComponent(searchStockIngComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchStockIngBtn)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        inventoryManagementIngredientPanelLayout.setVerticalGroup(
            inventoryManagementIngredientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryManagementIngredientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inventoryIngredientsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(inventoryManagementIngredientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchStockIngBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchStockIngComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
        );

        inventoryManagementSplitPane.setLeftComponent(inventoryManagementIngredientPanel);

        inventoryManagementBlendPanel.setBackground(new java.awt.Color(255, 255, 255));
        inventoryManagementBlendPanel.setPreferredSize(new java.awt.Dimension(615, 498));

        inventoryBlendLbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        inventoryBlendLbl.setText("Blends");

        searchStockBlendsBtn.setText("Go");
        searchStockBlendsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStockBlendsBtnActionPerformed(evt);
            }
        });

        inventryBlendTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Blend Category", "Blend Name", "Visible Stock (g)", "Invisible Stock (g)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inventryBlendTable.setRowHeight(24);
        jScrollPane2.setViewportView(inventryBlendTable);
        if (inventryBlendTable.getColumnModel().getColumnCount() > 0) {
            inventryBlendTable.getColumnModel().getColumn(1).setResizable(false);
            inventryBlendTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            inventryBlendTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Start typing blend name to update stock");

        searchStockBlendComboBox.setEditable(true);
        searchStockBlendComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStockBlendComboBoxActionPerformed(evt);
            }
        });

        blendDeliveryBtn.setText("Deliver Blend");
        blendDeliveryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blendDeliveryBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inventoryManagementBlendPanelLayout = new javax.swing.GroupLayout(inventoryManagementBlendPanel);
        inventoryManagementBlendPanel.setLayout(inventoryManagementBlendPanelLayout);
        inventoryManagementBlendPanelLayout.setHorizontalGroup(
            inventoryManagementBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryManagementBlendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryManagementBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                    .addGroup(inventoryManagementBlendPanelLayout.createSequentialGroup()
                        .addGroup(inventoryManagementBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(inventoryManagementBlendPanelLayout.createSequentialGroup()
                                .addComponent(searchStockBlendComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchStockBlendsBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blendDeliveryBtn))
                            .addComponent(inventoryBlendLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        inventoryManagementBlendPanelLayout.setVerticalGroup(
            inventoryManagementBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryManagementBlendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inventoryBlendLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(inventoryManagementBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchStockBlendsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchStockBlendComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendDeliveryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
        );

        inventoryManagementSplitPane.setRightComponent(inventoryManagementBlendPanel);

        javax.swing.GroupLayout inventoryPanelLayout = new javax.swing.GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventoryPanelLayout.createSequentialGroup()
                .addComponent(inventoryManagementSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1265, Short.MAX_VALUE)
                .addContainerGap())
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventoryPanelLayout.createSequentialGroup()
                .addComponent(inventoryManagementSplitPane)
                .addContainerGap())
        );

        mainTabbedPane.addTab("    Inventory Management    ", inventoryPanel);

        settingsPanel.setBackground(new java.awt.Color(255, 255, 255));

        settingsTabbedPane.setBackground(new java.awt.Color(255, 255, 255));

        settingsIngPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Start typing ingredient name to update");

        searchIngredientBtn.setText("Go");
        searchIngredientBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIngredientBtnActionPerformed(evt);
            }
        });

        addItemBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addItemBtn.setText("Add New Ingredient");
        addItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemBtnActionPerformed(evt);
            }
        });

        settingsIngredientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IngredientName", "Supplier", "Unit Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        settingsIngredientTable.setRowHeight(24);
        jScrollPane10.setViewportView(settingsIngredientTable);
        if (settingsIngredientTable.getColumnModel().getColumnCount() > 0) {
            settingsIngredientTable.getColumnModel().getColumn(0).setResizable(false);
            settingsIngredientTable.getColumnModel().getColumn(1).setResizable(false);
            settingsIngredientTable.getColumnModel().getColumn(2).setResizable(false);
        }

        searchIngredientComboBox.setEditable(true);
        searchIngredientComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIngredientComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsIngPanelLayout = new javax.swing.GroupLayout(settingsIngPanel);
        settingsIngPanel.setLayout(settingsIngPanelLayout);
        settingsIngPanelLayout.setHorizontalGroup(
            settingsIngPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsIngPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsIngPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10)
                    .addGroup(settingsIngPanelLayout.createSequentialGroup()
                        .addGroup(settingsIngPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(settingsIngPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(settingsIngPanelLayout.createSequentialGroup()
                                .addComponent(searchIngredientComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchIngredientBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 711, Short.MAX_VALUE)))
                        .addComponent(addItemBtn)))
                .addContainerGap())
        );
        settingsIngPanelLayout.setVerticalGroup(
            settingsIngPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsIngPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsIngPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(addItemBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsIngPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchIngredientComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchIngredientBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        settingsTabbedPane.addTab("    Ingredients    ", settingsIngPanel);

        settingsBlendPanel.setBackground(new java.awt.Color(255, 255, 255));

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"123", "esee", "mama"},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Blend Code", "Blend Name", "Base Composition"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.setRowHeight(24);
        productTable.setRowSelectionAllowed(false);
        jScrollPane4.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(0).setMinWidth(100);
            productTable.getColumnModel().getColumn(0).setMaxWidth(100);
            productTable.getColumnModel().getColumn(1).setMinWidth(170);
            productTable.getColumnModel().getColumn(1).setMaxWidth(300);
            productTable.getColumnModel().getColumn(2).setMinWidth(200);
            productTable.getColumnModel().getColumn(2).setMaxWidth(300);
        }

        searchProductBtn.setText("Go");
        searchProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchProductBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Start typing blend name to view details");

        addProductBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addProductBtn.setText("Add New Blend");
        addProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductBtnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Ingredient ", "Percentage (%)", "Flavour", "Percentage (%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(24);
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        inventoryBlendLbl1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        inventoryBlendLbl1.setText("Blend Details");

        searchBlendComboBox.setEditable(true);
        searchBlendComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBlendComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsBlendPanelLayout = new javax.swing.GroupLayout(settingsBlendPanel);
        settingsBlendPanel.setLayout(settingsBlendPanelLayout);
        settingsBlendPanelLayout.setHorizontalGroup(
            settingsBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsBlendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsBlendPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(settingsBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                            .addGroup(settingsBlendPanelLayout.createSequentialGroup()
                                .addComponent(inventoryBlendLbl1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(settingsBlendPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 864, Short.MAX_VALUE)
                        .addComponent(addProductBtn))
                    .addGroup(settingsBlendPanelLayout.createSequentialGroup()
                        .addComponent(searchBlendComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchProductBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        settingsBlendPanelLayout.setVerticalGroup(
            settingsBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsBlendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(addProductBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(settingsBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBlendComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(settingsBlendPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(settingsBlendPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(inventoryBlendLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(settingsBlendPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(79, 79, 79))
        );

        settingsTabbedPane.addTab("    Blends    ", settingsBlendPanel);

        settingsIngHistoryPanel.setBackground(new java.awt.Color(255, 255, 255));

        ingStockHistoryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Ingredient Name", "Old Qty", "Updated Qty", "Reason", "Updated By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ingStockHistoryTbl.setRowHeight(23);
        jScrollPane8.setViewportView(ingStockHistoryTbl);
        if (ingStockHistoryTbl.getColumnModel().getColumnCount() > 0) {
            ingStockHistoryTbl.getColumnModel().getColumn(0).setResizable(false);
            ingStockHistoryTbl.getColumnModel().getColumn(1).setResizable(false);
            ingStockHistoryTbl.getColumnModel().getColumn(1).setPreferredWidth(200);
            ingStockHistoryTbl.getColumnModel().getColumn(2).setResizable(false);
            ingStockHistoryTbl.getColumnModel().getColumn(3).setResizable(false);
            ingStockHistoryTbl.getColumnModel().getColumn(4).setResizable(false);
            ingStockHistoryTbl.getColumnModel().getColumn(4).setPreferredWidth(400);
            ingStockHistoryTbl.getColumnModel().getColumn(5).setResizable(false);
            ingStockHistoryTbl.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Search History By Date Range");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Start from");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("To");

        EndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EndDateActionPerformed(evt);
            }
        });

        btnGo.setText("Go");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });

        btnIngredientHistoryReport.setText("Generate Report");
        btnIngredientHistoryReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngredientHistoryReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsIngHistoryPanelLayout = new javax.swing.GroupLayout(settingsIngHistoryPanel);
        settingsIngHistoryPanel.setLayout(settingsIngHistoryPanelLayout);
        settingsIngHistoryPanelLayout.setHorizontalGroup(
            settingsIngHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsIngHistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsIngHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1230, Short.MAX_VALUE)
                    .addGroup(settingsIngHistoryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsIngHistoryPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnIngredientHistoryReport, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        settingsIngHistoryPanelLayout.setVerticalGroup(
            settingsIngHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsIngHistoryPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(settingsIngHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIngredientHistoryReport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        settingsTabbedPane.addTab("    Ingredient Stock History    ", settingsIngHistoryPanel);

        settingsBlendHistoryPanel.setBackground(new java.awt.Color(255, 255, 255));

        blendStockHistoryTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Blend Name", "Old Qty", "Updated Qty", "Reason", "Updated By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(blendStockHistoryTbl);
        if (blendStockHistoryTbl.getColumnModel().getColumnCount() > 0) {
            blendStockHistoryTbl.getColumnModel().getColumn(0).setResizable(false);
            blendStockHistoryTbl.getColumnModel().getColumn(1).setResizable(false);
            blendStockHistoryTbl.getColumnModel().getColumn(1).setPreferredWidth(200);
            blendStockHistoryTbl.getColumnModel().getColumn(2).setResizable(false);
            blendStockHistoryTbl.getColumnModel().getColumn(3).setResizable(false);
            blendStockHistoryTbl.getColumnModel().getColumn(4).setResizable(false);
            blendStockHistoryTbl.getColumnModel().getColumn(4).setPreferredWidth(400);
            blendStockHistoryTbl.getColumnModel().getColumn(5).setResizable(false);
            blendStockHistoryTbl.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Search History By Date Range");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Start from");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("To");

        btnGo1.setText("Go");
        btnGo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGo1ActionPerformed(evt);
            }
        });

        jButton1.setText("Generate Report");

        javax.swing.GroupLayout settingsBlendHistoryPanelLayout = new javax.swing.GroupLayout(settingsBlendHistoryPanel);
        settingsBlendHistoryPanel.setLayout(settingsBlendHistoryPanelLayout);
        settingsBlendHistoryPanelLayout.setHorizontalGroup(
            settingsBlendHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsBlendHistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsBlendHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1230, Short.MAX_VALUE)
                    .addGroup(settingsBlendHistoryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BlendStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(blendEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGo1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsBlendHistoryPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        settingsBlendHistoryPanelLayout.setVerticalGroup(
            settingsBlendHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsBlendHistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsBlendHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BlendStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blendEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGo1))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        settingsTabbedPane.addTab("    Blend Stock History    ", settingsBlendHistoryPanel);

        settingsUserPanel.setBackground(new java.awt.Color(255, 255, 255));

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Username", "Firstname", "Lastname"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setRowHeight(20);
        jScrollPane1.setViewportView(userTable);

        addUserBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addUserBtn.setText("Add New User");
        addUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserBtnActionPerformed(evt);
            }
        });

        deleteUserBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deleteUserBtn.setText("Delete User");
        deleteUserBtn.setMinimumSize(new java.awt.Dimension(200, 25));
        deleteUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsUserPanelLayout = new javax.swing.GroupLayout(settingsUserPanel);
        settingsUserPanel.setLayout(settingsUserPanelLayout);
        settingsUserPanelLayout.setHorizontalGroup(
            settingsUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1230, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsUserPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(settingsUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteUserBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addUserBtn, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        settingsUserPanelLayout.setVerticalGroup(
            settingsUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsUserPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(addUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        settingsTabbedPane.addTab("    Users    ", settingsUserPanel);

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsTabbedPane)
                .addContainerGap())
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addComponent(settingsTabbedPane)
                .addContainerGap())
        );

        mainTabbedPane.addTab("    Settings    ", settingsPanel);

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teaeli/logo-new (Custom).png"))); // NOI18N

        timeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Hello Mr. Dushantha");

        logoutBtn.setText("Log Out");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        profileBtn.setText("Profile");
        profileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainTabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(profileBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoutBtn))
                            .addComponent(timeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(logoutBtn)
                            .addComponent(profileBtn))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemBtnActionPerformed
        AddIngredient addItem = new AddIngredient();
        addItem.setVisible(true);
        addItem.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }//GEN-LAST:event_addItemBtnActionPerformed

    private void addProductBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductBtnActionPerformed
        AddNewBlend addNewProduct = new AddNewBlend();
        addNewProduct.setVisible(true);
        addNewProduct.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }//GEN-LAST:event_addProductBtnActionPerformed

    private void searchIngredientBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchIngredientBtnActionPerformed
        serchIngredientCombo();
    }//GEN-LAST:event_searchIngredientBtnActionPerformed

    private void addNewBlendsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewBlendsBtnActionPerformed
        CreateNewBlendOrder1 createNewBlendOrder = new CreateNewBlendOrder1();
        createNewBlendOrder.setVisible(true);
        createNewBlendOrder.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }//GEN-LAST:event_addNewBlendsBtnActionPerformed

    private void profileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileBtnActionPerformed

        EditProfile editProfile = new EditProfile();
        editProfile.setVisible(true);
        editProfile.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //String UserName = new LoginFrame().user;
        String userName = loginFrame.user;

        try {
            con = dbcon.setConnection();//get the connection
            String query = "SELECT username,firstname,lastname FROM user where username = ('" + userName + "')";
            ResultSet rs = dbcon.getResult(query, con);

            while (rs.next()) {
                user.setUserName(rs.getString(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
            }

        } catch (SQLException e) {
            System.out.println(e);//an error occured while executing

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

        editProfile.setVisible(true);
        editProfile.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        editProfile.lblUserName.setText(user.getUserName());
        editProfile.txtFirstName.setText(user.getFirstName());
        editProfile.txtLastName.setText(user.getLastName());


    }//GEN-LAST:event_profileBtnActionPerformed

    private void addUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserBtnActionPerformed
        AddNewUser newUser = new AddNewUser();
        newUser.setAdminPannel(this);
        newUser.setVisible(true);
        newUser.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }//GEN-LAST:event_addUserBtnActionPerformed


    private void searchProductBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchProductBtnActionPerformed
        BlendDetails updateProduct = new BlendDetails();
        
        if(searchBlendComboBox.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(null, "Please Select a Blend");
        }else{
            updateProduct.setVisible(true);
            updateProduct.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            searchBlendComboBox.setSelectedIndex(-1);
        }
        
    }//GEN-LAST:event_searchProductBtnActionPerformed


    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        this.setVisible(false);
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
        lf.setSize(740, 400);
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void searchOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchOrderBtnActionPerformed
        OrderDetails orderDetails = new OrderDetails();
        String id = orderSearchCombo.getSelectedItem().toString();
        Order tmp = order.viewOrder((DefaultTableModel) orderDetails.blendTable.getModel(), (DefaultTableModel) orderDetails.orderDetailsTable.getModel(), id);
        orderDetails.orderIDLabel.setText(tmp.getOrderID());
        orderDetails.dateLabel.setText(tmp.getDate());
        orderDetails.setVisible(true);
        orderDetails.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        orderSearchCombo.setSelectedIndex(-1);
    }//GEN-LAST:event_searchOrderBtnActionPerformed

    private void searchStockIngBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStockIngBtnActionPerformed

        searchStockIngredientCombo();
    }//GEN-LAST:event_searchStockIngBtnActionPerformed

    private void searchStockBlendsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStockBlendsBtnActionPerformed

        searchStockBlendCombo();
    }//GEN-LAST:event_searchStockBlendsBtnActionPerformed

    private void deleteUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserBtnActionPerformed
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        if (userTable.getSelectedRow() == -1) {
            if (userTable.getSelectedRow() == 0) {
                JOptionPane.showMessageDialog(this, "Table is empty");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete");
            }
        } else {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            String uID = model.getValueAt(userTable.getSelectedRow(), 0).toString();
            int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete user having Employee ID of " + uID + "? ", "Warning", dialogButton);
            if (a == JOptionPane.YES_OPTION) {

                int id = Integer.parseInt(uID);

                int rst = user.removeUser(id);
                if (rst == 1) {
                    user.viewUser((DefaultTableModel) userTable.getModel());
                    JOptionPane.showMessageDialog(this, "User successfully deleted");
                } else {
                    JOptionPane.showMessageDialog(this, "Error occured! User couldn't be deleted");
                }

            } else {
                return;
            }

        }
    }//GEN-LAST:event_deleteUserBtnActionPerformed

    private void blendDeliveryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blendDeliveryBtnActionPerformed

        int selectedIndex = searchStockBlendComboBox.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "You haven't select any ingredient name !", "Empty Selection", JOptionPane.ERROR_MESSAGE);
        } else {

            String selectedIngName = (String) searchStockBlendComboBox.getSelectedItem();

            Blend blendDelivery = new Blend();

            if (blendDelivery.checkAndLoadBlendDeliverDetails(selectedIngName)) {

                searchStockBlendComboBox.setSelectedIndex(-1);

                DeliverBlend deliverBlend = new DeliverBlend();

                deliverBlend.setAdminPannel(this);
                deliverBlend.setVisible(true);
                deliverBlend.setDefaultCloseOperation(HIDE_ON_CLOSE);
                deliverBlend.blendNameLbl.setText(blendDelivery.getBlendName());
                deliverBlend.blendCatgLbl.setText(blendDelivery.getBlendCategory());
                deliverBlend.allocatedQtyLbl.setText(String.valueOf(blendDelivery.getOrderedStock()));
                deliverBlend.freeQtyLbl.setText(String.valueOf(blendDelivery.getVisibleStock()));

            } else {
                JOptionPane.showMessageDialog(this, "You have selected invalid blend name !", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                searchStockBlendComboBox.setSelectedIndex(-1);
            }
        }
    }//GEN-LAST:event_blendDeliveryBtnActionPerformed

    /* start of searchStockIngredientCombo method */
    private void searchStockIngredientCombo() {

        int selectedIndex = searchStockIngComboBox.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "You haven't select any ingredient name !", "Empty Selection", JOptionPane.ERROR_MESSAGE);
        } else {

            String selectedIngName = (String) searchStockIngComboBox.getSelectedItem();

            Ingredient ingredeintForStock = new Ingredient();

            if (ingredeintForStock.checkAndLoadIngredientStockDetails(selectedIngName)) {
                searchStockIngComboBox.setSelectedIndex(-1);

                UpdateIngStock updateStock = new UpdateIngStock();

                updateStock.setAdminPannel(this);
                updateStock.setVisible(true);
                updateStock.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                updateStock.updateStockItemNameLbl.setText(ingredeintForStock.getIngName());
                updateStock.updateStockCategoryLbl.setText(ingredeintForStock.getIngCategoryName());
                updateStock.stockQtyLbl.setText(String.valueOf(ingredeintForStock.getVisibleStock()));
            } else {
                JOptionPane.showMessageDialog(this, "You have selected invalid ingredient!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                searchStockIngComboBox.setSelectedIndex(-1);
            }
        }
    }
    /* end of searchStockIngredientCombo method */

    /* start of searchStockBlendCombo method */
    private void searchStockBlendCombo() {

        int selectedIndex = searchStockBlendComboBox.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "You haven't select any blend name !", "Empty Selection", JOptionPane.ERROR_MESSAGE);
        } else {

            Blend blendForStock = new Blend();

            String selectedBlendName = (String) searchStockBlendComboBox.getSelectedItem();

            if (blendForStock.checkAndLoadBlendStockDetails(selectedBlendName)) {
                searchStockBlendComboBox.setSelectedIndex(-1);

                UpdateBlendStock updateBlendStock = new UpdateBlendStock();

                updateBlendStock.setAdminPannel(this);
                updateBlendStock.setVisible(true);
                updateBlendStock.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                updateBlendStock.updateStockItemNameLbl.setText(blendForStock.getBlendName());
                updateBlendStock.updateStockItemCategoryLbl.setText(blendForStock.getBlendCategory());
                updateBlendStock.stockQtyLbl.setText(String.valueOf(blendForStock.getVisibleStock()));
            } else {
                JOptionPane.showMessageDialog(this, "You have selected invalid blend name !", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                searchStockBlendComboBox.setSelectedIndex(-1);
            }
        }
    }
    /* end of searchStockBlendCombo method */

    /* start of searchIngredientCombo method */
    private void serchIngredientCombo() {
        String[] resultArray = new String[5];

        String searchItem = (String) searchIngredientComboBox.getSelectedItem();

        if (searchItem.equalsIgnoreCase("")) {
            System.out.println("inside no select");
            JOptionPane.showMessageDialog(null, "You Haven't selected an Ingredient!!!", "Pleae select", 0);

        } else {
            try {
                resultArray = ingredient.viewAllDetailsOfAIngredient((String) searchIngredientComboBox.getSelectedItem());
            } catch (SQLException ex) {
                Logger.getLogger(AdminPannel.class.getName()).log(Level.SEVERE, null, ex);
            }
            IngredientDetails itemDetails = new IngredientDetails();
            Supplier supplier = new Supplier();

            //set values to fields in IngredientDetails window
            //load supplier list to combobox
            AutoSuggest supplierComboboxAutoSuggest = new AutoSuggest();
            try {
                supplierComboboxAutoSuggest.setAutoSuggest(itemDetails.supplierCombobox, supplier.loadSuppliersForCombobox());
            } catch (SQLException ex) {
                Logger.getLogger(AdminPannel.class.getName()).log(Level.SEVERE, null, ex);
            }

            itemDetails.itemNameTxt.setText(resultArray[0]);
            itemDetails.setName(resultArray[1]); //set ingid as name
            itemDetails.itemTypeCombo.setSelectedItem(resultArray[2]);
            itemDetails.supplierCombobox.setSelectedItem(resultArray[3]);
            itemDetails.unitPriceTxt.setText(resultArray[4]);

            itemDetails.setVisible(true);
            itemDetails.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            searchIngredientComboBox.setSelectedIndex(-1);
        }
    }
    /* start of searchIngredientCombo method */

    private void orderSearchComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderSearchComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderSearchComboActionPerformed

    private void searchStockBlendComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStockBlendComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchStockBlendComboBoxActionPerformed

    private void searchIngredientComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchIngredientComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchIngredientComboBoxActionPerformed

    private void searchStockIngComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStockIngComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchStockIngComboBoxActionPerformed

    private void searchBlendComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBlendComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBlendComboBoxActionPerformed

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        SimpleDateFormat javadate = new SimpleDateFormat("yyyy-MM-dd");
        if (StartDate.getDate() == null || EndDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please choose date range");
        } else if (StartDate.getDate().after(EndDate.getDate())) {
            JOptionPane.showMessageDialog(this, "Please choose valid date range");
        } else {
            Date start = StartDate.getDate();
            Date end = EndDate.getDate();
            String startdate = javadate.format(start);
            String enddate = javadate.format(end);
            StockHistory ingredientHistoryStock = new StockHistory();
            ingredientHistoryStock.populateStockIngredientHistoryTableByDate((DefaultTableModel) ingStockHistoryTbl.getModel(), startdate, enddate);
        }
    }//GEN-LAST:event_btnGoActionPerformed

    private void btnGo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGo1ActionPerformed
        SimpleDateFormat javadate = new SimpleDateFormat("yyyy-MM-dd");
        if (BlendStartDate.getDate() == null || blendEndDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please choose date range");
        } else if (BlendStartDate.getDate().after(blendEndDate.getDate())) {
            JOptionPane.showMessageDialog(this, "Please choose valid date range");
        } else {
            Date start = StartDate.getDate();
            Date end = EndDate.getDate();
            String startdate = javadate.format(start);
            String enddate = javadate.format(end);
            StockHistory stockhistory = new StockHistory();
            stockhistory.populateStockIngredientHistoryTableByDate((DefaultTableModel) blendStockHistoryTbl.getModel(), startdate, enddate);
            //populateStockBlendHistoryTableByDate((DefaultTableModel) blendStockHistoryTbl.getModel(),startdate,enddate);
        }
    }//GEN-LAST:event_btnGo1ActionPerformed

    private void EndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EndDateActionPerformed

    }//GEN-LAST:event_EndDateActionPerformed

    private void btnIngredientHistoryReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngredientHistoryReportActionPerformed
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy:MM:dd");
        String today = sdf2.format(date);
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        SimpleDateFormat javadate = new SimpleDateFormat("yyyy-MM-dd");
        Date start = StartDate.getDate();
        Date end = EndDate.getDate();
        String startdate = javadate.format(start);
        String enddate = javadate.format(end);
        try {
            String reportdate = today.replace(":", "_");
            //New PDF File will be created as ACCReport2016_01_01 //today's date
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Teaeli\\Ingredient Stock History\\" + reportdate + "" + ".pdf"));
            document.open();
            Image image2 = Image.getInstance("C:\\Teaeli\\Logos\\logo-new (Custom).png");
            document.add(image2);
            Paragraph paragraph1 = new Paragraph("leafspice (pvt)Ltd.\nAddress: 1/52, Galle Road,Colombo 03.\nT.P:0112552225\n\n");
            document.add(paragraph1);
            Paragraph paragraph2 = new Paragraph("       Date : " + today + "", FontFactory.getFont(FontFactory.HELVETICA, 12));
            document.add(paragraph2);
            paragraph1 = new Paragraph("                Ingredient Stock History From  " +startdate+   " To " +enddate+ "" , FontFactory.getFont(FontFactory.HELVETICA, 16));
            document.add(paragraph1);
            //adding a table
            PdfPTable t = new PdfPTable(6);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);

            t.addCell(new PdfPCell(new Phrase("Date")));
            t.addCell(new PdfPCell(new Phrase("Ingredient NAme")));
            t.addCell(new PdfPCell(new Phrase("Old Qty")));
            t.addCell(new PdfPCell(new Phrase("Updated Qty")));
            t.addCell(new PdfPCell(new Phrase("Reason")));
            t.addCell(new PdfPCell(new Phrase("Updated By")));
            int rows = ingStockHistoryTbl.getRowCount();

            for (int i = 0; i < rows; i++) {
                t.addCell(new PdfPCell(new Phrase(ingStockHistoryTbl.getValueAt(i, 0) + "")));
                if (ingStockHistoryTbl.getValueAt(i, 1) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(ingStockHistoryTbl.getValueAt(i, 1) + "")));
                }
                if (ingStockHistoryTbl.getValueAt(i, 2) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(ingStockHistoryTbl.getValueAt(i, 2) + "")));
                }
                if (ingStockHistoryTbl.getValueAt(i, 3) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(ingStockHistoryTbl.getValueAt(i, 3) + "")));
                }
                if (ingStockHistoryTbl.getValueAt(i, 4) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(ingStockHistoryTbl.getValueAt(i, 4) + "")));
                }
                if (ingStockHistoryTbl.getValueAt(i, 5) == null) {
                    t.addCell(new PdfPCell(new Phrase("-")));
                } else {
                    t.addCell(new PdfPCell(new Phrase(ingStockHistoryTbl.getValueAt(i, 5) + "")));
                }

            }
            document.add(t);

            if ((new File("C:\\Teaeli\\Ingredient Stock History\\" + reportdate + "" + ".pdf")).exists()) {

                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler C:\\Teaeli\\Ingredient Stock History\\" + reportdate + "" + ".pdf");
                p.waitFor();
            }

        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "File already exists!!!");
        }
        document.close();
    }//GEN-LAST:event_btnIngredientHistoryReportActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPannel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPannel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPannel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPannel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPannel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker BlendStartDate;
    private org.jdesktop.swingx.JXDatePicker EndDate;
    private org.jdesktop.swingx.JXDatePicker StartDate;
    private javax.swing.JButton addItemBtn;
    private javax.swing.JButton addNewBlendsBtn;
    private javax.swing.JButton addProductBtn;
    private javax.swing.JButton addUserBtn;
    private javax.swing.JButton blendDeliveryBtn;
    private org.jdesktop.swingx.JXDatePicker blendEndDate;
    private javax.swing.JTable blendStockHistoryTbl;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnGo1;
    private javax.swing.JButton btnIngredientHistoryReport;
    private javax.swing.JButton deleteUserBtn;
    public javax.swing.JTable ingStockHistoryTbl;
    private javax.swing.JLabel inventoryBlendLbl;
    private javax.swing.JLabel inventoryBlendLbl1;
    private javax.swing.JLabel inventoryIngredientsLbl;
    private javax.swing.JPanel inventoryManagementBlendPanel;
    private javax.swing.JPanel inventoryManagementIngredientPanel;
    private javax.swing.JSplitPane inventoryManagementSplitPane;
    private javax.swing.JPanel inventoryPanel;
    private javax.swing.JTable inventryBlendTable;
    private javax.swing.JTable inventryIngredientTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JPanel orderHandlingPanel;
    private javax.swing.JTable orderListTable;
    private javax.swing.JComboBox orderSearchCombo;
    private javax.swing.JTable productTable;
    private javax.swing.JButton profileBtn;
    private javax.swing.JComboBox searchBlendComboBox;
    private javax.swing.JButton searchIngredientBtn;
    public javax.swing.JComboBox searchIngredientComboBox;
    private javax.swing.JButton searchOrderBtn;
    private javax.swing.JButton searchProductBtn;
    private javax.swing.JComboBox searchStockBlendComboBox;
    private javax.swing.JButton searchStockBlendsBtn;
    private javax.swing.JButton searchStockIngBtn;
    private javax.swing.JComboBox searchStockIngComboBox;
    private javax.swing.JPanel settingsBlendHistoryPanel;
    private javax.swing.JPanel settingsBlendPanel;
    private javax.swing.JPanel settingsIngHistoryPanel;
    private javax.swing.JPanel settingsIngPanel;
    public static javax.swing.JTable settingsIngredientTable;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JTabbedPane settingsTabbedPane;
    private javax.swing.JPanel settingsUserPanel;
    private javax.swing.JLabel timeLabel;
    public javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JTable flavourTable;

}
