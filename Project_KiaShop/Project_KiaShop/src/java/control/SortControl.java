/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import dao.SortDAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
@WebServlet(name = "SortControl", urlPatterns = {"/Sort"})
public class SortControl extends HttpServlet {

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
        SortDAO dao = new SortDAO();
        List<Category> listC = dao.getAllCategory();
        request.setAttribute("listC", listC);
        List<Product> list = null;
        String price = request.getParameter("price");
        String sortOption = request.getParameter("sortOption");
        // Kiểm tra và thực hiện sắp xếp nếu có yêu cầu
        if (sortOption != null && !sortOption.isEmpty()) {
            if (sortOption.equals("AZ")) {
                list = dao.getProductSortedByNameAZ();
            } else if (sortOption.equals("ZA")) {
                list = dao.getProductSortedByNameZA();
            } else if (sortOption.equals("asc")) {
                list = dao.getProductSortedByPriceAscending();
            } else if (sortOption.equals("des")) {
                list = dao.getProductSortedByPriceDescending();
            } else if (sortOption.equals("new")) {
                list = dao.getProductSortedByNewestProduct();
            }
            request.setAttribute("listP", list);
            request.setAttribute("sortOption", sortOption);
            request.getRequestDispatcher("Shop.jsp").forward(request, response);
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
