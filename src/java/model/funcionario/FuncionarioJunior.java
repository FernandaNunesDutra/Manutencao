/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.funcionario;

import model.pedido.RolDefeitos;

/**
 *
 * @author fernanda
 */
public class FuncionarioJunior extends Funcionario{

    public FuncionarioJunior(Funcionario superior, int id) {
        this.funcionarioSuperior = superior;
        this.id = id;
        this.listaDefeitos.add(RolDefeitos.getInstance().getTipoDefeitoSimples());
    }
}