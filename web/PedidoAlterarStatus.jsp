<jsp:useBean id="bean" class="bean.StatusBean" scope="request"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>Alterar Status</title>
</head>

<body>
    <h1>Alterar status do pedido</h1>

    <br>Status atual: ${param.statusPedido}

    <form action="FrontController?action=PedidoAlterarStatus" method="post">
        
        Mudar o status para:
        <select id="comboStatus" name="statusId">
            <option value="">..</option>
            
            <c:forEach var="statusPedido" items="${bean.status}" varStatus="loop">
                <option value="${statusPedido.retornaId()}">${statusPedido.retornarStatus()}</option>
            </c:forEach>
        </select>
       
        <input type="hidden" name="pedidoId" value="${param.pedidoId}">
        <input type="hidden" name="statusPedido" value="${param.statusPedido}">
        <input type="submit"/>
    </form>
    
        <br>    
        
    ${resultado}

    <br> 
    
    <a href="PedidoMostrar.jsp">Mostrar Pedidos</a>
    
</body>
</html>
