/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.OrderDetails;
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
public class ManagerDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

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

    public List<OrderDetails> getAllOrderDetails() {
        List<OrderDetails> list = new ArrayList<>();
        String query = "SELECT * FROM OrderDetails WHERE Quantity > 0"; // Thêm khoảng trắng sau Quantity
        try {
            // Mở kết nối tới cơ sở dữ liệu
            conn = new DBContext().getConnection();
            // Chuẩn bị câu lệnh SQL
            ps = conn.prepareStatement(query);
            // Thực thi câu lệnh truy vấn
            rs = ps.executeQuery();
            // Duyệt qua kết quả trả về
            while (rs.next()) {
                // Tạo đối tượng OrderDetails từ kết quả truy vấn và thêm vào danh sách
                list.add(new OrderDetails(
                        rs.getInt(1), // Cột 1
                        rs.getInt(2), // Cột 2
                        rs.getDouble(3), // Cột 3
                        rs.getInt(4) // Cột 4
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int calculateTotalStockQuantity(List<Product> products) {
        int totalQuantity = 0;
        for (Product product : products) {
            totalQuantity += product.getAmount();
        }
        return totalQuantity;
    }

    public int countTheNumberSold(List<OrderDetails> orderDetailsList) {
        int totalTheNumberSold = 0;
        for (OrderDetails orderDetail : orderDetailsList) {
            totalTheNumberSold += orderDetail.getQuantity();
        }
        return totalTheNumberSold;
    }

    public double extortionOfAllProducts(List<Product> products) {
        double totalPrice = 0;
        if (products != null) {
            for (Product product : products) {
                if (product.getAmount() > 0 && product.getPrice() > 0) {
                    totalPrice += product.getAmount() * product.getPrice();
                }
            }
        }
        return totalPrice;
    }

    public List<Product> getProductsSortedByNameAZ() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getName));
        return products;
    }

    //Sắp xếp khách hàng Z-A
    public List<Product> getProductsSortedByNameZA() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getName).reversed());
        return products;
    }

    public List<Product> getNewProducts() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getId).reversed());
        return products;
    }
    public List<Product> getOldProducts() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getId));
        return products;
    }
}
