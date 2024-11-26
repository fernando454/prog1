import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Caixa {
    private Scanner scanner = new Scanner(System.in);
    private double extrato;
    private double valorFinal;
    private Map<String, Boolean> cuponsDisponiveis;

    public Caixa() {
        this.extrato = 0;
        this.valorFinal = 0;
        this.cuponsDisponiveis = new HashMap<>();
    }

    public double getExtrato() {
        return extrato;
    }

    public void receber(Quarto quarto, Funcionario funcionario) {
        double valor = quarto.getPreco();
        System.out.println("Houve consumo durante a estadia, informe o valor: ");
        double consumo = scanner.nextDouble();

        if (consumo >= 0) {
            valor += consumo;
            valorFinal = valor;

            if (valorFinal > 500) {
                String codigoCupom = gerarCodigoCupom();
                cuponsDisponiveis.put(codigoCupom, true);
                System.out.println("Parabéns! Você ganhou um cupom de desconto: " + codigoCupom);
            }

            System.out.println("Deseja aplicar um cupom? Digite o código do cupom ou '69' para ignorar:");

            scanner.nextLine();
            String entradaCupom;

            while (true) {
                entradaCupom = scanner.nextLine();

                if (entradaCupom.equals("69")) {
                    System.out.println("Cupom ignorado. Valor final permanece: R$ " + valorFinal);
                    break;
                } else if (cuponsDisponiveis.getOrDefault(entradaCupom, false)) {
                    cuponsDisponiveis.put(entradaCupom, false);
                    aplicarDesconto();
                    break;
                } else {
                    System.out.println("Código inválido ou já utilizado. Tente novamente ou digite '69' para ignorar.");
                }
            }

            extrato += valorFinal;
            System.out.println("Venda registrada. Valor da venda: R$ " + valorFinal);
        } else {
            System.out.println("Valor de consumo inválido. Somente valores positivos são aceitos.");
        }

        quarto.setLimpo(false);
        Limpeza.limpar(funcionario, quarto);

        valorFinal = 0;
    }

    private String gerarCodigoCupom() {
        int codigoAleatorio = (int) (Math.random() * 1_000_000_00);
        return "CUPOM" + codigoAleatorio;
    }

    private void aplicarDesconto() {
        if (valorFinal >= 500 && valorFinal <= 800) {
            valorFinal -= valorFinal * 0.10;
        } else if (valorFinal > 800 && valorFinal <= 1000) {
            valorFinal -= valorFinal * 0.20;
        } else if (valorFinal > 1000) {
            valorFinal -= valorFinal * 0.30;
        } else {
            valorFinal -= valorFinal * 0.05;
        }
        System.out.println("Cupom aplicado! Valor final: R$ " + valorFinal);
    }
}
