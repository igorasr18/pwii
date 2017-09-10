<%@page import="model.dataGridServlet"%>
<%@page import="model.compromissoItem"%>
<%@page import="model.compromissosServices"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : PaginaInicial
    Created on : 08/09/2017, 20:54:15
    Author     : 2141123903
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <script type="text/javascript">
 
        function ConfirmOnDelete(item) {
            if (confirm("Are you sure to delete " + item + "?") === true)
                return true;
            else
                return false;
       }
    </script>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de Compromissos</title>
    </head>
    
    <body>
        <h1>Agenda de Compromissos</h1>
        <%if(session.getAttribute("login")!=null){%>
            <%
            %>
        <%} else{
            response.sendRedirect("loginAgenda.jsp?msg='Efetue o login'");
        }%>
        <%
                
            String usuario = session.getAttribute("login").toString();
            request.setAttribute("lgn", session.getAttribute("login"));
                
            %>
        
        <form name="loginfrm" action="./dataGridServlet" method="POST" >
            
            <table>
                <tr>
                    <td>Usuario:<td>
                        
                        <input type="hidden" name="usuario" value="${requestScope.usuario}">    
                    <td><input type="hidden" name="insert" value="Inserir"</td>
                    
                    <td>Compromisso: </td>
                    <td><input type="text" name="compromisso" value="" /> </td>
                    <td>Data: </td>
                    <td><input type="text" name="data" value="" /></td>
                    <td>Hora: </td>
                    <td><input type="text" name="hora" value="" /></td>
                </tr>
            </table>
            <input type="submit" value="Inserir">
            <!--<input type="submit" value="Inserir" onclick="compromissoServices/insertCompromisso(lgn, compromisso, data, hora)"/>-->
        </form>
        
        <fieldset>
            <legend>Lista de Compromissos</legend>
            ${errorMessage}
            ${successMessage}
             
 <%
 out.println(session.getAttribute("login"));
 %>
            <div>
                <form action="<c:url value="./dataGridServlet" />" method="POST">
                      <%
                          int limitStart = 0;
                          int limitMax = 15;
                          int pageSize = 15;
                          int allUserCount = 0;
                          int pageIndex = 0; //start page from index 0
                          String s = session.getAttribute("login").toString();
                          session = request.getSession(false);
                          session.getAttribute("login").toString();
                          
                          dataGridServlet d = new dataGridServlet();
                          d.setUsu(s);
                          out.print(s);
                          compromissosServices compromissoServ = new compromissosServices();
                         
                          List<compromissoItem> CompromissosList = new ArrayList<compromissoItem>();                          
                          CompromissosList = compromissoServ.getCompromissos(s);                         
 
 
                          String tableCompromissos = "<table class=\"mainTable\" cellspacing=\"0\" rules=\"all\" id=\"MainContent_GridView1\" style=\"border-color:Gray;border-width:1px;border-style:Solid;width:95%;border-collapse:collapse;\">";
                          tableCompromissos += "<tr style=\"color:White;background-color:#6699CC;font-weight:bold; padding:4px;\">";
                          tableCompromissos += "<th scope=\"col\">ID.</th>";
                          tableCompromissos += "<th scope=\"col\">Usuario</th>";
                          tableCompromissos += "<th scope=\"col\">Compromisso</th>";
                          tableCompromissos += "<th scope=\"col\">Data</th>";
                          tableCompromissos += "<th scope=\"col\">Hora</th>";
                          tableCompromissos += "<th scope=\"col\">&nbsp;</th>";
                          tableCompromissos += "<th scope=\"col\">&nbsp;</th></tr>";
 
                          int numberRecord = pageIndex * pageSize;
                          int balance = allUserCount - numberRecord;
                          int startRekodToShow = numberRecord + 1;
                          int index = startRekodToShow;

                          for (compromissoItem ci : CompromissosList) {
                              tableCompromissos += "<tr style=\"border-color:Gray;border-width:1px;border-style:Solid;\">";
                              tableCompromissos += "<td style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:20px;padding:4px;\">";
                              tableCompromissos += ci.getId();
                              tableCompromissos += "</td>";
                              tableCompromissos += "<td style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:100px;padding:4px;\">";
                              tableCompromissos += ci.getUsuario();
                              tableCompromissos += "</td>";
                              tableCompromissos += "<td style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:80px;padding:4px;\">";
                              tableCompromissos += ci.getCompromisso();
                              tableCompromissos += "</td>";
                              tableCompromissos += "<td style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:80px;padding:4px;\">";
                              tableCompromissos += ci.getData();
                              tableCompromissos += "</td>";
                              tableCompromissos += "<td style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:80px;padding:4px;\">";
                              tableCompromissos += ci.getHora();
                              tableCompromissos += "</td>";
                              tableCompromissos += "<td style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:50px;padding:4px;\">";
                              tableCompromissos += "<input type=\"submit\" class=\"buttonLikeLink\" name=\"" + ci.getId() + "\" onclick=\"return ConfirmOnDelete('" + ci.getId() + "');\" value=\"Deletar\" />";
                              tableCompromissos += "</td>";
                              tableCompromissos += "<td style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:50px;padding:4px;\">";
                              tableCompromissos += "<input type=\"submit\" class=\"buttonLikeLink\" name=\"" + ci.getId() + "\" value=\"Editar\" ></input>";
                              tableCompromissos += "</td>";
                              tableCompromissos += "</tr>";
                              //index++;
                          }
                          tableCompromissos += "</table>";
                          out.print(tableCompromissos);
                      %>
            </form>
        </div>
    </fieldset>

    </body>
    
  
</html>
