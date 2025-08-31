/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
public class ManagerOrderDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String query = "select * from [Project_KiAShop5].[dbo].[Order]";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getString(9)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public double getTotalEarnings() {
        double total = 0;
        String query = "select SUM(totalPrice) from [Order] WHERE status = 'Accepted'";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve

            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return total;
    }

    public int getTotalOrderCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM [Order]";
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

    public int getCountWaitingOrders() {
        int count = 0;
        String query = "SELECT count(*) FROM [Project_KiAShop5].[dbo].[Order] where status ='Waiting'";
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

    public int getCountAcceptedOrders() {
        int count = 0;
        String query = "SELECT count(*) FROM [Project_KiAShop5].[dbo].[Order] where status ='Accepted'";
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

    public List<Order> searchOrderByPhoneNumber(String txtSearch) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM [Project_KiAShop5].[dbo].[Order] WHERE sdt LIKE ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setDate(rs.getDate("orderDate"));
                order.setAccountID(rs.getInt("accountID"));
                order.setAddress(rs.getString("addressReceive"));
                order.setSdt(rs.getString("sdt"));
                order.setName(rs.getString("name"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> searchOrderByName(String txtSearch) {
        List<Order> list = new ArrayList<>();
        String query = "SELECT * FROM [Project_KiAShop5].[dbo].[Order] WHERE LOWER(name) LIKE LOWER(?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setDate(rs.getDate("orderDate"));
                order.setAccountID(rs.getInt("accountID"));
                order.setAddress(rs.getString("addressReceive"));
                order.setSdt(rs.getString("sdt"));
                order.setName(rs.getString("name"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setStatus(rs.getString("status"));

                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getAllWaitingOrders() {
        List<Order> list = new ArrayList<>();
        String query = "select * from [Project_KiAShop5].[dbo].[Order] where status ='Waiting'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Order> getAllAcceptedOrders() {
        List<Order> list = new ArrayList<>();
        String query = "select * from [Project_KiAShop5].[dbo].[Order] where status ='Accepted'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
