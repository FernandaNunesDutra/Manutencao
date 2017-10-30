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
public class FuncionarioCoordenador extends Funcionario{

    public FuncionarioCoordenador(int id) {
        this.id = id;
        this.listaDefeitos.add(RolDefeitos.getInstance().getTipoDefeitoSimples());
        this.listaDefeitos.add(RolDefeitos.getInstance().getTipoDefeitoMedio());
        this.listaDefeitos.add(RolDefeitos.getInstance().getTipoDefeitoDificil());
        this.listaDefeitos.add(RolDefeitos.getInstance().getTipoDefeitoMuitoDificil());
    }
}
