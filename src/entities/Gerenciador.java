package entities;

import java.util.ArrayList;
import java.util.List;

public class Gerenciador {
    private List<Servico> servicos;

    public Gerenciador() {
        this.servicos = new ArrayList<>();
    }

    public void addServico(Servico servicoAdicionado) {
        this.servicos.add(servicoAdicionado);
        System.out.println("Serviço registrado com sucesso!");
    }

    public void removerServico(String servicoRemovido) {
        if (this.servicos.isEmpty()) {
            System.out.println("Não existe nenhum serviço aqui!");
            return;
        }
        boolean conseguiuRemover = servicos.removeIf(s -> s.getCliente().equalsIgnoreCase(servicoRemovido));
        if (conseguiuRemover) {
            System.out.println("Serviço removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o serviço.");
        }
    }

    public void listarTodos() {
        for (Servico s : this.servicos) {
            System.out.printf("Nome do cliente: %s | Nome do modelo: %s | Preço do conserto: R$ %.2f%n", s.getCliente(), s.getModelo(), s.getPrecoConserto());
        }
    }
}

