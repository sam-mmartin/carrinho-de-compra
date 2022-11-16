package com.example.application;

import java.util.List;
import java.util.Scanner;

import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.Account;
import com.example.application.interfaces.ServicePessoa;
import com.example.application.services.ServicePedido;
import com.example.models.loja.pedido.Pedido;
import com.example.models.pessoa.PessoaJuridica;

public class AccountPJ implements Account {

   private ServicePessoa<PessoaJuridica, DTOCadastrarPJ> servicePJ;
   private ServicePedido servicePedido;
   private PessoaJuridica user;

   private Scanner scanner = new Scanner(System.in);
   private String option;

   public AccountPJ(ServicePessoa<PessoaJuridica, DTOCadastrarPJ> servicePJ,
         ServicePedido servicePedido) {
      this.servicePJ = servicePJ;
      this.servicePedido = servicePedido;

      servicePJ.seed();
   }

   public PessoaJuridica getUser() {
      return user;
   }

   public void menu() {
      System.out.println("1 - Dados da conta");
      System.out.println("2 - Sair");
      System.out.println("3 - Apagar minha conta");
      System.out.println("0 - Voltar");
   }

   public void userAccount() {
      if (user != null) {
         do {
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
            System.out.printf("\033c");
         } while (!option.equals("exit"));
      } else {
         System.out.println("Realize login para acessar sua conta.");
      }
   }

   public void viewUserInfos() {
      List<Pedido> pedidos = servicePedido.getAll(user.getCnpj());

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

   public void singIn(String id) {
      boolean cadastrou = false;

      System.out.printf("\033c");
      System.out.println("------- Cadastre-se --------");

      if (id == null) {
         System.out.print("Informe seu CNPJ: ");
         id = scanner.nextLine();
      } else {
         System.out.println("CNPJ: " + id);
      }

      System.out.print("Informe o Nome Fantasia: ");
      String nomeFantasia = scanner.nextLine();

      System.out.print("Informe a Razão Social: ");
      String razaoSocial = scanner.nextLine();

      System.out.print("Informe seu e-mail:");
      String email = scanner.nextLine();

      System.out.print("Informe a data de constituição da empresa: ");
      String dataCriacao = scanner.nextLine();

      try {
         DTOCadastrarPJ pj = new DTOCadastrarPJ(id, nomeFantasia, razaoSocial, email, dataCriacao);
         setUser(pj);
         cadastrou = true;
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }

      if (!cadastrou) {
         System.out.println("Não foi possível concluir o cadastro. Tente novamente.");
      }
   }

   public void logIn(String id) {
      try {
         if (id == null) {
            System.out.print("Informe seu CPF: ");
            id = scanner.nextLine();
         }

         user = servicePJ.getById(id);

         if (user != null) {
            System.out.println("Você entrou. Boas Compras.");
         }
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         singIn(id);
      }
   }

   public void singOut() {
      servicePJ.remove(user);
      System.out.println("Sua conta foi apagada.");
   }

   public void logOut() {
      System.out.println("Você saiu!");
      user = null;
   }

   @Override
   public <T> void setUser(T user) {
      DTOCadastrarPJ pj = (DTOCadastrarPJ) user;
      servicePJ.executa(pj);
   }

}
