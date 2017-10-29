<%@ page import="model.cliente.ClienteFactory" %>
<%@ page import="model.pedido.MetodoPagamentoFactory" %>
<jsp:useBean id="bean" class="bean.ClienteBean" scope="request"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        
        <form action="FrontController?action=PedidoCadastrar" method="post">
            Cliente:
            
            <select id="comboCliente" name="clienteId">
                <option value="">..</option>
                <c:forEach var="cliente" items="${bean.clientes}" varStatus="loop">
                    <option value="${cliente.getId()}">${cliente.getNome()}</option>
                </c:forEach>
            </select>
                
            <select id="comboPagamento" name="tipoPagamento">
                <option value="<%= MetodoPagamentoFactory.TIPO_A_VISTA %>">A vista</option>
                <option value="<%= MetodoPagamentoFactory.TIPO_A_PRAZO %>">A prazo</option>
            </select><br/>
            
            Aparelho:
            <input type="text" name="textAparelho"/><br/>  
            <input type="submit"/>
        </form>
        
        <br>    
        ${resultado}
        <br>
        
        <a href="PedidoMostrar.jsp">Mostrar Pedidos</a>
    </body>
</html>

