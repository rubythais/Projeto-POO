package model;
import java.io.Serializable;

public class LoteIngresso implements Serializable {
    private String id;
    private int quantidade;
    private double preco;
    private Evento evento;

    public LoteIngresso(int quantidade, double preco, Evento evento) {
        this.id = java.util.UUID.randomUUID().toString();
        this.quantidade = quantidade;
        this.preco = preco;
        this.evento = evento;
    }

    public boolean verificarDisponibilidade() {
        return quantidade > 0;
    }

    public void diminuirQuantidade() {
        if (quantidade > 0) {
            quantidade--;
        }
    }


    public String getId() { return id; }
    public int getQuantidade() { return quantidade; }
    public double getPreco() { return preco; }
    public Evento getEvento() { return evento; }
}

