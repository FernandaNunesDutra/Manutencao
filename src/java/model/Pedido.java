/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author fernanda
 */
public class Pedido {
    
    private int codigo;
    private String cliente;
    private String aparelho;
    private Date dataRecebido;
    private StatusPedido status;

    public Pedido(String cliente, String aparelho) {
        this.cliente = cliente;
        this.aparelho = aparelho;
        this.dataRecebido = new Date();
        this.status = new StatusRecebido();
    }

    public Pedido(int codigo, String cliente, String aparelho, Date dataRecebido) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.aparelho = aparelho;
        this.dataRecebido = dataRecebido;
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getAparelho() {
        return aparelho;
    }

    public void setAparelho(String aparelho) {
        this.aparelho = aparelho;
    }

    public Date getDataRecebido() {
        return dataRecebido;
    }

    public void setDataRecebido(Date dataRecebido) {
        this.dataRecebido = dataRecebido;
    }
    
}
