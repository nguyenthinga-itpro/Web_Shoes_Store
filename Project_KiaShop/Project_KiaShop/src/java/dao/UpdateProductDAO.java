/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Category;
import entity.Product;
import entity.SubImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
public class UpdateProductDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Product getProductByID(String id) {
        String query = "select * from Product where pID = ?";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, id);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
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

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public void updateSubImage(String pID, String sImage, String sId) {
        String query = "UPDATE SubImage set pID = ?, SImage = ? where subImageID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, pID);
            ps.setString(2, sImage);
            ps.setString(3, sId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateProduct(String name, String image, String price,
            String title, String description, String cid, int amount, int pID) {
        String query = "UPDATE Product set pName = ?, image = ?, price = ?, title = ?, description = ?, cID = ?, pAmount = ? where pID = ?";
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
            ps.setInt(8, pID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateSizeAndQuantity(int productId, int sizeValue, int quantity) {
        try {
            // Tìm sizeID dựa trên sizevalue
            int sizeId = getSizeIdBySizeValue(sizeValue);

            if (sizeId != -1) {
                // Nếu tìm thấy sizeID, cập nhật dữ liệu trong bảng SizeDetail
                String sizeDetailUpdateQuery = "UPDATE SizeDetail SET quantity = ? WHERE pID = ? AND sizeID = ?";
                PreparedStatement sizeDetailUpdatePs = conn.prepareStatement(sizeDetailUpdateQuery);
                sizeDetailUpdatePs.setInt(1, quantity);
                sizeDetailUpdatePs.setInt(2, productId);
                sizeDetailUpdatePs.setInt(3, sizeId);
                sizeDetailUpdatePs.executeUpdate();

                // Đóng kết nối và tài nguyên
                sizeDetailUpdatePs.close();
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

    public List<SubImage> getAllSubImageByPID(String cid) {
        List<SubImage> list = new ArrayList<>();
        String query = "select S.*\n"
                + "from Product P, SubImage S\n"
                + "where P.pID = S.pID and S.pID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SubImage(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
