package com.example.application.interfaces;

import java.util.List;

import com.example.application.dto.produto.DTOCadastrarProduto;
import com.example.models.produto.Produto;

public interface IServiceProduto {

   public List<Produto> getAll();

   public Produto getById(String uuid);

   public Produto getProdutoPorDescricao(String descricao);

   public List<Produto> getProdutoPorMarca(String marca);

   public List<Produto> getProdutoPorModelo(String modelo);

   public void executa(DTOCadastrarProduto objeto);

   public void seed();

}
