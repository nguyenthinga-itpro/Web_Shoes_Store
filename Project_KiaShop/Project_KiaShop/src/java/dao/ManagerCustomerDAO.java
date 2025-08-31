/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GTX
 */
public class ManagerCustomerDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int getTotalCustomerCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getNewCustomerCountToday(Date date) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0 AND CAST(date AS DATE) = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
            ps.setDate(1, sqlDate);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public List<Account> searchCustomersByPhoneNumber(String txtSearch) {
        List<Account> customers = new ArrayList<>();
        String query = "SELECT * FROM Account WHERE phone LIKE ? and role = 0";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account customer = new Account();
                customer.setId(rs.getInt("uID"));
                customer.setFullname(rs.getString("fullname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public List<Account> searchCustomersByName(String txtSearch) {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM Account WHERE LOWER(fullname) LIKE LOWER(?) AND role = 0";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account customer = new Account();
                customer.setId(rs.getInt("uID"));
                customer.setFullname(rs.getString("fullname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                list.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Account> getAllCustomer() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account where role = 0";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("uID"));
                account.setFullname(rs.getString("fullname"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setAddress(rs.getString("address"));
                list.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
        }

        return list;
    }

    public List<Account> getCustomersSortedByNameAZ() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getFullname));
        return customers;
    }

    //Sắp xếp khách hàng Z-A
    public List<Account> getCustomersSortedByNameZA() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getFullname).reversed());
        return customers;
    }

    public List<Account> getNewCustomers() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getId).reversed());
        return customers;
    }
    public List<Account> getOldCustomers() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getId));
        return customers;
    }
}
