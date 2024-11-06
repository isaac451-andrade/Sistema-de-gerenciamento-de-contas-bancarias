import java.util.Scanner;

public class Sistemas {
    private static Scanner input = new Scanner(System.in);
    private static Banco banco = new Banco();

    public void menuInicial() {
        while (true) {
            int opcaoMenu = getOpcaoMenu("\n[ 1 ] Cadastrar Usuário\n[ 2 ] Login\n[ 3 ] Sair\nEscolha uma opção: ",
                    new int[] { 1, 2, 3 });

            if (opcaoMenu == 1) {
                menuCadastro();
            } else if (opcaoMenu == 2) {
                menuLogado();
            } else {
                System.out.println("Sistema Finalizado com sucesso!");
                break;
            }

        }

    }

    private void menuCadastro() {
        System.out.println("Olá! Eu sou o sistema LV e estou aqui para lhe guiar por seu cadastro!\n");
        while (true) {
            String nome = obterInputString("Digite seu nome: ");
            int idade = obterIdadeValidado("Digite sua idade: ");
            String cpf = obterCpfValidado("Digite seu cpf sem pontos e hifens: ");

            Pessoa usuario = banco.encontrarPessoaPorCpf(cpf);
            if (usuario == null) {
                usuario = new Pessoa(nome, idade, cpf);
                banco.addPessoa(usuario);

                banco.atribuirContaCorrente(usuario);
                banco.atribuirContaPoupanca(usuario);

                System.out.println("Usuário cadastrado com sucesso!");
                System.out.printf("\n| Conta Corrente %s e Conta Poupanca %s já criadas |",
                        usuario.getContaCorrente().numero, usuario.getContaPoupanca().numero);
                System.out.println();        
                break;
            } else {
                int sair = getOpcaoMenu("CPF já cadastrado! Quer tentar novamente? [ 1 ] Sim [ 2 ] Sair\nOpção: ",
                        new int[] { 1, 2 });
                if (sair == 2) {
                    break;
                }
            }
        }
    }

    private void menuLogado() {
        System.out.println("Olá! Eu sou o sistema LV e estou aqui para lhe ajudar no seu login!\n");
        while (true) {
            String cpf = obterCpfValidado("Digite seu cpf sem pontos e hifens: ");

            Pessoa usuario = banco.encontrarPessoaPorCpf(cpf);
            if (usuario != null) {
                System.out.println("Login feito com sucesso!");
                System.out.println();
                menuPrincipal(usuario);
                break;
            } else {
                int sair = getOpcaoMenu("CPF não encontrado! Quer tentar novamente? [ 1 ] Sim [ 2 ] Sair\nOpção: ",
                        new int[] { 1, 2 });
                if (sair == 2) {
                    break;
                }
            }

        }

    }

    private void menuPrincipal(Pessoa usuario) {
        System.out.printf("Bem vindo, %s! ", usuario.getNome());
        System.out.println();
        System.out.println();
        while(true){
            int opcaoMenuPrincipal = getOpcaoMenu("[ 1 ] Exibir suas Informações\n[ 2 ] Gerenciar Conta Corrente\n[ 3 ] Gerenciar Conta Poupanca\n[ 4 ] Sair\nOpção: ", new int[]{1, 2, 3, 4});
            System.out.println();
            if(opcaoMenuPrincipal == 1){
                System.out.println("=--------------------------------=");
                usuario.exibirInfo();
                System.out.println("=--------------------------------=");
            }
            else if (opcaoMenuPrincipal == 2){
                gerenciaContacorrente(usuario);

            }else if(opcaoMenuPrincipal == 3){
                gerenciaContaPoupanca(usuario);

            }else {
                System.out.println("Logout feito com sucesso!");
                break;
            }
            System.out.println();
        }
    }

