package com.example.application.resources;

import com.example.application.AccountPF;
import com.example.application.AccountPJ;
import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.ServicePessoa;
import com.example.application.interfaces.DI.AccountInjector;
import com.example.application.interfaces.DI.RepositoryInjector;
import com.example.application.interfaces.DI.ServiceInjector;
import com.example.application.services.ServicePedido;
import com.example.models.VOs.CNPJ;
import com.example.models.VOs.CPF;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;

public class UserInjector implements AccountInjector {

   private RepositoryInjector repositories;
   private ServiceInjector services;

   public UserInjector(RepositoryInjector repositories, ServiceInjector services) {
      this.repositories = repositories;
      this.services = services;
   }

   @Override
   public AccountPF getAccountPF(ServicePedido servicePedido) {
      RepositorioPessoa<PessoaFisica, CPF> repoPF = repositories.getRepositorioPF();
      ServicePessoa<PessoaFisica, DTOCadastrarPF> servicePF = services.getServicePF(repoPF);

      return new AccountPF(servicePF, servicePedido);
   }

   @Override
   public AccountPJ getAccountPJ(ServicePedido servicePedido) {
      RepositorioPessoa<PessoaJuridica, CNPJ> repoPJ = repositories.getRepositorioPJ();
      ServicePessoa<PessoaJuridica, DTOCadastrarPJ> servicePJ = services.getServicePJ(repoPJ);

      return new AccountPJ(servicePJ, servicePedido);
   }

}
