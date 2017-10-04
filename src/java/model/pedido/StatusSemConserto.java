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
public class StatusSemConserto implements StatusPedido {
    
    private int id;
    
    @Override
    public void receber(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Sem Conserto\' para \'Recebido\' é inválida!");
    }

    @Override
    public void avaliar(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Sem Conserto\' para \'Em avaliação\' é inválida!");
    }

    @Override
    public void realizarManutencao(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Sem Conserto\' para \'Em manutenção\' é inválida!");
    }

    @Override
    public void aguardarPeca(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Sem Conserto\' para \'Aguardando peça\' é inválida!");
    }

    @Override
    public void cancelar(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Sem Conserto\' para \'Cancelado\' é inválida!");
    }

    @Override
    public void informarSemConserto(Pedido pedido) throws InvalidStateChangeException {

    }

    @Override
    public void informarConserto(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Sem Conserto\' para \'Consertado\' é inválida!");
    }

    @Override
    public String retornarStatus() {
        return "Sem Conserto";
    }

    @Override
    public int retornaId() {
        return StatusFactory.SEM_CONSERTO;
    }
    

}
