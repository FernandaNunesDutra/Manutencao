package model.pedido;

public final class TipoDefeitoFactory {

    public static final String SIMPLES = "S";
    public static final String MEDIO = "M";
    public static final String DIFICIL = "D";
    public static final String MUITO_DIFICIL = "MD";
    
    public static TipoDefeito getTipoDefeitoPedido(String defeitoId){
        TipoDefeito tipoDefeitoPedido;
        
        switch(defeitoId){
            case SIMPLES:
                tipoDefeitoPedido = RolDefeitos.getInstance().getTipoDefeitoSimples();
                break;
            case MEDIO:
                tipoDefeitoPedido = RolDefeitos.getInstance().getTipoDefeitoMedio();
                break;
            case DIFICIL:
                tipoDefeitoPedido = RolDefeitos.getInstance().getTipoDefeitoDificil();
                break;
            case MUITO_DIFICIL:
                tipoDefeitoPedido = RolDefeitos.getInstance().getTipoDefeitoMuitoDificil();
                break;
            default:
                throw new IllegalArgumentException("Defeito id inválido: " + defeitoId);
        }
        return tipoDefeitoPedido;
    }

}
