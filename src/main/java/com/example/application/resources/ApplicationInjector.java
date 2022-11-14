package com.example.application.resources;

import java.math.BigDecimal;

import com.example.application.Account;
import com.example.application.Loja;
import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.ServicePessoa;
import com.example.application.interfaces.DI.AppInjector;
import com.example.application.interfaces.DI.RepositoryInjector;
import com.example.application.interfaces.DI.ServiceInjector;
import com.example.application.services.ServiceCart;
import com.example.application.services.ServiceFrete;
import com.example.application.services.ServicePedido;
import com.example.application.services.ServiceProduto;
import com.example.models.VOs.CNPJ;
import com.example.models.VOs.CPF;
import com.example.models.frete.interfaces.RepositorioFrete;
import com.example.models.loja.interfaces.Carrinho;
import com.example.models.loja.interfaces.RepositorioPedido;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;
import com.example.models.produto.interfaces.RepositorioProduto;

public class ApplicationInjector implements AppInjector {

   @Override
   public Loja getLoja(RepositoryInjector repositories, ServiceInjector services) {

      RepositorioProduto produtos = repositories.getRepositorioProduto();
      RepositorioFrete fretes = repositories.getRepositorioFrete();
      RepositorioPedido pedidos = repositories.getRepositorioPedido();
      Carrinho carrinho = repositories.getCarrinho();

      ServiceFrete correios = services.getIserviceFrete(fretes, new BigDecimal("0.4"));
      ServiceProduto serviceProduto = services.getServiceProduto(produtos, correios);
      ServiceCart serviceCart = services.getServiceCart(carrinho, correios);
      PaginaProdutos paginaProdutos = services.getPageProdutos(serviceProduto);
      ServicePedido servicePedido = services.getServicePedido(pedidos);
      Account serviceLogin = getAccount(repositories, services, servicePedido);

      serviceProduto.seed();

      return new Loja(paginaProdutos, serviceCart, serviceLogin, servicePedido);

   }

   @Override
   public Account getAccount(RepositoryInjector repositories, ServiceInjector services, ServicePedido servicePedido) {

      RepositorioPessoa<PessoaFisica, CPF> repoPF = repositories.getRepositorioPF();
      RepositorioPessoa<PessoaJuridica, CNPJ> repoPJ = repositories.getRepositorioPJ();

      ServicePessoa<PessoaFisica, DTOCadastrarPF> servicePF = services.getServicePF(repoPF);
      ServicePessoa<PessoaJuridica, DTOCadastrarPJ> servicePJ = services.getServicePJ(repoPJ);

      return new Account(servicePF, servicePJ, servicePedido);
   }

}
