/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.cliente.Cliente;
import model.cliente.ClienteFactory;

/**
 *
 * @author fernanda
 */
public class ClienteDAO {
    
    private static ClienteDAO instance;

    private ClienteDAO() {}

    public static ClienteDAO getInstance() {
        if (instance == null)
            instance = new ClienteDAO();
        return instance;
    }
    
    public List<Cliente> searchAll() throws SQLException, ClassNotFoundException {
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = null;
        Statement st = null;

        try {
            String query = "select * from cliente";

            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cliente cliente = ClienteFactory.createCliente(rs.getInt("tipo"), rs.getString("nome"));
                cliente.setId(rs.getInt("id"));
                        
                clientes.add(cliente);
            }
        } finally {
            closeResources(conn, st);
        }

        return clientes;
    }
    
    
    public Cliente search(String clienteId) throws SQLException, ClassNotFoundException {
        Cliente cliente;
        Connection conn = null;
        Statement st = null;

        try {
            String query = "select * from cliente where id='"+clienteId+"'";

            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            rs.next();
            
            cliente = ClienteFactory.createCliente(rs.getInt("tipo"), rs.getString("nome"));
            cliente.setId(rs.getInt("id"));
                        
        } finally {
            closeResources(conn, st);
        }

        return cliente;
    }
    
    private void closeResources(Connection conn, Statement st) throws SQLException {
        if (st != null) st.close();
        if (conn != null) conn.close();
    }
}
