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
import model.funcionario.Funcionario;
import model.pedido.Pedido;
import persistencia.FuncionarioDAO;
import persistencia.PedidoDAO;

/**
 *
 * @author fernanda
 */
public class PedidoAtribuirConsertoAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pedidoId = request.getParameter("pedidoId");        
        
        if (pedidoId.equals("")) {
            response.sendRedirect("PedidoMostrar.jsp");
        } else {
            try {
                Pedido pedido = PedidoDAO.getInstance().search(pedidoId);
                Funcionario funcionario = FuncionarioDAO.getInstance().search("J");
                int idFuncionario = funcionario.atribuirConserto(pedido.getDefeito());
                if(idFuncionario == 0){
                    request.setAttribute("resultado", "Não foi possível atribuir o pedido a um funcionário!");
                } else {
                    request.setAttribute("resultado", "Conserto atribuido sucesso!");
                    pedido.setIdFuncionario(idFuncionario);
                    PedidoDAO.getInstance().alterFuncionario(pedido);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("resultado", "Houve um erro na atribuição do conserto!");
                ex.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher("PedidoMostrar.jsp");
            rd.forward(request, response);
        }
    }
    
}
