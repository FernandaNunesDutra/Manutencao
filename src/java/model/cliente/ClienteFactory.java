package model.cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ClienteFactory {

    public static final int TIPO_PF = 1;
    public static final int TIPO_PJ = 2;

    public static Cliente getCliente(ResultSet rs) throws SQLException {
        int tipo = rs.getInt("tipo");
        Cliente cliente;
        switch (tipo) {
            case TIPO_PF:
                cliente = new ClienteFisico(rs.getInt("id"), rs.getString("nome"));
                break;
            case TIPO_PJ:
                cliente = new ClienteJuridico(rs.getInt("id"), rs.getString("nome"));
                break;
            default:
                throw new IllegalArgumentException("Tipo de cliente inválido: " + tipo);
        }

        return cliente;
    }

    public static Cliente createCliente(int tipoCliente, String nomeCliente) {
        Cliente cliente;
        switch (tipoCliente) {
            case TIPO_PF:
                cliente = new ClienteFisico(nomeCliente);
                break;
            case TIPO_PJ:
                cliente = new ClienteJuridico(nomeCliente);
                break;
            default:
                throw new IllegalArgumentException("Tipo de cliente inválido: " + tipoCliente);
        }

        return cliente;
    }
}
