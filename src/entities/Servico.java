package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Servico {
    private Cliente cliente;
    private String modelo;
    private Double precoModelo;
    private Double precoConserto;
    private LocalDateTime data;

    public Servico(Cliente cliente, String modelo, Double precoModelo, LocalDateTime dataEntrada) {
        this.cliente = cliente;
        this.modelo = modelo;
        this.precoModelo = precoModelo;
        this.data = dataEntrada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
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

    public LocalDateTime getData() {
        return data;
    }
}