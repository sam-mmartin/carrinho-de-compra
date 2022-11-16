package com.example.application;

import java.util.Scanner;

import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.Account;
import com.example.application.interfaces.ServicePessoa;
import com.example.application.services.ServicePedido;
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

   @Override
   public void userAccount() {
      // TODO Auto-generated method stub

   }

   @Override
   public void viewUserInfos() {
      // TODO Auto-generated method stub

   }

   @Override
   public void singIn(String id) {
      // TODO Auto-generated method stub

   }

   @Override
   public void logIn(String id) {
      // TODO Auto-generated method stub

   }

   @Override
   public void singOut() {
      // TODO Auto-generated method stub

   }

   @Override
   public void logOut() {
      // TODO Auto-generated method stub

   }

   @Override
   public <T> void setUser(T user) {
      // TODO Auto-generated method stub

   }

}
