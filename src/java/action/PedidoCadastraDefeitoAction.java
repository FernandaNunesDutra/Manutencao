/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.pedido.Defeito;
import model.pedido.Pedido;
import model.pedido.TipoDefeito;
import model.pedido.TipoDefeitoFactory;
import persistencia.PedidoDAO;

/**
 *
 * @author fernanda
 */
public class PedidoCadastraDefeitoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String pedidoId = request.getParameter("pedidoId");
        String tipoDefeitoString = request.getParameter("TipoDefeito");
        
        RequestDispatcher rd = null;
        
        try {
            Pedido pedido = PedidoDAO.getInstance().search(pedidoId); 
            
            TipoDefeito tipoDefeito = TipoDefeitoFactory.getTipoDefeitoPedido(tipoDefeitoString);
            Defeito defeito = new Defeito(tipoDefeito);
            pedido.setDefeito(defeito);
            
            PedidoDAO.getInstance().alterDefeito(pedido);
            
            rd = request.getRequestDispatcher("PedidoMostrar.jsp");
        
        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("resultado", "Houve um erro ao adicionar defeito!");
            rd = request.getRequestDispatcher("PedidoCadastrarDefeito.jsp");
            ex.printStackTrace();
        }

        rd.forward(request, response);
    }

    
}
