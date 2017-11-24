/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pedido;

public class PedidoMemento {
    private StatusPedido status;

    public PedidoMemento(StatusPedido status) {
        this.status = status;
    }

    public StatusPedido getStatusSalvo() {
        return status;
    }

    @Override
    public String toString() {
        return status.retornarStatus();
    }
    
}
