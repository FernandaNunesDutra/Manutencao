package bean;

import model.pedido.StatusPedido;
import persistencia.StatusDAO;

import java.sql.SQLException;
import java.util.List;

public class StatusBean {

    public List<StatusPedido> getStatus() throws SQLException, ClassNotFoundException {
        return StatusDAO.getInstance().searchAll();
    }
}
