package entities.enums;

public enum OpcaoMenu {
    SAIR(0),
    CADASTRAR(1),
    MAIS_CAROS(2),
    LISTAR(3),
    REMOVER(4),
    LUCRO(5);

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
