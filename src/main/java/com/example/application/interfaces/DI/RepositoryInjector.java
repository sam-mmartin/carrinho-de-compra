package com.example.application.interfaces.DI;

import com.example.models.VOs.CNPJ;
import com.example.models.VOs.CPF;
import com.example.models.frete.interfaces.RepositorioFrete;
import com.example.models.loja.interfaces.Carrinho;
import com.example.models.loja.interfaces.RepositorioPedido;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;
import com.example.models.produto.interfaces.RepositorioProduto;

public interface RepositoryInjector {

   public RepositorioPessoa<PessoaFisica, CPF> getRepositorioPF();

   public RepositorioPessoa<PessoaJuridica, CNPJ> getRepositorioPJ();

   public RepositorioProduto getRepositorioProduto();

   public RepositorioFrete getRepositorioFrete();

   public RepositorioPedido getRepositorioPedido();

   public Carrinho getCarrinho();

}
