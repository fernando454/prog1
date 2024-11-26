public class Reserva {
    private Cliente cliente;
    private Quarto quarto;
    private boolean confirmada;

    public Reserva(Cliente cliente, Quarto quarto) {
        this.cliente = cliente;
        this.quarto = quarto;
        this.confirmada = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void confirmar() {
        if (quarto.isDisponivel()) {
            this.confirmada = true;
            quarto.setDisponivel(false);
            System.out.println("Reserva confirmada para o cliente " + cliente.getNome());
        } else {
            System.out.println("O quarto selecionado não está disponível.");
        }
    }

    public void cancelar() {
        if (confirmada) {
            this.confirmada = false;
            quarto.setDisponivel(true);
            System.out.println("Reserva cancelada para o cliente " + cliente.getNome());
        } else {
            System.out.println("A reserva já estava cancelada.");
        }
    }
}
