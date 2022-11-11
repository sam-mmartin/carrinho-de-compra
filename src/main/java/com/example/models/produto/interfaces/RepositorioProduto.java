package com.example.models.produto.interfaces;

import java.util.List;

import com.example.models.produto.Produto;

public interface RepositorioProduto {

   public void cadastrar(Produto objeto);

   public List<Produto> listarTodos();

   public Produto buscarPorId(String uuid);

   public Produto buscarPorDescricao(String descricao);

   public List<Produto> buscarPorMarca(String marca);

   public List<Produto> buscarPorModelo(String modelo);
}
