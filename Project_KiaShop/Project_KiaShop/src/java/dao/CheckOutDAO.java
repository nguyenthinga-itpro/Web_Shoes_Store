/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.SizeDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MSI GTX
 */
public class CheckOutDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insertOrder(String date, int accountID, String address, String sdt, String name, double total, String status) {
        String query = "INSERT INTO [Order](orderDate, accountID,addressReceive,sdt,name,totalPrice,status)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, date);
            //ps.setDate(1, (java.sql.Date) date);
            ps.setInt(2, accountID);
            ps.setString(3, address);
            ps.setString(4, sdt);
            ps.setString(5, name);
            ps.setDouble(6, total);
            ps.setString(7, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getOrderID() {
        String query = "SELECT TOP(1) id from [Order] order BY id DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void insertOrderDetails(int orderID, int productID, double price, int amount, int sizeId) {
        String query = "INSERT INTO OrderDetails VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderID);
            ps.setInt(2, productID);
            ps.setDouble(3, price);
            ps.setInt(4, amount);
            ps.setInt(5, sizeId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAmounProduct(int amount, int productID) {
        String query = "UPDATE Product SET pAmount = ? WHERE pID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            ps.setInt(2, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public SizeDetail getSizeDetail(int sizeId, int pID) {
        String query = "select * from SizeDetail where sizeID = ? and pID = ?";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setInt(1, sizeId);
            ps.setInt(2, pID);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new SizeDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }

        } catch (Exception e) {
        }
        return null;
    }

    public void updateQuantitySize(int sizeId, int productID, int quantity) {
        String query = "UPDATE SizeDetail SET quantity = ? WHERE pID = ? and sizeID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2, productID);
            ps.setInt(3, sizeId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
