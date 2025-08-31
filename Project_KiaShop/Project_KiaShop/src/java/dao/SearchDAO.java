/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Category;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
public class SearchDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> search(String txtSearch, String cID) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE isDeleted != 1";

        if (cID != null && !cID.trim().equals("")) {
            query += " AND cID = ?";
        }

        if (txtSearch != null && !txtSearch.trim().equals("")) {
            query += " AND pName LIKE ?";
        }

        try {
            DAO dao = new DAO();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            int paramIndex = 1;

            if (cID != null && !cID.trim().equals("")) {
                ps.setInt(paramIndex++, Integer.parseInt(cID));
            }

            if (txtSearch != null && !txtSearch.trim().equals("")) {
                ps.setString(paramIndex++, "%" + txtSearch + "%");
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where pName like ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
        }
        return list;
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

    public List<Product> searchWithPaging(String txtSearch, int pageIndex, int pageSize, String cID) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE isDeleted != 1";

        if (cID != null && !cID.trim().equals("")) {
            query += " AND cID = ?";
        }

        if (txtSearch != null && !txtSearch.trim().equals("")) {
            query += " AND pName LIKE ?";
        }

        query += " ORDER BY pID OFFSET (? * ? - ?) ROWS FETCH NEXT ? ROWS ONLY";

        try {
            DAO dao = new DAO();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            int paramIndex = 1;

            if (cID != null && !cID.trim().equals("")) {
                ps.setInt(paramIndex++, Integer.parseInt(cID));
            }

            if (txtSearch != null && !txtSearch.trim().equals("")) {
                ps.setString(paramIndex++, "%" + txtSearch + "%");
            }

            ps.setInt(paramIndex++, pageIndex);
            ps.setInt(paramIndex++, pageSize);
            ps.setInt(paramIndex++, pageSize);
            ps.setInt(paramIndex, pageSize);

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
