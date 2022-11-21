package com.example.application;

import java.util.List;
import java.util.Scanner;

import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.interfaces.Account;
import com.example.application.interfaces.ServicePessoa;
import com.example.application.services.ServicePedido;
import com.example.models.loja.pedido.Pedido;
import com.example.models.pessoa.PessoaFisica;

public class AccountPF implements Account {

   private ServicePessoa<PessoaFisica, DTOCadastrarPF> servicePF;
   private ServicePedido servicePedido;
   private PessoaFisica user;

   private Scanner scanner;
   private String option;

   public AccountPF(ServicePessoa<PessoaFisica, DTOCadastrarPF> servicePF,
         ServicePedido servicePedido) {
      this.servicePF = servicePF;
      this.servicePedido = servicePedido;

      servicePF.seed();
   }

   public PessoaFisica getUser() {
      return user;
   }

   private void menu() {
      System.out.println("1 - Dados da conta");
      System.out.println("2 - Sair");
      System.out.println("3 - Apagar minha conta");
      System.out.println("0 - Voltar");
   }

   @Override
   public void userAccount() {
      if (user != null) {
         scanner = new Scanner(System.in);

         do {
            System.out.printf("\033c");
            menu();
            option = scanner.nextLine();
            System.out.printf("\033c");

            switch (option) {
               case "1":
                  viewUserInfos();
                  break;

               case "2":
                  logOut();
                  break;

               case "3":
                  singOut();
                  logOut();
                  option = "exit";
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
      } else {
         System.out.println("Realize login para acessar sua conta.");
      }
   }

   @Override
   public void viewUserInfos() {
      List<Pedido> pedidos = servicePedido.getAll(user.getCpf().getNumero());

      System.out.printf("\033c");
      System.out.println(user);
      System.out.println("PEDIDOS");

      if (pedidos != null) {
         pedidos.forEach(p -> {
            System.out.println("Pedido: " + p.getIdPedido());
            p.getItens().forEach(i -> System.out.println(i));
            System.out.println("=====================================================================");
         });
      }
   }

   @Override
   public void singIn(String id) {
      boolean cadastrou = false;

      System.out.printf("\033c");
      System.out.println("------- Cadastre-se --------");

      if (id == null) {
         System.out.print("Informe seu CPF: ");
         id = scanner.nextLine();
      } else {
         System.out.println("CPF: " + id);
      }

      System.out.print("Informe seu e-mail:");
      String email = scanner.nextLine();

      System.out.print("Informe seu nome: ");
      String nome = scanner.nextLine();

      try {
         DTOCadastrarPF pf = new DTOCadastrarPF(nome, id, email);
         setUser(pf);
         cadastrou = true;
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }

      if (!cadastrou) {
         System.out.println("Não foi possível concluir o cadastro. Tente novamente.");
      }
   }

   @Override
   public void logIn(String id) {
      try {
         if (id == null) {
            System.out.print("Informe seu CPF: ");
            id = scanner.nextLine();
         }

         user = servicePF.getById(id);

         if (user != null) {
            System.out.println("Você entrou. Boas Compras.");
         }
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         singIn(id);
      }
   }

   @Override
   public void singOut() {
      servicePF.remove(user);
      System.out.println("Sua conta foi apagada.");
   }

   @Override
   public void logOut() {
      System.out.println("Você saiu!");
      user = null;
   }

   @Override
   public <T> void setUser(T user) {
      DTOCadastrarPF pf = (DTOCadastrarPF) user;
      servicePF.executa(pf);
   }

}
