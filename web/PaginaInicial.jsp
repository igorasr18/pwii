<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : PaginaInicial
    Created on : 08/09/2017, 20:54:15
    Author     : 2141123903
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            out.println(request.getParameter("usuario"));
        %>

    </body>
    <asp:GridView ID="gvListarCompromissos" Runat="server" 
          DataSourceID="compromissosDataSource" select
            DataKeyNames="Compromissos" AutoGenerateColumns="False">
            <Columns>
                <asp:BoundField ReadOnly="True" HeaderText="Usuario" 
                  InsertVisible="False" DataField="usuario"
                    SortExpression="usuario">
                    <ItemStyle HorizontalAlign="Center"></ItemStyle>
                </asp:BoundField>
                <asp:BoundField HeaderText="Compromisso" 
                 DataField="Compromisso" 
                 SortExpression="Compromisso">
                </asp:BoundField>
                <asp:BoundField HeaderText="Data" 
                 DataField="data" 
                 SortExpression="data">
                </asp:BoundField>
                <asp:BoundField HeaderText="Hora" 
                DataField="hora" SortExpression="hora" 
                </asp:BoundField>
            </Columns>
            <SelectedRowStyle ForeColor="White" Font-Bold="True" 
             BackColor="#738A9C"></SelectedRowStyle>
            <RowStyle ForeColor="#8C4510" 
               BackColor="#FFF7E7"></RowStyle>
        <asp:GridView>
        <asp:ObjectDataSource ID="compromissosDataSource" 
            Runat="server" SelectMethod="GetProducts">
        </asp:ObjectDataSource>
  
</html>
