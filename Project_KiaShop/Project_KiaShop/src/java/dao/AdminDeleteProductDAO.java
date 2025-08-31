/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MSI GTX
 */
public class AdminDeleteProductDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void deleteSubImage(String pid) throws Exception {//edit param
        //edit query (my_table), number of param
        String query = "delete from SubImage\n"
                + "where pID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteProduct(String pid) throws Exception {//edit param
        //edit query (my_table), number of param
        String query = "Update Product\n"
                + "set isDeleted = 1 WHERE pID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }
}
