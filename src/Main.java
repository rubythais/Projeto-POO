import model.*;
import service.Relatorio;
import utils.Validador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Evento> eventos = new ArrayList<>();
    private static List<Participante> participantes = new ArrayList<>();
    private static List<Palestrante> palestrantes = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Eventos ===");
            System.out.println("1. Criar novo evento");
            System.out.println("2. Cadastrar participante");
            System.out.println("3. Cadastrar palestrante");
            System.out.println("4. Criar lote de ingressos");
            System.out.println("5. Vender ingresso");
            System.out.println("6. Gerar certificado");
            System.out.println("7. Gerar relatórios");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    criarEvento();
                    break;
                case 2:
                    cadastrarParticipante();
                    break;
                case 3:
                    cadastrarPalestrante();
                    break;
                case 4:
                    criarLoteIngressos();
                    break;
                case 5:
                    venderIngresso();
                    break;
                case 6:
                    gerarCertificado();
                    break;
                case 7:
                    menuRelatorios();
                    break;
                case 8:
                    System.out.println("Encerrando o sistema...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarEvento() {
        System.out.println("\n=== Criar Novo Evento ===");
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();

        System.out.print("Local: ");
        String local = scanner.nextLine();

        System.out.print("Capacidade máxima: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Data (dd/MM/yyyy HH:mm): ");
        String dataStr = scanner.nextLine();
        LocalDateTime data = LocalDateTime.parse(dataStr, 
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        Evento evento = new Evento(nome, data, local, capacidade);
        eventos.add(evento);
        System.out.println("Evento criado com sucesso! ID: " + evento.getId());
    }

    private static void cadastrarParticipante() {
        System.out.println("\n=== Cadastrar Participante ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();
        while (!Validador.validarEmail(email)) {
            System.out.println("Email inválido! Tente novamente: ");
            email = scanner.nextLine();
        }

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        while (!Validador.validarCPF(cpf)) {
            System.out.println("CPF inválido! Tente novamente: ");
            cpf = scanner.nextLine();
        }

        Participante participante = new Participante(nome, email, cpf);
        participantes.add(participante);
        System.out.println("Participante cadastrado com sucesso! ID: " + participante.getId());
    }

    private static void cadastrarPalestrante() {
        System.out.println("\n=== Cadastrar Palestrante ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();
        while (!Validador.validarEmail(email)) {
            System.out.println("Email inválido! Tente novamente: ");
            email = scanner.nextLine();
        }

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        while (!Validador.validarCPF(cpf)) {
            System.out.println("CPF inválido! Tente novamente: ");
            cpf = scanner.nextLine();
        }

        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();

        Palestrante palestrante = new Palestrante(nome, email, cpf, especialidade);
        palestrantes.add(palestrante);
        System.out.println("Palestrante cadastrado com sucesso! ID: " + palestrante.getId());
    }

    private static void criarLoteIngressos() {
        if (eventos.isEmpty()) {
            System.out.println("Não há eventos cadastrados!");
            return;
        }

        System.out.println("\n=== Criar Lote de Ingressos ===");
        System.out.println("Eventos disponíveis:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println(i + ". " + eventos.get(i).getNome());
        }

        System.out.print("Selecione o evento: ");
        int eventoIndex = scanner.nextInt();
        scanner.nextLine(); 

        if (eventoIndex >= 0 && eventoIndex < eventos.size()) {
            Evento evento = eventos.get(eventoIndex);

            System.out.print("Quantidade de ingressos: ");
            int quantidade = scanner.nextInt();

            System.out.print("Preço do ingresso: R$ ");
            double preco = scanner.nextDouble();

            evento.criarLote(quantidade, preco);
            System.out.println("Lote de ingressos criado com sucesso!");
        } else {
            System.out.println("Evento inválido!");
        }
    }

    private static void venderIngresso() {
        if (eventos.isEmpty() || participantes.isEmpty()) {
            System.out.println("É necessário ter eventos e participantes cadastrados!");
            return;
        }

        System.out.println("\n=== Vender Ingresso ===");
        System.out.println("Eventos disponíveis:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println(i + ". " + eventos.get(i).getNome());
        }

        System.out.print("Selecione o evento: ");
        int eventoIndex = scanner.nextInt();

        if (eventoIndex >= 0 && eventoIndex < eventos.size()) {
            Evento evento = eventos.get(eventoIndex);
            List<LoteIngresso> lotes = evento.getLotes();

            if (lotes.isEmpty()) {
                System.out.println("Não há lotes de ingressos disponíveis para este evento!");
                return;
            }

            System.out.println("\nLotes disponíveis:");
            for (int i = 0; i < lotes.size(); i++) {
                LoteIngresso lote = lotes.get(i);
                System.out.println(i + ". Preço: R$ " + lote.getPreco() + 
                                 " - Disponíveis: " + lote.getQuantidade());
            }

            System.out.print("Selecione o lote: ");
            int loteIndex = scanner.nextInt();

            if (loteIndex >= 0 && loteIndex < lotes.size()) {
                System.out.println("\nParticipantes:");
                for (int i = 0; i < participantes.size(); i++) {
                    System.out.println(i + ". " + participantes.get(i).getNome());
                }

                System.out.print("Selecione o participante: ");
                int participanteIndex = scanner.nextInt();

                if (participanteIndex >= 0 && participanteIndex < participantes.size()) {
                    Participante participante = participantes.get(participanteIndex);
                    LoteIngresso lote = lotes.get(loteIndex);

                    Ingresso ingresso = evento.venderIngresso(participante, lote);
                    if (ingresso != null) {
                        System.out.println("Ingresso vendido com sucesso! ID: " + ingresso.getId());
                    } else {
                        System.out.println("Não foi possível vender o ingresso!");
                    }
                } else {
                    System.out.println("Participante inválido!");
                }
            } else {
                System.out.println("Lote inválido!");
            }
        } else {
            System.out.println("Evento inválido!");
        }
    }

    private static void gerarCertificado() {
        if (eventos.isEmpty() || participantes.isEmpty()) {
            System.out.println("É necessário ter eventos e participantes cadastrados!");
            return;
        }

        System.out.println("\n=== Gerar Certificado ===");
        System.out.println("Eventos disponíveis:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println(i + ". " + eventos.get(i).getNome());
        }

        System.out.print("Selecione o evento: ");
        int eventoIndex = scanner.nextInt();

        if (eventoIndex >= 0 && eventoIndex < eventos.size()) {
            Evento evento = eventos.get(eventoIndex);
            List<Participante> participantesEvento = evento.getParticipantes();

            if (participantesEvento.isEmpty()) {
                System.out.println("Não há participantes neste evento!");
                return;
            }

            System.out.println("\nParticipantes do evento:");
            for (int i = 0; i < participantesEvento.size(); i++) {
                System.out.println(i + ". " + participantesEvento.get(i).getNome());
            }

            System.out.print("Selecione o participante: ");
            int participanteIndex = scanner.nextInt();

            if (participanteIndex >= 0 && participanteIndex < participantesEvento.size()) {
                Participante participante = participantesEvento.get(participanteIndex);
                Certificado certificado = evento.gerarCertificado(participante);
                if (certificado != null) {
                    certificado.emitir();
                    participante.adicionarCertificado(certificado);
                } else {
                    System.out.println("Não foi possível gerar o certificado!");
                }
            } else {
                System.out.println("Participante inválido!");
            }
        } else {
            System.out.println("Evento inválido!");
        }
    }

    private static void menuRelatorios() {
        if (eventos.isEmpty()) {
            System.out.println("Não há eventos cadastrados!");
            return;
        }

        System.out.println("\n=== Relatórios ===");
        System.out.println("1. Relatório de Participantes");
        System.out.println("2. Relatório de Ingressos Vendidos");
        System.out.println("3. Relatório de Palestrantes");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("\nEventos disponíveis:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println(i + ". " + eventos.get(i).getNome());
        }

        System.out.print("Selecione o evento: ");
        int eventoIndex = scanner.nextInt();

        if (eventoIndex >= 0 && eventoIndex < eventos.size()) {
            Evento evento = eventos.get(eventoIndex);

            switch (opcao) {
                case 1:
                    Relatorio.gerarRelatorioParticipantes(evento);
                    break;
                case 2:
                    Relatorio.gerarRelatorioIngressosVendidos(evento);
                    break;
                case 3:
                    Relatorio.gerarRelatorioPalestrantes(evento);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } else {
            System.out.println("Evento inválido!");
        }
    }
}

