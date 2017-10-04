<jsp:useBean id="bean" class="bean.PedidoBean" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>Produtos</title>
</head>
<body>
<h1>Produtos</h1>

${mensagens}

<h3><b>Data de
    recebimento&nbsp;&nbsp;&nbsp;&nbsp;Cliente&nbsp;&nbsp;&nbsp;&nbsp;Método de Pagamento&nbsp;&nbsp;&nbsp;&nbsp;Aparelho&nbsp;&nbsp;&nbsp;&nbsp;Status</b>
</h3>
<c:forEach var="pedido" items="${bean.pedidos}">
    <h4>${pedido.dataRecebido}&nbsp;&nbsp;&nbsp;&nbsp;${pedido.cliente.mostrarInformacoes()}&nbsp;&nbsp;&nbsp;&nbsp;${pedido.metodoPagamento.imprimir()}&nbsp;&nbsp;&nbsp;&nbsp;${pedido.aparelho}&nbsp;&nbsp;&nbsp;&nbsp;${pedido.status}</h4>

    <form action="PedidoAlterarStatus.jsp" method="post">
        <input type="hidden" name="pedidoId" id="pedidoId" value="${pedido.id}"/>
        <input type="hidden" name="statusPedido" id="statusPedido" value="${pedido.status}"/>
        <button type="submit">Alterar Status</button>
    </form>
</c:forEach>
</body>
</html>
