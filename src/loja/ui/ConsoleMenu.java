package loja.ui;
import java.util.Scanner;
public class ConsoleMenu {
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
                    
                    break;
                case 2:System.out.println("Opção de alterar produto");
                    break;
                case 3:System.out.println("Opção alterar cliente");
                    break;
                case 4:System.out.println("Opção criar nota da compra");
                    break;
                case 5:System.out.println("Opção listar produtos");
                    break;
                case 6:System.out.println("Opção listar clientes");
                    break;
                case 0:System.out.println("Opção De sair d");
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
        System.out.println("Criar nota de compras");
        System.out.println("Listar produtos");
        System.out.println("Listar Clientes");
        System.out.println("0-Sair"); 
        System.out.println("---------FIM----------");
    }

    public void cadastrarCliente(){

    }
}


