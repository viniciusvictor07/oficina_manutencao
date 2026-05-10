package entities;

public enum OpcaoMenu {
    SAIR(0),
    CADASTRAR(1),
    LISTAR(2),
    REMOVER(3),
    LUCRO(4),
    MAIS_CAROS(5);

    private final int codigo;

    OpcaoMenu(int codigoInformado) {
        this.codigo = codigoInformado;
    }

    public int getCodigo() {
        return codigo;
    }

    public static OpcaoMenu buscarporCodigo(int codigoDigitado) {
        for (OpcaoMenu opcao : OpcaoMenu.values()) {
            if (opcao.getCodigo() == codigoDigitado) {
                return opcao;
            }
        }
        return null;
    }
}
