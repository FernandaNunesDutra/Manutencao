package model.cliente;

public class ClienteJuridico extends Cliente {

    ClienteJuridico(int codigo, String nome) {
        super(codigo, nome);
    }

    ClienteJuridico(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return "PJ";
    }
}
