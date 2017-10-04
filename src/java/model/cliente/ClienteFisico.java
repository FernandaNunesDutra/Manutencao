package model.cliente;

public class ClienteFisico extends Cliente {

    ClienteFisico(int codigo, String nome) {
        super(codigo, nome);
    }

    ClienteFisico(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return "PF";
    }
}
