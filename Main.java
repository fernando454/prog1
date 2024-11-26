import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Funcionario funcionario = null;
        Caixa caixa = new Caixa();

        while (true) {
            System.out.println("\n=== Sistema de Reserva de Quartos ===");
            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();

            funcionario = new Funcionario(nome);
            System.out.println("Login bem-sucedido como: " + funcionario.getNome());
            break;
        }

        Quarto[] quartos = new Quarto[6];
        for (int i = 0; i < quartos.length; i++) {
            TipoQuarto tipo = TipoQuarto.values()[i % TipoQuarto.values().length];
            quartos[i] = new Quarto(i + 1, tipo, 100 + (i * 50), "Acessórios para " + tipo);
        }

        while (true) {
            System.out.println("\n=== Ações ===");
            System.out.println("1. Realizar reserva de quarto");
            System.out.println("2. Verificar disponibilidade do quarto");
            System.out.println("3. Cancelar reserva");
            System.out.println("4. Receber");
            System.out.println("5. Extrato");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção (1-6): ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente para reserva: ");
                    String nomeClienteReserva = scanner.nextLine();

                    System.out.println("======= Escolha o número do quarto que deseja reservar: =======");

                    for (Quarto quarto : quartos) {
                        System.out.println(quarto.getNumero() + ". Tipo: " + quarto.getTipo() + ", Preço: R$ "
                                + quarto.getPreco());
                    }
                    int numeroQuartoEscolhaReserva = scanner.nextInt();
                    scanner.nextLine();
                    Quarto quartoEscolhidoReserva = quartos[numeroQuartoEscolhaReserva - 1];
                    quartoEscolhidoReserva.reservar(nomeClienteReserva);
                    break;
                case 2:
                    System.out.println("\n=== Verificar Disponibilidade ===");
                    System.out.println("Escolha o número do quarto que deseja verificar:");
                    for (Quarto quarto : quartos) {
                        System.out.println(quarto.getNumero() + ". Tipo: " + quarto.getTipo());
                    }
                    int numeroQuartoEscolha = scanner.nextInt();
                    scanner.nextLine();
                    Quarto quartoEscolhido = quartos[numeroQuartoEscolha - 1];

                    if (quartoEscolhido.verificarDisponibilidade()) {
                        System.out.println("Quarto " + quartoEscolhido.getNumero() + " disponível!");
                    } else {
                        System.out.println("Quarto " + quartoEscolhido.getNumero() + " indisponível.");
                    }
                    break;
                case 3:
                    System.out.println("=== Escolha o número do quarto que deseja cancelar a reserva: ===");
                    for (Quarto quarto : quartos) {
                        System.out.println(quarto.getNumero() + ". Tipo: " + quarto.getTipo() + ", Reservado: "
                                + (quarto.verificarDisponibilidade() ? "Não" : "Sim"));
                    }
                    int numeroQuartoEscolhaCancelamento = scanner.nextInt();
                    scanner.nextLine();

                    Quarto quartoEscolhidoCancelamento = quartos[numeroQuartoEscolhaCancelamento - 1];

                    if (!quartoEscolhidoCancelamento.verificarDisponibilidade()) {
                        quartoEscolhidoCancelamento.cancelarReserva();
                        System.out.println("Reserva do quarto " + quartoEscolhidoCancelamento.getNumero()
                                + " cancelada com sucesso!");
                    } else {
                        System.out
                                .println("Quarto " + quartoEscolhidoCancelamento.getNumero() + " não está reservado.");
                    }
                    break;
                case 4:
                    System.out.println("Qual Quarto será pago: ");
                    for (Quarto quarto : quartos) {
                        System.out.println(quarto.getNumero() + ". Tipo: " + quarto.getTipo());
                    }
                    int numeroQuartoReceber = scanner.nextInt();
                    scanner.nextLine();
                    Quarto quartoReceber = quartos[numeroQuartoReceber - 1];

                    if (!quartoReceber.isDisponivel()) {
                        caixa.receber(quartoReceber, funcionario);
                    } else {
                        System.out.println(
                                "O quarto " + quartoReceber.getNumero() + " não está ocupado e não pode ser pago.");
                    }
                    break;

                case 5:
                    System.out.println("Valor acumulado em caixa (total do dia): R$ " + caixa.getExtrato());
                    break;

                case 6:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        }
    }
}