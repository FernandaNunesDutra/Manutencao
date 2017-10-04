package model.pedido;

public class MetodoPagamentoFactory {

    public static final int TIPO_A_VISTA = 1;
    public static final int TIPO_A_PRAZO = 2;

    public static MetodoPagamento createMetodoPagamento(int tipo) {
        MetodoPagamento metodoPagamento;
        switch (tipo) {
            case TIPO_A_VISTA:
                metodoPagamento = new MetodoPagamentoAVista();
                break;
            case TIPO_A_PRAZO:
                metodoPagamento = new MetodoPagamentoAPrazo();
                break;
            default:
                throw new IllegalArgumentException("Tipo inválido de método de pagamento: " + tipo);
        }
        return metodoPagamento;
    }
}
