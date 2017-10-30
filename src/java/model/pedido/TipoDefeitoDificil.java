/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pedido;

/**
 *
 * @author fernanda
 */
public class TipoDefeitoDificil implements TipoDefeito{

    @Override
    public String retornaTipoDefeito() {
        return "Difícil";
    }

    @Override
    public String retornaCodigoTipoDefeito() {
        return "D";
    }
    
}
