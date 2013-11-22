/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.softech.tutorial.servlettutorial;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author ssledz
 */
@WebServlet(name = "AddAccountServlet", urlPatterns = {"/add-account"})
public class AddAccountServlet extends HttpServlet {

  @Resource(mappedName = "jdbc/usersDatasource")
  private DataSource ds;

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

    User user = new User();
    user.setFirstName(request.getParameter("firstName"));
    user.setLastName(request.getParameter("lastName"));
    user.setEmail(request.getParameter("email"));
    user.setLogin(request.getParameter("login"));
    user.setPassword(request.getParameter("password"));

    List<User> users = (List<User>) request.getServletContext().getAttribute("users");

    if (users == null) {
      users = new LinkedList<>();
      request.getServletContext().setAttribute("users", users);
    }

    users.add(user);
    
    try(Connection conn = ds.getConnection()) {
      
      PreparedStatement pstm = conn.prepareStatement("insert into USERS(first_name, last_name, email, login, password) values (?, ?, ?, ?, ?)");
      pstm.setString(1, user.getFirstName());
      pstm.setString(2, user.getLastName());
      pstm.setString(3, user.getEmail());
      pstm.setString(4, user.getLogin());
      pstm.setString(5, user.getPassword());
      
      pstm.executeUpdate();
      
      
    } catch (SQLException ex) {
      Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet AddAccountServlet</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet AddAccountServlet at " + request.getContextPath() + "</h1>");
      out.println("<a href='add-user-form.html'>Add User Form</a>");
      out.println("<a href='list-all-accounts'>List All User</a>");
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
