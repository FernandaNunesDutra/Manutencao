/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.funcionario;

/**
 *
 * @author fernanda
 */
public class FuncionarioSenior extends Funcionario{

    public FuncionarioSenior(Funcionario superior) {
        setFuncionarioSuperior(superior);
    }
    
    @Override
    public String getDescricaoCargo() {
        return "Sênior";
    }
}
