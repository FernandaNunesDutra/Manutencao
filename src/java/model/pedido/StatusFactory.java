package model.pedido;

public final class StatusFactory {

    public static final int RECEBIDO = 1;
    public static final int EM_AVALIACAO = 2;
    public static final int EM_MANUTENCAO = 3;
    public static final int CONSERTADO = 4;
    public static final int SEM_CONSERTO = 5;
    public static final int CANCELADO = 6;
    public static final int AGUARDANDO_PECA = 7;

    public static StatusPedido getStatusPedido(int statusId){
        StatusPedido statusPedido;
        switch(statusId){
            case RECEBIDO:
                statusPedido = new StatusRecebido();
                break;
            case EM_AVALIACAO:
                statusPedido = new StatusAvaliar();
                break;
            case EM_MANUTENCAO:
                statusPedido = new StatusManutencao();
                break;
            case CONSERTADO:
                statusPedido = new StatusConsertado();
                break;
            case SEM_CONSERTO:
                statusPedido = new StatusSemConserto();
                break;
            case CANCELADO:
                statusPedido = new StatusCancelado();
                break;
            case AGUARDANDO_PECA:
                statusPedido = new StatusAguardandoPeca();
                break;
            default:
                throw new IllegalArgumentException("Status id inválido: " + statusId);
        }
        return statusPedido;
    }

}
