package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainController {

    @FXML
    private void handleCriarEvento(ActionEvent event) {
        
        mostrarMensagem("Criar Evento", "Funcionalidade em desenvolvimento.");
    }

    @FXML
    private void handleCadastrarParticipante(ActionEvent event) {
       
        mostrarMensagem("Cadastrar Participante", "Funcionalidade em desenvolvimento.");
    }

    @FXML
    private void handleCadastrarPalestrante(ActionEvent event) {
        
        mostrarMensagem("Cadastrar Palestrante", "Funcionalidade em desenvolvimento.");
    }

    @FXML
    private void handleCriarLoteIngressos(ActionEvent event) {
        
        mostrarMensagem("Criar Lote de Ingressos", "Funcionalidade em desenvolvimento.");
    }

    @FXML
    private void handleVenderIngresso(ActionEvent event) {
       
        mostrarMensagem("Vender Ingresso", "Funcionalidade em desenvolvimento.");
    }

    @FXML
    private void handleGerarCertificado(ActionEvent event) {
        
        mostrarMensagem("Gerar Certificado", "Funcionalidade em desenvolvimento.");
    }

    @FXML
    private void handleGerarRelatorios(ActionEvent event) {
        /
        mostrarMensagem("Gerar Relatórios", "Funcionalidade em desenvolvimento.");
    }

    @FXML
    private void handleSair(ActionEvent event) {
        
        System.exit(0);
    }

    private void mostrarMensagem(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
