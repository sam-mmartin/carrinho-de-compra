package com.example.application.resources;

import com.example.application.interfaces.DI.RepositoryInjector;
import com.example.infrastructure.RepositorioDeFrete;
import com.example.infrastructure.RepositorioDePF;
import com.example.infrastructure.RepositorioDePJ;
import com.example.infrastructure.RepositorioDeProduto;
import com.example.infrastructure.cart.Cart;
import com.example.models.VOs.CNPJ;
import com.example.models.VOs.CPF;
import com.example.models.frete.interfaces.RepositorioFrete;
import com.example.models.loja.interfaces.Carrinho;
import com.example.models.pessoa.Pessoa;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;
import com.example.models.produto.interfaces.RepositorioProduto;

public class RepositoryDependencyInjector implements RepositoryInjector {

   @Override
   public RepositorioPessoa<PessoaFisica, CPF> getRepositorioPF() {
      return new RepositorioDePF();
   }

   @Override
   public RepositorioPessoa<PessoaJuridica, CNPJ> getRepositorioPJ() {
      return new RepositorioDePJ();
   }

   @Override
   public RepositorioProduto getRepositorioProduto() {
      return new RepositorioDeProduto();
   }

   @Override
   public RepositorioFrete getRepositorioFrete() {
      return new RepositorioDeFrete();
   }

   @Override
   public Carrinho getRepositorioPedido(Pessoa cliente) {
      return new Cart(cliente);
   }

}
