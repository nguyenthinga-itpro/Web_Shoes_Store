/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
public class OrderDetailDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<OrderDetails> getOrderDetailByOrderID(int orderId) {
        String query = "SELECT od.*, p.image, p.pName, c.cName, s.sizevalue from OrderDetails od\n"
                + "left join Product p on od.ProductID = p.pID\n"
                + "left join Category c on p.cID = c.cID\n"
                + "left join Size s on od.sizeID = s.sizeID "
                + "where OrderID = ?";
        try {
            List<OrderDetails> ls = new ArrayList<>();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                ls.add(new OrderDetails(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10)));
            }
            return ls;
        } catch (Exception e) {
        }
        return null;
    }
}
