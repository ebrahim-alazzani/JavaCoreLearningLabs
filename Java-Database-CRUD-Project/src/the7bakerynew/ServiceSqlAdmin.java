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
public class ServiceSqlAdmin {

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

    public Staff getAdmin(Staff admin) {

        String email = admin.getUserName();
        String password = admin.getPassword();

        try {

            String sql = "SELECT * From Staff  WHERE UserName=? and Password=? and Mng_staffID=0";

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
                admin.setDateBirth(rs.getDate("DATEBIRTH"));

                return admin;

            } else {
                JOptionPane.showMessageDialog(null, "Login  not Successfully");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Login  " + e.getMessage());

        }
        return admin;
    }

    public int InsertStaff(Staff staff) throws SQLException {
        int result = 0;

        PreparedStatement newStock;

        int id = GetNextValue("Staff", "Staff_ID");
        String query = " INSERT INTO Staff(Staff_ID,Email,Fname, Lname, Phone, Username, Password,Salary,Shift,Gender,DateBirth,MNG_STAFFID)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        newStock = con.prepareStatement(query);
        newStock.setInt(1, id);
        newStock.setString(2, staff.getEmail());
        newStock.setString(3, staff.getFname());
        newStock.setString(4, staff.getLname());
        newStock.setInt(5, staff.getPhone());
        newStock.setString(6, staff.getUserName());
        newStock.setString(7, staff.getPassword());
        newStock.setDouble(8, staff.getSalary());
        newStock.setString(9, staff.getShift());
        newStock.setString(10, staff.getGender());
        newStock.setDate(11, staff.getDateBirth());

        newStock.setInt(12, staff.getMng_staffID());
        result = newStock.executeUpdate();

        return result;

    }

    public void updateStaff(int id, Staff staff) {
        try {
            PreparedStatement newStock;

            // Connection c = con();
            String sql = "UPDATE Staff set Email=?,Fname=?, Lname=?, Phone=?, Username=?,"
                    + " Password=?,Salary=?,Shift=?,Gender=?,DateBirth=?"
                    + " WHERE Staff_ID = ?";

            newStock = con.prepareStatement(sql);
            newStock.setString(1, staff.getEmail());
            newStock.setString(2, staff.getFname());
            newStock.setString(3, staff.getLname());
            newStock.setInt(4, staff.getPhone());
            newStock.setString(5, staff.getUserName());
            newStock.setString(6, staff.getPassword());
            newStock.setDouble(7, staff.getSalary());
            newStock.setString(8, staff.getShift());
            newStock.setString(9, staff.getGender());
            newStock.setDate(10, staff.getDateBirth());

            newStock.setInt(11, id);

            newStock.execute();
            JOptionPane.showMessageDialog(null, "Updated");
            // tableemp();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public ArrayList getStaffAll() throws SQLException {
        Staff staff;
        ArrayList<Staff> staffs = new ArrayList();

        String sql = "SELECT * From Staff";

        this.pst = con.prepareStatement(sql);
        rs = this.pst.executeQuery();
        while (rs.next()) {

            staff = new Staff();
            staff.setStaff_ID(rs.getInt("Staff_ID"));

            staff.setUserName(rs.getString("UserName"));
            staff.setFname(rs.getString("Fname"));
            staff.setLname(rs.getString("Lname"));
            staff.setPhone(rs.getInt("Phone"));
            staff.setEmail(rs.getString("Email"));

            staff.setGender(rs.getString("Gender"));
            staff.setDateBirth(rs.getDate("DateBirth"));
            staff.setSalary(rs.getDouble("Salary"));
            staff.setShift(rs.getString("Shift"));
            staffs.add(staff);
        }

        return staffs;
    }

    public void deleteEmployee(int id) {

        try {
            PreparedStatement newStock;

            // Connection c = con();
            String sql = "DELETE FROM Staff WHERE Staff_ID = ?";
            newStock = con.prepareCall(sql);
            newStock.setInt(1, id);
            newStock.execute();
            JOptionPane.showMessageDialog(null, "Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }

    }

}
