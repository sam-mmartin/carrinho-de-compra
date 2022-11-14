package com.example.application.interfaces.DI;

import java.math.BigDecimal;

import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.ServicePessoa;
import com.example.application.resources.PaginaProdutos;
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

public interface ServiceInjector {

      public ServicePessoa<PessoaFisica, DTOCadastrarPF> getServicePF(RepositorioPessoa<PessoaFisica, CPF> pessoas);

      public ServicePessoa<PessoaJuridica, DTOCadastrarPJ> getServicePJ(
                  RepositorioPessoa<PessoaJuridica, CNPJ> pessoas);

      public ServiceProduto getServiceProduto(RepositorioProduto produtos, ServiceFrete correios);

      public PaginaProdutos getPageProdutos(ServiceProduto service);

      public ServiceCart getServiceCart(Carrinho carrinho, ServiceFrete correios);

      public ServiceFrete getIserviceFrete(RepositorioFrete correios, BigDecimal desconto);

      public ServicePedido getServicePedido(RepositorioPedido pedidos);
}
