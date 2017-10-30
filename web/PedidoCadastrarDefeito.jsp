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
    <h1>Alterar Defeito do Pedido</h1>

    <form action="FrontController?action=PedidoCadastraDefeito" method="post">
       
        Tipo de Defeito:
        <select id="comboStatus" name="TipoDefeito">
            <option value="">..</option>
            <option value="S">Simples</option>
            <option value="M">Médio</option>
            <option value="D">Difícil</option>
            <option value="MD">Muito Difícil</option>
        </select>
        
        <br/>
        
        <input type="hidden" name="pedidoId" value="${param.pedidoId}">
        <input type="submit"/>
    </form>
    
    <br>    
        
    ${resultado}
    
    <br>
        
    <a href="PedidoMostrar.jsp">Mostrar Pedidos</a>

</body>
</html>
