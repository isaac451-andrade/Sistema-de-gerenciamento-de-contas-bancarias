public class ContaPoupanca extends Conta {
    protected String numero;
    public ContaPoupanca(Pessoa pessoa, String numero){
        super(pessoa);
        this.numero = numero;
    }

    public void transferirDaContaCorrente(ContaCorrente contaCorrente, double valor) {
        if (valor <= contaCorrente.getSaldo()) {
            contaCorrente.sacar(valor);
            this.depositar(valor);
            System.out.println("Valor transferido com sucesso!");
        } else {
            System.out.println("Saldo insuficiente na conta corrente para transferir.");
        }
    }

    public void transferirParaContaCorrente(ContaCorrente contaCorrente, double valor){
        if(valor <= saldo){
            this.sacar(valor);
            contaCorrente.depositar(valor);
            System.out.println("Valor transferido com sucesso!");
        } else {
            System.out.println("Saldo insuficiente na conta poupança para transferir.");
        }
    }
}