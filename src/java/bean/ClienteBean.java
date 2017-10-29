/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.SQLException;
import java.util.List;
import model.cliente.Cliente;
import persistencia.ClienteDAO;

/**
 *
 * @author fernanda
 */
public class ClienteBean {
   
    public List<Cliente> getClientes() throws SQLException, ClassNotFoundException {
        return ClienteDAO.getInstance().searchAll();
    }
}
