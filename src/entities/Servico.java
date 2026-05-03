package entities;

public class Servico {
    private String cliente;
    private String modelo;
    private Double precoModelo;
    private Double precoConserto;

    public Servico(String cliente, String modelo, Double precoModelo) {
        this.cliente = cliente;
        this.modelo = modelo;
        this.precoModelo = precoModelo;
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

    public Double getPrecoModelo() {
        return precoModelo;
    }

    public void setPrecoModelo(Double precoModelo) {
        this.precoModelo = precoModelo;
    }

    public Double getPrecoConserto() {
        return precoConserto;
    }

    public void setPrecoConserto(Double precoConserto) {
        this.precoConserto = precoConserto;
    }
}