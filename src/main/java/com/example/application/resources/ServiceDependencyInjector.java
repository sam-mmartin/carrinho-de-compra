package com.example.application.resources;

import java.math.BigDecimal;

import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.ServicePessoa;
import com.example.application.interfaces.DI.ServiceInjector;
import com.example.application.services.ServiceCart;
import com.example.application.services.ServiceFrete;
import com.example.application.services.ServicePF;
import com.example.application.services.ServicePJ;
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

public class ServiceDependencyInjector implements ServiceInjector {

   @Override
   public ServicePessoa<PessoaFisica, DTOCadastrarPF> getServicePF(RepositorioPessoa<PessoaFisica, CPF> pessoas) {
      return new ServicePF(pessoas);
   }

   @Override
   public ServicePessoa<PessoaJuridica, DTOCadastrarPJ> getServicePJ(
         RepositorioPessoa<PessoaJuridica, CNPJ> pessoas) {
      return new ServicePJ(pessoas);
   }

   @Override
   public ServiceProduto getServiceProduto(RepositorioProduto produtos, ServiceFrete correios) {
      return new ServiceProduto(produtos, correios);
   }

   @Override
   public PaginaProdutos getPageProdutos(ServiceProduto serviceProduto) {
      return new PaginaProdutos(serviceProduto);
   }

   @Override
   public ServiceCart getServiceCart(Carrinho carrinho, ServiceFrete correios) {
      return new ServiceCart(carrinho, correios);
   }

   @Override
   public ServiceFrete getIserviceFrete(RepositorioFrete correios, BigDecimal desconto) {
      return new ServiceFrete(correios, desconto);
   }

   @Override
   public ServicePedido getServicePedido(RepositorioPedido pedidos) {
      return new ServicePedido(pedidos);
   }

}
