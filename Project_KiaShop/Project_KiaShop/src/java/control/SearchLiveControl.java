/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.google.gson.JsonObject;
import dao.DAO;
import dao.SearchDAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SearchLiveControl", urlPatterns = {"/search"})
public class SearchLiveControl extends HttpServlet {

    public int getPageSize(int numberProduct, int allProduct) {
        int pageSize = allProduct / numberProduct;
        if (allProduct % numberProduct != 0) {
            pageSize = (allProduct / numberProduct) + 1;
        }
        return pageSize;

    }

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

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String sort = request.getParameter("sort");// huy
        sort = (sort == null || sort.equals("")) ? "asc" : sort;// huy
        String txtSearch = request.getParameter("txt");
        String cID = request.getParameter("cID");

        int pageSize = getPageSize(6, new SearchDAO().search(txtSearch, cID).size());
        String index = request.getParameter("pageIndex");
        int pageIndex = 0;
        if (index == null) {
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(index);
        }
        List<Product> list = new SearchDAO().searchWithPaging(txtSearch, pageIndex, 6, cID); // huyyyy

        PrintWriter out = response.getWriter();
        String product = "";
        for (Product o : list) {
            product += "     <div class=\"col-12 col-sm-6 col-md-12 col-xl-6\">\n"
                    + "                            <div class=\"single-product-wrapper\">\n"
                    + "                                <!-- Product Image -->\n"
                    + "                                <div class=\"product-img\">\n"
                    + "                                    <img src=\"" + o.getImage() + "\" alt=\"\">\n"
                    + "\n"
                    + "                                </div>\n"
                    + "\n"
                    + "                                <!-- Product Description -->\n"
                    + "                                <div class=\"product-description d-flex align-items-center justify-content-between\">\n"
                    + "                                    <!-- Product Meta Data -->\n"
                    + "                                    <div class=\"product-meta-data\">\n"
                    + "                                        <div class=\"line\"></div>\n"
                    + "                                        <p class=\"product-price\">$" + o.getPrice() + "</p>\n"
                    + "                                        <a href=\"productDetail?productID=" + o.getId() + "\">\n"
                    + "                                            <h6>" + o.getName() + "</h6>\n"
                    + "                                        </a>\n"
                    + "                                    </div>\n"
                    + "                                    <!-- Ratings & Cart -->\n"
                    + "                                    <div class=\"ratings-cart text-right\">\n"
                    + "                                        <div class=\"ratings\">\n"
                    + "                                            <i class=\"fa fa-star\" aria-hidden=\"true\"></i>\n"
                    + "                                            <i class=\"fa fa-star\" aria-hidden=\"true\"></i>\n"
                    + "                                            <i class=\"fa fa-star\" aria-hidden=\"true\"></i>\n"
                    + "                                            <i class=\"fa fa-star\" aria-hidden=\"true\"></i>\n"
                    + "                                            <i class=\"fa fa-star\" aria-hidden=\"true\"></i>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"cart\">\n";
            if (o.getAmount() != 0) {
                product
                        += "                                                <a href=\"cart?id=" + o.getId() + "&action=add\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Add to Cart\"><img src=\"img/core-img/cart.png\" alt=\"\"></a>\n"
                        + "                                                <a href=\"Shop.jsp\" style='font-size:16px;' title=\"Add to favourite\">&#129505;</a>\n";
            } else {

                product += "                                                <a href=\"ShopControl\" data-toggle=\"tooltip\" data-placement=\"left\">Sold out</a>\n"
                        + "                                                <a href=\"FavouriteControl\" style='font-size:16px;' title=\"Add to favourite\">&#129505;</a>\n";
            }
            product
                    += "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>";
        }

        String strPaging = "";
        String active = "";
        for (int i = 1; i <= pageSize; i++) {
            if (pageIndex == i) {
                active = "active";
            } else {
                active = "";
            }
            strPaging += "<li class=\"page-item " + active + "\"><a\r\n"
                    + "									class=\"page-link\"\r\n"
                    + "									href=\"ShopControl?pageIndex=" + i + "&txtSearch=" + txtSearch + "\">" + i + "</a></li>";
        }

        String categoryMenu = "";
        List<Category> lsNewsType = new SearchDAO().getAllCategory();
        int id = (cID == null || cID.trim().equals("")) ? lsNewsType.get(0).getId() : Integer.parseInt(cID);
        for (Category newsType : lsNewsType) {
            if (newsType.getId() == id) {
                active = "active";
            } else {
                active = "";
            }
            categoryMenu += "<li class=\"" + active + "\"><a href=\"ShopControl?pageIndex=1&txtSearch=" + txtSearch + "&cID=" + newsType.getId() + "\">" + newsType.getName() + "</a></li>";
        }
        /* construct your json */
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("product", product);
        jsonResponse.addProperty("paging", strPaging);
        jsonResponse.addProperty("categoryMenu", categoryMenu);
        /* send to the client the JSON string */
        response.getWriter().write(jsonResponse.toString());
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
