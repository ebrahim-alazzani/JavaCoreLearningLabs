/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package the7bakerynew;

import java.awt.Image;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fadakahmed
 */
public class Menu7 extends javax.swing.JFrame {

    /**
     * Creates new form Menu7
     */
    Order order = new Order();

    Customer customer = new Customer();

    public Menu7(Order order, Customer customer) {
        initComponents();
        ds = new SevicesSqlCustomer();
        this.customer = customer;
        //   tableemp();
        getPaymentAndTotal();

        this.order = order;
        txtOrderId1.setText(order.getOrder_ID() + "");
        getTables();

    }
    SevicesSqlCustomer ds;
    ArrayList<Items> itemses;

    public Menu7(Customer customer) {
        initComponents();
        ds = new SevicesSqlCustomer();
        this.customer = customer;

        this.order = new Order();
        getTables();
        getPaymentAndTotal();

        add();

        txtOrderId1.setText(order.getOrder_ID() + "");
        getTables();

    }

    private Menu7() {
    }

    public void getTables() {
        getCake();
        getSandwich();
        getHotDrink();
        getColdDrink();
        tableCakeOrder(order.getOrder_ID());
        tableSandwichOrder(order.getOrder_ID());
        tableHot_Drinkses(order.getOrder_ID());
        tableCold_Drinkses(order.getOrder_ID());
        getPaymentAndTotal();
    }

    public Order getOrderAddLast(int orderId) {
        order = new Order();
        order = ds.getOrderAddLast(orderId);

        return order;
    }

    public void add() {
        int result = 0;

        int OrderId = 0;

        try {
            OrderId = ds.GetNextValue("Orders", "Order_ID");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "R failed." + ex.getMessage(), "Entry Error", 1);
        }
        this.order.setCustomer(customer);
        this.order.setOrderDate(new java.sql.Date(System.currentTimeMillis()));

        this.order.setTotal(0);

