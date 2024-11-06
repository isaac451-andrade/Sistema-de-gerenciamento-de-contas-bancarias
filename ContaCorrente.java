public class ContaCorrente extends Conta  {
    protected String numero;
    public ContaCorrente(Pessoa pessoa, String numero){
        super(pessoa);
        this.numero = numero;

    }
    
    public double aplicarRendimento() {
        int quantidadeCemEmSaldo = (int) saldo / 100;
        double rendimento = saldo * (0.0005 *quantidadeCemEmSaldo);
        saldo += rendimento;
        return rendimento;
    }
}
