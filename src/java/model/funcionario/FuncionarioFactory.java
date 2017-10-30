package model.funcionario;

import java.sql.SQLException;

public final class FuncionarioFactory {

    public static final String JUNIOR = "J";
    public static final String PLENO  = "P";
    public static final String SENIOR = "S";
    public static final String COORDENADOR = "C";

    public static Funcionario getFuncionario(String cargo, int id, 
            Funcionario funcionarioSuperior) throws SQLException {
        Funcionario funcionario;
        switch (cargo) {
            case JUNIOR:
                funcionario = new FuncionarioJunior(funcionarioSuperior, id);
                break;
            case PLENO:
                funcionario = new FuncionarioPleno(funcionarioSuperior, id);
                break;
            case SENIOR:
                funcionario = new FuncionarioSenior(funcionarioSuperior, id);
                break;
            case COORDENADOR:
                funcionario = new FuncionarioCoordenador(id);
                break;
            default:
                throw new IllegalArgumentException("Cargo inválido: " + cargo);
        }

        return funcionario;
    }

}
