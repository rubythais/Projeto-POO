import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import service.Relatorio;
import utils.Validador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventoGUI extends Application {
    private List<Evento> eventos = new ArrayList<>();
    private List<Participante> participantes = new ArrayList<>();
    private List<Palestrante> palestrantes = new ArrayList<>();
    private TextArea logArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Gerenciamento de Eventos");
        
        TabPane tabPane = new TabPane();
        
        Tab eventosTab = new Tab("Eventos");
        eventosTab.setContent(criarTelaEventos());
        
        Tab participantesTab = new Tab("Participantes");
        participantesTab.setContent(criarTelaParticipantes());
        
        Tab palestrantesTab = new Tab("Palestrantes");
        palestrantesTab.setContent(criarTelaPalestrantes());
        
        Tab ingressosTab = new Tab("Ingressos");
        ingressosTab.setContent(criarTelaIngressos());
        
        Tab certificadosTab = new Tab("Certificados");
        certificadosTab.setContent(criarTelaCertificados());
        
        Tab relatoriosTab = new Tab("Relatórios");
        relatoriosTab.setContent(criarTelaRelatorios());
        
        tabPane.getTabs().addAll(eventosTab, participantesTab, palestrantesTab, ingressosTab, certificadosTab, relatoriosTab);
        
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefRowCount(5);
        
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));
        mainLayout.getChildren().addAll(tabPane, new Label("Log de Operações:"), logArea);
        
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox criarTelaEventos() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome do Evento");
        
        TextField localField = new TextField();
        localField.setPromptText("Local do Evento");
        
        TextField capacidadeField = new TextField();
        capacidadeField.setPromptText("Capacidade Máxima");
        
        TextField dataField = new TextField();
        dataField.setPromptText("Data (dd/MM/yyyy HH:mm)");
        
        Button criarButton = new Button("Criar Evento");
        criarButton.setOnAction(e -> {
            try {
                String nome = nomeField.getText();
                String local = localField.getText();
                int capacidade = Integer.parseInt(capacidadeField.getText());
                LocalDateTime data = LocalDateTime.parse(dataField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                
                Evento evento = new Evento(nome, local, capacidade, data);
                eventos.add(evento);
                logArea.appendText("Evento criado: " + nome + "\n");
                
                nomeField.clear();
                localField.clear();
                capacidadeField.clear();
                dataField.clear();
            } catch (Exception ex) {
                logArea.appendText("Erro ao criar evento: " + ex.getMessage() + "\n");
            }
        });

        ListView<String> eventosList = new ListView<>();
        atualizarListaEventos(eventosList);

        layout.getChildren().addAll(
            new Label("Nome:"), nomeField,
            new Label("Local:"), localField,
            new Label("Capacidade:"), capacidadeField,
            new Label("Data:"), dataField,
            criarButton,
            new Label("Eventos Cadastrados:"),
            eventosList
        );

        return layout;
    }



    private void atualizarListaEventos(ListView<String> listView) {
        listView.getItems().clear();
        for (Evento e : eventos) {
            listView.getItems().add(e.getNome() + " - " + e.getLocal() + " - " + e.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
