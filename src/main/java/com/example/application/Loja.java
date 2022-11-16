package com.example.application;

import java.util.Random;
import java.util.Scanner;

import com.example.application.dto.pedido.ItemPedidoDTO;
import com.example.application.interfaces.DI.AccountInjector;
import com.example.application.resources.PaginaProdutos;
import com.example.application.services.ServiceCart;
import com.example.application.services.ServicePedido;
import com.example.models.loja.pedido.Pedido;

public class Loja {

   private String option;
   private String select;
   private Scanner scanner;

   private PaginaProdutos paginaProdutos;
   private ServiceCart carrinho;
   private ServicePedido servicePedido;
   private AccountInjector accountInjector;
   private AccountPF accountPF;
   private AccountPJ accountPJ;
   private boolean isPessoaFisica;

   public Loja(PaginaProdutos paginaProdutos, ServiceCart carrinho, ServicePedido servicePedido,
         AccountInjector accountInjector) {
      this.paginaProdutos = paginaProdutos;
      this.carrinho = carrinho;
      this.servicePedido = servicePedido;
      this.accountInjector = accountInjector;
   }

   public void run() {

      paginaProdutos.seed();
      scanner = new Scanner(System.in);

      do {
         menu();
         select = scanner.nextLine();
         System.out.printf("\033c");

         switch (select) {
            case "1":
               addProductToCart();
               break;
            case "2":
               updateCartProduct();
               break;
            case "3":
               removeProductFromCart();
               break;
            case "4":
               viewCart();
               break;
            case "5":
               carrinho.removeAll();
               break;
            case "6":
               startAccountService();

               if (isPessoaFisica) {
                  accountPF.logIn(option);
               } else {
                  accountPJ.logIn(option);
               }
               break;
            case "7":
               startAccountService();

               if (isPessoaFisica) {
                  accountPF.singIn(option);
               } else {
                  accountPJ.singIn(option);
               }
               break;
            case "8":
               closeOrder();
               break;
            case "9":
               checkIfAccountServiceStarted();

               if (accountPF != null && isPessoaFisica) {
                  if (accountPF.getUser() == null) {
                     accountPF.logIn(option);
                  }

                  accountPF.userAccount();
               }

               if (accountPJ != null && !isPessoaFisica) {
                  if (accountPJ.getUser() == null) {
                     accountPJ.logIn(option);
                  }

                  accountPJ.userAccount();
               }
               break;
            case "10":
               checkIfAccountServiceStarted();

               if (isPessoaFisica) {
                  accountPF.logOut();
               } else {
                  accountPJ.logOut();
               }
            case "0":
               select = "exit";
               break;
            default:
               System.out.println("Opção inválida.");
               break;
         }

         scanner.nextLine();
      } while (!select.equals("exit"));

      scanner.close();
   }

   private void startAccountService() {
      System.out.print("Informe seu CPF/CNPJ: ");
      option = scanner.nextLine();

      switch (option.length()) {
         case 14:
            if (accountPF == null) {
               accountPF = accountInjector.getAccountPF(servicePedido);
            }

            isPessoaFisica = true;
            break;

         case 18:
            if (accountPJ == null) {
               accountPJ = accountInjector.getAccountPJ(servicePedido);
            }

            isPessoaFisica = false;
            break;
         default:
            break;
      }
   }

   private void checkIfAccountServiceStarted() {
      if (accountPF == null && accountPJ == null) {
         startAccountService();
      }
   }

   public void addProductToCart() {
      paginaProdutos.listarTodos();

      System.out.print("Escolha o produto que deseja: ");
      this.option = scanner.nextLine();
      int prodOption = Integer.parseInt(this.option);

      System.out.print("\nInforme a quantidade: ");
      this.option = scanner.nextLine();
      int prodQuant = Integer.parseInt(this.option);

      ItemPedidoDTO item = paginaProdutos.selecionarProduto(prodOption, prodQuant);
      carrinho.executa(item.criarItemPedido());
   }

