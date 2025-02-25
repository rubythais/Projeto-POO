package model;

import java.util.ArrayList;
impoimport java.io.Serializable;

public class Participante implements Serializable {
    private List<Evento> eventosInscritos;
    private List<Certificado> certificados;

    public Participante(String nome, String email, String cpf) {
        super(nome, email, cpf);
        this.eventosInscritos = new ArrayList<>();
        this.certificados = new ArrayList<>();
    }

    public void inscreverEmEvento(Evento evento) {
        if (!eventosInscritos.contains(evento)) {
            eventosInscritos.add(evento);
            evento.adicionarParticipante(this);
        }
    }

    public void cancelarInscricao(Evento evento) {
        eventosInscritos.remove(evento);
        evento.removerParticipante(this);
    }

    public void adicionarCertificado(Certificado certificado) {
        certificados.add(certificado);
    }

    public List<Evento> getEventosInscritos() {
        return new ArrayList<>(eventosInscritos);
    }

    public List<Certificado> getCertificados() {
        return new ArrayList<>(certificados);
    }
}

