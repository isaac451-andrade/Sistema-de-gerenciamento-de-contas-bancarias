public class Pessoa {
    private String nome;
    private int idade;
    private String cpf;
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;

    public Pessoa(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        if (this.contaCorrente == null)
            this.contaCorrente = contaCorrente;
        else {
            System.out.println("Usuário já tem conta corrente!");
        }
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        if (this.contaPoupanca == null)
            this.contaPoupanca = contaPoupanca;
        else {
            System.out.println("Usuário já tem conta Poupança!");
        }
    }

    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }

    public void exibirInfo() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade:  " + idade);
        System.out.println("Cpf:  " + cpf);
        System.out.println("Numero Conta Corrente: " + contaCorrente.numero);
        System.out.println("Numero Conta Poupanca: " + contaPoupanca.numero);
    }
}
