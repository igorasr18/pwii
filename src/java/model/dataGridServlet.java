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
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
public class dataGridServlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        String actionValue = "";
        String compromissoSelecionado = "";
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {
            compromissoSelecionado = (String) en.nextElement();            
            actionValue = request.getParameter(compromissoSelecionado);  
        }
        if(compromissoSelecionado.trim().equalsIgnoreCase("")){
            request.setAttribute("errorMessage","Cannot find user id");
            request.getRequestDispatcher("/datagrid.jsp").forward(request, response);
        }else{            
            if(actionValue.contains("Remove")){    
                compromissosServices compromissoServis = new compromissosServices();
                if(compromissoServis.deleteCompromisso(compromissoSelecionado)){
                    request.setAttribute("successMessage", "Successfully delete user: <b>" + compromissoSelecionado + "</b>");
                    request.getRequestDispatcher("./PaginaInicial.jsp").forward(request, response);
                }else{
                    request.setAttribute("errorMessage", "Failed to delete user: <b>" + compromissoSelecionado + "</b>, please try again");                    
                   request.getRequestDispatcher("./PaginaInicial.jsp").forward(request, response);
                }                
            }else{
                //process redirect to modify user page with query string user id
                //redirect to modify page
           }
        }
    }
}