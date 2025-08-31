/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author MSI GTX
 */
public class AdminAddProductDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Product checkExistProduct(String name, String image, String price,
            String title, String description, String cid, int amount) {
        String query = "select * from Product\n"
                + "where pName = ? and [image] = ? and price = ? and cID = ? and title = ? and [description] = ? and pAmount = ? and isDeleted != 1";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, cid);
            ps.setString(5, title);
            ps.setString(6, description);
            ps.setInt(7, amount);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void addNewProduct(String name, String image, String price,
            String title, String description, String cid, int amount) {
        String query = "INSERT into Product (pName, [image], price, title, [description], cID, pAmount, isDeleted)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, cid);
            ps.setInt(7, amount);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addNewSubImage(String pID, String Simage) {
        String query = "INSERT into SubImage (pID, SImage)\n"
                + "VALUES (?, ?)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, pID);
            ps.setString(2, Simage);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getProductIDToAdd() {
        String query = "SELECT TOP(1) pID from Product order BY pID DESC";
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

    public void addSizeAndQuantity(int productId, int sizeValue, int quantity) {
        try {
            // Tìm sizeID dựa trên sizevalue
            int sizeId = getSizeIdBySizeValue(sizeValue);

            if (sizeId != -1) {
                // Nếu tìm thấy sizeID, thêm dữ liệu vào bảng SizeDetail
                String sizeDetailQuery = "INSERT INTO SizeDetail (pID, sizeID, quantity) VALUES (?, ?, ?)";
                PreparedStatement sizeDetailPs = conn.prepareStatement(sizeDetailQuery);
                sizeDetailPs.setInt(1, productId);
                sizeDetailPs.setInt(2, sizeId);
                sizeDetailPs.setInt(3, quantity);
                sizeDetailPs.executeUpdate();

                // Đóng kết nối và tài nguyên
                sizeDetailPs.close();
            } else {
                System.out.println("Không thể tìm thấy sizeID cho sizevalue đã cho.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      private int getSizeIdBySizeValue(int sizeValue) {
        String query = "SELECT sizeID FROM Size WHERE sizevalue = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sizeValue);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("sizeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy sizeID cho sizevalue đã cho
    }
}
