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
public class OrderHistoryDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Order> getOrderHistoryByAccountID(int accID) {
        String query = "SELECT * from [Order] where accountID = ?";
        try {
            List<Order> ls = new ArrayList<>();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accID);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
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

                ls.add(order);
            }
            return ls;
        } catch (Exception e) {
        }
        return null;
    }
}
