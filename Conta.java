public abstract class Conta {
    protected double saldo = 0;
    protected Pessoa titular;

    public Conta(Pessoa pessoa) {
        this.titular = pessoa;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public double getSaldo() {
        return saldo;
    }
}
