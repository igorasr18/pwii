/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 2141123903
 */
public class LoginAuthentication extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginAuthentication</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginAuthentication at " + request.getContextPath() + "</h1>");
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
         PrintWriter out = response.getWriter();
    String connectionURL = "jdbc:mysql://localhost:3311/mysql";
    Connection connection=null;
    ResultSet rs;
    String usuario=new String("");
    String senha=new String("");
    response.setContentType("text/html");
    try {
        // Load the database driver
        Class.forName("com.mysql.jdbc.Driver");
        // Get a Connection to the database
        connection = DriverManager.getConnection(connectionURL, "root", "root"); 
        //Add the data into the database
        String usuarioDigitado = request.getParameter("usuario");
        String senhaDigitada = request.getParameter("senha");
        String sql = "select usuario , senha from Usuarios";
        Statement s = connection.createStatement();
        s.executeQuery (sql);
        rs = s.getResultSet();
        while (rs.next ()){
            usuario=rs.getString("usuario");
            senha=rs.getString("senha");
            if(usuario.equals(request.getParameter("usuario")) && senha.equals(request.getParameter("senha"))){
                HttpSession sessao = request.getSession();
                sessao.setAttribute("login", usuario);
                
                response.sendRedirect("PaginaInicial.jsp");
            }
        }
        response.sendRedirect("loginAgenda.jsp?msg=Nenhum usuario logado.");

        rs.close ();
        s.close ();
    }catch(Exception e){
        System.out.println("Exception is ;"+e);
    }
    
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
