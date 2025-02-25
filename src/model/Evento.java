package model;

import interfaces.Validavel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento implements Validavel {
    private String id;
    private String nome;
    private LocalDateTime data;
    private String local;
    private int capacidadeMaxima;
    private List<Participante> participantes;
    private List<Palestrante> palestrantes;
    private List<LoteIngresso> lotes;
    private List<Ingresso> ingressos;

    public Evento(String nome, LocalDateTime data, String local, int capacidadeMaxima) {
        this.id = java.util.UUID.randomUUID().toString();
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.capacidadeMaxima = capacidadeMaxima;
        this.participantes = new ArrayList<>();
        this.palestrantes = new ArrayList<>();
        this.lotes = new ArrayList<>();
        this.ingressos = new ArrayList<>();
    }

    public boolean adicionarParticipante(Participante participante) {
        if (participantes.size() < capacidadeMaxima && !participantes.contains(participante)) {
            participantes.add(participante);
            return true;
        }
        return false;
    }

    public void removerParticipante(Participante participante) {
        participantes.remove(participante);
    }

    public void adicionarPalestrante(Palestrante palestrante) {
        if (!palestrantes.contains(palestrante)) {
            palestrantes.add(palestrante);
        }
    }

    public void removerPalestrante(Palestrante palestrante) {
        palestrantes.remove(palestrante);
    }

    public void criarLote(int quantidade, double preco) {
        LoteIngresso lote = new LoteIngresso(quantidade, preco, this);
        lotes.add(lote);
    }

    public Ingresso venderIngresso(Participante participante, LoteIngresso lote) {
        if (lote.verificarDisponibilidade()) {
            Ingresso ingresso = new Ingresso(lote.getPreco(), this, participante, lote);
            ingressos.add(ingresso);
            lote.diminuirQuantidade();
            this.adicionarParticipante(participante);
            participante.inscreverEmEvento(this);
            return ingresso;
        }
        return null;
    }

    public Certificado gerarCertificado(Participante participante) {
        if (participantes.contains(participante)) {
            return new Certificado(participante, this);
        }
        return null;
    }

    public boolean verificarParticipante(Participante participante) {
        return participantes.contains(participante);
    }

    @Override
    public boolean validar() {
        return nome != null && !nome.isEmpty() && 
               data != null && 
               local != null && !local.isEmpty() && 
               capacidadeMaxima > 0;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public List<Participante> getParticipantes() { return new ArrayList<>(participantes); }
    public List<Palestrante> getPalestrantes() { return new ArrayList<>(palestrantes); }
    public List<LoteIngresso> getLotes() { return new ArrayList<>(lotes); }
}