        try {
            if (ds.InsertOrders(this.order) == 1) {

                this.order = new Order();
                this.order = getOrderAddLast(OrderId);

                JOptionPane.showMessageDialog(this, "Records inserted successfully.", "Entry Error", 1);
            } else {
                JOptionPane.showMessageDialog(this, "Records insertion failed.", "Entry Error", 1);
            }

            //   con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "R failed." + ex.getMessage(), "Entry Error", 1);
        }
    }

    public void getCake() {
        try {

            cbCake.removeAllItems();
            itemses = ds.SelectItems(1);
            for (int i = 0; i < itemses.size(); i++) {

                cbCake.setModel(new DefaultComboBoxModel(itemses.toArray()));

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //   cbCake.getModel().setSelectedItem(" ");
    }

    public void getSandwich() {
        try {

            cbSandwich.removeAllItems();
            itemses = ds.SelectItems(2);
            for (int i = 0; i < itemses.size(); i++) {

                cbSandwich.setModel(new DefaultComboBoxModel(itemses.toArray()));

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getHotDrink() {
        try {

            cbHotDrinks.removeAllItems();
            itemses = ds.SelectItems(3);
            for (int i = 0; i < itemses.size(); i++) {

                cbHotDrinks.setModel(new DefaultComboBoxModel(itemses.toArray()));

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getColdDrink() {
        try {

            cbColdDrinks.removeAllItems();
            itemses = ds.SelectItems(4);
            for (int i = 0; i < itemses.size(); i++) {

                cbColdDrinks.setModel(new DefaultComboBoxModel(itemses.toArray()));

            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void add(Order_Info order_Info) {
        int result = 0;

        try {
            if (ds.InsertItemOrder(order_Info) == 1) {
                //tableemp();
                JOptionPane.showMessageDialog(this, "Records inserted successfully.", "Entry Error", 1);
            } else {
                JOptionPane.showMessageDialog(this, "Records insertion failed.", "Entry Error", 1);
            }

            //   con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Menu7.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ArrayList<Cake> listCakes;

    public void tableCakeOrder(int orderId) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Id", "PriceTotal", "QUantity",
            "Item Name", "Price Unit", "Size", "Layer", "Flavor", "Frosting"
        }, 0);
        listCakes = ds.SelectOrderCake(orderId);
        Object rowdata[] = new Object[9];
        //System.out.println("size" + list.size());
        for (int i = 0; i < listCakes.size(); i++) {
            rowdata[0] = listCakes.get(i).getOrder_Info_ID();
            rowdata[1] = listCakes.get(i).getItem_price();
            rowdata[2] = listCakes.get(i).getQuantity();
            rowdata[3] = listCakes.get(i).getItems().getName();
            rowdata[4] = listCakes.get(i).getItems().getPrice();
            rowdata[5] = listCakes.get(i).Size;
            rowdata[6] = listCakes.get(i).Layer;
            rowdata[7] = listCakes.get(i).Flavor;
            rowdata[8] = listCakes.get(i).Frosting;

            model.addRow(rowdata);
        }

        jtbCake.setModel(model);
    }

    ArrayList<Sandwich> listSandwichs;

    public void tableSandwichOrder(int orderId) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Id", "PriceTotal", "QUantity",
            "Item Name", "Price Unit", "BreadType", "ProteinType", "Spicy"
        }, 0);
        listSandwichs = ds.SelectOrderSandwich(orderId);
        Object rowdata[] = new Object[8];
        //System.out.println("size" + list.size());
        for (int i = 0; i < listSandwichs.size(); i++) {
            rowdata[0] = listSandwichs.get(i).getOrder_Info_ID();
            rowdata[1] = listSandwichs.get(i).getItem_price();
            rowdata[2] = listSandwichs.get(i).getQuantity();
            rowdata[3] = listSandwichs.get(i).getItems().getName();
            rowdata[4] = listSandwichs.get(i).getItems().getPrice();
            rowdata[5] = listSandwichs.get(i).BreadType;
            rowdata[6] = listSandwichs.get(i).ProteinType;
            rowdata[7] = listSandwichs.get(i).Spicy;

            model.addRow(rowdata);
        }

        jtbSandwich.setModel(model);
    }

    ArrayList<Hot_Drinks> listHot_Drinkses;

    public void tableHot_Drinkses(int orderId) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Id", "PriceTotal", "QUantity",
            "Item Name", "Price Unit", "Size", "AddSugar", "ExtraHot", "MilkType"
        }, 0);
        listHot_Drinkses = ds.SelectOrderHot_Drinks(orderId);
        Object rowdata[] = new Object[9];
        //System.out.println("size" + list.size());
        for (int i = 0; i < listHot_Drinkses.size(); i++) {
            rowdata[0] = listHot_Drinkses.get(i).getOrder_Info_ID();
            rowdata[1] = listHot_Drinkses.get(i).getItem_price();
            rowdata[2] = listHot_Drinkses.get(i).getQuantity();
            rowdata[3] = listHot_Drinkses.get(i).getItems().getName();
            rowdata[4] = listHot_Drinkses.get(i).getItems().getPrice();
            rowdata[5] = listHot_Drinkses.get(i).Size;
            rowdata[6] = listHot_Drinkses.get(i).AddSugar;
            rowdata[7] = listHot_Drinkses.get(i).ExtraHot;
            rowdata[8] = listHot_Drinkses.get(i).MilkType;

            model.addRow(rowdata);
        }

        jtbHotDrink.setModel(model);
    }

    ArrayList<Cold_Drinks> listCold_Drinkses;

    public void tableCold_Drinkses(int orderId) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Id", "PriceTotal", "QUantity",
            "Item Name", "Price Unit", "Size", "AddSugar", "Ice_Shaped", "MilkType"
        }, 0);
        listCold_Drinkses = ds.SelectOrderCold_Drinkse(orderId);
        Object rowdata[] = new Object[9];
        //System.out.println("size" + list.size());
        for (int i = 0; i < listCold_Drinkses.size(); i++) {
            rowdata[0] = listCold_Drinkses.get(i).getOrder_Info_ID();
            rowdata[1] = listCold_Drinkses.get(i).getItem_price();
            rowdata[2] = listCold_Drinkses.get(i).getQuantity();
            rowdata[3] = listCold_Drinkses.get(i).getItems().getName();
            rowdata[4] = listCold_Drinkses.get(i).getItems().getPrice();
            rowdata[5] = listCold_Drinkses.get(i).Size;
            rowdata[6] = listCold_Drinkses.get(i).AddSugar;
            rowdata[7] = listCold_Drinkses.get(i).Ice_Shaped;
            rowdata[8] = listCold_Drinkses.get(i).MilkType;

            model.addRow(rowdata);
        }

        jtbColdDrink.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        jCheckBox4 = new javax.swing.JCheckBox();
        txtPriceHotDrinkConst = new javax.swing.JLabel();
        txtPriceColdConst = new javax.swing.JLabel();
        txtPriceCakeConst = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtPriceColdDrink = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        spQuantityCake = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtPriceCake = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        cbLayerCake = new javax.swing.JComboBox<>();
        cbFroastingCake = new javax.swing.JComboBox<>();
        cbSizeCake = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        imgCake = new javax.swing.JLabel();
        cbCake = new javax.swing.JComboBox<>();
        txtPriceCake1 = new javax.swing.JLabel();
        txtPriceCakeTotal = new javax.swing.JLabel();
        txtPriceCake7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        btSaveCake = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPriceSandwich = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        spQuantitySandwich = new javax.swing.JSpinner();
        jLabel75 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        cbSpicySandwich = new javax.swing.JComboBox<>();
        cbProteinTypeSandwich = new javax.swing.JComboBox<>();
        cbBreadTypeSandwich = new javax.swing.JComboBox<>();
        cbSandwich = new javax.swing.JComboBox<>();
        imgSandwich = new javax.swing.JLabel();
        btSaveSandich = new javax.swing.JButton();
        txtPriceCake2 = new javax.swing.JLabel();
        txtPriceSandwichTotal = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        imgColdDrink = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        spQuantityColdDrink = new javax.swing.JSpinner();
        jLabel59 = new javax.swing.JLabel();
        txtPriceCold = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        cbAddSugarColdDrink = new javax.swing.JComboBox<>();
        cbIceShapedColdDrink = new javax.swing.JComboBox<>();
        jLabel90 = new javax.swing.JLabel();
        cbSizeColdDrink = new javax.swing.JComboBox<>();
        cbMilkTypeColdDrink = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jCheckBox11 = new javax.swing.JCheckBox();
        cbColdDrinks = new javax.swing.JComboBox<>();
        btSaveColdDrink = new javax.swing.JButton();
        txtPriceCake4 = new javax.swing.JLabel();
        txtPriceColdToatl = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtPriceCake6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        imgHotDrink = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        spQuantityHotDrink = new javax.swing.JSpinner();
        jLabel42 = new javax.swing.JLabel();
        txtPriceHotDrink = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        cbExtraHotHotDrink = new javax.swing.JComboBox<>();
        cbMilkTypHotDrink = new javax.swing.JComboBox<>();
        cbAddSugarHotDrink = new javax.swing.JComboBox<>();
        cbSizeHotDrink = new javax.swing.JComboBox<>();
        cbHotDrinks = new javax.swing.JComboBox<>();
        btSave1 = new javax.swing.JButton();
        txtPriceCake3 = new javax.swing.JLabel();
        txtPriceHotDrinkTotal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtPriceCake5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtAmountPay = new javax.swing.JTextField();
        txtAmount = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btPayment = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtbCake = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtbSandwich = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtbHotDrink = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtbColdDrink = new javax.swing.JTable();
        jLabel101 = new javax.swing.JLabel();
        txtOrderId1 = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtPayment = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jLabel32.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel32.setText("Price : ");

        jLabel33.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        jLabel33.setText("50.00 SAR");

        jLabel34.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel34.setText("Quantity :");

        jLabel35.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel35.setText("Purchase :");

        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        txtPriceHotDrinkConst.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceHotDrinkConst.setText("0");

        txtPriceColdConst.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceColdConst.setText("0");

        txtPriceCakeConst.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCakeConst.setText("0");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        txtPriceColdDrink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPriceColdDrinkMouseClicked(evt);
            }
        });

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel14.setToolTipText("1000 Calories");

        jLabel18.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel18.setText("Quantity :");

        jLabel19.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel19.setText("Price : ");

        jLabel20.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel20.setText("Total");

        txtPriceCake.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCake.setText("0");

        jLabel94.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel94.setText("Layers :");

        jLabel95.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel95.setText("Froasting :");

        jLabel96.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel96.setText("Size :");

        cbLayerCake.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        cbLayerCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLayerCakeActionPerformed(evt);
            }
        });

        cbFroastingCake.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "YES", "NO" }));
        cbFroastingCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFroastingCakeActionPerformed(evt);
            }
        });

        cbSizeCake.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L" }));
        cbSizeCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSizeCakeActionPerformed(evt);
            }
        });

        cbCake.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCake.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbCakeMouseClicked(evt);
            }
        });
        cbCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCakeActionPerformed(evt);
            }
        });

        txtPriceCake1.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCake1.setText("SAR");

        txtPriceCakeTotal.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCakeTotal.setText("0");

        txtPriceCake7.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCake7.setText("SAR");

        jButton4.setText("Result Toatl");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(imgCake, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel95)
                                        .addGap(15, 15, 15)
                                        .addComponent(cbFroastingCake, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel96)
                                        .addGap(51, 51, 51)
                                        .addComponent(cbSizeCake, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                            .addComponent(jLabel19)
                                            .addGap(0, 0, 0)
                                            .addComponent(txtPriceCake, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(txtPriceCake1))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel18)
                                                .addComponent(jLabel20))
                                            .addGap(11, 11, 11)
                                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel10Layout.createSequentialGroup()
                                                    .addComponent(txtPriceCakeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtPriceCake7)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton4))
                                                .addComponent(spQuantityCake, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 47, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel94)
                                .addGap(32, 32, 32)
                                .addComponent(cbLayerCake, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(cbCake, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel14)
                .addGap(4, 4, 4)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgCake, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(cbCake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(txtPriceCake)
                            .addComponent(txtPriceCake1))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel94)
                            .addComponent(cbLayerCake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel95)
                            .addComponent(cbFroastingCake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96)
                            .addComponent(cbSizeCake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(spQuantityCake, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPriceCakeTotal)
                                .addComponent(txtPriceCake7)
                                .addComponent(jButton4)))))
                .addGap(2, 2, 2)
                .addComponent(jLabel22))
        );

        jScrollPane4.setViewportView(jPanel10);

        btSaveCake.setFont(new java.awt.Font("Lemonada SemiBold", 1, 14)); // NOI18N
        btSaveCake.setText("Add To Order");
        btSaveCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveCakeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addComponent(btSaveCake, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btSaveCake, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtPriceColdDrink.addTab("Cake", jPanel3);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jLabel1.setToolTipText("Ingredients: flour, sugar, yeast, salt, milk, water, eggs, olive oil, thyme");

        txtPriceSandwich.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceSandwich.setText("0");

        jLabel73.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel73.setText("Price :");

        jLabel74.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel74.setText("Quantity :");

        jLabel75.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel75.setText("Total");

        jLabel91.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel91.setText("Spice :");

        jLabel92.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel92.setText("Bread Type :");

        jLabel93.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel93.setText("Protein Type : ");

        cbSpicySandwich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "YES" }));
        cbSpicySandwich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSpicySandwichActionPerformed(evt);
            }
        });

        cbProteinTypeSandwich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chicken", "Meat", "Tuna" }));
        cbProteinTypeSandwich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProteinTypeSandwichActionPerformed(evt);
            }
        });

        cbBreadTypeSandwich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "B", "W" }));
        cbBreadTypeSandwich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBreadTypeSandwichActionPerformed(evt);
            }
        });

        cbSandwich.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbSandwich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbSandwichMouseClicked(evt);
            }
        });
        cbSandwich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSandwichActionPerformed(evt);
            }
        });

        btSaveSandich.setFont(new java.awt.Font("Lemonada SemiBold", 1, 14)); // NOI18N
        btSaveSandich.setText("Add To Order");
        btSaveSandich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveSandichActionPerformed(evt);
            }
        });

        txtPriceCake2.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCake2.setText("SAR");

        txtPriceSandwichTotal.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceSandwichTotal.setText("0");

        jButton3.setText("Result Toatl");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(imgSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel73)
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPriceSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addComponent(txtPriceCake2))))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel92)
                                        .addGap(40, 40, 40)
                                        .addComponent(cbBreadTypeSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel93)
                                        .addGap(27, 27, 27)
                                        .addComponent(cbProteinTypeSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel91)
                                        .addGap(81, 81, 81)
                                        .addComponent(cbSpicySandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel74)
                                        .addGap(61, 61, 61)
                                        .addComponent(spQuantitySandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btSaveSandich, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel75)
                                        .addGap(46, 46, 46)
                                        .addComponent(txtPriceSandwichTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel4))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(cbSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPriceSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPriceCake2))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbBreadTypeSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbProteinTypeSandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSpicySandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spQuantitySandwich, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPriceSandwichTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addGap(18, 18, 18)
                        .addComponent(btSaveSandich)))
                .addGap(140, 140, 140)
                .addComponent(jLabel4))
        );

        jScrollPane1.setViewportView(jPanel7);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 43, Short.MAX_VALUE))
        );

        txtPriceColdDrink.addTab("Sandwich", jPanel4);

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        imgColdDrink.setToolTipText("100 Calories");

        jLabel56.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel56.setText("Price :");

        jLabel57.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel57.setText("Quantity :");

        jLabel58.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel58.setText("Total");

        txtPriceCold.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCold.setText("0");

        jLabel87.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel87.setText("Add Sugar :");

        jLabel88.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel88.setText("Milk type :");

        jLabel89.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel89.setText("Ice Shaped :");

        cbAddSugarColdDrink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "YES" }));
        cbAddSugarColdDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAddSugarColdDrinkActionPerformed(evt);
            }
        });

        cbIceShapedColdDrink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cubes", "Crashed" }));
        cbIceShapedColdDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIceShapedColdDrinkActionPerformed(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel90.setText("Size:");

        cbSizeColdDrink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L" }));
        cbSizeColdDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSizeColdDrinkActionPerformed(evt);
            }
        });

        cbMilkTypeColdDrink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal Milk", "YES" }));
        cbMilkTypeColdDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMilkTypeColdDrinkActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel71.setText("Purchase :");

        cbColdDrinks.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbColdDrinks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbColdDrinksMouseClicked(evt);
            }
        });
        cbColdDrinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbColdDrinksActionPerformed(evt);
            }
        });

        btSaveColdDrink.setFont(new java.awt.Font("Lemonada SemiBold", 1, 14)); // NOI18N
        btSaveColdDrink.setText("Add To Order");
        btSaveColdDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveColdDrinkActionPerformed(evt);
            }
        });

        txtPriceCake4.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCake4.setText("SAR");

        txtPriceColdToatl.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceColdToatl.setText("0");

        jButton2.setText("Resualt Toatl");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtPriceCake6.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCake6.setText("SAR");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(imgColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbColdDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(jLabel59))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addGap(26, 26, 26)
                                .addComponent(txtPriceCold, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel88)
                                .addGap(32, 32, 32)
                                .addComponent(cbMilkTypeColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel87)
                                .addGap(23, 23, 23)
                                .addComponent(cbAddSugarColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel89)
                                .addGap(18, 18, 18)
                                .addComponent(cbIceShapedColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel90)
                                .addGap(70, 70, 70)
                                .addComponent(cbSizeColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(btSaveColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel58))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spQuantityColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(txtPriceColdToatl, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPriceCake4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2)))
                                .addGap(11, 11, 11))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(jLabel71)
                        .addGap(12, 12, 12)
                        .addComponent(jCheckBox11))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap(510, Short.MAX_VALUE)
                    .addComponent(txtPriceCake6)
                    .addGap(171, 171, 171)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cbColdDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel59)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel56))
                            .addComponent(txtPriceCold))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel88))
                            .addComponent(cbMilkTypeColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel87))
                            .addComponent(cbAddSugarColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel89))
                            .addComponent(cbIceShapedColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel90))
                            .addComponent(cbSizeColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(spQuantityColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel58))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPriceColdToatl)
                                    .addComponent(txtPriceCake4)
                                    .addComponent(jButton2))))
                        .addGap(17, 17, 17)
                        .addComponent(btSaveColdDrink)))
                .addGap(334, 334, 334)
                .addComponent(jLabel38)
                .addGap(265, 265, 265)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71)
                    .addComponent(jCheckBox11))
                .addGap(6, 6, 6)
                .addComponent(jLabel12))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(txtPriceCake6)
                    .addContainerGap(838, Short.MAX_VALUE)))
        );

        jScrollPane3.setViewportView(jPanel9);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtPriceColdDrink.addTab("Cold drinks", jPanel6);

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setToolTipText("");
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jLabel36.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel36.setText("Price : ");

        jLabel41.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel41.setText("Quantity :");

        spQuantityHotDrink.setValue(1);
        spQuantityHotDrink.setVerifyInputWhenFocusTarget(false);
        spQuantityHotDrink.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                spQuantityHotDrinkAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        spQuantityHotDrink.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                spQuantityHotDrinkInputMethodTextChanged(evt);
            }
        });
        spQuantityHotDrink.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spQuantityHotDrinkPropertyChange(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel42.setText("Total");

        txtPriceHotDrink.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceHotDrink.setText("0");

        jLabel97.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel97.setText("Extra hot :");

        jLabel98.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel98.setText("Milk Type :");

        jLabel99.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel99.setText("Size :");

        jLabel100.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel100.setText("Add Sugar :");

        cbExtraHotHotDrink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "YES" }));
        cbExtraHotHotDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbExtraHotHotDrinkActionPerformed(evt);
            }
        });

        cbMilkTypHotDrink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Normal Milk" }));
        cbMilkTypHotDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMilkTypHotDrinkActionPerformed(evt);
            }
        });

        cbAddSugarHotDrink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "YES" }));
        cbAddSugarHotDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAddSugarHotDrinkActionPerformed(evt);
            }
        });

        cbSizeHotDrink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L" }));
        cbSizeHotDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSizeHotDrinkActionPerformed(evt);
            }
        });

        cbHotDrinks.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbHotDrinks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbHotDrinksMouseClicked(evt);
            }
        });
        cbHotDrinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHotDrinksActionPerformed(evt);
            }
        });

        btSave1.setFont(new java.awt.Font("Lemonada SemiBold", 1, 14)); // NOI18N
        btSave1.setText("Add To Order");
        btSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSave1ActionPerformed(evt);
            }
        });

        txtPriceCake3.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCake3.setText("SAR");

        txtPriceHotDrinkTotal.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceHotDrinkTotal.setText("0");

        jButton1.setText("Result Toatal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtPriceCake5.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 14)); // NOI18N
        txtPriceCake5.setText("SAR");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(imgHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel97)
                                        .addGap(22, 22, 22)
                                        .addComponent(cbExtraHotHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel98)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbMilkTypHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel100)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbAddSugarHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel99)
                                        .addGap(61, 61, 61)
                                        .addComponent(cbSizeHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel41)
                                            .addComponent(jLabel42))
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(spQuantityHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(txtPriceHotDrinkTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPriceCake3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton1))))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(btSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addGap(12, 12, 12)
                                        .addComponent(txtPriceHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPriceCake5))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbHotDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(cbHotDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPriceHotDrink)
                                .addComponent(txtPriceCake5)))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel97))
                            .addComponent(cbExtraHotHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel98))
                            .addComponent(cbMilkTypHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel100))
                            .addComponent(cbAddSugarHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel99))
                            .addComponent(cbSizeHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel41))
                            .addComponent(spQuantityHotDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txtPriceHotDrinkTotal)
                            .addComponent(jButton1)
                            .addComponent(txtPriceCake3))
                        .addGap(32, 32, 32)
                        .addComponent(btSave1)))
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(337, 337, 337)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane2.setViewportView(jPanel8);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtPriceColdDrink.addTab("Hot drinks", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtPriceColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtPriceColdDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.addTab("Home", jPanel1);

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAmountPay.setEnabled(false);
        txtAmountPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountPayActionPerformed(evt);
            }
        });
        jPanel11.add(txtAmountPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, 150, 30));

        txtAmount.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        txtAmount.setText("Total :");
        jPanel11.add(txtAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 190, -1));

        jLabel17.setBackground(new java.awt.Color(153, 153, 153));
        jLabel17.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 36)); // NOI18N
        jLabel17.setText("Cake List View With Details");
        jPanel11.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 610, 50));

        btPayment.setText("Payment");
        btPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPaymentActionPerformed(evt);
            }
        });
        jPanel11.add(btPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 370, 170, 30));

        jScrollPane6.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtbCake.setBackground(new java.awt.Color(247, 247, 247));
        jtbCake.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jtbCake.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbCake.setGridColor(new java.awt.Color(247, 247, 247));
        jtbCake.setSelectionBackground(new java.awt.Color(96, 83, 150));
        jtbCake.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbCakeMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jtbCake);

        jTabbedPane2.addTab("Cake Order", jScrollPane6);

        jScrollPane7.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtbSandwich.setBackground(new java.awt.Color(247, 247, 247));
        jtbSandwich.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jtbSandwich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbSandwich.setGridColor(new java.awt.Color(247, 247, 247));
        jtbSandwich.setSelectionBackground(new java.awt.Color(96, 83, 150));
        jtbSandwich.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbSandwichMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jtbSandwich);

        jTabbedPane2.addTab("SandwichOrder", jScrollPane7);

        jScrollPane8.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtbHotDrink.setBackground(new java.awt.Color(247, 247, 247));
        jtbHotDrink.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jtbHotDrink.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbHotDrink.setGridColor(new java.awt.Color(247, 247, 247));
        jtbHotDrink.setSelectionBackground(new java.awt.Color(96, 83, 150));
        jtbHotDrink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbHotDrinkMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jtbHotDrink);

        jTabbedPane2.addTab("Hot Drink Order", jScrollPane8);

        jScrollPane9.setBackground(new java.awt.Color(247, 247, 247));
        jScrollPane9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtbColdDrink.setBackground(new java.awt.Color(247, 247, 247));
        jtbColdDrink.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jtbColdDrink.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbColdDrink.setGridColor(new java.awt.Color(247, 247, 247));
        jtbColdDrink.setSelectionBackground(new java.awt.Color(96, 83, 150));
        jtbColdDrink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbColdDrinkMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jtbColdDrink);

        jTabbedPane2.addTab("Cold Drink Order", jScrollPane9);

        jPanel11.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 710, 220));

        jLabel101.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        jLabel101.setText("Total :");
        jPanel11.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 370, -1, 30));

        txtOrderId1.setFont(new java.awt.Font("Hiragino Mincho ProN", 1, 14)); // NOI18N
        txtOrderId1.setText("Total :");
        jPanel11.add(txtOrderId1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 190, -1));

        exit.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        exit.setText("X");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMousePressed(evt);
            }
        });
        jPanel11.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 22, -1));

        txtTotal.setEnabled(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel11.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 160, 30));

        txtPayment.setEnabled(false);
        txtPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaymentActionPerformed(evt);
            }
        });
        jPanel11.add(txtPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, 150, 30));

        jButton5.setText("Back To Main");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 140, -1));

        jButton6.setText("View Order");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 100, -1));

        jTabbedPane1.addTab("Cart", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void txtAmountPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountPayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountPayActionPerformed

    String IceShapedColdDrink;
    private void cbIceShapedColdDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIceShapedColdDrinkActionPerformed
        IceShapedColdDrink = (String) cbIceShapedColdDrink.getSelectedItem();
    }//GEN-LAST:event_cbIceShapedColdDrinkActionPerformed
    Boolean AddSugarColdDrink;


    private void btSaveColdDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveColdDrinkActionPerformed
        Price = 0;

        Quntity = Integer.parseInt(spQuantityHotDrink.getValue().toString());
        Price += Quntity * Double.parseDouble(txtPriceHotDrink.getText());
        Cold_Drinks cold_Drinks = new Cold_Drinks(AddSugarColdDrinkSt, MilkTypeColdDrink,
                SizeColdDrink, IceShapedColdDrink, 0, order, items, Quntity, Price);
        add(cold_Drinks);
        getPaymentAndTotal();
        getTables();


    }//GEN-LAST:event_btSaveColdDrinkActionPerformed

    private void btSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSave1ActionPerformed
        Price = 0;

        Quntity = Integer.parseInt(spQuantityHotDrink.getValue().toString());
        Price += Quntity * Double.parseDouble(txtPriceHotDrink.getText());
        txtPriceHotDrinkTotal.setText(Price + "");
        Hot_Drinks drinks = new Hot_Drinks(AddSugarHotDrinkSt, SizeHotDrink, MilkTypHotDrink,
                ExtraHotHotDrinkSt, 0, order, items, Quntity, Price);
        add(drinks);
        getPaymentAndTotal();
        getTables();


    }//GEN-LAST:event_btSave1ActionPerformed


    private void btSaveSandichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveSandichActionPerformed
        Price = 0;

        Quntity = Integer.parseInt(spQuantitySandwich.getValue().toString());
        Price += Quntity * Double.parseDouble(txtPriceSandwich.getText());
        Sandwich sandwich = new Sandwich(SpicySandwichSt, BreadTypeSandwich, ProteinTypeSandwich,
                0, order, items, Quntity, Price);

        add(sandwich);
        getPaymentAndTotal();
        getTables();


    }//GEN-LAST:event_btSaveSandichActionPerformed

    double Price;
    int Quntity;
    private void btSaveCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveCakeActionPerformed
        Price = 0;
        Quntity = Integer.parseInt(spQuantityCake.getValue().toString());
        Price += Quntity * Double.parseDouble(txtPriceCake.getText());
        Cake cake = new Cake(SizeCake, Integer.parseInt(LayerCake), FrostingCake, "k", 0, order, items, Quntity, Price);

        add(cake);
        getPaymentAndTotal();
        getTables();

    }//GEN-LAST:event_btSaveCakeActionPerformed

    String Imagepath;
    Items items = new Items();
    private void cbCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCakeActionPerformed

    }//GEN-LAST:event_cbCakeActionPerformed

    private void txtPriceColdDrinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPriceColdDrinkMouseClicked

    }//GEN-LAST:event_txtPriceColdDrinkMouseClicked


    private void cbCakeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCakeMouseClicked
        items = (Items) cbCake.getSelectedItem();
        if (items != null) {
            txtPriceCakeConst.setText(items.getPrice() + "");
            txtPriceCake.setText(items.getPrice() + "");
            //priceItems=items.getPrice();
            imgCake.setIcon(Resize(items.getItemImg()));

        }
    }//GEN-LAST:event_cbCakeMouseClicked

    private void cbSandwichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbSandwichMouseClicked
        items = (Items) cbSandwich.getSelectedItem();
        if (items != null) {
            txtPriceSandwich.setText(items.getPrice() + "");

            imgSandwich.setIcon(Resize(items.getItemImg()));

        }
    }//GEN-LAST:event_cbSandwichMouseClicked

    private void cbHotDrinksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbHotDrinksMouseClicked
        items = (Items) cbHotDrinks.getSelectedItem();
        if (items != null) {
            txtPriceHotDrink.setText(items.getPrice() + "");
            txtPriceHotDrinkConst.setText(items.getPrice() + "");

            imgHotDrink.setIcon(Resize(items.getItemImg()));

        }
    }//GEN-LAST:event_cbHotDrinksMouseClicked

    private void cbColdDrinksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbColdDrinksMouseClicked
        items = (Items) cbColdDrinks.getSelectedItem();
        if (items != null) {
               txtPriceColdConst.setText(items.getPrice() + "");
            txtPriceCold.setText(txtPriceCakeConst.getText());

            imgColdDrink.setIcon(Resize(items.getItemImg()));

        }
    }//GEN-LAST:event_cbColdDrinksMouseClicked

    String SizeCake;
    String LayerCake;
    String FrostingCake;
    double layerAddprice = 0, layerAddprice3 = 10, priceItems = 0;
    private void cbLayerCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLayerCakeActionPerformed
        LayerCake = (String) cbLayerCake.getSelectedItem();

        if (LayerCake.equals("2")) {
            priceItems = Double.parseDouble(txtPriceCakeConst.getText());
            layerAddprice = 5;

            priceItems = priceItems + layerAddprice+sizeAddprice;
            txtPriceCake.setText(priceItems + "");
        } else if (LayerCake.equals("3")) {
            priceItems = Double.parseDouble(txtPriceCakeConst.getText());
            layerAddprice = 10;

            priceItems = priceItems + layerAddprice+sizeAddprice;
            txtPriceCake.setText(priceItems + "");
        } else {
            priceItems = Double.parseDouble(txtPriceCakeConst.getText());
            layerAddprice = 0;

            priceItems = priceItems + layerAddprice+sizeAddprice;

            txtPriceCake.setText(priceItems + "");
        }
    }//GEN-LAST:event_cbLayerCakeActionPerformed
    double sizeAddprice = 0;
    private void cbSizeCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSizeCakeActionPerformed
        SizeCake = (String) cbSizeCake.getSelectedItem();
    
        if (SizeCake.equals("M")) {
            priceItems = Double.parseDouble(txtPriceCakeConst.getText());
            sizeAddprice = 2;
            priceItems = priceItems + layerAddprice + sizeAddprice;
            txtPriceCake.setText(priceItems + "");
        } else if (SizeCake.equals("L")) {
             priceItems = Double.parseDouble(txtPriceCakeConst.getText());
            sizeAddprice = 4;
            priceItems = priceItems + layerAddprice + sizeAddprice;
            txtPriceCake.setText(priceItems + "");
        }else {
            priceItems = Double.parseDouble(txtPriceCakeConst.getText());
            sizeAddprice = 0;

            priceItems = priceItems + sizeAddprice;

            txtPriceCake.setText(priceItems + "");
        }

    }//GEN-LAST:event_cbSizeCakeActionPerformed

    private void cbFroastingCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFroastingCakeActionPerformed
        FrostingCake = (String) cbFroastingCake.getSelectedItem();
    }//GEN-LAST:event_cbFroastingCakeActionPerformed

    String BreadTypeSandwich, ProteinTypeSandwich, SpicySandwichSt;

    private void cbBreadTypeSandwichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBreadTypeSandwichActionPerformed
        BreadTypeSandwich = (String) cbBreadTypeSandwich.getSelectedItem();
    }//GEN-LAST:event_cbBreadTypeSandwichActionPerformed

    private void cbProteinTypeSandwichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProteinTypeSandwichActionPerformed
        ProteinTypeSandwich = (String) cbProteinTypeSandwich.getSelectedItem();

    }//GEN-LAST:event_cbProteinTypeSandwichActionPerformed


    private void cbSpicySandwichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSpicySandwichActionPerformed
        SpicySandwichSt = (String) cbSpicySandwich.getSelectedItem();


    }//GEN-LAST:event_cbSpicySandwichActionPerformed

    String ExtraHotHotDrinkSt, MilkTypHotDrink, AddSugarHotDrinkSt, SizeHotDrink;
    private void cbExtraHotHotDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbExtraHotHotDrinkActionPerformed
        ExtraHotHotDrinkSt = (String) cbExtraHotHotDrink.getSelectedItem();
    }//GEN-LAST:event_cbExtraHotHotDrinkActionPerformed


    private void cbMilkTypHotDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMilkTypHotDrinkActionPerformed
        MilkTypHotDrink = (String) cbMilkTypHotDrink.getSelectedItem();
    }//GEN-LAST:event_cbMilkTypHotDrinkActionPerformed

    private void cbAddSugarHotDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAddSugarHotDrinkActionPerformed
        AddSugarHotDrinkSt = (String) cbAddSugarHotDrink.getSelectedItem();
    }//GEN-LAST:event_cbAddSugarHotDrinkActionPerformed
    double sizeAddpriceHotDrink,priceItemsHotDrink;

    private void cbSizeHotDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSizeHotDrinkActionPerformed
        SizeHotDrink = (String) cbSizeHotDrink.getSelectedItem();
          if (SizeHotDrink.equals("M")) {
            priceItemsHotDrink = Double.parseDouble(txtPriceColdConst.getText());
            sizeAddpriceHotDrink = 2;
            priceItemsHotDrink = priceItemsHotDrink  + sizeAddpriceHotDrink;
            txtPriceCold.setText(priceItemsHotDrink + "");
        } else if (SizeHotDrink.equals("L")) {
             priceItemsHotDrink = Double.parseDouble(txtPriceColdConst.getText());
            sizeAddpriceHotDrink = 4;
            priceItemsHotDrink = priceItemsHotDrink  + sizeAddpriceHotDrink;
            txtPriceCold.setText(priceItemsHotDrink + "");
        }else {
            priceItemsHotDrink = Double.parseDouble(txtPriceColdConst.getText());
            sizeAddpriceHotDrink = 0;

            priceItemsHotDrink = priceItemsHotDrink + sizeAddpriceHotDrink;

            txtPriceCold.setText(priceItemsHotDrink + "");
        }

    }//GEN-LAST:event_cbSizeHotDrinkActionPerformed

    String MilkTypeColdDrink, AddSugarColdDrinkSt, SizeColdDrink;
    private void cbMilkTypeColdDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMilkTypeColdDrinkActionPerformed
        MilkTypeColdDrink = (String) cbMilkTypeColdDrink.getSelectedItem();
    }//GEN-LAST:event_cbMilkTypeColdDrinkActionPerformed

    private void cbAddSugarColdDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAddSugarColdDrinkActionPerformed
        AddSugarColdDrinkSt = (String) cbAddSugarColdDrink.getSelectedItem();
    }//GEN-LAST:event_cbAddSugarColdDrinkActionPerformed


    double sizeAddpriceColdDrink,priceItemsColdDrink;
    private void cbSizeColdDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSizeColdDrinkActionPerformed
        SizeColdDrink = (String) cbSizeColdDrink.getSelectedItem();
         if (SizeColdDrink.equals("M")) {
            priceItemsColdDrink = Double.parseDouble(txtPriceColdConst.getText());
            sizeAddpriceColdDrink = 2;
            priceItemsColdDrink = priceItemsColdDrink  + sizeAddpriceColdDrink;
            txtPriceCold.setText(priceItemsColdDrink + "");
        } else if (SizeColdDrink.equals("L")) {
             priceItemsColdDrink = Double.parseDouble(txtPriceColdConst.getText());
            sizeAddpriceColdDrink = 4;
            priceItemsColdDrink = priceItemsColdDrink  + sizeAddpriceColdDrink;
            txtPriceCold.setText(priceItemsColdDrink + "");
        }else {
            priceItemsColdDrink = Double.parseDouble(txtPriceColdConst.getText());
            sizeAddpriceColdDrink = 0;

            priceItemsColdDrink = priceItemsColdDrink + sizeAddpriceColdDrink;

            txtPriceCold.setText(priceItemsColdDrink + "");
        }

    }//GEN-LAST:event_cbSizeColdDrinkActionPerformed

    private void cbColdDrinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbColdDrinksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbColdDrinksActionPerformed

    private void cbHotDrinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHotDrinksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHotDrinksActionPerformed

    private void cbSandwichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSandwichActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSandwichActionPerformed

    private void jtbCakeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbCakeMouseClicked
        //TODO add your handling code here:
        this.order = new Order();
        DefaultTableModel model = (DefaultTableModel) jtbCake.getModel();

        int selectedrow = jtbCake.getSelectedRow();
        order.setOrder_ID(Integer.parseInt(model.getValueAt(selectedrow, 0).toString()));
        // order.setOrderDate(model.getValueAt(selectedrow, 1).toString());

        //  btUpdate.setEnabled(true);
        //btDelete.setEnabled(true);
        // btSave.setEnabled(false);
    }//GEN-LAST:event_jtbCakeMouseClicked
    public double Payment(int orderId) {
        double pay = 0;
        Payment p = ds.SelectPayment(orderId);
        pay = p.getAmount();
        return pay;
    }

    public double getTotalOrder(int orderId) {
        double total = 0;
        total = ds.SelectOrderTotalSum(orderId);
        return total;
    }
    double amounts, payments, an;

    public void getPaymentAndTotal() {
        amounts = getTotalOrder(order.getOrder_ID());
        txtTotal.setText(amounts + "");
        payments = Payment(order.getOrder_ID());
        an = amounts - payments;
        txtAmountPay.setText(an + "");
        txtPayment.setText(payments + "");

        //  tableOrderItem(order.getOrder_ID());
        //  btSave.setEnabled(false);
    }

    private void btPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPaymentActionPerformed

        if (amounts == payments) {
            if (amounts == 0) {
                JOptionPane.showMessageDialog(this, "Not Any Amount", "Entry Error", 1);
            } else {
                JOptionPane.showMessageDialog(this, "Comblet Payment Amount", "Entry Error", 1);
            }

        } else {
            btPayment.setEnabled(true);
            addPayment();

        }
    }//GEN-LAST:event_btPaymentActionPerformed
    Payment payment = new Payment();

    public void addPayment() {
        int result = 0;

        payment.setOrder_ID(order.getOrder_ID());
        payment.setAmount(an + payments);

        ds.updatePayment(order.getOrder_ID(), payment);
        getPaymentAndTotal();
        JOptionPane.showMessageDialog(this, "Comblet Payment.", "Entry Error", 1);

        //   con.close();
    }
    private void jtbSandwichMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbSandwichMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbSandwichMouseClicked

    private void jtbHotDrinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbHotDrinkMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbHotDrinkMouseClicked

    private void jtbColdDrinkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbColdDrinkMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtbColdDrinkMouseClicked

    private void exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMousePressed
        // TODO add your handling code here:

        OrderView orderView = new OrderView(order, customer);
        orderView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_exitMousePressed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaymentActionPerformed

    private void spQuantityHotDrinkAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_spQuantityHotDrinkAncestorAdded


    }//GEN-LAST:event_spQuantityHotDrinkAncestorAdded

    private void spQuantityHotDrinkInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_spQuantityHotDrinkInputMethodTextChanged
    }//GEN-LAST:event_spQuantityHotDrinkInputMethodTextChanged

    private void spQuantityHotDrinkPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spQuantityHotDrinkPropertyChange
    }//GEN-LAST:event_spQuantityHotDrinkPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Price = 0;
        Quntity = Integer.parseInt(spQuantityHotDrink.getValue().toString());
        Price += Quntity * Double.parseDouble(txtPriceHotDrink.getText());
        txtPriceHotDrinkTotal.setText(Price + "");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Price = 0;
        Quntity = Integer.parseInt(spQuantityColdDrink.getValue().toString());
        Price += Quntity * Double.parseDouble(txtPriceCold.getText());
        txtPriceColdToatl.setText(Price + "");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Price = 0;
        Quntity = Integer.parseInt(spQuantitySandwich.getValue().toString());
        Price += Quntity * Double.parseDouble(txtPriceSandwich.getText());
        txtPriceSandwichTotal.setText(Price + "");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Price = 0;
        Quntity = Integer.parseInt(spQuantityCake.getValue().toString());
        Price += Quntity * Double.parseDouble(txtPriceCake.getText());
        txtPriceCakeTotal.setText(Price + "");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        bakery b7 = new bakery();

        b7.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        OrderView orderView = new OrderView(order, customer);
        orderView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed
    public ImageIcon Resize(String Imagepath) {
        ImageIcon myimage = null;
        if (Imagepath != null) {
            myimage = new ImageIcon(Imagepath);
        }
        Image img = myimage.getImage();
        img = img.getScaledInstance(imgCake.getWidth(), imgCake.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
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
            java.util.logging.Logger.getLogger(Menu7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu7().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPayment;
    private javax.swing.JButton btSave1;
    private javax.swing.JButton btSaveCake;
    private javax.swing.JButton btSaveColdDrink;
    private javax.swing.JButton btSaveSandich;
    private javax.swing.JComboBox<String> cbAddSugarColdDrink;
    private javax.swing.JComboBox<String> cbAddSugarHotDrink;
    private javax.swing.JComboBox<String> cbBreadTypeSandwich;
    private javax.swing.JComboBox<String> cbCake;
    private javax.swing.JComboBox<String> cbColdDrinks;
    private javax.swing.JComboBox<String> cbExtraHotHotDrink;
    private javax.swing.JComboBox<String> cbFroastingCake;
    private javax.swing.JComboBox<String> cbHotDrinks;
    private javax.swing.JComboBox<String> cbIceShapedColdDrink;
    private javax.swing.JComboBox<String> cbLayerCake;
    private javax.swing.JComboBox<String> cbMilkTypHotDrink;
    private javax.swing.JComboBox<String> cbMilkTypeColdDrink;
    private javax.swing.JComboBox<String> cbProteinTypeSandwich;
    private javax.swing.JComboBox<String> cbSandwich;
    private javax.swing.JComboBox<String> cbSizeCake;
    private javax.swing.JComboBox<String> cbSizeColdDrink;
    private javax.swing.JComboBox<String> cbSizeHotDrink;
    private javax.swing.JComboBox<String> cbSpicySandwich;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel imgCake;
    private javax.swing.JLabel imgColdDrink;
    private javax.swing.JLabel imgHotDrink;
    private javax.swing.JLabel imgSandwich;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jtbCake;
    private javax.swing.JTable jtbColdDrink;
    private javax.swing.JTable jtbHotDrink;
    private javax.swing.JTable jtbSandwich;
    private javax.swing.JSpinner spQuantityCake;
    private javax.swing.JSpinner spQuantityColdDrink;
    private javax.swing.JSpinner spQuantityHotDrink;
    private javax.swing.JSpinner spQuantitySandwich;
    private javax.swing.JLabel txtAmount;
    private javax.swing.JTextField txtAmountPay;
    private javax.swing.JLabel txtOrderId1;
    private javax.swing.JTextField txtPayment;
    private javax.swing.JLabel txtPriceCake;
    private javax.swing.JLabel txtPriceCake1;
    private javax.swing.JLabel txtPriceCake2;
    private javax.swing.JLabel txtPriceCake3;
    private javax.swing.JLabel txtPriceCake4;
    private javax.swing.JLabel txtPriceCake5;
    private javax.swing.JLabel txtPriceCake6;
    private javax.swing.JLabel txtPriceCake7;
    private javax.swing.JLabel txtPriceCakeConst;
    private javax.swing.JLabel txtPriceCakeTotal;
    private javax.swing.JLabel txtPriceCold;
    private javax.swing.JLabel txtPriceColdConst;
    private javax.swing.JTabbedPane txtPriceColdDrink;
    private javax.swing.JLabel txtPriceColdToatl;
    private javax.swing.JLabel txtPriceHotDrink;
    private javax.swing.JLabel txtPriceHotDrinkConst;
    private javax.swing.JLabel txtPriceHotDrinkTotal;
    private javax.swing.JLabel txtPriceSandwich;
    private javax.swing.JLabel txtPriceSandwichTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
