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
import model.Pedido;

/**
 *
 * @author fernanda
 */
public class PedidoDAO {
     private static PedidoDAO instance;
    
    private PedidoDAO(){  }
    
    public static PedidoDAO getInstance(){
        if(instance == null)
            instance = new PedidoDAO();
        return instance;
    }
    
    public void save(Pedido pedido) throws ClassNotFoundException, SQLException{
       Connection conn = null;
        Statement st = null;
        
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute("insert into pedidos (cliente, aparelho) values ('" + pedido.getCliente() + "', '" + pedido.getAparelho()+"')");
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
    public Pedido search(String codigo) throws ClassNotFoundException, SQLException {
        Pedido pedido = null;
        Connection conn = null;
        Statement st = null;
        
        try {
            String query = "select * from pedido where codigo = '"+codigo+"'";
            
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(query);
            rs.next();
            
            pedido = new Pedido(rs.getInt("codigo"), rs.getString("cliente"), rs.getString("aparelho"),  rs.getDate("data"));
            
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        
        return pedido;
    }
    
    public void alter(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        
        try {
            String query = "UPDATE pedido SET cliente ='"+pedido.getCliente()+"' , "
                    + "     aparelho='"+pedido.getAparelho()+"' , " 
                    + "     where codigo = '"+pedido.getCodigo()+"'";
            
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute(query);
                        
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        
    }

        public void delete(String codigo) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        
        try {
            String query = "DELETE FROM produto where codigo = '"+codigo+"'";
            
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            st.execute(query);
                        
        } catch(SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
        
    }    

    private void closeResources(Connection conn, Statement st) {
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();

        } catch(SQLException e) {

        }
    }
}
