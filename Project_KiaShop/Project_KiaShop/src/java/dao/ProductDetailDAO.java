/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Product;
import entity.SizeDetail;
import entity.SubImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
public class ProductDetailDAO {

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

    public String getCnameByCID(String cid) {
        String query = "select distinct cName from Product P, Category C\n"
                + "where P.cID = C.cID and P.cID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, cid);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<SizeDetail> getProductSizesByProductID(int productID) {
        List<SizeDetail> sizes = new ArrayList<>();
        String query = "SELECT sd.*, s.sizevalue\n"
                + "FROM SizeDetail sd\n"
                + "JOIN Size s ON sd.sizeID = s.sizeID\n"
                + "WHERE sd.pID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productID);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int pID = rs.getInt("pID");
                    int sizeID = rs.getInt("sizeID");
                    int quantity = rs.getInt("quantity");
                    int sizeValue = rs.getInt("sizevalue");
                    SizeDetail size = new SizeDetail(sizeID, pID, quantity, sizeValue);
                    sizes.add(size);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizes;
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
