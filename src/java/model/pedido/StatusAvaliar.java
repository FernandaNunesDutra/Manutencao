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
public class StatusAvaliar implements StatusPedido {
    
    @Override
    public void receber(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Em avaliação\' para \'Recebido\' é inválida!");
    }

    @Override
    public void avaliar(Pedido pedido) throws InvalidStateChangeException {

    }

    @Override
    public void realizarManutencao(Pedido pedido) throws InvalidStateChangeException {
        if(pedido.getDefeito() == null) {
            throw new InvalidStateChangeException("Mudança de status de \'Em avaliação\' para \'Em manutenção\' é inválida"
                    + " quando o defeito do pedido não está setado!");
        }
        pedido.setStatus(new StatusManutencao());
    }

    @Override
    public void aguardarPeca(Pedido pedido) throws InvalidStateChangeException {
        pedido.setStatus(new StatusAguardandoPeca());
    }

    @Override
    public void cancelar(Pedido pedido) throws InvalidStateChangeException {
        pedido.setStatus(new StatusCancelado());
    }

    @Override
    public void informarSemConserto(Pedido pedido) throws InvalidStateChangeException {
        pedido.setStatus(new StatusSemConserto());
    }

    @Override
    public void informarConserto(Pedido pedido) throws InvalidStateChangeException {
        throw new InvalidStateChangeException("Mudança de status de \'Em avaliação\' para \'Consertado\' é inválida!");
    }

    @Override
    public String retornarStatus() {
        return "Em avaliação";
    }

     @Override
    public int retornaId() {
        return StatusFactory.EM_AVALIACAO;
    }
    
}
