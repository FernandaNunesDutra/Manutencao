/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.funcionario;

import java.util.ArrayList;
import model.pedido.Defeito;


public abstract class Funcionario {

    protected ArrayList listaDefeitos = new ArrayList();
    private Funcionario funcionarioSuperior;

    public abstract String getDescricaoCargo();

    public Funcionario getFuncionarioSuperior() {
        return funcionarioSuperior;
    }

    public void setFuncionarioSuperior(Funcionario funcionarioSuperior) {
        this.funcionarioSuperior = funcionarioSuperior;
    }
    
    public String atribuirConserto(Defeito defeito) {
        if (listaDefeitos.contains(defeito.getTipoDefeito())) {
            return getDescricaoCargo();
        } else if (funcionarioSuperior != null) {
            return funcionarioSuperior.atribuirConserto(defeito);
        } else {
            return "Sem Conserto";
        }
    }

}
