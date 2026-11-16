/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the7bakerynew;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ServiceSqlEmployee {

    Connection con = DBconnect.ConnectDB();
    DefaultTableModel model;
    Statement stmt;
    PreparedStatement pst;
    ResultSet rs;
    
      public int GetNextValue(String table, String feild) throws SQLException {
        int result = 1;

        Statement st;
        ResultSet rs;

        st = con.createStatement();
        rs = st.executeQuery("SELECT MAX(" + feild + ") FROM " + table + " ");
        while (rs.next()) {
            result = rs.getInt(1);
        }

        return (result + 1);

    }

    public Staff getEmployee(Staff admin) {

        String email = admin.getUserName();
        String password = admin.getPassword();

        try {

            String sql = "SELECT * From Staff  WHERE UserName=? and Password=? ";

            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.next()) {

                admin.setStaff_ID(rs.getInt("Staff_ID"));

                admin.setUserName(rs.getString("UserName"));
                admin.setFname(rs.getString("Fname"));
                admin.setLname(rs.getString("Lname"));
                admin.setEmail(rs.getString("EMAIL"));
                admin.setPhone(rs.getInt("Phone"));

                admin.setPassword(rs.getString("Password"));
                admin.setShift(rs.getString("Shift"));
                admin.setGender(rs.getString("Gender"));
                admin.setSalary(rs.getDouble("Salary"));
                admin.setDateBirth(rs.getDate("DateBirth"));

                return admin;

            } else {
                JOptionPane.showMessageDialog(null, "Login  not Successfully");

            }

        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Login "+e.getMessage());

        }
        return admin;
    }
    
     public ArrayList getUserAll() throws SQLException {
        Customer customer;
        ArrayList<Customer> customers = new ArrayList();

        String sql = "SELECT * From Customer";

        this.pst = con.prepareStatement(sql);
        rs = this.pst.executeQuery();
        while (rs.next()) {

            customer = new Customer();
            customer.setCustomer_ID(rs.getInt("Customer_ID"));

            customer.setUserName(rs.getString("UserName"));
            customer.setFname(rs.getString("Fname"));
            customer.setLname(rs.getString("Lname"));
            customer.setPhone_No(rs.getString("Phone_No"));
            customers.add(customer);
        }

        return customers;
    }
     
         //Stadiums Sql
    public int InsertItem(Items items) throws SQLException {
        int result = 0;
        int id = GetNextValue("ITEMS", "ITEM_ID");
        PreparedStatement newStock;

        String query = "INSERT INTO ITEMS (ITEM_ID, NAME,CATEGORIE_ID, PRICE,ITEMIMG,CALORIES)  VALUES (?,?,?,?,?,?)";
        newStock = con.prepareStatement(query);
        newStock.setInt(1, id);

        newStock.setString(2, items.getName());
        newStock.setInt(3, items.getCategories().getCategorieId());

        newStock.setDouble(4, items.getPrice());
        newStock.setString(5, items.getItemImg());
        newStock.setInt(6, items.getCalories());

        result = newStock.executeUpdate();

        return result;

    }

    public ArrayList SelectItems() {
        ArrayList<Items> itemses = new ArrayList();
        try {

            String sql = "SELECT i.ITEM_ID,i.\"NAME\",i.PRICE,i.ITEMIMG,i.CALORIES,c.CATEGORIE_ID,c.CATEGORIE_NAME \n"
                    + "FROM ITEMS i,categories c\n"
                    + "\n"
                    + "where i.CATEGORIE_ID=c.CATEGORIE_ID";
            this.pst = con.prepareStatement(sql);
            rs = this.pst.executeQuery();
            while (rs.next()) {
                Categories categories = new Categories(rs.getInt("CATEGORIE_ID"), rs.getString("CATEGORIE_NAME"));
                itemses.add(new Items(rs.getInt("ITEM_ID"),
                        rs.getString("NAME"), rs.getDouble("PRICE"),
                        5, categories, rs.getString("ITEMIMG"),rs.getInt("CALORIES")));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return itemses;
    }

    public ArrayList SelectItems(int categorieId) {
        ArrayList<Items> itemses = new ArrayList();
        try {

            String sql = "SELECT i.ITEM_ID,i.\"NAME\",i.PRICE,i.ITEMIMG,i.CALORIES,c.CATEGORIE_ID,c.CATEGORIE_NAME \n"
                    + "FROM ITEMS i,categories c\n"
                    + "\n"
                    + "where i.CATEGORIE_ID=c.CATEGORIE_ID and c.CATEGORIE_ID=?";
            this.pst = con.prepareStatement(sql);
            pst.setInt(1, categorieId);

            rs = this.pst.executeQuery();
            while (rs.next()) {
                Categories categories = new Categories(rs.getInt("CATEGORIE_ID"), rs.getString("CATEGORIE_NAME"));
                itemses.add(new Items(rs.getInt("ITEM_ID"),
                        rs.getString("NAME"), rs.getDouble("PRICE"),
                        5, categories, rs.getString("ITEMIMG"),rs.getInt("CALORIES")));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return itemses;
    }

    public void updateItems(int id, Items items) {
        try {
            PreparedStatement newStock;

            // Connection c = con();
            String sql = "UPDATE ITEMS set NAME=?, PRICE=?, CALORIES=?, CATEGORIE_ID =?,ITEMIMG=?"
                    + " WHERE ITEM_ID = ?";

            newStock = con.prepareStatement(sql);
            newStock.setString(1, items.getName());
            newStock.setDouble(2, items.getPrice());
            newStock.setInt(3, items.getCalories());
            newStock.setInt(4, items.getCategories().getCategorieId());
            newStock.setString(5, items.getItemImg());

            newStock.setInt(6, id);

            newStock.execute();
            JOptionPane.showMessageDialog(null, "Updated");
            // tableemp();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void deleteItems(int id) {

        try {
            PreparedStatement newStock;

            // Connection c = con();
            String sql = "DELETE FROM ITEMS WHERE ITEM_ID = ?";
            newStock = con.prepareCall(sql);
            newStock.setInt(1, id);
            newStock.execute();
            JOptionPane.showMessageDialog(null, "Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

    public ArrayList SelectCategories() {
        ArrayList<Categories> categorieses = new ArrayList();
        try {

            String sql = "SELECT * FROM categories";
            this.pst = con.prepareStatement(sql);
            rs = this.pst.executeQuery();
            while (rs.next()) {
                categorieses.add(new Categories(rs.getInt("Categorie_ID"),
                        rs.getString("Categorie_Name")));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return categorieses;
    }

}
