    <%-- 
    Document   : PedidoBuscar
    Created on : 28/09/2017, 19:51:39
    Author     : fernanda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedido</title>
    </head>
    <body>
        <h1>Buscar Pedido</h1>
        <form action="FrontController?action=LerPedido" method="post">
          Entre com o código:
          <input type="text" name="textCodigo"/><br/>
          <input type="submit"/>
        </form>  
    </body>
</html>