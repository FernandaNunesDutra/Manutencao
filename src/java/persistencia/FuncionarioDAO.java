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
import model.funcionario.Funcionario;
import model.funcionario.FuncionarioFactory;

/**
 *
 * @author fernanda
 */
public class FuncionarioDAO {
    
    private static FuncionarioDAO instance;

    private FuncionarioDAO() {
    }

    public static FuncionarioDAO getInstance() {
        if (instance == null)
            instance = new FuncionarioDAO();
        return instance;
    }
    
    public Funcionario search(String cargo) throws ClassNotFoundException, SQLException {
        Funcionario funcionario = null;
        Connection conn = null;
        Statement st = null;

        try {
            
            String query = "SELECT * FROM funcionario WHERE cargo = '" + cargo + "'";
            
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Funcionario superior = search(rs.getInt("id_superior"));
                funcionario = FuncionarioFactory.getFuncionario(
                        cargo, rs.getInt("id"), superior);
            }
            
        } finally {
            closeResources(conn, st);
        }

        return funcionario;
    }
    
    private Funcionario search(int id) throws SQLException, ClassNotFoundException{
        Funcionario funcionario = null;
        Connection conn = null;
        Statement st = null;

        try {
            
            String query = "SELECT * FROM funcionario WHERE id = " + id;
            
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Funcionario superior = search(rs.getInt("id_superior"));
                funcionario = FuncionarioFactory.getFuncionario(
                        rs.getString("cargo"), rs.getInt("id"), superior);
            }            
        } finally {
            closeResources(conn, st);
        }

        return funcionario;
    }
    
    private void closeResources(Connection conn, Statement st) throws SQLException {
        if (st != null) st.close();
        if (conn != null) conn.close();
    }
}
