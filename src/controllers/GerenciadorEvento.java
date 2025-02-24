import java.util.ArrayList;
import java.io.*;

public class GerenciadorEvento {
    private ArrayList<Participante> participantes;
    private ArrayList<Palestrante> palestrantes;
    private ArrayList<Evento> eventos;

    public GerenciadorEvento() {
        this.participantes = new ArrayList<>();
        this.palestrantes = new ArrayList<>();
        this.eventos = new ArrayList<>();
        carregarDados(); 
    }

    public void cadastrarParticipante(String nome, String email) {
        Participante participante = new Participante(nome, email);
        participantes.add(participante);
        salvarDados(); 
        System.out.println("Participante cadastrado com sucesso!");
    }

    public void emitirCertificado(String nomeParticipante) {
        boolean encontrado = false;
        for (Participante participante : participantes) {
            if (participante.getNome().equalsIgnoreCase(nomeParticipante)) {
                System.out.println("\nCertificado");
                System.out.println("===========================================");
                System.out.println("Certificamos que " + participante.getNome());
                System.out.println("participou do evento [Nome do Evento]");
                System.out.println("===========================================");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Participante não encontrado.");
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("dados_evento.dat"))) {
            oos.writeObject(participantes);
            oos.writeObject(palestrantes);
            oos.writeObject(eventos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("dados_evento.dat"))) {
            participantes = (ArrayList<Participante>) ois.readObject();
            palestrantes = (ArrayList<Palestrante>) ois.readObject();
            eventos = (ArrayList<Evento>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Arquivo ainda não existe ou erro na leitura
            System.out.println("Iniciando com dados vazios");
        }
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public ArrayList<Palestrante> getPalestrantes() {
        return palestrantes;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }
}
