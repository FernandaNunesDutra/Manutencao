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
public class TipoDefeitoMedio implements TipoDefeito{

    @Override
    public String retornaTipoDefeito() {
        return "Médio";
    }
    
    @Override
    public String retornaCodigoTipoDefeito() {
        return "M";
    }
    
}