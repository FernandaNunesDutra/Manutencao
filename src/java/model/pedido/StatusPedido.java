/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pedido;

import model.InvalidStateChangeException;

/**
 * @author fernanda
 */
public interface StatusPedido {
    
    void receber(Pedido pedido) throws InvalidStateChangeException;

    void avaliar(Pedido pedido) throws InvalidStateChangeException;

    void realizarManutencao(Pedido pedido) throws InvalidStateChangeException;

    void aguardarPeca(Pedido pedido) throws InvalidStateChangeException;

    void cancelar(Pedido pedido) throws InvalidStateChangeException;

    void informarSemConserto(Pedido pedido) throws InvalidStateChangeException;

    void informarConserto(Pedido pedido) throws InvalidStateChangeException;

    String retornarStatus();
        
    int retornaId();
}
