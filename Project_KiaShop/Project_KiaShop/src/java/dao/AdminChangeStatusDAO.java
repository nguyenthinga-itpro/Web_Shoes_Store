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
public class AdminChangeStatusDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void updateStatusOrder(String id) {
        String query = "Update [Order] set status = 'Accepted' WHERE id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
        }
    }

    public void cancelOrder(String id) {
        String query = "Update [Order] set status = 'Canceled' WHERE id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
        }
    }
}
