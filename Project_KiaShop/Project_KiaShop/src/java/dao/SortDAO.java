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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
public class SortDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

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

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where isDeleted != 1";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
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

    public List<Product> getProductSortedByNameAZ() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getName));
        return products;
    }

    //Sắp xếp sản phẩm hàng Z-A
    public List<Product> getProductSortedByNameZA() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getName).reversed());
        return products;
    }

    // Phương thức để sắp xếp danh sách sản phẩm theo giá tăng dần
    public List<Product> getProductSortedByPriceAscending() {
        List<Product> products = getAllProduct();
        // Sử dụng phương thức sort của lớp Collections và truyền vào một Comparator
        Collections.sort(products, Comparator.comparing(Product::getPrice));
        return products;
    }

    // Phương thức để sắp xếp danh sách sản phẩm theo giá giảm dần
    public List<Product> getProductSortedByPriceDescending() {
        List<Product> products = getAllProduct();
        // Sử dụng phương thức sort của lớp Collections và truyền vào một Comparator
        Collections.sort(products, Comparator.comparing(Product::getPrice).reversed());
        return products;
    }

    // Phương thức để sắp xếp danh sách sản phẩm theo giá giảm dần
    public List<Product> getProductSortedByNewestProduct() {
        List<Product> products = getAllProduct();
        // Sử dụng phương thức sort của lớp Collections và truyền vào một Comparator
        Collections.sort(products, Comparator.comparing(Product::getId).reversed());
        return products;
    }
}
