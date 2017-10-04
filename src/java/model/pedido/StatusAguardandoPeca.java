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
public class StatusAguardandoPeca implements StatusPedido {

    @Override
    public void receber(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Aguardando peça\' para \'Recebido\' é inválida!");
    }

    @Override
    public void avaliar(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Aguardando peça\' para \'Em avaliação\' é inválida!");
    }

    @Override
    public void realizarManutencao(Pedido pedido) throws InvalidStateChangeException {
        pedido.setStatus(new StatusManutencao());
    }

    @Override
    public void aguardarPeca(Pedido pedido) throws InvalidStateChangeException {

    }

    @Override
    public void cancelar(Pedido pedido) throws InvalidStateChangeException {
        pedido.setStatus(new StatusCancelado());
    }

    @Override
    public void informarSemConserto(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Aguardando peça\' para \'Sem Conserto\' é inválida!");
    }

    @Override
    public void informarConserto(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Aguardando peça\' para \'Consertado\' é inválida!");
    }

    @Override
    public String retornarStatus() {
        return "Aguardando peça";
    }
}
