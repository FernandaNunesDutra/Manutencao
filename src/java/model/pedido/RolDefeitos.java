/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pedido;

/**
 *
 * @author LucasRezende
 */
public class RolDefeitos {

    private static final RolDefeitos ROL_DEFEITOS = new RolDefeitos();
    private final TipoDefeitoSimples tipoDefeitoSimples = new TipoDefeitoSimples();
    private final TipoDefeitoMedio tipoDefeitoMedio = new TipoDefeitoMedio();
    private final TipoDefeitoDificil tipoDefeitoDificil = new TipoDefeitoDificil();
    private final TipoDefeitoMuitoDificil tipoDefeitoMuitoDificil = new TipoDefeitoMuitoDificil();

    public static RolDefeitos getInstance() {
        return ROL_DEFEITOS;
    }

    public TipoDefeitoSimples getTipoDefeitoSimples() {
        return tipoDefeitoSimples;
    }

    public TipoDefeitoMedio getTipoDefeitoMedio() {
        return tipoDefeitoMedio;
    }

    public TipoDefeitoDificil getTipoDefeitoDificil() {
        return tipoDefeitoDificil;
    }

    public TipoDefeitoMuitoDificil getTipoDefeitoMuitoDificil() {
        return tipoDefeitoMuitoDificil;
    }
}