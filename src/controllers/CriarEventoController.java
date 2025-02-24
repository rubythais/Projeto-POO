package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CriarEventoController {
    @FXML private TextField txtNome;
    @FXML private TextField txtData;

    public void salvarEvento() {
        String nome = txtNome.getText();
        String data = txtData.getText();
        System.out.println("Evento criado: " + nome + " em " + data);
    }
}
