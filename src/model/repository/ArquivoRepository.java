package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoRepository<T> {
    private String nomeArquivo;

    public ArquivoRepository(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void salvar(List<T> items) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(nomeArquivo))) {
            oos.writeObject(new ArrayList<>(items));
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> carregar() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(nomeArquivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

