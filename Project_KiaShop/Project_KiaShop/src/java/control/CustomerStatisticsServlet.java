/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.CustomerStatisticsDAO;
import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 *
 * @author Nguyen Thi Nga - CS171351
 */
@WebServlet(name = "CustomerStatisticsServlet", urlPatterns = {"/CustomerStatisticsServlet"})
public class CustomerStatisticsServlet extends HttpServlet {

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
        CustomerStatisticsDAO dao = new CustomerStatisticsDAO();
        String selectedDate = request.getParameter("selectedDate");
        LocalDate selectedLocalDate = null;

        if (selectedDate != null && !selectedDate.isEmpty()) {
            selectedLocalDate = LocalDate.parse(selectedDate);
            int currentDay = selectedLocalDate.getDayOfMonth();
            int currentMonth = selectedLocalDate.getMonthValue();
            int currentYear = selectedLocalDate.getYear();

            // Gọi các phương thức trong DAO để lấy dữ liệu cho ngày, tháng và năm hiện tại
            int totalCustomersToday = dao.getTotalCustomerCountByDate(currentYear + "-" + currentMonth + "-" + currentDay);
            int totalCustomersThisMonth = dao.getTotalCustomerCountByMonth(currentYear + "-" + currentMonth);
            int totalCustomersThisYear = dao.getTotalCustomerCountByYear(String.valueOf(currentYear));

            int newCustomersToday = dao.getNewCustomerCountByDate(currentYear + "-" + currentMonth + "-" + currentDay);
            int newCustomersThisMonth = dao.getNewCustomerCountByMonth(currentYear, currentMonth);
            int newCustomersThisYear = dao.getNewCustomerCountByYear(currentYear);

            request.setAttribute("selectedDate", selectedDate);
            request.setAttribute("totalCustomersToday", totalCustomersToday);
            request.setAttribute("totalCustomersThisMonth", totalCustomersThisMonth);
            request.setAttribute("totalCustomersThisYear", totalCustomersThisYear);
            request.setAttribute("newCustomersToday", newCustomersToday);
            request.setAttribute("newCustomersThisMonth", newCustomersThisMonth);
            request.setAttribute("newCustomersThisYear", newCustomersThisYear);
        }
        request.getRequestDispatcher("DashBord.jsp").forward(request, response);
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
