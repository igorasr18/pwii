<%-- 
    Document   : logout
    Created on : 15/09/2017, 21:23:02
    Author     : Lucas Carvalho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <%  
            session.setAttribute("login", null);
            session.setAttribute("lgn", null);
            response.sendRedirect("loginAgenda.jsp");
        %> 
    </body>
</html>
