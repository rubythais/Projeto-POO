package model;

import interfaces.Validavel;
import java.io.Serializable;

public class Ingresso implements Serializable {
    private String id;
    private double preco;
    private Evento evento;
    private Participante participante;
    private LoteIngresso lote;
    private boolean valido;

    public Ingresso(double preco, Evento evento, Participante participante, LoteIngresso lote) {
        this.id = java.util.UUID.randomUUID().toString();
        this.preco = preco;
        this.evento = evento;
        this.participante = participante;
        this.lote = lote;
        this.valido = true;
    }

    @Override
    public boolean validar() {
        return valido && evento != null && participante != null;
    }

    // Getters e Setters
    public String getId() { return id; }
    public double getPreco() { return preco; }
    public Evento getEvento() { return evento; }
    public Participante getParticipante() { return participante; }
    public boolean isValido() { return valido; }
    public void setValido(boolean valido) { this.valido = valido; }
}

