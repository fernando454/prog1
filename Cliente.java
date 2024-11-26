public class Cliente extends Usuario {
    private String telefoneCliente;

    public Cliente(String nome, String telefoneCliente) {
        this.nome = nome;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public void realizarReserva() {
        System.out.println("Reserva realizada pelo cliente: " + nome);
    }
}
