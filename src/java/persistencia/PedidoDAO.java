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

            String queryPedido = "INSERT INTO pedido (cliente, aparelho, dataRecebido, status, tipoPagamento) "
                               + "VALUES ("+ clienteId +", '" + pedido.getAparelho() + "', '" + dataStr + "', " 
                               + StatusFactory.RECEBIDO + ", " + tipoPagamento + ");";

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
                StatusPedido statusPedido = StatusFactory.getStatusPedido(rs.getInt("status"));
                
                Cliente cliente = ClienteFactory.getCliente(rs);
                
                MetodoPagamento metodoPagamento = MetodoPagamentoFactory.createMetodoPagamento(rs.getInt("tipoPagamento"));
                
                pedido = new Pedido(rs.getInt("id"), cliente, metodoPagamento, rs.getString("aparelho"),
                        rs.getDate("dataRecebido"), statusPedido);
                
                tipoDefeitoId = rs.getString("grau_defeito");
                
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
        Connection conn = null;
        Statement st = null;

        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            String updatePedidoQuery = "UPDATE pedido SET status =" + pedido.getIdStatus() + " WHERE id = " + pedido.getId();
            st.executeUpdate(updatePedidoQuery);
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
                StatusPedido statusPedido = StatusFactory.getStatusPedido(rs.getInt("status"));
                Cliente cliente = ClienteFactory.getCliente(rs);
                MetodoPagamento metodoPagamento = MetodoPagamentoFactory.createMetodoPagamento(rs.getInt("tipoPagamento"));
                
                Pedido pedido = new Pedido(rs.getInt("id"), cliente, metodoPagamento, rs.getString("aparelho"),
                        rs.getDate("dataRecebido"), statusPedido);
                
                
                
                tipoDefeitoId = rs.getString("grau_defeito");
                
                if(tipoDefeitoId != null){
                    TipoDefeito tipoDefeito = TipoDefeitoFactory.getTipoDefeitoPedido(rs.getString("grau_defeito"));
                    Defeito defeito = new Defeito(tipoDefeito);
                    pedido.setDefeito(defeito);
                }
                
                 pedidos.add(pedido);
            }
        } finally {
            closeResources(conn, st);
        }

        return pedidos;
    }
    
    private void closeResources(Connection conn, Statement st) throws SQLException {
        if (st != null) st.close();
        if (conn != null) conn.close();
    }
}
