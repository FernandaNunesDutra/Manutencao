package model.pedido;

public class MetodoPagamentoAVista implements MetodoPagamento {

    @Override
    public String imprimir() {
        return "À vista";
    }
}
