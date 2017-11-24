/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import model.cliente.Cliente;
import model.cliente.ClienteFactory;
import model.pedido.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fernanda
 */
public class PedidoDAO {
    private static PedidoDAO instance;

    private PedidoDAO() {
    }

    public static PedidoDAO getInstance() {
        if (instance == null)
            instance = new PedidoDAO();
        return instance;
    }

    public void save(Pedido pedido, String clienteId, int tipoPagamento) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            String dataStr = new SimpleDateFormat("yyyy-MM-dd").format(pedido.getDataRecebido());

            String queryPedido = "INSERT INTO pedido (cliente, aparelho, dataRecebido, tipoPagamento) "
                               + "VALUES ("+ clienteId +", '" + pedido.getAparelho() + "', '" + dataStr + "', " + tipoPagamento + ");";
            
            //Buscando ultimo id
            int lastId = 0;
            PreparedStatement ps = conn.prepareStatement(queryPedido, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                lastId = rs.getInt(1);
            }
            
            queryPedido = "INSERT INTO hist_pedido_status (id_pedido, id_status) VALUES ('"+ lastId +"', '" + StatusFactory.RECEBIDO + "');";

            st.execute(queryPedido);
           
            
        } finally {
            closeResources(conn, st);
        }
    }

    public Pedido search(String codigo) throws ClassNotFoundException, SQLException {
        Pedido pedido = null;
        String tipoDefeitoId;
        Connection conn = null;
        Statement st = null;

        try {
            String query = "SELECT pedido.*, cliente.* FROM pedido INNER JOIN cliente ON pedido.cliente = cliente.id" +
                    " WHERE pedido.id = " + codigo;

            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                StatusPedido statusPedido = StatusFactory.getStatusPedido(buscaUltimoStatus(rs.getInt("id")));
                
                Cliente cliente = ClienteFactory.getCliente(rs);
                
                MetodoPagamento metodoPagamento = MetodoPagamentoFactory.createMetodoPagamento(rs.getInt("tipoPagamento"));
                
                pedido = new Pedido(rs.getInt("id"), rs.getInt("id_funcionario"), cliente, metodoPagamento, rs.getString("aparelho"),
                         rs.getDate("dataRecebido"), statusPedido);
                                
                tipoDefeitoId = rs.getString("grau_defeito");
                
                //Memento
                pedido = buscaHistoricoStatus(pedido);
                             
                if(tipoDefeitoId != null){
                    TipoDefeito tipoDefeito = TipoDefeitoFactory.getTipoDefeitoPedido(rs.getString("grau_defeito"));
                    Defeito defeito = new Defeito(tipoDefeito);
                    pedido.setDefeito(defeito);
                }
                
            }
        } finally {
            closeResources(conn, st);
        }

        return pedido;
    }

    public void alter(Pedido pedido) throws ClassNotFoundException, SQLException {
        insereHistoricoStatus(pedido.getId() , pedido.getIdStatus());
    }
    
    public void alterDesfazStatus(Pedido pedido) throws ClassNotFoundException, SQLException {
         Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
                      
            String query = "SELECT * FROM hist_pedido_status WHERE id_pedido='"+pedido.getId()+"' ORDER BY id DESC LIMIT 1";
            ResultSet rs = st.executeQuery(query);
            if(rs.next()) {
                query = "DELETE FROM hist_pedido_status WHERE id='"+rs.getInt("id")+"'";
                st.executeUpdate(query);
            }

            
        } finally {
            closeResources(conn, st);
        }
                
    }
    
    public void alterDefeito(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            String codigo = pedido.getDefeito().getTipoDefeito().retornaCodigoTipoDefeito();
            String updatePedidoQuery = "UPDATE pedido SET  grau_defeito='" + codigo + "' WHERE id = " + pedido.getId();
            st.executeUpdate(updatePedidoQuery);
        } finally {
            closeResources(conn, st);
        }
    }

    public void alterFuncionario(Pedido pedido) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            int idFuncionario = pedido.getIdFuncionario();
            String updatePedidoQuery = "UPDATE pedido SET  id_funcionario=" + idFuncionario + " WHERE id = " + pedido.getId();
            st.executeUpdate(updatePedidoQuery);
            
        } finally {
            closeResources(conn, st);
        }
    }

    public List<Pedido> searchAll() throws SQLException, ClassNotFoundException {
        List<Pedido> pedidos = new ArrayList<>();
        String tipoDefeitoId;
        Connection conn = null;
        Statement st = null;

        try {
            String query = "SELECT pedido.*,cliente.* FROM pedido INNER JOIN cliente ON pedido.cliente = cliente.id";

            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                StatusPedido statusPedido = StatusFactory.getStatusPedido(buscaUltimoStatus(rs.getInt("id")));
                Cliente cliente = ClienteFactory.getCliente(rs);
                MetodoPagamento metodoPagamento = MetodoPagamentoFactory.createMetodoPagamento(rs.getInt("tipoPagamento"));
                
                Pedido pedido = new Pedido(rs.getInt("id"), rs.getInt("id_funcionario"), cliente, metodoPagamento, rs.getString("aparelho"),
                        rs.getDate("dataRecebido"), statusPedido);
                
                tipoDefeitoId = rs.getString("grau_defeito");
                
                if(tipoDefeitoId != null){
                    TipoDefeito tipoDefeito = TipoDefeitoFactory.getTipoDefeitoPedido(rs.getString("grau_defeito"));
                    Defeito defeito = new Defeito(tipoDefeito);
                    pedido.setDefeito(defeito);
                }
                
                pedidos.add(buscaHistoricoStatus(pedido));
            }
        } finally {
            closeResources(conn, st);
        }

        return pedidos;
    }
    
    private void insereHistoricoStatus(int pedidoId , int statusId) throws ClassNotFoundException, SQLException{
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            
            String queryStatus = "INSERT INTO hist_pedido_status (id_pedido, id_status) "
                               + "VALUES ('"+ pedidoId +"', '" + statusId + "');";
            
            String query = "SELECT * FROM hist_pedido_status WHERE id_pedido='"+pedidoId+"' ORDER BY id DESC LIMIT 1";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                if(rs.getInt("id_status") != statusId){
                   st.execute(queryStatus); 
                }
            }else{
                st.execute(queryStatus);
            }

            
        } finally {
            closeResources(conn, st);
        }
    }
    
    private Pedido buscaHistoricoStatus(Pedido pedido) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
                      
            String query = "SELECT * FROM hist_pedido_status WHERE id_pedido='"+pedido.getId()+"' ORDER BY id ASC";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                 pedido.setStatus(StatusFactory.getStatusPedido(rs.getInt("id_status")));
                 pedido.getStatusSalvos().add(pedido.saveToMemento());
            }

            
        } finally {
            closeResources(conn, st);
        }
                
        return pedido;
    }
    
    private int buscaUltimoStatus(int pedidoId)throws ClassNotFoundException, SQLException{
        Connection conn = null;
        Statement st = null;
        int id=0;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            
            String query = "SELECT * FROM hist_pedido_status WHERE id_pedido='"+pedidoId+"' ORDER BY id DESC LIMIT 1";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                id = rs.getInt("id_status");
            }

            
        } finally {
            closeResources(conn, st);
        }
        
        return id;
    }
    
    private void closeResources(Connection conn, Statement st) throws SQLException {
        if (st != null) st.close();
        if (conn != null) conn.close();
    }
}
