<%-- 
    Document   : PedidoCadastrar
    Created on : 28/09/2017, 19:51:27
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
        <h1>Cadastrar</h1>
        
        <form action="FrontController?action=GravarPedido" method="post">
            Cliente:
            <input type="text" name="textCliente"/><br/>
            Aparelho:
            <input type="text" name="textAparelho"/><br/>  
            <input type="submit"/>
        </form>
    </body>
</html>

