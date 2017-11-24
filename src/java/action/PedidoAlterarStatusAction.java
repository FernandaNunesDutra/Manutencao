/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import model.InvalidStateChangeException;
import model.pedido.Pedido;
import model.pedido.StatusFactory;
import persistencia.PedidoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.pedido.PedidoMemento;

/**
 * @author fernanda
 */
public class PedidoAlterarStatusAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int statusId = Integer.parseInt(request.getParameter("statusId"));
        String statusPedido = request.getParameter("statusPedido").replaceAll(" ", "%20");
        String pedidoId = request.getParameter("pedidoId");

        RequestDispatcher rd;
        try {
            Pedido pedido = PedidoDAO.getInstance().search(pedidoId);
            switch (statusId) {
                case StatusFactory.RECEBIDO:
                    pedido.receber();
                    break;
                case StatusFactory.EM_AVALIACAO:
                    pedido.avaliar();
                    break;
                case StatusFactory.EM_MANUTENCAO:
                    pedido.realizarManutencao();
                    break;
                case StatusFactory.CONSERTADO:
                    pedido.informarConserto();
                    break;
                case StatusFactory.SEM_CONSERTO:
                    pedido.informarSemConserto();
                    break;
                case StatusFactory.CANCELADO:
                    pedido.cancelar();
                    break;
                case StatusFactory.AGUARDANDO_PECA:
                    pedido.aguardarPeca();
                    break;
                default:
                    throw new IllegalArgumentException("Status id inválido: " + statusId);
            }
            
            PedidoDAO.getInstance().alter(pedido);
            
            //Memento
           /* ArrayList<PedidoMemento> statusSalvos = pedido.getStatusSalvos();
            statusSalvos.add(pedido.saveToMemento());*/
             
            List<String> mensagens = pedido.getCliente().getMensagens();
            
            if(!mensagens.isEmpty()){
                request.setAttribute("mensagens", mensagens);
            }
            
            rd = request.getRequestDispatcher("PedidoMostrar.jsp");
        
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("resultado", "Houve um erro ao alterar o status do pedido!");
            rd = request.getRequestDispatcher("PedidoAlterarStatus.jsp?statusPedido="
                    + statusPedido + "&pedidoId=" + pedidoId);
            ex.printStackTrace();
        } catch (InvalidStateChangeException ex) {
            request.setAttribute("resultado", ex.getMessage());
            rd = request.getRequestDispatcher("PedidoAlterarStatus.jsp?statusPedido="
                    + statusPedido + "&pedidoId=" + pedidoId);
        }

        rd.forward(request, response);
    }

}
