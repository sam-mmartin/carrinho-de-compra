package com.example.application.resources;

import java.math.BigDecimal;
import com.example.application.Login;
import com.example.application.Loja;
import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.ILogin;
import com.example.application.interfaces.ILoja;
import com.example.application.interfaces.IServiceCart;
import com.example.application.interfaces.IServiceFrete;
import com.example.application.interfaces.IServiceProduto;
import com.example.application.interfaces.Page;
import com.example.application.interfaces.ServicePessoa;
import com.example.application.interfaces.DI.AppInjector;
import com.example.application.interfaces.DI.RepositoryInjector;
import com.example.application.interfaces.DI.ServiceInjector;
import com.example.models.VOs.CNPJ;
import com.example.models.VOs.CPF;
import com.example.models.frete.interfaces.RepositorioFrete;
import com.example.models.loja.interfaces.Carrinho;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;
import com.example.models.produto.interfaces.RepositorioProduto;

public class ApplicationInjector implements AppInjector {

   @Override
   public ILoja getLoja(RepositoryInjector repositories, ServiceInjector services) {

      RepositorioProduto produtos = repositories.getRepositorioProduto();
      RepositorioFrete fretes = repositories.getRepositorioFrete();
      Carrinho carrinho = repositories.getRepositorioPedido();

      IServiceFrete correios = services.getIserviceFrete(fretes, new BigDecimal("0.4"));
      IServiceProduto serviceProduto = services.getServiceProduto(produtos, correios);
      IServiceCart serviceCart = services.getServiceCart(carrinho, correios);
      Page paginaProdutos = services.getPageProdutos(serviceProduto);
      ILogin serviceLogin = getLogin(repositories, services);

      return new Loja(serviceProduto, paginaProdutos, serviceCart, serviceLogin);

   }

   @Override
   public ILogin getLogin(RepositoryInjector repositories, ServiceInjector services) {

      RepositorioPessoa<PessoaFisica, CPF> repoPF = repositories.getRepositorioPF();
      RepositorioPessoa<PessoaJuridica, CNPJ> repoPJ = repositories.getRepositorioPJ();

      ServicePessoa<PessoaFisica, DTOCadastrarPF> servicePF = services.getServicePF(repoPF);
      ServicePessoa<PessoaJuridica, DTOCadastrarPJ> servicePJ = services.getServicePJ(repoPJ);

      return new Login(servicePF, servicePJ);
   }

}
