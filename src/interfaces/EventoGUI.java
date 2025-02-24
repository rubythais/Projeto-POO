import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EventoGUI extends Application {
    private GerenciadorEvento gerenciador;
    private TextArea logArea;

    @Override
    public void start(Stage primaryStage) {
        gerenciador = new GerenciadorEvento();
        
        primaryStage.setTitle("Sistema de Gerenciamento de Eventos");
        
        TabPane tabPane = new TabPane();
        
        Tab participantesTab = new Tab("Participantes");
        participantesTab.setClosable(false);
        participantesTab.setContent(criarTelaParticipantes());
        
        Tab certificadosTab = new Tab("Certificados");
        certificadosTab.setClosable(false);
        certificadosTab.setContent(criarTelaCertificados());
        
        tabPane.getTabs().addAll(participantesTab, certificadosTab);
        

        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefRowCount(5);
        
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));
        mainLayout.getChildren().addAll(tabPane, new Label("Log de Operações:"), logArea);
        
        Scene scene = new Scene(mainLayout, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox criarTelaParticipantes() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome do Participante");
        
        TextField emailField = new TextField();
        emailField.setPromptText("Email do Participante");
        
        Button cadastrarButton = new Button("Cadastrar Participante");
        cadastrarButton.setOnAction(e -> {
            String nome = nomeField.getText().trim();
            String email = emailField.getText().trim();
            
            if (!nome.isEmpty() && !email.isEmpty()) {
                gerenciador.cadastrarParticipante(nome, email);
                logArea.appendText("Participante cadastrado: " + nome + "\n");
                nomeField.clear();
                emailField.clear();
            } else {
                logArea.appendText("Erro: Preencha todos os campos!\n");
            }
        });

        ListView<String> participantesList = new ListView<>();
        atualizarListaParticipantes(participantesList);

        layout.getChildren().addAll(
            new Label("Nome:"), nomeField,
            new Label("Email:"), emailField,
            cadastrarButton,
            new Label("Participantes Cadastrados:"),
            participantesList
        );

        return layout;
    }

    private VBox criarTelaCertificados() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField nomeBuscaField = new TextField();
        nomeBuscaField.setPromptText("Nome do Participante");

        Button emitirButton = new Button("Emitir Certificado");
        emitirButton.setOnAction(e -> {
            String nome = nomeBuscaField.getText().trim();
            if (!nome.isEmpty()) {
                gerenciador.emitirCertificado(nome);
                logArea.appendText("Tentativa de emissão de certificado para: " + nome + "\n");
                nomeBuscaField.clear();
            } else {
                logArea.appendText("Erro: Digite o nome do participante!\n");
            }
        });

        layout.getChildren().addAll(
            new Label("Nome do Participante:"),
            nomeBuscaField,
            emitirButton
        );

        return layout;
    }

    private void atualizarListaParticipantes(ListView<String> listView) {
        listView.getItems().clear();
        for (Participante p : gerenciador.getParticipantes()) {
            listView.getItems().add(p.getNome() + " - " + p.getEmail());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
