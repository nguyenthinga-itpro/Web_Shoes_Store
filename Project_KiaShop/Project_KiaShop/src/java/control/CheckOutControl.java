/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.CheckOutDAO;
import dao.DAO;
import entity.Account;
import entity.Cart;
import entity.Product;
import entity.SizeDetail;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutControl", urlPatterns = {"/CheckOut"})
public class CheckOutControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            CheckOutDAO dao = new CheckOutDAO();
            HttpSession session = request.getSession();

            Account a = (Account) session.getAttribute("acc");
            // get Information from form
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

            Cart c = (Cart) session.getAttribute("cart");
            double totalPrice = 0;
            if (c != null) {
                totalPrice = c.getAmount();
            }

            if (totalPrice == 0.0) {
                response.sendRedirect("ShopControl");
                return;
            }
            // get Date have
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date dateInsert = java.sql.Date.valueOf(dateFormat.format(date));

            dao.insertOrder(dateInsert + "", a.getId(), address, phone, firstname + " " + lastname, totalPrice, "Waiting");

            int orderID = dao.getOrderID();

            for (Product product : c.getItems()) {
                dao.insertOrderDetails(orderID, product.getId(), product.getPrice(), product.getNumberInCart(), product.getSizeInCart().getSizeID());
            }

            for (Product product : c.getItems()) {
                int reduceAmount = product.getAmount() - product.getNumberInCart();
                dao.updateAmounProduct(reduceAmount, product.getId());
                SizeDetail sd = dao.getSizeDetail(product.getSizeInCart().getSizeID(), product.getId());
                dao.updateQuantitySize(product.getSizeInCart().getSizeID(), product.getId(), sd.getQuantity() - product.getNumberInCart());
            }

            session.removeAttribute("cart");
            request.setAttribute("message", "Order successfull!");
            request.getRequestDispatcher("CheckOut.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "Order fail!");
            request.getRequestDispatcher("CheckOut.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
