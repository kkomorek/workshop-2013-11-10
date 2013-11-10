/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.softech.tutorial.servlettutorial;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ssledz
 */
@WebServlet(name = "ListAllAccounts", urlPatterns = {"/list-all-accounts"})
public class ListAllAccounts extends HttpServlet {

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

        List<User> users = (List<User>) request.getServletContext().getAttribute("users");

        User logged = (User) request.getSession().getAttribute("logedUser");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListAllAccounts</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h2>");
            if (logged != null) {
                out.println("You are logged as " + logged.getFirstName());
            } else {
                out.println("You are not logged in");
            }
            out.println("</h2>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>FirstName</th><th>LastName</th><th>Email</th>");
            out.println("</tr>");

            if (users != null) {
                for (User user : users) {
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(user.getFirstName());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(user.getLastName());
                    out.println("</td>");
                    out.println("<td>");
                    out.println(user.getEmail());
                    out.println("</td>");
                    out.println("</tr>");
                }
            }
            out.println("</table>");

            out.println("<a href='add-user-form.html'>Add User Form</a>");
            out.println("</body>");
            out.println("</html>");
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
