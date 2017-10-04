<%@ page import="model.cliente.ClienteFactory" %>
<%@ page import="model.pedido.MetodoPagamentoFactory" %><%--
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
        
        <form action="FrontController?action=PedidoCadastrar" method="post">
            Cliente:
            <input type="text" name="nomeCliente"/>
            <select id="comboTipo" name="tipoCliente">
                <option value="<%= ClienteFactory.TIPO_PF %>">Pessoa física</option>
                <option value="<%= ClienteFactory.TIPO_PJ %>">Pessoa jurídica</option>
            </select>
            <select id="comboPagamento" name="tipoPagamento">
                <option value="<%= MetodoPagamentoFactory.TIPO_A_VISTA %>">A vista</option>
                <option value="<%= MetodoPagamentoFactory.TIPO_A_PRAZO %>">A prazo</option>
            </select><br/>
            Aparelho:
            <input type="text" name="textAparelho"/><br/>  
            <input type="submit"/>
        </form>

        ${resultado}

        <a href="PedidoMostrar.jsp">Mostrar Pedidos</a>
    </body>
</html>

