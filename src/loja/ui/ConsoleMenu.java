package loja.ui;
import java.math.BigDecimal;
import java.util.*;
import loja.model.produto.*;
import loja.model.cliente.*;
import java.math.BigDecimal;

public class ConsoleMenu {
    private List<Produto> produtosCadastrados = new ArrayList<>();
    private List<Cliente> clientesCadastrados = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar(){
        int opcao;
        do {
            exibirmenu();
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:System.out.println("Opção de cadastrar Produtop");
                    cadastrarProduto();
                    break;
                case 2:System.out.println("Opção cadastrar Cliente");
                    cadastrarCliente();
                    break;
                case 3:System.out.println("Opção alterar cliente");

                    break;
                case 4:System.out.println("Opção criar nota da compra");
                    break;
                case 5:System.out.println("Opção listar produtos");
                    listarClientes();
                    break;
                case 6:System.out.println("Opção listar clientes");
                    break;
                case 0:System.out.println("Opção De sair");
                    break;
                default:
                    break;
            }

        } while (opcao != 0);
    }

    public void exibirmenu(){
        System.out.println("---------MENU---------");
        System.out.println("1-Cadastrar Produto");
        System.out.println("2-Cadastrar Cliente");
        System.out.println("3-Alterar Cliente");
        System.out.println("4-Criar nota de compras");
        System.out.println("5-Listar produtos");
        System.out.println("6-Listar Clientes");
        System.out.println("0-Sair"); 
        System.out.println("---------FIM----------");
    }

    public void cadastrarCliente(){
        String id       = InputUtils.lerString("Digite o ID: ");
        String nome     = InputUtils.lerString("Digite o nome: ");
        String endereco = InputUtils.lerString("Digite o endereço: ");
        String telefone = InputUtils.lerString("Digite o telefone: ");
        System.out.println("Cliente Cadastrado");
        System.out.println("Nome: "+ nome +" Endereço: "+endereco+" Telefone: "+ telefone);
        Cliente novoCliente = new Cliente(id,nome,endereco,telefone);
        this.clientesCadastrados.add(novoCliente);
    }
    public void cadastrarProduto(){
        String nomeProduto = InputUtils.lerString("Digite o nome do produto: ");
        String codigo      = InputUtils.lerString("Digite o codigo do produto: ");
        BigDecimal preco   = InputUtils.lerPreco("Digite o valor do produto: ");
        BigDecimal estoque = InputUtils.lerPreco("Digite o estoque desse produto: ");

        Produto novoProduto = new Produto(codigo, nomeProduto, preco, estoque);
        this.produtosCadastrados.add(novoProduto);
    }
    public void listarClientes(){
        System.out.println("====-Clientes cadastrados-====");
        if(clientesCadastrados.isEmpty()){
            System.out.println("Nenhum cliente cadastrado");
        }else {
            for(Cliente cliente : clientesCadastrados){
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Endereço: " + cliente.getEndereco());
                System.out.println("Telefone: " + cliente.getTelefone());

                if(cliente instanceof PessoaFisica){
                    PessoaFisica pf = (PessoaFisica) cliente;
                    System.out.println("CPF: "+ pf.getCPF());
                }else if(cliente instanceof PessoaJuridica){
                    PessoaJuridica pj = (PessoaJuridica) cliente;
                    System.out.println("CNPJ: " + pj.getCNPJ());
                }
            }
        }
        System.out.println("====-FIM DOS CLIENTES-====");
    }

}