   public void updateCartProduct() {
      if (yourCart()) {
         carrinho.getAllItems();

         System.out.print("Selecione o produto que deseja: ");
         this.option = scanner.nextLine();
         String uuid = this.option;

         System.out.print("\nInforme a quantidade: ");
         this.option = scanner.nextLine();
         int prodQuant = Integer.parseInt(this.option);

         carrinho.updateItem(uuid, prodQuant);
      }
   }

   public void removeProductFromCart() {
      if (yourCart()) {
         carrinho.getAllItems();

         System.out.print("Selecione o produto que deseja: ");
         this.option = scanner.nextLine();

         carrinho.removeItem(this.option);
      }
   }

   public void viewCart() {
      if (yourCart()) {
         carrinho.viewAllItems();
         System.out.println("======================================================================");
         System.out.println("Total: R$" + carrinho.totalPurchaseAmount());
      }
   }

   public void closeOrder() {
      if (yourCart()) {
         boolean limparCarrinho = false;

         setAccount();

         System.out.printf("\033c");

         if (isPessoaFisica) {
            System.out.println(accountPF.getUser());
         } else {
            System.out.println(accountPJ.getUser());
         }

         System.out.println();
         carrinho.viewAllItems();

         System.out.println("======================================================================");
         System.out.println("Valor total: " + carrinho.totalPurchaseAmount());
         System.out.println("\n1 - Finalizar compra");
         System.out.println("2 - Cancelar compra");
         option = scanner.nextLine();

         System.out.printf("\033c");

         switch (option) {
            case "1":
               System.out.print("Finalizar pedido [Y] - [n]:");
               option = scanner.nextLine();

               if (option.equals("Y") || option.equals("y")) {
                  limparCarrinho = true;
                  finalizePurchase();
               }
               break;
            case "2":
               System.out.println("Sentimos muito que não deseja continuar. :(");
               limparCarrinho = true;
               break;
            default:
               System.out.println("Opção inválida!");
               break;
         }

         if (limparCarrinho) {
            carrinho.removeAll();
         }
      }
   }

   public void setAccount() {
      if (accountPF == null && accountPJ == null) {
         System.out.printf("\033c");
         startAccountService();

         if (isPessoaFisica) {
            while (accountPF.getUser() == null) {
               System.out.printf("\033c");
               accountPF.logIn(null);
            }
         } else {
            while (accountPJ.getUser() == null) {
               System.out.printf("\033c");
               accountPJ.logIn(null);
            }
         }
      }
   }

   public void menu() {
      System.out.printf("\033c");
      System.out.println("1 -  Comprar");
      System.out.println("2 -  Alterar quantidade de um produto");
      System.out.println("3 -  Remover produto do carrinho");
      System.out.println("4 -  Carrinho");
      System.out.println("5 -  Limpar carrinho");
      System.out.println("6 -  Fazer Login");
      System.out.println("7 -  Cadastre-se");
      System.out.println("8 -  Fechar pedido");
      System.out.println("9 -  Minha conta");
      System.out.println("10 - Sair");
      System.out.println("0 - Fechar");
   }

   public boolean yourCart() {
      if (carrinho.getQuantityOfItems() == 0) {
         System.out.println("Não há produtos no carrinho. Adicione produtos a seu carrinho!");
         return false;
      }

      return true;
   }

   public void finalizePurchase() {
      int numeroPedido = new Random().nextInt();
      Pedido novo;

      if (isPessoaFisica) {
         novo = new Pedido(String.valueOf(numeroPedido),
               accountPF.getUser().getCpf().getNumero(), carrinho.getAllItems());
      } else {
         novo = new Pedido(String.valueOf(numeroPedido),
               accountPJ.getUser().getCnpj(), carrinho.getAllItems());
      }

      servicePedido.executa(novo);

      System.out.printf("\033c");
      System.out.println("Agradecemos a preferência! Acompanhe seu pedido: " +
            numeroPedido);
   }
}
