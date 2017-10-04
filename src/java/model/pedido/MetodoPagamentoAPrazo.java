package model.pedido;

public class MetodoPagamentoAPrazo implements MetodoPagamento {

    @Override
    public String imprimir() {
        return "A prazo";
    }
}
