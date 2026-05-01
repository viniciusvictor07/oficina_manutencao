package entities;

public class Servico {
    private String cliente;
    private String modelo;
    private Double precoConserto;

    public Servico(String cliente, String modelo, Double precoConserto) {
        this.cliente = cliente;
        this.modelo = modelo;
        this.precoConserto = precoConserto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecoConserto() {
        return precoConserto;
    }

    public void setPrecoConserto(Double precoConserto) {
        this.precoConserto = precoConserto;
    }
}
