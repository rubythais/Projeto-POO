package model;

import interfaces.Emissivel;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Certificado implements Serializable {
    
    private String id;
    private Participante participante;
    private Evento evento;
    private LocalDateTime dataEmissao;

    public Certificado(Participante participante, Evento evento) {
        this.id = java.util.UUID.randomUUID().toString();
        this.participante = participante;
        this.evento = evento;
        this.dataEmissao = LocalDateTime.now();
    }

    @Override
    public void emitir() {
        System.out.println("Certificado emitido para " + participante.getNome() + 
                          " referente ao evento " + evento.getNome());
    }

   
    public String getId() { return id; }
    public Participante getParticipante() { return participante; }
    public Evento getEvento() { return evento; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
}

