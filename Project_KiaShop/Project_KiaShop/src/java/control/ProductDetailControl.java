/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import dao.ProductDetailDAO;
import entity.Product;
import entity.SizeDetail;
import entity.SubImage;
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
@WebServlet(name = "ProductDetailControl", urlPatterns = {"/productDetail"})
public class ProductDetailControl extends HttpServlet {

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

        String pID = request.getParameter("productID");
        ProductDetailDAO dao = new ProductDetailDAO();
        Product p = dao.getProductByID(pID);
        int cid = p.getCateID();
        String cname = dao.getCnameByCID(cid + "");
        List<SizeDetail> sizes = dao.getProductSizesByProductID(Integer.parseInt(pID));
        List<SubImage> listImage = dao.getAllSubImageByPID(pID);
        String image0 = "", image1 = "", image2 = "", image3 = "";
        if (!listImage.isEmpty()) {
            if (listImage.size() >= 1) {
                image0 = listImage.get(0).getImage();
            }
            if (listImage.size() >= 2) {
                image1 = listImage.get(1).getImage();
            }
            if (listImage.size() >= 3) {
                image2 = listImage.get(2).getImage();
            }
            if (listImage.size() >= 4) {
                image3 = listImage.get(3).getImage();
            }
        }
        request.setAttribute("detail", p);
        request.setAttribute("cateName", cname);
        request.setAttribute("cid", cid);
        request.setAttribute("sizes", sizes);
        request.setAttribute("image0", image0);
        request.setAttribute("image1", image1);
        request.setAttribute("image2", image2);
        request.setAttribute("image3", image3);
        request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);
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
