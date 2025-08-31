/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Product;
import entity.Size;
import entity.SizeDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GTX
 */
public class CartDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Size getSizeById(int sizeId) {

        String query = "SELECT *"
                + "FROM Size s "
                + "WHERE s.sizeID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, sizeId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    Size size = new Size(rs.getInt(1), rs.getInt(2));
                    return size;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public SizeDetail getProductSizesByProductIDAndSizeID(int productID, int sizeId) {

        String query = "SELECT sd.*, s.sizevalue\n"
                + "FROM SizeDetail sd\n"
                + "JOIN Size s ON sd.sizeID = s.sizeID\n"
                + "WHERE sd.pID = ? and s.sizeID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productID);
            ps.setInt(2, sizeId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int pID = rs.getInt("pID");
                    int sizeID = rs.getInt("sizeID");
                    int quantity = rs.getInt("quantity");
                    int sizeValue = rs.getInt("sizevalue");
                    SizeDetail size = new SizeDetail(sizeID, pID, quantity, sizeValue);
                    return size;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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
}
