import java.util.ArrayList;
import java.util.Random;

public class Banco {
    ArrayList<Pessoa> pessoas = new ArrayList<>();
    ArrayList<ContaCorrente> contasCorrentes = new ArrayList<>();
    ArrayList<ContaPoupanca> contasPoupancas = new ArrayList<>();

    public void addPessoa(Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    public Pessoa encontrarPessoaPorCpf(String cpf) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf))
                return pessoa;
        }
        return null;
    }

    private void addContaCorrente(ContaCorrente contaCorrente) {
        contasCorrentes.add(contaCorrente);
    }

    private void addContaPoupanca(ContaPoupanca contaPoupanca) {
        contasPoupancas.add(contaPoupanca);
    }

    public void atribuirContaCorrente(Pessoa usuario) {
        String numero = gerarNumeroContaCorrente();
        while (numero == null) {
            numero = gerarNumeroContaCorrente();
        }
        ContaCorrente contaCorrente = new ContaCorrente(usuario, numero);
        usuario.setContaCorrente(contaCorrente);
        addContaCorrente(contaCorrente);
    }

    private String gerarNumeroContaCorrente() {
        Random random = new Random();
        String numero = String.format("%06d-12", random.nextInt(999999));
        if (!existeNumeroContaCorrente(numero)) {
            return numero;
        }
        return null;
    }

    private boolean existeNumeroContaCorrente(String numeroConta) {
        for (ContaCorrente contaCorrente : contasCorrentes) {
            if (numeroConta.equals(contaCorrente.numero)) {
                return true;
            }
        }
        return false;
    }

    public void atribuirContaPoupanca(Pessoa usuario) {
        String numero = gerarNumeroContaPoupanca();
        while (numero == null) {
            numero = gerarNumeroContaPoupanca();
        }
        ContaPoupanca contaPoupanca = new ContaPoupanca(usuario, numero);
        usuario.setContaPoupanca(contaPoupanca);
        addContaPoupanca(contaPoupanca);
    }

    private String gerarNumeroContaPoupanca() {
        Random random = new Random();
        String numero = String.format("%06d-10", random.nextInt(999999));
        if (!existeNumeroContaPoupanca(numero)) {
            return numero;
        }
        return null;
    }

    private boolean existeNumeroContaPoupanca(String numeroConta) {
        for (ContaPoupanca contaPoupanca : contasPoupancas) {
            if (numeroConta.equals(contaPoupanca.numero)) {
                return true;
            }
        }
        return false;
    }
}
