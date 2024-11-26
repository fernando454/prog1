public class Limpeza {
    public static void limpar(Funcionario funcionario, Quarto quarto) {
        System.out.println(
                "Funcionário " + funcionario.getNome() + " foi encaminhado para limpar o Quarto " + quarto.getNumero()
                        + ".");
        quarto.setLimpo(true);
        quarto.setDisponivel(true);
    }
}
