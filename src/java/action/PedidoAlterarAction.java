/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;

/**
 *
 * @author fernanda
 */
public class PedidoAlterarAction  implements Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("textCliente");
        String aparelho = request.getParameter("textAparelho");
        String codigo = request.getParameter("textCodigo"); 
        
        if(nome.equals("") || aparelho.equals("")){
        
            try {
                request.setAttribute("codigo", codigo);
                request.setAttribute("nome", nome);
                request.setAttribute("aparelho", aparelho);

            
                RequestDispatcher view = request.getRequestDispatcher("PedidoAlterar.jsp");
            
                view.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(PedidoAlterarAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try{
                Pedido pedido = new Pedido(codigo, nome, aparelho);
                ClienteDAO.getInstance().alter(cliente);
            
                response.sendRedirect("TelaSucesso.jsp");
            
            } catch(SQLException e){
                response.sendRedirect("TelaErro.jsp");
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                  Logger.getLogger(GravarClienteAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
