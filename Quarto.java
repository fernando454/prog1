import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Quarto {
    private int numero;
    private TipoQuarto tipo;
    private double preco;
    private boolean disponivel;
    private boolean limpo;
    private String acessorios;
    private String nomeCliente;

    public Quarto(int numero, TipoQuarto tipo, double preco, String acessorios) {
        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
        this.acessorios = acessorios;
        this.disponivel = true;
        this.limpo = true;
    }

    public int getNumero() {
        return numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public double getPreco() {
        return preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void getAcessorios() {
        System.out.println(acessorios);
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean verificarDisponibilidade() {
        return disponivel;
    }

    public void reservar(String nomeCliente) {
        if (disponivel) {
            this.nomeCliente = nomeCliente;
            disponivel = false;

            LocalDateTime agora = LocalDateTime.now();
            LocalDateTime horaFim = agora.plusHours(2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            System.out.println("A reserva do quarto " + tipo + " com o valor de R$ " + preco +
                    ", está disponível a partir das " + agora.format(formatter) + " até as " +
                    horaFim.format(formatter) + ", reserva criada em nome de " + nomeCliente + ".");

        } else {
            System.out.println("O quarto " + numero + " já está reservado.");
        }
    }

    public boolean cancelarReserva() {
        if (!disponivel) {
            disponivel = true;
            this.nomeCliente = null;
            return true;
        }
        return false;
    }

    public void setLimpo(boolean limpo) {
        this.limpo = limpo;
    }
}