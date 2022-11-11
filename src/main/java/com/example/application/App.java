package com.example.application;

import java.math.BigDecimal;

import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.interfaces.ILoja;
import com.example.application.interfaces.IServiceCart;
import com.example.application.interfaces.IServiceFrete;
import com.example.application.interfaces.IServiceProduto;
import com.example.application.interfaces.Page;
import com.example.application.interfaces.ServicePessoa;
import com.example.application.interfaces.DI.InjectorInterface;
import com.example.application.resources.Injector;
import com.example.models.EventPublisher;
import com.example.models.VOs.CPF;
import com.example.models.frete.interfaces.RepositorioFrete;
import com.example.models.loja.interfaces.Carrinho;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;
import com.example.models.pessoa.logs.LogPFCadastrada;
import com.example.models.produto.interfaces.RepositorioProduto;

public class App {
   public static void main(String[] args) {
      System.out.println("Hello, World!");

      InjectorInterface injector = new Injector();
      PessoaFisica pessoa = getPessoaFisica(injector);

      RepositorioProduto produtos = injector
            .getRepositoryInjector().getRepositorioProduto();

      RepositorioFrete fretes = injector
            .getRepositoryInjector().getRepositorioFrete();

      Carrinho carrinho = injector
            .getRepositoryInjector().getRepositorioPedido(pessoa);

      IServiceFrete correios = injector
            .getServiceInjector()
            .getIserviceFrete(fretes, new BigDecimal("0.4"));

      IServiceProduto serviceProduto = injector
            .getServiceInjector().getServiceProduto(produtos, correios);

      Page paginaProdutos = injector
            .getServiceInjector().getPageProdutos(serviceProduto);

      IServiceCart serviceCart = injector
            .getServiceInjector().getServiceCart(carrinho, correios);

      ILoja loja = injector
            .getServiceInjector().getLoja(serviceProduto, paginaProdutos, serviceCart);

      loja.run();
   }

   private static PessoaFisica getPessoaFisica(InjectorInterface injector) {
      EventPublisher publicador = new EventPublisher();
      publicador.adicionar(new LogPFCadastrada());

      RepositorioPessoa<PessoaFisica, CPF> repoPF = injector.getRepositoryInjector().getRepositorioPF();
      ServicePessoa<PessoaFisica, DTOCadastrarPF> servicePF = injector
            .getServiceInjector()
            .getServicePF(repoPF, publicador);

      servicePF.seed();
      PessoaFisica pessoa = servicePF.getById("001.002.003-04");

      return pessoa;
   }
}
