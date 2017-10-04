package bean;

import model.pedido.Pedido;
import persistencia.PedidoDAO;

import java.sql.SQLException;
import java.util.List;

public class PedidoBean {

    public List<Pedido> getPedidos() throws SQLException, ClassNotFoundException {
        return PedidoDAO.getInstance().searchAll();
    }
}
