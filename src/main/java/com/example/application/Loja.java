package com.example.application;

import java.util.Random;
import java.util.Scanner;

import com.example.application.dto.pedido.ItemPedidoDTO;
import com.example.application.interfaces.ILoja;
import com.example.application.interfaces.IServiceCart;
import com.example.application.interfaces.IServiceProduto;
import com.example.application.interfaces.Page;

public class Loja implements ILoja {

   private String option;
   private int prodOption;
   private int prodQuant;
   private Scanner scanner = new Scanner(System.in);

   private IServiceProduto produtos;
   private Page paginaProdutos;
   private IServiceCart carrinho;

   public Loja(IServiceProduto produtos, Page paginaProdutos, IServiceCart carrinho) {
      this.produtos = produtos;
      this.paginaProdutos = paginaProdutos;
      this.carrinho = carrinho;
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
               closeOrder();
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
      option = scanner.nextLine();
      prodOption = Integer.parseInt(option);

      System.out.print("\nInforme a quantidade: ");
      option = scanner.nextLine();
      prodQuant = Integer.parseInt(option);

      ItemPedidoDTO item = paginaProdutos.selecionarProduto(prodOption, prodQuant);
      carrinho.addItem(item.criarItemPedido());
   }

   @Override
   public void updateCartProduct() {
      if (yourCart()) {
         carrinho.getAllItems();

         System.out.print("Selecione o produto que deseja: ");
         option = scanner.nextLine();
         String uuid = option;

         System.out.print("\nInforme a quantidade: ");
         option = scanner.nextLine();
         prodQuant = Integer.parseInt(option);

         carrinho.updateItem(uuid, prodQuant);
      }
   }

   @Override
   public void removeProductFromCart() {
      if (yourCart()) {
         carrinho.getAllItems();

         System.out.print("Selecione o produto que deseja: ");
         option = scanner.nextLine();

         carrinho.removeItem(option);
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
         System.out.println(carrinho.getCliente());
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
      System.out.println("5 - Fechar pedido");
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
