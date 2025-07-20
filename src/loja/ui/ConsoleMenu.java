package loja.ui;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import loja.model.produto.*;
import loja.model.cliente.*;
import loja.model.nota.Nota;


public class ConsoleMenu {
    private List<Produto> produtosCadastrados = new ArrayList<>();
    private List<Cliente> clientesCadastrados = new ArrayList<>();
    private List<Nota> notasEmitidas = new ArrayList<>();
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
                    alterarCliente();
                    break;
                case 4:System.out.println("Opção criar nota da compra");

                    break;
                case 5:System.out.println("Opção listar produtos");
                    listarProdutos();
                    break;
                case 6:System.out.println("Opção listar clientes");
                    listarClientes();
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
        System.out.println("==========-Clientes cadastrados-==========");
        if(clientesCadastrados.isEmpty()){
            System.out.println("Nenhum cliente cadastrado");
        }else {
            int i=1;
            for(Cliente cliente : clientesCadastrados){
                System.out.println("\n\nCliente "+ i+" -===================");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Endereço: " + cliente.getEndereco());
                System.out.println("Telefone: " + cliente.getTelefone());
                i++;
                if(cliente instanceof PessoaFisica pf){
                    System.out.println("CPF: "+ pf.getCPF());
                }else if(cliente instanceof PessoaJuridica pj){
                    System.out.println("CNPJ: " + pj.getCNPJ());
                }
            }
        }
        System.out.println("\n==========-FIM DOS CLIENTES-==========");
    }

    public void listarProdutos(){
        System.out.println("==========-Produtos cadastrados-==========");
        if(produtosCadastrados.isEmpty()){
            System.out.println("Nenhum produto cadastrado");
        }else{
            DecimalFormat formatoMoeda = new DecimalFormat("R$ #,##0.00");
            int i =1;
            for(Produto produto : produtosCadastrados){
                System.out.println("Produto "+ i+" -==================");
                System.out.println("Nome: " + produto.getNome() );
                System.out.println("Codigo: " + produto.getCodigo());
                System.out.println("Preço base: "+ formatoMoeda.format(produto.getPreco()));
                System.out.println("Estoque: "+produto.getEstoque());

                if(produto instanceof ProdutoFisico pf){
                    System.out.println("Frente; " + formatoMoeda.format(pf.getPreco()));
                }else if(produto instanceof ProdutoDigital pd){
                    System.out.println("Link: " + pd.getLinkDoProduto());
                }else if(produto instanceof ProdutoPerecivel pp){
                    DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.println("Validade: "+ pp.getDataDeValidade().format(formatadorData));
                }
            }
        }
    }
    public void alterarCliente(){
        System.out.println("Alteração de dados do cliente");
        if(clientesCadastrados.isEmpty()){
            System.out.println("Não há clientes para alterar");
            return;  
        }
        listarClientes();
        System.out.println("Digite o número do cliente: ");
        int escCliente = scanner.nextInt();
        if(escCliente < 0 || escCliente >= clientesCadastrados.size()){
            System.out.println("ERRO SELEÇÃO INVÁLIDA");
            return;
        }
        Cliente clienteSelecionado = clientesCadastrados.get(escCliente);

        System.out.println("O que deseja altera?");
        System.out.println("1- Nome");
        System.out.println("2- Endereço");
        System.out.println("3- Telefone");
        System.out.print("Escolha uma opção: ");
        int opcaoC = scanner.nextInt();
        scanner.nextLine();
        switch (opcaoC) {
            case 1:
                String novoNome = InputUtils.lerString("Digite o novo nome: ");
                clienteSelecionado.setNome(novoNome);
                break;
            case 2:
                String novoEnd = InputUtils.lerString("Digite o novo Endereço: ");
                clienteSelecionado.setEndereco(novoEnd);
                break;
            case 3:
                String novoTel = InputUtils.lerString("Digite o novo telefone: ");
                clienteSelecionado.setTelefone(novoTel);
                break;
        
            default:
            System.out.println("Opção inválida");
            return;            
                
        }
        System.out.println("Cliente alterado com sucesso!");
    }

    public void criarNota(){
        System.out.println("Criando nota de compra");
        if(clientesCadastrados.isEmpty() || produtosCadastrados.isEmpty()){
            System.out.println("É necessario ter pelo menos um cliente e um produto");
            return;
        }
        listarClientes();
        System.out.print("Digite o número do cliente: ");
        int indCliente = scanner.nextInt();
        scanner.nextLine();
        if(indCliente < 0 || indCliente >= clientesCadastrados.size()){
            System.out.println("Erro de seleção");
            return;
        }
        Cliente clienteDaNota = clientesCadastrados.get(indCliente);
        Nota novaNota = new Nota(clienteDaNota);
        String finaliza = "";
        do {
            System.out.println("Adicionando a nota");
            listarProdutos();
            System.out.print("Digite o numero do produto que deseja adcionar: ");
            int indProduto = scanner.nextInt();
            scanner.nextLine();
            if(indProduto < 0 || indProduto >= produtosCadastrados.size()){
                System.out.println("Seleção invalida");
                continue;
            }
            Produto produtoDoCliente = produtosCadastrados.get(indProduto);

            System.out.println("Digite a quantidade: ");
            int quant = scanner.nextInt();
            scanner.nextLine();
            try {
                novaNota.adicionarItem(produtoDoCliente, quant);
                System.out.println("Produto adicinado");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao adiconar item " + e.getMessage());
            }
            System.out.println("Deseja adicionar outro produto? (S/N)");
            finaliza = scanner.nextLine();

        } while (finaliza.equalsIgnoreCase("S"));

        this.notasEmitidas.add(novaNota);
        System.out.println("Nota criada com sucesso");
    }
}


