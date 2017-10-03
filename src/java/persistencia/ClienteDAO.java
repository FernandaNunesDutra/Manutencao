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
import model.Cliente;

/**
 *
 * @author fernanda
 */
public class ClienteDAO {
    private static ClienteDAO instance;
    
    private ClienteDAO(){  }
    
    public static ClienteDAO getInstance(){
        if(instance == null)
            instance = new ClienteDAO();
        return instance;
    }
    
    public List<Cliente> search() throws ClassNotFoundException, SQLException {
        List<Cliente> clientes = new ArrayList<Cliente>();
        Connection conn = null;
        Statement st = null;
        
        try {
            String query = "select * from cliente ";
            
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                
                Cliente cliente = new Cliente(rs.getInt("id") , rs.getString("cliente"));
                clientes.add(cliente);
                
            }
            
            
            
            
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        
        return clientes;
    }
    
    
    private void closeResources(Connection conn, Statement st) {
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();

        } catch(SQLException e) {

        }
    }
}
