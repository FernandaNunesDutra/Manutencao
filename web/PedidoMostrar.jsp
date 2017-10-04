<jsp:useBean id="bean" class="bean.PedidoBean" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
    padding: 15px;
    
}
</style>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>Produtos</title>
</head>
<body>

<h1>Produtos Recebidos</h1>

${mensagens}

    <table>
        <tr>
            <td><b>Data de Recebimento</b></td>
            <td><b>Cliente</b></td>
            <td><b>Método de Pagamento</b></td>
            <td><b>Aparelho</b></td>
            <td colspan="2"><b>Status</b></td>
        </tr>
        <c:forEach var="pedido" items="${bean.pedidos}">
            <tr>
                <td>${pedido.dataRecebido}</td>
                <td>${pedido.cliente.mostrarInformacoes()}</td>
                <td>${pedido.metodoPagamento.imprimir()}</td>
                <td>${pedido.aparelho}</td>
                <td>${pedido.status}</td>
                <td>
                    <form action="PedidoAlterarStatus.jsp" method="post">
                        <input type="hidden" name="pedidoId" id="pedidoId" value="${pedido.id}"/>
                        <input type="hidden" name="statusPedido" id="statusPedido" value="${pedido.status}"/>
                        <button type="submit">Alterar Status</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
