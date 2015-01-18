/*
 * The MIT License
 *
 * @Version 0.9 
 * Copyright 2014 Glenn McGuire <glennmcguire9@gmail.com> <www.github.com/gmac>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package my.honeypotadmin;

import java.awt.FlowLayout;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

public class AppMain extends javax.swing.JFrame {

    private static String Addr = "";
    private String Barcode = "";
    private String PMKey = "";
    private String Username = "";
    private double totalIncome;
    private int ItemIndex = 0;
    private SQLInterface sql = null;
    private String[][] User = null;
    private String[][] searchUser = null;
    private String[][] Stock = null;
    private String[][] searchStock = null;
    private String[] PurchaseList;
    private final Path currentRelativePath = Paths.get("");
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM");
    private final Date date = new Date();

    public AppMain() {
        sql = new SQLInterface(Addr);

        initComponents();

        UserList.setModel(Userlm);
        StockList.setModel(Stocklm);
        Purchases.setModel(Purchlm);
        UserItemAddList.setModel(UserAddlm);

        JComponent editor = numberSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
            spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        }

        SQLSetup();

        UserList.setSelectedIndex(0);
        StockList.setSelectedIndex(0);
        UserItemAddList.setSelectedIndex(0);
        StockListMouseClicked(null);
        UserListMouseClicked(null);
    }

    private void SQLSetup() {
        User = sql.SQLSend("SELECT PMKEYS, Name, Surname, WeekCost, PriorWeekCost, Purchases FROM Users ORDER BY Surname ASC", 6);
        Stock = sql.SQLSend("SELECT Barcode, Name, Cost, Stock, InitialStock FROM Products ORDER BY Name ASC", 5);
        totalIncome = (double) Double.valueOf(sql.SQLSend("SELECT SUM(WeekCost) FROM Users", 5)[0][0]);
        Userlm.removeAllElements();
        Stocklm.removeAllElements();
        Purchlm.removeAllElements();
        UserAddlm.removeAllElements();
        UserToolLabel.setText("Number of Users: " + User[0].length);
        ItemToolLabel.setText("Number of Items: " + Stock[0].length);

        for (int i = 0; i < User[1].length; i++) {
            Userlm.addElement("[" + User[0][i] + "] \t" + User[1][i] + " " + User[2][i]);
            UserAddlm.addElement("[" + User[0][i] + "] \t" + User[1][i] + " " + User[2][i]);
        }
        for (int i = 0; i < Stock[1].length; i++) {
            Stocklm.addElement(Stock[1][i] + " [" + Stock[0][i] + "] ");
        }

    }

    public void UserPieSetup(double User, double total) {

        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("User's Usage", User);
        pieDataset.setValue("Remaining Division Usage", total - User);

        JFreeChart chart = ChartFactory.createPieChart3D("User Usage", pieDataset, true, true, true);

        PiePlot P = (PiePlot3D) chart.getPlot();

        ChartPanel panel = new ChartPanel(chart);

        UserPiePanel.removeAll();
        UserPiePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        UserPiePanel.add(panel);
        UserPiePanel.updateUI();

    }

    public void PurchaseSort(String list) {
        try {
            Purchlm.removeAllElements();
            PurchaseList = list.split(",");
            for (String item : PurchaseList) {
                String[] temp = item.split(":");
                if (!"Blank".equals(temp[0])) {
                    Purchlm.addElement(temp[1] + " Purchases of " + temp[0]);
                }
            }
        } catch (Exception ex) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabPanel = new javax.swing.JTabbedPane();
        UserPanel = new javax.swing.JPanel();
        UserToolbar = new javax.swing.JToolBar();
        UserToolLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        BillPanel = new javax.swing.JPanel();
        BillText = new javax.swing.JTextField();
        BillSlider = new javax.swing.JSlider();
        PMKeyPanel = new javax.swing.JPanel();
        PMKeySlider = new javax.swing.JSlider();
        PMKeyText = new javax.swing.JTextField();
        UserSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Purchases = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        UserPiePanel = new javax.swing.JPanel();
        UserScollPanel = new javax.swing.JScrollPane();
        UserList = new javax.swing.JList();
        UserSearch = new javax.swing.JTextField();
        RemoveUser = new javax.swing.JButton();
        AddUser = new javax.swing.JButton();
        StockPanel = new javax.swing.JPanel();
        StockScrollPanel = new javax.swing.JScrollPane();
        StockList = new javax.swing.JList();
        StockSearch = new javax.swing.JTextField();
        CurStockPanel = new javax.swing.JPanel();
        CurStockSlider = new javax.swing.JSlider();
        CurStockText = new javax.swing.JTextField();
        PricePanel = new javax.swing.JPanel();
        PriceSlider = new javax.swing.JSlider();
        PriceText = new javax.swing.JTextField();
        InitStockPanel = new javax.swing.JPanel();
        InitStockSlider = new javax.swing.JSlider();
        InitStockText = new javax.swing.JTextField();
        BarcodePanel = new javax.swing.JPanel();
        BarcodeSlider = new javax.swing.JSlider();
        BarcodeText = new javax.swing.JTextField();
        AddStock = new javax.swing.JButton();
        RemoveStock = new javax.swing.JButton();
        ItemToolBar = new javax.swing.JToolBar();
        ItemToolLabel = new javax.swing.JLabel();
        ItemSave = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        UserItemAddList = new javax.swing.JList();
        numberSpinner = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        MaintenancePane = new javax.swing.JPanel();
        MaitenancePanel = new javax.swing.JPanel();
        ExportBill = new javax.swing.JButton();
        StockRecount = new javax.swing.JButton();
        BillLabel = new javax.swing.JLabel();
        BillText1 = new javax.swing.JLabel();
        BillText2 = new javax.swing.JLabel();
        StockLabel = new javax.swing.JLabel();
        StockText1 = new javax.swing.JLabel();
        StockText2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        SQLText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Honeypot Admin Panel v0.9 - Glenn McGuire 2015");
        setPreferredSize(new java.awt.Dimension(1100, 665));

        UserToolbar.setBackground(new java.awt.Color(204, 204, 204));
        UserToolbar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        UserToolbar.setForeground(new java.awt.Color(204, 0, 153));
        UserToolbar.setRollover(true);

        UserToolLabel.setText("EMPTY");
        UserToolbar.add(UserToolLabel);

        BillPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Weekly Bill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 153, 51)));

        BillText.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        BillSlider.setMaximum(10000);
        BillSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                BillSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout BillPanelLayout = new javax.swing.GroupLayout(BillPanel);
        BillPanel.setLayout(BillPanelLayout);
        BillPanelLayout.setHorizontalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BillPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BillSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BillText, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        BillPanelLayout.setVerticalGroup(
            BillPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BillPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(BillText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(BillSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PMKeyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PMKey", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 153, 51)));
        PMKeyPanel.setEnabled(false);

        PMKeySlider.setEnabled(false);

        PMKeyText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PMKeyText.setEnabled(false);

        javax.swing.GroupLayout PMKeyPanelLayout = new javax.swing.GroupLayout(PMKeyPanel);
        PMKeyPanel.setLayout(PMKeyPanelLayout);
        PMKeyPanelLayout.setHorizontalGroup(
            PMKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMKeyPanelLayout.createSequentialGroup()
                .addGroup(PMKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PMKeyPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(PMKeyText, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PMKeyPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(PMKeySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        PMKeyPanelLayout.setVerticalGroup(
            PMKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMKeyPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(PMKeyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(PMKeySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        UserSave.setText("Save");
        UserSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserSaveActionPerformed(evt);
            }
        });

        Purchases.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(Purchases);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BillPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(UserSave)
                        .addGap(43, 43, 43)
                        .addComponent(PMKeyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BillPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PMKeyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserSave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("Basics", jPanel1);

        UserPiePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout UserPiePanelLayout = new javax.swing.GroupLayout(UserPiePanel);
        UserPiePanel.setLayout(UserPiePanelLayout);
        UserPiePanelLayout.setHorizontalGroup(
            UserPiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 651, Short.MAX_VALUE)
        );
        UserPiePanelLayout.setVerticalGroup(
            UserPiePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserPiePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserPiePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Graphics", jPanel3);

        UserList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserListMouseClicked(evt);
            }
        });
        UserList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserListKeyReleased(evt);
            }
        });
        UserScollPanel.setViewportView(UserList);

        UserSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UserSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserSearchKeyReleased(evt);
            }
        });

        RemoveUser.setText("Remove Selected User");
        RemoveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveUserActionPerformed(evt);
            }
        });

        AddUser.setText("Add New User");
        AddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserPanelLayout = new javax.swing.GroupLayout(UserPanel);
        UserPanel.setLayout(UserPanelLayout);
        UserPanelLayout.setHorizontalGroup(
            UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(UserToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(UserPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(UserSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(RemoveUser, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addComponent(AddUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(UserScollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        UserPanelLayout.setVerticalGroup(
            UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserPanelLayout.createSequentialGroup()
                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(UserPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1))
                    .addGroup(UserPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(UserScollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(UserSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(RemoveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(UserToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TabPanel.addTab("User Data", UserPanel);

        StockList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StockListMouseClicked(evt);
            }
        });
        StockScrollPanel.setViewportView(StockList);

        StockSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockSearchActionPerformed(evt);
            }
        });
        StockSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StockSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                StockSearchKeyReleased(evt);
            }
        });

        CurStockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Current Stock"));

        CurStockSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CurStockSliderMouseReleased(evt);
            }
        });
        CurStockSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                CurStockSliderStateChanged(evt);
            }
        });

        CurStockText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CurStockText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurStockTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CurStockPanelLayout = new javax.swing.GroupLayout(CurStockPanel);
        CurStockPanel.setLayout(CurStockPanelLayout);
        CurStockPanelLayout.setHorizontalGroup(
            CurStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CurStockPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(CurStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CurStockSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CurStockText))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        CurStockPanelLayout.setVerticalGroup(
            CurStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CurStockPanelLayout.createSequentialGroup()
                .addComponent(CurStockText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CurStockSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PricePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Price"));

        PriceSlider.setMaximum(1000);
        PriceSlider.setMinorTickSpacing(5);
        PriceSlider.setValue(500);
        PriceSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PriceSliderMouseReleased(evt);
            }
        });
        PriceSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PriceSliderStateChanged(evt);
            }
        });

        PriceText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PriceText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PricePanelLayout = new javax.swing.GroupLayout(PricePanel);
        PricePanel.setLayout(PricePanelLayout);
        PricePanelLayout.setHorizontalGroup(
            PricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PricePanelLayout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(PricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PriceSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PriceText))
                .addGap(43, 43, 43))
        );
        PricePanelLayout.setVerticalGroup(
            PricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PricePanelLayout.createSequentialGroup()
                .addComponent(PriceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PriceSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        InitStockPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Initial Stock"));

        InitStockSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                InitStockSliderMouseReleased(evt);
            }
        });
        InitStockSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                InitStockSliderStateChanged(evt);
            }
        });

        InitStockText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        InitStockText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InitStockTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InitStockPanelLayout = new javax.swing.GroupLayout(InitStockPanel);
        InitStockPanel.setLayout(InitStockPanelLayout);
        InitStockPanelLayout.setHorizontalGroup(
            InitStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InitStockPanelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(InitStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(InitStockSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(InitStockText))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        InitStockPanelLayout.setVerticalGroup(
            InitStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InitStockPanelLayout.createSequentialGroup()
                .addComponent(InitStockText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(InitStockSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        BarcodePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Barcode", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 153, 51)));

        BarcodeSlider.setEnabled(false);

        BarcodeText.setEditable(false);
        BarcodeText.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout BarcodePanelLayout = new javax.swing.GroupLayout(BarcodePanel);
        BarcodePanel.setLayout(BarcodePanelLayout);
        BarcodePanelLayout.setHorizontalGroup(
            BarcodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarcodePanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(BarcodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BarcodeText, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BarcodeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        BarcodePanelLayout.setVerticalGroup(
            BarcodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BarcodePanelLayout.createSequentialGroup()
                .addComponent(BarcodeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BarcodeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddStock.setText("Add New Item");
        AddStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStockActionPerformed(evt);
            }
        });

        RemoveStock.setText("Remove Selected User");
        RemoveStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveStockActionPerformed(evt);
            }
        });

        ItemToolBar.setBackground(new java.awt.Color(204, 204, 204));
        ItemToolBar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        ItemToolBar.setForeground(new java.awt.Color(204, 0, 153));
        ItemToolBar.setRollover(true);

        ItemToolLabel.setText("EMPTY");
        ItemToolBar.add(ItemToolLabel);

        ItemSave.setText("Save to Database");
        ItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemSaveActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Item to User", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        UserItemAddList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(UserItemAddList);

        numberSpinner.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        numberSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jButton3.setText("Add to User Account");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Remove from User Account");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberSpinner)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(numberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        javax.swing.GroupLayout StockPanelLayout = new javax.swing.GroupLayout(StockPanel);
        StockPanel.setLayout(StockPanelLayout);
        StockPanelLayout.setHorizontalGroup(
            StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StockSearch)
                    .addComponent(AddStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RemoveStock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(StockScrollPanel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(StockPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BarcodePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CurStockPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InitStockPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PricePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ItemSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(ItemToolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        StockPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BarcodePanel, InitStockPanel, PricePanel});

        StockPanelLayout.setVerticalGroup(
            StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StockPanelLayout.createSequentialGroup()
                        .addComponent(StockScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(StockSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RemoveStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddStock, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StockPanelLayout.createSequentialGroup()
                        .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BarcodePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PricePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(StockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(InitStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CurStockPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ItemSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(ItemToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TabPanel.addTab("Stock Data", StockPanel);

        MaitenancePanel.setBackground(new java.awt.Color(255, 204, 204));
        MaitenancePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Weekly Bill Process", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        ExportBill.setText("Export Weekly Bills");
        ExportBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportBillActionPerformed(evt);
            }
        });

        StockRecount.setText("Perform Stock ReCounts");
        StockRecount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockRecountActionPerformed(evt);
            }
        });

        BillLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        BillLabel.setText("Export Weekly Bills");

        BillText1.setText("By executing this process, the user data regarding weekly bills will be exported to the excel file");

        BillText2.setText("'Output.csv' in the same directory as this software (Current Bills will be reset to $0.00!).");

        StockLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        StockLabel.setText("Perform Stock ReCounts");

        StockText1.setText("By executing this process, you will be prompted for the stock counts of all products in the system");

        StockText2.setText("consecutively, so please be ready with stock counts of all items.");

        javax.swing.GroupLayout MaitenancePanelLayout = new javax.swing.GroupLayout(MaitenancePanel);
        MaitenancePanel.setLayout(MaitenancePanelLayout);
        MaitenancePanelLayout.setHorizontalGroup(
            MaitenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MaitenancePanelLayout.createSequentialGroup()
                .addGroup(MaitenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MaitenancePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BillLabel))
                    .addGroup(MaitenancePanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(MaitenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BillText2)
                            .addComponent(BillText1)))
                    .addGroup(MaitenancePanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(MaitenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StockText2)
                            .addComponent(StockText1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MaitenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExportBill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StockRecount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(MaitenancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MaitenancePanelLayout.setVerticalGroup(
            MaitenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaitenancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MaitenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MaitenancePanelLayout.createSequentialGroup()
                        .addComponent(BillLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BillText1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BillText2))
                    .addComponent(ExportBill, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(StockLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MaitenancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MaitenancePanelLayout.createSequentialGroup()
                        .addComponent(StockText1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StockText2))
                    .addComponent(StockRecount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Misc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        SQLText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SQLTextActionPerformed(evt);
            }
        });

        jButton1.setText("Send SQL Statement");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("REFRESH PROGRAM DATA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(SQLText, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SQLText)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MaintenancePaneLayout = new javax.swing.GroupLayout(MaintenancePane);
        MaintenancePane.setLayout(MaintenancePaneLayout);
        MaintenancePaneLayout.setHorizontalGroup(
            MaintenancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaintenancePaneLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(MaintenancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MaitenancePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MaintenancePaneLayout.setVerticalGroup(
            MaintenancePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaintenancePaneLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(MaitenancePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        TabPanel.addTab("Maintenance", MaintenancePane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(TabPanel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StockListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StockListMouseClicked
        String line = StockList.getSelectedValue().toString();
        Barcode = line.substring(line.indexOf('[') + 1, line.indexOf(']'));

        int ItemIndex = indexOfIntArray(Stock[0], Barcode);

        PriceSlider.setValue(((int) Double.parseDouble(Stock[2][ItemIndex])) * 100);

        CurStockSlider.setValue(Integer.parseInt(Stock[3][ItemIndex]));
        InitStockSlider.setValue(Integer.parseInt(Stock[4][ItemIndex]));

        BarcodeText.setText(Barcode);
        CurStockText.setText(Stock[3][ItemIndex]);
        InitStockText.setText(Stock[4][ItemIndex]);

        PriceText.setText(Stock[2][ItemIndex]);

    }//GEN-LAST:event_StockListMouseClicked

    private void PriceSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PriceSliderStateChanged
        PriceText.setText(Math.round(5.00 * Math.ceil(PriceSlider.getValue() / 5.00)) / 100.00 + "");
    }//GEN-LAST:event_PriceSliderStateChanged

    private void PriceSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PriceSliderMouseReleased
        PriceText.requestFocus();
    }//GEN-LAST:event_PriceSliderMouseReleased

    private void PriceTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceTextActionPerformed
        PriceSlider.setValue((int) (100.00 * Double.parseDouble(PriceText.getText())));
    }//GEN-LAST:event_PriceTextActionPerformed

    private void CurStockSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_CurStockSliderStateChanged
        CurStockText.setText(CurStockSlider.getValue() + "");
    }//GEN-LAST:event_CurStockSliderStateChanged

    private void CurStockTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurStockTextActionPerformed
        CurStockSlider.setValue((int) Integer.valueOf(CurStockText.getText()));
    }//GEN-LAST:event_CurStockTextActionPerformed

    private void InitStockTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InitStockTextActionPerformed
        InitStockSlider.setValue((int) Integer.valueOf(InitStockText.getText()));
    }//GEN-LAST:event_InitStockTextActionPerformed

    private void InitStockSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_InitStockSliderStateChanged
        InitStockText.setText(InitStockSlider.getValue() + "");
    }//GEN-LAST:event_InitStockSliderStateChanged

    private void ExportBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportBillActionPerformed
        try {
            /* String Spents = "Name, Bill";
             String UserSp = "";
             Stock = sql.SQLSend("SELECT Barcode, Name, Cost, Stock, InitialStock FROM Products", 5);
             for (int i = 0; i < Stock[1].length; i++) {
             if (Stock[1][i] != null) {
             Spents += ", " + Stock[1][i];
             }
             }*/

            SQLSetup();

            //---------------------------------------
            String message = "Name, Purchases\n";
            for (int i = 0; i < User[1].length; i++) {
                if (User[1][i] != null) {
                    message += User[1][i] + " " + User[2][i] + ", " + User[5][i] + "\n";
                }
            }
            new FileOutputStream("Purchases " + dateFormat.format(date) + ".csv", false).close();
            FileWriter fileOut = new FileWriter("Purchases " + dateFormat.format(date) + ".csv");
            fileOut.write(message);
            fileOut.flush();
            //---------------------------------------

            //---------------------------------------
            message = "Name, Bill\n";
            for (int i = 0; i < User[1].length; i++) {
                if (User[1][i] != null) {
                    message += User[1][i] + " " + User[2][i] + ", $" + User[3][i] + "\n";
                }
            }
            message += "\nTotal:, $" + totalIncome;
            new FileOutputStream("Bills " + dateFormat.format(date) + ".csv", false).close();
            fileOut = new FileWriter("Bills " + dateFormat.format(date) + ".csv");
            fileOut.write(message);
            fileOut.flush();
            //---------------------------------------

            JOptionPane.showMessageDialog(null, "Bills are exported to Bills.csv", "Success!", JOptionPane.INFORMATION_MESSAGE);
            sql.SQLUpdate("UPDATE Users SET PriorWeekCost = WeekCost");
            sql.SQLUpdate("UPDATE Users SET WeekCost = 0");
            sql.SQLUpdate("UPDATE Users SET Purchases = ''");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error outputting Bills.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ExportBillActionPerformed

    private void StockRecountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockRecountActionPerformed
        try {

            SQLSetup();

            String message = "Name, Initial Stock, Final Stock, Number Purchased, Income\n";
            for (int i = 0; i < Stock[1].length; i++) {
                if (Stock[1][i] != null) {
                    int stockDiff = (Integer.valueOf(Stock[4][i]) - Integer.valueOf(Stock[3][i]));
                    message += Stock[1][i] + ", " + Stock[4][i] + ", " + Stock[3][i] + ", " + stockDiff + ", $" + ((double) stockDiff * Double.valueOf(Stock[2][i])) + "\n";
                }
            }
            new FileOutputStream("Stock " + dateFormat.format(date) + ".csv", false).close();
            FileWriter fileOut = new FileWriter("Stock " + dateFormat.format(date) + ".csv");
            fileOut.write(message);
            fileOut.flush();
            JOptionPane.showMessageDialog(null, "Stocks are exported to Stock.csv", "Success!", JOptionPane.INFORMATION_MESSAGE);

            String StockUPD = "UPDATE Products SET Stock = CASE Name ";
            String StockEnd = "";
            int NewStock = 0;

            for (String item : Stock[1]) {
                if (item != null) {
                    NewStock = (int) Integer.valueOf(JOptionPane.showInputDialog("Please enter stock count of item " + item + ".", "0"));
                    StockUPD += ("WHEN '" + item + "' THEN '" + NewStock + "' ");
                    StockEnd += "'" + item + "', ";
                }
            }

            StockUPD += "END WHERE Name IN (" + StockEnd.substring(0, StockEnd.length() - 2) + ");";

            sql.SQLUpdate(StockUPD);
            sql.SQLUpdate("UPDATE Products SET InitialStock = Stock");

            SQLSetup();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_StockRecountActionPerformed

    private void RemoveStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveStockActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you woud like to remove this item from the system?", "Remove Item", JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0) {
            Stocklm.remove(StockList.getSelectedIndex());
            sql.SQLUpdate("DELETE FROM Products WHERE Barcode=" + Barcode);
            SQLSetup();
        }
    }//GEN-LAST:event_RemoveStockActionPerformed

    private void AddStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStockActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you woud like to add an item to the system?", "Add Item", JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0) {

            String newBarcode = JOptionPane.showInputDialog("Please enter Barcode for new item.", "");

            while (indexOfIntArray(Stock[0], newBarcode) != -1) {
                newBarcode = JOptionPane.showInputDialog("Please enter UNUSED Barcode for new item.", "");
            }

            String newName = JOptionPane.showInputDialog("Please enter Name for new item.", "");
            String newCost = JOptionPane.showInputDialog("Please enter Cost for new item.", "");
            String newStock = JOptionPane.showInputDialog("Please enter Stock Count for new item.", "");

            sql.SQLUpdate("INSERT INTO Products VALUES ('" + newBarcode + "','" + newName + "','" + newCost + "','" + newStock + "','" + newStock + "', 0)");

            SQLSetup();
            newBarcode = newName = newCost = newStock = null;

        }
    }//GEN-LAST:event_AddStockActionPerformed

    private void ItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemSaveActionPerformed
        try {
            sql.SQLUpdate("UPDATE Products SET Cost = '" + PriceText.getText() + "', Stock = '" + CurStockText.getText() + "', InitialStock='" + InitStockText.getText() + "' WHERE Barcode = '" + BarcodeText.getText() + "'");
            JOptionPane.showMessageDialog(null, "Update Successfull.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There was an error with the action perfomed.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            SQLSetup();
        }

    }//GEN-LAST:event_ItemSaveActionPerformed

    private void CurStockSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CurStockSliderMouseReleased
        CurStockText.requestFocus();
    }//GEN-LAST:event_CurStockSliderMouseReleased

    private void InitStockSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InitStockSliderMouseReleased
        InitStockText.requestFocus();
    }//GEN-LAST:event_InitStockSliderMouseReleased

    private void SQLTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SQLTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SQLTextActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        sql.SQLUpdate(SQLText.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SQLSetup();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String line = UserItemAddList.getSelectedValue().toString();
        String line2 = StockList.getSelectedValue().toString();

        String barcode;
        String pmkeys;
        String item;

        pmkeys = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
        barcode = line2.substring(line2.indexOf('[') + 1, line2.indexOf(']'));
        item = line2.substring(0, line2.indexOf('[') - 1);

        updUserItem(pmkeys, barcode, (int) numberSpinner.getValue(), item);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String line = UserItemAddList.getSelectedValue().toString();
        String line2 = StockList.getSelectedValue().toString();

        String barcode;
        String pmkeys;
        String item;

        pmkeys = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
        barcode = line2.substring(line2.indexOf('[') + 1, line2.indexOf(']'));
        item = line2.substring(0, line2.indexOf('[') - 1);

        updUserItem(pmkeys, barcode, -1 * (int) numberSpinner.getValue(), item);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void StockSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockSearchActionPerformed

    }//GEN-LAST:event_StockSearchActionPerformed

    private void StockSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StockSearchKeyPressed

    }//GEN-LAST:event_StockSearchKeyPressed

    private void StockSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StockSearchKeyReleased
                Stocklm.removeAllElements();

        searchStock = sql.SQLSend("SELECT Barcode, Name FROM Products WHERE Name LIKE '%" + StockSearch.getText() + "%' ORDER BY Name ASC", 2);

        for (int i = 0; i < searchStock[1].length; i++) {
            Stocklm.addElement(searchStock[1][i] + " [" + searchStock[0][i] + "] ");
        }

        if ("".equals(StockSearch.getText())) {
            for (int i = 0; i < Stock[1].length; i++) {
                Stocklm.addElement(Stock[1][i] + " [" + Stock[0][i] + "] ");
            }
        }
    }//GEN-LAST:event_StockSearchKeyReleased

    private void UserSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserSaveActionPerformed
        try {
            sql.SQLUpdate("UPDATE Users SET WeekCost = '" + BillText.getText() + "' WHERE PMKEYS = '" + PMKeyText.getText() + "'");
            JOptionPane.showMessageDialog(null, "Update Successfull.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There was an error with the action perfomed.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            SQLSetup();
        }
    }//GEN-LAST:event_UserSaveActionPerformed

    private void BillSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_BillSliderStateChanged
        BillText.setText(Math.round(5.00 * Math.ceil(BillSlider.getValue() / 5.00)) / 100.00 + "");
    }//GEN-LAST:event_BillSliderStateChanged

    private void UserSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserSearchKeyReleased

        Userlm.removeAllElements();

        searchUser = sql.SQLSend("SELECT PMKEYS, CONCAT(Name, ' ', Surname) AS Fullname FROM Users HAVING Fullname LIKE '%" + UserSearch.getText() + "%' ORDER BY Surname ASC", 2);

        for (int i = 0; i < searchUser[1].length; i++) {
            Userlm.addElement("[" + searchUser[0][i] + "] " + searchUser[1][i]);
        }

        if ("".equals(UserSearch.getText())) {
            for (int i = 0; i < User[1].length; i++) {
                Userlm.addElement("[" + User[0][i] + "] " + User[1][i] + " " + User[2][i]);
            }
        }
    }//GEN-LAST:event_UserSearchKeyReleased

    private void UserSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserSearchKeyPressed

    }//GEN-LAST:event_UserSearchKeyPressed

    private void AddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddUserActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you woud like to add a user to the system?", "Add User", JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0) {

            String newUser = JOptionPane.showInputDialog("Please enter PMKeys for new user.", "");
            String newGivenName = JOptionPane.showInputDialog("Please enter First name for new user.", "");
            String newSurname = JOptionPane.showInputDialog("Please enter Surname for new user.", "");

            sql.SQLUpdate("INSERT INTO Users VALUES ('" + newUser + "','" + newGivenName + "','" + newSurname + "','0','0','')");

            SQLSetup();
            newUser = newGivenName = newSurname = null;
        }
    }//GEN-LAST:event_AddUserActionPerformed

    private void RemoveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveUserActionPerformed

        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you woud like to remove this user from the system?", "Remove User", JOptionPane.YES_NO_OPTION);
        if (dialogResult == 0) {
            Userlm.remove(UserList.getSelectedIndex());
            sql.SQLUpdate("DELETE FROM Users WHERE PMKEYS=" + PMKey);
            SQLSetup();
        }
    }//GEN-LAST:event_RemoveUserActionPerformed

    private void UserListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserListMouseClicked
        String line = UserList.getSelectedValue().toString();
        PMKey = line.substring(1, line.indexOf(']'));

        ItemIndex = indexOfIntArray(User[0], PMKey);

        PMKeyText.setText(PMKey);

        Username = User[1][ItemIndex];

        BillSlider.setValue(((int) Double.parseDouble(User[3][ItemIndex])) * 100);
        BillText.setText(User[3][ItemIndex]);

        UserPieSetup((double) Double.valueOf(User[3][ItemIndex]), totalIncome);

        PurchaseSort(User[5][ItemIndex]);
    }//GEN-LAST:event_UserListMouseClicked

    private void UserListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserListKeyReleased
        UserListMouseClicked(null);
    }//GEN-LAST:event_UserListKeyReleased

    public void updUserItem(String Pmkeys, String itemBarcode, int numberOf, String itemName) {
        String[] purchArr = {sql.SQLSend("SELECT Purchases FROM Users WHERE PMKEYS = " + Pmkeys, 1)[0][0]};
        Boolean found = false;
        List purchases = new ArrayList();

        String purchList[] = (purchArr[0].indexOf(',') != -1) ? purchArr[0].split(",") : purchArr;

        if (!"".equals(purchArr[0])) {
            for (String item : purchList) {
                String[] temp = item.split(":");
                if (temp[0].equals(itemName)) {
                    found = true;
                    if ((Integer.valueOf(temp[1]) + numberOf) != 0) {
                        purchases.add(temp[0]);
                        purchases.add("" + (Integer.valueOf(temp[1]) + numberOf));
                    }
                } else {
                    purchases.add(temp[0]);
                    purchases.add(temp[1]);
                }
            }
        }

        if (!found) {
            purchases.add(itemName);
            purchases.add(numberOf);
        }

        purchArr[0] = "";

        for (int i = 0; i < purchases.size() - 1; i += 2) {
            purchArr[0] += ("," + purchases.get(i) + ":" + purchases.get(i + 1));
        }

        purchArr[0] = (!purchases.isEmpty()) ? purchArr[0].substring(1, purchArr[0].length()) : "";

        sql.SQLUpdate("UPDATE Products SET Stock = (Stock - " + numberOf + ") WHERE Barcode = '" + itemBarcode + "'");

        sql.SQLUpdate("UPDATE Users SET WeekCost = (WeekCost + " + (double) numberOf * (Double.valueOf(PriceText.getText())) + ") WHERE PMKEYS = " + Pmkeys);

        sql.SQLUpdate("UPDATE Users SET Purchases = '" + purchArr[0] + "' WHERE PMKEYS = " + Pmkeys);

        int userSel = UserItemAddList.getSelectedIndex();
        int stockSel = StockList.getSelectedIndex();

        SQLSetup();

        UserItemAddList.setSelectedIndex(userSel);
        StockList.setSelectedIndex(stockSel);
        
        JOptionPane.showMessageDialog(null, "Success! User [" + Pmkeys + "] was updated with " + numberOf + " additions of item [" + itemBarcode + "]", "Update", JOptionPane.INFORMATION_MESSAGE);
    }

    public static int indexOfIntArray(String[] array, String key) {
        int returnvalue = -1;
        for (int i = 0; i < array.length; ++i) {
            if (key.equals(array[i])) {
                returnvalue = i;
                break;
            }
        }
        return returnvalue;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html

         try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
         if ("Nimbus".equals(info.getName())) {
         javax.swing.UIManager.setLookAndFeel(info.getClassName());
         break;
         }
         }
         } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(AppMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }
         //</editor-fold>
         */
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Addr = JOptionPane.showInputDialog("Please enter LAN Address of shopping terminal.", "192.168.0.4");
                if (Addr != null) {
                    new AppMain().setVisible(true);
                } else {
                    System.exit(0);
                }
            }
        });
    }

    private final DefaultListModel Userlm = new DefaultListModel();
    private final DefaultListModel Stocklm = new DefaultListModel();
    private final DefaultListModel Purchlm = new DefaultListModel();
    private final DefaultListModel UserAddlm = new DefaultListModel();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddStock;
    private javax.swing.JButton AddUser;
    private javax.swing.JPanel BarcodePanel;
    private javax.swing.JSlider BarcodeSlider;
    private javax.swing.JTextField BarcodeText;
    private javax.swing.JLabel BillLabel;
    private javax.swing.JPanel BillPanel;
    private javax.swing.JSlider BillSlider;
    private javax.swing.JTextField BillText;
    private javax.swing.JLabel BillText1;
    private javax.swing.JLabel BillText2;
    private javax.swing.JPanel CurStockPanel;
    private javax.swing.JSlider CurStockSlider;
    private javax.swing.JTextField CurStockText;
    private javax.swing.JButton ExportBill;
    private javax.swing.JPanel InitStockPanel;
    private javax.swing.JSlider InitStockSlider;
    private javax.swing.JTextField InitStockText;
    private javax.swing.JButton ItemSave;
    private javax.swing.JToolBar ItemToolBar;
    private javax.swing.JLabel ItemToolLabel;
    private javax.swing.JPanel MaintenancePane;
    private javax.swing.JPanel MaitenancePanel;
    private javax.swing.JPanel PMKeyPanel;
    private javax.swing.JSlider PMKeySlider;
    private javax.swing.JTextField PMKeyText;
    private javax.swing.JPanel PricePanel;
    private javax.swing.JSlider PriceSlider;
    private javax.swing.JTextField PriceText;
    private javax.swing.JList Purchases;
    private javax.swing.JButton RemoveStock;
    private javax.swing.JButton RemoveUser;
    private javax.swing.JTextField SQLText;
    private javax.swing.JLabel StockLabel;
    private javax.swing.JList StockList;
    private javax.swing.JPanel StockPanel;
    private javax.swing.JButton StockRecount;
    private javax.swing.JScrollPane StockScrollPanel;
    private javax.swing.JTextField StockSearch;
    private javax.swing.JLabel StockText1;
    private javax.swing.JLabel StockText2;
    private javax.swing.JTabbedPane TabPanel;
    private javax.swing.JList UserItemAddList;
    private javax.swing.JList UserList;
    private javax.swing.JPanel UserPanel;
    private javax.swing.JPanel UserPiePanel;
    private javax.swing.JButton UserSave;
    private javax.swing.JScrollPane UserScollPanel;
    private javax.swing.JTextField UserSearch;
    private javax.swing.JLabel UserToolLabel;
    private javax.swing.JToolBar UserToolbar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSpinner numberSpinner;
    // End of variables declaration//GEN-END:variables
}