    private void gerenciaContacorrente(Pessoa usuario){
        ContaCorrente contaCorrente = usuario.getContaCorrente();
        System.out.printf("Conta Corrente: %s", contaCorrente.numero);

        while (true) {
            
            int opcao = getOpcaoMenu("\n[ 1 ] Ver saldo\n[ 2 ] Depositar\n[ 3 ] Aplicar rendimentos \n[ 4 ] Transferir Para Poupança\n[ 5 ] Sair\nOpção: ", new int[]{1, 2, 3, 4, 5});
            if(opcao == 1){
                System.out.println("$------------------------$");
                System.out.printf("   Saldo: R$%.2f\n", contaCorrente.saldo);
                System.out.println("$------------------------$");
                System.out.println();
            }
            else if(opcao == 2){
                double deposito = obterDepositoValidado("Digite o valor do deposito: R$");
                contaCorrente.saldo += deposito;
                System.out.println("Deposito efetuado!");
                System.out.println();
            }
            else if(opcao == 3){
                double rendimento = contaCorrente.aplicarRendimento();
                if(rendimento ==0){
                    System.out.println("Nenhum rendimento!");
                }else{
                    System.out.printf("Rendimento de R$%.2f aplicado sobre seu saldo!", rendimento);
                }
                System.out.println();
            }
            else if(opcao == 4){
                ContaPoupanca contaPoupanca = usuario.getContaPoupanca();

                double valorATransferir = obterDepositoValidado("Digite o valor da transferência: R$");
                contaPoupanca.transferirDaContaCorrente(contaCorrente, valorATransferir);
                System.out.println();
            }
            else if(opcao== 5){
                break;
            }

        }    
        System.out.println();
    }

    private void gerenciaContaPoupanca(Pessoa usuario){
        ContaPoupanca contaPoupanca = usuario.getContaPoupanca();
        System.out.printf("Conta Poupança: %s", contaPoupanca.numero);

        while (true) {
            int opcao = getOpcaoMenu("\n[ 1 ] Ver saldo\n[ 2 ] Transferir Para Conta Corrente\n[ 3 ] Sair\nOpção: ", new int[]{1, 2, 3});

            if(opcao == 1){
                System.out.println("$------------------------$");
                System.out.printf("   Saldo: R$%.2f\n", contaPoupanca.saldo);
                System.out.println("$------------------------$");
                System.out.println();
            }
            else if(opcao == 2){
                ContaCorrente contaCorrente = usuario.getContaCorrente();
                double valorATransferir = obterDepositoValidado("Digite o valor da transferência: R$");
                contaPoupanca.transferirParaContaCorrente(contaCorrente, valorATransferir);
                System.out.println();

            } else{
                break;
            }
        }

    }

    // Utilitários:

    private int getOpcaoMenu(String mensagem, int[] listaOpcoes) {
        int indice;

        while (true) {
            try {
                System.out.print(mensagem);
                indice = Integer.parseInt(input.nextLine());
                if (!checarOpcaoMenu(indice, listaOpcoes)) {
                    System.out.println("Valor fora do intervalo de opção!");
                    System.out.println();
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido!");
            }
        }
        return indice;
    }

    private boolean checarOpcaoMenu(int indice, int[] listaOpcoes) {
        for (int i : listaOpcoes) {
            if (indice == i) {
                return true;
            }
        }
        return false;

    }

    private String obterCpfValidado(String mensagemInicial) {
        System.out.print(mensagemInicial);
        String cpf = input.nextLine();
        while (cpf.length() != 11 || !isNumerico(cpf)) {
            System.out.print("VALOR INVÁLIDO! Seu cpf é menor do que 11 dígitos ou possui caracteres especiais! CPF: ");
            cpf = input.nextLine();
        }
        System.out.println();
        return cpf;
    }

    private double obterDepositoValidado(String mensagem){
        double deposito;
        while(true){
            try{
                System.out.print(mensagem);
                deposito = Double.parseDouble(input.nextLine());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Valor inválido!");
            }
        }
        return deposito;

    }

    private boolean isNumerico(String valor) {
        if (valor == null || valor.isEmpty()) {
            return false;
        }

        for (int pos = 0; pos < valor.length(); pos++) {
            if (!Character.isDigit(valor.charAt(pos))) {
                return false;
            }
        }
        return true;
    }

    private String obterInputString(String mensagem) {
        System.out.print(mensagem);
        String valor = input.nextLine();
        System.out.println();
        return valor;
    }

    private int obterIdadeValidado(String mensagem) {
        int idade;
        while (true) {
            try {
                System.out.print(mensagem);
                idade = Integer.parseInt(input.nextLine());
                if (idade < 18) {
                    System.out.println("Idade insuficiente para abertura de conta!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido!");
            }
        }
        System.out.println();
        return idade;

    }

}
