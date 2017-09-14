<%-- 
    Document   : update
    Created on : 14/09/2017, 19:42:14
    Author     : Igor
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(""
            + "jdbc:mysql://localhost:3311/mysql", "root", "root");
            Statement stm = con.createStatement();

            String usuario = "";
            String compromisso = "";
            String data = "";
            String hora = "";
            int id = Integer.parseInt(request.getParameter("id"));
            
            if(request.getParameter("usuario")!=null){
                usuario = request.getParameter("usuario");
                compromisso = request.getParameter("compromisso");
                data = request.getParameter("data");
                hora = request.getParameter("hora");
                String sql = "Update compromissos set compromisso='"+compromisso +"',data='" + data+"',hora='" + hora +"' where id="+ id;
                int resultado = stm.executeUpdate(sql);
                if(resultado > 0){
                    out.println("Registro alterado com sucesso");
                    response.sendRedirect("PaginaInicial.jsp");
                } else {
                    out.println("Se ferrou");
                }
            } else {
                ResultSet res = stm.executeQuery("select * from compromissos where id="+id);
                if(res.next()){
                        usuario = res.getString(2);
                        compromisso = res.getString(3);
                        data = res.getString(4);
                        hora = res.getString(5);

                } else {
                    out.println("Sem Compromissos");
                }    
            }

        %>
        
        <form action="update.jsp" method="get" name="frmCompromisso">
            <input  name="id" type="hidden" value="<%=id%>" />
            <input  name="usuario" type="hidden" value="<%=usuario%>" />
            <fieldset>
                <legend>Editar Compromisso</legend>
                <table>
                    <tr>
                        <td>Compromisso:</td>
                        <td><input name="compromisso" type="text" value="<%=compromisso%>"/></td>
                    </tr><tr>
                        <td>Data:</td>
                        <td><input name="data" type="date" value="<%=data%>"/></td>
                    </tr>
                    <tr>
                        <td>Hora:</td>
                        <td><input type="time" name="hora" value="<%=hora%>" /></td>
                    </tr>
                </table>
                <input type="submit" value="Editar" />
            </fieldset>
            
        </form>
    </body>
</html>
