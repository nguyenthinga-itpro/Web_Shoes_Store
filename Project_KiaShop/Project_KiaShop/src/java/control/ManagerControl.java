/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import dao.ManagerDAO;
import entity.OrderDetails;
import entity.Product;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ManagerControl extends HttpServlet {

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

//        ManagerDAO dao = new ManagerDAO();
//        List<Product> listP = dao.getAllProduct();
//        List<OrderDetails> orderDetailsList = dao.getAllOrderDetails();
//        int numberProduct = listP.size();
//        int calculateTotalStockQuantity = dao.calculateTotalStockQuantity(listP);
//        int countTheNumberSold = dao.countTheNumberSold(orderDetailsList);
//        double totalPrice = dao.extortionOfAllProducts(listP);
//        request.setAttribute("listP", listP);
//        request.setAttribute("numberP", numberProduct);
//        request.setAttribute("calculateTotalStockQuantity", calculateTotalStockQuantity);
//        request.setAttribute("countTheNumberSold", countTheNumberSold);
//        request.setAttribute("totalPrice", totalPrice);
//        request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
        String sortOption = request.getParameter("sortOption");
        ManagerDAO dao = new ManagerDAO();
        List<Product> listP = dao.getAllProduct();
        List<Product> products = dao.getAllProduct();
        List<OrderDetails> orderDetailsList = dao.getAllOrderDetails();
        int numberProduct = listP.size();
        int calculateTotalStockQuantity = dao.calculateTotalStockQuantity(listP);
        int countTheNumberSold = dao.countTheNumberSold(orderDetailsList);
        double totalPrice = dao.extortionOfAllProducts(listP);

        // Kiểm tra và thực hiện sắp xếp nếu có yêu cầu
        if (sortOption != null && !sortOption.isEmpty()) {
            if (sortOption.equals("AZ")) {
                products = dao.getProductsSortedByNameAZ();
            } else if (sortOption.equals("ZA")) {
                products = dao.getProductsSortedByNameZA();
            } else if (sortOption.equals("createdAt")) {
                products = dao.getNewProducts();
            } else if (sortOption.equals("createdAl")) {
                products = dao.getOldProducts();
            }
        }

        request.setAttribute("listP", products);
        request.setAttribute("numberP", numberProduct);
        request.setAttribute("calculateTotalStockQuantity", calculateTotalStockQuantity);
        request.setAttribute("countTheNumberSold", countTheNumberSold);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("sortOption", sortOption);
        request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);

    }

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
