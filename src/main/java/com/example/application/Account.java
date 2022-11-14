package com.example.application;

import java.util.List;
import java.util.Scanner;

import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.IAccount;
import com.example.application.interfaces.IServicePedido;
import com.example.application.interfaces.ServicePessoa;
import com.example.models.loja.pedido.Pedido;
import com.example.models.pessoa.Pessoa;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.PessoaJuridica;

public class Account implements IAccount {

   private ServicePessoa<PessoaFisica, DTOCadastrarPF> servicePF;
   private ServicePessoa<PessoaJuridica, DTOCadastrarPJ> servicePJ;
   private IServicePedido servicePedido;

   private Scanner scanner = new Scanner(System.in);
   private String option;

   public Account(ServicePessoa<PessoaFisica, DTOCadastrarPF> servicePF,
         ServicePessoa<PessoaJuridica, DTOCadastrarPJ> servicePJ,
         IServicePedido servicePedido) {
      this.servicePF = servicePF;
      this.servicePJ = servicePJ;
      this.servicePedido = servicePedido;

      servicePF.seed();
      servicePJ.seed();
   }

   @Override
   public Pessoa userAccount(Pessoa user) {
      if (user != null) {
         do {
            menu();
            option = scanner.nextLine();
            System.out.printf("\033c");

            switch (option) {
               case "1":
                  viewUserInfos(user);
                  break;

               case "2":
                  user = logOut();
                  break;

               case "3":
                  singOut(user, user.getId().length());
                  user = logOut();
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

      return user;
   }

   @Override
   public void viewUserInfos(Pessoa user) {
      List<Pedido> pedidos = servicePedido.getAll(user.getId());

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
         System.out.print("Informe seu CPF/CNPJ: ");
         id = scanner.nextLine();
      } else {
         System.out.println("CPF/CNPJ: " + id);
      }

      System.out.print("Informe seu e-mail:");
      String email = scanner.nextLine();

      switch (id.length()) {
         case 14:
            System.out.print("Informe seu nome: ");
            String nome = scanner.nextLine();

            try {
               DTOCadastrarPF pf = new DTOCadastrarPF(nome, id, email);
               setUser(pf, id.length());
               cadastrou = true;
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            break;
         case 18:
            System.out.println("Informe a Razão Social: ");
            String razaoSocial = scanner.nextLine();

            System.out.println("Informe o Nome Fantasia: ");
            String nomeFantasia = scanner.nextLine();

            System.out.println("Informe a Data de abertura: ");
            String dataAbertura = scanner.nextLine();

            try {
               DTOCadastrarPJ pj = new DTOCadastrarPJ(id, nomeFantasia, razaoSocial, email, dataAbertura);
               setUser(pj, id.length());
               cadastrou = true;
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
         default:
            break;
      }

      if (!cadastrou) {
         System.out.println("Não foi possível concluir o cadastro. Tente novamente.");
      }
   }

   @Override
   public Pessoa logIn() {
      System.out.print("Informe seu CPF/CNPJ: ");
      this.option = scanner.nextLine();

      try {
         Pessoa user = getUser(this.option.length());
         if (user != null) {
            System.out.println("Você entrou. Boas Compras.");
         }
         return user;
      } catch (RuntimeException e) {
         System.out.println(e.getMessage());
         singIn(this.option);
         return null;
      }
   }

   @Override
   public <T> void singOut(T user, int tipo) {
      switch (tipo) {
         case 14:
            servicePF.remove((PessoaFisica) user);
            break;
         case 18:
            servicePJ.remove((PessoaJuridica) user);
            break;
         default:
            break;
      }

      System.out.println("Sua conta foi apagada.");
   }

   @Override
   public Pessoa logOut() {
      System.out.println("Você saiu!");
      return null;
   }

   @Override
   public <T> void setUser(T user, int tipo) {
      switch (tipo) {
         case 14:
            DTOCadastrarPF pf = (DTOCadastrarPF) user;
            servicePF.executa(pf);
            break;
         case 18:
            DTOCadastrarPJ pj = (DTOCadastrarPJ) user;
            servicePJ.executa(pj);
            break;
         default:
            break;
      }
   }

   @Override
   public Pessoa getUser(int tipo) {
      switch (tipo) {
         case 14:
            return servicePF.getById(option);
         case 18:
            return servicePJ.getById(option);
         default:
            return null;
      }
   }

   public void menu() {
      System.out.println("1 - Dados da conta");
      System.out.println("2 - Sair");
      System.out.println("3 - Apagar minha conta");
      System.out.println("0 - Voltar");
   }
}
