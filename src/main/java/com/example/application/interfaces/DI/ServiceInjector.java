package com.example.application.interfaces.DI;

import java.math.BigDecimal;

import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.ILoja;
import com.example.application.interfaces.IServiceCart;
import com.example.application.interfaces.IServiceFrete;
import com.example.application.interfaces.IServiceProduto;
import com.example.application.interfaces.Page;
import com.example.application.interfaces.ServicePessoa;
import com.example.models.EventPublisher;
import com.example.models.VOs.CNPJ;
import com.example.models.VOs.CPF;
import com.example.models.frete.interfaces.RepositorioFrete;
import com.example.models.loja.interfaces.Carrinho;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;
import com.example.models.produto.interfaces.RepositorioProduto;

public interface ServiceInjector {

      public ServicePessoa<PessoaFisica, DTOCadastrarPF> getServicePF(RepositorioPessoa<PessoaFisica, CPF> pessoas,
                  EventPublisher publicador);

      public ServicePessoa<PessoaJuridica, DTOCadastrarPJ> getServicePJ(
                  RepositorioPessoa<PessoaJuridica, CNPJ> pessoas);

      public IServiceProduto getServiceProduto(RepositorioProduto produtos, IServiceFrete correios);

      public Page getPageProdutos(IServiceProduto service);

      public IServiceCart getServiceCart(Carrinho carrinho, IServiceFrete correios);

      public ILoja getLoja(IServiceProduto serviceProdutos, Page paginaProdutos, IServiceCart carrinho);

      public IServiceFrete getIserviceFrete(RepositorioFrete correios, BigDecimal desconto);
}
