/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.funcionario;

import java.util.ArrayList;
import model.pedido.Defeito;
import model.pedido.TipoDefeito;


public abstract class Funcionario {

    protected int id;
    protected ArrayList<TipoDefeito> listaDefeitos = new ArrayList();
    protected Funcionario funcionarioSuperior;
    
    public int atribuirConserto(Defeito defeito) {
        if (listaDefeitos.contains(defeito.getTipoDefeito())) {
            return id;
        } else if (funcionarioSuperior != null) {
            return funcionarioSuperior.atribuirConserto(defeito);
        } else {
            return 0;
        }
    }

}
