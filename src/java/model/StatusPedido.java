/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fernanda
 */
public interface StatusPedido {
    
    void avaliar(Pedido pedido);
    void realizarManutencao(Pedido pedido);
    void aguardarPeca(Pedido pedido);
    void cancelar(Pedido pedido);
    void informarSemConserto(Pedido pedido);
    void informarConserto(Pedido pedido);
    
}
