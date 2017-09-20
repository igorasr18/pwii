<%-- 
    Document   : loginAgenda
    Created on : 08/09/2017, 09:35:14
    Author     : Igor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"  language="java"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda</title>
    </head>
    <body style="background-color:#81DAF5;">
    <center>
        
        <%
//                session.setAttribute("login", "off");
//                session.invalidate();
            %>
        
        <h1>Agenda</h1>
        <form name="loginfrm" action="./LoginAuthentication" method="POST" >
            <table>
                <tr>
                    <td>Usuário: </td>
                    <td><input type="text" name="usuario" value="" /> </td>
                </tr>
                <tr>
                    <td>Senha: </td>
                    <td><input type="password" name="senha" value="" /></td>
                </tr>
            </table>
            <input type="submit" value="Entrar" />
        </form>
        
        <%if(request.getParameter("msg")!=null){%>
        <h4 style="color: red"><%=request.getParameter("msg")%></h4>
        <%} else{
           // Não faz nada 
        }%>
    </center>

    </body>
</html>
