/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MSI GTX
 */
public class CustomerStatisticsDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int getTotalCustomerCountByDate(String selectedDate) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0 AND CAST(date AS DATE) = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, selectedDate);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalCustomerCountByMonth(String selectedMonth) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0 AND MONTH(date) = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, selectedMonth);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalCustomerCountByYear(String selectedYear) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0 AND YEAR(date) = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, selectedYear);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getNewCustomerCountByDate(String selectedDate) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0 AND CAST(date AS DATE) = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, selectedDate);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getNewCustomerCountByMonth(int year, int month) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0 AND YEAR(date) = ? AND MONTH(date) = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, year);
            ps.setInt(2, month);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getNewCustomerCountByYear(int year) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0 AND YEAR(date) = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, year);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
