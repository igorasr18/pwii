/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Igor
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
public class dataGridServlet extends HttpServlet {
    
    String usu = "";
        public void setUsu(String usu){
            this.usu = usu;
        }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String actionValue = "";
        String compromissoSelecionado = "";
        
        String action = request.getParameter("insert");
        
        //String compromisso = "";
        
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {
            compromissoSelecionado = (String) en.nextElement();            
            actionValue = request.getParameter(compromissoSelecionado);  
        }
        if(compromissoSelecionado.trim().equalsIgnoreCase("")){
            request.setAttribute("errorMessage","Cannot find user id");
            request.getRequestDispatcher("/PaginaInicial.jsp").forward(request, response);
        }else{            
            if(actionValue.contains("Deletar")){    
                compromissosServices compromissoServis = new compromissosServices();
                try {
                    if(compromissoServis.deleteCompromisso(compromissoSelecionado)){
                        request.setAttribute("successMessage", "Successfully delete user: <b>" + compromissoSelecionado + "</b>");
                        request.getRequestDispatcher("/PaginaInicial.jsp").forward(request, response);
                    }else{
                        request.setAttribute("errorMessage", "Failed to delete user: <b>" + compromissoSelecionado + "</b>, please try again");
                        request.getRequestDispatcher("/PaginaInicial.jsp").forward(request, response);                
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(dataGridServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if(action.contains("Inserir")){
                compromissosServices compromissoServis2 = new compromissosServices();
                //String usuario = request.getAttribute("lgn").toString();
                
                HttpSession sessao = request.getSession();
                sessao.setAttribute("login", usu);
                
                String usuario = request.getParameter("usuario");
                String compromisso = request.getParameter("compromisso");
                String data = request.getParameter("data");
                String hora = request.getParameter("hora");
                try {
                    compromissoServis2.insertCompromisso(usu, compromisso, data, hora);
                    //request.getRequestDispatcher("/PaginaInicial.jsp").forward(request, response);
                    response.sendRedirect("./PaginaInicial.jsp");
                } catch (SQLException ex) {
                    //request.getRequestDispatcher("/PaginaInicial.jsp").forward(request, response);    
                    Logger.getLogger(dataGridServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}