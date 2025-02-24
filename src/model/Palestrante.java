package model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Palestrante implements Serializable {
    private String especialidade;
    private List<Evento> eventosPalestrando;

    public Palestrante(String nome, String email, String cpf, String especialidade) {
        super(nome, email, cpf);
        this.especialidade = especialidade;
        this.eventosPalestrando = new ArrayList<>();
    }

    public void adicionarEventoPalestra(Evento evento) {
        if (!eventosPalestrando.contains(evento)) {
            eventosPalestrando.add(evento);
            evento.adicionarPalestrante(this);
        }
    }

    public void removerEventoPalestra(Evento evento) {
        eventosPalestrando.remove(evento);
        evento.removerPalestrante(this);
    }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public List<Evento> getEventosPalestrando() { return new ArrayList<>(eventosPalestrando); }
}

