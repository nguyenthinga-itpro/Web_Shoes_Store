/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import dao.ShopDAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShopControl", urlPatterns = {"/ShopControl"})
public class ShopControl extends HttpServlet {

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
        ShopDAO dao = new ShopDAO();
        try {
            List<Category> listC = dao.getAllCategory();
            request.setAttribute("listC", listC);
            String currentCID = request.getParameter("cID");

            // Kiểm tra nếu cID không được truyền từ request, sẽ hiển thị tất cả sản phẩm
            boolean showAllProducts = (currentCID == null);
            request.setAttribute("showAllProducts", showAllProducts);
            request.setAttribute("cID", currentCID);

            int pageIndex = (request.getParameter("pageIndex") != null) ? Integer.parseInt(request.getParameter("pageIndex")) : 1;
            int numberProductPerPage = (request.getParameter("numberProductPerPage") != null) ? Integer.parseInt(request.getParameter("numberProductPerPage")) : 4;
            int totalPage;
            List<Product> paginatedProducts;

            if (showAllProducts) {
                // Hiển thị tất cả sản phẩm
                List<Product> allProducts = dao.getAllProduct();
                int pageSize = dao.getPageSize(numberProductPerPage, allProducts.size());
                paginatedProducts = dao.paginateList(allProducts, pageIndex, numberProductPerPage);
                totalPage = pageSize;
            } else {
                // Hiển thị danh sách sản phẩm cho danh mục được chọn
                List<Product> ls = dao.getProductByCid(currentCID);
                int totalProducts = ls.size();
                totalPage = (int) Math.ceil((double) totalProducts / numberProductPerPage);
                int start = (pageIndex - 1) * numberProductPerPage;
                int end = Math.min(start + numberProductPerPage, totalProducts);
                paginatedProducts = ls.subList(start, end);
            }

            // Chuyển danh sách sản phẩm đến JSP
            request.setAttribute("listP", paginatedProducts);
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("numberProductPerPage", numberProductPerPage);
            request.setAttribute("tag", currentCID);

            request.getRequestDispatcher("Shop.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
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
