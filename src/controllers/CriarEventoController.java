package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Evento;

public class CriarEventoController {
    @FXML private TextField txtNome;
    @FXML private TextField txtData;

    public void salvarEvento() {
        String nome = txtNome.getText();
        String data = txtData.getText();

        Evento evento = new Evento(nome, data);
        System.out.println("Evento criado: " + evento.getNome() + " em " + evento.getData());
    }
}
