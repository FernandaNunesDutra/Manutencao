/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import model.pedido.Pedido;
import persistencia.PedidoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import model.pedido.PedidoMemento;
import model.pedido.StatusFactory;

/**
 * @author fernanda
 */
public class PedidoDesfazerStatusAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pedidoId = request.getParameter("pedidoId");
        
        RequestDispatcher rd;
        try {
                Pedido pedido = PedidoDAO.getInstance().search(pedidoId);
                
                if(pedido.getIdStatus() != StatusFactory.RECEBIDO){
                    PedidoDAO.getInstance().alterDesfazStatus(pedido);
            
                    //Memento
                    ArrayList<PedidoMemento> statusSalvos = pedido.getStatusSalvos();
            
                    pedido.restoreFromMemento(statusSalvos.get(statusSalvos.size()-1));
                } else {
                    request.setAttribute("resultado", "Impossível desfazer esta ação!");
                }
                
                rd = request.getRequestDispatcher("PedidoMostrar.jsp");
        
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("resultado", "Houve um erro ao desfazer o status do pedido!");
            rd = request.getRequestDispatcher("PedidoMostrar.jsp?");
            ex.printStackTrace();
        }

        rd.forward(request, response);
    }

}
