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
public class Defeito {
     
    private String descricao;
    private TipoDefeito tipoDefeito;

    public Defeito(TipoDefeito tipoDefeito , String descricao) {
        this.tipoDefeito = tipoDefeito;
        this.descricao = descricao;
    }
    
    public Defeito(TipoDefeito tipoDefeito) {
        this.tipoDefeito = tipoDefeito;
    }

    public TipoDefeito getTipoDefeito() {
        return tipoDefeito;
    }

    public void setTipoDefeito(TipoDefeito tipoDefeito) {
        this.tipoDefeito = tipoDefeito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
