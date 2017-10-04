/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import model.cliente.Cliente;
import model.cliente.ClienteFactory;
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

/**
 * @author fernanda
 */
public class PedidoCadastrarAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomeCliente = request.getParameter("nomeCliente");
        String aparelho = request.getParameter("textAparelho");
        int tipoCliente = Integer.parseInt(request.getParameter("tipoCliente"));
        int tipoPagamento = Integer.parseInt(request.getParameter("tipoPagamento"));

        if (nomeCliente.equals("") || aparelho.equals("")) {
            response.sendRedirect("PedidoCadastrar.jsp");
        } else {
            try {
                Cliente cliente = ClienteFactory.createCliente(tipoCliente, nomeCliente);
                MetodoPagamento metodoPagamento = MetodoPagamentoFactory.createMetodoPagamento(tipoPagamento);
                Pedido pedido = new Pedido(cliente, aparelho, metodoPagamento);
                PedidoDAO.getInstance().save(pedido, tipoCliente, tipoPagamento);
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
