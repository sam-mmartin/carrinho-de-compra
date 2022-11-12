package com.example.application;

import java.util.Random;
import java.util.Scanner;

import com.example.application.dto.pedido.ItemPedidoDTO;
import com.example.application.interfaces.ILogin;
import com.example.application.interfaces.ILoja;
import com.example.application.interfaces.IServiceCart;
import com.example.application.interfaces.IServiceProduto;
import com.example.application.interfaces.Page;
import com.example.models.pessoa.Pessoa;

public class Loja implements ILoja {

   private String option;
   private Scanner scanner = new Scanner(System.in);
   private Pessoa cliente;

   private IServiceProduto produtos;
   private Page paginaProdutos;
   private IServiceCart carrinho;
   private ILogin serviceLogin;

   public Loja(IServiceProduto produtos, Page paginaProdutos, IServiceCart carrinho, ILogin serviceLogin) {
      this.produtos = produtos;
      this.paginaProdutos = paginaProdutos;
      this.carrinho = carrinho;
      this.serviceLogin = serviceLogin;
   }

   @Override
   public void run() {

      produtos.seed();
      paginaProdutos.seed();

      do {
         menu();
         option = scanner.nextLine();
         System.out.printf("\033c");

         switch (option) {
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
               this.cliente = serviceLogin.logIn();
               break;
            case "7":
               serviceLogin.singIn(null);
               break;
            case "8":
               closeOrder();
               break;
            case "9":
               serviceLogin.userAccount(cliente);
               break;
            case "0":
               option = "exit";
               break;
            default:
               System.out.println("Opção inválida.");
               break;
         }

         scanner.nextLine();
      } while (!option.equals("exit"));
   }

   @Override
   public void addProductToCart() {
      paginaProdutos.listarTodos();

      System.out.print("Escolha o produto que deseja: ");
      this.option = scanner.nextLine();
      int prodOption = Integer.parseInt(this.option);

      System.out.print("\nInforme a quantidade: ");
      this.option = scanner.nextLine();
      int prodQuant = Integer.parseInt(this.option);

      ItemPedidoDTO item = paginaProdutos.selecionarProduto(prodOption, prodQuant);
      carrinho.addItem(item.criarItemPedido());
   }

   @Override
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

   @Override
   public void removeProductFromCart() {
      if (yourCart()) {
         carrinho.getAllItems();

         System.out.print("Selecione o produto que deseja: ");
         this.option = scanner.nextLine();

         carrinho.removeItem(this.option);
      }
   }

   @Override
   public void viewCart() {
      if (yourCart()) {
         carrinho.getAllItems();
         System.out.println("======================================================================");
         System.out.println("Total: R$" + carrinho.totalPurchaseAmount());
      }
   }

   @Override
   public void closeOrder() {
      if (yourCart()) {
         boolean limparCarrinho = false;

         if (cliente == null) {
            serviceLogin.logIn();
         }

         System.out.printf("\033c");
         System.out.println(cliente);
         carrinho.getAllItems();

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
                  int numeroPedido = new Random().nextInt();
                  System.out.printf("\033c");
                  System.out.println("Agradecemos a preferência! Acompanhe seu pedido: " + numeroPedido);
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

   @Override
   public void menu() {
      System.out.printf("\033c");
      System.out.println("1 - Comprar");
      System.out.println("2 - Alterar quantidade de um produto");
      System.out.println("3 - Remover produto do carrinho");
      System.out.println("4 - Carrinho");
      System.out.println("5 - Limpar carrinho");
      System.out.println("6 - Fazer Login");
      System.out.println("7 - Cadastre-se");
      System.out.println("8 - Fechar pedido");
      System.out.println("9 - Minha conta");
      System.out.println("0 - Sair");
   }

   public boolean yourCart() {
      if (carrinho.getQuantityOfItems() == 0) {
         System.out.println("Não há produtos no carrinho. Adicione produtos a seu carrinho!");
         return false;
      }

      return true;
   }
}
