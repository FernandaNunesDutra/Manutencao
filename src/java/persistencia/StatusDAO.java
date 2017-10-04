/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import model.pedido.StatusFactory;
import model.pedido.StatusPedido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fernanda
 */
public class StatusDAO {
    private static StatusDAO instance;

    private StatusDAO() {}

    public static StatusDAO getInstance() {
        if (instance == null)
            instance = new StatusDAO();
        return instance;
    }

    private void closeResources(Connection conn, Statement st) throws SQLException {
        if (st != null) st.close();
        if (conn != null) conn.close();
    }

    public List<StatusPedido> searchAll() throws SQLException, ClassNotFoundException {
        List<StatusPedido> statusPedido = new ArrayList<>();
        Connection conn = null;
        Statement st = null;

        try {
            String query = "select * from statusPedido";

            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                StatusPedido status = StatusFactory.getStatusPedido(rs.getInt("id"));
                statusPedido.add(status);
            }
        } finally {
            closeResources(conn, st);
        }

        return statusPedido;
    }
}
