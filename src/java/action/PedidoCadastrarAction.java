/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import model.cliente.Cliente;
import model.pedido.MetodoPagamento;
import model.pedido.MetodoPagamentoFactory;
import model.pedido.Pedido;
import persistencia.PedidoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import persistencia.ClienteDAO;

/**
 * @author fernanda
 */
public class PedidoCadastrarAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String clienteId = request.getParameter("clienteId");
        String aparelho = request.getParameter("textAparelho");
        int tipoPagamento = Integer.parseInt(request.getParameter("tipoPagamento"));
        
        if (clienteId.equals("") || aparelho.equals("")) {
            response.sendRedirect("PedidoCadastrar.jsp");
        } else {
            try {
                Cliente cliente = ClienteDAO.getInstance().search(clienteId);                
                MetodoPagamento metodoPagamento = MetodoPagamentoFactory.createMetodoPagamento(tipoPagamento);
                Pedido pedido = new Pedido(cliente, aparelho, metodoPagamento);
                PedidoDAO.getInstance().save(pedido, clienteId, tipoPagamento);
                
                request.setAttribute("resultado", "Pedido cadastrado com sucesso!");
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("resultado", "Houve um erro no cadastro do pedido!");
                ex.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher("PedidoCadastrar.jsp");
            rd.forward(request, response);
        }
    }

}
